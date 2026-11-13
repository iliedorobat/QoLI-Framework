from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS, KEY_SEPARATOR
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.processing.data_consolidation import MergeUtils
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.auxiliary.auxiliary_stats import AuxiliaryStats
from framework.stats.dimensions.main_activity.main_activity_aggr_params import *
from framework.stats.dimensions.main_activity.main_activity_params import *
from framework.stats.dimensions.main_activity.main_activity_paths import *

MAX_LEGAL_WORKING_HOURS = 12 * 7


def _prepare_avg_remaining_work_hours(avg_work_hours: dict[str, int | float | None]) -> dict[str, int | float | None]:
    prepared_map = {}

    for year in range(MIN_YEAR, MAX_YEAR + 1):
        for code in EU28_MEMBERS:
            key = f"{code}{KEY_SEPARATOR}{year}"
            prepared_map[key] = MAX_LEGAL_WORKING_HOURS - avg_work_hours[key]

    return prepared_map


def _prepare_working_flexibility_ratio(
        flexibility_full_ratio: dict[str, int | float | None],
        flexibility_restrictive_ratio: dict[str, int | float | None],
        flexibility_total_ratio: dict[str, int | float | None],
) -> dict[str, int | float | None]:
    prepared_map = {}

    for year in range(MIN_YEAR, MAX_YEAR + 1):
        for code in EU28_MEMBERS:
            key = f"{code}{KEY_SEPARATOR}{year}"

            value_full = flexibility_full_ratio[key]
            value_restrictive = flexibility_restrictive_ratio[key]
            value_total = flexibility_total_ratio[key]

            prepared_map[key] = (value_full + value_restrictive) / value_total * 100

    return prepared_map


