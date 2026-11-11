from typing import Dict, List, Any

from framework.common.constants import KEY_SEPARATOR, EU28_MEMBERS
from framework.fetching.fetch_utils import ParamsNames, filter_params_keys
from framework.processing.local_parser import read_json_file, get_dimension_keys
from framework.processing.map_utils import sort_key, get_entry_code


class MergeUtils:
    """Utility class for merging and consolidating data maps."""

    @staticmethod
    def consolidate_map(params: Dict[str, List[str]], file_path: str) -> Dict[str, Any]:
        """
        Prepare data based on a set of parameters values (change the key name).

        E.g.:
            {
                [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2015]: 64,
                [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2016]: 65,
                [I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2017]: 67,
                [I_DSK_BAB, IND_TOTAL, PC_IND, BE, 2015]: 60,
                ...
            }

        will be processed to:
            {
                AT_2015: 64,
                AT_2016: 65,
                AT_2017: 67,
                BE_2015: 60,
                ...
            }

        Args:
            params: The global allowed query values (the allowed query values
                    excepting the year and the country code)
            file_path: The full access path to the desired file

        Returns:
            Sorted dict with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
        """
        consolidated_list = {}
        entries = read_json_file(file_path)

        local_query_keys = get_dimension_keys(file_path)
        country_index = local_query_keys.index(ParamsNames.GEO)
        year_index = local_query_keys.index(ParamsNames.TIME)

        for entry_key, value in entries.items():
            # entry_values = the queried values for parameters
            # Example:
            #      parameters: [unit, sex, isced11, age, geo, time]
            #      query_entries: [PC, T, ED5-8, Y15-64, UK, 2017]
            local_query_values = list(entry_key)

            country = local_query_values[country_index]
            year = int(local_query_values[year_index])

            if MergeUtils._is_param_included(params, local_query_keys, local_query_values):
                key = f"{country}{KEY_SEPARATOR}{year}"
                consolidated_list[key] = value

        return dict(sorted(consolidated_list.items(), key=sort_key))

    @staticmethod
    def _is_param_included(params: Dict[str, List[str]],
                           local_query_keys: List[str],
                           local_query_values: List[str]) -> bool:
        """
        Check if the current iterated key-value pair (local_query_keys and local_query_values)
        is included in the list of key-value pairs used to fetch data (params).

        Args:
            params: List of parameters used to fetch data (E.g.: AVG_WORKING_HOURS_2007_PARAMS, etc.)
            local_query_keys: List of local parameters (taken from data stored on disk)
            local_query_values: List of local values (taken from data stored on disk)

        Returns:
            True/False
        """
        params_keys = filter_params_keys(params)

        for param_key in params_keys:
            local_index = local_query_keys.index(param_key)
            local_value = local_query_values[local_index]
            param_value = next(iter(params[param_key]))

            if local_value != param_value:
                return False

        return True

    @staticmethod
    def consolidate_maps(maps_list: List[Dict[str, Any]]) -> Dict[str, Any]:
        """
        Consolidate a list of maps into a single one.

        Args:
            maps_list: The list of maps

        Returns:
            Sorted dict with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
        """
        prepared_map = {}

        # Iterate over EU28_MEMBERS in order to add the entries by country code into the ordered map
        for code in EU28_MEMBERS:
            for map_item in maps_list:
                for entry_key, entry_value in map_item.items():
                    entry_code = get_entry_code((entry_key, entry_value))

                    if code == entry_code and entry_key not in prepared_map:
                        prepared_map[entry_key] = entry_value

        return dict(sorted(prepared_map.items(), key=sort_key))
