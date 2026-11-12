import time

from framework.fetching.fetch_data import fetch_data, fetch_satisfaction_ratio
from framework.fetching.file_utils import write_to_json_file
from framework.stats.main_activity.main_activity_params import *
from framework.stats.main_activity.main_activity_paths import *


def fetch_main_activity_data() -> None:
    time.sleep(0.1)
    write_to_json_file(_get_avg_working_hours_2007(), MAIN_ACTIVITY_RAW_PATH, AVG_WORKING_HOURS_2007_FILE_NAME)
    write_to_json_file(_get_avg_working_hours_2008(), MAIN_ACTIVITY_RAW_PATH, AVG_WORKING_HOURS_2008_FILE_NAME)
    write_to_json_file(_get_employment_ratio(), MAIN_ACTIVITY_RAW_PATH, EMPLOYMENT_RATIO_FILE_NAME)
    write_to_json_file(_get_inactive_population_ratio(), MAIN_ACTIVITY_RAW_PATH, INACTIVE_POPULATION_RATIO_FILE_NAME)
    write_to_json_file(_get_involuntary_part_time_ratio(), MAIN_ACTIVITY_RAW_PATH, INVOLUNTARY_PART_TIME_RATIO_FILE_NAME)
    write_to_json_file(_get_job_satisfaction(), MAIN_ACTIVITY_RAW_PATH, JOB_SATISFACTION_FILE_NAME)
    write_to_json_file(_get_long_term_unemployment_ratio(), MAIN_ACTIVITY_RAW_PATH, LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME)
    write_to_json_file(_get_low_wage_earners_ratio(), MAIN_ACTIVITY_RAW_PATH, LOW_WAGE_EARNERS_RATIO_FILE_NAME)
    write_to_json_file(_get_low_work_intensity_ratio(), MAIN_ACTIVITY_RAW_PATH, LOW_WORK_INTENSITY_RATIO_FILE_NAME)
    write_to_json_file(_get_researchers(), MAIN_ACTIVITY_RAW_PATH, RESEARCHERS_FILE_NAME)
    write_to_json_file(_get_temporary_employment_ratio(), MAIN_ACTIVITY_RAW_PATH, TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME)
    write_to_json_file(_get_unemployment_ratio(), MAIN_ACTIVITY_RAW_PATH, UNEMPLOYMENT_RATIO_FILE_NAME)
    write_to_json_file(_get_working_flexibility_ratio(), MAIN_ACTIVITY_RAW_PATH, WORKING_FLEXIBILITY_RATIO_FILE_NAME)
    write_to_json_file(_get_working_nights_ratio(), MAIN_ACTIVITY_RAW_PATH, WORKING_NIGHTS_RATIO_FILE_NAME)


def _get_avg_working_hours_2007() -> str:
    """
    Average number of usual weekly hours of work in main job
    worked by full-time employed persons aged 15 years or over

    Aggregation: country
    Data type: hours (number)
    Dataset: lfsa_ewhuna
    Years: 1992-2008

    Comments: NUTS 2 regions => lfst_r_lfe2ehour

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_ewhuna", AVG_WORKING_HOURS_2007_PARAMS)


def _get_avg_working_hours_2008() -> str:
    """
    Average number of usual weekly hours of work in main job
    worked by full-time employed persons aged 15 years or over

    Aggregation: country
    Data type: hours (number)
    Dataset: lfsa_ewhun2
    Years: 2008-2025

    Comments: NUTS 2 regions => lfst_r_lfe2ehour

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_ewhun2", AVG_WORKING_HOURS_2008_PARAMS)


def _get_employment_ratio() -> str:
    """
    Employment rates

    Aggregation: country
    Data type: percentage of people aged from 15 to 64 years (%)
    Dataset: lfsa_ergaed
    Years: 1983-2025

    Comments: NUTS 2 regions => lfst_r_lfe2emprt
    """
    return fetch_data("lfsa_ergaed", EMPLOYMENT_RATIO_PARAMS)


