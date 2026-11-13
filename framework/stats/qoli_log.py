from framework.common.constants import DIRECTION_ROW, SERIES_TYPE_COUNTRY
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.stats.dimensions.education.education_stats import EducationStats
from framework.stats.dimensions.environment.environment_stats import EnvironmentStats
from framework.stats.dimensions.gov_rights.gov_rights_stats import GovRightsStats
from framework.stats.dimensions.health.health_stats import HealthStats
from framework.stats.dimensions.leisure_interaction.leisure_interaction_stats import LeisureInteractStats
from framework.stats.dimensions.main_activity.main_activity_stats import MainActivityStats
from framework.stats.dimensions.material_living.material_living_stats import MaterialLivingStats
from framework.stats.dimensions.overall_experience.overall_experience_stats import OverallExperienceStats
from framework.stats.dimensions.qoli_paths import QOLI_FILE_NAME
from framework.stats.dimensions.qoli_stats import QoLIStats
from framework.stats.dimensions.safety.safety_stats import SafetyStats
from framework.stats.qoli_csv_stats import QoLICsvStats


def log_dimensions(
        dimensions: list[str],
        series_type: str=SERIES_TYPE_COUNTRY,
        direction: str=DIRECTION_ROW
):
    """Log the QoLI and the QoLI dimensions values"""
    data_by_countries = QoLIStats.prepare_extended_dimensions(dimensions, None, MIN_YEAR, MAX_YEAR)

    QoLICsvStats.print_dimensions(
        [f"--dimension={QOLI_FILE_NAME}"],
        series_type,
        data_by_countries,
        direction,
    )


def log_aggr_indicators(
        dimensions: list[str],
        ind_list: list[str],
        series_type: str=SERIES_TYPE_COUNTRY,
        direction: str=DIRECTION_ROW
):
    """Log aggregated indicators"""
    dim_list = [f"--dimension={item}" for item in dimensions]
    aggr_list = dim_list + [f"--indicator={indicator}" for indicator in ind_list]

    EducationStats.print_aggr_indicators(aggr_list, series_type, direction)
    EnvironmentStats.print_aggr_indicators(aggr_list, series_type, direction)
    GovRightsStats.print_aggr_indicators(aggr_list, series_type, direction)
    HealthStats.print_aggr_indicators(aggr_list, series_type, direction)
    LeisureInteractStats.print_aggr_indicators(aggr_list, series_type, direction)
    MainActivityStats.print_aggr_indicators(aggr_list, series_type, direction)
    MaterialLivingStats.print_aggr_indicators(aggr_list, series_type, direction)
    OverallExperienceStats.print_aggr_indicators(aggr_list, series_type, direction)
    SafetyStats.print_aggr_indicators(aggr_list, series_type, direction)


def log_data_availability(status_year: int = MAX_YEAR, show_ind_level: bool = False):
    """Print the data inconsistencies (available dataset and expected dataset)"""
    EducationStats.print_data_availability(status_year, show_ind_level)
    EnvironmentStats.print_data_availability(status_year, show_ind_level)
    GovRightsStats.print_data_availability(status_year, show_ind_level)
    HealthStats.print_data_availability(status_year, show_ind_level)
    LeisureInteractStats.print_data_availability(status_year, show_ind_level)
    MainActivityStats.print_data_availability(status_year, show_ind_level)
    MaterialLivingStats.print_data_availability(status_year, show_ind_level)
    OverallExperienceStats.print_data_availability(status_year, show_ind_level)
    SafetyStats.print_data_availability(status_year, show_ind_level)
