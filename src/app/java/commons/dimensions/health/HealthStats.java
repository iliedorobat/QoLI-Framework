package app.java.commons.dimensions.health;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.common.CommonStats;
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
            workAccidents = Preparation.preparePerThousandInhabitant(CommonStats.population, initWorkAccidents);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("alcoholicRatio", alcoholicRatio);
        put("bmiObeseRatio", bmiObeseRatio);
        put("bmiOverweightRatio", bmiOverweightRatio);
        put("fruitsVegetablesRatio", fruitsVegetablesRatio);
        put("healthyLifeRatio", healthyLifeRatio);
        put("healthyLifeYears", healthyLifeYears);
        put("hospitalBeds", hospitalBeds);
        put("lifeExpectancy", lifeExpectancy);
        put("longHealthIssuesRatio", longHealthIssuesRatio);
        put("healthPersonnel", personnelTotal);
        put("physicalActivitiesRatio", physicalActivitiesRatio);
        put("smokersRatio", smokersRatio);
        put("unmetDentalRatio", unmetDentalRatio);
        put("unmetMedicalRatio", unmetMedicalRatio);
        put("workAccidents", workAccidents);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Alcoholic Ratio", Preparation.filterMap(initAlcoholicRatio));
            put("BMI Obese Ratio", Preparation.filterMap(initBmiObeseRatio));
            put("BMI Overweight Ratio", Preparation.filterMap(initBmiOverweightRatio));
            put("Fruits & Vegetables Ratio", Preparation.filterMap(initFruitsVegetablesRatio));
            put("Healthy Life Ratio", Preparation.filterMap(initHealthyLifeRatio));
            put("Healthy Life Years", Preparation.filterMap(initHealthyLifeYears));
            put("Hospital Beds", Preparation.filterMap(initHospitalBeds));
            put("Life Expectancy", Preparation.filterMap(initLifeExpectancy));
            put("Long Health Issues Ratio", Preparation.filterMap(initLongHealthIssuesRatio));
            put("Personnel Dentists", Preparation.filterMap(initPersonnelDentists));
            put("Personnel Doctors", Preparation.filterMap(initPersonnelDoctors));
            put("Personnel Nurses", Preparation.filterMap(initPersonnelNurses));
            put("Personnel Pharma", Preparation.filterMap(initPersonnelPharma));
            put("Personnel Therapists", Preparation.filterMap(initPersonnelTherapists));
            put("Physical Activities Ratio", Preparation.filterMap(initPhysicalActivitiesRatio));
            put("Smokers Ratio", Preparation.filterMap(initSmokersRatio));
            put("Unmet Dental Ratio", Preparation.filterMap(initUnmetDentalRatio));
            put("Unmet Medical Ratio", Preparation.filterMap(initUnmetMedicalRatio));
            put("Work Accidents", Preparation.filterMap(initWorkAccidents));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.ALCOHOLIC_RATIO, alcoholicRatio);
            put(IndicatorNames.FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatio);
            put(IndicatorNames.BMI_OVERWEIGHT_RATIO, bmiOverweightRatio);
            put(IndicatorNames.BMI_OBESE_RATIO, bmiObeseRatio);
            put(IndicatorNames.DENTISTS, personnelDentists);
            put(IndicatorNames.DOCTORS, personnelDoctors);
            put(IndicatorNames.NURSES, personnelNurses);
            put(IndicatorNames.PHARMACISTS, personnelPharmacists);
            put(IndicatorNames.PHYSIOTHERAPISTS, personnelPhysiotherapists);
            put(IndicatorNames.TOTAL_HEALTH_PERSONNEL, personnelTotal);
            put(IndicatorNames.HEALTHY_LIFE_RATIO, healthyLifeRatio);
            put(IndicatorNames.HEALTHY_LIFE_YEARS, healthyLifeYears);
            put(IndicatorNames.HOSPITAL_BEDS, hospitalBeds);
            put(IndicatorNames.LIFE_EXPECTANCY, lifeExpectancy);
            put(IndicatorNames.LONG_HEALTH_ISSUES_RATIO, longHealthIssuesRatio);
            put(IndicatorNames.PHYSICAL_ACTIVITIES_RATIO, physicalActivitiesRatio);
            put(IndicatorNames.SMOKERS_RATIO, smokersRatio);
            put(IndicatorNames.UNMET_DENTAL_RATIO, unmetDentalRatio);
            put(IndicatorNames.UNMET_MEDICAL_RATIO, unmetMedicalRatio);
            put(IndicatorNames.WORK_ACCIDENTS, workAccidents);
        }};

        Print.printChartData(args, indicators, HEALTH_FILE_NAME, EU28_MEMBERS, seriesType, direction);
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
