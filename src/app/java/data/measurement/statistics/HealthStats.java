package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HealthStats {
    // The lists of queried values
    private static final String[]
            ALCOHOLIC_RATIO = {"PC", "DAY", "T", "TOTAL", "NAT"},
            BODY_MASS_INDEX_OVERWEIGHT = {"PC", "BMI_GE25", "TOTAL", "T", "TOTAL"},
            BODY_MASS_INDEX_OBESE = {"PC", "BMI_GE30", "TOTAL", "T", "TOTAL"},
            FRUITS_VEGETABLES_RATIO = {"PC", "GE5", "TOTAL", "T", "TOTAL"},
            HEALTH_PERSONNEL = {"P_HTHAB", "OC221"},
            HEALTHY_LIFE_RATIO = {"PC", "TOTAL", "Y_GE16", "T", "VG_G"},
            HEALTHY_LIFE_YEARS_FEMALE = {"F_0_DFLE"},
            HEALTHY_LIFE_YEARS_MALE = {"M_0_DFLE"},
            HOSPITAL_BEDS = {"P_HTHAB", "HBEDT"},
            LIFE_EXPECTANCY = {"YR", "T", "Y_LT1"},
            LONG_HEALTH_ISSUE_RATIO = {"PC", "TOTAL", "Y_GE16", "T"},
            PHYSICAL_ACTIVITIES = {"PC", "MV_AERO_MSC", "TOTAL", "T", "TOTAL"},
            SMOKERS_RATIO = {"PC", "TOTAL", "TOTAL", "T", "TOTAL"},
            UNMET_DENTAL_RATIO = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            UNMET_MEDICAL_RATIO = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            WORK_ACCIDENTS = {"NR", "TOTAL", "D_GE4"};

    private static final String
            alcoholicRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO + Constants.JSON_EXTENSION,
            bodyMassIndexPath = FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX + Constants.JSON_EXTENSION,
            fruitsVegetablesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO + Constants.JSON_EXTENSION,
            healthPersonnelPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL + Constants.JSON_EXTENSION,
            healthyLifeRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO + Constants.JSON_EXTENSION,
            healthyLifeYearsPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS + Constants.JSON_EXTENSION,
            hospitalBedsPath = FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS + Constants.JSON_EXTENSION,
            lifeExpectancyPath = FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY + Constants.JSON_EXTENSION,
            longHealthIssueRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUE_RATIO + Constants.JSON_EXTENSION,
            physicalActivitiesPath = FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES + Constants.JSON_EXTENSION,
            smokersRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO + Constants.JSON_EXTENSION,
            unmetDentalRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_RATIO + Constants.JSON_EXTENSION,
            unmetMedicalRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_RATIO + Constants.JSON_EXTENSION,
            workAccidentsPath = FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS + Constants.JSON_EXTENSION;

    private static final Map<String, Number>
            initAlcoholicRatio = Initializer.initConsolidatedMap(ALCOHOLIC_RATIO, alcoholicRatioPath),
            initBmiOverweightRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OVERWEIGHT, bodyMassIndexPath),
            initBmiObeseRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OBESE, bodyMassIndexPath),
            initFruitsVegetablesRatio = Initializer.initConsolidatedMap(FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatioPath),
            initHealthPersonnel = Initializer.initConsolidatedMap(HEALTH_PERSONNEL, healthPersonnelPath),
            initHealthyLifeRatio = Initializer.initConsolidatedMap(HEALTHY_LIFE_RATIO, healthyLifeRatioPath),
            initHealthyLifeYearsFemale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_FEMALE, healthyLifeYearsPath),
            initHealthyLifeYearsMale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_MALE, healthyLifeYearsPath),
            initHospitalBeds = Initializer.initConsolidatedMap(HOSPITAL_BEDS, hospitalBedsPath),
            initLifeExpectancy = Initializer.initConsolidatedMap(LIFE_EXPECTANCY, lifeExpectancyPath),
            initLongHealthIssueRatio = Initializer.initConsolidatedMap(LONG_HEALTH_ISSUE_RATIO, longHealthIssueRatioPath),
            initPhysicalActivities = Initializer.initConsolidatedMap(PHYSICAL_ACTIVITIES, physicalActivitiesPath),
            initSmokersRatio = Initializer.initConsolidatedMap(SMOKERS_RATIO, smokersRatioPath),
            initUnmetDentalRatio = Initializer.initConsolidatedMap(UNMET_DENTAL_RATIO, unmetDentalRatioPath),
            initUnmetMedicalRatio = Initializer.initConsolidatedMap(UNMET_MEDICAL_RATIO, unmetMedicalRatioPath),
            initWorkAccidents = Initializer.initConsolidatedMap(WORK_ACCIDENTS, workAccidentsPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                alcoholicRatio = Preparation.prepareData(initAlcoholicRatio), // no data
                bmiOverweightRatio = Preparation.prepareData(initBmiOverweightRatio), // not used
                bmiObeseRatio = Preparation.prepareData(initBmiObeseRatio),
                fruitsVegetablesRatio = Preparation.prepareData(initFruitsVegetablesRatio),
                healthPersonnel = Preparation.prepareData(initHealthPersonnel),
                healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
                healthyLifeYearsFemale = Preparation.prepareData(initHealthyLifeYearsFemale),
                healthyLifeYearsMale = Preparation.prepareData(initHealthyLifeYearsMale),
                hospitalBeds = Preparation.prepareData(initHospitalBeds),
                lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
                longHealthIssueRatio = Preparation.prepareData(initLongHealthIssueRatio),
                physicalActivities = Preparation.prepareData(initPhysicalActivities), // no data
                smokersRatio = Preparation.prepareData(initSmokersRatio),
                unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
                unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
                workAccidents = consolidateWorkAccidents();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedBodyMassIndexObese = MathUtils.percentageReverseRatio(bmiObeseRatio, key),
                        reversedLongHealthIssueRatio = MathUtils.percentageReverseRatio(longHealthIssueRatio, key),
                        reversedSmokersRatio = MathUtils.percentageReverseRatio(smokersRatio, key),
                        reversedUnmetDentalStatus = MathUtils.percentageReverseRatio(unmetDentalRatio, key),
                        reversedUnmetMedicalStatus = MathUtils.percentageReverseRatio(unmetMedicalRatio, key),
                        personnel = generateTensThousand(healthPersonnel, key),
                        beds = generateTensThousand(hospitalBeds, key),
                        reversedWorkAccidents = MathUtils.percentageReverseRatio(workAccidents, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(reversedBodyMassIndexObese)
                        * MathUtils.percentageSafetyDouble(fruitsVegetablesRatio, key)
                        * MathUtils.percentageSafetyDouble(personnel)
                        * MathUtils.percentageSafetyDouble(healthyLifeRatio, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeYearsFemale, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeYearsMale, key)
                        * MathUtils.percentageSafetyDouble(beds)
                        * MathUtils.percentageSafetyDouble(lifeExpectancy, key)
                        * MathUtils.percentageSafetyDouble(reversedLongHealthIssueRatio)
                        * MathUtils.percentageSafetyDouble(reversedSmokersRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnmetDentalStatus)
                        * MathUtils.percentageSafetyDouble(reversedUnmetMedicalStatus)
                        * MathUtils.percentageSafetyDouble(reversedWorkAccidents);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(workAccidents, true));
//        Print.print(consolidatedList, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        //TODO: initAlcoholicRatio, initBodyMassIndexOverweight and initPhysicalActivities are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initBmiObeseRatio));
            add(Preparation.filterMap(initFruitsVegetablesRatio));
            add(Preparation.filterMap(initHealthPersonnel));
            add(Preparation.filterMap(initHealthyLifeRatio));
            add(Preparation.filterMap(initHealthyLifeYearsFemale));
            add(Preparation.filterMap(initHealthyLifeYearsMale));
            add(Preparation.filterMap(initHospitalBeds));
            add(Preparation.filterMap(initLifeExpectancy));
            add(Preparation.filterMap(initLongHealthIssueRatio));
            add(Preparation.filterMap(initSmokersRatio));
            add(Preparation.filterMap(initUnmetDentalRatio));
            add(Preparation.filterMap(initUnmetMedicalRatio));
            add(Preparation.filterMap(initWorkAccidents));
        }};
    }

    /**
     * Transform all of the values expressed as hundred thousand into ten thousand
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateWorkAccidents() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number> workAccidents = Preparation.prepareData(initWorkAccidents);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);
                Number value = MathUtils.generateThousandPerInhabitant(key, workAccidents.get(key));
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Transform a value expressed as hundred thousand into tens thousand
     *
     * @param map The related map
     * @param key The key
     * @return The tens thousand value
     */
    //TODO: rename to generatePerTenThousandInhabitants
    private static double generateTensThousand(Map<String, Number> map, String key) {
        return map.get(key).doubleValue() / 10;
    }
}
