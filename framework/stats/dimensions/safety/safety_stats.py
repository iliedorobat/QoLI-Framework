from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS, EU28_MEMBERS_EXTENDED
from framework.fetching.fetch_utils import MIN_YEAR, MAX_YEAR
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.processing.math_utils import MathUtils
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.auxiliary.auxiliary_stats import AuxiliaryStats
from framework.stats.dimensions.safety.safety_aggr_params import *
from framework.stats.dimensions.safety.safety_params import *
from framework.stats.dimensions.safety.safety_paths import *


def _prepare_offences(
        offence: dict[str, int | float | None],
) -> dict[str, int | float | None]:
    consolidated_list = {}

    for year in range(MIN_YEAR, MAX_YEAR + 1):
        uk_value = 0

        for code in EU28_MEMBERS_EXTENDED:
            key = f"{code}_{year}"
            value = offence[key]

            if code in ("UKC-L", "UKM", "UKN"):
                uk_value += value
            else:
                consolidated_list[key] = value

        consolidated_list[f"UK_{year}"] = uk_value

    return consolidated_list


def _prepare_offences_ratio(
        attempted_homicide_offences: dict[str, int | float | None],
        assault_offences: dict[str, int | float | None],
        bribery_offences: dict[str, int | float | None],
        burglary_offences: dict[str, int | float | None],
        burglary_private_offences: dict[str, int | float | None],
        computers_offences: dict[str, int | float | None],
        corruption_offences: dict[str, int | float | None],
        criminal_groups_offences: dict[str, int | float | None],
        fraud_offences: dict[str, int | float | None],
        homicide_offences: dict[str, int | float | None],
        kidnapping_offences: dict[str, int | float | None],
        money_laundering_offences: dict[str, int | float | None],
        narcotics_offences: dict[str, int | float | None],
        rape_offences: dict[str, int | float | None],
        robbery_offences: dict[str, int | float | None],
        sexual_assault_offences: dict[str, int | float | None],
        sexual_exploitation_offences: dict[str, int | float | None],
        sexual_violence_offences: dict[str, int | float | None],
        theft_offences: dict[str, int | float | None],
        theft_vehicle_offences: dict[str, int | float | None],
) -> dict[str, int | float | None]:
    offence_maps = [
        attempted_homicide_offences,
        assault_offences,
        bribery_offences,
        burglary_offences,
        burglary_private_offences,
        computers_offences,
        corruption_offences,
        criminal_groups_offences,
        fraud_offences,
        homicide_offences,
        kidnapping_offences,
        money_laundering_offences,
        narcotics_offences,
        rape_offences,
        robbery_offences,
        sexual_assault_offences,
        sexual_exploitation_offences,
        sexual_violence_offences,
        theft_offences,
        theft_vehicle_offences,
    ]

    consolidated_list = {}

    for year in range(MIN_YEAR, MAX_YEAR + 1):
        uk_sum = 0

        for code in EU28_MEMBERS_EXTENDED:
            key = f"{code}_{year}"
            total = sum(offence[key] for offence in offence_maps)

            if code in ("UKC-L", "UKM", "UKN"):
                uk_sum += total
            else:
                consolidated_list[key] = MathUtils.generate_per_hundred_inhabitants(
                    AuxiliaryStats.population, key, total
                )

        uk_key = f"UK_{year}"
        consolidated_list[uk_key] = MathUtils.generate_per_hundred_inhabitants(
            AuxiliaryStats.population, uk_key, uk_sum
        )

    return consolidated_list


