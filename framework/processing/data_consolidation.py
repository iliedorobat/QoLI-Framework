from typing import Any

from framework.common.constants import KEY_SEPARATOR, EU28_MEMBERS
from framework.fetching.fetch_utils import filter_params_keys, ParamsNames
from framework.processing.local_parser import read_json_file, dataset_to_map
from framework.processing.map_utils import sort_key, get_entry_code


class MergeUtils:
    """Utility class for merging and consolidating data maps."""

    @staticmethod
    def consolidate_map(
            params: dict[str, list[str]],
            file_path: str
    ) -> dict[str, Any]:
        """
        Prepare data based on a set of parameters values (change the key name).

        E.g.:
            Input:
            {
                (I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2015): 64,
                (I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2016): 65,
                (I_DSK_BAB, IND_TOTAL, PC_IND, AT, 2017): 67,
                (I_DSK_BAB, IND_TOTAL, PC_IND, BE, 2015): 60,
                ...
            }

            Output:
            {
                "AT_2015": 64,
                "AT_2016": 65,
                "AT_2017": 67,
                "BE_2015": 60,
                ...
            }

        Args:
            params: The parameters used to fetch data (e.g., AVG_WORKING_HOURS_2007_PARAMS).
                    Only entries matching all specified parameter values are included.
            file_path: The full access path to the JSON file

        Returns:
            Sorted dict with COUNTRY_CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
        """
        consolidated_list = {}
        dataset = read_json_file(file_path)
        json_data = dataset_to_map(dataset)

        local_query_keys = dataset.get("id")
        country_index = local_query_keys.index(ParamsNames.GEO)
        year_index = local_query_keys.index(ParamsNames.TIME)

        for entry_key, value in json_data.items():
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
    def _is_param_included(
            params: dict[str, list[str]],
            local_query_keys: list[str],
            local_query_values: list[str]
    ) -> bool:
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
    def consolidate_maps(maps_list: list[dict[str, Any]]) -> dict[str, Any]:
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
