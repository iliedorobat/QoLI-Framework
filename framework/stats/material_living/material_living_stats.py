from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.material_living.material_living_aggr_params import *
from framework.stats.material_living.material_living_params import *
from framework.stats.material_living.material_living_paths import *


class MaterialLivingStats:
    # Intermediate data
    init_dwelling_issues_ratio = Initializer.init_consolidated_map(
        DWELLING_ISSUES_RATIO_PARAMS, DWELLING_ISSUES_RATIO_PATH
    )
    init_end_meet_inability_d_ratio = Initializer.init_consolidated_map(
        END_MEET_INABILITY_D_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH
    )
    init_end_meet_inability_gd_ratio = Initializer.init_consolidated_map(
        END_MEET_INABILITY_GD_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH
    )
    init_financial_satisfaction_ratio = Initializer.init_consolidated_map(
        FINANCIAL_SATISFACTION_PARAMS, FINANCIAL_SATISFACTION_PATH
    )
    init_gdp_per_capita_pps_ratio = Initializer.init_consolidated_map(
        GDP_PER_CAPITA_PPS_RATIO_PARAMS, GDP_PER_CAPITA_PPS_RATIO_PATH
    )
    init_high_income_ratio = Initializer.init_consolidated_map(
        HIGH_INCOME_RATIO_PARAMS, HIGH_INCOME_RATIO_PATH
    )
    init_income_quintile_ratio = Initializer.init_consolidated_map(
        INCOME_QUINTILE_RATIO_PARAMS, INCOME_QUINTILE_RATIO_PATH
    )
    init_lack_of_baths_ratio = Initializer.init_consolidated_map(
        LACK_OF_BATHS_RATIO_PARAMS, LACK_OF_BATHS_RATIO_PATH
    )
    init_low_work_intensity_ratio = Initializer.init_consolidated_map(
        LOW_WORK_INTENSITY_RATIO_PARAMS, LOW_WORK_INTENSITY_RATIO_PATH
    )
    init_material_deprivation_ratio = Initializer.init_consolidated_map(
        MATERIAL_DEPRIVATION_RATIO_PARAMS, MATERIAL_DEPRIVATION_RATIO_PATH
    )
    init_median_income_pps = Initializer.init_consolidated_map(
        MEDIAN_INCOME_PPS_PARAMS, MEDIAN_INCOME_PPS_PATH
    )
    init_over_occupied_ratio = Initializer.init_consolidated_map(
        OVER_OCCUPIED_RATIO_PARAMS, OVER_OCCUPIED_RATIO_PATH
    )
    init_poverty_risk_ratio = Initializer.init_consolidated_map(
        POVERTY_RISK_RATIO_PARAMS, POVERTY_RISK_RATIO_PATH
    )
    init_under_occupied_ratio = Initializer.init_consolidated_map(
        UNDER_OCCUPIED_RATIO_PARAMS, UNDER_OCCUPIED_RATIO_PATH
    )

    # Prepared indicators
    median_income_pps = Preparation.prepare_data(init_median_income_pps)

    end_meet_inability_d_ratio = Preparation.prepare_data(init_end_meet_inability_d_ratio)
    end_meet_inability_gd_ratio = Preparation.prepare_data(init_end_meet_inability_gd_ratio)

    dwelling_issues_ratio = Preparation.prepare_data(init_dwelling_issues_ratio)
    end_meet_inability_ratio = StatsUtils.calculate_sum([
        end_meet_inability_d_ratio,
        end_meet_inability_gd_ratio,
    ])
    financial_satisfaction_ratio = Preparation.prepare_data(init_financial_satisfaction_ratio)
    gdp_per_capita_pps_ratio = Preparation.prepare_data(init_gdp_per_capita_pps_ratio)
    high_income_ratio = Preparation.prepare_data(init_high_income_ratio)
    income_quintile_ratio = Preparation.prepare_data(init_income_quintile_ratio)
    lack_of_baths_ratio = Preparation.prepare_data(init_lack_of_baths_ratio)
    low_work_intensity_ratio = Preparation.prepare_data(init_low_work_intensity_ratio)
    material_deprivation_ratio = Preparation.prepare_data(init_material_deprivation_ratio)
    median_income_pps_ratio = Preparation.prepare_pps_ratio(median_income_pps)
    over_occupied_ratio = Preparation.prepare_data(init_over_occupied_ratio)
    poverty_risk_ratio = Preparation.prepare_data(init_poverty_risk_ratio)
    under_occupied_ratio = Preparation.prepare_data(init_under_occupied_ratio)

    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict([
        (DWELLING_ISSUES_RATIO, Preparation.filter_map(init_dwelling_issues_ratio)),
        (END_MEET_INABILITY_D_RATIO, Preparation.filter_map(init_end_meet_inability_d_ratio)),
        (END_MEET_INABILITY_GD_RATIO, Preparation.filter_map(init_end_meet_inability_gd_ratio)),
        (FINANCIAL_SATISFACTION_RATIO, Preparation.filter_map(init_financial_satisfaction_ratio)),
        (GDP_PER_CAPITA_PPS_RATIO, Preparation.filter_map(init_gdp_per_capita_pps_ratio)),
        (HIGH_INCOME_RATIO, Preparation.filter_map(init_high_income_ratio)),
        (INCOME_QUINTILE_RATIO, Preparation.filter_map(init_income_quintile_ratio)),
        (LACK_OF_BATHS_RATIO, Preparation.filter_map(init_lack_of_baths_ratio)),
        (LOW_WORK_INTENSITY_RATIO, Preparation.filter_map(init_low_work_intensity_ratio)),
        (MATERIAL_DEPRIVATION_RATIO, Preparation.filter_map(init_material_deprivation_ratio)),
        (MEDIAN_INCOME_PPS_RATIO, Preparation.filter_map(init_median_income_pps)),
        (OVER_OCCUPIED_RATIO, Preparation.filter_map(init_over_occupied_ratio)),
        (POVERTY_RISK_RATIO, Preparation.filter_map(init_poverty_risk_ratio)),
        (UNDER_OCCUPIED_RATIO, Preparation.filter_map(init_under_occupied_ratio)),
    ])

    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        DWELLING_ISSUES_RATIO: dwelling_issues_ratio,
        END_MEET_INABILITY_RATIO: end_meet_inability_ratio,
        FINANCIAL_SATISFACTION_RATIO: financial_satisfaction_ratio,
        GDP_PER_CAPITA_PPS_RATIO: gdp_per_capita_pps_ratio,
        HIGH_INCOME_RATIO: high_income_ratio,
        INCOME_QUINTILE_RATIO: income_quintile_ratio,
        LACK_OF_BATHS_RATIO: lack_of_baths_ratio,
        LOW_WORK_INTENSITY_RATIO: low_work_intensity_ratio,
        MATERIAL_DEPRIVATION_RATIO: material_deprivation_ratio,
        MEDIAN_INCOME_PPS_RATIO: median_income_pps_ratio,
        OVER_OCCUPIED_RATIO: over_occupied_ratio,
        POVERTY_RISK_RATIO: poverty_risk_ratio,
        UNDER_OCCUPIED_RATIO: under_occupied_ratio,
    }

    base_indicators: dict[str, dict[str, int | float | None]] = {
        DWELLING_ISSUES_RATIO: dwelling_issues_ratio,
        END_MEET_INABILITY_D_RATIO: end_meet_inability_d_ratio,
        END_MEET_INABILITY_GD_RATIO: end_meet_inability_gd_ratio,
        FINANCIAL_SATISFACTION_RATIO: financial_satisfaction_ratio,
        GDP_PER_CAPITA_PPS_RATIO: gdp_per_capita_pps_ratio,
        HIGH_INCOME_RATIO: high_income_ratio,
        INCOME_QUINTILE_RATIO: income_quintile_ratio,
        LACK_OF_BATHS_RATIO: lack_of_baths_ratio,
        LOW_WORK_INTENSITY_RATIO: low_work_intensity_ratio,
        MATERIAL_DEPRIVATION_RATIO: material_deprivation_ratio,
        MEDIAN_INCOME_PPS_RATIO: median_income_pps_ratio,
        OVER_OCCUPIED_RATIO: over_occupied_ratio,
        POVERTY_RISK_RATIO: poverty_risk_ratio,
        UNDER_OCCUPIED_RATIO: under_occupied_ratio,
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
            LIVING_CONDITIONS,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            MaterialLivingStats.aggr_indicators,
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
            LIVING_CONDITIONS,
            IND_PARAMS,
            IND_REVERSED_STATES,
            MaterialLivingStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(
            args: list[str],
            series_type: str,
            direction: str,
    ) -> None:
        print_chart_data(
            args,
            MaterialLivingStats.aggr_indicators,
            LIVING_CONDITIONS,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        print_data_availability(
            MaterialLivingStats.raw_indicators,
            LIVING_CONDITIONS,
            target_year,
            ind_status,
        )