def _get_inactive_population_ratio() -> str:
    """
    Economically inactive population

    Aggregation: country
    Data type: percentage of the population aged 15-64 years who were economically inactive (%)
    Dataset: lfsa_ipga
    Years: 1983-2025

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_ipga", INACTIVE_POPULATION_RATIO_PARAMS)


def _get_involuntary_part_time_ratio() -> str:
    """
    Involuntary part-time employment
    Proxy for underemployment (working less than one is able and willing)

    Aggregation: country
    Data type: percentage of the total part-time employment aged 15-64 years (%)
    Dataset: lfsa_eppgai
    Years: 1983-2025

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_eppgai", INVOLUNTARY_PART_TIME_RATIO_PARAMS)


def _get_job_satisfaction() -> str:
    """
    Percentage of the population rating their job satisfaction as high

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(JOB_SATISFACTION_PARAMS)


def _get_long_term_unemployment_ratio() -> str:
    """
    Long-term unemployment rates - annual average (the ratio of people who have been
    unemployed for at least a year to the total size of the labour force)

    Aggregation: country
    Data type: percentage of labour force aged 15-74 years (%)
    Dataset: une_ltu_a
    Years: 2003-2025

    Comments: NUTS 2 regions => lfst_r_lfu2ltu

    GREATER IS WORSE!
    """
    return fetch_data("une_ltu_a", LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS)


def _get_low_wage_earners_ratio() -> str:
    """
    Low-wage earners as a proportion of all employees (excluding apprentices)

    Aggregation: country
    Data type: percentage of employees earning two thirds or less of national median earnings (%)
    Dataset: earn_ses_pub1a
    Years: 2006; 2010; 2014; 2018; 2022

    GREATER IS WORSE!
    """
    return fetch_data("earn_ses_pub1a", LOW_WAGE_EARNINGS_RATIO_PARAMS)


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


def _get_researchers() -> str:
    """
    Total researchers full-time equivalent
    FTE (Full-time equivalent) corresponds to one year's work by one person
    (for example, a person who devotes 40% of his time to R&D is counted as 0.4 FTE)

    Aggregation: country
    Data type: count (number)
    Dataset: rd_p_persocc
    Years: 1980-2024
    """
    return fetch_data("rd_p_persocc", RESEARCHERS_PARAMS)


def _get_temporary_employment_ratio() -> str:
    """
    Temporary contracts - annual data

    Aggregation: country
    Data type: percentage of total employment (%)
    Dataset: lfsi_pt_a
    Years: 2003-2025

    GREATER IS WORSE!
    """
    return fetch_data("lfsi_pt_a", TEMPORARY_EMPLOYMENT_RATIO_PARAMS)


def _get_unemployment_ratio() -> str:
    """
    Unemployment rates

    Aggregation: country
    Data type: percentage of labour force aged 15-74 years (%)
    Dataset: lfsa_urgaed
    Years: 1983-2025

    Comments: NUTS 2 regions => lfst_r_lfu3rt

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_urgaed", UNEMPLOYMENT_RATIO_PARAMS)


def _get_working_flexibility_ratio() -> str:
    """
    Persons in employment by working time flexibility

    Aggregation: country
    Data type: thousands persons aged from 15 to 74 years (%)
    Dataset: lfso_19fxwt01
    Years: 2019
    """
    return fetch_data("lfso_19fxwt01", WORKING_FLEXIBILITY_RATIO_PARAMS)


def _get_working_nights_ratio() -> str:
    """
    Employed persons working at nights

    Aggregation: country
    Data type: percentage of the total employment aged from 15 to 64 years (%)
    Dataset: lfsa_ewpnig
    Years: 1992-2025

    GREATER IS WORSE!
    """
    return fetch_data("lfsa_ewpnig", WORKING_NIGHTS_RATIO_PARAMS)


__all__ = ["fetch_main_activity_data"]
