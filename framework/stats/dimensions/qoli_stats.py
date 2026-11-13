from framework.common.constants import EU28_MEMBERS
from framework.processing.map_utils import generate_key, get_entry_year, sort_key
from framework.processing.math_utils import MathUtils
from framework.stats.dimensions.education.education_stats import EducationStats
from framework.stats.dimensions.environment.environment_stats import EnvironmentStats
from framework.stats.dimensions.gov_rights.gov_rights_stats import GovRightsStats
from framework.stats.dimensions.health.health_stats import HealthStats
from framework.stats.dimensions.leisure_interaction.leisure_interaction_stats import LeisureInteractStats
from framework.stats.dimensions.main_activity.main_activity_stats import MainActivityStats
from framework.stats.dimensions.material_living.material_living_stats import MaterialLivingStats
from framework.stats.dimensions.overall_experience.overall_experience_stats import OverallExperienceStats
from framework.stats.dimensions.qoli_aggr_params import *
from framework.stats.dimensions.safety.safety_stats import SafetyStats


def _extract_time_range(entries: dict[str, float | None]) -> list[int]:
    time_range = {
        get_entry_year(key)
        for key, value in entries.items()
        if value is not None
    }
    return sorted(time_range, reverse=True)

def _extract_multiset_time_range(
        entries: dict[str, dict[str, float | None]],
) -> dict[str, list[int]]:
    return {
        key: _extract_time_range(values)
        for key, values in entries.items()
    }


class QoLIStats:
    raw_indicators = {
        **EducationStats.raw_indicators,
        **EnvironmentStats.raw_indicators,
        **GovRightsStats.raw_indicators,
        **GovRightsStats.raw_indicators,
        **HealthStats.raw_indicators,
        **LeisureInteractStats.raw_indicators,
        **MainActivityStats.raw_indicators,
        **MaterialLivingStats.raw_indicators,
        **OverallExperienceStats.raw_indicators,
        **SafetyStats.raw_indicators,
    }

    # TODO: check if raw_indicators_time_range is instantiated
    raw_indicators_time_range = _extract_multiset_time_range(raw_indicators)

    aggr_indicators = {
        **EducationStats.aggr_indicators,
        **EnvironmentStats.aggr_indicators,
        **GovRightsStats.aggr_indicators,
        **HealthStats.aggr_indicators,
        **LeisureInteractStats.aggr_indicators,
        **MainActivityStats.aggr_indicators,
        **MaterialLivingStats.aggr_indicators,
        **OverallExperienceStats.aggr_indicators,
        **SafetyStats.aggr_indicators,
    }

    base_indicators = {
        **EducationStats.base_indicators,
        **EnvironmentStats.base_indicators,
        **GovRightsStats.base_indicators,
        **HealthStats.base_indicators,
        **LeisureInteractStats.base_indicators,
        **MainActivityStats.base_indicators,
        **MaterialLivingStats.base_indicators,
        **OverallExperienceStats.base_indicators,
        **SafetyStats.base_indicators,
    }

    @staticmethod
    def generate_aggr_stats(
            aggrs: list[str] | None,
            country_codes: list[str] | None,
            start_year: int,
            end_year: int,
    ) -> dict[str, float | None]:
        aggr_list = QoLIStats.get_aggr_list(aggrs)
        country_list = QoLIStats.get_country_list(country_codes)

        consolidated_list = {}
        stats = QoLIStats.prepare_dimensions(aggr_list, country_list, start_year, end_year)

        for year in range(start_year, end_year + 1):
            for code in country_list:
                key = generate_key(code, year)
                product = 1.0

                for dimension_name, dimension_values in stats.items():
                    exists = any(
                        indicator_name == dimension_name
                        or indicator_name.startswith(f"{dimension_name}:")
                        for indicator_name in aggr_list
                    )

                    if exists:
                        product *= dimension_values[key]

                value = MathUtils.get_log_value(product)
                consolidated_list[key] = value

        return dict(sorted(consolidated_list.items(), key=sort_key))

    @staticmethod
    def prepare_dimensions(
            aggr_list: list[str],
            country_codes: list[str] | None,
            start_year: int,
            end_year: int,
    ) -> dict[str, dict[str, float | None]]:
        country_list = QoLIStats.get_country_list(country_codes)

        return {
            EDUCATION: EducationStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            ENVIRONMENT: EnvironmentStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            GOVERNANCE: GovRightsStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            HEALTH: HealthStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            LEISURE_INTERACT: LeisureInteractStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            LIVING_CONDITIONS: MaterialLivingStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            MAIN_ACTIVITY: MainActivityStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            OVERALL_EXPERIENCE: OverallExperienceStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
            SAFETY: SafetyStats.generate_aggr_stats(aggr_list, country_list, start_year, end_year),
        }

    @staticmethod
    def prepare_extended_dimensions(
            aggr_list: list[str],
            country_codes: list[str] | None,
            start_year: int,
            end_year: int,
    ) -> dict[str, dict[str, float | None]]:
        data_by_countries = QoLIStats.prepare_dimensions(aggr_list, country_codes, start_year, end_year)
        data_by_countries[QOLI] = QoLIStats.generate_aggr_stats(aggr_list, country_codes, start_year, end_year)
        return data_by_countries

    @staticmethod
    def get_aggr_list(aggrs: list[str] | None) -> list[str]:
        return AGGR_PARAMS if not aggrs else aggrs

    @staticmethod
    def get_country_list(country_codes: list[str] | None) -> list[str]:
        return country_codes if country_codes else EU28_MEMBERS
