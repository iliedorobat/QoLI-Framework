from framework.common.param_names import ParamNames
from framework.common.param_values import HAPPINESS_LEVELS, SATISFACTION_LEVELS, SATISFACTION_TYPES
from framework.fetching.fetch_utils import add_params
from framework.stats.auxiliary.auxiliary_params import get_satisfaction_params

LEVELS = [
    HAPPINESS_LEVELS.ALWAYS,
    HAPPINESS_LEVELS.MOST,
]


def _get_happiness_params(happiness_levels: str | list[str]) -> dict[str, list[str]]:
    if isinstance(happiness_levels, str):
        happiness_levels = [happiness_levels]

    params = {
        ParamNames.AGE: ["Y_GE16"],
        ParamNames.FREQ: ["A"],
        ParamNames.ISCED_11: ["TOTAL"],
        ParamNames.SEX: ["T"],
        ParamNames.UNIT: ["PC"],
    }
    add_params(params, ParamNames.FREQUENCY, happiness_levels)
    return params


HAPPINESS_RATIO_PARAMS = _get_happiness_params(LEVELS)
HAPPINESS_ALWAYS_RATIO_PARAMS = _get_happiness_params(HAPPINESS_LEVELS.ALWAYS)
HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS = _get_happiness_params(HAPPINESS_LEVELS.MOST)

HIGH_SATISFACTION_RATIO_PARAMS = get_satisfaction_params(
    SATISFACTION_LEVELS.HIGH,
    SATISFACTION_TYPES.MEANING_OF_LIFE,
)


__all__ = [
    "LEVELS",
    "HAPPINESS_RATIO_PARAMS",
    "HAPPINESS_ALWAYS_RATIO_PARAMS",
    "HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS",
    "HIGH_SATISFACTION_RATIO_PARAMS",
]
