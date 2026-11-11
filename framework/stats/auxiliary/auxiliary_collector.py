"""
Fetch population data and write to JSON file
"""

from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file
from framework.stats.auxiliary.auxiliary_params import POPULATION_PARAMS
from framework.stats.auxiliary.auxiliary_paths import AUXILIARY_RAW_PATH, POPULATION_FILE_NAME


def fetch_auxiliary_data():
    population = _get_population()
    write_to_json_file(population, AUXILIARY_RAW_PATH, POPULATION_FILE_NAME)

def _get_population():
    """
    Population on 1 January

    Aggregation: country
    Data type: count (number)
    Dataset: demo_pjan
    Years: 1960-2025

    Returns:
        str: Population data
    """
    return fetch_data("demo_pjan", POPULATION_PARAMS)


__all__ = [
    'fetch_auxiliary_data',
]
