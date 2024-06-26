package ro.webdata.qoli.aggr.stats.dimensions.health;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryStats;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.health.HealthAggrParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.health.HealthParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.health.HealthPaths.*;

public class HealthStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initDepressiveMajorRatio = Initializer.initConsolidatedMap(DEPRESSIVE_MAJOR_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH),
            initDepressiveNormalRatio = Initializer.initConsolidatedMap(DEPRESSIVE_NORMAL_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH),
            initDepressiveOtherRatio = Initializer.initConsolidatedMap(DEPRESSIVE_OTHER_RATIO_PARAMS, DEPRESSIVE_RATIO_PATH);

    private static final Map<String, Number>
            initBmiRatio = Initializer.initConsolidatedMap(BMI_PARAMS, BMI_PATH),
            initHealthyLifeRatio = Initializer.initConsolidatedMap(HEALTHY_LIFE_RATIO_PARAMS, HEALTHY_LIFE_RATIO_PATH),
            initHealthyLifeYears = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_PARAMS, HEALTHY_LIFE_YEARS_PATH),
            initHospitalBeds = Initializer.initConsolidatedMap(HOSPITAL_BEDS_PARAMS, HOSPITAL_BEDS_PATH),
            initLifeExpectancy = Initializer.initConsolidatedMap(LIFE_EXPECTANCY_PARAMS, LIFE_EXPECTANCY_PATH),
            initLongHealthIssuesRatio = Initializer.initConsolidatedMap(LONG_HEALTH_ISSUE_RATIO_PARAMS, LONG_HEALTH_ISSUES_RATIO_PATH),
            initNonAlcoholicRatio = Initializer.initConsolidatedMap(NON_ALCOHOLIC_RATIO_PARAMS, NON_ALCOHOLIC_RATIO_PATH),
            initNonFruitsVegetablesRatio = Initializer.initConsolidatedMap(NON_FRUITS_VEGETABLES_RATIO_PARAMS, NON_FRUITS_VEGETABLES_RATIO_PATH),
            initPersonnelDentists = Initializer.initConsolidatedMap(PERSONNEL_DENTISTS_PARAMS, HEALTH_PERSONNEL_PATH),
            initPersonnelDoctors = Initializer.initConsolidatedMap(PERSONNEL_DOCTORS_PARAMS, HEALTH_PERSONNEL_PATH),
            initPersonnelMidwives = Initializer.initConsolidatedMap(PERSONNEL_MIDWIVES_PARAMS, HEALTH_PERSONNEL_PATH),
            initPersonnelNurses = Initializer.initConsolidatedMap(PERSONNEL_NURSES_PARAMS, HEALTH_PERSONNEL_PATH),
            initPersonnelPharma = Initializer.initConsolidatedMap(PERSONNEL_PHARMA_PARAMS, HEALTH_PERSONNEL_PATH),
            initPersonnelTherapists = Initializer.initConsolidatedMap(PERSONNEL_THERAPISTS_PARAMS, HEALTH_PERSONNEL_PATH),
            initPhysicalActivitiesRatio = Initializer.initConsolidatedMap(PHYSICAL_ACTIVITIES_RATIO_PARAMS, PHYSICAL_ACTIVITIES_RATIO_PATH),
            initSmokersRatio = Initializer.initConsolidatedMap(SMOKERS_RATIO_PARAMS, SMOKERS_RATIO_PATH),
            initUnmetDentalRatio = Initializer.initConsolidatedMap(UNMET_DENTAL_RATIO_PARAMS, UNMET_DENTAL_RATIO_PATH),
            initUnmetMedicalRatio = Initializer.initConsolidatedMap(UNMET_MEDICAL_RATIO_PARAMS, UNMET_MEDICAL_RATIO_PATH),
            initWorkAccidents = Initializer.initConsolidatedMap(WORK_ACCIDENTS_PARAMS, WORK_ACCIDENTS_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate depressiveRatio
            depressiveMajorRatio = Preparation.prepareData(initDepressiveMajorRatio),
            depressiveNormalRatio = Preparation.prepareData(initDepressiveNormalRatio),
            depressiveOtherRatio = Preparation.prepareData(initDepressiveOtherRatio),

            // Intermediate data used to calculate personnelTotal
            personnelDentists = Preparation.prepareData(initPersonnelDentists),
            personnelDoctors = Preparation.prepareData(initPersonnelDoctors),
            personnelMidwives = Preparation.prepareData(initPersonnelMidwives),
            personnelNurses = Preparation.prepareData(initPersonnelNurses),
            personnelPharmacists = Preparation.prepareData(initPersonnelPharma),
            personnelPhysiotherapists = Preparation.prepareData(initPersonnelTherapists),

            bmiRatio = Preparation.prepareData(initBmiRatio),
            // Aggregate the share of population facing with depressive symptoms into a single indicator
            depressiveRatio = StatsUtils.calculateSum(
                    new ArrayList<>() {{
                        add(depressiveNormalRatio);
                        add(depressiveMajorRatio);
                        add(depressiveOtherRatio);
                    }}
            ),
            healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
            healthyLifeYears = Preparation.prepareData(initHealthyLifeYears),
            hospitalBeds = Preparation.prepareData(initHospitalBeds),
            lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
            longHealthIssuesRatio = Preparation.prepareData(initLongHealthIssuesRatio),
            nonAlcoholicRatio = Preparation.prepareData(initNonAlcoholicRatio),
            nonFruitsVegetablesRatio = Preparation.prepareData(initNonFruitsVegetablesRatio),
            // Aggregate the health personnel into a single indicator representing the
            // total personnel (excepting the caring personnel) per million inhabitants
            personnelTotal = StatsUtils.calculateSum(
                    new ArrayList<>() {{
                        add(personnelDentists);
                        add(personnelDoctors);
                        add(personnelMidwives);
                        add(personnelNurses);
                        add(personnelPharmacists);
                        add(personnelPhysiotherapists);
                    }},
                    // Transform "per hundred thousand" inhabitants into "per million" inhabitants
                    1.0/10
            ),
            physicalActivitiesRatio = Preparation.prepareData(initPhysicalActivitiesRatio),
            smokersRatio = Preparation.prepareData(initSmokersRatio),
            unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
            unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
            workAccidents = Preparation.preparePerThousandInhabitant(AuxiliaryStats.population, initWorkAccidents);

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(BODY_MASS_INDEX, Preparation.filterMap(initBmiRatio));
        put(DEPRESSIVE_MAJOR_RATIO, Preparation.filterMap(initDepressiveMajorRatio));
        put(DEPRESSIVE_NORMAL_RATIO, Preparation.filterMap(initDepressiveNormalRatio));
        put(DEPRESSIVE_OTHER_RATIO, Preparation.filterMap(initDepressiveOtherRatio));
        put(HEALTHY_LIFE_RATIO, Preparation.filterMap(initHealthyLifeRatio));
        put(HEALTHY_LIFE_YEARS, Preparation.filterMap(initHealthyLifeYears));
        put(HOSPITAL_BEDS, Preparation.filterMap(initHospitalBeds));
        put(LIFE_EXPECTANCY, Preparation.filterMap(initLifeExpectancy));
        put(LONG_HEALTH_ISSUES_RATIO, Preparation.filterMap(initLongHealthIssuesRatio));
        put(NON_ALCOHOLIC_RATIO, Preparation.filterMap(initNonAlcoholicRatio));
        put(NON_FRUITS_VEGETABLES_RATIO, Preparation.filterMap(initNonFruitsVegetablesRatio));
        put(PERSONNEL_DENTISTS, Preparation.filterMap(initPersonnelDentists));
        put(PERSONNEL_DOCTORS, Preparation.filterMap(initPersonnelDoctors));
        put(PERSONNEL_NURSES, Preparation.filterMap(initPersonnelNurses));
        put(PERSONNEL_PHARMACISTS, Preparation.filterMap(initPersonnelPharma));
        put(PERSONNEL_THERAPISTS, Preparation.filterMap(initPersonnelTherapists));
        put(PHYSICAL_ACTIVITIES_RATIO, Preparation.filterMap(initPhysicalActivitiesRatio));
        put(SMOKERS_RATIO, Preparation.filterMap(initSmokersRatio));
        put(UNMET_DENTAL_RATIO, Preparation.filterMap(initUnmetDentalRatio));
        put(UNMET_MEDICAL_RATIO, Preparation.filterMap(initUnmetMedicalRatio));
        put(WORK_ACCIDENTS, Preparation.filterMap(initWorkAccidents));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(BODY_MASS_INDEX, bmiRatio);
        put(DEPRESSIVE_MAJOR_RATIO, depressiveMajorRatio);
        put(DEPRESSIVE_NORMAL_RATIO, depressiveNormalRatio);
        put(DEPRESSIVE_OTHER_RATIO, depressiveOtherRatio);
        put(DEPRESSIVE_RATIO, depressiveRatio);
        put(HEALTHY_LIFE_RATIO, healthyLifeRatio);
        put(HEALTHY_LIFE_YEARS, healthyLifeYears);
        put(HOSPITAL_BEDS, prepareHospitalBedsRatio());
        put(LIFE_EXPECTANCY, lifeExpectancy);
        put(LONG_HEALTH_ISSUES_RATIO, longHealthIssuesRatio);
        put(NON_ALCOHOLIC_RATIO, nonAlcoholicRatio);
        put(NON_FRUITS_VEGETABLES_RATIO, nonFruitsVegetablesRatio);
        put(PERSONNEL_DENTISTS, personnelDentists);
        put(PERSONNEL_DOCTORS, personnelDoctors);
        put(PERSONNEL_MIDWIVES, personnelMidwives);
        put(PERSONNEL_NURSES, personnelNurses);
        put(PERSONNEL_PHARMACISTS, personnelPharmacists);
        put(PERSONNEL_THERAPISTS, personnelPhysiotherapists);
        put(PERSONNEL_TOTAL, personnelTotal);
        put(PHYSICAL_ACTIVITIES_RATIO, physicalActivitiesRatio);
        put(SMOKERS_RATIO, smokersRatio);
        put(UNMET_DENTAL_RATIO, unmetDentalRatio);
        put(UNMET_MEDICAL_RATIO, unmetMedicalRatio);
        put(WORK_ACCIDENTS, workAccidents);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(BODY_MASS_INDEX, bmiRatio);
        put(DEPRESSIVE_MAJOR_RATIO, depressiveMajorRatio);
        put(DEPRESSIVE_NORMAL_RATIO, depressiveNormalRatio);
        put(DEPRESSIVE_OTHER_RATIO, depressiveOtherRatio);
        put(HEALTHY_LIFE_RATIO, healthyLifeRatio);
        put(HEALTHY_LIFE_YEARS, healthyLifeYears);
        put(HOSPITAL_BEDS, hospitalBeds);
        put(LIFE_EXPECTANCY, lifeExpectancy);
        put(LONG_HEALTH_ISSUES_RATIO, longHealthIssuesRatio);
        put(NON_ALCOHOLIC_RATIO, nonAlcoholicRatio);
        put(NON_FRUITS_VEGETABLES_RATIO, nonFruitsVegetablesRatio);
        put(PERSONNEL_DENTISTS, personnelDentists);
        put(PERSONNEL_DOCTORS, personnelDoctors);
        put(PERSONNEL_NURSES, personnelNurses);
        put(PERSONNEL_PHARMACISTS, personnelPharmacists);
        put(PERSONNEL_THERAPISTS, personnelPhysiotherapists);
        put(PHYSICAL_ACTIVITIES_RATIO, physicalActivitiesRatio);
        put(SMOKERS_RATIO, smokersRatio);
        put(UNMET_DENTAL_RATIO, unmetDentalRatio);
        put(UNMET_MEDICAL_RATIO, unmetMedicalRatio);
        put(WORK_ACCIDENTS, workAccidents);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, HEALTH, AGGR_PARAMS, AGGR_REVERSED_STATES, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, HEALTH, IND_PARAMS, IND_REVERSED_STATES, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, HEALTH, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, HEALTH, targetYear, indStatus);
    }

    // Transform hospital beds "per thousand" inhabitants into "per million" inhabitants
    private static Map<String, Number> prepareHospitalBedsRatio() {
        return StatsUtils.calculateSum(
                new ArrayList<>() {{
                    add(hospitalBeds);
                }},
                // Transform "per hundred thousand" inhabitants into "per million" inhabitants
                1.0/10
        );
    }
}
