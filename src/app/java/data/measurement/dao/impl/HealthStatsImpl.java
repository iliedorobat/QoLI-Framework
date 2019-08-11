package app.java.data.measurement.dao.impl;

import app.java.commons.*;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.HealthStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.Map;
import java.util.TreeMap;

public class HealthStatsImpl implements HealthStatsDAO {
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
            UNMET_DENTAL_STATUS = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            UNMET_MEDICAL_STATUS = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            WORK_ACCIDENTS = {"NR", "TOTAL", "D_GE4"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            alcoholicRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO + JSON_EXT,
            bodyMassIndexPath = FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX + JSON_EXT,
            fruitsVegetablesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO + JSON_EXT,
            healthPersonnelPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL + JSON_EXT,
            healthyLifeRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO + JSON_EXT,
            healthyLifeYearsPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS + JSON_EXT,
            hospitalBedsPath = FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS + JSON_EXT,
            lifeExpectancyPath = FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY + JSON_EXT,
            longHealthIssueRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUE_RATIO + JSON_EXT,
            physicalActivitiesPath = FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES + JSON_EXT,
            smokersRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO + JSON_EXT,
            unmetDentalStatusPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_STATUS + JSON_EXT,
            unmetMedicalStatusPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_STATUS + JSON_EXT,
            workAccidentsPath = FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS + JSON_EXT;

    private static final Map<String, Number>
            initAlcoholicRatio = Initializer.initConsolidatedList(ALCOHOLIC_RATIO, alcoholicRatioPath),
            initBodyMassIndexOverweight = Initializer.initConsolidatedList(BODY_MASS_INDEX_OVERWEIGHT, bodyMassIndexPath),
            initBodyMassIndexObese = Initializer.initConsolidatedList(BODY_MASS_INDEX_OBESE, bodyMassIndexPath),
            initFruitsVegetablesRatio = Initializer.initConsolidatedList(FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatioPath),
            initHealthPersonnel = Initializer.initConsolidatedList(HEALTH_PERSONNEL, healthPersonnelPath),
            initHealthyLifeRatio = Initializer.initConsolidatedList(HEALTHY_LIFE_RATIO, healthyLifeRatioPath),
            initHealthyLifeYearsFemale = Initializer.initConsolidatedList(HEALTHY_LIFE_YEARS_FEMALE, healthyLifeYearsPath),
            initHealthyLifeYearsMale = Initializer.initConsolidatedList(HEALTHY_LIFE_YEARS_MALE, healthyLifeYearsPath),
            initHospitalBeds = Initializer.initConsolidatedList(HOSPITAL_BEDS, hospitalBedsPath),
            initLifeExpectancy = Initializer.initConsolidatedList(LIFE_EXPECTANCY, lifeExpectancyPath),
            initLongHealthIssueRatio = Initializer.initConsolidatedList(LONG_HEALTH_ISSUE_RATIO, longHealthIssueRatioPath),
            initPhysicalActivities = Initializer.initConsolidatedList(PHYSICAL_ACTIVITIES, physicalActivitiesPath),
            initSmokersRatio = Initializer.initConsolidatedList(SMOKERS_RATIO, smokersRatioPath),
            initUnmetDentalStatus = Initializer.initConsolidatedList(UNMET_DENTAL_STATUS, unmetDentalStatusPath),
            initUnmetMedicalStatus = Initializer.initConsolidatedList(UNMET_MEDICAL_STATUS, unmetMedicalStatusPath),
            initWorkAccidents = Initializer.initConsolidatedList(WORK_ACCIDENTS, workAccidentsPath);

    public Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                alcoholicRatio = Preparation.prepareData(initAlcoholicRatio), // no data
                bodyMassIndexOverweight = Preparation.prepareData(initBodyMassIndexOverweight), // not used
                bodyMassIndexObese = Preparation.prepareData(initBodyMassIndexObese),
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
                unmetDentalStatus = Preparation.prepareData(initUnmetDentalStatus),
                unmetMedicalStatus = Preparation.prepareData(initUnmetMedicalStatus),
                workAccidents = consolidateWorkAccidents();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedBodyMassIndexObese = MathUtils.percentageReverseRatio(bodyMassIndexObese, key),
                        reversedLongHealthIssueRatio = MathUtils.percentageReverseRatio(longHealthIssueRatio, key),
                        reversedSmokersRatio = MathUtils.percentageReverseRatio(smokersRatio, key),
                        reversedUnmetDentalStatus = MathUtils.percentageReverseRatio(unmetDentalStatus, key),
                        reversedUnmetMedicalStatus = MathUtils.percentageReverseRatio(unmetMedicalStatus, key),
                        personnel = generateTensThousand(healthPersonnel, key),
                        beds = generateTensThousand(hospitalBeds, key),
                        reversedWorkAccidents = MathUtils.percentageReverseRatio(workAccidents, key);

//                System.out.println(workAccidents);

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

    /**
     * Transform all of the values expressed as hundred thousand into tens thousand
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
    private static double generateTensThousand(Map<String, Number> map, String key) {
        return map.get(key).doubleValue() / 10;
    }
}
