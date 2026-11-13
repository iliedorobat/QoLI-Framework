from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS, KEY_SEPARATOR
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.leisure_interaction.leisure_interaction_aggr_params import *
from framework.stats.dimensions.leisure_interaction.leisure_interaction_params import *
from framework.stats.dimensions.leisure_interaction.leisure_interaction_paths import *


def _calculate_sum(
        map1: dict[str, int | float | None],
        map2: dict[str, int | float | None],
) -> dict[str, int | float | None]:
    """Calculate the sum of two maps for every EU member and year."""
    consolidated_map = {}

    for year in range(MIN_YEAR, MAX_YEAR + 1):
        for code in EU28_MEMBERS:
            key = f"{code}{KEY_SEPARATOR}{year}"
            value = map1[key] + map2[key]
            consolidated_map[key] = value

    return consolidated_map


class LeisureInteractStats:
    # Intermediate data which will be grouped into a single indicator
    init_area_satisfaction_ratio = Initializer.init_consolidated_map(
        AREA_SATISFACTION_RATIO_PARAMS, AREA_SATISFACTION_RATIO_PATH
    )
    init_asking_ratio = Initializer.init_consolidated_map(
        ASKING_RATIO_PARAMS, ASKING_RATIO_PATH
    )
    init_discussion_ratio = Initializer.init_consolidated_map(
        DISCUSSION_PARAMS_RATIO, DISCUSSION_RATIO_PATH
    )
    init_formal_voluntary_ratio = Initializer.init_consolidated_map(
        FORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH
    )
    init_frequency_contact_fam_ratio = Initializer.init_consolidated_map(
        INTERACTIONS_FAM_PARAMS, FREQUENCY_CONTACT_RATIO_PATH
    )
    init_frequency_contact_frd_ratio = Initializer.init_consolidated_map(
        INTERACTIONS_FRD_PARAMS, FREQUENCY_CONTACT_RATIO_PATH
    )
    init_getting_together_fam_ratio = Initializer.init_consolidated_map(
        INTERACTIONS_FAM_PARAMS, GETTING_TOGETHER_RATIO_PATH
    )
    init_getting_together_frd_ratio = Initializer.init_consolidated_map(
        INTERACTIONS_FRD_PARAMS, GETTING_TOGETHER_RATIO_PATH
    )
    init_informal_voluntary_ratio = Initializer.init_consolidated_map(
        INFORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH
    )
    init_rel_satisfaction_ratio = Initializer.init_consolidated_map(
        REL_SATISFACTION_RATIO_PARAMS, REL_SATISFACTION_RATIO_PATH
    )
    init_social_activities_ratio = Initializer.init_consolidated_map(
        SOCIAL_ACTIVITIES_RATIO_PARAMS, SOCIAL_ACTIVITIES_RATIO_PATH
    )
    init_time_satisfaction_ratio = Initializer.init_consolidated_map(
        TIME_SATISFACTION_RATIO_PARAMS, TIME_SATISFACTION_RATIO_PATH
    )

    # Intermediate data to be consolidated into a single indicator
    init_np_fin_cin_ratio = Initializer.init_consolidated_map(
        NP_FIN_CIN_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_fin_cult_ratio = Initializer.init_consolidated_map(
        NP_FIN_CULT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_fin_live_ratio = Initializer.init_consolidated_map(
        NP_FIN_LIVE_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_fin_sport_ratio = Initializer.init_consolidated_map(
        NP_FIN_SPORT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )

    # Intermediate data to be consolidated into a single indicator
    init_np_nnb_cin_ratio = Initializer.init_consolidated_map(
        NP_NNB_CIN_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_nnb_cult_ratio = Initializer.init_consolidated_map(
        NP_NNB_CULT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_nnb_live_ratio = Initializer.init_consolidated_map(
        NP_NNB_LIVE_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_nnb_sport_ratio = Initializer.init_consolidated_map(
        NP_NNB_SPORT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH
    )

    # Intermediate data to be consolidated into a single indicator
    init_np_no_interest_formal_ratio = Initializer.init_consolidated_map(
        NP_NO_INTEREST_FORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_no_interest_informal_ratio = Initializer.init_consolidated_map(
        NP_NO_INTEREST_INFORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_time_formal_ratio = Initializer.init_consolidated_map(
        NP_TIME_FORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH
    )
    init_np_time_informal_ratio = Initializer.init_consolidated_map(
        NP_TIME_INFORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH
    )

    # Intermediate data used to calculate social_activities_np_ratio
    np_fin_cin_ratio = Preparation.prepare_data(init_np_fin_cin_ratio)
    np_fin_cult_ratio = Preparation.prepare_data(init_np_fin_cult_ratio)
    np_fin_live_ratio = Preparation.prepare_data(init_np_fin_live_ratio)
    np_fin_sport_ratio = Preparation.prepare_data(init_np_fin_sport_ratio)

    # Intermediate data used to calculate social_activities_np_ratio
    np_nnb_cin_ratio = Preparation.prepare_data(init_np_nnb_cin_ratio)
    np_nnb_cult_ratio = Preparation.prepare_data(init_np_nnb_cult_ratio)
    np_nnb_live_ratio = Preparation.prepare_data(init_np_nnb_live_ratio)
    np_nnb_sport_ratio = Preparation.prepare_data(init_np_nnb_sport_ratio)

    # Intermediate data used to calculate voluntary_activities_np_ratio
    np_no_interest_formal_ratio = Preparation.prepare_data(init_np_no_interest_formal_ratio)
    np_no_interest_informal_ratio = Preparation.prepare_data(init_np_no_interest_informal_ratio)
    np_time_formal_ratio = Preparation.prepare_data(init_np_time_formal_ratio)
    np_time_informal_ratio = Preparation.prepare_data(init_np_time_informal_ratio)

    # Intermediate data used to calculate non_participation_ratio
    np_formal_ratio = _calculate_sum(np_no_interest_formal_ratio, np_time_formal_ratio)
    np_informal_ratio = _calculate_sum(np_no_interest_informal_ratio, np_time_informal_ratio)
    np_cin_ratio = _calculate_sum(np_fin_cin_ratio, np_nnb_cin_ratio)
    np_cult_ratio = _calculate_sum(np_fin_cult_ratio, np_nnb_cult_ratio)
    np_live_ratio = _calculate_sum(np_fin_live_ratio, np_nnb_live_ratio)
    np_sport_ratio = _calculate_sum(np_fin_sport_ratio, np_nnb_sport_ratio)

    area_satisfaction_ratio = Preparation.prepare_data(init_area_satisfaction_ratio)
    asking_ratio = Preparation.prepare_data(init_asking_ratio)
    discussion_ratio = Preparation.prepare_data(init_discussion_ratio)
    formal_voluntary_ratio = Preparation.prepare_data(init_formal_voluntary_ratio)
    frequency_contact_fam_ratio = Preparation.prepare_data(init_frequency_contact_fam_ratio)
    frequency_contact_frd_ratio = Preparation.prepare_data(init_frequency_contact_frd_ratio)
    getting_together_fam_ratio = Preparation.prepare_data(init_getting_together_fam_ratio)
    getting_together_frd_ratio = Preparation.prepare_data(init_getting_together_frd_ratio)
    informal_voluntary_ratio = Preparation.prepare_data(init_informal_voluntary_ratio)
    rel_satisfaction_ratio = Preparation.prepare_data(init_rel_satisfaction_ratio)
    social_activities_ratio = Preparation.prepare_data(init_social_activities_ratio)
    time_satisfaction_ratio = Preparation.prepare_data(init_time_satisfaction_ratio)

    # Aggregate the frequency contact ratios into a single ratio
    frequency_contact_ratio = StatsUtils.calculate_geometric_mean(
        [
            frequency_contact_fam_ratio,
            frequency_contact_frd_ratio,
        ]
    )

    # Aggregate the getting together ratios into a single ratio
    getting_together_ratio = StatsUtils.calculate_geometric_mean(
        [
            getting_together_fam_ratio,
            getting_together_frd_ratio,
        ]
    )

    # Aggregate the non-participation ratios into a single ratio
    non_participation_ratio = StatsUtils.calculate_geometric_mean(
        [
            np_formal_ratio,
            np_informal_ratio,
            np_cin_ratio,
            np_cult_ratio,
            np_live_ratio,
            np_sport_ratio,
        ]
    )

    # Aggregate the participation ratios into a single ratio
    participation_ratio = StatsUtils.calculate_geometric_mean(
        [
            informal_voluntary_ratio,
            formal_voluntary_ratio,
            social_activities_ratio,
        ]
    )

    # Raw indicators (unfiltered)
    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict(
        [
            (AREA_SATISFACTION_RATIO, Preparation.filter_map(init_area_satisfaction_ratio)),
            (ASKING_RATIO, Preparation.filter_map(init_asking_ratio)),
            (DISCUSSION_RATIO, Preparation.filter_map(init_discussion_ratio)),
            (FORMAL_VOLUNTARY_ACTIVITIES_RATIO, Preparation.filter_map(init_formal_voluntary_ratio)),
            (FREQUENCY_CONTACT_FAM_RATIO, Preparation.filter_map(init_frequency_contact_fam_ratio)),
            (FREQUENCY_CONTACT_FRD_RATIO, Preparation.filter_map(init_frequency_contact_frd_ratio)),
            (GETTING_TOGETHER_FAM_RATIO, Preparation.filter_map(init_getting_together_fam_ratio)),
            (GETTING_TOGETHER_FRD_RATIO, Preparation.filter_map(init_getting_together_frd_ratio)),
            (INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, Preparation.filter_map(init_informal_voluntary_ratio)),
            (RELATIONSHIPS_SATISFACTION_RATIO, Preparation.filter_map(init_rel_satisfaction_ratio)),
            (SOCIAL_ACTIVITIES_RATIO, Preparation.filter_map(init_social_activities_ratio)),
            (TIME_SPENT_SATISFACTION, Preparation.filter_map(init_time_satisfaction_ratio)),
            (NP_FIN_CINEMA_RATIO, Preparation.filter_map(init_np_fin_cin_ratio)),
            (NP_FIN_CULTURE_RATIO, Preparation.filter_map(init_np_fin_cult_ratio)),
            (NP_FIN_LIVE_RATIO, Preparation.filter_map(init_np_fin_live_ratio)),
            (NP_FIN_SPORT_RATIO, Preparation.filter_map(init_np_fin_sport_ratio)),
            (NP_NNB_CINEMA_RATIO, Preparation.filter_map(init_np_nnb_cin_ratio)),
            (NP_NNB_CULTURE_RATIO, Preparation.filter_map(init_np_nnb_cult_ratio)),
            (NP_NNB_LIVE_RATIO, Preparation.filter_map(init_np_nnb_live_ratio)),
            (NP_NNB_SPORT_RATIO, Preparation.filter_map(init_np_nnb_sport_ratio)),
            (NP_NO_INTEREST_FORMAL_RATIO, Preparation.filter_map(init_np_no_interest_formal_ratio)),
            (NP_NO_INTEREST_INFORMAL_RATIO, Preparation.filter_map(init_np_no_interest_informal_ratio)),
            (NP_TIME_FORMAL_RATIO, Preparation.filter_map(init_np_time_formal_ratio)),
            (NP_TIME_INFORMAL_RATIO, Preparation.filter_map(init_np_time_informal_ratio)),
        ]
    )

    # Aggregation indicators
    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        AREA_SATISFACTION_RATIO: area_satisfaction_ratio,
        ASKING_RATIO: asking_ratio,
        DISCUSSION_RATIO: discussion_ratio,
        FREQUENCY_CONTACT_RATIO: frequency_contact_ratio,
        GETTING_TOGETHER_RATIO: getting_together_ratio,
        NP_RATIO: non_participation_ratio,
        PARTICIPATION_RATIO: participation_ratio,
        RELATIONSHIPS_SATISFACTION_RATIO: rel_satisfaction_ratio,
        TIME_SPENT_SATISFACTION: time_satisfaction_ratio,
    }

    # Base indicators
    base_indicators: dict[str, dict[str, int | float | None]] = {
        AREA_SATISFACTION_RATIO: area_satisfaction_ratio,
        ASKING_RATIO: asking_ratio,
        DISCUSSION_RATIO: discussion_ratio,
        FORMAL_VOLUNTARY_ACTIVITIES_RATIO: formal_voluntary_ratio,
        FREQUENCY_CONTACT_FAM_RATIO: frequency_contact_fam_ratio,
        FREQUENCY_CONTACT_FRD_RATIO: frequency_contact_frd_ratio,
        GETTING_TOGETHER_FAM_RATIO: getting_together_fam_ratio,
        GETTING_TOGETHER_FRD_RATIO: getting_together_frd_ratio,
        INFORMAL_VOLUNTARY_ACTIVITIES_RATIO: informal_voluntary_ratio,
        RELATIONSHIPS_SATISFACTION_RATIO: rel_satisfaction_ratio,
        SOCIAL_ACTIVITIES_RATIO: social_activities_ratio,
        TIME_SPENT_SATISFACTION: time_satisfaction_ratio,
        NP_FIN_CINEMA_RATIO: np_fin_cin_ratio,
        NP_FIN_CULTURE_RATIO: np_fin_cult_ratio,
        NP_FIN_LIVE_RATIO: np_fin_live_ratio,
        NP_FIN_SPORT_RATIO: np_fin_sport_ratio,
        NP_NNB_CINEMA_RATIO: np_nnb_cin_ratio,
        NP_NNB_CULTURE_RATIO: np_nnb_cult_ratio,
        NP_NNB_LIVE_RATIO: np_nnb_live_ratio,
        NP_NNB_SPORT_RATIO: np_nnb_sport_ratio,
        NP_NO_INTEREST_FORMAL_RATIO: np_no_interest_formal_ratio,
        NP_NO_INTEREST_INFORMAL_RATIO: np_no_interest_informal_ratio,
        NP_TIME_FORMAL_RATIO: np_time_formal_ratio,
        NP_TIME_INFORMAL_RATIO: np_time_informal_ratio,
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
            LEISURE_INTERACT,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            LeisureInteractStats.aggr_indicators,
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
            LEISURE_INTERACT,
            IND_PARAMS,
            IND_REVERSED_STATES,
            LeisureInteractStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(
            args: list[str],
            series_type: str,
            direction: str,
    ) -> None:
        """Print aggregation indicators"""
        print_chart_data(
            args,
            LeisureInteractStats.aggr_indicators,
            LEISURE_INTERACT,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        """Print data availability for the leisure and interaction dimension"""
        print_data_availability(
            LeisureInteractStats.raw_indicators,
            LEISURE_INTERACT,
            target_year,
            ind_status,
        )
