from collections import OrderedDict

from framework.common.constants import EU28_MEMBERS
from framework.processing.data_initialization import Initializer
from framework.processing.data_preparation import Preparation
from framework.stats.dimensions.auxiliary.auxiliary_stats import AuxiliaryStats
from framework.stats.common.print_utils import print_chart_data, print_data_availability
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.health.health_aggr_params import *
from framework.stats.dimensions.health.health_params import *
from framework.stats.dimensions.health.health_paths import *


def _prepare_hospital_beds_ratio(
        hospital_beds: dict[str, int | float]
) -> dict[str, int | float | None]:
    """Transform hospital beds per thousand inhabitants into per million inhabitants."""
    return StatsUtils.calculate_sum([hospital_beds], 1.0 / 10)


class HealthStats:
    # Intermediate data which will be grouped into a single indicator
    init_depressive_major_ratio = Initializer.init_consolidated_map(
        DEPRESSIVE_MAJOR_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH
    )
    init_depressive_normal_ratio = Initializer.init_consolidated_map(
        DEPRESSIVE_NORMAL_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH
    )
    init_depressive_other_ratio = Initializer.init_consolidated_map(
        DEPRESSIVE_OTHER_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH
    )

    init_bmi_ratio = Initializer.init_consolidated_map(BMI_PARAMS, BMI_PATH)
    init_healthy_life_ratio = Initializer.init_consolidated_map(
        HEALTHY_LIFE_RATIO_PARAMS, HEALTHY_LIFE_RATIO_PATH
    )
    init_healthy_life_years = Initializer.init_consolidated_map(
        HEALTHY_LIFE_YEARS_PARAMS, HEALTHY_LIFE_YEARS_PATH
    )
    init_hospital_beds = Initializer.init_consolidated_map(
        HOSPITAL_BEDS_PARAMS, HOSPITAL_BEDS_PATH
    )
    init_life_expectancy = Initializer.init_consolidated_map(
        LIFE_EXPECTANCY_PARAMS, LIFE_EXPECTANCY_PATH
    )
    init_long_health_issues_ratio = Initializer.init_consolidated_map(
        LONG_HEALTH_ISSUE_RATIO_PARAMS, LONG_HEALTH_ISSUES_RATIO_PATH
    )
    init_non_alcoholic_ratio = Initializer.init_consolidated_map(
        NON_ALCOHOLIC_RATIO_PARAMS, NON_ALCOHOLIC_RATIO_PATH
    )
    init_non_fruits_vegetables_ratio = Initializer.init_consolidated_map(
        NON_FRUITS_VEGETABLES_RATIO_PARAMS, NON_FRUITS_VEGETABLES_RATIO_PATH
    )
    init_personnel_dentists = Initializer.init_consolidated_map(
        PERSONNEL_DENTISTS_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_personnel_doctors = Initializer.init_consolidated_map(
        PERSONNEL_DOCTORS_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_personnel_midwives = Initializer.init_consolidated_map(
        PERSONNEL_MIDWIVES_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_personnel_nurses = Initializer.init_consolidated_map(
        PERSONNEL_NURSES_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_personnel_pharma = Initializer.init_consolidated_map(
        PERSONNEL_PHARMA_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_personnel_therapists = Initializer.init_consolidated_map(
        PERSONNEL_THERAPISTS_PARAMS, HEALTH_PERSONNEL_PATH
    )
    init_physical_activities_ratio = Initializer.init_consolidated_map(
        PHYSICAL_ACTIVITIES_RATIO_PARAMS, PHYSICAL_ACTIVITIES_RATIO_PATH
    )
    init_smokers_ratio = Initializer.init_consolidated_map(
        SMOKERS_RATIO_PARAMS, SMOKERS_RATIO_PATH
    )
    init_unmet_dental_ratio = Initializer.init_consolidated_map(
        UNMET_DENTAL_RATIO_PARAMS, UNMET_DENTAL_RATIO_PATH
    )
    init_unmet_medical_ratio = Initializer.init_consolidated_map(
        UNMET_MEDICAL_RATIO_PARAMS, UNMET_MEDICAL_RATIO_PATH
    )
    init_work_accidents = Initializer.init_consolidated_map(
        WORK_ACCIDENTS_PARAMS, WORK_ACCIDENTS_PATH
    )

    # Intermediate data used to calculate depressive_ratio
    depressive_major_ratio = Preparation.prepare_data(init_depressive_major_ratio)
    depressive_normal_ratio = Preparation.prepare_data(init_depressive_normal_ratio)
    depressive_other_ratio = Preparation.prepare_data(init_depressive_other_ratio)

    # Intermediate data used to calculate personnel_total
    personnel_dentists = Preparation.prepare_data(init_personnel_dentists)
    personnel_doctors = Preparation.prepare_data(init_personnel_doctors)
    personnel_midwives = Preparation.prepare_data(init_personnel_midwives)
    personnel_nurses = Preparation.prepare_data(init_personnel_nurses)
    personnel_pharmacists = Preparation.prepare_data(init_personnel_pharma)
    personnel_physiotherapists = Preparation.prepare_data(init_personnel_therapists)

    bmi_ratio = Preparation.prepare_data(init_bmi_ratio)

    # Aggregate the share of population facing depressive symptoms
    # into a single indicator
    depressive_ratio = StatsUtils.calculate_sum(
        [
            depressive_normal_ratio,
            depressive_major_ratio,
            depressive_other_ratio,
        ]
    )

    healthy_life_ratio = Preparation.prepare_data(init_healthy_life_ratio)
    healthy_life_years = Preparation.prepare_data(init_healthy_life_years)
    hospital_beds = Preparation.prepare_data(init_hospital_beds)
    life_expectancy = Preparation.prepare_data(init_life_expectancy)
    long_health_issues_ratio = Preparation.prepare_data(init_long_health_issues_ratio)
    non_alcoholic_ratio = Preparation.prepare_data(init_non_alcoholic_ratio)
    non_fruits_vegetables_ratio = Preparation.prepare_data(init_non_fruits_vegetables_ratio)

    # Aggregate health personnel into a single indicator representing
    # total personnel (except caring personnel) per million inhabitants
    personnel_total = StatsUtils.calculate_sum(
        [
            personnel_dentists,
            personnel_doctors,
            personnel_midwives,
            personnel_nurses,
            personnel_pharmacists,
            personnel_physiotherapists,
        ],
        1.0 / 10,
    )

    physical_activities_ratio = Preparation.prepare_data(init_physical_activities_ratio)
    smokers_ratio = Preparation.prepare_data(init_smokers_ratio)
    unmet_dental_ratio = Preparation.prepare_data(init_unmet_dental_ratio)
    unmet_medical_ratio = Preparation.prepare_data(init_unmet_medical_ratio)
    work_accidents = Preparation.prepare_per_thousand_inhabitant(
        AuxiliaryStats.population, init_work_accidents
    )

    # Raw indicators (unfiltered)
    raw_indicators: dict[str, dict[str, int | float | None]] = OrderedDict(
        [
            (BODY_MASS_INDEX, Preparation.filter_map(init_bmi_ratio)),
            (DEPRESSIVE_MAJOR_RATIO, Preparation.filter_map(init_depressive_major_ratio)),
            (DEPRESSIVE_NORMAL_RATIO, Preparation.filter_map(init_depressive_normal_ratio)),
            (DEPRESSIVE_OTHER_RATIO, Preparation.filter_map(init_depressive_other_ratio)),
            (HEALTHY_LIFE_RATIO, Preparation.filter_map(init_healthy_life_ratio)),
            (HEALTHY_LIFE_YEARS, Preparation.filter_map(init_healthy_life_years)),
            (HOSPITAL_BEDS, Preparation.filter_map(init_hospital_beds)),
            (LIFE_EXPECTANCY, Preparation.filter_map(init_life_expectancy)),
            (LONG_HEALTH_ISSUES_RATIO, Preparation.filter_map(init_long_health_issues_ratio)),
            (NON_ALCOHOLIC_RATIO, Preparation.filter_map(init_non_alcoholic_ratio)),
            (NON_FRUITS_VEGETABLES_RATIO, Preparation.filter_map(init_non_fruits_vegetables_ratio)),
            (PERSONNEL_DENTISTS, Preparation.filter_map(init_personnel_dentists)),
            (PERSONNEL_DOCTORS, Preparation.filter_map(init_personnel_doctors)),
            (PERSONNEL_NURSES, Preparation.filter_map(init_personnel_nurses)),
            (PERSONNEL_PHARMACISTS, Preparation.filter_map(init_personnel_pharma)),
            (PERSONNEL_THERAPISTS, Preparation.filter_map(init_personnel_therapists)),
            (PHYSICAL_ACTIVITIES_RATIO, Preparation.filter_map(init_physical_activities_ratio)),
            (SMOKERS_RATIO, Preparation.filter_map(init_smokers_ratio)),
            (UNMET_DENTAL_RATIO, Preparation.filter_map(init_unmet_dental_ratio)),
            (UNMET_MEDICAL_RATIO, Preparation.filter_map(init_unmet_medical_ratio)),
            (WORK_ACCIDENTS, Preparation.filter_map(init_work_accidents)),
        ]
    )

    # Aggregation indicators
    aggr_indicators: dict[str, dict[str, int | float | None]] = {
        BODY_MASS_INDEX: bmi_ratio,
        DEPRESSIVE_MAJOR_RATIO: depressive_major_ratio,
        DEPRESSIVE_NORMAL_RATIO: depressive_normal_ratio,
        DEPRESSIVE_OTHER_RATIO: depressive_other_ratio,
        DEPRESSIVE_RATIO: depressive_ratio,
        HEALTHY_LIFE_RATIO: healthy_life_ratio,
        HEALTHY_LIFE_YEARS: healthy_life_years,
        # TODO: check aggr_indicators[HOSPITAL_BEDS]
        HOSPITAL_BEDS: _prepare_hospital_beds_ratio(hospital_beds),
        LIFE_EXPECTANCY: life_expectancy,
        LONG_HEALTH_ISSUES_RATIO: long_health_issues_ratio,
        NON_ALCOHOLIC_RATIO: non_alcoholic_ratio,
        NON_FRUITS_VEGETABLES_RATIO: non_fruits_vegetables_ratio,
        PERSONNEL_DENTISTS: personnel_dentists,
        PERSONNEL_DOCTORS: personnel_doctors,
        PERSONNEL_MIDWIVES: personnel_midwives,
        PERSONNEL_NURSES: personnel_nurses,
        PERSONNEL_PHARMACISTS: personnel_pharmacists,
        PERSONNEL_THERAPISTS: personnel_physiotherapists,
        PERSONNEL_TOTAL: personnel_total,
        PHYSICAL_ACTIVITIES_RATIO: physical_activities_ratio,
        SMOKERS_RATIO: smokers_ratio,
        UNMET_DENTAL_RATIO: unmet_dental_ratio,
        UNMET_MEDICAL_RATIO: unmet_medical_ratio,
        WORK_ACCIDENTS: work_accidents,
    }

    # Base indicators
    base_indicators: dict[str, dict[str, int | float | None]] = {
        BODY_MASS_INDEX: bmi_ratio,
        DEPRESSIVE_MAJOR_RATIO: depressive_major_ratio,
        DEPRESSIVE_NORMAL_RATIO: depressive_normal_ratio,
        DEPRESSIVE_OTHER_RATIO: depressive_other_ratio,
        HEALTHY_LIFE_RATIO: healthy_life_ratio,
        HEALTHY_LIFE_YEARS: healthy_life_years,
        HOSPITAL_BEDS: hospital_beds,
        LIFE_EXPECTANCY: life_expectancy,
        LONG_HEALTH_ISSUES_RATIO: long_health_issues_ratio,
        NON_ALCOHOLIC_RATIO: non_alcoholic_ratio,
        NON_FRUITS_VEGETABLES_RATIO: non_fruits_vegetables_ratio,
        PERSONNEL_DENTISTS: personnel_dentists,
        PERSONNEL_DOCTORS: personnel_doctors,
        PERSONNEL_NURSES: personnel_nurses,
        PERSONNEL_PHARMACISTS: personnel_pharmacists,
        PERSONNEL_THERAPISTS: personnel_physiotherapists,
        PHYSICAL_ACTIVITIES_RATIO: physical_activities_ratio,
        SMOKERS_RATIO: smokers_ratio,
        UNMET_DENTAL_RATIO: unmet_dental_ratio,
        UNMET_MEDICAL_RATIO: unmet_medical_ratio,
        WORK_ACCIDENTS: work_accidents,
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
            HEALTH,
            AGGR_PARAMS,
            AGGR_REVERSED_STATES,
            HealthStats.aggr_indicators,
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
            HEALTH,
            IND_PARAMS,
            IND_REVERSED_STATES,
            HealthStats.base_indicators,
        )

    @staticmethod
    def print_aggr_indicators(args: list[str], series_type: str, direction: str) -> None:
        """Print aggregation indicators"""
        print_chart_data(
            args,
            HealthStats.aggr_indicators,
            HEALTH,
            EU28_MEMBERS,
            series_type,
            direction,
        )

    @staticmethod
    def print_data_availability(target_year: int, ind_status: bool) -> None:
        """Print data availability for the health dimension"""
        print_data_availability(
            HealthStats.raw_indicators,
            HEALTH,
            target_year,
            ind_status,
        )
