package app.java.commons.dimensions.materialLiving;

import app.java.commons.constants.Constants;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class MaterialLivingPaths {
    public static final String LIVING_CONDITIONS_FILE_NAME = "livingConditions";

    public static final String DWELLING_ISSUES_RATIO_FILE_NAME = "dwellingIssuesRatio";
    public static final String END_MEET_INABILITY_RATIO_FILE_NAME = "endMeetInabilityRatio";
    public static final String END_MEET_INABILITY_D_RATIO_FILE_NAME = "endMeetInabilityDRatio";
    public static final String END_MEET_INABILITY_GD_RATIO_FILE_NAME = "endMeetInabilityGDRatio";
    public static final String FINANCIAL_SATISFACTION_FILE_NAME = "financialSatisfaction";
    public static final String GDP_PER_CAPITA_PPS_RATIO_FILE_NAME = "gdpPerCapitaPpsRatio";
    public static final String HIGH_INCOME_RATIO_FILE_NAME = "highIncomeRatio";
    public static final String INCOME_QUINTILE_RATIO_FILE_NAME = "incomeQuintileRatio";
    public static final String LACK_OF_BATHS_RATIO_FILE_NAME = "lackOfBathsRatio";
    public static final String LOW_WORK_INTENSITY_RATIO_FILE_NAME = "lowWorkIntensityRatio";
    public static final String MATERIAL_DEPRIVATION_RATIO_FILE_NAME = "materialDeprivationRatio";
    public static final String MEDIAN_INCOME_PPS_FILE_NAME = "medianIncome";
    public static final String OVER_OCCUPIED_RATIO_FILE_NAME = "overOccupiedRatio";
    public static final String POVERTY_RISK_RATIO_FILE_NAME = "povertyRiskRatio";
    public static final String UNDER_OCCUPIED_RATIO_FILE_NAME = "underOccupiedRatio";

    public static final String LIVING_CONDITIONS_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, LIVING_CONDITIONS_FILE_NAME);

    private static String generatePath(String fileName) {
        return LIVING_CONDITIONS_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            DWELLING_ISSUES_RATIO_PATH = generatePath(DWELLING_ISSUES_RATIO_FILE_NAME),
            END_MEET_INABILITY_RATIO_PATH = generatePath(END_MEET_INABILITY_RATIO_FILE_NAME),
            FINANCIAL_SATISFACTION_PATH = generatePath(FINANCIAL_SATISFACTION_FILE_NAME),
            GDP_PER_CAPITA_PPS_RATIO_PATH = generatePath(GDP_PER_CAPITA_PPS_RATIO_FILE_NAME),
            HIGH_INCOME_RATIO_PATH = generatePath(HIGH_INCOME_RATIO_FILE_NAME),
            INCOME_QUINTILE_RATIO_PATH = generatePath(INCOME_QUINTILE_RATIO_FILE_NAME),
            LACK_OF_BATHS_RATIO_PATH = generatePath(LACK_OF_BATHS_RATIO_FILE_NAME),
            LOW_WORK_INTENSITY_RATIO_PATH = generatePath(LOW_WORK_INTENSITY_RATIO_FILE_NAME),
            MATERIAL_DEPRIVATION_RATIO_PATH = generatePath(MATERIAL_DEPRIVATION_RATIO_FILE_NAME),
            MEDIAN_INCOME_PPS_PATH = generatePath(MEDIAN_INCOME_PPS_FILE_NAME),
            OVER_OCCUPIED_RATIO_PATH = generatePath(OVER_OCCUPIED_RATIO_FILE_NAME),
            POVERTY_RISK_RATIO_PATH = generatePath(POVERTY_RISK_RATIO_FILE_NAME),
            UNDER_OCCUPIED_RATIO_PATH = generatePath(UNDER_OCCUPIED_RATIO_FILE_NAME);
}
