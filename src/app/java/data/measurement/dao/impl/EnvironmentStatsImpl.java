package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.EnvironmentStatsDAO;

public class EnvironmentStatsImpl implements EnvironmentStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String
            airPollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.AIR_POLLUTION_RATIO + JSON_EXT,
            noisePollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.NOISE_POLLUTION_RATIO + JSON_EXT,
            pollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.POLLUTION_RATIO + JSON_EXT,
            waterSupplyRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.WATER_SUPPLY_RATIO + JSON_EXT;
}
