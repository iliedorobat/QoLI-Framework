from framework.common.param_names import ParamNames
from framework.common.param_values import HEALTH_PROBLEMS, MED_SPEC
from framework.fetching.fetch_utils import add_params


def _add_params(params: dict[str, list[str]], param_name: str, param_values: list[str]) -> None:
    add_params(params, param_name, param_values)


def _get_health_personnel_params(roles: str | list[str]) -> dict[str, list[str]]:
    if isinstance(roles, str):
        roles = [roles]

    params = {
        ParamNames.FREQ: ["A"],
        ParamNames.UNIT: ["P_HTHAB"],
        ParamNames.WORKING_STATUS: ["PRACT"],
    }

    _add_params(params, ParamNames.MED_SPEC, roles)
    return params


def _get_depressive_params(health_problems: str | list[str]) -> dict[str, list[str]]:
    if isinstance(health_problems, str):
        health_problems = [health_problems]

    params = {
        ParamNames.AGE: ["TOTAL"],
        ParamNames.FREQ: ["A"],
        ParamNames.QUANT_INC: ["TOTAL"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["PC"],
    }

    _add_params(params, ParamNames.HEALTH_PROBLEMS, health_problems)
    return params


BMI_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.BMI: ["BMI18P5-24"],  # Normal body mass
    ParamNames.FREQ: ["A"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

DEPRESSIVE_RATIO_PARAMS = _get_depressive_params([
    HEALTH_PROBLEMS.DEPRESSIVE,
    HEALTH_PROBLEMS.MAJOR_DEPRESSIVE,
    HEALTH_PROBLEMS.OTHER_DEPRESSIVE,
])
DEPRESSIVE_NORMAL_RATIO_PARAMS = _get_depressive_params(HEALTH_PROBLEMS.DEPRESSIVE)
DEPRESSIVE_MAJOR_RATIO_PARAMS = _get_depressive_params(HEALTH_PROBLEMS.MAJOR_DEPRESSIVE)
DEPRESSIVE_OTHER_RATIO_PARAMS = _get_depressive_params(HEALTH_PROBLEMS.OTHER_DEPRESSIVE)


HEALTH_PERSONNEL_PARAMS = _get_health_personnel_params([
    MED_SPEC.CARING_PERSONAL,
    MED_SPEC.DENTISTS,
    MED_SPEC.DOCTORS,
    MED_SPEC.MIDWIVES,
    MED_SPEC.NURSES,
    MED_SPEC.PHARMACISTS,
    MED_SPEC.PHYSIOTHERAPISTS,
])
PERSONNEL_CARING_PARAMS = _get_health_personnel_params(MED_SPEC.CARING_PERSONAL)
PERSONNEL_DENTISTS_PARAMS = _get_health_personnel_params(MED_SPEC.DENTISTS)
PERSONNEL_DOCTORS_PARAMS = _get_health_personnel_params(MED_SPEC.DOCTORS)
PERSONNEL_MIDWIVES_PARAMS = _get_health_personnel_params(MED_SPEC.MIDWIVES)
PERSONNEL_NURSES_PARAMS = _get_health_personnel_params(MED_SPEC.NURSES)
PERSONNEL_PHARMA_PARAMS = _get_health_personnel_params(MED_SPEC.PHARMACISTS)
PERSONNEL_THERAPISTS_PARAMS = _get_health_personnel_params(MED_SPEC.PHYSIOTHERAPISTS)


HEALTHY_LIFE_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.LEVELS: ["VG_G"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

HEALTHY_LIFE_YEARS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HLTH_HLE: ["HLY_Y0"],  # Healthy life years in absolute value at birth
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["YR"],
}

HOSPITAL_BEDS_PARAMS = {
    ParamNames.FACILITY: ["HBEDT"],
    ParamNames.HEALTH_CARE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.UNIT: ["P_HTHAB"],
}

LIFE_EXPECTANCY_PARAMS = {
    ParamNames.AGE: ["Y_LT1"],  # Life expectancy for people less than 1 year
    ParamNames.FREQ: ["A"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["YR"],
}

LONG_HEALTH_ISSUE_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

NON_ALCOHOLIC_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.FREQUENCY: ["NVR_NM12"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

NON_FRUITS_VEGETABLES_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.N_PORTION: ["0"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

PHYSICAL_ACTIVITIES_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.ISCED_11: ["TOTAL"],
    ParamNames.PHYSICAL_ACTIVITY: ["MV_AERO_MSC"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

SMOKERS_RATIO_PARAMS = {
    ParamNames.AGE: ["TOTAL"],
    ParamNames.FREQ: ["A"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.SEX: ["T"],
    ParamNames.SMOKING: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

UNMET_DENTAL_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.REASON: ["TXP_TFAR_WLIST"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

UNMET_MEDICAL_RATIO_PARAMS = {
    ParamNames.AGE: ["Y_GE16"],
    ParamNames.FREQ: ["A"],
    ParamNames.QUANT_INC: ["TOTAL"],
    ParamNames.REASON: ["TXP_TFAR_WLIST"],
    ParamNames.SEX: ["T"],
    ParamNames.UNIT: ["PC"],
}

WORK_ACCIDENTS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.INJURY: ["TOTAL"],
    ParamNames.NACE_R2: ["A_C-N"],
    ParamNames.SEVERITY: ["D_GE4"],
    ParamNames.UNIT: ["NR"],
}


__all__ = [
    "BMI_PARAMS",
    "DEPRESSIVE_RATIO_PARAMS",
    "DEPRESSIVE_NORMAL_RATIO_PARAMS",
    "DEPRESSIVE_MAJOR_RATIO_PARAMS",
    "DEPRESSIVE_OTHER_RATIO_PARAMS",
    "HEALTH_PERSONNEL_PARAMS",
    "PERSONNEL_CARING_PARAMS",
    "PERSONNEL_DENTISTS_PARAMS",
    "PERSONNEL_DOCTORS_PARAMS",
    "PERSONNEL_MIDWIVES_PARAMS",
    "PERSONNEL_NURSES_PARAMS",
    "PERSONNEL_PHARMA_PARAMS",
    "PERSONNEL_THERAPISTS_PARAMS",
    "HEALTHY_LIFE_RATIO_PARAMS",
    "HEALTHY_LIFE_YEARS_PARAMS",
    "HOSPITAL_BEDS_PARAMS",
    "LIFE_EXPECTANCY_PARAMS",
    "LONG_HEALTH_ISSUE_RATIO_PARAMS",
    "NON_ALCOHOLIC_RATIO_PARAMS",
    "NON_FRUITS_VEGETABLES_RATIO_PARAMS",
    "PHYSICAL_ACTIVITIES_RATIO_PARAMS",
    "SMOKERS_RATIO_PARAMS",
    "UNMET_DENTAL_RATIO_PARAMS",
    "UNMET_MEDICAL_RATIO_PARAMS",
    "WORK_ACCIDENTS_PARAMS",
]
