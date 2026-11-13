"""
Collector for environment-related statistical data from Eurostat.
"""

from time import sleep

from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file


def fetch_environment_data() -> None:
    """
    Fetch all environment indicators and write them to JSON files.
    """
    sleep(0.1)  # Sleep 100 milliseconds
    write_to_json_file(_get_air_pollution_ratio(), ENVIRONMENT_RAW_PATH, AIR_POLLUTION_RATIO_FILE_NAME)
    write_to_json_file(_get_noise_pollution_ratio(), ENVIRONMENT_RAW_PATH, NOISE_POLLUTION_RATIO_FILE_NAME)
    write_to_json_file(_get_pollution_ratio(), ENVIRONMENT_RAW_PATH, POLLUTION_RATIO_FILE_NAME)
    write_to_json_file(_get_water_supply_ratio(), ENVIRONMENT_RAW_PATH, WATER_SUPPLY_RATIO_FILE_NAME)


def _get_air_pollution_ratio() -> str:
    """
    Exposure to air pollution:
    - Particulates < 2.5 µm
    - Particulates < 10 µm
    - Acidifying gas emissions (NH3, NOX)
    - Ozone precursors (CH4, CO, NMVOC, NOX)

    https://ec.europa.eu/eurostat/statistics-explained/index.php?title=Air_pollution_statistics_-_air_emissions_accounts

    Aggregation: country
    Data type: Kilograms per capita
    Dataset: env_ac_ainah_r2
    Years: 1995-2024

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("env_ac_ainah_r2", AIR_POLLUTION_RATIO_PARAMS)


def _get_noise_pollution_ratio() -> str:
    """
    Noise from neighbours or from the street

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mddw01
    Note: EU-SILC survey
    Years: 2003-2020; 2023

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("ilc_mddw01", NOISE_POLLUTION_RATIO_PARAMS)


def _get_pollution_ratio() -> str:
    """
    Pollution, grime or other environmental problems (e.g.: smoke, dust,
    unpleasant smells or polluted water)

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mddw02
    Note: EU-SILC survey
    Years: 2003-2020; 2023

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("ilc_mddw02", POLLUTION_RATIO_PARAMS)


def _get_water_supply_ratio() -> str:
    """
    Population connected to public water supply

    Aggregation: country
    Data type: percentage (%)
    Dataset: env_wat_pop
    Years: 1990; 1995; 2000-2024

    Comment: NUTS 2 regions => env_watpop_r2
    Lack of data: IT; LV; SI; UK

    Returns:
        HTTP Response as a string
    """
    return fetch_data("env_wat_pop", WATER_SUPPLY_RATIO_PARAMS)


__all__ = [
    'fetch_environment_data',
]
