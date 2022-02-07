package app.java.commons.dimesntions.health;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
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
            HEALTH_PERSONNEL = HealthParams.getHealthPersonnelParams(ParamsValues.ISCO08.get("doctors")), // FIXME: Get data not only for doctors
            HEALTHY_LIFE_RATIO = HealthParams.getHealthyLifeParams(),
            HEALTHY_LIFE_YEARS_FEMALE = HealthParams.getHealthyLifeYearsParams(ParamsValues.SEX.get("female")),
            HEALTHY_LIFE_YEARS_MALE = HealthParams.getHealthyLifeYearsParams(ParamsValues.SEX.get("male")),
            HOSPITAL_BEDS = HealthParams.getHospitalBedsParams(),
            LIFE_EXPECTANCY = HealthParams.getLifeExpectancyParams(),
            LONG_HEALTH_ISSUE_RATIO = HealthParams.getLongHealthIssuesParams(),
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
            initBmiOverweightRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OVERWEIGHT, bodyMassIndexPath),
            initBmiObeseRatio = Initializer.initConsolidatedMap(BODY_MASS_INDEX_OBESE, bodyMassIndexPath),
            initFruitsVegetablesRatio = Initializer.initConsolidatedMap(FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatioPath),
            initHealthPersonnel = Initializer.initConsolidatedMap(HEALTH_PERSONNEL, healthPersonnelPath),
            initHealthyLifeRatio = Initializer.initConsolidatedMap(HEALTHY_LIFE_RATIO, healthyLifeRatioPath),
            initHealthyLifeYearsFemale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_FEMALE, healthyLifeYearsPath),
            initHealthyLifeYearsMale = Initializer.initConsolidatedMap(HEALTHY_LIFE_YEARS_MALE, healthyLifeYearsPath),
            initHospitalBeds = Initializer.initConsolidatedMap(HOSPITAL_BEDS, hospitalBedsPath),
            initLifeExpectancy = Initializer.initConsolidatedMap(LIFE_EXPECTANCY, lifeExpectancyPath),
            initLongHealthIssuesRatio = Initializer.initConsolidatedMap(LONG_HEALTH_ISSUE_RATIO, longHealthIssuesRatioPath),
            initPhysicalActivitiesRatio = Initializer.initConsolidatedMap(PHYSICAL_ACTIVITIES_RATIO, physicalActivitiesRatioPath),
            initSmokersRatio = Initializer.initConsolidatedMap(SMOKERS_RATIO, smokersRatioPath),
            initUnmetDentalRatio = Initializer.initConsolidatedMap(UNMET_DENTAL_RATIO, unmetDentalRatioPath),
            initUnmetMedicalRatio = Initializer.initConsolidatedMap(UNMET_MEDICAL_RATIO, unmetMedicalRatioPath),
            initWorkAccidents = Initializer.initConsolidatedMap(WORK_ACCIDENTS, workAccidentsPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                alcoholicRatio = Preparation.prepareData(initAlcoholicRatio), // FIXME: no data
                bmiOverweightRatio = Preparation.prepareData(initBmiOverweightRatio), // FIXME: not used
                bmiObeseRatio = Preparation.prepareData(initBmiObeseRatio),
                fruitsVegetablesRatio = Preparation.prepareData(initFruitsVegetablesRatio),
                healthPersonnel = transformHundredThousandToTenThousand(initHealthPersonnel),
                healthyLifeRatio = Preparation.prepareData(initHealthyLifeRatio),
                healthyLifeYearsFemale = Preparation.prepareData(initHealthyLifeYearsFemale),
                healthyLifeYearsMale = Preparation.prepareData(initHealthyLifeYearsMale),
                hospitalBeds = transformHundredThousandToTenThousand(initHospitalBeds),
                lifeExpectancy = Preparation.prepareData(initLifeExpectancy),
                longHealthIssuesRatio = Preparation.prepareData(initLongHealthIssuesRatio),
                physicalActivitiesRatio = Preparation.prepareData(initPhysicalActivitiesRatio), // FIXME: no data
                smokersRatio = Preparation.prepareData(initSmokersRatio),
                unmetDentalRatio = Preparation.prepareData(initUnmetDentalRatio),
                unmetMedicalRatio = Preparation.prepareData(initUnmetMedicalRatio),
                workAccidents = Preparation.preparePerThousandInhabitant(initWorkAccidents);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        reversedBodyMassIndexObese = MathUtils.percentageReverseRatio(bmiObeseRatio, key),
                        reversedLongHealthIssueRatio = MathUtils.percentageReverseRatio(longHealthIssuesRatio, key),
                        reversedSmokersRatio = MathUtils.percentageReverseRatio(smokersRatio, key),
                        reversedUnmetDentalStatus = MathUtils.percentageReverseRatio(unmetDentalRatio, key),
                        reversedUnmetMedicalStatus = MathUtils.percentageReverseRatio(unmetMedicalRatio, key),
                        reversedWorkAccidents = MathUtils.percentageReverseRatio(workAccidents, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(fruitsVegetablesRatio, key)
                        * MathUtils.percentageSafetyDouble(lifeExpectancy, key)
                        * MathUtils.percentageSafetyDouble(healthPersonnel, key)
                        * MathUtils.percentageSafetyDouble(healthyLifeRatio, key)
                        * MathUtils.percentageSafetyDouble(hospitalBeds, key)
                        // TODO: gender gap healthy years (healthyLifeYearsFemale/healthyLifeYearsMale)
//                        * MathUtils.percentageSafetyDouble(healthyLifeYearsFemale, key)
//                        * MathUtils.percentageSafetyDouble(healthyLifeYearsMale, key)
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
        //TODO: initAlcoholicRatio, initBodyMassIndexOverweight and initPhysicalActivitiesRatio are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initBmiObeseRatio));
            add(Preparation.filterMap(initFruitsVegetablesRatio));
            add(Preparation.filterMap(initHealthPersonnel));
            add(Preparation.filterMap(initHealthyLifeRatio));
            add(Preparation.filterMap(initHealthyLifeYearsFemale));
            add(Preparation.filterMap(initHealthyLifeYearsMale));
            add(Preparation.filterMap(initHospitalBeds));
            add(Preparation.filterMap(initLifeExpectancy));
            add(Preparation.filterMap(initLongHealthIssuesRatio));
            add(Preparation.filterMap(initSmokersRatio));
            add(Preparation.filterMap(initUnmetDentalRatio));
            add(Preparation.filterMap(initUnmetMedicalRatio));
            add(Preparation.filterMap(initWorkAccidents));
        }};
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
