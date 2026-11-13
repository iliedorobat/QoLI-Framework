import time

from framework.fetching.fetch_data import fetch_data, fetch_satisfaction_ratio, fetch_active_people_ratio
from framework.fetching.file_utils import write_to_json_file
from framework.stats.dimensions.leisure_interaction.leisure_interaction_params import *
from framework.stats.dimensions.leisure_interaction.leisure_interaction_paths import *


def fetch_leisure_interaction_data() -> None:
    time.sleep(0.1)

    write_to_json_file(_get_asking_ratio(), LEISURE_INTERACT_RAW_PATH, ASKING_RATIO_FILE_NAME)
    write_to_json_file(_get_discussion_ratio(), LEISURE_INTERACT_RAW_PATH, DISCUSSION_RATIO_FILE_NAME)
    write_to_json_file(_get_frequency_contact_ratio(), LEISURE_INTERACT_RAW_PATH, FREQUENCY_CONTACT_RATIO_FILE_NAME)
    write_to_json_file(_get_getting_together_ratio(), LEISURE_INTERACT_RAW_PATH, GETTING_TOGETHER_RATIO_FILE_NAME)
    write_to_json_file(_get_recreation_areas_satisfaction(), LEISURE_INTERACT_RAW_PATH, AREA_SATISFACTION_RATIO_FILE_NAME)
    write_to_json_file(_get_relationships_satisfaction(), LEISURE_INTERACT_RAW_PATH, RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME)
    write_to_json_file(_get_social_activities_np_ratio(), LEISURE_INTERACT_RAW_PATH, SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME)
    write_to_json_file(_get_social_activities_ratio(), LEISURE_INTERACT_RAW_PATH, SOCIAL_ACTIVITIES_RATIO_FILE_NAME)
    write_to_json_file(_get_time_spent_satisfaction(), LEISURE_INTERACT_RAW_PATH, TIME_SPENT_SATISFACTION_FILE_NAME)
    write_to_json_file(_get_voluntary_activities_np_ratio(), LEISURE_INTERACT_RAW_PATH, VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME)
    write_to_json_file(_get_voluntary_activities_ratio(), LEISURE_INTERACT_RAW_PATH, VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME)


def _get_asking_ratio() -> str:
    """Persons (16 years or over) who have someone to ask for help (moral, material or financial) from family, relatives, friends or neighbours.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp15
    Years: 2015
    """
    return fetch_data("ilc_scp15", ASKING_RATIO_PARAMS)


def _get_discussion_ratio() -> str:
    """Persons (16 years or over) who have someone to discuss personal matters.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp17
    Years: 2013; 2015
    """
    return fetch_data("ilc_scp17", DISCUSSION_PARAMS_RATIO)


def _get_getting_together_ratio() -> str:
    """People (16 years or over) getting together with family, relatives or friends at least once a week.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp09
    Years: 2015; 2022
    """
    return fetch_data("ilc_scp09", INTERACTIONS_PARAMS)


def _get_frequency_contact_ratio() -> str:
    """People (16 years or over) who contact family, relatives or friends at least once a week.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp11
    Years: 2015; 2022
    """
    return fetch_data("ilc_scp11", INTERACTIONS_PARAMS)


def _get_recreation_areas_satisfaction() -> str:
    """Percentage of the population rating the satisfaction with recreational and green areas as high.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(AREA_SATISFACTION_RATIO_PARAMS)


def _get_relationships_satisfaction() -> str:
    """Percentage of the population rating their personal relationships' satisfaction as high.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(REL_SATISFACTION_RATIO_PARAMS)


def _get_social_activities_np_ratio() -> str:
    """Non-participation in cultural activities or sports events during the previous 12 months due to financial reasons or due to a lack of facilities.

    People aged 16 years or over.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp05
    Years: 2015; 2022

    GREATER IS WORSE!
    """
    return fetch_data("ilc_scp05", SOCIAL_ACTIVITIES_NP_PARAMS)


def _get_social_activities_ratio() -> str:
    """Participation in any cultural or sport activities in the last 12 months.

    Population aged 16 years or over.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp02
    Years: 2015; 2022

    Comments: NUTS 2 regions => ilc_scp01
    """
    return fetch_data("ilc_scp02", SOCIAL_ACTIVITIES_RATIO_PARAMS)


def _get_time_spent_satisfaction() -> str:
    """Percentage of the population rating their time spent satisfaction as high.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_pw05b
    Years: 2013; 2018; 2022
    """
    return fetch_satisfaction_ratio(TIME_SATISFACTION_RATIO_PARAMS)


def _get_voluntary_activities_np_ratio() -> str:
    """Non-participation in formal or informal voluntary activities during the previous 12 months due to lack of time or due to no interest.

    People aged 16 years or over.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp21
    Years: 2015

    GREATER IS WORSE!
    """
    return fetch_data("ilc_scp21", VOLUNTARY_ACTIVITIES_NP_RATIO_PARAMS)


def _get_voluntary_activities_ratio() -> str:
    """Participation in formal or informal voluntary activities.

    People aged 16 years or over.

    Aggregation: country
    Data type: percentage (%)
    Dataset: ilc_scp19
    Years: 2015; 2022
    """
    return fetch_active_people_ratio(VOLUNTARY_RATIO_PARAMS)


__all__ = ["fetch_leisure_interaction_data"]
