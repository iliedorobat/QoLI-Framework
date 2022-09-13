package app.java.commons.dimesntions.health;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimesntions.common.CommonStats;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class HealthStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            ALCOHOLIC_RATIO = HealthParams.getAlcoholicParams(),
            BODY_MASS_INDEX_OVERWEIGHT = HealthParams.getBodyMassIndexParams(ParamsValues.BMI.get("overweight")),
            BODY_MASS_INDEX_OBESE = HealthParams.getBodyMassIndexParams(ParamsValues.BMI.get("obese")),
            FRUITS_VEGETABLES_RATIO = HealthParams.getFVParams(),
            HEALTHY_LIFE_RATIO = HealthParams.getHealthyLifeParams(),
            HEALTHY_LIFE_YEARS_FEMALE = HealthParams.getHealthyLifeYearsParams(ParamsValues.SEX.get("female")),
            HEALTHY_LIFE_YEARS_MALE = HealthParams.getHealthyLifeYearsParams(ParamsValues.SEX.get("male")),
            HOSPITAL_BEDS = HealthParams.getHospitalBedsParams(),
            LIFE_EXPECTANCY = HealthParams.getLifeExpectancyParams(),
            LONG_HEALTH_ISSUE_RATIO = HealthParams.getLongHealthIssuesParams(),
            PERSONNEL_DENTISTS = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("dentists")),
            PERSONNEL_DOCTORS = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("doctors")),
            PERSONNEL_NURSES = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("nurses")),
            PERSONNEL_PHARMA = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("pharmacists")),
            PERSONNEL_THERAPISTS = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("physiotherapists")),
            PHYSICAL_ACTIVITIES_RATIO = HealthParams.getPhysicalActivitiesParams(),
            SMOKERS_RATIO = HealthParams.getSmokersParams(),
            UNMET_DENTAL_RATIO = HealthParams.getUnmetDentalParams(),
            UNMET_MEDICAL_RATIO = HealthParams.getUnmetMedicalParams(),
            WORK_ACCIDENTS = HealthParams.getWorkAccidentsParams();

    private static final String
            alcoholicRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO + JSON_EXTENSION,
            bodyMassIndexPath = FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX + JSON_EXTENSION,
            fruitsVegetablesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO + JSON_EXTENSION,
            healthPersonnelPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL + JSON_EXTENSION,
            healthyLifeRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO + JSON_EXTENSION,
            healthyLifeYearsPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS + JSON_EXTENSION,
            hospitalBedsPath = FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS + JSON_EXTENSION,
            lifeExpectancyPath = FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY + JSON_EXTENSION,
            longHealthIssuesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUES_RATIO + JSON_EXTENSION,
            physicalActivitiesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES_RATIO + JSON_EXTENSION,
            smokersRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO + JSON_EXTENSION,
            unmetDentalRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_RATIO + JSON_EXTENSION,
            unmetMedicalRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_RATIO + JSON_EXTENSION,
            workAccidentsPath = FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS + JSON_EXTENSION;

    private static final Map<String, Number>
            initAlcoholicRatio = Initializer.initConsolidatedMap(ALCOHOLIC_RATIO, alcoholicRatioPath),
            initBmiObeseRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OBESE, bodyMassIndexPath),
            initBmiOverweightRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OVERWEIGHT, bodyMassIndexPath),
            initFruitsVegetablesRatio = Initializer.initConsolidatedMap(FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatioPath),
            initHealthyLifeRatio = Initializer.initConsolidatedMap(HEALTHY_LIFE_RATIO, healthyLifeRatioPath),
            initHealthyLifeYearsFemale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_FEMALE, healthyLifeYearsPath),
            initHealthyLifeYearsMale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_MALE, healthyLifeYearsPath),
            initHospitalBeds = Initializer.initConsolidatedMap(HOSPITAL_BEDS, hospitalBedsPath),
            initLifeExpectancy = Initializer.initConsolidatedMap(LIFE_EXPECTANCY, lifeExpectancyPath),
            initLongHealthIssuesRatio = Initializer.initConsolidatedMap(LONG_HEALTH_ISSUE_RATIO, longHealthIssuesRatioPath),
            initPersonnelDentists = Initializer.initConsolidatedMap(PERSONNEL_DENTISTS, healthPersonnelPath),
            initPersonnelDoctors = Initializer.initConsolidatedMap(PERSONNEL_DOCTORS, healthPersonnelPath),
            initPersonnelNurses = Initializer.initConsolidatedMap(PERSONNEL_NURSES, healthPersonnelPath),
            initPersonnelPharma = Initializer.initConsolidatedMap(PERSONNEL_PHARMA, healthPersonnelPath),
            initPersonnelTherapists = Initializer.initConsolidatedMap(PERSONNEL_THERAPISTS, healthPersonnelPath),
            initPhysicalActivitiesRatio = Initializer.initConsolidatedMap(PHYSICAL_ACTIVITIES_RATIO, physicalActivitiesRatioPath),
            initSmokersRatio = Initializer.initConsolidatedMap(SMOKERS_RATIO, smokersRatioPath),
            initUnmetDentalRatio = Initializer.initConsolidatedMap(UNMET_DENTAL_RATIO, unmetDentalRatioPath),
            initUnmetMedicalRatio = Initializer.initConsolidatedMap(UNMET_MEDICAL_RATIO, unmetMedicalRatioPath),
            initWorkAccidents = Initializer.initConsolidatedMap(WORK_ACCIDENTS, workAccidentsPath);

    public static final Map<String, Number>
            alcoholicRatio = Preparation.prepareData(initAlcoholicRatio),
            fruitsVegetablesRatio = Preparation.prepareData(initFruitsVegetablesRatio),

            bmiOverweightRatio = Preparation.prepareData(initBmiOverweightRatio),
            bmiObeseRatio = Preparation.prepareData(initBmiObeseRatio),
            compactBmiRatio = consolidateBmiRatio(),

            dentists = Preparation.prepareData(initPersonnelDentists),
            doctors = Preparation.prepareData(initPersonnelDoctors),
            nurses = Preparation.prepareData(initPersonnelNurses),
            pharmacists = Preparation.prepareData(initPersonnelPharma),
            physiotherapists = Preparation.prepareData(initPersonnelTherapists),
            compactHealthPersonnel = consolidatePersonnelRatio(),

            healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
            healthyLifeYearsFemale = Preparation.prepareData(initHealthyLifeYearsFemale),
            healthyLifeYearsMale = Preparation.prepareData(initHealthyLifeYearsMale),
            compactHealthyLifeGenderGap = consolidateHealthyLifeGenderGap(),

            hospitalBeds = transformHundredThousandToTenThousand(initHospitalBeds),
            lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
            longHealthIssuesRatio = Preparation.prepareData(initLongHealthIssuesRatio),
            physicalActivitiesRatio = Preparation.prepareData(initPhysicalActivitiesRatio),
            smokersRatio = Preparation.prepareData(initSmokersRatio),
            unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
            unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
            workAccidents = Preparation.preparePerThousandInhabitant(CommonStats.population, initWorkAccidents);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        healthyLifeGaps = - compactHealthyLifeGenderGap.get(key).doubleValue(),
                        reversedAlcoholicRatio = MathUtils.percentageReverseRatio(alcoholicRatio, key),
                        reversedBodyMassIndexObese = MathUtils.percentageReverseRatio(compactBmiRatio, key),
                        reversedLongHealthIssueRatio = MathUtils.percentageReverseRatio(longHealthIssuesRatio, key),
                        reversedSmokersRatio = MathUtils.percentageReverseRatio(smokersRatio, key),
                        reversedUnmetDentalStatus = MathUtils.percentageReverseRatio(unmetDentalRatio, key),
                        reversedUnmetMedicalStatus = MathUtils.percentageReverseRatio(unmetMedicalRatio, key),
                        reversedWorkAccidents = MathUtils.percentageReverseRatio(workAccidents, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(fruitsVegetablesRatio, key)
                        * MathUtils.percentageSafetyDouble(lifeExpectancy, key)
                        * MathUtils.percentageSafetyDouble(compactHealthPersonnel, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeRatio, key)
                        * MathUtils.percentageSafetyDouble(hospitalBeds, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeGaps)
                        * MathUtils.percentageSafetyDouble(physicalActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedAlcoholicRatio)
                        * MathUtils.percentageSafetyDouble(reversedBodyMassIndexObese)
                        * MathUtils.percentageSafetyDouble(reversedLongHealthIssueRatio)
                        * MathUtils.percentageSafetyDouble(reversedSmokersRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnmetDentalStatus)
                        * MathUtils.percentageSafetyDouble(reversedUnmetMedicalStatus)
                        * MathUtils.percentageSafetyDouble(reversedWorkAccidents);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(workAccidents, true));
