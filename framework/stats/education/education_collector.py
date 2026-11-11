"""
Collector for education-related statistical data from Eurostat.
"""

from time import sleep

from framework.fetching.fetch_data import fetch_data
from framework.fetching.file_utils import write_to_json_file
from framework.stats.education.education_params import *
from framework.stats.education.education_paths import *


def fetch_education_data() -> None:
    """
    Fetch all education indicators and write them to JSON files.
    """
    sleep(0.1)  # Sleep 100 milliseconds
    write_to_json_file(_get_digital_skills_ratio(), EDUCATION_RAW_PATH, DIGITAL_SKILLS_RATIO_FILE_NAME)
    write_to_json_file(_get_dropout_ratio(), EDUCATION_RAW_PATH, DROPOUT_RATIO_FILE_NAME)
    write_to_json_file(_get_early_education_ratio(), EDUCATION_RAW_PATH, EARLY_EDU_RATIO_FILE_NAME)
    write_to_json_file(_get_education_ratio(), EDUCATION_RAW_PATH, EDU_RATIO_FILE_NAME)
    write_to_json_file(_get_inactive_young_ratio(), EDUCATION_RAW_PATH, INACTIVE_YOUNG_RATIO_FILE_NAME)
    write_to_json_file(_get_no_known_foreign_lang_ratio(), EDUCATION_RAW_PATH, NO_FOREIGN_LANG_KNOWN_RATIO_FILE_NAME)
    write_to_json_file(_get_pupils_ratio_2012(), EDUCATION_RAW_PATH, PUPILS_RATIO_2012_FILE_NAME)
    write_to_json_file(_get_pupils_ratio_2013(), EDUCATION_RAW_PATH, PUPILS_RATIO_2013_FILE_NAME)
    write_to_json_file(_get_training_last_month_ratio(), EDUCATION_RAW_PATH, TRAINING_LAST_MONTH_RATIO_FILE_NAME)
    write_to_json_file(_get_training_last_year_ratio(), EDUCATION_RAW_PATH, TRAINING_LAST_YEAR_RATIO_FILE_NAME)

def _get_digital_skills_ratio() -> str:
    """
    Share of individuals (aged 16-74) having at least basic digital skills.

    Aggregation: country
    Data type: percentage (%)
    Dataset: sdg_04_70
    Note: The digital skills indicator methodology has changed substantially to reflect the
          Digital Competence Framework 2.0. As a consequence, 2021 is the beginning of a new
          time series. https://ec.europa.eu/eurostat/cache/metadata/en/sdg_04_70_esmsip2.htm
    Years: 2021; 2023; 2025

    Returns:
        HTTP Response as a string
    """
    return fetch_data("sdg_04_70", DIGITAL_SKILLS_PARAMS)

def _get_dropout_ratio() -> str:
    """
    Early leavers (from 18 to 24 years) from education and training.

    Aggregation: country
    Data type: percentage (%)
    Dataset: edat_lfse_14
    Years: 1992-2025

    Comments: NUTS 2 regions => edat_lfse_16

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("edat_lfse_14", DROPOUT_RATIO_PARAMS)

def _get_early_education_ratio() -> str:
    """
    Participation in early childhood education: pupils aged between
    3 years old and the starting age of compulsory education at primary level.

    Aggregation: country
    Data type: percentage (%)
    Dataset: educ_uoe_enra21
    Years: 1998-2025

    Comments: NUTS 2 regions => educ_uoe_enra22

    Returns:
        HTTP Response as a string
    """
    return fetch_data("educ_uoe_enra21", EARLY_EDUCATION_RATIO_PARAMS)

def _get_education_ratio() -> str:
    """
    Population (from 15 to 64 years) by educational attainment level.

    Aggregation: country
    Data type: percentage (%)
    Dataset: edat_lfse_03
    Years: 1992-2025

    Comments: NUTS 2 regions => edat_lfse_04

    Returns:
        HTTP Response as a string
    """
    return fetch_data("edat_lfse_03", EDUCATION_RATIO_PARAMS)

def _get_inactive_young_ratio() -> str:
    """
    Young people (from 15 to 29 years) neither in employment nor in education and training.

    Aggregation: country
    Data type: NEET rates (%)
    Dataset: edat_lfse_20
    Years: 2000-2025

    Comments: NUTS 2 regions => edat_lfse_22

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("edat_lfse_20", INACTIVE_YOUNG_RATIO_PARAMS)

def _get_no_known_foreign_lang_ratio() -> str:
    """
    Proportion of people (from 25 to 64 years) who don't know any foreign language (self-reported).

    Aggregation: country
    Data type: percentage (%)
    Dataset: edat_aes_l21
    Years: 2007; 2011; 2016; 2022

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("edat_aes_l22", NO_FOREIGN_LANG_KNOWN_RATIO_PARAMS)

def _get_pupils_ratio_2012() -> str:
    """
    Ratio of pupils to teachers for primary and secondary education (levels 1-3).

    Aggregation: country
    Data type: ratio of pupils to teachers (number)
    Dataset: educ_iste
    Years: 1998-2012

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("educ_iste", PUPILS_RATIO_2012_PARAMS)

def _get_pupils_ratio_2013() -> str:
    """
    Ratio of pupils to teachers for primary and secondary education (levels 1-3).

    Aggregation: country
    Data type: ratio of pupils to teachers (number)
    Dataset: educ_uoe_perp04
    Years: 2013-2024

    GREATER IS WORSE!

    Returns:
        HTTP Response as a string
    """
    return fetch_data("educ_uoe_perp04", PUPILS_RATIO_2013_PARAMS)

def _get_training_last_month_ratio() -> str:
    """
    Participation rate in education and training (last 4 weeks - from 25 to 64 years).

    Aggregation: country
    Data type: percentage (%)
    Dataset: trng_lfse_03
    Years: 2004-2025

    Returns:
        HTTP Response as a string
    """
    return fetch_data("trng_lfse_03", TRAINING_LAST_MONTH_RATIO_PARAMS)

def _get_training_last_year_ratio() -> str:
    """
    Participation rate in education and training (last 12 months - from 25 to 64 years).

    Aggregation: country
    Data type: percentage (%)
    Dataset: trng_aes_100
    Years: 2007; 2011; 2016; 2022

    Returns:
        HTTP Response as a string
    """
    return fetch_data("trng_aes_100", TRAINING_LAST_YEAR_RATIO_PARAMS)


__all__ = [
    'fetch_education_data',
]
