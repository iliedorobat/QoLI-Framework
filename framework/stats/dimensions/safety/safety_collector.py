import time

from framework.common.constants import EU28_MEMBERS_EXTENDED
from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file
from framework.stats.dimensions.safety.safety_params import *
from framework.stats.dimensions.safety.safety_paths import *


def fetch_safety_data() -> None:
    time.sleep(0.1)
    write_to_json_file(_get_crime_ratio(), SAFETY_RAW_PATH, CRIME_RATIO_FILE_NAME)
    write_to_json_file(_get_offences(), SAFETY_RAW_PATH, OFFENCES_FILE_NAME)
    write_to_json_file(_get_pension_pps(), SAFETY_RAW_PATH, PENSION_PPS_FILE_NAME)
    write_to_json_file(_get_social_protection_pps(), SAFETY_RAW_PATH, SOCIAL_PROTECTION_RATIO_FILE_NAME)
    write_to_json_file(_get_unexpected_ratio(), SAFETY_RAW_PATH, UNEXPECTED_RATIO_FILE_NAME)
    write_to_json_file(_get_non_payment_ratio(), SAFETY_RAW_PATH, NON_PAYMENT_RATIO_FILE_NAME)


def _get_crime_ratio() -> str:
    """
    Share of the population who perceived there was crime, violence or vandalism in the area where they live.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mddw03
    Note: EU-SILC survey
    Years: 2003-2020; 2023

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mddw03", CRIME_RATIO_PARAMS)


def _get_non_payment_ratio() -> str:
    """
    Share of the population in arrears on mortgage or rent, utility bills or hire purchase.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mdes05
    Note: EU-SILC survey
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mdes05", NON_PAYMENT_RATIO_PARAMS)


def _get_offences() -> str:
    """
    Recorded offences by offence category (Assault, Robbery, Burglary, Theft).

    Aggregation: country
    Data type: count (number)
    Dataset: crim_off_cat
    Years: 2008-2024

    GREATER IS WORSE!
    """
    return fetch_data("crim_off_cat", OFFENCES_PARAMS, EU28_MEMBERS_EXTENDED)


def _get_pension_pps() -> str:
    """
    Pensions expenditure level.

    Aggregation: country
    Data type: PPS per inhabitant (number)
    Dataset: spr_exp_pens
    Years: 1990-2024
    """
    return fetch_data("spr_exp_pens", PENSION_PPS_PARAMS)


def _get_social_protection_pps() -> str:
    """
    Social protection expenditure level.

    Aggregation: country
    Data type: PPS per inhabitant (number)
    Dataset: spr_exp_type
    Years: 1990-2024
    """
    return fetch_data("spr_exp_type", SOCIAL_PROTECTION_PPS_PARAMS)


def _get_unexpected_ratio() -> str:
    """
    Share of the population unable to face unexpected financial expenses.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mdes04
    Note: EU-SILC survey
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mdes04", UNEXPECTED_RATIO_PARAMS)


__all__ = ["fetch_safety_data"]
