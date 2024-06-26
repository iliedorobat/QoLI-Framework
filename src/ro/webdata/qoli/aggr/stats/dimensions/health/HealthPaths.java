package ro.webdata.qoli.aggr.stats.dimensions.health;

import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.io.File;

public class HealthPaths {
    public static final String HEALTH_FILE_NAME = "health";

    public static final String BMI_FILE_NAME = "bodyMassIndex";
    public static final String DEPRESSIVE_RATIO_FILE_NAME = "depressiveRatio";
    public static final String HEALTH_PERSONNEL_FILE_NAME = "healthPersonnel";
    public static final String HEALTHY_LIFE_RATIO_FILE_NAME = "healthyLifeRatio";
    public static final String HEALTHY_LIFE_YEARS_FILE_NAME = "healthyLifeYears";
    public static final String HOSPITAL_BEDS_FILE_NAME = "hospitalBeds";
    public static final String LIFE_EXPECTANCY_FILE_NAME = "lifeExpectancy";
    public static final String LONG_HEALTH_ISSUES_RATIO_FILE_NAME = "longHealthIssuesRatio";
    public static final String NON_ALCOHOLIC_RATIO_FILE_NAME = "nonAlcoholicRatio";
    public static final String NON_FRUITS_VEGETABLES_RATIO_FILE_NAME = "nonFruitsVegetablesRatio";
    public static final String PHYSICAL_ACTIVITIES_RATIO_FILE_NAME = "physicalActivitiesRatio";
    public static final String SMOKERS_RATIO_FILE_NAME = "smokersRatio";
    public static final String UNMET_DENTAL_RATIO_FILE_NAME = "unmetDentalStatus";
    public static final String UNMET_MEDICAL_RATIO_FILE_NAME = "unmetMedicalStatus";
    public static final String WORK_ACCIDENTS_FILE_NAME = "workAccidents";

    public static final String HEALTH_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, HEALTH_FILE_NAME);

    private static String generatePath(String fileName) {
        return HEALTH_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }
    
    public static final String
            BMI_PATH = generatePath(BMI_FILE_NAME),
            DEPRESSIVE_RATIO_PATH = generatePath(DEPRESSIVE_RATIO_FILE_NAME),
            HEALTH_PERSONNEL_PATH = generatePath(HEALTH_PERSONNEL_FILE_NAME),
            HEALTHY_LIFE_RATIO_PATH = generatePath(HEALTHY_LIFE_RATIO_FILE_NAME),
            HEALTHY_LIFE_YEARS_PATH = generatePath(HEALTHY_LIFE_YEARS_FILE_NAME),
            HOSPITAL_BEDS_PATH = generatePath(HOSPITAL_BEDS_FILE_NAME),
            LIFE_EXPECTANCY_PATH = generatePath(LIFE_EXPECTANCY_FILE_NAME),
            LONG_HEALTH_ISSUES_RATIO_PATH = generatePath(LONG_HEALTH_ISSUES_RATIO_FILE_NAME),
            NON_ALCOHOLIC_RATIO_PATH = generatePath(NON_ALCOHOLIC_RATIO_FILE_NAME),
            NON_FRUITS_VEGETABLES_RATIO_PATH = generatePath(NON_FRUITS_VEGETABLES_RATIO_FILE_NAME),
            PHYSICAL_ACTIVITIES_RATIO_PATH = generatePath(PHYSICAL_ACTIVITIES_RATIO_FILE_NAME),
            SMOKERS_RATIO_PATH = generatePath(SMOKERS_RATIO_FILE_NAME),
            UNMET_DENTAL_RATIO_PATH = generatePath(UNMET_DENTAL_RATIO_FILE_NAME),
            UNMET_MEDICAL_RATIO_PATH = generatePath(UNMET_MEDICAL_RATIO_FILE_NAME),
            WORK_ACCIDENTS_PATH = generatePath(WORK_ACCIDENTS_FILE_NAME);
}
