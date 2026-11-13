from framework.common.constants import SERIES_TYPE_REGION

QOLI_FILE_NAME = "qoli"


def get_series_directory(series_type: str) -> str:
    """
    Get the directory name of the input series type.

    Args:
        series_type: The type of the aggregation (REGION or COUNTRY).

    Returns:
        The directory name of the input series type.
    """
    if series_type == SERIES_TYPE_REGION:
        return "regions"
    return "countries"


__all__ = [
    "QOLI_FILE_NAME",
    "get_series_directory",
]
