from framework.common.param_names import ParamNames
from framework.common.param_values import WORKING_FLEXIBILITY, SATISFACTION_LEVELS, SATISFACTION_TYPES
from framework.fetching.fetch_utils import add_params
from framework.stats.dimensions.auxiliary.auxiliary_params import get_satisfaction_params, LOW_WORK_INTENSITY_RATIO_PARAMS as AUX_LOW_WORK_INTENSITY_RATIO_PARAMS


def _get_flexibility_params(flexibilities: str | list[str]) -> dict[str, list[str]]:
    if isinstance(flexibilities, str):
        flexibilities = [flexibilities]

    params = {
        ParamNames.AGE: ["Y15-74"],
        ParamNames.FREQ: ["A"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["THS_PER"],
        ParamNames.WORKING_STATUS: ["EMP"],
    }
    add_params(params, ParamNames.WORKING_TIME_FLEX, flexibilities)
    return params


AVG_WORKING_HOURS_2007_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.NACE_R1: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["HR"],
    ParamNames.WORK_TIME: ["TOTAL"],
    ParamNames.WORKING_STATUS: ["EMP"],
}

AVG_WORKING_HOURS_2008_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.NACE_R2: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["HR"],
    ParamNames.WORK_TIME: ["TOTAL"],
    ParamNames.WORKING_STATUS: ["EMP"],
}

EMPLOYMENT_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

INACTIVE_POPULATION_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

INVOLUNTARY_PART_TIME_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

JOB_SATISFACTION_PARAMS = get_satisfaction_params(SATISFACTION_LEVELS.HIGH, SATISFACTION_TYPES.JOB)

LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-74"],
    ParamNames.FREQ: ["A"],
    ParamNames.INDIC_EM: ["LTU"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC_ACT"],
}

LOW_WAGE_EARNINGS_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.SIZECLAS: ["GE10"],
    ParamNames.UNIT: ["PC"],
}

LOW_WORK_INTENSITY_RATIO_PARAMS = AUX_LOW_WORK_INTENSITY_RATIO_PARAMS

RESEARCHERS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.PROF_POS: ["TOTAL"],
    ParamNames.SECT_PERF: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["FTE"],
}

TEMPORARY_EMPLOYMENT_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC_EMP"],
    ParamNames.WORKING_STATUS: ["EMP_TEMP"],
}

UNEMPLOYMENT_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-74"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

WORKING_FLEXIBILITY_RATIO_PARAMS = _get_flexibility_params([
    WORKING_FLEXIBILITY.TOTAL,
    WORKING_FLEXIBILITY.PERSON_DECISION,
    WORKING_FLEXIBILITY.RESTRICTIVE_DECISION,
])
WORKING_FLEXIBILITY_FULL_RATIO_PARAMS = _get_flexibility_params(WORKING_FLEXIBILITY.PERSON_DECISION)
WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_PARAMS = _get_flexibility_params(WORKING_FLEXIBILITY.RESTRICTIVE_DECISION)
WORKING_FLEXIBILITY_TOTAL_RATIO_PARAMS = _get_flexibility_params(WORKING_FLEXIBILITY.TOTAL)

WORKING_NIGHTS_RATIO_PARAMS = {
    ParamNames.AGE: ["Y15-64"],
    ParamNames.FREQ: ["A"],
    ParamNames.FREQUENCY: ["USU"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
    ParamNames.WORKING_STATUS: ["EMP"],
}


__all__ = [
    "AVG_WORKING_HOURS_2007_PARAMS",
    "AVG_WORKING_HOURS_2008_PARAMS",
    "EMPLOYMENT_RATIO_PARAMS",
    "INACTIVE_POPULATION_RATIO_PARAMS",
    "INVOLUNTARY_PART_TIME_RATIO_PARAMS",
    "JOB_SATISFACTION_PARAMS",
    "LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS",
    "LOW_WAGE_EARNINGS_RATIO_PARAMS",
    "LOW_WORK_INTENSITY_RATIO_PARAMS",
    "RESEARCHERS_PARAMS",
    "TEMPORARY_EMPLOYMENT_RATIO_PARAMS",
    "UNEMPLOYMENT_RATIO_PARAMS",
    "WORKING_FLEXIBILITY_RATIO_PARAMS",
    "WORKING_FLEXIBILITY_FULL_RATIO_PARAMS",
    "WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_PARAMS",
    "WORKING_FLEXIBILITY_TOTAL_RATIO_PARAMS",
    "WORKING_NIGHTS_RATIO_PARAMS",
]
