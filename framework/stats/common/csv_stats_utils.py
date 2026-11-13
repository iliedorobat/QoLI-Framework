import os

from framework.common.constants import EU28_MEMBERS, DIRECTION_ROW, CSV_SEPARATOR, PREPARED_DATASET_PATH, CSV_EXTENSION
from framework.fetching.file_utils import write_to_file
from framework.processing.map_utils import get_entry_code
from framework.processing.math_utils import MathUtils
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.qoli_paths import get_series_directory

_ROUNDING_PLACES = 2


def generate_variation(
        main_map: dict[str, float],
        is_relative: bool,
) -> dict[str, list[float | None]]:
    """
    Generate a list with the relative or the absolute individual variations
    calculated between two adjacent terms for the same country code:
        * (entry[i] / entry[i-1] * 100) - 100;
        * entry[i] - entry[i-1].

    Args:
        main_map: The initialized map (see Initializer.init_map).
        is_relative: Specify if the calculated variation is relative or absolute.

    Returns:
        The list of the individual variations.
    """
    deviation_list = {}
    entries = list(main_map.items())

    for code in EU28_MEMBERS:
        country_deviation_list = []

        # Start from second record
        for j in range(1, len(entries)):
            entry = entries[j]
            entry_code = get_entry_code(entry)
            entry_value = entry[1]

            prev_entry = entries[j - 1]
            prev_entry_code = get_entry_code(prev_entry)
            prev_entry_value = prev_entry[1]

            if entry_code == code and prev_entry_code == code:
                deviation = _calculate_variation(entry_value, prev_entry_value, is_relative)
                country_deviation_list.append(deviation)

        deviation_list[code] = country_deviation_list

    return deviation_list


def generate_chart_data(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        direction: str,
        start_year: int,
        end_year: int,
) -> str:
    """
    Generate CSV data that can be imported into Word charts.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        directory_name: Directory name of the target dimension/indicator.
        direction: Display years on rows or columns (ROW or COLUMN).
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.

    Returns:
        Data prepared for use in Word charts.
    """
    data = StatsUtils.get_entries(entries, series_type, start_year, end_year)

    if direction == DIRECTION_ROW:
        return _generate_chart_rows(data, members_list, series_type, directory_name, start_year, end_year)

    return _generate_chart_columns(data, members_list, series_type, directory_name, start_year, end_year)


def _generate_chart_columns(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        start_year: int,
        end_year: int,
) -> str:
    """
    Generate CSV data with countries/regions on rows and years on columns.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        directory_name: Directory name of the target dimension/indicator.
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.

    Returns:
        Data prepared for use in Word charts.
    """
    header = (
        f"--- {series_type} --- {directory_name} ---"
        f"\nCountries{CSV_SEPARATOR}"
    )
    output = [
        header,
        CSV_SEPARATOR.join(str(year) for year in range(start_year, end_year + 1)),
        "\n"
    ]

    for code in members_list:
        line = [f"{code}{CSV_SEPARATOR}"]

        for year in range(start_year, end_year + 1):
            value = StatsUtils.generate_json_value(entries, code, year)
            line.append(_format_number(value))

            if year < end_year:
                line.append(CSV_SEPARATOR)

        output.append("".join(line))
        output.append("\n")

    return "".join(output)


def _generate_chart_rows(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        start_year: int,
        end_year: int,
) -> str:
    """
    Generate CSV data with years on rows and countries/regions on columns.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        directory_name: Directory name of the target dimension/indicator.
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.

    Returns:
        Data prepared for use in Word charts.
    """
    header = (
        f"--- {series_type} --- {directory_name} ---"
        f"\nYears{CSV_SEPARATOR}"
    )
    output = [
        header,
        CSV_SEPARATOR.join(members_list),
        "\n"
    ]
    length = len(members_list)

    for year in range(start_year, end_year + 1):
        line = [f"{year}{CSV_SEPARATOR}"]

        for i, code in enumerate(members_list):
            key = f"{code}_{year}"
            value = entries.get(key)

            line.append(str(value) if value is not None else "null")

            if i < length - 1:
                line.append(CSV_SEPARATOR)

        output.append("".join(line))
        output.append("\n")

    return "".join(output)


def write_chart_data(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        direction: str,
        prepared_indicators: dict[str, dict[str, float]] | None,
        calculate_indicators: bool,
        start_year: int,
        end_year: int,
) -> None:
    """
    Export the prepared chart data to a CSV file.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        directory_name: Directory name of the target dimension/indicator.
        direction: Display years on rows or columns (ROW or COLUMN).
        prepared_indicators: A map containing indicators which make up the target dimension.
        calculate_indicators: Calculate or not the indicators that make up the QoLI dimensions.
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.
    """
    output = generate_chart_data(entries, members_list, series_type, directory_name, direction, start_year, end_year)
    series_directory = get_series_directory(series_type)
    full_path = os.path.join(PREPARED_DATASET_PATH, "csv", series_directory)

    write_to_file(output, full_path, directory_name, CSV_EXTENSION)

    if calculate_indicators and prepared_indicators is not None:
        for indicator_name, value in prepared_indicators.items():
            indicator_output = generate_chart_data(value, members_list, series_type, directory_name, direction, start_year, end_year)
            indicator_full_path = os.path.join(full_path, directory_name)
            write_to_file(indicator_output, indicator_full_path, indicator_name, CSV_EXTENSION)


def _calculate_variation(
        entry_value: float | None,
        prev_entry_value: float | None,
        is_relative: bool,
) -> float | None:
    """
    Calculate the variation between two numbers.

    Args:
        entry_value: The current value.
        prev_entry_value: The previous value.
        is_relative: Specify if the calculated variation is relative or absolute.

    Returns:
        The variation between current value and the previous one:
            * a positive value means that the current values increased compared to the previous value;
            * a negative value means that the current values decreased compared to the previous value.
    """
    deviation = None

    if entry_value is not None and prev_entry_value is not None:
        if is_relative:
            ratio = entry_value / prev_entry_value * 100
            diff = ratio - 100
            deviation = MathUtils.round(diff, _ROUNDING_PLACES)
        else:
            diff = entry_value - prev_entry_value
            deviation = MathUtils.round(diff, _ROUNDING_PLACES)

    return deviation


def _format_number(value: float | None) -> str:
    if value is None:
        return "null"

    return f"{value:,.4f}".rstrip("0").rstrip(".")


__all__ = [
    "generate_variation",
    "generate_chart_data",
    "write_chart_data",
]
