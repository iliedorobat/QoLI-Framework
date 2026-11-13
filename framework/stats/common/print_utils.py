from framework.common.constants import KEY_SEPARATOR
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.processing.map_utils import get_entry_code, get_entry_year
from framework.stats.common.csv_stats_utils import generate_chart_data


def print_chart_data(
        args: list[str],
        indicators: dict[str, dict[str, float]],
        dimension: str,
        members_list: list[str],
        series_type: str,
        direction: str,
) -> None:
    if f"--dimension={dimension}" in args:
        for indicator_name, indicator_map in indicators.items():
            if f"--indicator={indicator_name}" in args:
                print_chart_data_for_entries(
                    indicator_map,
                    members_list,
                    series_type,
                    indicator_name,
                    direction,
                )


def print_chart_data_for_entries(
        entries: dict[str, float],
        members_list: list[str],
        series_type: str,
        directory_name: str,
        direction: str,
) -> None:
    output = generate_chart_data(
        entries,
        members_list,
        series_type,
        directory_name,
        direction,
        MIN_YEAR,
        MAX_YEAR,
    )
    print(output)


def print_entries(entries: dict[str, float], print_null: bool) -> None:
    prev_code = None

    for key, value in entries.items():
        entry = (key, value)

        # Print an empty line if the country code is different than the previous one.
        code = get_entry_code(entry)

        if prev_code is not None and prev_code != code:
            print()

        prev_code = code
        print_entry(entry, print_null)


def print_entry(entry: tuple[str, float | None], print_null: bool) -> None:
    key, value = entry

    if print_null:
        print(f"{key}={value}")
    elif value is not None:
        print(f"{key}={value}")


def print_entries_for_code(
        entries: dict[str, float],
        print_null: bool,
        code: str,
) -> None:
    for key, value in entries.items():
        if f"{code}{KEY_SEPARATOR}" in key:
            print_entry((key, value), print_null)


def print_variation(entries: dict[str, list[float | None]]) -> None:
    for code, values in entries.items():
        print(f"{code}={values}")


def print_variation_for_code(
        entries: dict[str, list[float | None]],
        code: str,
) -> None:
    for entry_code, values in entries.items():
        if f"{code}{KEY_SEPARATOR}" in entry_code:
            print(f"{entry_code}={values}")


def _filter_map(
        prepared_map: dict[str, float],
        target_year: int,
) -> dict[str, float]:
    filtered_map = {}

    for key, value in sorted(prepared_map.items()):
        year = get_entry_year(key)

        if year == target_year:
            filtered_map[key] = value

    return filtered_map


def print_data_availability(
        data: dict[str, dict[str, float | None]],
        dimension_name: str,
        target_year: int,
        print_ind_status: bool,
) -> None:
    available = 0
    expected = 0

    for indicator_name, indicator_map in data.items():
        values = indicator_map

        if target_year > -1:
            values = _filter_map(values, target_year)

        indicator_available = sum(
            1 for value in values.values() if value is not None
        )
        indicator_expected = len(values)

        available += indicator_available
        expected += indicator_expected

        if print_ind_status:
            print(
                f"\t{indicator_name}:"
                f"\n\t\tAvailable: {indicator_available}"
                f"\n\t\tMissing: {indicator_expected - indicator_available}"
            )

    print(
        f"{dimension_name}:"
        f"\n\tAvailable: {available}"
        f"\n\tMissing: {expected - available}"
        f"\n------------------------"
    )


def format_number(number: float) -> str:
    return f"{number:,.4f}"


__all__ = [
    "print_chart_data",
    "print_chart_data_for_entries",
    "print_entries",
    "print_entry",
    "print_entries_for_code",
    "print_variation",
    "print_variation_for_code",
    "print_data_availability",
    "format_number",
]
