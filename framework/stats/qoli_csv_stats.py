from collections.abc import Mapping, Sequence
from numbers import Number

from framework.common.constants import SERIES_TYPE_COUNTRY, SERIES_TYPE_REGION, EU28_MEMBERS, EU28_REGIONS
from framework.stats.common.csv_stats_utils import write_chart_data
from framework.stats.common.print_utils import print_chart_data_for_entries
from framework.stats.education.education_paths import EDUCATION_FILE_NAME
from framework.stats.education.education_stats import EducationStats
from framework.stats.environment.environment_paths import ENVIRONMENT_FILE_NAME
from framework.stats.gov_rights.gov_rights_paths import GOVERNANCE_FILE_NAME
from framework.stats.health.health_paths import HEALTH_FILE_NAME
from framework.stats.leisure_interaction.leisure_interaction_paths import LEISURE_INTERACT_FILE_NAME
from framework.stats.main_activity.main_activity_paths import MAIN_ACTIVITY_FILE_NAME
from framework.stats.material_living.material_living_paths import LIVING_CONDITIONS_FILE_NAME
from framework.stats.overall_experience.overall_experience_paths import OVERALL_EXPERIENCE_FILE_NAME
from framework.stats.qoli_aggr_params import *
from framework.stats.qoli_paths import QOLI_FILE_NAME
from framework.stats.safety.safety_paths import SAFETY_FILE_NAME


class QoLICsvStats:

    @staticmethod
    def print_dimensions(
            args: Sequence[str],
            series_type: str,
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
    ) -> None:
        if f"--dimension={QOLI_FILE_NAME}" in args:
            if series_type == SERIES_TYPE_COUNTRY:
                QoLICsvStats._print_countries_data(data_by_countries, direction)
            elif series_type == SERIES_TYPE_REGION:
                QoLICsvStats._print_regions_data(data_by_countries, direction)

    @staticmethod
    def write_dimensions(
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLICsvStats._write_data_by_countries(data_by_countries, direction, calculate_indicators, start_year, end_year)
        QoLICsvStats._write_data_by_regions(data_by_countries, direction, calculate_indicators, start_year, end_year)

    @staticmethod
    def _print_countries_data(
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
    ) -> None:
        print(f"\n----------- {SERIES_TYPE_COUNTRY} DATA -----------\n")
        QoLICsvStats._print_data(EU28_MEMBERS, SERIES_TYPE_COUNTRY, data_by_countries, direction)
        print("--------------------------------------")

    @staticmethod
    def _print_regions_data(
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
    ) -> None:
        print(f"\n----------- {SERIES_TYPE_REGION} DATA -----------\n")
        QoLICsvStats._print_data(EU28_REGIONS, SERIES_TYPE_REGION, data_by_countries, direction)
        print("--------------------------------------")

    @staticmethod
    def _write_data_by_countries(
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLICsvStats._write_data(EU28_MEMBERS, SERIES_TYPE_COUNTRY, data_by_countries, direction, calculate_indicators, start_year, end_year)

    @staticmethod
    def _write_data_by_regions(
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        QoLICsvStats._write_data(EU28_REGIONS, SERIES_TYPE_REGION, data_by_countries, direction, calculate_indicators, start_year, end_year)

    @staticmethod
    def _print_data(
            members_list: Sequence[str],
            series_type: str,
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
    ) -> None:
        print_chart_data_for_entries(data_by_countries[QOLI], members_list, series_type, QOLI_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[EDUCATION], members_list, series_type, EDUCATION_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[ENVIRONMENT], members_list, series_type, ENVIRONMENT_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[GOVERNANCE], members_list, series_type, GOVERNANCE_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[HEALTH], members_list, series_type, HEALTH_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[LEISURE_INTERACT], members_list, series_type, LEISURE_INTERACT_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[LIVING_CONDITIONS], members_list, series_type, LIVING_CONDITIONS_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[MAIN_ACTIVITY], members_list, series_type, MAIN_ACTIVITY_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[OVERALL_EXPERIENCE], members_list, series_type, OVERALL_EXPERIENCE_FILE_NAME, direction)
        print_chart_data_for_entries(data_by_countries[SAFETY], members_list, series_type, SAFETY_FILE_NAME, direction)

    @staticmethod
    def _write_data(
            members_list: Sequence[str],
            series_type: str,
            data_by_countries: Mapping[str, Mapping[str, Number]],
            direction: str,
            calculate_indicators: bool,
            start_year: int,
            end_year: int,
    ) -> None:
        write_chart_data(data_by_countries[QOLI], members_list, series_type, QOLI_FILE_NAME, direction, None, calculate_indicators, start_year, end_year)
        write_chart_data(data_by_countries[EDUCATION], members_list, series_type, EDUCATION_FILE_NAME, direction, EducationStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # TODO:
        # write_chart_data(data_by_countries[ENVIRONMENT], members_list, series_type, ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[GOVERNANCE], members_list, series_type, GOVERNANCE_FILE_NAME, direction, GovRightsStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[HEALTH], members_list, series_type, HEALTH_FILE_NAME, direction, HealthStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[LEISURE_INTERACT], members_list, series_type, LEISURE_INTERACT_FILE_NAME, direction, LeisureInteractStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[LIVING_CONDITIONS], members_list, series_type, LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[MAIN_ACTIVITY], members_list, series_type, MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[OVERALL_EXPERIENCE], members_list, series_type, OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.aggr_indicators, calculate_indicators, start_year, end_year)
        # write_chart_data(data_by_countries[SAFETY], members_list, series_type, SAFETY_FILE_NAME, direction, SafetyStats.aggr_indicators, calculate_indicators, start_year, end_year)


__all__ = [
    "QoLICsvStats"
]
