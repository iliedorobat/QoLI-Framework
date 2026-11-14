"""
Utility methods for map operations.
"""

from framework.common.constants import KEY_SEPARATOR


def generate_key(code: str, year: int | float) -> str:
    """
    Generate a composite key from code and year.

    Args:
        code: The code string
        year: The year (int or float)

    Returns:
        A composite key combining code and year
    """
    return f"{code}{KEY_SEPARATOR}{year}"

def get_entry_code(key: str) -> str | None:
    """
    Extract the code part from a map entry key.

    Args:
        key: The key to look up

    Returns:
        The code part or None if key format is invalid
    """
    key_list = key.split(KEY_SEPARATOR)

    if len(key_list) == 2:
        return key_list[0]

    return None

def get_entry_year(entry: str) -> int | None:
    """
    Extract the year part from a map entry key.

    Args:
        entry: A tuple of (key, value)

    Returns:
        The year as an integer or None if key format is invalid
    """
    key_list = entry.split(KEY_SEPARATOR)

    if len(key_list) == 2:
        return int(key_list[1])

    return None

def sort_key(entry: tuple[str, float | None]) -> tuple[str, int]:
    """
    Extract sortable key from a (key, value) tuple.
    Sorts by country code, then by year.

    Args:
        entry: A tuple of (key, value) where key is like "AT_2015"

    Returns:
        A tuple (country_code, year) for proper sorting
    """
    key = entry[0]  # e.g., "AT_2015"
    code, year = key.split(KEY_SEPARATOR)  # Split on "_"

    return code, int(year)


__all__ = [
    "generate_key",
    "get_entry_code",
    "get_entry_year",
    "sort_key",
]
