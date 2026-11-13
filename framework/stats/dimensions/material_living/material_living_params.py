from framework.common.param_names import ParamNames
from framework.common.param_values import LEV_DIFF, SATISFACTION_LEVELS, SATISFACTION_TYPES
from framework.fetching.fetch_utils import add_params
from framework.stats.dimensions.auxiliary.auxiliary_params import get_satisfaction_params, LOW_WORK_INTENSITY_RATIO_PARAMS as AUX_LOW_WORK_INTENSITY_RATIO_PARAMS

QUINTILE_AGES = {
    "less_65": "Y_LT65",  # less than 65 years
    "over_65": "Y_GE65",  # 65 years or over
    "total": "TOTAL",     # Total
}


DWELLING_ISSUES_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}


def _get_end_meet_inability_params(levels: str | list[str]) -> dict[str, list[str]]:
    if isinstance(levels, str):
        levels = [levels]
    params = {
        ParamNames.FREQ: ["A"],
        ParamNames.HH_COMP: ["TOTAL"],
        ParamNames.RSK_POV_TH: ["TOTAL"],
        ParamNames.UNIT: ["PC"],
    }
    add_params(params, ParamNames.LEV_DIFF, levels)
    return params



def _get_income_quintile_params() -> dict[str, list[str]]:
    params = {
        ParamNames.FREQ: ["A"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["RAT"],
    }
    add_params(params, ParamNames.AGE, [
        QUINTILE_AGES.get("less_65"),
        QUINTILE_AGES.get("over_65"),
        QUINTILE_AGES.get("total"),
    ])
    return params


END_MEET_INABILITY_RATIO_PARAMS = _get_end_meet_inability_params([
    LEV_DIFF.GREAT_DIFFICULTY,
    LEV_DIFF.DIFFICULTY,
])
END_MEET_INABILITY_D_RATIO_PARAMS = _get_end_meet_inability_params(LEV_DIFF.DIFFICULTY)
END_MEET_INABILITY_GD_RATIO_PARAMS = _get_end_meet_inability_params(LEV_DIFF.GREAT_DIFFICULTY)

FINANCIAL_SATISFACTION_PARAMS = get_satisfaction_params(
    SATISFACTION_LEVELS.HIGH,
    SATISFACTION_TYPES.FINANCIAL,
)

GDP_PER_CAPITA_PPS_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.INDIC_PPP: ["VI_PPS_EU27_2020_HAB"],
    ParamNames.PPP_CAT_18: ["GDP"],
}

HIGH_INCOME_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.INCOME: ["PC_GE130"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

INCOME_QUINTILE_RATIO_PARAMS = _get_income_quintile_params()

LACK_OF_BATHS_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

LOW_WORK_INTENSITY_RATIO_PARAMS = AUX_LOW_WORK_INTENSITY_RATIO_PARAMS

MATERIAL_DEPRIVATION_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

MEDIAN_INCOME_PPS_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.STAT_INFO: ["MED_EI"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PPS"],
}

OVER_OCCUPIED_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

POVERTY_RISK_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["B_60"],
    ParamNames.STAT_INFO: ["MED_EI"],
    ParamNames.UNIT: ["PC"],
}

UNDER_OCCUPIED_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}


__all__ = [
    "QUINTILE_AGES",
    "DWELLING_ISSUES_RATIO_PARAMS",
    "END_MEET_INABILITY_RATIO_PARAMS",
    "END_MEET_INABILITY_D_RATIO_PARAMS",
    "END_MEET_INABILITY_GD_RATIO_PARAMS",
    "FINANCIAL_SATISFACTION_PARAMS",
    "GDP_PER_CAPITA_PPS_RATIO_PARAMS",
    "HIGH_INCOME_RATIO_PARAMS",
    "INCOME_QUINTILE_RATIO_PARAMS",
    "LACK_OF_BATHS_RATIO_PARAMS",
    "LOW_WORK_INTENSITY_RATIO_PARAMS",
    "MATERIAL_DEPRIVATION_RATIO_PARAMS",
    "MEDIAN_INCOME_PPS_PARAMS",
    "OVER_OCCUPIED_RATIO_PARAMS",
    "POVERTY_RISK_RATIO_PARAMS",
    "UNDER_OCCUPIED_RATIO_PARAMS",
]
