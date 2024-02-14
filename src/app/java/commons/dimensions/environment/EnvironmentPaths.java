package app.java.commons.dimensions.environment;

import app.java.commons.constants.FilePathConst;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EnvironmentPaths {
    public static final String AIR_POLLUTION_RATIO_FILE_NAME = "airPollutionRatio";
    public static final String NOISE_POLLUTION_RATIO_FILE_NAME = "noisePollutionRatio";
    public static final String POLLUTION_RATIO_FILE_NAME = "pollutionRatio";
    public static final String WATER_SUPPLY_RATIO_FILE_NAME = "waterSupplyRatio";

    private static String generatePath(String fileName) {
        return FilePathConst.ENVIRONMENT_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            AIR_POLLUTION_RATIO_PATH = generatePath(AIR_POLLUTION_RATIO_FILE_NAME),
            NOISE_POLLUTION_RATIO_PATH = generatePath(NOISE_POLLUTION_RATIO_FILE_NAME),
            POLLUTION_RATIO_PATH = generatePath(POLLUTION_RATIO_FILE_NAME),
            WATER_SUPPLY_RATIO_PATH = generatePath(WATER_SUPPLY_RATIO_FILE_NAME);
}
