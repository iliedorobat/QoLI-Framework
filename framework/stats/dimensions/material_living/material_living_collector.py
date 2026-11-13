import time

from framework.fetching.fetch_data import fetch_data, fetch_satisfaction_ratio
from framework.fetching.file_utils import write_to_json_file
from framework.stats.dimensions.material_living.material_living_params import *
from framework.stats.dimensions.material_living.material_living_paths import *


def fetch_material_living_data() -> None:
    time.sleep(0.1)
    write_to_json_file(_get_dwelling_issues_ratio(), LIVING_CONDITIONS_RAW_PATH, DWELLING_ISSUES_RATIO_FILE_NAME)
    write_to_json_file(_get_end_meet_inability_ratio(), LIVING_CONDITIONS_RAW_PATH, END_MEET_INABILITY_RATIO_FILE_NAME)
    write_to_json_file(_get_financial_satisfaction(), LIVING_CONDITIONS_RAW_PATH, FINANCIAL_SATISFACTION_FILE_NAME)
    write_to_json_file(_get_gdp_per_capita_pps_ratio(), LIVING_CONDITIONS_RAW_PATH, GDP_PER_CAPITA_PPS_RATIO_FILE_NAME)
    write_to_json_file(_get_high_income_ratio(), LIVING_CONDITIONS_RAW_PATH, HIGH_INCOME_RATIO_FILE_NAME)
    write_to_json_file(_get_income_quintile_ratio(), LIVING_CONDITIONS_RAW_PATH, INCOME_QUINTILE_RATIO_FILE_NAME)
    write_to_json_file(_get_lack_of_baths_ratio(), LIVING_CONDITIONS_RAW_PATH, LACK_OF_BATHS_RATIO_FILE_NAME)
    write_to_json_file(_get_low_work_intensity_ratio(), LIVING_CONDITIONS_RAW_PATH, LOW_WORK_INTENSITY_RATIO_FILE_NAME)
    write_to_json_file(_get_material_deprivation_ratio(), LIVING_CONDITIONS_RAW_PATH, MATERIAL_DEPRIVATION_RATIO_FILE_NAME)
    write_to_json_file(_get_median_income(), LIVING_CONDITIONS_RAW_PATH, MEDIAN_INCOME_PPS_FILE_NAME)
    write_to_json_file(_get_over_occupied_ratio(), LIVING_CONDITIONS_RAW_PATH, OVER_OCCUPIED_RATIO_FILE_NAME)
    write_to_json_file(_get_poverty_risk_ratio(), LIVING_CONDITIONS_RAW_PATH, POVERTY_RISK_RATIO_FILE_NAME)
    write_to_json_file(_get_under_occupied_ratio(), LIVING_CONDITIONS_RAW_PATH, UNDER_OCCUPIED_RATIO_FILE_NAME)


def _get_dwelling_issues_ratio() -> str:
    """
    Population living in a dwelling with a leaking roof, damp walls, floors
    or foundation, or rot in window frames of floor

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mdho01
    Note: EU-SILC survey
    Years: 2003-2020; 2023

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mdho01", DWELLING_ISSUES_RATIO_PARAMS)


def _get_end_meet_inability_ratio() -> str:
    """
    Inability to make ends meet

    Aggregation: country
    Data type: proportion of the population living in households, by difficulty of making ends meet (%)
    Dataset: ilc_mdes09
    Note: EU-SILC survey
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mdes09", END_MEET_INABILITY_RATIO_PARAMS)


def _get_financial_satisfaction() -> str:
    """
    Percentage of the population rating their financial satisfaction as high

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(FINANCIAL_SATISFACTION_PARAMS)


def _get_gdp_per_capita_pps_ratio() -> str:
    """
    GDP per capita in PPS

    Aggregation: country
    Data type: percentage (%)
    Dataset: tec00114
    Years: 2011-2025
    """
    return fetch_data("tec00114", GDP_PER_CAPITA_PPS_RATIO_PARAMS)


def _get_high_income_ratio() -> str:
    """
    Proportion of the population having income of 130% of median income or more

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_di20
    Years: 2003-2025
    """
    return fetch_data("ilc_di20", HIGH_INCOME_RATIO_PARAMS)


def _get_income_quintile_ratio() -> str:
    """
    Inequality of income distribution (S80/S20 income quintile share ratio) by selected age group

    Aggregation: country
    Data type: income quintile share ratio (%)
    Dataset: ilc_di11
    Note: EU-SILC survey
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("ilc_di11", INCOME_QUINTILE_RATIO_PARAMS)


def _get_lack_of_baths_ratio() -> str:
    """
    Population having neither a bath, nor a shower, nor indoor flushing toilet in their household

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mdho05
    Note: EU-SILC survey
    Years: 2003-2020; 2023

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mdho05", LACK_OF_BATHS_RATIO_PARAMS)


def _get_low_work_intensity_ratio() -> str:
    """
    People living in households with very low work intensity

    Aggregation: country
    Data type: proportion of total population aged 0 to 59 years (%)
    Dataset: ilc_lvhl11
    Years: 2003-2025

    Comments: NUTS 2 regions => ilc_lvhl21

    GREATER IS WORSE!
    """
    return fetch_data("ilc_lvhl11", LOW_WORK_INTENSITY_RATIO_PARAMS)


def _get_material_deprivation_ratio() -> str:
    """
    Severe material deprivation rate

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_mddd11
    Years: 2003-2020

    Comments: NUTS regions => ilc_mddd21

    GREATER IS WORSE!
    """
    return fetch_data("ilc_mddd11", MATERIAL_DEPRIVATION_RATIO_PARAMS)


def _get_median_income() -> str:
    """
    Median income

    Aggregation: country
    Data type: Purchasing Power Standard (number)
    Dataset: ilc_di03
    Note: EU-SILC and ECHP surveys
    Years: 1995-2025

    Comments: NUTS 2 regions => nama_10r_2hhinc
    """
    return fetch_data("ilc_di03", MEDIAN_INCOME_PPS_PARAMS)


def _get_over_occupied_ratio() -> str:
    """
    Overcrowding rate

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_lvho05a
    Note: EU-SILC survey
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("ilc_lvho05a", OVER_OCCUPIED_RATIO_PARAMS)


def _get_poverty_risk_ratio() -> str:
    """
    At-risk-of-poverty rate (cut-off point: 60% of median equivalised income after social transfers)

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_li03
    Note: EU-SILC and ECHP surveys
    Years: 1995-2025

    Comments: NUTS regions => ilc_li41

    GREATER IS WORSE!
    """
    return fetch_data("ilc_li03", POVERTY_RISK_RATIO_PARAMS)


def _get_under_occupied_ratio() -> str:
    """
    Share of people living in under-occupied dwellings

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_lvho50a
    Note: EU-SILC survey
    Years: 2003-2025
    """
    return fetch_data("ilc_lvho50a", UNDER_OCCUPIED_RATIO_PARAMS)


__all__ = ["fetch_material_living_data"]
