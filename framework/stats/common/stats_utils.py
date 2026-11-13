import math
from collections import OrderedDict
from typing import Optional, Any

from framework.common.constants import *
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.processing.map_utils import get_entry_code, get_entry_year, generate_key
from framework.processing.math_utils import MathUtils


class StatsUtils:
    """
    Utility class for statistical aggregation and processing.
    """

    @staticmethod
    def generate_stats(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
            main_aggregator: str,
            allowed_aggr_list: list[str],
            reversed_map: dict[str, bool],
            prepared_indicators: dict[str, dict[str, Any]]
    ) -> dict[str, Any]:
        """
        Aggregate the result of the target indicators (aggr_list) into a single value.
        The target indicators are filtered based on a specific series of indicators
        which describe a dimension (allowed_aggr_list).

        Args:
            aggr_list: List of aggregation params
            country_codes: List of analyzed country codes (E.g.: ["AT", "BE", "RO"])
            start_year: The year from which the analysis begins
            end_year: The year in which the analysis ends
            main_aggregator: The aggregator describing the target dimension
            allowed_aggr_list: List of aggregation params specific to the target dimension
            reversed_map: Map containing specifications about which indicator describes a negative state
            prepared_indicators: Map that contains the prepared indicators specific to the target dimension

        Returns:
            Sorted dictionary with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015)
        """
        if not country_codes:
            country_codes = EU28_MEMBERS

        filtered_aggr_list = StatsUtils._filter_aggr_list(aggr_list, main_aggregator, allowed_aggr_list)
        return StatsUtils.generate_stats_filtered(
            filtered_aggr_list, country_codes, start_year, end_year, reversed_map, prepared_indicators
        )

    @staticmethod
    def generate_stats_filtered(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
            reversed_map: dict[str, bool],
            prepared_indicators: dict[str, dict[str, Any]]
    ) -> dict[str, Any]:
        """
        Aggregate the result of the target indicators (aggr_list) into a single value.

        Args:
            aggr_list: List of aggregation params
            country_codes: List of analyzed country codes
            start_year: The year from which the analysis begins
            end_year: The year in which the analysis ends
            reversed_map: Map containing specifications about which indicator describes a negative state
            prepared_indicators: Map that contains the prepared indicators specific to the target dimension

        Returns:
            Sorted dictionary with COUNTRY-CODE_YEAR as key
        """
        consolidated_list = OrderedDict()

        for year in range(start_year, end_year + 1):
            for code in country_codes:
                key = generate_key(code, year)
                product = 1.0

                for indicator_name, values in prepared_indicators.items():
                    aggr = next((item for item in aggr_list if item == indicator_name), None)

                    if aggr is not None and aggr == indicator_name:
                        if indicator_name not in reversed_map:
                            print(f"reversed map does not contains {indicator_name}", flush=True)
                            continue

                        is_reversed = reversed_map[indicator_name]
                        product *= (
                            MathUtils.percentage_reverse_ratio(values, key)
                            if is_reversed
                            else values[key]
                        )

                number_value = MathUtils.get_log_value(product)
                consolidated_list[key] = number_value

        return consolidated_list

    @staticmethod
    def filter_stats(
            entries: dict[str, Any],
            entries_time_range: Optional[list[int]],
            members_list: list[str],
            series_type: str,
            start_year: int,
            end_year: int
    ) -> dict[str, dict[int, Any]]:
        """
        Filter the generated stats in a range.

        Args:
            entries: The map containing prepared data for a specific dimension
            entries_time_range: [OPTIONAL] The list of allowed time range
            members_list: The list of countries/regions
            series_type: The type of the aggregation (REGION or COUNTRY)
            start_year: The year the analysis starts
            end_year: The year the analysis ends

        Returns:
            Filtered stats in a specific range
        """
        stats = OrderedDict()
        data = StatsUtils.get_entries(entries, series_type, start_year, end_year)

        for code in members_list:
            item_stats = OrderedDict()

            for year in range(start_year, end_year + 1):
                if entries_time_range is None or year in entries_time_range:
                    key = f"{code}_{year}"
                    value = data.get(key)

                    if value is not None:
                        item_stats[year] = value

            if item_stats:
                stats[code] = item_stats

        return stats

    @staticmethod
    def generate_json_value(entries: dict[str, Any], code: str, year: int) -> Any:
        """
        Aggregate values of the regions of the UK (UKC-L & UKM & UKN) into a single value (UK).
        Used on offences ratio.

        Args:
            entries: The map with target dimension data
            code: The country code for which the aggregation is done
            year: The year for which the aggregation is done

        Returns:
            Prepared value
        """
        key = f"{code}_{year}"
        value = entries.get(key)

        # Handle offences data
        if value is None and code == "UK":
            uk_sum = 0
            uk_sum += entries.get(f"UKC-L_{year}", 0)
            uk_sum += entries.get(f"UKM_{year}", 0)
            uk_sum += entries.get(f"UKN_{year}", 0)
            value = uk_sum

        return value

    @staticmethod
    def get_entries(
            entries: dict[str, Any],
            series_type: str,
            start_year: int,
            end_year: int
    ) -> dict[str, Any]:
        """
        Get countries data or regions data based on "series_type".

        Args:
            entries: The map with target dimension data
            series_type: The type of the aggregation (REGION or COUNTRY)
            start_year: The year the analysis starts
            end_year: The year the analysis ends

        Returns:
            Countries/regions data
        """
        return (
            StatsUtils._aggregate_regions(entries, start_year, end_year)
            if series_type == SERIES_TYPE_REGION
            else entries
        )

    @staticmethod
    def calculate_geometric_mean(
            maps_list: list[dict[str, Any]],
            multiplication_factor: float=1.0
    ) -> dict[str, Any]:
        """
        Consolidate the values using the geometric mean.

        Args:
            maps_list: The list of maps
            multiplication_factor: A value used to adjust the original value

        Returns:
            The consolidated list of maps
        """
        consolidated_list = OrderedDict()

        for year in range(MIN_YEAR, MAX_YEAR + 1):
            for code in EU28_MEMBERS:
                key = generate_key(code, year)
                product = 1.0

                for map_item in maps_list:
                    value = StatsUtils._get_prod_safe_value(map_item, code, year) * multiplication_factor
                    product *= value

                value = math.pow(product, 1.0 / len(maps_list))
                consolidated_list[key] = value

        return consolidated_list

    @staticmethod
    def calculate_sum(
            maps_list: list[dict[str, Any]],
            multiplication_factor: float=1.0
    ) -> dict[str, Any]:
        """
        Consolidate the values by summing them up.

        Args:
            maps_list: The list of maps
            multiplication_factor: A value used to adjust the original value

        Returns:
            The consolidated list of maps
        """
        consolidated_list = OrderedDict()

        for year in range(MIN_YEAR, MAX_YEAR + 1):
            for code in EU28_MEMBERS:
                key = generate_key(code, year)
                total_sum = 0.0

                for map_item in maps_list:
                    value = float(map_item[key])
                    total_sum += value

                total_sum *= multiplication_factor
                consolidated_list[key] = total_sum

        return consolidated_list

    @staticmethod
    def _aggregate_regions(
            entries: dict[str, Any],
            start_year: int,
            end_year: int
    ) -> dict[str, Any]:
        """
        Consolidate calculated values of EU countries into values of EU regions.

        Args:
            entries: The map with target dimension data
            start_year: The year the analysis starts
            end_year: The year the analysis ends

        Returns:
            Regions data prepared to be exported as a JSON
        """
        consolidated_list = OrderedDict()

        for year in range(start_year, end_year + 1):
            eastern_counter = 0
            northern_counter = 0
            southern_counter = 0
            western_counter = 0
            eastern_sum = 0.0
            northern_sum = 0.0
            southern_sum = 0.0
            western_sum = 0.0

            for entry_key, entry_value in entries.items():
                entry_code = get_entry_code(entry_key)
                entry_year = get_entry_year(entry_key)
                entry_num_value = float(entry_value)

                if entry_year is not None and entry_year == year:
                    if entry_code in EU_EASTERN_MEMBERS:
                        eastern_sum += entry_num_value
                        eastern_counter += 1
                    if entry_code in EU_NORTHERN_MEMBERS:
                        northern_sum += entry_num_value
                        northern_counter += 1
                    if entry_code in EU_SOUTHERN_MEMBERS:
                        southern_sum += entry_num_value
                        southern_counter += 1
                    if entry_code in EU_WESTERN_MEMBERS:
                        western_sum += entry_num_value
                        western_counter += 1

            consolidated_list[generate_key("EU_EASTERN", year)] = (
                eastern_sum / eastern_counter if eastern_counter > 0 else 0
            )
            consolidated_list[generate_key("EU_NORTHERN", year)] = (
                northern_sum / northern_counter if northern_counter > 0 else 0
            )
            consolidated_list[generate_key("EU_SOUTHERN", year)] = (
                southern_sum / southern_counter if southern_counter > 0 else 0
            )
            consolidated_list[generate_key("EU_WESTERN", year)] = (
                western_sum / western_counter if western_counter > 0 else 0
            )

        return consolidated_list

    @staticmethod
    def _get_prod_safe_value(entries: dict[str, Any], code: str, year: int) -> float:
        """
        Get the safe value. A safe value is considered to be either the neutral value (1)
        or a value greater than the neutral value.

        Args:
            entries: The map containing prepared data for a specific indicator
            code: The country code for which the calculation is done
            year: The year for which the calculation is done

        Returns:
            Safe value
        """
        input_key = generate_key(code, year)
        value = float(entries[input_key])

        if value != 0:
            return value

        # Get the value recorded to the nearest year which is greater than 0.0 for cases where
        # the datasets contain the value "0.0" instead of "null".
        # This preparation prevents corruption of the result of the product of values.
        for crr_year in range(year, MIN_YEAR - 1, -1):
            key = generate_key(code, crr_year)
            value = float(entries[key])

            if value != 0:
                return value

        return 1.0

    @staticmethod
    def _filter_aggr_list(
            aggr_list: Optional[list[str]],
            main_aggregator: str,
            allowed_aggr_list: list[str]
    ) -> list[str]:
        """
        Filter the target indicators (aggr_list) based on a specific series of indicators
        which describe a dimension (allowed_aggr_list).

        Args:
            aggr_list: List of aggregation params
            main_aggregator: The aggregator describing the target dimension
            allowed_aggr_list: List of aggregation params specific to the target dimension

        Returns:
            Filtered aggregation list
        """
        if not aggr_list:
            return allowed_aggr_list

        if main_aggregator in aggr_list:
            return allowed_aggr_list

        return [item for item in aggr_list if item in allowed_aggr_list]
