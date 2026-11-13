from framework.common.param_names import ParamNames
from framework.common.param_values import *
from framework.fetching.fetch_utils import add_params
from framework.stats.dimensions.auxiliary.auxiliary_params import get_active_people_params, get_satisfaction_params

SOCIAL_INTERACTIONS = [
    ACL00.CINEMA,
    ACL00.CULTURE,
    ACL00.LIVE,
    ACL00.SPORTS,
]

VOLUNTARY_ACTIVITIES = [
    ACL00.FORMAL,
    ACL00.INFORMAL,
]


def _get_interactions_params(types: str | list[str]) -> dict[str, list[str]]:
    if isinstance(types, str):
        types = [types]

    params = {
        ParamNames.AGE: ["Y_GE16"],
        ParamNames.FREQ: ["A"],
        ParamNames.FREQUENCY: ["WEEK"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["PC"],
    }

    add_params(params, ParamNames.PERS_CAT, types)
    return params


def _get_non_participation_params(activity: str, reason: str) -> dict[str, list[str]]:
    return {
        ParamNames.ACL_00: [activity],
        ParamNames.AGE: ["Y_GE16"],
        ParamNames.FREQ: ["A"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.REASON: [reason],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["PC"],
    }


def _get_activities_np_params(activities: list[str], reasons: list[str]) -> dict[str, list[str]]:
    params = get_active_people_params(activities)
    add_params(params, ParamNames.REASON, reasons)
    return params


def _get_voluntary_activities_params(activity: str) -> dict[str, list[str]]:
    return get_active_people_params([activity])


AREA_SATISFACTION_RATIO_PARAMS = get_satisfaction_params(SATISFACTION_LEVELS.HIGH, SATISFACTION_TYPES.GREEN_AREAS)

ASKING_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

DISCUSSION_PARAMS_RATIO = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

INTERACTIONS_PARAMS = _get_interactions_params([
    PERS_CAT.FAMILY,
    PERS_CAT.FRIENDS,
])
INTERACTIONS_FAM_PARAMS = _get_interactions_params(PERS_CAT.FAMILY)
INTERACTIONS_FRD_PARAMS = _get_interactions_params(PERS_CAT.FRIENDS)

SOCIAL_ACTIVITIES_NP_PARAMS = _get_activities_np_params(SOCIAL_INTERACTIONS, [
    SOCIAL_ACTIVITIES_NP_REASON.AWAY,
    SOCIAL_ACTIVITIES_NP_REASON.FINANCIAL,
    SOCIAL_ACTIVITIES_NP_REASON.OTHER,
    SOCIAL_ACTIVITIES_NP_REASON.UNINTERESTED,
])

NP_FIN_CIN_RATIO_PARAMS = _get_non_participation_params(ACL00.CINEMA, SOCIAL_ACTIVITIES_NP_REASON.FINANCIAL)
NP_FIN_CULT_RATIO_PARAMS = _get_non_participation_params(ACL00.CULTURE, SOCIAL_ACTIVITIES_NP_REASON.FINANCIAL)
NP_FIN_LIVE_RATIO_PARAMS = _get_non_participation_params(ACL00.LIVE, SOCIAL_ACTIVITIES_NP_REASON.FINANCIAL)
NP_FIN_SPORT_RATIO_PARAMS = _get_non_participation_params(ACL00.SPORTS, SOCIAL_ACTIVITIES_NP_REASON.FINANCIAL)

NP_NNB_CIN_RATIO_PARAMS = _get_non_participation_params(ACL00.CINEMA, SOCIAL_ACTIVITIES_NP_REASON.AWAY)
NP_NNB_CULT_RATIO_PARAMS = _get_non_participation_params(ACL00.CULTURE, SOCIAL_ACTIVITIES_NP_REASON.AWAY)
NP_NNB_LIVE_RATIO_PARAMS = _get_non_participation_params(ACL00.LIVE, SOCIAL_ACTIVITIES_NP_REASON.AWAY)
NP_NNB_SPORT_RATIO_PARAMS = _get_non_participation_params(ACL00.SPORTS, SOCIAL_ACTIVITIES_NP_REASON.AWAY)

SOCIAL_ACTIVITIES_RATIO_PARAMS = {
    ParamNames.DEG_URB: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.FREQUENCY: ["GE1"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

TIME_SATISFACTION_RATIO_PARAMS = get_satisfaction_params(SATISFACTION_LEVELS.HIGH, SATISFACTION_TYPES.TIME_SPENT)

REL_SATISFACTION_RATIO_PARAMS = get_satisfaction_params(SATISFACTION_LEVELS.HIGH, SATISFACTION_TYPES.RELATIONSHIPS)

VOLUNTARY_ACTIVITIES_NP_RATIO_PARAMS = _get_activities_np_params(VOLUNTARY_ACTIVITIES, [
    VOLUNTARY_ACTIVITIES_NP_REASON.NO_INTEREST,
    VOLUNTARY_ACTIVITIES_NP_REASON.OTHER,
    VOLUNTARY_ACTIVITIES_NP_REASON.TIME,
])

NP_NO_INTEREST_FORMAL_RATIO_PARAMS = _get_non_participation_params(ACL00.FORMAL, VOLUNTARY_ACTIVITIES_NP_REASON.NO_INTEREST)
NP_NO_INTEREST_INFORMAL_RATIO_PARAMS = _get_non_participation_params(ACL00.INFORMAL, VOLUNTARY_ACTIVITIES_NP_REASON.NO_INTEREST)
NP_TIME_FORMAL_RATIO_PARAMS = _get_non_participation_params(ACL00.FORMAL, VOLUNTARY_ACTIVITIES_NP_REASON.TIME)
NP_TIME_INFORMAL_RATIO_PARAMS = _get_non_participation_params(ACL00.INFORMAL, VOLUNTARY_ACTIVITIES_NP_REASON.TIME)

VOLUNTARY_RATIO_PARAMS = get_active_people_params(VOLUNTARY_ACTIVITIES)

FORMAL_VOLUNTARY_RATIO_PARAMS = _get_voluntary_activities_params(ACL00.FORMAL)
INFORMAL_VOLUNTARY_RATIO_PARAMS = _get_voluntary_activities_params(ACL00.INFORMAL)


__all__ = [
    "SOCIAL_INTERACTIONS",
    "VOLUNTARY_ACTIVITIES",
    "AREA_SATISFACTION_RATIO_PARAMS",
    "ASKING_RATIO_PARAMS",
    "DISCUSSION_PARAMS_RATIO",
    "INTERACTIONS_PARAMS",
    "INTERACTIONS_FAM_PARAMS",
    "INTERACTIONS_FRD_PARAMS",
    "SOCIAL_ACTIVITIES_NP_PARAMS",
    "NP_FIN_CIN_RATIO_PARAMS",
    "NP_FIN_CULT_RATIO_PARAMS",
    "NP_FIN_LIVE_RATIO_PARAMS",
    "NP_FIN_SPORT_RATIO_PARAMS",
    "NP_NNB_CIN_RATIO_PARAMS",
    "NP_NNB_CULT_RATIO_PARAMS",
    "NP_NNB_LIVE_RATIO_PARAMS",
    "NP_NNB_SPORT_RATIO_PARAMS",
    "SOCIAL_ACTIVITIES_RATIO_PARAMS",
    "TIME_SATISFACTION_RATIO_PARAMS",
    "REL_SATISFACTION_RATIO_PARAMS",
    "VOLUNTARY_ACTIVITIES_NP_RATIO_PARAMS",
    "NP_NO_INTEREST_FORMAL_RATIO_PARAMS",
    "NP_NO_INTEREST_INFORMAL_RATIO_PARAMS",
    "NP_TIME_FORMAL_RATIO_PARAMS",
    "NP_TIME_INFORMAL_RATIO_PARAMS",
    "VOLUNTARY_RATIO_PARAMS",
    "FORMAL_VOLUNTARY_RATIO_PARAMS",
    "INFORMAL_VOLUNTARY_RATIO_PARAMS",
]
