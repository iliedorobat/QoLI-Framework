import time

from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file
from framework.stats.dimensions.health.health_params import *
from framework.stats.dimensions.health.health_paths import *


def fetch_health_data() -> None:
    time.sleep(0.1)

    write_to_json_file(_get_body_mass_index_ratio(), HEALTH_RAW_PATH, BMI_FILE_NAME)
    write_to_json_file(_get_depressive_ratio(), HEALTH_RAW_PATH, DEPRESSIVE_RATIO_FILE_NAME)
    write_to_json_file(_get_life_expectancy(), HEALTH_RAW_PATH, LIFE_EXPECTANCY_FILE_NAME)
    write_to_json_file(_get_long_health_issues_ratio(), HEALTH_RAW_PATH, LONG_HEALTH_ISSUES_RATIO_FILE_NAME)
    write_to_json_file(_get_health_personnel_ratio(), HEALTH_RAW_PATH, HEALTH_PERSONNEL_FILE_NAME)
    write_to_json_file(_get_healthy_life_ratio(), HEALTH_RAW_PATH, HEALTHY_LIFE_RATIO_FILE_NAME)
    write_to_json_file(_get_healthy_life_years(), HEALTH_RAW_PATH, HEALTHY_LIFE_YEARS_FILE_NAME)
    write_to_json_file(_get_hospital_beds_ratio(), HEALTH_RAW_PATH, HOSPITAL_BEDS_FILE_NAME)
    write_to_json_file(_get_non_alcoholic_ratio(), HEALTH_RAW_PATH, NON_ALCOHOLIC_RATIO_FILE_NAME)
    write_to_json_file(_get_non_fruits_vegetables_ratio(), HEALTH_RAW_PATH, NON_FRUITS_VEGETABLES_RATIO_FILE_NAME)
    write_to_json_file(_get_physical_activities_ratio(), HEALTH_RAW_PATH, PHYSICAL_ACTIVITIES_RATIO_FILE_NAME)
    write_to_json_file(_get_smokers_ratio(), HEALTH_RAW_PATH, SMOKERS_RATIO_FILE_NAME)
    write_to_json_file(_get_unmet_dental_ratio(), HEALTH_RAW_PATH, UNMET_DENTAL_RATIO_FILE_NAME)
    write_to_json_file(_get_unmet_medical_ratio(), HEALTH_RAW_PATH, UNMET_MEDICAL_RATIO_FILE_NAME)
    write_to_json_file(_get_work_accidents(), HEALTH_RAW_PATH, WORK_ACCIDENTS_FILE_NAME)


def _get_body_mass_index_ratio() -> str:
    """People having body mass within normal limits.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_bm1i
    Years: 2014; 2019
    """
    return fetch_data("hlth_ehis_bm1i", BMI_PARAMS)


def _get_depressive_ratio() -> str:
    """Share of people who faced depressive symptoms.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_mh1i
    Years: 2014; 2019

    GREATER IS WORSE!
    """
    return fetch_data("hlth_ehis_mh1i", DEPRESSIVE_RATIO_PARAMS)


def _get_health_personnel_ratio() -> str:
    """Health personnel: medical doctors, nurses and midwives, dentists,
    pharmacists, and physiotherapists.

    Aggregation: NUTS 2 regions
    Dataset: hlth_rs_prs2
    Value type: per hundred thousand inhabitants (number)
    Years: 1960-2024
    """
    return fetch_data("hlth_rs_prs2", HEALTH_PERSONNEL_PARAMS)


def _get_healthy_life_ratio() -> str:
    """Self-perceived very-good or good health (people aged 16 years or over).

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_silc_10
    Years: 2005-2025
    """
    return fetch_data("hlth_silc_10", HEALTHY_LIFE_RATIO_PARAMS)


def _get_healthy_life_years() -> str:
    """Health expectancy at birth.

    The number of remaining years that a person of specific age is expected
    to live without any severe or moderate health problems.

    Aggregation: country
    Data type: year (number)
    Dataset: hlth_hlye
    Years: 2004-2024
    """
    return fetch_data("hlth_hlye", HEALTHY_LIFE_YEARS_PARAMS)


def _get_hospital_beds_ratio() -> str:
    """Hospital beds.

    Aggregation: country
    Data type: per hundred thousand inhabitants (number)
    Dataset: hlth_rs_bds1
    Years: 1960-2024

    Hospital beds provide information on health care capacities,
    i.e. the maximum number of patients who can be treated by hospitals.
    """
    return fetch_data("hlth_rs_bds1", HOSPITAL_BEDS_PARAMS)


def _get_life_expectancy() -> str:
    """Life expectancy at birth.

    The number of remaining years a person is expected to live at birth
    or at a certain age.

    Aggregation: country
    Data type: years (number)
    Dataset: demo_mlexpec
    Years: 1960-2024

    Comments: NUTS 2 regions => demo_r_mlifexp
    """
    return fetch_data("demo_mlexpec", LIFE_EXPECTANCY_PARAMS)


def _get_long_health_issues_ratio() -> str:
    """People (aged 16 years or over) having a long-standing illness
    or health problem.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_silc_11
    Years: 2008-2025

    GREATER IS WORSE!
    """
    return fetch_data("hlth_silc_11", LONG_HEALTH_ISSUE_RATIO_PARAMS)


def _get_non_alcoholic_ratio() -> str:
    """Share of the population who have never or in the last 12 months
    had heavy episodic drinking.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_al3i
    Years: 2014; 2019
    """
    return fetch_data("hlth_ehis_al3i", NON_ALCOHOLIC_RATIO_PARAMS)


def _get_non_fruits_vegetables_ratio() -> str:
    """Share of people who don't consume fruit and vegetables daily.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_fv3i
    Years: 2014; 2019
    """
    return fetch_data("hlth_ehis_fv3i", NON_FRUITS_VEGETABLES_RATIO_PARAMS)


def _get_physical_activities_ratio() -> str:
    """Proportion of the population aged 15 and over who did aerobic
    and muscle-strengthening exercise.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_pe9e
    Years: 2014; 2019
    """
    return fetch_data("hlth_ehis_pe9e", PHYSICAL_ACTIVITIES_RATIO_PARAMS)


def _get_smokers_ratio() -> str:
    """Daily smokers of cigarettes.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_ehis_sk3i
    Years: 2014; 2019

    GREATER IS WORSE!
    """
    return fetch_data("hlth_ehis_sk3i", SMOKERS_RATIO_PARAMS)


def _get_unmet_dental_ratio() -> str:
    """Self-reported unmet needs for dental examination (people aged 16 years or over).

    Reasons: Too expensive or too far to travel or waiting list.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_silc_09
    Years: 2008-2025

    GREATER IS WORSE!
    """
    return fetch_data("hlth_silc_09", UNMET_DENTAL_RATIO_PARAMS)


def _get_unmet_medical_ratio() -> str:
    """Self-reported unmet needs for medical examination (people aged 16 years or over).

    Reasons: Too expensive or too far to travel or waiting list.

    Aggregation: country
    Data type: percentage (%)
    Dataset: hlth_silc_08
    Years: 2008-2025

    GREATER IS WORSE!
    """
    return fetch_data("hlth_silc_08", UNMET_MEDICAL_RATIO_PARAMS)


def _get_work_accidents() -> str:
    """Accidents at work requiring 4 or more days of medical care.

    Aggregation: country
    Data type: number
    Dataset: hsw_mi07
    Years: 2008-2024

    GREATER IS WORSE!
    """
    return fetch_data("hsw_mi07", WORK_ACCIDENTS_PARAMS)


__all__ = ["fetch_health_data"]
