from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS, KEY_SEPARATOR
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR, INIT_MAP_MIN_YEAR, INIT_MAP_MAX_YEAR
from framework.processing.map_utils import generate_key, get_entry_year
from framework.processing.math_utils import MathUtils


class Preparation:
    """Data preparation utilities for handling null values and transformations."""

    @staticmethod
    def prepare_data(
            main_map: dict[str, int | float],
            country_codes: list[str] | None = None
    ) -> dict[str, int | float]:
        """
        Add values for the years that have null values.

        Example:
            Input:  {EU28_2011: None, EU28_2012: None, EU28_2013: 93.3, ...}
            Output: {EU28_2011: None, EU28_2012: None, EU28_2013: 93.3, EU28_2014: 93.3, ...}

        Args:
            main_map: The initialized map
            country_codes: The list of country codes (defaults to EU28_MEMBERS)

        Returns:
            Prepared map without null values
        """
        if country_codes is None:
            country_codes = EU28_MEMBERS

        prepared_map = OrderedDict(main_map)

        for code in country_codes:
            Preparation._fill_right_null_values(prepared_map, code)
            Preparation._fill_left_null_values(prepared_map, code)
            Preparation._fill_null_values(main_map, prepared_map, code)

        return Preparation.filter_map(prepared_map)

    @staticmethod
    def prepare_per_thousand_inhabitant(
            population_map: dict[str, int | float],
            init_map: dict[str, int | float]
    ) -> dict[str, int | float]:
        """
        Transform all values into values per thousand inhabitants.

        Args:
            population_map: Total population map
            init_map: The initialized map

        Returns:
            An ordered map with aggregated data
        """
        generated_map = OrderedDict()
        prepared_map = Preparation.prepare_data(init_map)

        for year in range(MIN_YEAR, MAX_YEAR + 1):
            for code in EU28_MEMBERS:
                key = generate_key(code, year)
                value = prepared_map.get(key)
                if value is not None:
                    transformed_value = MathUtils.generate_per_thousand_inhabitants(population_map, key, value)
                    generated_map[key] = transformed_value

        return generated_map

    @staticmethod
    def prepare_per_ten_thousand_inhabitants(
            population_map: dict[str, int | float],
            init_map: dict[str, int | float]
    ) -> dict[str, int | float]:
        """
        Transform all values into values per ten thousand inhabitants.

        Args:
            population_map: Total population map
            init_map: The initialized map

        Returns:
            An ordered map with aggregated data
        """
        generated_map = OrderedDict()
        prepared_map = Preparation.prepare_data(init_map)

        for year in range(MIN_YEAR, MAX_YEAR + 1):
            for code in EU28_MEMBERS:
                key = generate_key(code, year)
                value = prepared_map.get(key)
                if value is not None:
                    transformed_value = MathUtils.generate_per_ten_thousand_inhabitants(population_map, key, value)
                    generated_map[key] = transformed_value

        return generated_map

    @staticmethod
    def prepare_pps_ratio(
            entries: dict[str, int | float]
    ) -> dict[str, int | float]:
        """
        Calculate the share of PPS compared to EU average.

        Args:
            entries: The map containing prepared data for a specific dimension

        Returns:
            Map containing the PPS Ratio
        """
        prepared_map = OrderedDict()

        for year in range(MIN_YEAR, MAX_YEAR + 1):
            for code in EU28_MEMBERS:
                key = generate_key(code, year)
                value = Preparation._calculate_pps_ratio(entries, code, year)
                prepared_map[key] = value

        return prepared_map

    @staticmethod
    def calculate_eu_average(
            main_map: dict[str, int | float], year: int
    ) -> float | None:
        """
        Calculate the average of the EU for a specific year.

        Args:
            main_map: The initialized map
            year: The year for which the average is calculated

        Returns:
            Average of the EU, or None if no data available
        """
        total = 0.0
        count = 0

        for key, value in main_map.items():
            if value is not None and key is not None:
                try:
                    entry_year = int(key.split(KEY_SEPARATOR)[1])
                    if entry_year == year:
                        total += float(value)
                        count += 1
                except (IndexError, ValueError):
                    continue

        return total / count if count > 0 else None

    @staticmethod
    def filter_map(
            prepared_map: dict[str, int | float]
    ) -> dict[str, int | float]:
        """
        Fill the output map only with data for the analyzed period.

        Args:
            prepared_map: The prepared map containing all entries

        Returns:
            Filtered map by the analyzed period
        """
        filtered_map = OrderedDict()

        for key, value in prepared_map.items():
            year = get_entry_year(key)
            if MIN_YEAR <= year <= MAX_YEAR:
                filtered_map[key] = value

        return filtered_map

    @staticmethod
    def _fill_null_values(
            main_map: dict[str, int | float],
            prepared_map: dict[str, int | float],
            code: str
    ) -> None:
        """
        Add values for the years that have null values for a specified country code.

        Args:
            main_map: The initialized map
            prepared_map: The prepared map containing all entries
            code: The country code for which the ratio is calculated
        """
        for year in range(INIT_MAP_MIN_YEAR, INIT_MAP_MAX_YEAR + 1):
            key = generate_key(code, year)
            value = prepared_map.get(key)
            eu_average = Preparation.calculate_eu_average(main_map, year)
            Preparation._add_key_value(prepared_map, key, value, eu_average)

        Preparation._fill_right_null_values(prepared_map, code)
        Preparation._fill_left_null_values(prepared_map, code)

    @staticmethod
    def _fill_right_null_values(
            prepared_map: dict[str, int | float], code: str
    ) -> None:
        """
        Add values for next years that have null values for a specified country code.
        Fills forward with the last known value.

        Args:
            prepared_map: The prepared map containing all entries
            code: The country code for which the processing is being done
        """
        prev_value = None

        for year in range(INIT_MAP_MIN_YEAR, INIT_MAP_MAX_YEAR + 1):
            key = generate_key(code, year)
            value = prepared_map.get(key)

            Preparation._add_key_value(prepared_map, key, value, prev_value)

            if value is not None:
                prev_value = value

    @staticmethod
    def _fill_left_null_values(
            prepared_map: dict[str, int | float], code: str
    ) -> None:
        """
        Add values for previous years that have null values for a specified country code.
        Fills backward with the next known value.

        Args:
            prepared_map: The prepared map containing all entries
            code: The country code for which the processing is being done
        """
        next_value = None

        for year in range(INIT_MAP_MAX_YEAR, INIT_MAP_MIN_YEAR - 1, -1):
            key = generate_key(code, year)
            value = prepared_map.get(key)

            Preparation._add_key_value(prepared_map, key, value, next_value)

            if value is not None:
                next_value = value

    @staticmethod
    def _add_key_value(
            map_dict: dict[str, int | float],
            key: str,
            value: int | float | None = None,
            saved_value: int | float | None = None
    ) -> None:
        """
        Add an entry to the map if it's missing (None).

        Args:
            map_dict: The map/dictionary
            key: The key
            value: The value
            saved_value: A buffer value (previous/next value)
        """
        if map_dict.get(key) is None:
            if value is not None:
                map_dict[key] = value
            elif saved_value is not None:
                map_dict[key] = saved_value

    @staticmethod
    def _calculate_pps_ratio(
            entries: dict[str, int | float], code: str, year: int
    ) -> float | None:
        """
        Calculate the share of PPS compared to EU average.

        Args:
            entries: The map containing prepared data for a specific dimension
            code: The country code for which the ratio is calculated
            year: The year for which the ratio is calculated

        Returns:
            PPS Ratio, or None if EU average is unavailable
        """
        eu_average = Preparation.calculate_eu_average(entries, year)
        key = generate_key(code, year)
        value = entries.get(key)

        if eu_average is None:
            raise ValueError("The 'eu_average' variable is None.")

        return (value / eu_average) * 100 if value is not None else None
