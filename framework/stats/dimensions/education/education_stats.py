from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_consolidation import MergeUtils
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.education.education_aggr_params import *
from framework.stats.dimensions.education.education_params import *
from framework.stats.dimensions.education.education_paths import *


class EducationStats:
    # Intermediate data which will be grouped into a single indicator
    consolidated_pupils_ratio_2012 = MergeUtils.consolidate_map(
        PUPILS_RATIO_2012_PARAMS, PUPILS_RATIO_2012_PATH
    )
    consolidated_pupils_ratio_2013 = MergeUtils.consolidate_map(
        PUPILS_RATIO_2013_PARAMS, PUPILS_RATIO_2013_PATH
    )
    pupils_ratio_list = [consolidated_pupils_ratio_2012, consolidated_pupils_ratio_2013]

    # Initialize data for all indicators
    init_digital_skills_ratio = Initializer.init_consolidated_map(
        DIGITAL_SKILLS_PARAMS, DIGITAL_SKILLS_RATIO_PATH
    )
    init_dropout_ratio = Initializer.init_consolidated_map(
        DROPOUT_RATIO_PARAMS, DROPOUT_RATIO_PATH
    )
    init_early_education_ratio = Initializer.init_consolidated_map(
        EARLY_EDUCATION_RATIO_PARAMS, EARLY_EDUCATION_RATIO_PATH
    )
    init_education_ratio = Initializer.init_consolidated_map(
        EDUCATION_RATIO_PARAMS, EDUCATION_RATIO_PATH
    )
    init_inactive_young_ratio = Initializer.init_consolidated_map(
        INACTIVE_YOUNG_RATIO_PARAMS, INACTIVE_YOUNG_RATIO_PATH
    )
    init_no_known_foreign_lang_ratio = Initializer.init_consolidated_map(
        NO_FOREIGN_LANG_KNOWN_RATIO_PARAMS, NO_FOREIGN_LANG_KNOWN_RATIO_PATH
    )
    init_pupils_ratio = Initializer.init_consolidated_maps(pupils_ratio_list)
    init_training_last_month_ratio = Initializer.init_consolidated_map(
        TRAINING_LAST_MONTH_RATIO_PARAMS, TRAINING_LAST_MONTH_RATIO_PATH
    )
    init_training_last_year_ratio = Initializer.init_consolidated_map(
        TRAINING_LAST_YEAR_RATIO_PARAMS, TRAINING_LAST_YEAR_RATIO_PATH
    )

    # Prepare data for all indicators
    digital_skills_ratio = Preparation.prepare_data(init_digital_skills_ratio)
    dropout_ratio = Preparation.prepare_data(init_dropout_ratio)
    early_education_ratio = Preparation.prepare_data(init_early_education_ratio)
    education_ratio = Preparation.prepare_data(init_education_ratio)
    inactive_young_ratio = Preparation.prepare_data(init_inactive_young_ratio)
    no_known_foreign_lang_ratio = Preparation.prepare_data(
        init_no_known_foreign_lang_ratio
    )
    pupils_ratio = Preparation.prepare_data(init_pupils_ratio)
    training_last_month_ratio = Preparation.prepare_data(init_training_last_month_ratio)
    training_last_year_ratio = Preparation.prepare_data(init_training_last_year_ratio)

    # Raw indicators (unfiltered)
    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict(
        [
            (DIGITAL_SKILLS_RATIO, Preparation.filter_map(init_digital_skills_ratio)),
            (DROPOUT_RATIO, Preparation.filter_map(init_dropout_ratio)),
            (EARLY_EDU_RATIO, Preparation.filter_map(init_early_education_ratio)),
            (EDUCATION_RATIO, Preparation.filter_map(init_education_ratio)),
            (INACTIVE_YOUNG_RATIO, Preparation.filter_map(init_inactive_young_ratio)),
            (
                NO_FOREIGN_LANG_KNOWN_RATIO,
                Preparation.filter_map(init_no_known_foreign_lang_ratio),
            ),
            (PUPILS_RATIO, Preparation.filter_map(init_pupils_ratio)),
            (
                TRAINING_LAST_MONTH_RATIO,
                Preparation.filter_map(init_training_last_month_ratio),
            ),
            (TRAINING_LAST_YEAR_RATIO, Preparation.filter_map(init_training_last_year_ratio)),
        ]
    )

    # Aggregation indicators
    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        DIGITAL_SKILLS_RATIO: digital_skills_ratio,
        DROPOUT_RATIO: dropout_ratio,
        EARLY_EDU_RATIO: early_education_ratio,
        EDUCATION_RATIO: education_ratio,
        INACTIVE_YOUNG_RATIO: inactive_young_ratio,
        NO_FOREIGN_LANG_KNOWN_RATIO: no_known_foreign_lang_ratio,
        PUPILS_RATIO: pupils_ratio,
        TRAINING_LAST_MONTH_RATIO: training_last_month_ratio,
        TRAINING_LAST_YEAR_RATIO: training_last_year_ratio,
    }

    # Base indicators
    base_indicators: dict[str, dict[str, int | float | None]] = {
        DIGITAL_SKILLS_RATIO: digital_skills_ratio,
        DROPOUT_RATIO: dropout_ratio,
        EARLY_EDU_RATIO: early_education_ratio,
        EDUCATION_RATIO: education_ratio,
        INACTIVE_YOUNG_RATIO: inactive_young_ratio,
        NO_FOREIGN_LANG_KNOWN_RATIO: no_known_foreign_lang_ratio,
        PUPILS_RATIO: pupils_ratio,
        TRAINING_LAST_MONTH_RATIO: training_last_month_ratio,
        TRAINING_LAST_YEAR_RATIO: training_last_year_ratio,
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
            EDUCATION,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            EducationStats.aggr_indicators,
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
            EDUCATION,
            IND_PARAMS,
            IND_REVERSED_STATES,
            EducationStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(args: list[str], series_type: str, direction: str) -> None:
        """Print aggregation indicators"""
        print_chart_data(
            args, EducationStats.aggr_indicators, EDUCATION, EU28_MEMBERS, series_type, direction
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        """Print data availability for the education dimension"""
        print_data_availability(EducationStats.raw_indicators, EDUCATION, target_year, ind_status)
