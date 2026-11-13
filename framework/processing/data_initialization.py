"""
Basic methods for initializing lists (add "null" value for the missing keys)
"""

from typing import Any

from framework.common.constants import EU28_MEMBERS
from framework.fetching.fetch_utils import INIT_MAP_MIN_YEAR, INIT_MAP_MAX_YEAR
from framework.processing.data_consolidation import MergeUtils
from framework.processing.map_utils import generate_key


class Initializer:
    """Basic methods for initializing maps with default values for missing keys."""

    @staticmethod
    def init_map(
            original_map: dict[str, Any],
            country_codes: list[str] = None
    ) -> dict[str, Any]:
        """
        Create a new sorted map with values for all the possible keys for a LEVERAGE PERIOD
        OF TIME.

        A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period (required
        if in the analyzed period there is no data for a country code).
        If the key is missing from the original map, set a default value (None).
        A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)

        Args:
            original_map: The original input map
            country_codes: The list of country codes (defaults to EU28_MEMBERS)

        Returns:
            A new sorted map with no missing keys
        """
        if country_codes is None:
            country_codes = EU28_MEMBERS

        init_map = {}

        for code in country_codes:
            for year in range(INIT_MAP_MIN_YEAR, INIT_MAP_MAX_YEAR + 1):
                Initializer._init_empty_data(original_map, init_map, code, year)

        return dict(sorted(init_map.items()))

    @staticmethod
    def init_consolidated_maps(maps_list: list[dict[str, Any]]) -> dict[str, Any]:
        """
        Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
        PERIOD OF TIME.

        A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period (required
        if in the analyzed period there is no data for a country code).
        If the key is missing from the original map, set a default value (None).
        A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)

        Args:
            maps_list: The list of maps

        Returns:
            A new sorted map with no missing keys
        """
        consolidated_list = MergeUtils.consolidate_maps(maps_list)
        return Initializer.init_map(consolidated_list)

    @staticmethod
    def init_consolidated_map(
            params: dict[str, list[str]],
            file_path: str,
            country_codes: list[str] = None
    ) -> dict[str, Any] | None:
        """
        Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
        PERIOD OF TIME.

        A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period (required
        if in the analyzed period there is any data for a country code).

        Args:
            params: The global allowed query values (the allowed query values
                   excepting the year and the country code)
            file_path: The full access path to the desired file
            country_codes: The list of country codes (defaults to EU28_MEMBERS)

        Returns:
            A new sorted consolidated map with no missing keys
        """
        if country_codes is None:
            country_codes = EU28_MEMBERS

        try:
            consolidated_list = MergeUtils.consolidate_map(params, file_path)
            return Initializer.init_map(consolidated_list, country_codes)
        except Exception as e:
            print(f"Error: {file_path}\n{params}", file=__import__('sys').stderr)
            return None

    @staticmethod
    def _init_empty_data(
            original_map: dict[str, Any],
            init_map: dict[str, Any],
            code: str,
            year: int
    ) -> None:
        """
        Add a default value for the key that is missing from the original map.

        Args:
            original_map: The original input map
            init_map: The new map which is built by adding all the key-value pairs for the analysed period
            code: The country code for which the calculation is done
            year: The year for which the calculation is done
        """
        key = generate_key(code, year)

        for entry_key, entry_value in original_map.items():
            if entry_key == key:
                init_map[key] = entry_value
                return

        if key not in init_map:
            init_map[key] = None
