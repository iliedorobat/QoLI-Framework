"""
Collector for factors which influence the life of the population from
the perspective of governing, law making and equality of opportunity.
"""

import os
import time

from framework.common.constants import XLS_EXTENSION
from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file, download_excel_file, convert_xls_to_csv
from framework.stats.gov_rights.gov_rights_params import *
from framework.stats.gov_rights.gov_rights_paths import *


def fetch_gov_rights_data() -> None:
    time.sleep(0.1)

    write_to_json_file(_get_citizenship_ratio(), GOV_RIGHTS_RAW_PATH, CITIZENSHIP_RATIO_FILE_NAME)
    write_to_json_file(_get_gender_emp_gap(), GOV_RIGHTS_RAW_PATH, GENDER_EMP_GAP_FILE_NAME)
    write_to_json_file(_get_gender_pay_gap(), GOV_RIGHTS_RAW_PATH, GENDER_PAY_GAP_FILE_NAME)
    write_to_json_file(_get_population_trust(), GOV_RIGHTS_RAW_PATH, POPULATION_TRUST_FILE_NAME)
    write_to_json_file(_get_population_trust_other(), GOV_RIGHTS_RAW_PATH, POPULATION_TRUST_OTHER_FILE_NAME)

    _write_voter_turnout()


def _write_voter_turnout() -> None:
    """
    Voter turnout (the parliamentary elections).

    Aggregation: country
    Data type: percentage (%)
    Dataset: https://www.idea.int/data-tools/data/voter-turnout
    Note: International IDEA
    """
    file_url = (
        "https://www.idea.int/data-tools/export?"
        "type=region_only&themeId=293&world=all&loc=home"
    )

    file_path = GOV_RIGHTS_RAW_PATH
    file_name = VOTER_TURNOUT_FILE_NAME

    download_excel_file(
        file_url,
        file_path,
        file_name,
        XLS_EXTENSION,
    )

    convert_xls_to_csv(
        file_path,
        file_name,
        XLS_EXTENSION,
    )

    # Remove the XLS file after it has been converted to CSV.
    xls_file_path = os.path.join(
        file_path,
        f"{file_name}{XLS_EXTENSION}",
    )

    os.remove(xls_file_path)


def _get_citizenship_ratio() -> str:
    """
    Active citizenship participation (people aged 16 years or over).

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp19
    Years: 2015; 2022
    """
    return fetch_data("ilc_scp19", CITIZENSHIP_RATIO_PARAMS)


def _get_gender_emp_gap() -> str:
    """
    The difference between the employment rates of working-age men and
    women (defined here as those aged 20-64).

    Aggregation: country
    Data type: percentage (%)
    Dataset: tesem060
    Years: 2005-2025

    GREATER IS WORSE!
    """
    return fetch_data("tesem060", GENDER_EMP_GAP_PARAMS)


def _get_gender_pay_gap() -> str:
    """
    Gender pay gap in unadjusted form by NACE Rev. 2 activity - structure
    of earnings survey methodology.

    Industry, construction and services (except public administration,
    defense, compulsory social security).

    Aggregation: country
    Data type: percentage (%)
    Dataset: earn_gr_gpgr2
    Years: 2007-2024

    GREATER IS WORSE!
    """
    return fetch_data("earn_gr_gpgr2", GENDER_PAY_GAP_PARAMS)


def _get_population_trust() -> str:
    """
    Average rating of population trust (16 years or over) in police,
    legal system and political system.

    Aggregation: country
    Data type: rating (number between 0-10)
    Dataset: ilc_pw03b
    Years: 2013
    """
    return fetch_data("ilc_pw03b", POPULATION_TRUST_PARAMS)


def _get_population_trust_other() -> str:
    """
    Average rating of population trust (16 years or over) in other systems.

    Aggregation: country
    Data type: rating (number between 0-10)
    Dataset: ilc_pw03
    Years: 2013; 2018; 2021-2025
    """
    return fetch_data("ilc_pw03", POPULATION_TRUST_OTHER_PARAMS)


__all__ = [
    "fetch_gov_rights_data",
]
