package app.java.commons.dimensions.health;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.dimensions.auxiliary.AuxiliaryStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.health.HealthParams.*;
import static app.java.commons.dimensions.health.HealthPaths.*;

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
            personnelNurses = Preparation.prepareData(initPersonnelNurses),
            personnelPharmacists = Preparation.prepareData(initPersonnelPharma),
            personnelPhysiotherapists = Preparation.prepareData(initPersonnelTherapists),

            bmiRatio = Preparation.prepareData(initBmiRatio),
            depressiveRatio = prepareTotalDepressiveRatio(),
            healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
            healthyLifeYears = Preparation.prepareData(initHealthyLifeYears),
            hospitalBeds = Preparation.prepareData(initHospitalBeds),
            lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
            longHealthIssuesRatio = Preparation.prepareData(initLongHealthIssuesRatio),
            nonAlcoholicRatio = Preparation.prepareData(initNonAlcoholicRatio),
            nonFruitsVegetablesRatio = Preparation.prepareData(initNonFruitsVegetablesRatio),
            personnelTotal = prepareTotalPersonnelRatio(),
            physicalActivitiesRatio = Preparation.prepareData(initPhysicalActivitiesRatio),
            smokersRatio = Preparation.prepareData(initSmokersRatio),
            unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
            unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
            workAccidents = Preparation.preparePerThousandInhabitant(AuxiliaryStats.population, initWorkAccidents);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(BMI_FILE_NAME, Preparation.filterMap(initBmiRatio));
        put(DEPRESSIVE_MAJOR_RATIO_FILE_NAME, Preparation.filterMap(initDepressiveMajorRatio));
        put(DEPRESSIVE_NORMAL_RATIO_FILE_NAME, Preparation.filterMap(initDepressiveNormalRatio));
        put(DEPRESSIVE_OTHER_RATIO_FILE_NAME, Preparation.filterMap(initDepressiveOtherRatio));
        put(HEALTHY_LIFE_RATIO_FILE_NAME, Preparation.filterMap(initHealthyLifeRatio));
        put(HEALTHY_LIFE_YEARS_FILE_NAME, Preparation.filterMap(initHealthyLifeYears));
        put(HOSPITAL_BEDS_FILE_NAME, Preparation.filterMap(initHospitalBeds));
        put(LIFE_EXPECTANCY_FILE_NAME, Preparation.filterMap(initLifeExpectancy));
        put(LONG_HEALTH_ISSUES_RATIO_FILE_NAME, Preparation.filterMap(initLongHealthIssuesRatio));
        put(NON_ALCOHOLIC_RATIO_FILE_NAME, Preparation.filterMap(initNonAlcoholicRatio));
        put(NON_FRUITS_VEGETABLES_RATIO_FILE_NAME, Preparation.filterMap(initNonFruitsVegetablesRatio));
        put(PERSONNEL_DENTISTS_FILE_NAME, Preparation.filterMap(initPersonnelDentists));
        put(PERSONNEL_DOCTORS_FILE_NAME, Preparation.filterMap(initPersonnelDoctors));
        put(PERSONNEL_NURSES_FILE_NAME, Preparation.filterMap(initPersonnelNurses));
        put(PERSONNEL_PHARMA_FILE_NAME, Preparation.filterMap(initPersonnelPharma));
        put(PERSONNEL_THERAPISTS_FILE_NAME, Preparation.filterMap(initPersonnelTherapists));
        put(PHYSICAL_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initPhysicalActivitiesRatio));
        put(SMOKERS_RATIO_FILE_NAME, Preparation.filterMap(initSmokersRatio));
        put(UNMET_DENTAL_RATIO_FILE_NAME, Preparation.filterMap(initUnmetDentalRatio));
        put(UNMET_MEDICAL_RATIO_FILE_NAME, Preparation.filterMap(initUnmetMedicalRatio));
        put(WORK_ACCIDENTS_FILE_NAME, Preparation.filterMap(initWorkAccidents));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(BMI_FILE_NAME, initBmiRatio);
        put(DEPRESSIVE_MAJOR_RATIO_FILE_NAME, depressiveMajorRatio);
        put(DEPRESSIVE_NORMAL_RATIO_FILE_NAME, depressiveNormalRatio);
        put(DEPRESSIVE_OTHER_RATIO_FILE_NAME, depressiveOtherRatio);
        put(DEPRESSIVE_RATIO_FILE_NAME, depressiveRatio);
        put(HEALTHY_LIFE_RATIO_FILE_NAME, healthyLifeRatio);
        put(HEALTHY_LIFE_YEARS_FILE_NAME, healthyLifeYears);
        put(HOSPITAL_BEDS_FILE_NAME, hospitalBeds);
        put(LIFE_EXPECTANCY_FILE_NAME, lifeExpectancy);
        put(LONG_HEALTH_ISSUES_RATIO_FILE_NAME, longHealthIssuesRatio);
        put(NON_ALCOHOLIC_RATIO_FILE_NAME, nonAlcoholicRatio);
        put(NON_FRUITS_VEGETABLES_RATIO_FILE_NAME, nonFruitsVegetablesRatio);
        put(PERSONNEL_DENTISTS_FILE_NAME, personnelDentists);
        put(PERSONNEL_DOCTORS_FILE_NAME, personnelDoctors);
        put(PERSONNEL_NURSES_FILE_NAME, personnelNurses);
        put(PERSONNEL_PHARMA_FILE_NAME, personnelPharmacists);
        put(PERSONNEL_THERAPISTS_FILE_NAME, personnelPhysiotherapists);
        put(PERSONNEL_TOTAL_FILE_NAME, personnelTotal);
        put(PHYSICAL_ACTIVITIES_RATIO_FILE_NAME, physicalActivitiesRatio);
        put(SMOKERS_RATIO_FILE_NAME, smokersRatio);
        put(UNMET_DENTAL_RATIO_FILE_NAME, unmetDentalRatio);
        put(UNMET_MEDICAL_RATIO_FILE_NAME, unmetMedicalRatio);
        put(WORK_ACCIDENTS_FILE_NAME, workAccidents);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(bmiRatio, key)
                        * MathUtils.percentageSafetyDouble(lifeExpectancy, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeRatio, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeYears, key)
                        * MathUtils.percentageSafetyDouble(nonAlcoholicRatio, key)
                        * MathUtils.percentageSafetyDouble(physicalActivitiesRatio, key)

                        * (MathUtils.percentageSafetyDouble(hospitalBeds, key) / 10) // per million inhabitants
                        * (MathUtils.percentageSafetyDouble(personnelTotal, key) / 10) // per million inhabitants

                        * MathUtils.percentageSafetyDouble(depressiveRatio, key, true)
                        * MathUtils.percentageSafetyDouble(longHealthIssuesRatio, key, true)
                        * MathUtils.percentageSafetyDouble(nonFruitsVegetablesRatio, key, true)
                        * MathUtils.percentageSafetyDouble(smokersRatio, key, true)
                        * MathUtils.percentageSafetyDouble(unmetDentalRatio, key, true)
                        * MathUtils.percentageSafetyDouble(unmetMedicalRatio, key, true)
                        * MathUtils.percentageSafetyDouble(workAccidents, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(workAccidents, true));
//        Print.print(consolidatedList, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, HEALTH_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, HEALTH_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Aggregate the share of population facing with depressive symptoms into a single indicator
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareTotalDepressiveRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueNormal = depressiveNormalRatio.get(key).doubleValue();
                double valueMajor = depressiveMajorRatio.get(key).doubleValue();
                double valueOther = depressiveOtherRatio.get(key).doubleValue();

                Number value = valueNormal + valueMajor + valueOther;
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Aggregate the health personnel into a single indicator representing the
     * total personnel per hundred thousand inhabitants
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareTotalPersonnelRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueDentists = personnelDentists.get(key).doubleValue();
                double valueDoctors = personnelDoctors.get(key).doubleValue();
                double valueNurses = personnelNurses.get(key).doubleValue();
                double valuePharmacists = personnelPharmacists.get(key).doubleValue();
                double valuePhysiotherapists = personnelPhysiotherapists.get(key).doubleValue();

                double value = 0
                        + valueDentists
                        + valueDoctors
                        + valueNurses
                        + valuePharmacists
                        + valuePhysiotherapists;

                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