class SafetyStats:
    # Intermediate data
    init_crime_ratio = Initializer.init_consolidated_map(
        CRIME_RATIO_PARAMS, CRIME_RATIO_PATH
    )
    init_non_payment_ratio = Initializer.init_consolidated_map(
        NON_PAYMENT_RATIO_PARAMS, NON_PAYMENT_RATIO_PATH
    )
    init_pension_pps = Initializer.init_consolidated_map(
        PENSION_PPS_PARAMS, PENSION_PPS_PATH
    )
    init_social_protection_pps = Initializer.init_consolidated_map(
        SOCIAL_PROTECTION_PPS_PARAMS, SOCIAL_PROTECTION_PPS_PATH
    )
    init_unexpected_ratio = Initializer.init_consolidated_map(
        UNEXPECTED_RATIO_PARAMS, UNEXPECTED_RATIO_PATH
    )

    # Offence data, including the extended EU country list
    init_assault_offences = Initializer.init_consolidated_map(
        OFFENCES_ASSAULT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_attempted_homicide_offences = Initializer.init_consolidated_map(
        OFFENCES_ATTEMPTED_HOMICIDE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_bribery_offences = Initializer.init_consolidated_map(
        OFFENCES_BRIBERY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_burglary_offences = Initializer.init_consolidated_map(
        OFFENCES_BURGLARY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_burglary_private_offences = Initializer.init_consolidated_map(
        OFFENCES_BURGLARY_PRIVATE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_computers_offences = Initializer.init_consolidated_map(
        OFFENCES_COMPUTERS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_corruption_groups_offences = Initializer.init_consolidated_map(
        OFFENCES_CORRUPTION_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_criminal_groups_offences = Initializer.init_consolidated_map(
        OFFENCES_CRIMINAL_GROUPS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_fraud_groups_offences = Initializer.init_consolidated_map(
        OFFENCES_FRAUD_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_homicide_offences = Initializer.init_consolidated_map(
        OFFENCES_HOMICIDE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_kidnapping_offences = Initializer.init_consolidated_map(
        OFFENCES_KIDNAPPING_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_money_laundering_offences = Initializer.init_consolidated_map(
        OFFENCES_MONEY_LAUNDERING_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_narcotics_offences = Initializer.init_consolidated_map(
        OFFENCES_NARCOTICS_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_rape_offences = Initializer.init_consolidated_map(
        OFFENCES_RAPE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_robbery_offences = Initializer.init_consolidated_map(
        OFFENCES_ROBBERY_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_sexual_violence_offences = Initializer.init_consolidated_map(
        OFFENCES_SEXUAL_VIOLENCE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_sexual_assault_offences = Initializer.init_consolidated_map(
        OFFENCES_SEXUAL_ASSAULT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_sexual_exploitation_offences = Initializer.init_consolidated_map(
        OFFENCES_SEXUAL_EXPLOITATION_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_theft_offences = Initializer.init_consolidated_map(
        OFFENCES_THEFT_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )
    init_theft_vehicle_offences = Initializer.init_consolidated_map(
        OFFENCES_THEFT_VEHICLE_PARAMS, OFFENCES_PATH, EU28_MEMBERS_EXTENDED
    )

    # Prepared indicators
    pension_pps = Preparation.prepare_data(init_pension_pps)
    social_protection_pps = Preparation.prepare_data(init_social_protection_pps)

    # Intermediate data used to calculate total offences
    attempted_homicide_offences = Preparation.prepare_data(
        init_attempted_homicide_offences, EU28_MEMBERS_EXTENDED
    )
    assault_offences = Preparation.prepare_data(
        init_assault_offences, EU28_MEMBERS_EXTENDED
    )
    bribery_offences = Preparation.prepare_data(
        init_bribery_offences, EU28_MEMBERS_EXTENDED
    )
    burglary_offences = Preparation.prepare_data(
        init_burglary_offences, EU28_MEMBERS_EXTENDED
    )
    burglary_private_offences = Preparation.prepare_data(
        init_burglary_private_offences, EU28_MEMBERS_EXTENDED
    )
    computers_offences = Preparation.prepare_data(
        init_computers_offences, EU28_MEMBERS_EXTENDED
    )
    corruption_offences = Preparation.prepare_data(
        init_corruption_groups_offences, EU28_MEMBERS_EXTENDED
    )
    criminal_groups_offences = Preparation.prepare_data(
        init_criminal_groups_offences, EU28_MEMBERS_EXTENDED
    )
    fraud_offences = Preparation.prepare_data(
        init_fraud_groups_offences, EU28_MEMBERS_EXTENDED
    )
    homicide_offences = Preparation.prepare_data(
        init_homicide_offences, EU28_MEMBERS_EXTENDED
    )
    kidnapping_offences = Preparation.prepare_data(
        init_kidnapping_offences, EU28_MEMBERS_EXTENDED
    )
    money_laundering_offences = Preparation.prepare_data(
        init_money_laundering_offences, EU28_MEMBERS_EXTENDED
    )
    narcotics_offences = Preparation.prepare_data(
        init_narcotics_offences, EU28_MEMBERS_EXTENDED
    )
    rape_offences = Preparation.prepare_data(
        init_rape_offences, EU28_MEMBERS_EXTENDED
    )
    robbery_offences = Preparation.prepare_data(
        init_robbery_offences, EU28_MEMBERS_EXTENDED
    )
    sexual_assault_offences = Preparation.prepare_data(
        init_sexual_assault_offences, EU28_MEMBERS_EXTENDED
    )
    sexual_exploitation_offences = Preparation.prepare_data(
        init_sexual_exploitation_offences, EU28_MEMBERS_EXTENDED
    )
    sexual_violence_offences = Preparation.prepare_data(
        init_sexual_violence_offences, EU28_MEMBERS_EXTENDED
    )
    theft_offences = Preparation.prepare_data(
        init_theft_offences, EU28_MEMBERS_EXTENDED
    )
    theft_vehicle_offences = Preparation.prepare_data(
        init_theft_vehicle_offences, EU28_MEMBERS_EXTENDED
    )

    crime_ratio = Preparation.prepare_data(init_crime_ratio)
    non_payment_ratio = Preparation.prepare_data(init_non_payment_ratio)
    pension_pps_ratio = Preparation.prepare_pps_ratio(pension_pps)
    social_protection_pps_ratio = Preparation.prepare_pps_ratio(social_protection_pps)

    total_offences_ratio = _prepare_offences_ratio(
        attempted_homicide_offences,
        assault_offences,
        bribery_offences,
        burglary_offences,
        burglary_private_offences,
        computers_offences,
        corruption_offences,
        criminal_groups_offences,
        fraud_offences,
        homicide_offences,
        kidnapping_offences,
        money_laundering_offences,
        narcotics_offences,
        rape_offences,
        robbery_offences,
        sexual_assault_offences,
        sexual_exploitation_offences,
        sexual_violence_offences,
        theft_offences,
        theft_vehicle_offences,
    )
    unexpected_ratio = Preparation.prepare_data(init_unexpected_ratio)

    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict([
        (CRIME_RATIO, Preparation.filter_map(init_crime_ratio)),
        (NON_PAYMENT_RATIO, Preparation.filter_map(init_non_payment_ratio)),
        (PENSION_PPS_RATIO, Preparation.filter_map(init_pension_pps)),
        (SOCIAL_PROTECTION_PPS_RATIO, Preparation.filter_map(init_social_protection_pps)),
        (UNEXPECTED_RATIO, Preparation.filter_map(init_unexpected_ratio)),
        (OFFENCES_ASSAULT, Preparation.filter_map(init_assault_offences)),
        (OFFENCES_ATTEMPTED_HOMICIDE, Preparation.filter_map(init_attempted_homicide_offences)),
        (OFFENCES_BRIBERY, Preparation.filter_map(init_bribery_offences)),
        (OFFENCES_BURGLARY, Preparation.filter_map(init_burglary_offences)),
        (OFFENCES_BURGLARY_PRIVATE, Preparation.filter_map(init_burglary_private_offences)),
        (OFFENCES_COMPUTERS, Preparation.filter_map(init_computers_offences)),
        (OFFENCES_CRIMINAL_GROUPS, Preparation.filter_map(init_criminal_groups_offences)),
        (OFFENCES_CORRUPTION, Preparation.filter_map(init_corruption_groups_offences)),
        (OFFENCES_FRAUD, Preparation.filter_map(init_fraud_groups_offences)),
        (OFFENCES_HOMICIDE, Preparation.filter_map(init_homicide_offences)),
        (OFFENCES_KIDNAPPING, Preparation.filter_map(init_kidnapping_offences)),
        (OFFENCES_MONEY_LAUNDERING, Preparation.filter_map(init_money_laundering_offences)),
        (OFFENCES_NARCOTICS, Preparation.filter_map(init_narcotics_offences)),
        (OFFENCES_RAPE, Preparation.filter_map(init_rape_offences)),
        (OFFENCES_ROBBERY, Preparation.filter_map(init_robbery_offences)),
        (OFFENCES_SEXUAL_ASSAULT, Preparation.filter_map(init_sexual_assault_offences)),
        (OFFENCES_SEXUAL_EXPLOITATION, Preparation.filter_map(init_sexual_exploitation_offences)),
        (OFFENCES_SEXUAL_VIOLENCE, Preparation.filter_map(init_sexual_violence_offences)),
        (OFFENCES_THEFT, Preparation.filter_map(init_theft_offences)),
        (OFFENCES_THEFT_VEHICLE, Preparation.filter_map(init_theft_vehicle_offences)),
    ])

    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        CRIME_RATIO: crime_ratio,
        NON_PAYMENT_RATIO: non_payment_ratio,
        OFFENCES_RATIO: total_offences_ratio,
        PENSION_PPS_RATIO: pension_pps_ratio,
        SOCIAL_PROTECTION_PPS_RATIO: social_protection_pps_ratio,
        UNEXPECTED_RATIO: unexpected_ratio,
    }

    base_indicators: dict[str, dict[str, int | float | None]] = {
        CRIME_RATIO: crime_ratio,
        NON_PAYMENT_RATIO: non_payment_ratio,
        OFFENCES_ASSAULT: _prepare_offences(assault_offences),
        OFFENCES_ATTEMPTED_HOMICIDE: _prepare_offences(attempted_homicide_offences),
        OFFENCES_BRIBERY: _prepare_offences(bribery_offences),
        OFFENCES_BURGLARY: _prepare_offences(burglary_offences),
        OFFENCES_BURGLARY_PRIVATE: _prepare_offences(burglary_private_offences),
        OFFENCES_COMPUTERS: _prepare_offences(computers_offences),
        OFFENCES_CRIMINAL_GROUPS: _prepare_offences(criminal_groups_offences),
        OFFENCES_CORRUPTION: _prepare_offences(corruption_offences),
        OFFENCES_FRAUD: _prepare_offences(fraud_offences),
        OFFENCES_HOMICIDE: _prepare_offences(homicide_offences),
        OFFENCES_KIDNAPPING: _prepare_offences(kidnapping_offences),
        OFFENCES_MONEY_LAUNDERING: _prepare_offences(money_laundering_offences),
        OFFENCES_NARCOTICS: _prepare_offences(narcotics_offences),
        OFFENCES_RAPE: _prepare_offences(rape_offences),
        OFFENCES_ROBBERY: _prepare_offences(robbery_offences),
        OFFENCES_SEXUAL_ASSAULT: _prepare_offences(sexual_assault_offences),
        OFFENCES_SEXUAL_EXPLOITATION: _prepare_offences(sexual_exploitation_offences),
        OFFENCES_SEXUAL_VIOLENCE: _prepare_offences(sexual_violence_offences),
        OFFENCES_THEFT: _prepare_offences(theft_offences),
        OFFENCES_THEFT_VEHICLE: _prepare_offences(theft_vehicle_offences),
        PENSION_PPS_RATIO: pension_pps_ratio,
        SOCIAL_PROTECTION_PPS_RATIO: social_protection_pps_ratio,
        UNEXPECTED_RATIO: unexpected_ratio,
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
            SAFETY,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            SafetyStats.aggr_indicators,
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
            SAFETY,
            IND_PARAMS,
            IND_REVERSED_STATES,
            SafetyStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(
            args: list[str],
            series_type: str,
            direction: str,
    ) -> None:
        print_chart_data(
            args,
            SafetyStats.aggr_indicators,
            SAFETY,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        print_data_availability(
            SafetyStats.raw_indicators,
            SAFETY,
            target_year,
            ind_status,
        )
