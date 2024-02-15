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
    private static final Map<String, Number>
            initAlcoholicRatio = Initializer.initConsolidatedMap(ALCOHOLIC_RATIO_PARAMS, ALCOHOLIC_RATIO_PATH),
            initBmiObeseRatio = Initializer.initConsolidatedMap(BMI_OBESE_PARAMS, BMI_PATH),
            initBmiOverweightRatio = Initializer.initConsolidatedMap(BMI_OVERWEIGHT_PARAMS, BMI_PATH),
            initFruitsVegetablesRatio = Initializer.initConsolidatedMap(FRUITS_VEGETABLES_RATIO_PARAMS, FRUITS_VEGETABLES_RATIO_PATH),
            initHealthyLifeRatio = Initializer.initConsolidatedMap(HEALTHY_LIFE_RATIO_PARAMS, HEALTHY_LIFE_RATIO_PATH),
            initHealthyLifeYears = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_PARAMS, HEALTHY_LIFE_YEARS_PATH),
            initHospitalBeds = Initializer.initConsolidatedMap(HOSPITAL_BEDS_PARAMS, HOSPITAL_BEDS_PATH),
            initLifeExpectancy = Initializer.initConsolidatedMap(LIFE_EXPECTANCY_PARAMS, LIFE_EXPECTANCY_PATH),
            initLongHealthIssuesRatio = Initializer.initConsolidatedMap(LONG_HEALTH_ISSUE_RATIO_PARAMS, LONG_HEALTH_ISSUES_RATIO_PATH),
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
            // Intermediate data used to calculate personnelTotal
            personnelDentists = Preparation.prepareData(initPersonnelDentists),
            personnelDoctors = Preparation.prepareData(initPersonnelDoctors),
            personnelNurses = Preparation.prepareData(initPersonnelNurses),
            personnelPharmacists = Preparation.prepareData(initPersonnelPharma),
            personnelPhysiotherapists = Preparation.prepareData(initPersonnelTherapists),

            alcoholicRatio = Preparation.prepareData(initAlcoholicRatio),
            bmiObeseRatio = Preparation.prepareData(initBmiObeseRatio),
            bmiOverweightRatio = Preparation.prepareData(initBmiOverweightRatio),
            fruitsVegetablesRatio = Preparation.prepareData(initFruitsVegetablesRatio),
            healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
            healthyLifeYears = Preparation.prepareData(initHealthyLifeYears),
            hospitalBeds = Preparation.prepareData(initHospitalBeds),
            lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
            longHealthIssuesRatio = Preparation.prepareData(initLongHealthIssuesRatio),
            personnelTotal = preparePersonnelRatio(),
            physicalActivitiesRatio = Preparation.prepareData(initPhysicalActivitiesRatio),
            smokersRatio = Preparation.prepareData(initSmokersRatio),
            unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
            unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
            workAccidents = Preparation.preparePerThousandInhabitant(AuxiliaryStats.population, initWorkAccidents);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(ALCOHOLIC_RATIO_FILE_NAME, Preparation.filterMap(initAlcoholicRatio));
        put(BMI_OBESE_FILE_NAME, Preparation.filterMap(initBmiObeseRatio));
        put(BMI_OVERWEIGHT_FILE_NAME, Preparation.filterMap(initBmiOverweightRatio));
        put(FRUITS_VEGETABLES_RATIO_FILE_NAME, Preparation.filterMap(initFruitsVegetablesRatio));
        put(HEALTHY_LIFE_RATIO_FILE_NAME, Preparation.filterMap(initHealthyLifeRatio));
        put(HEALTHY_LIFE_YEARS_FILE_NAME, Preparation.filterMap(initHealthyLifeYears));
        put(HOSPITAL_BEDS_FILE_NAME, Preparation.filterMap(initHospitalBeds));
        put(LIFE_EXPECTANCY_FILE_NAME, Preparation.filterMap(initLifeExpectancy));
        put(LONG_HEALTH_ISSUES_RATIO_FILE_NAME, Preparation.filterMap(initLongHealthIssuesRatio));
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
        put(ALCOHOLIC_RATIO_FILE_NAME, alcoholicRatio);
        put(BMI_OBESE_FILE_NAME, bmiObeseRatio);
        put(BMI_OVERWEIGHT_FILE_NAME, bmiOverweightRatio);
        put(FRUITS_VEGETABLES_RATIO_FILE_NAME, fruitsVegetablesRatio);
        put(HEALTHY_LIFE_RATIO_FILE_NAME, healthyLifeRatio);
        put(HEALTHY_LIFE_YEARS_FILE_NAME, healthyLifeYears);
        put(HOSPITAL_BEDS_FILE_NAME, hospitalBeds);
        put(LIFE_EXPECTANCY_FILE_NAME, lifeExpectancy);
        put(LONG_HEALTH_ISSUES_RATIO_FILE_NAME, longHealthIssuesRatio);
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
                        * MathUtils.percentageSafetyDouble(fruitsVegetablesRatio, key)
                        * MathUtils.percentageSafetyDouble(lifeExpectancy, key)
                        * MathUtils.percentageSafetyDouble(personnelTotal, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeRatio, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeYears, key)
                        * MathUtils.percentageSafetyDouble(hospitalBeds, key)
                        * MathUtils.percentageSafetyDouble(physicalActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(alcoholicRatio, key, true)
                        * MathUtils.percentageSafetyDouble(bmiOverweightRatio, key, true)
                        * MathUtils.percentageSafetyDouble(bmiObeseRatio, key, true)
                        * MathUtils.percentageSafetyDouble(longHealthIssuesRatio, key, true)
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

    /**
     * Aggregate the health personnel into a single indicator representing the
     * total personnel per hundred thousand inhabitants
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> preparePersonnelRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double value = 0
                        + personnelDentists.get(key).doubleValue()
                        + personnelDoctors.get(key).doubleValue()
                        + personnelNurses.get(key).doubleValue()
                        + personnelPharmacists.get(key).doubleValue()
                        + personnelPhysiotherapists.get(key).doubleValue();

                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
