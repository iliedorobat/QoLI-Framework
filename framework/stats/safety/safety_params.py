from collections.abc import Collection

from framework.common.param_names import ParamNames
from framework.common.param_values import ICCS
from framework.fetching.fetch_utils import add_params

CRIME_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

NON_PAYMENT_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

ICCS_VALUES = [
    ICCS.ASSAULT,
    ICCS.ATTEMPTED_HOMICIDE,
    ICCS.BRIBERY,
    ICCS.BURGLARY,
    ICCS.BURGLARY_PRIVATE,
    ICCS.COMPUTERS,
    ICCS.CRIMINAL_GROUPS,
    ICCS.CORRUPTION,
    ICCS.FRAUD,
    ICCS.HOMICIDE,
    ICCS.KIDNAPPING,
    ICCS.MONEY_LAUNDERING,
    ICCS.NARCOTICS,
    ICCS.RAPE,
    ICCS.ROBBERY,
    ICCS.SEXUAL_ASSAULT,
    ICCS.SEXUAL_VIOLENCE,
    ICCS.SEXUAL_EXPLOITATION,
    ICCS.THEFT,
    ICCS.THEFT_VEHICLE,
]


def _get_offences_params(offences: str | Collection[str]) -> dict[str, list[str]]:
    if isinstance(offences, str):
        offences = [offences]

    params = {
        ParamNames.FREQ: ["A"],
        ParamNames.UNIT: ["NR"],
    }
    add_params(params, ParamNames.ICCS, offences)
    return params


OFFENCES_PARAMS = _get_offences_params(ICCS_VALUES)

# UK = UKC-L + UKM + UKN (England and Wales + Scotland + Northern Ireland)
OFFENCES_ASSAULT_PARAMS = _get_offences_params(ICCS.ASSAULT)
OFFENCES_ATTEMPTED_HOMICIDE_PARAMS = _get_offences_params(ICCS.ATTEMPTED_HOMICIDE)
OFFENCES_BRIBERY_PARAMS = _get_offences_params(ICCS.BRIBERY)
OFFENCES_BURGLARY_PARAMS = _get_offences_params(ICCS.BURGLARY)
OFFENCES_BURGLARY_PRIVATE_PARAMS = _get_offences_params(ICCS.BURGLARY_PRIVATE)
OFFENCES_COMPUTERS_PARAMS = _get_offences_params(ICCS.COMPUTERS)
OFFENCES_CORRUPTION_PARAMS = _get_offences_params(ICCS.CORRUPTION)
OFFENCES_CRIMINAL_GROUPS_PARAMS = _get_offences_params(ICCS.CRIMINAL_GROUPS)
OFFENCES_FRAUD_PARAMS = _get_offences_params(ICCS.FRAUD)
OFFENCES_HOMICIDE_PARAMS = _get_offences_params(ICCS.HOMICIDE)
OFFENCES_KIDNAPPING_PARAMS = _get_offences_params(ICCS.KIDNAPPING)
OFFENCES_MONEY_LAUNDERING_PARAMS = _get_offences_params(ICCS.MONEY_LAUNDERING)
OFFENCES_NARCOTICS_PARAMS = _get_offences_params(ICCS.NARCOTICS)
OFFENCES_RAPE_PARAMS = _get_offences_params(ICCS.RAPE)
OFFENCES_ROBBERY_PARAMS = _get_offences_params(ICCS.ROBBERY)
OFFENCES_SEXUAL_ASSAULT_PARAMS = _get_offences_params(ICCS.SEXUAL_ASSAULT)
OFFENCES_SEXUAL_VIOLENCE_PARAMS = _get_offences_params(ICCS.SEXUAL_VIOLENCE)
OFFENCES_SEXUAL_EXPLOITATION_PARAMS = _get_offences_params(ICCS.SEXUAL_EXPLOITATION)
OFFENCES_THEFT_PARAMS = _get_offences_params(ICCS.THEFT)
OFFENCES_THEFT_VEHICLE_PARAMS = _get_offences_params(ICCS.THEFT_VEHICLE)


PENSION_PPS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.SPDEPB: ["TOTAL"],
    ParamNames.SPDEPM: ["TOTAL"],
    ParamNames.UNIT: ["PPS_HAB"],
}

SOCIAL_PROTECTION_PPS_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.SPDEPS: ["SPR"],
    ParamNames.UNIT: ["PPS_HAB"],
}

UNEXPECTED_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}


__all__ = [
    "CRIME_RATIO_PARAMS",
    "NON_PAYMENT_RATIO_PARAMS",
    "OFFENCES_PARAMS",
    "OFFENCES_ASSAULT_PARAMS",
    "OFFENCES_ATTEMPTED_HOMICIDE_PARAMS",
    "OFFENCES_BRIBERY_PARAMS",
    "OFFENCES_BURGLARY_PARAMS",
    "OFFENCES_BURGLARY_PRIVATE_PARAMS",
    "OFFENCES_COMPUTERS_PARAMS",
    "OFFENCES_CORRUPTION_PARAMS",
    "OFFENCES_CRIMINAL_GROUPS_PARAMS",
    "OFFENCES_FRAUD_PARAMS",
    "OFFENCES_HOMICIDE_PARAMS",
    "OFFENCES_KIDNAPPING_PARAMS",
    "OFFENCES_MONEY_LAUNDERING_PARAMS",
    "OFFENCES_NARCOTICS_PARAMS",
    "OFFENCES_RAPE_PARAMS",
    "OFFENCES_ROBBERY_PARAMS",
    "OFFENCES_SEXUAL_ASSAULT_PARAMS",
    "OFFENCES_SEXUAL_VIOLENCE_PARAMS",
    "OFFENCES_SEXUAL_EXPLOITATION_PARAMS",
    "OFFENCES_THEFT_PARAMS",
    "OFFENCES_THEFT_VEHICLE_PARAMS",
    "PENSION_PPS_PARAMS",
    "SOCIAL_PROTECTION_PPS_PARAMS",
    "UNEXPECTED_RATIO_PARAMS",
]