class MainActivityStats:
    # Intermediate data which will be grouped into a single indicator
    avg_work_hours_2007 = MergeUtils.consolidate_map(
        AVG_WORKING_HOURS_2007_PARAMS, AVG_WORKING_HOURS_2007_PATH
    )
    avg_work_hours_2008 = MergeUtils.consolidate_map(
        AVG_WORKING_HOURS_2008_PARAMS, AVG_WORKING_HOURS_2008_PATH
    )
    avg_work_hours_list = [
        avg_work_hours_2007,
        avg_work_hours_2008,
    ]

    # Intermediate data which will be grouped into a single indicator
    init_flexibility_full_ratio = Initializer.init_consolidated_map(
        WORKING_FLEXIBILITY_FULL_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH
    )
    init_flexibility_restrictive_ratio = Initializer.init_consolidated_map(
        WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH
    )
    init_flexibility_total_ratio = Initializer.init_consolidated_map(
        WORKING_FLEXIBILITY_TOTAL_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH
    )

    init_avg_work_hours_list = Initializer.init_consolidated_maps(avg_work_hours_list)
    init_employment_ratio = Initializer.init_consolidated_map(
        EMPLOYMENT_RATIO_PARAMS, EMPLOYMENT_RATIO_PATH
    )
    init_inactive_population_ratio = Initializer.init_consolidated_map(
        INACTIVE_POPULATION_RATIO_PARAMS, INACTIVE_POPULATION_RATIO_PATH
    )
    init_involuntary_part_time_ratio = Initializer.init_consolidated_map(
        INVOLUNTARY_PART_TIME_RATIO_PARAMS, INVOLUNTARY_PART_TIME_RATIO_PATH
    )
    init_job_satisfaction = Initializer.init_consolidated_map(
        JOB_SATISFACTION_PARAMS, JOB_SATISFACTION_PATH
    )
    init_long_term_unemployment_ratio = Initializer.init_consolidated_map(
        LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS, LONG_TERM_UNEMPLOYMENT_RATIO_PATH
    )
    init_low_wage_earnings_ratio = Initializer.init_consolidated_map(
        LOW_WAGE_EARNINGS_RATIO_PARAMS, LOW_WAGE_EARNINGS_RATIO_PATH
    )
    init_low_work_intensity_ratio = Initializer.init_consolidated_map(
        LOW_WORK_INTENSITY_RATIO_PARAMS, LOW_WORK_INTENSITY_RATIO_PATH
    )
    init_researchers = Initializer.init_consolidated_map(
        RESEARCHERS_PARAMS, RESEARCHERS_PATH
    )
    init_temporary_employment_ratio = Initializer.init_consolidated_map(
        TEMPORARY_EMPLOYMENT_RATIO_PARAMS, TEMPORARY_EMPLOYMENT_RATIO_PATH
    )
    init_unemployment_ratio = Initializer.init_consolidated_map(
        UNEMPLOYMENT_RATIO_PARAMS, UNEMPLOYMENT_RATIO_PATH
    )
    init_working_nights_ratio = Initializer.init_consolidated_map(
        WORKING_NIGHTS_RATIO_PARAMS, WORKING_NIGHTS_RATIO_PATH
    )

    # Prepared indicators
    avg_work_hours = Preparation.prepare_data(init_avg_work_hours_list)

    flexibility_full_ratio = Preparation.prepare_data(init_flexibility_full_ratio)
    flexibility_restrictive_ratio = Preparation.prepare_data(init_flexibility_restrictive_ratio)
    flexibility_total_ratio = Preparation.prepare_data(init_flexibility_total_ratio)

    avg_remaining_work_hours = _prepare_avg_remaining_work_hours(avg_work_hours)

    employment_ratio = Preparation.prepare_data(init_employment_ratio)
    inactive_population_ratio = Preparation.prepare_data(init_inactive_population_ratio)
    involuntary_part_time_ratio = Preparation.prepare_data(init_involuntary_part_time_ratio)
    job_satisfaction = Preparation.prepare_data(init_job_satisfaction)
    long_term_unemployment_ratio = Preparation.prepare_data(init_long_term_unemployment_ratio)
    low_wage_earnings_ratio = Preparation.prepare_data(init_low_wage_earnings_ratio)
    low_work_intensity_ratio = Preparation.prepare_data(init_low_work_intensity_ratio)
    researchers_ratio = Preparation.prepare_per_ten_thousand_inhabitants(
        AuxiliaryStats.population, init_researchers
    )
    temporary_employment_ratio = Preparation.prepare_data(init_temporary_employment_ratio)
    unemployment_ratio = Preparation.prepare_data(init_unemployment_ratio)
    working_flexibility_ratio = _prepare_working_flexibility_ratio(
        flexibility_full_ratio,
        flexibility_restrictive_ratio,
        flexibility_total_ratio,
    )
    working_nights_ratio = Preparation.prepare_data(init_working_nights_ratio)

    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict([
        (AVG_WORKING_HOURS, Preparation.filter_map(init_avg_work_hours_list)),
        (EMPLOYMENT_RATIO, Preparation.filter_map(init_employment_ratio)),
        (INACTIVE_POPULATION_RATIO, Preparation.filter_map(init_inactive_population_ratio)),
        (INVOLUNTARY_PART_TIME_RATIO, Preparation.filter_map(init_involuntary_part_time_ratio)),
        (JOB_SATISFACTION, Preparation.filter_map(init_job_satisfaction)),
        (LONG_TERM_UNEMPLOYMENT_RATIO, Preparation.filter_map(init_long_term_unemployment_ratio)),
        (LOW_WAGE_EARNERS_RATIO, Preparation.filter_map(init_low_wage_earnings_ratio)),
        (LOW_WORK_INTENSITY_RATIO, Preparation.filter_map(init_low_work_intensity_ratio)),
        (RESEARCHERS_RATIO, Preparation.filter_map(init_researchers)),
        (TEMPORARY_EMPLOYMENT_RATIO, Preparation.filter_map(init_temporary_employment_ratio)),
        (UNEMPLOYMENT_RATIO, Preparation.filter_map(init_unemployment_ratio)),
        (WORKING_FLEXIBILITY_FULL_RATIO, Preparation.filter_map(init_flexibility_full_ratio)),
        (WORKING_FLEXIBILITY_RESTRICTIVE_RATIO, Preparation.filter_map(init_flexibility_restrictive_ratio)),
        (WORKING_FLEXIBILITY_TOTAL_RATIO, Preparation.filter_map(init_flexibility_total_ratio)),
        (WORKING_NIGHTS_RATIO, Preparation.filter_map(init_working_nights_ratio)),
    ])

    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        AVG_REMAINING_WORKING_HOURS: avg_remaining_work_hours,
        EMPLOYMENT_RATIO: employment_ratio,
        INACTIVE_POPULATION_RATIO: inactive_population_ratio,
        INVOLUNTARY_PART_TIME_RATIO: involuntary_part_time_ratio,
        JOB_SATISFACTION: job_satisfaction,
        LONG_TERM_UNEMPLOYMENT_RATIO: long_term_unemployment_ratio,
        LOW_WAGE_EARNERS_RATIO: low_wage_earnings_ratio,
        LOW_WORK_INTENSITY_RATIO: low_work_intensity_ratio,
        RESEARCHERS_RATIO: researchers_ratio,
        TEMPORARY_EMPLOYMENT_RATIO: temporary_employment_ratio,
        UNEMPLOYMENT_RATIO: unemployment_ratio,
        WORKING_FLEXIBILITY_RATIO: working_flexibility_ratio,
        WORKING_NIGHTS_RATIO: working_nights_ratio,
    }

    base_indicators: dict[str, dict[str, int | float | None]] = {
        AVG_WORKING_HOURS: avg_work_hours,
        EMPLOYMENT_RATIO: employment_ratio,
        INACTIVE_POPULATION_RATIO: inactive_population_ratio,
        INVOLUNTARY_PART_TIME_RATIO: involuntary_part_time_ratio,
        JOB_SATISFACTION: job_satisfaction,
        LONG_TERM_UNEMPLOYMENT_RATIO: long_term_unemployment_ratio,
        LOW_WAGE_EARNERS_RATIO: low_wage_earnings_ratio,
        LOW_WORK_INTENSITY_RATIO: low_work_intensity_ratio,
        RESEARCHERS_RATIO: researchers_ratio,
        TEMPORARY_EMPLOYMENT_RATIO: temporary_employment_ratio,
        UNEMPLOYMENT_RATIO: unemployment_ratio,
        WORKING_FLEXIBILITY_FULL_RATIO: flexibility_full_ratio,
        WORKING_FLEXIBILITY_RESTRICTIVE_RATIO: flexibility_restrictive_ratio,
        WORKING_NIGHTS_RATIO: working_nights_ratio,
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
            MAIN_ACTIVITY,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            MainActivityStats.aggr_indicators,
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
            MAIN_ACTIVITY,
            IND_PARAMS,
            IND_REVERSED_STATES,
            MainActivityStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(
            args: list[str],
            series_type: str,
            direction: str,
    ) -> None:
        print_chart_data(
            args,
            MainActivityStats.aggr_indicators,
            MAIN_ACTIVITY,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        print_data_availability(
            MainActivityStats.raw_indicators,
            MAIN_ACTIVITY,
            target_year,
            ind_status,
        )
