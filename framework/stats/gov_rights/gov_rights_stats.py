import csv
import sys
from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.gov_rights.gov_rights_aggr_params import *
from framework.stats.gov_rights.gov_rights_aggr_params import GOVERNANCE
from framework.stats.gov_rights.gov_rights_params import *
from framework.stats.gov_rights.gov_rights_paths import *


def _voter_turnout_csv_to_map() -> dict[str, dict[str, int | float | None]]:
    """Convert voter turnout data from CSV into maps grouped by election type."""
    eu_parliamentary_map = {}
    parliamentary_map = {}
    presidential_map = {}

    try:
        with open(VOTER_TURNOUT_PATH, "r", encoding="utf-8", newline="") as csv_file:
            reader = csv.reader(csv_file, delimiter=";")

            # Skip the header
            next(reader, None)

            for items in reader:
                if not items or not any(item.strip() for item in items):
                    continue

                if len(items) < 6:
                    # Voter turnout value does not exist
                    continue

                country_code = items[1].strip().upper()
                if country_code == "GB":
                    country_code = "UK"

                election_type = items[3].strip().lower()
                year = int(float(items[4].split("-")[0]))
                value_str = items[5].replace("%", "").strip()

                if not value_str or country_code not in EU28_MEMBERS:
                    continue

                value = float(value_str)
                key = f"{country_code}_{year}"

                if election_type == "eu parliament":
                    eu_parliamentary_map[key] = value
                elif election_type == "parliamentary":
                    parliamentary_map[key] = value
                elif election_type == "presidential":
                    presidential_map[key] = value

    except FileNotFoundError:
        print(f"The file {VOTER_TURNOUT_PATH} has not been found.", file=sys.stderr)
    except OSError:
        print(f"Error at reading the file {VOTER_TURNOUT_PATH} from the disk.", file=sys.stderr)

    return {
        "euParliament": dict(sorted(eu_parliamentary_map.items())),
        "parliamentary": dict(sorted(parliamentary_map.items())),
        "presidential": dict(sorted(presidential_map.items())),
    }


