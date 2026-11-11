"""
Utility methods for map operations.
"""

from typing import List, Dict

from framework.constants.constants import KEY_SEPARATOR


def get_unique_keys(params: Dict[str, List[str]]) -> List[str]:
    """
    Get the unique keys of a multi-dimensional value map.

    Args:
        params: The multi-dimensional value map

    Returns:
        The list of unique keys
    """
    unique_keys = set(params.keys())
    return list(unique_keys)

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

def get_entry_code(entry: tuple[str, int | float]) -> str | None:
    """
    Extract the code part from a map entry key.

    Args:
        entry: A tuple of (key, value)

    Returns:
        The code part or None if key format is invalid
    """
    key, _ = entry
    key_list = key.split(KEY_SEPARATOR)

    if len(key_list) == 2:
        return key_list[0]

    return None

def get_entry_year(entry: tuple[str, int | float]) -> int | None:
    """
    Extract the year part from a map entry key.

    Args:
        entry: A tuple of (key, value)

    Returns:
        The year as an integer or None if key format is invalid
    """
    key, _ = entry
    key_list = key.split(KEY_SEPARATOR)

    if len(key_list) == 2:
        return int(key_list[1])

    return None

def sort_key(item):
    """
    Extract sortable key from a (key, value) tuple.
    Sorts by country code, then by year.

    Args:
        item: A tuple of (key, value) where key is like "AT_2015"

    Returns:
        A tuple (country_code, year) for proper sorting
    """
    key = item[0]  # e.g., "AT_2015"
    parts = key.split(KEY_SEPARATOR)  # Split on "_"
    country = parts[0]
    year = int(parts[1])
    return country, year