//        Print.print(consolidatedList, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initAlcoholicRatio));
            add(Preparation.filterMap(initBmiObeseRatio));
            add(Preparation.filterMap(initBmiOverweightRatio));
            add(Preparation.filterMap(initFruitsVegetablesRatio));
            add(Preparation.filterMap(initHealthyLifeRatio));
            add(Preparation.filterMap(initHealthyLifeYearsFemale));
            add(Preparation.filterMap(initHealthyLifeYearsMale));
            add(Preparation.filterMap(initHospitalBeds));
            add(Preparation.filterMap(initLifeExpectancy));
            add(Preparation.filterMap(initLongHealthIssuesRatio));
            add(Preparation.filterMap(initPersonnelDentists));
            add(Preparation.filterMap(initPersonnelDoctors));
            add(Preparation.filterMap(initPersonnelNurses));
            add(Preparation.filterMap(initPersonnelPharma));
            add(Preparation.filterMap(initPersonnelTherapists));
            add(Preparation.filterMap(initPhysicalActivitiesRatio));
            add(Preparation.filterMap(initSmokersRatio));
            add(Preparation.filterMap(initUnmetDentalRatio));
            add(Preparation.filterMap(initUnmetMedicalRatio));
            add(Preparation.filterMap(initWorkAccidents));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType) {
        if (args.contains("--dimension=" + DimensionNames.HEALTH)) {
            if (args.contains("--indicator=" + IndicatorNames.ALCOHOLIC_RATIO))
                Print.printChartData(alcoholicRatio, EU28_MEMBERS, seriesType, IndicatorNames.ALCOHOLIC_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.FRUITS_VEGETABLES_RATIO))
                Print.printChartData(fruitsVegetablesRatio, EU28_MEMBERS, seriesType, IndicatorNames.FRUITS_VEGETABLES_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.BMI_OVERWEIGHT_RATIO))
                Print.printChartData(bmiOverweightRatio, EU28_MEMBERS, seriesType, IndicatorNames.BMI_OVERWEIGHT_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.BMI_OBESE_RATIO))
                Print.printChartData(bmiObeseRatio, EU28_MEMBERS, seriesType, IndicatorNames.BMI_OBESE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_BMI_RATIO))
                Print.printChartData(compactBmiRatio, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_BMI_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.DENTISTS))
                Print.printChartData(dentists, EU28_MEMBERS, seriesType, IndicatorNames.DENTISTS);
            if (args.contains("--indicator=" + IndicatorNames.DOCTORS))
                Print.printChartData(doctors, EU28_MEMBERS, seriesType, IndicatorNames.DOCTORS);
            if (args.contains("--indicator=" + IndicatorNames.NURSES))
                Print.printChartData(nurses, EU28_MEMBERS, seriesType, IndicatorNames.NURSES);
            if (args.contains("--indicator=" + IndicatorNames.PHARMACISTS))
                Print.printChartData(pharmacists, EU28_MEMBERS, seriesType, IndicatorNames.PHARMACISTS);
            if (args.contains("--indicator=" + IndicatorNames.PHYSIOTHERAPISTS))
                Print.printChartData(physiotherapists, EU28_MEMBERS, seriesType, IndicatorNames.PHYSIOTHERAPISTS);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_HEALTH_PERSONNEL))
                Print.printChartData(compactHealthPersonnel, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_HEALTH_PERSONNEL);
            if (args.contains("--indicator=" + IndicatorNames.HEALTHY_LIFE_RATIO))
                Print.printChartData(healthyLifeRatio, EU28_MEMBERS, seriesType, IndicatorNames.HEALTHY_LIFE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.HEALTHY_LIFE_YEARS_FEMALE))
                Print.printChartData(healthyLifeYearsFemale, EU28_MEMBERS, seriesType, IndicatorNames.HEALTHY_LIFE_YEARS_FEMALE);
            if (args.contains("--indicator=" + IndicatorNames.HEALTHY_LIFE_YEARS_MALE))
                Print.printChartData(healthyLifeYearsMale, EU28_MEMBERS, seriesType, IndicatorNames.HEALTHY_LIFE_YEARS_MALE);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_HEALTHY_LIFE_GENDER_GAP))
                Print.printChartData(compactHealthyLifeGenderGap, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_HEALTHY_LIFE_GENDER_GAP);
            if (args.contains("--indicator=" + IndicatorNames.HOSPITAL_BEDS))
                Print.printChartData(hospitalBeds, EU28_MEMBERS, seriesType, IndicatorNames.HOSPITAL_BEDS);
            if (args.contains("--indicator=" + IndicatorNames.LIFE_EXPECTANCY))
                Print.printChartData(lifeExpectancy, EU28_MEMBERS, seriesType, IndicatorNames.LIFE_EXPECTANCY);
            if (args.contains("--indicator=" + IndicatorNames.LONG_HEALTH_ISSUES_RATIO))
                Print.printChartData(longHealthIssuesRatio, EU28_MEMBERS, seriesType, IndicatorNames.LONG_HEALTH_ISSUES_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.PHYSICAL_ACTIVITIES_RATIO))
                Print.printChartData(physicalActivitiesRatio, EU28_MEMBERS, seriesType, IndicatorNames.PHYSICAL_ACTIVITIES_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.SMOKERS_RATIO))
                Print.printChartData(smokersRatio, EU28_MEMBERS, seriesType, IndicatorNames.SMOKERS_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.UNMET_DENTAL_RATIO))
                Print.printChartData(unmetDentalRatio, EU28_MEMBERS, seriesType, IndicatorNames.UNMET_DENTAL_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.UNMET_MEDICAL_RATIO))
                Print.printChartData(unmetMedicalRatio, EU28_MEMBERS, seriesType, IndicatorNames.UNMET_MEDICAL_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.WORK_ACCIDENTS))
                Print.printChartData(workAccidents, EU28_MEMBERS, seriesType, IndicatorNames.WORK_ACCIDENTS);
        }
    }

    /**
     * Aggregate the population trust ratios into a single index (the average)
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateBmiRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * bmiOverweightRatio.get(key).doubleValue()
                        * bmiObeseRatio.get(key).doubleValue();

                Number value = MathUtils.getSquareValue(product, 2);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Generate the healthy life years gender gap (male HLY ratio - female HLY ratio)
     *
     * @return An ordered map with prepared data
     */
    // TODO: create a generic method (see consolidateEmploymentGenderGap)
    private static Map<String, Number> consolidateHealthyLifeGenderGap() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (String code : EU28_MEMBERS) {
            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double femaleRatio = healthyLifeYearsFemale.get(key).doubleValue();
                double maleRatio = healthyLifeYearsMale.get(key).doubleValue();
                Number genderGap = maleRatio - femaleRatio;
                preparedMap.put(key, genderGap);
            }
        }

        return preparedMap;
    }

    private static Map<String, Number> consolidatePersonnelRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                if (dentists.get(key) == null) {
                    System.out.println();
                }

                double sum = 0
                        + dentists.get(key).doubleValue()
                        + doctors.get(key).doubleValue()
                        + nurses.get(key).doubleValue()
                        + pharmacists.get(key).doubleValue()
                        + physiotherapists.get(key).doubleValue();

                Number value = MathUtils.generatePerHundredThousandInhabitants(CommonStats.population, key, sum);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Transform all values per hundred thousand inhabitants into values per ten thousand inhabitants
     *
     * @param initMap The initialized map (see Initializer.initMap)
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> transformHundredThousandToTenThousand(Map<String, Number> initMap) {
        Map<String, Number> generatedList = new TreeMap<>(new MapOrder());
        Map<String, Number> preparedMap = Preparation.prepareData(initMap);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                Number value = preparedMap.get(key).doubleValue() / 10;
                generatedList.put(key, value);
            }
        }

        return generatedList;
    }
}