class GovRightsStats:
    # Intermediate data which will be grouped into a single indicator
    init_population_legtst_trust = Initializer.init_consolidated_map(
        POPULATION_LEGAL_TRUST_PARAMS, POPULATION_TRUST_PATH
    )
    init_population_others_trust = Initializer.init_consolidated_map(
        POPULATION_OTHER_TRUST_PARAMS, POPULATION_TRUST_OTHER_PATH
    )
    init_population_plctst_trust = Initializer.init_consolidated_map(
        POPULATION_POLICE_TRUST_PARAMS, POPULATION_TRUST_PATH
    )
    init_population_plttst_trust = Initializer.init_consolidated_map(
        POPULATION_POLITIC_TRUST_PARAMS, POPULATION_TRUST_PATH
    )

    init_voter_turnout = _voter_turnout_csv_to_map()

    # Intermediate data which will be grouped into a single indicator
    init_turnout_eu_parliament = Initializer.init_map(init_voter_turnout["euParliament"])
    init_turnout_parliamentary = Initializer.init_map(init_voter_turnout["parliamentary"])
    init_turnout_presidential = Initializer.init_map(init_voter_turnout["presidential"])

    init_citizenship_ratio = Initializer.init_consolidated_map(
        CITIZENSHIP_RATIO_PARAMS, CITIZENSHIP_RATIO_PATH
    )
    init_gender_emp_gap = Initializer.init_consolidated_map(
        GENDER_EMP_GAP_PARAMS, GENDER_EMP_GAP_PATH
    )
    init_gender_pay_gap = Initializer.init_consolidated_map(
        GENDER_PAY_GAP_PARAMS, GENDER_PAY_GAP_PATH
    )

    # Intermediate data used to calculate population_trust_ratio
    population_legtst_trust_ratio = Preparation.prepare_data(init_population_legtst_trust)
    population_others_trust_ratio = Preparation.prepare_data(init_population_others_trust)
    population_plctst_trust_ratio = Preparation.prepare_data(init_population_plctst_trust)
    population_plttst_trust_ratio = Preparation.prepare_data(init_population_plttst_trust)

    # Intermediate data used to calculate voter_turnout
    turnout_eu_parliament = Preparation.prepare_data(init_turnout_eu_parliament)
    turnout_parliamentary = Preparation.prepare_data(init_turnout_parliamentary)
    turnout_presidential = Preparation.prepare_data(init_turnout_presidential)

    citizenship = Preparation.prepare_data(init_citizenship_ratio)
    gender_emp_gap = Preparation.prepare_data(init_gender_emp_gap)
    gender_pay_gap = Preparation.prepare_data(init_gender_pay_gap)

    # Aggregate the population trust ratios into a single index
    population_trust_ratio = StatsUtils.calculate_geometric_mean(
        [
            population_legtst_trust_ratio,
            population_others_trust_ratio,
            population_plctst_trust_ratio,
            population_plttst_trust_ratio,
        ],
        10.0,
    )

    # Aggregate voter turnout for different types of elections into a single index
    voter_turnout = StatsUtils.calculate_geometric_mean(
        [
            turnout_eu_parliament,
            turnout_parliamentary,
            turnout_presidential,
        ]
    )

    # Raw indicators (unfiltered)
    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict(
        [
            (CITIZENSHIP_RATIO, Preparation.filter_map(init_citizenship_ratio)),
            (GENDER_EMP_GAP, Preparation.filter_map(init_gender_emp_gap)),
            (GENDER_PAY_GAP, Preparation.filter_map(init_gender_pay_gap)),
            (POPULATION_TRUST_LEGAL, Preparation.filter_map(init_population_legtst_trust)),
            (POPULATION_TRUST_OTHER, Preparation.filter_map(init_population_others_trust)),
            (POPULATION_TRUST_POLICE, Preparation.filter_map(init_population_plctst_trust)),
            (POPULATION_TRUST_POLITIC, Preparation.filter_map(init_population_plttst_trust)),
            (VOTER_TURNOUT_EU_PARLIAMENT, Preparation.filter_map(init_turnout_eu_parliament)),
            (VOTER_TURNOUT_PARLIAMENTARY, Preparation.filter_map(init_turnout_parliamentary)),
            (VOTER_TURNOUT_PRESIDENTIAL, Preparation.filter_map(init_turnout_presidential)),
        ]
    )

    # Aggregation indicators
    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        CITIZENSHIP_RATIO: citizenship,
        GENDER_EMP_GAP: gender_emp_gap,
        GENDER_PAY_GAP: gender_pay_gap,
        POPULATION_TRUST: population_trust_ratio,
        VOTER_TURNOUT: voter_turnout,
    }

    # Base indicators
    base_indicators: dict[str, dict[str, int | float | None]] = {
        CITIZENSHIP_RATIO: citizenship,
        GENDER_EMP_GAP: gender_emp_gap,
        GENDER_PAY_GAP: gender_pay_gap,
        POPULATION_TRUST_LEGAL: population_legtst_trust_ratio,
        POPULATION_TRUST_OTHER: population_others_trust_ratio,
        POPULATION_TRUST_POLICE: population_plctst_trust_ratio,
        POPULATION_TRUST_POLITIC: population_plttst_trust_ratio,
        POPULATION_TRUST: population_trust_ratio,
        VOTER_TURNOUT_EU_PARLIAMENT: turnout_eu_parliament,
        VOTER_TURNOUT_PARLIAMENTARY: turnout_parliamentary,
        VOTER_TURNOUT_PRESIDENTIAL: turnout_presidential,
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
            GOVERNANCE,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            GovRightsStats.aggr_indicators,
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
            GOVERNANCE,
            IND_PARAMS,
            IND_REVERSED_STATES,
            GovRightsStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(args: list[str], series_type: str, direction: str) -> None:
        """Print aggregation indicators"""
        print_chart_data(
            args,
            GovRightsStats.aggr_indicators,
            GOVERNANCE,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        """Print data availability for the governance dimension"""
        print_data_availability(
            GovRightsStats.raw_indicators,
            GOVERNANCE,
            target_year,
            ind_status,
        )
