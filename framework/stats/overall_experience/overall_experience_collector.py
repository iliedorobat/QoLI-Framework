import time

from framework.fetching.fetch_data import fetch_data, fetch_satisfaction_ratio
from framework.fetching.file_utils import write_to_json_file
from framework.stats.overall_experience.overall_experience_params import *
from framework.stats.overall_experience.overall_experience_paths import *


def fetch_overall_experience_data() -> None:
    time.sleep(0.1)
    write_to_json_file(_get_happiness_ratio(), OVERALL_EXPERIENCE_RAW_PATH, HAPPINESS_RATIO_FILE_NAME)
    write_to_json_file(_get_high_satisfaction_ratio(), OVERALL_EXPERIENCE_RAW_PATH, HIGH_SATISFACTION_RATIO_FILE_NAME)


def _get_happiness_ratio() -> str:
    """
    Percentage of the population being happy always or most of the time in the last 4 weeks.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw08
    Years: 2013; 2018; 2022
    """
    return fetch_data("ilc_pw08", HAPPINESS_RATIO_PARAMS)


def _get_high_satisfaction_ratio() -> str:
    """
    Percentage of the population rating their overall life satisfaction as high.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(HIGH_SATISFACTION_RATIO_PARAMS)


__all__ = ["fetch_overall_experience_data"]
