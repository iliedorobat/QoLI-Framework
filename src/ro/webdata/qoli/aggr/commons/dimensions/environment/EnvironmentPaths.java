package ro.webdata.qoli.aggr.commons.dimensions.environment;

import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.io.File;

public class EnvironmentPaths {
    public static final String ENVIRONMENT_FILE_NAME = "environment";

    public static final String AIR_POLLUTION_Nh3_RATIO_FILE_NAME = "airPollutionNh3Ratio";
    public static final String AIR_POLLUTION_CH4_RATIO_FILE_NAME = "airPollutionCh4Ratio";
    public static final String AIR_POLLUTION_CO_RATIO_FILE_NAME = "airPollutionCoRatio";
    public static final String AIR_POLLUTION_NMVOC_RATIO_FILE_NAME = "airPollutionNmvocRatio";
    public static final String AIR_POLLUTION_NOX_RATIO_FILE_NAME = "airPollutionNoxRatio";
    public static final String AIR_POLLUTION_PM_2_5_RATIO_FILE_NAME = "airPollutionPm2_5Ratio";
    public static final String AIR_POLLUTION_PM_10_RATIO_FILE_NAME = "airPollutionPm10Ratio";
    public static final String AIR_POLLUTION_RATIO_FILE_NAME = "airPollutionRatio";
    public static final String NOISE_POLLUTION_RATIO_FILE_NAME = "noisePollutionRatio";
    public static final String POLLUTION_RATIO_FILE_NAME = "pollutionRatio";
    public static final String WATER_SUPPLY_RATIO_FILE_NAME = "waterSupplyRatio";

    public static final String ENVIRONMENT_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, ENVIRONMENT_FILE_NAME);

    private static String generatePath(String fileName) {
        return ENVIRONMENT_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            AIR_POLLUTION_RATIO_PATH = generatePath(AIR_POLLUTION_RATIO_FILE_NAME),
            NOISE_POLLUTION_RATIO_PATH = generatePath(NOISE_POLLUTION_RATIO_FILE_NAME),
            POLLUTION_RATIO_PATH = generatePath(POLLUTION_RATIO_FILE_NAME),
            WATER_SUPPLY_RATIO_PATH = generatePath(WATER_SUPPLY_RATIO_FILE_NAME);
}
