import json
import os

from framework.common.constants import PREPARED_DATASET_PATH, JSON_EXTENSION
from framework.fetching.file_utils import write_to_file
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.qoli_paths import get_series_directory


def write_json_data(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        prepared_indicators: dict[str, dict[str, float]] | None,
        calculate_indicators: bool,
        start_year: int,
        end_year: int,
) -> None:
    """
    Export the prepared chart data to a JSON file.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        directory_name: Directory name of the target dimension/indicator.
        prepared_indicators: A map containing indicators which make up the target dimension.
        calculate_indicators: Calculate or not the indicators that make up the QoLI dimensions.
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.
    """
    stats = _prepare_json_data(entries, members_list, series_type, start_year, end_year)

    data = json.dumps(stats)
    series_directory = get_series_directory(series_type)
    full_path = os.path.join(PREPARED_DATASET_PATH, "json", series_directory)

    write_to_file(data, full_path, directory_name, JSON_EXTENSION)

    if calculate_indicators and prepared_indicators is not None:
        for indicator_name, value in prepared_indicators.items():
            indicator_stats = _prepare_json_data(value, members_list, series_type, start_year, end_year)
            indicator_data = json.dumps(indicator_stats)
            indicator_full_path = os.path.join(full_path, directory_name)

            write_to_file(indicator_data, indicator_full_path, indicator_name, JSON_EXTENSION)


def _prepare_json_data(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        start_year: int,
        end_year: int,
) -> dict[str, dict[int, float | None]]:
    """
    Generate sorted data that can be exported as JSON.

    Args:
        entries: The map with target dimension data.
        members_list: The list of countries/regions.
        series_type: The type of the aggregation (REGION or COUNTRY).
        start_year: The year the analysis starts.
        end_year: The year the analysis ends.

    Returns:
        Data prepared to be exported as JSON.
    """
    data = StatsUtils.get_entries(entries, series_type, start_year, end_year)
    stats = {}

    for code in sorted(members_list):
        item_stats = {}

        for year in range(start_year, end_year + 1):
            item_stats[year] = StatsUtils.generate_json_value(data, code, year)

        stats[code] = item_stats

    return stats


__all__ = [
    "write_json_data",
]
