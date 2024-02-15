package app.java.commons.dimensions.environment;

import app.java.commons.constants.Constants;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EnvironmentPaths {
    public static final String ENVIRONMENT_FILE_NAME = "environment";

    public static final String AIR_POLLUTION_RATIO_FILE_NAME = "airPollutionRatio";
    public static final String NOISE_POLLUTION_RATIO_FILE_NAME = "noisePollutionRatio";
    public static final String PM_2_5_POLLUTION_RATIO_FILE_NAME = "pm2_5PollutionRatio";
    public static final String PM_10_POLLUTION_RATIO_FILE_NAME = "pm10PollutionRatio";
    public static final String POLLUTION_RATIO_FILE_NAME = "pollutionRatio";
    public static final String WATER_SUPPLY_RATIO_FILE_NAME = "waterSupplyRatio";

    public static final String ENVIRONMENT_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, ENVIRONMENT_FILE_NAME);

    private static String generatePath(String fileName) {
        return ENVIRONMENT_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            AIR_POLLUTION_RATIO_PATH = generatePath(AIR_POLLUTION_RATIO_FILE_NAME),
            NOISE_POLLUTION_RATIO_PATH = generatePath(NOISE_POLLUTION_RATIO_FILE_NAME),
            POLLUTION_RATIO_PATH = generatePath(POLLUTION_RATIO_FILE_NAME),
            WATER_SUPPLY_RATIO_PATH = generatePath(WATER_SUPPLY_RATIO_FILE_NAME);
}
