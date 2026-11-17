from dataclasses import dataclass, field

from framework.stats.dimensions.qoli_stats import QoLIStats, extract_time_range

from framework.stats.dimensions.education.education_aggr_params import EDUCATION, \
    IND_REVERSED_STATES as EDU_IND_REVERSED_STATES, IND_PARAMS_UNITS as EDU_IND_PARAMS_UNITS
from framework.stats.dimensions.environment.environment_aggr_params import ENVIRONMENT, \
    IND_REVERSED_STATES as ENV_IND_REVERSED_STATES, IND_PARAMS_UNITS as ENV_IND_PARAMS_UNITS
from framework.stats.dimensions.gov_rights.gov_rights_aggr_params import GOVERNANCE, \
    IND_REVERSED_STATES as GOV_IND_REVERSED_STATES, IND_PARAMS_UNITS as GOV_IND_PARAMS_UNITS
from framework.stats.dimensions.health.health_aggr_params import HEALTH, \
    IND_REVERSED_STATES as HEALTH_IND_REVERSED_STATES, IND_PARAMS_UNITS as HEALTH_IND_PARAMS_UNITS
from framework.stats.dimensions.leisure_interaction.leisure_interaction_aggr_params import LEISURE_INTERACT, \
    IND_REVERSED_STATES as LEISURE_IND_REVERSED_STATES, IND_PARAMS_UNITS as LEISURE_IND_PARAMS_UNITS
from framework.stats.dimensions.main_activity.main_activity_aggr_params import MAIN_ACTIVITY, \
    IND_REVERSED_STATES as MAIN_ACTIVITY_IND_REVERSED_STATES, IND_PARAMS_UNITS as MAIN_ACTIVITY_IND_PARAMS_UNITS
from framework.stats.dimensions.material_living.material_living_aggr_params import LIVING_CONDITIONS, \
    IND_REVERSED_STATES as LIVING_IND_REVERSED_STATES, IND_PARAMS_UNITS as LIVING_IND_PARAMS_UNITS
from framework.stats.dimensions.overall_experience.overall_experience_aggr_params import OVERALL_EXPERIENCE, \
    IND_REVERSED_STATES as OVERALL_IND_REVERSED_STATES, IND_PARAMS_UNITS as OVERALL_IND_PARAMS_UNITS
from framework.stats.dimensions.safety.safety_aggr_params import SAFETY, \
    IND_REVERSED_STATES as SAFETY_IND_REVERSED_STATES, IND_PARAMS_UNITS as SAFETY_IND_PARAMS_UNITS

_IND_PARAMS_UNITS = {
    EDUCATION: EDU_IND_PARAMS_UNITS,
    ENVIRONMENT: ENV_IND_PARAMS_UNITS,
    GOVERNANCE: GOV_IND_PARAMS_UNITS,
    HEALTH: HEALTH_IND_PARAMS_UNITS,
    LEISURE_INTERACT: LEISURE_IND_PARAMS_UNITS,
    MAIN_ACTIVITY: MAIN_ACTIVITY_IND_PARAMS_UNITS,
    LIVING_CONDITIONS: LIVING_IND_PARAMS_UNITS,
    OVERALL_EXPERIENCE: OVERALL_IND_PARAMS_UNITS,
    SAFETY: SAFETY_IND_PARAMS_UNITS,
}


_IND_REVERSED_STATES = {
    EDUCATION: EDU_IND_REVERSED_STATES,
    ENVIRONMENT: ENV_IND_REVERSED_STATES,
    GOVERNANCE: GOV_IND_REVERSED_STATES,
    HEALTH: HEALTH_IND_REVERSED_STATES,
    LEISURE_INTERACT: LEISURE_IND_REVERSED_STATES,
    MAIN_ACTIVITY: MAIN_ACTIVITY_IND_REVERSED_STATES,
    LIVING_CONDITIONS: LIVING_IND_REVERSED_STATES,
    OVERALL_EXPERIENCE: OVERALL_IND_REVERSED_STATES,
    SAFETY: SAFETY_IND_REVERSED_STATES,
}


@dataclass
class BaseIndicatorSchema:
    filename: str
    label: str
    negativeState: bool = False
    timeRange: list[int] = field(default_factory=list)
    units: str = ""

    def __post_init__(self):
        self.negativeState = self.is_reversed(self.filename)
        self.timeRange = self.extract_time_range(self.filename)
        self.units = self.get_units(self.filename)

    @staticmethod
    def is_reversed(ind_key: str) -> bool:
        for prefix, params in _IND_REVERSED_STATES.items():
            if ind_key.startswith(prefix):
                return params.get(ind_key, False)
        return False

    @staticmethod
    def get_units(ind_key: str) -> str:
        for prefix, params in _IND_PARAMS_UNITS.items():
            if ind_key.startswith(prefix):
                return params.get(ind_key, "")
        return ""

    @staticmethod
    def extract_time_range(ind_key: str) -> list[int]:
        raw_indicators = QoLIStats.raw_indicators.get(ind_key)
        return extract_time_range(raw_indicators)
