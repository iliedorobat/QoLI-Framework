"""
Aggregation parameters for environment-related quality of life indicators.
"""

from framework.common.param_units import ParamUnits


ENVIRONMENT = "environment"

# Aggregated parameter names
AIR_POLLUTION_RATIO = ENVIRONMENT + ":airPollutionRatio"
AIR_POLLUTION_NH_3_RATIO = ENVIRONMENT + ":airPollutionNh3Ratio"
AIR_POLLUTION_CH_4_RATIO = ENVIRONMENT + ":airPollutionCh4Ratio"
AIR_POLLUTION_CO_RATIO = ENVIRONMENT + ":airPollutionCoRatio"
AIR_POLLUTION_NMVOC_RATIO = ENVIRONMENT + ":airPollutionNmvocRatio"
AIR_POLLUTION_NOX_RATIO = ENVIRONMENT + ":airPollutionNoxRatio"
AIR_POLLUTION_PM_2_5_RATIO = ENVIRONMENT + ":airPollutionPm2_5Ratio"
AIR_POLLUTION_PM_10_RATIO = ENVIRONMENT + ":airPollutionPm10Ratio"
NOISE_POLLUTION_RATIO = ENVIRONMENT + ":noisePollutionRatio"
POLLUTION_RATIO = ENVIRONMENT + ":pollutionRatio"
WATER_SUPPLY_RATIO = ENVIRONMENT + ":waterSupplyRatio"

# Labels for aggregated parameters
AGGR_PARAMS_LABELS = {
    AIR_POLLUTION_RATIO: "Air Pollution Ratio",
    NOISE_POLLUTION_RATIO: "Noise Pollution Ratio",
    POLLUTION_RATIO: "Pollution Ratio",
    WATER_SUPPLY_RATIO: "Water Supply Ratio",
}

# Units for aggregated parameters
AGGR_PARAMS_UNITS = {
    AIR_POLLUTION_RATIO: ParamUnits.KG_PER_CAPITA,
    NOISE_POLLUTION_RATIO: ParamUnits.PERCENT,
    POLLUTION_RATIO: ParamUnits.PERCENT,
    WATER_SUPPLY_RATIO: ParamUnits.PERCENT,
}

# List of aggregated parameters
AGGR_PARAMS = list(AGGR_PARAMS_LABELS.keys())

# Reversed states for aggregated parameters
# (True = higher values are worse, False = higher values are better)
AGGR_REVERSED_STATES = {
    AIR_POLLUTION_RATIO: True,
    NOISE_POLLUTION_RATIO: True,
    POLLUTION_RATIO: True,
    WATER_SUPPLY_RATIO: False,
}

# Labels for individual parameters
IND_PARAMS_LABELS = {
    AIR_POLLUTION_NH_3_RATIO: "Air Pollution (NH3) Ratio",
    AIR_POLLUTION_CH_4_RATIO: "Air Pollution (CH4) Ratio",
    AIR_POLLUTION_CO_RATIO: "Air Pollution (CO) Ratio",
    AIR_POLLUTION_NMVOC_RATIO: "Air Pollution (NMVOC) Ratio",
    AIR_POLLUTION_NOX_RATIO: "Air Pollution (NOX) Ratio",
    AIR_POLLUTION_PM_2_5_RATIO: "Air Pollution (PM2.5) Ratio",
    AIR_POLLUTION_PM_10_RATIO: "Air Pollution (PM10) Ratio",
    NOISE_POLLUTION_RATIO: "Noise Pollution Ratio",
    POLLUTION_RATIO: "Pollution Ratio",
    WATER_SUPPLY_RATIO: "Water Supply Ratio",
}

# Units for individual parameters
IND_PARAMS_UNITS = {
    AIR_POLLUTION_NH_3_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_CH_4_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_CO_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_NMVOC_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_NOX_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_PM_2_5_RATIO: ParamUnits.KG_PER_CAPITA,
    AIR_POLLUTION_PM_10_RATIO: ParamUnits.KG_PER_CAPITA,
    NOISE_POLLUTION_RATIO: ParamUnits.PERCENT,
    POLLUTION_RATIO: ParamUnits.PERCENT,
    WATER_SUPPLY_RATIO: ParamUnits.PERCENT,
}

# List of individual parameters
IND_PARAMS = list(IND_PARAMS_LABELS.keys())

# Reversed states for individual parameters
IND_REVERSED_STATES = {
    AIR_POLLUTION_NH_3_RATIO: True,
    AIR_POLLUTION_CH_4_RATIO: True,
    AIR_POLLUTION_CO_RATIO: True,
    AIR_POLLUTION_NMVOC_RATIO: True,
    AIR_POLLUTION_NOX_RATIO: True,
    AIR_POLLUTION_PM_2_5_RATIO: True,
    AIR_POLLUTION_PM_10_RATIO: True,
    NOISE_POLLUTION_RATIO: True,
    POLLUTION_RATIO: True,
    WATER_SUPPLY_RATIO: False,
}


__all__ = [
    'ENVIRONMENT',
    'AIR_POLLUTION_RATIO',
    'AIR_POLLUTION_NH_3_RATIO',
    'AIR_POLLUTION_CH_4_RATIO',
    'AIR_POLLUTION_CO_RATIO',
    'AIR_POLLUTION_NMVOC_RATIO',
    'AIR_POLLUTION_NOX_RATIO',
    'AIR_POLLUTION_PM_2_5_RATIO',
    'AIR_POLLUTION_PM_10_RATIO',
    'NOISE_POLLUTION_RATIO',
    'POLLUTION_RATIO',
    'WATER_SUPPLY_RATIO',
    'AGGR_PARAMS_LABELS',
    'AGGR_PARAMS_UNITS',
    'AGGR_PARAMS',
    'AGGR_REVERSED_STATES',
    'IND_PARAMS_LABELS',
    'IND_PARAMS_UNITS',
    'IND_PARAMS',
    'IND_REVERSED_STATES',
]
