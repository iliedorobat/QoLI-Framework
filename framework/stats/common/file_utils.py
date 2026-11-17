import os
from pathlib import Path

from framework.common.constants import SERIES_TYPE_REGION
from framework.config.env_state import EnvState

# File extensions
JSON_EXTENSION = ".json"
CSV_EXTENSION = ".csv"
CSV_SEPARATOR = ";"
KEY_SEPARATOR = "_"
XLS_EXTENSION = ".xls"

def _get_base_path():
    """Determine the base path based on environment configuration."""
    if EnvState.IS_PRODUCTION:
        # TODO: check
        return os.path.join(os.sep, "var", "www", "life-index.eu", "server", "resources", "main")
    else:
        return os.path.join(os.sep, Path.cwd(), "datasets")

_BASE_PATH = _get_base_path()
PREPARED_DATASET_PATH = os.path.join(_BASE_PATH, "static", "prepared")
# Get the absolute path to the project root by traversing up from this file
# This file is at: QoLI-Framework/framework/collector/constants/constants.py

# Build the raw dataset path using absolute paths
RAW_DATASET_PATH = os.path.join(os.sep, _BASE_PATH, "raw", "json", "countries")


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
    "CSV_EXTENSION",
    "CSV_SEPARATOR",
    "JSON_EXTENSION",
    "KEY_SEPARATOR",
    "PREPARED_DATASET_PATH",
    "RAW_DATASET_PATH",
    "XLS_EXTENSION",
    "get_series_directory",
]
