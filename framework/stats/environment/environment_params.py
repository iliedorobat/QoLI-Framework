"""
Parameters for fetching environment-related data from Eurostat.
"""

from framework.constants.param_names import ParamNames
from framework.fetching.fetch_utils import add_params
from framework.constants.param_values import AIR_POL

# Air pollution types
AIR_POL_TYPES = [
    AIR_POL.AMMONIA,
    AIR_POL.CARBON_MONOXIDE,
    AIR_POL.METHANE,
    AIR_POL.NON_METHANE,
    AIR_POL.NITROGEN_OXIDES,
    AIR_POL.PM2_5,
    AIR_POL.PM10
]


def _get_air_pollution_ratio_params(pollution_types):
    """
    Generate air pollution parameters for given pollution types.

    Args:
        pollution_types: str or list of str representing pollution types

    Returns:
        dict: Parameters for fetching air pollution data
    """
    if isinstance(pollution_types, str):
        pollution_types = [pollution_types]

    params = {
        ParamNames.NACE_R2: ["TOTAL"],
        ParamNames.FREQ: ["A"],
        ParamNames.UNIT: ["KG_HAB"],  # Kilograms per capita
    }
    add_params(params, ParamNames.AIR_POL, pollution_types)
    return params


# Air pollution parameters for different pollutants
AIR_POLLUTION_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL_TYPES)
AIR_POLLUTION_CH4_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.METHANE)
AIR_POLLUTION_CO_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.CARBON_MONOXIDE)
AIR_POLLUTION_NMVOC_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.NON_METHANE)
AIR_POLLUTION_NH3_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.AMMONIA)
AIR_POLLUTION_NOX_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.NITROGEN_OXIDES)
AIR_POLLUTION_PM_2_5_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.PM2_5)
AIR_POLLUTION_PM_10_RATIO_PARAMS = _get_air_pollution_ratio_params(AIR_POL.PM10)

# Noise pollution parameters
NOISE_POLLUTION_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

# Pollution parameters
POLLUTION_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.HH_COMP: ["TOTAL"],
    ParamNames.RSK_POV_TH: ["TOTAL"],
    ParamNames.UNIT: ["PC"],
}

# Water supply parameters
WATER_SUPPLY_RATIO_PARAMS = {
    ParamNames.FREQ: ["A"],
    ParamNames.UNIT: ["PC"],
    ParamNames.WAT_PROC: ["POP_PWS"],
}


__all__ = [
    'AIR_POLLUTION_RATIO_PARAMS',
    'AIR_POLLUTION_CH4_RATIO_PARAMS',
    'AIR_POLLUTION_CO_RATIO_PARAMS',
    'AIR_POLLUTION_NMVOC_RATIO_PARAMS',
    'AIR_POLLUTION_NH3_RATIO_PARAMS',
    'AIR_POLLUTION_NOX_RATIO_PARAMS',
    'AIR_POLLUTION_PM_2_5_RATIO_PARAMS',
    'AIR_POLLUTION_PM_10_RATIO_PARAMS',
    'NOISE_POLLUTION_RATIO_PARAMS',
    'POLLUTION_RATIO_PARAMS',
    'WATER_SUPPLY_RATIO_PARAMS',
]
