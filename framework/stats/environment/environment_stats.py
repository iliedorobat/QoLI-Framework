from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.environment.environment_aggr_params import *
from framework.stats.environment.environment_params import *
from framework.stats.environment.environment_paths import *


class EnvironmentStats:
    # Intermediate data which will be grouped into a single indicator
    init_air_pollution_nh3_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_NH3_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_ch4_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_CH4_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_co_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_CO_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_nmvoc_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_NMVOC_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_nox_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_NOX_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_pm2_5_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_PM_2_5_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )
    init_air_pollution_pm10_ratio = Initializer.init_consolidated_map(
        AIR_POLLUTION_PM_10_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH
    )

    init_noise_pollution_ratio = Initializer.init_consolidated_map(
        NOISE_POLLUTION_RATIO_PARAMS, NOISE_POLLUTION_RATIO_PATH
    )
    init_pollution_ratio = Initializer.init_consolidated_map(
        POLLUTION_RATIO_PARAMS, POLLUTION_RATIO_PATH
    )
    init_water_supply_ratio = Initializer.init_consolidated_map(
        WATER_SUPPLY_RATIO_PARAMS, WATER_SUPPLY_RATIO_PATH
    )

    # Intermediate data used to calculate air_pollution_ratio
    air_pollution_nh3_ratio = Preparation.prepare_data(init_air_pollution_nh3_ratio)
    air_pollution_ch4_ratio = Preparation.prepare_data(init_air_pollution_ch4_ratio)
    air_pollution_co_ratio = Preparation.prepare_data(init_air_pollution_co_ratio)
    air_pollution_nmvoc_ratio = Preparation.prepare_data(init_air_pollution_nmvoc_ratio)
    air_pollution_nox_ratio = Preparation.prepare_data(init_air_pollution_nox_ratio)
    air_pollution_pm2_5_ratio = Preparation.prepare_data(init_air_pollution_pm2_5_ratio)
    air_pollution_pm10_ratio = Preparation.prepare_data(init_air_pollution_pm10_ratio)

    # Aggregate the "Pollution Ratios" into a single ratio
    air_pollution_ratio = StatsUtils.calculate_geometric_mean(
        [
            air_pollution_ch4_ratio,
            air_pollution_co_ratio,
            air_pollution_nmvoc_ratio,
            air_pollution_nh3_ratio,
            air_pollution_nox_ratio,
            air_pollution_pm2_5_ratio,
            air_pollution_pm10_ratio,
        ]
    )

    noise_pollution_ratio = Preparation.prepare_data(init_noise_pollution_ratio)
    pollution_ratio = Preparation.prepare_data(init_pollution_ratio)
    water_supply_ratio = Preparation.prepare_data(init_water_supply_ratio)

    # Raw indicators (unfiltered)
    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict(
        [
            (AIR_POLLUTION_NH_3_RATIO, Preparation.filter_map(init_air_pollution_nh3_ratio)),
            (AIR_POLLUTION_CH_4_RATIO, Preparation.filter_map(init_air_pollution_ch4_ratio)),
            (AIR_POLLUTION_CO_RATIO, Preparation.filter_map(init_air_pollution_co_ratio)),
            (AIR_POLLUTION_NMVOC_RATIO, Preparation.filter_map(init_air_pollution_nmvoc_ratio)),
            (AIR_POLLUTION_NOX_RATIO, Preparation.filter_map(init_air_pollution_nox_ratio)),
            (AIR_POLLUTION_PM_2_5_RATIO, Preparation.filter_map(init_air_pollution_pm2_5_ratio)),
            (AIR_POLLUTION_PM_10_RATIO, Preparation.filter_map(init_air_pollution_pm10_ratio)),
            (NOISE_POLLUTION_RATIO, Preparation.filter_map(init_noise_pollution_ratio)),
            (POLLUTION_RATIO, Preparation.filter_map(init_pollution_ratio)),
            (WATER_SUPPLY_RATIO, Preparation.filter_map(init_water_supply_ratio)),
        ]
    )

    # Aggregation indicators
    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        AIR_POLLUTION_RATIO: air_pollution_ratio,
        NOISE_POLLUTION_RATIO: noise_pollution_ratio,
        POLLUTION_RATIO: pollution_ratio,
        WATER_SUPPLY_RATIO: water_supply_ratio,
    }

    # Base indicators
    base_indicators: dict[str, dict[str, int | float | None]] = {
        AIR_POLLUTION_NH_3_RATIO: air_pollution_nh3_ratio,
        AIR_POLLUTION_CH_4_RATIO: air_pollution_ch4_ratio,
        AIR_POLLUTION_CO_RATIO: air_pollution_co_ratio,
        AIR_POLLUTION_NMVOC_RATIO: air_pollution_nmvoc_ratio,
        AIR_POLLUTION_NOX_RATIO: air_pollution_nox_ratio,
        AIR_POLLUTION_PM_2_5_RATIO: air_pollution_pm2_5_ratio,
        AIR_POLLUTION_PM_10_RATIO: air_pollution_pm10_ratio,
        NOISE_POLLUTION_RATIO: noise_pollution_ratio,
        POLLUTION_RATIO: pollution_ratio,
        WATER_SUPPLY_RATIO: water_supply_ratio,
    }

    @staticmethod
    def generate_aggr_stats(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
    ) -> dict[str, int | float | None]:
        """Generate aggregation statistics"""
        return StatsUtils.generate_stats(
            aggr_list,
            country_codes,
            start_year,
            end_year,
            ENVIRONMENT,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            EnvironmentStats.aggr_indicators,
        )

    @staticmethod
    def generate_base_stats(
            aggr_list: list[str],
            country_codes: list[str],
            start_year: int,
            end_year: int,
    ) -> dict[str, int | float | None]:
        """Generate base statistics"""
        return StatsUtils.generate_stats(
            aggr_list,
            country_codes,
            start_year,
            end_year,
            ENVIRONMENT,
            IND_PARAMS,
            IND_REVERSED_STATES,
            EnvironmentStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(args: list[str], series_type: str, direction: str) -> None:
        """Print aggregation indicators"""
        print_chart_data(
            args,
            EnvironmentStats.aggr_indicators,
            ENVIRONMENT,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        """Print data availability for the environment dimension"""
        print_data_availability(
            EnvironmentStats.raw_indicators,
            ENVIRONMENT,
            target_year,
            ind_status,
        )
