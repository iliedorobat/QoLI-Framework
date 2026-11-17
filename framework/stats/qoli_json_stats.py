from framework.common.constants import (
    EU28_MEMBERS,
    EU28_REGIONS,
    SERIES_TYPE_COUNTRY,
    SERIES_TYPE_REGION,
)
from framework.stats.common.json_stats_utils import write_json_data
from framework.stats.dimensions.education.education_paths import EDUCATION_FILE_NAME
from framework.stats.dimensions.education.education_stats import EducationStats
from framework.stats.dimensions.environment.environment_paths import ENVIRONMENT_FILE_NAME
from framework.stats.dimensions.environment.environment_stats import EnvironmentStats
from framework.stats.dimensions.gov_rights.gov_rights_paths import GOVERNANCE_FILE_NAME
from framework.stats.dimensions.gov_rights.gov_rights_stats import GovRightsStats
from framework.stats.dimensions.health.health_paths import HEALTH_FILE_NAME
from framework.stats.dimensions.health.health_stats import HealthStats
from framework.stats.dimensions.leisure_interaction.leisure_interaction_paths import LEISURE_INTERACT_FILE_NAME
from framework.stats.dimensions.leisure_interaction.leisure_interaction_stats import LeisureInteractStats
from framework.stats.dimensions.main_activity.main_activity_paths import MAIN_ACTIVITY_FILE_NAME
from framework.stats.dimensions.main_activity.main_activity_stats import MainActivityStats
from framework.stats.dimensions.material_living.material_living_paths import LIVING_CONDITIONS_FILE_NAME
from framework.stats.dimensions.material_living.material_living_stats import MaterialLivingStats
from framework.stats.dimensions.overall_experience.overall_experience_paths import OVERALL_EXPERIENCE_FILE_NAME
from framework.stats.dimensions.overall_experience.overall_experience_stats import OverallExperienceStats
from framework.stats.dimensions.qoli_aggr_params import *
from framework.stats.dimensions.qoli_paths import QOLI_FILE_NAME
from framework.stats.dimensions.safety.safety_paths import SAFETY_FILE_NAME
from framework.stats.dimensions.safety.safety_stats import SafetyStats


class QoLIJsonStats:
    @staticmethod
    def write_dimensions(
            data_by_countries: dict[str, dict[str, int | float | None]],
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLIJsonStats.write_data_by_countries(
            data_by_countries, calculate_indicators, start_year, end_year
        )
        QoLIJsonStats.write_data_by_regions(
            data_by_countries, calculate_indicators, start_year, end_year
        )

    @staticmethod
    def write_data_by_countries(
            data_by_countries: dict[str, dict[str, int | float | None]],
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLIJsonStats.write_data(
            EU28_MEMBERS,
            SERIES_TYPE_COUNTRY,
            data_by_countries,
            calculate_indicators,
            start_year,
            end_year,
        )

    @staticmethod
    def write_data_by_regions(
            data_by_countries: dict[str, dict[str, int | float | None]],
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLIJsonStats.write_data(
            EU28_REGIONS,
            SERIES_TYPE_REGION,
            data_by_countries,
            calculate_indicators,
            start_year,
            end_year,
        )

    @staticmethod
    def write_data(
            members_list: list[str],
            series_type: str,
            data_by_countries: dict[str, dict[str, int | float | None]],
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        write_json_data(
            data_by_countries[QOLI],
            members_list,
            series_type,
            QOLI_FILE_NAME,
            None,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[EDUCATION],
            members_list,
            series_type,
            EDUCATION_FILE_NAME,
            EducationStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[ENVIRONMENT],
            members_list,
            series_type,
            ENVIRONMENT_FILE_NAME,
            EnvironmentStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[GOVERNANCE],
            members_list,
            series_type,
            GOVERNANCE_FILE_NAME,
            GovRightsStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[HEALTH],
            members_list,
            series_type,
            HEALTH_FILE_NAME,
            HealthStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[LEISURE_INTERACT],
            members_list,
            series_type,
            LEISURE_INTERACT_FILE_NAME,
            LeisureInteractStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[LIVING_CONDITIONS],
            members_list,
            series_type,
            LIVING_CONDITIONS_FILE_NAME,
            MaterialLivingStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[MAIN_ACTIVITY],
            members_list,
            series_type,
            MAIN_ACTIVITY_FILE_NAME,
            MainActivityStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[OVERALL_EXPERIENCE],
            members_list,
            series_type,
            OVERALL_EXPERIENCE_FILE_NAME,
            OverallExperienceStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )
        write_json_data(
            data_by_countries[SAFETY],
            members_list,
            series_type,
            SAFETY_FILE_NAME,
            SafetyStats.aggr_indicators,
            calculate_indicators,
            start_year,
            end_year,
        )


__all__ = [
    "QoLIJsonStats",
]
