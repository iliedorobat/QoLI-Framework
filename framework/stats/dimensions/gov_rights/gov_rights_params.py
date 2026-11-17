from framework.common.param_names import ParamNames
from framework.common.param_values import ACL00, DOMAIN
from framework.stats.dimensions.auxiliary.auxiliary_params import get_active_people_params

ACTIVITIES = [
    ACL00.CITIZENSHIP,
]

DOMAIN_TRUST = [
    DOMAIN.LEGAL,
    DOMAIN.POLICE,
    DOMAIN.POLITIC,
]

DOMAIN_TRUST_OTHER = [
    DOMAIN.OTHER,
]


CITIZENSHIP_RATIO_PARAMS = get_active_people_params(ACTIVITIES)


GENDER_EMP_GAP_PARAMS = {
    ParamNames.AGE: ["Y20-64"],
    ParamNames.INDIC_EM: ["EMP_LFS"],  # Total employment (resident population concept - LFS)
    ParamNames.FREQ: ["A"],
    ParamNames.UNIT: ["PC_POP"],
}


GENDER_PAY_GAP_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.NACE_R2: ["B-S_X_O"],
    ParamNames.UNIT: ["PC"],
}


def _get_population_trust_params(trust_systems: str | list[str]) -> dict[str, list[str]]:
    if isinstance(trust_systems, str):
        trust_systems = [trust_systems]

    params = {
        ParamNames.AGE: ["Y_GE16"],
        ParamNames.FREQ: ["A"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["RTG"],
    }

    params.setdefault(ParamNames.DOMAIN, set()).update(trust_systems)

    return params


POPULATION_TRUST_PARAMS = _get_population_trust_params(DOMAIN_TRUST)
POPULATION_TRUST_OTHER_PARAMS = _get_population_trust_params(DOMAIN_TRUST_OTHER)
POPULATION_LEGAL_TRUST_PARAMS = _get_population_trust_params(DOMAIN.LEGAL)
POPULATION_POLICE_TRUST_PARAMS = _get_population_trust_params(DOMAIN.POLICE)
POPULATION_POLITIC_TRUST_PARAMS = _get_population_trust_params(DOMAIN.POLITIC)
POPULATION_OTHER_TRUST_PARAMS = _get_population_trust_params(DOMAIN.OTHER)


__all__ = [
    "ACTIVITIES",
    "DOMAIN_TRUST",
    "DOMAIN_TRUST_OTHER",
    "CITIZENSHIP_RATIO_PARAMS",
    "GENDER_EMP_GAP_PARAMS",
    "GENDER_PAY_GAP_PARAMS",
    "POPULATION_TRUST_PARAMS",
    "POPULATION_TRUST_OTHER_PARAMS",
    "POPULATION_LEGAL_TRUST_PARAMS",
    "POPULATION_POLICE_TRUST_PARAMS",
    "POPULATION_POLITIC_TRUST_PARAMS",
    "POPULATION_OTHER_TRUST_PARAMS",
]
