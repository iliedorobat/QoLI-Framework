from dataclasses import dataclass

from framework.stats.dimensions.education.education_aggr_params import EDUCATION, \
    AGGR_REVERSED_STATES as EDU_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as EDU_AGGR_PARAMS_UNITS
from framework.stats.dimensions.environment.environment_aggr_params import ENVIRONMENT, \
    AGGR_REVERSED_STATES as ENV_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as ENV_AGGR_PARAMS_UNITS
from framework.stats.dimensions.gov_rights.gov_rights_aggr_params import GOVERNANCE, \
    AGGR_REVERSED_STATES as GOV_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as GOV_AGGR_PARAMS_UNITS
from framework.stats.dimensions.health.health_aggr_params import HEALTH, \
    AGGR_REVERSED_STATES as HEALTH_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as HEALTH_AGGR_PARAMS_UNITS
from framework.stats.dimensions.leisure_interaction.leisure_interaction_aggr_params import LEISURE_INTERACT, \
    AGGR_REVERSED_STATES as LEISURE_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as LEISURE_AGGR_PARAMS_UNITS
from framework.stats.dimensions.main_activity.main_activity_aggr_params import MAIN_ACTIVITY, \
    AGGR_REVERSED_STATES as MAIN_ACTIVITY_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as MAIN_ACTIVITY_AGGR_PARAMS_UNITS
from framework.stats.dimensions.material_living.material_living_aggr_params import LIVING_CONDITIONS, \
    AGGR_REVERSED_STATES as LIVING_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as LIVING_AGGR_PARAMS_UNITS
from framework.stats.dimensions.overall_experience.overall_experience_aggr_params import OVERALL_EXPERIENCE, \
    AGGR_REVERSED_STATES as OVERALL_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as OVERALL_AGGR_PARAMS_UNITS
from framework.stats.dimensions.safety.safety_aggr_params import SAFETY, \
    AGGR_REVERSED_STATES as SAFETY_AGGR_REVERSED_STATES, AGGR_PARAMS_UNITS as SAFETY_AGGR_PARAMS_UNITS

_AGGR_PARAMS_UNITS = {
    EDUCATION: EDU_AGGR_PARAMS_UNITS,
    ENVIRONMENT: ENV_AGGR_PARAMS_UNITS,
    GOVERNANCE: GOV_AGGR_PARAMS_UNITS,
    HEALTH: HEALTH_AGGR_PARAMS_UNITS,
    LEISURE_INTERACT: LEISURE_AGGR_PARAMS_UNITS,
    MAIN_ACTIVITY: MAIN_ACTIVITY_AGGR_PARAMS_UNITS,
    LIVING_CONDITIONS: LIVING_AGGR_PARAMS_UNITS,
    OVERALL_EXPERIENCE: OVERALL_AGGR_PARAMS_UNITS,
    SAFETY: SAFETY_AGGR_PARAMS_UNITS,
}


_AGGR_REVERSED_STATES = {
    EDUCATION: EDU_AGGR_REVERSED_STATES,
    ENVIRONMENT: ENV_AGGR_REVERSED_STATES,
    GOVERNANCE: GOV_AGGR_REVERSED_STATES,
    HEALTH: HEALTH_AGGR_REVERSED_STATES,
    LEISURE_INTERACT: LEISURE_AGGR_REVERSED_STATES,
    MAIN_ACTIVITY: MAIN_ACTIVITY_AGGR_REVERSED_STATES,
    LIVING_CONDITIONS: LIVING_AGGR_REVERSED_STATES,
    OVERALL_EXPERIENCE: OVERALL_AGGR_REVERSED_STATES,
    SAFETY: SAFETY_AGGR_REVERSED_STATES,
}


@dataclass
class AggrIndicatorSchema:
    filename: str
    label: str
    checked: bool = True
    negativeState: bool = False
    units: str = ""

    def __post_init__(self):
        self.negativeState = self.is_reversed(self.filename)
        self.units = self.get_units(self.filename)

    @staticmethod
    def is_reversed(ind_key: str) -> bool:
        for prefix, params in _AGGR_REVERSED_STATES.items():
            if ind_key.startswith(prefix):
                return params.get(ind_key, False)
        return False

    @staticmethod
    def get_units(ind_key: str) -> str:
        for prefix, params in _AGGR_PARAMS_UNITS.items():
            if ind_key.startswith(prefix):
                return params.get(ind_key, "")
        return ""
