package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.EnvironmentStatsDAO;

import java.util.Map;

public class EnvironmentStatsImpl implements EnvironmentStatsDAO {
    // The lists of queried values
    private static final String[]
            AIR_POLLUTION_RATIO = {"PM10"},
            NOISE_POLLUTION_RATIO = {"TOTAL", "TOTAL", "PC"},
            POLLUTION_RATIO = {"TOTAL", "TOTAL", "PC"},
            WATER_SUPPLY_RATIO = {"POP_PWS", "PC"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            airPollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.AIR_POLLUTION_RATIO + JSON_EXT,
            noisePollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.NOISE_POLLUTION_RATIO + JSON_EXT,
            pollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.POLLUTION_RATIO + JSON_EXT,
            waterSupplyRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.WATER_SUPPLY_RATIO + JSON_EXT;

    private static final Map<String, Number>
            airPollutionRatio = MeasureUtils.consolidateList(AIR_POLLUTION_RATIO, airPollutionRatioPath),
            noisePollutionRatio = MeasureUtils.consolidateList(NOISE_POLLUTION_RATIO, noisePollutionRatioPath),
            pollutionRatio = MeasureUtils.consolidateList(POLLUTION_RATIO, pollutionRatioPath),
            waterSupplyRatio = MeasureUtils.consolidateList(WATER_SUPPLY_RATIO, waterSupplyRatioPath);

    public void print() {
//        System.out.println(airPollutionRatio);
        MeasureUtils.print(airPollutionRatioPath);
    }
}
