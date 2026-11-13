from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.overall_experience.overall_experience_aggr_params import *
from framework.stats.dimensions.overall_experience.overall_experience_params import *
from framework.stats.dimensions.overall_experience.overall_experience_paths import *


class OverallExperienceStats:
    # Intermediate data
    init_happiness_always_ratio = Initializer.init_consolidated_map(
        HAPPINESS_ALWAYS_RATIO_PARAMS, HAPPINESS_RATIO_PATH
    )
    init_happiness_most_of_the_time_ratio = Initializer.init_consolidated_map(
        HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS, HAPPINESS_RATIO_PATH
    )
    init_high_satisfaction_ratio = Initializer.init_consolidated_map(
        HIGH_SATISFACTION_RATIO_PARAMS, HIGH_SATISFACTION_RATIO_PATH
    )

    # Prepared indicators
    happiness_always_ratio = Preparation.prepare_data(init_happiness_always_ratio)
    happiness_most_of_the_time_ratio = Preparation.prepare_data(
        init_happiness_most_of_the_time_ratio
    )

    # Aggregate the proportion of population who declared they are happy
    # always or most of the time
    happiness_ratio = StatsUtils.calculate_sum([
        happiness_always_ratio,
        happiness_most_of_the_time_ratio,
    ])
    high_satisfaction_ratio = Preparation.prepare_data(init_high_satisfaction_ratio)

    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict([
        (HAPPINESS_ALWAYS_RATIO, Preparation.filter_map(init_happiness_always_ratio)),
        (HAPPINESS_MOST_TIME_RATIO, Preparation.filter_map(init_happiness_most_of_the_time_ratio)),
        (HIGH_SATISFACTION_RATIO, Preparation.filter_map(init_high_satisfaction_ratio)),
    ])

    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        HAPPINESS_RATIO: happiness_ratio,
        HIGH_SATISFACTION_RATIO: high_satisfaction_ratio,
    }

    base_indicators: dict[str, dict[str, int | float | None]] = {
        HAPPINESS_ALWAYS_RATIO: happiness_always_ratio,
        HAPPINESS_MOST_TIME_RATIO: happiness_most_of_the_time_ratio,
        HIGH_SATISFACTION_RATIO: high_satisfaction_ratio,
    }

    @staticmethod
    def generate_aggr_stats(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
    ) -> dict[str, int | float | None]:
        return StatsUtils.generate_stats(
            aggr_list,
            country_codes,
            start_year,
            end_year,
            OVERALL_EXPERIENCE,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            OverallExperienceStats.aggr_indicators,
        )

    @staticmethod
    def generate_base_stats(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
    ) -> dict[str, int | float | None]:
        return StatsUtils.generate_stats(
            aggr_list,
            country_codes,
            start_year,
            end_year,
            OVERALL_EXPERIENCE,
            IND_PARAMS,
            IND_REVERSED_STATES,
            OverallExperienceStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(
            args: list[str],
            series_type: str,
            direction: str,
    ) -> None:
        print_chart_data(
            args,
            OverallExperienceStats.aggr_indicators,
            OVERALL_EXPERIENCE,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        print_data_availability(
            OverallExperienceStats.raw_indicators,
            OVERALL_EXPERIENCE,
            target_year,
            ind_status,
        )
