package app.java.data.measurement.dao.impl;

import app.java.commons.*;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.dao.EnvironmentStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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
            initAirPollutionRatio = Initializer.initConsolidatedMap(AIR_POLLUTION_RATIO, airPollutionRatioPath),
            initNoisePollutionRatio = Initializer.initConsolidatedMap(NOISE_POLLUTION_RATIO, noisePollutionRatioPath),
            initPollutionRatio = Initializer.initConsolidatedMap(POLLUTION_RATIO, pollutionRatioPath),
            initWaterSupplyRatio = Initializer.initConsolidatedMap(WATER_SUPPLY_RATIO, waterSupplyRatioPath);

    public Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                airPollutionRatio = Preparation.prepareData(initAirPollutionRatio),  // no data
                noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
                pollutionRatio = Preparation.prepareData(initPollutionRatio),
                waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio); // no data

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedNoisePollutionRatio = MathUtils.percentageReverseRatio(noisePollutionRatio, key);
                double reversedPollutionRatio = MathUtils.percentageReverseRatio(pollutionRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(reversedNoisePollutionRatio)
                        * MathUtils.percentageSafetyDouble(reversedPollutionRatio);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(initAirPollutionRatio, true));
//        Print.print(initAirPollutionRatio, true);

        return consolidatedList;
    }

    public ArrayList<Map<String, Number>> getInitList() {
        //TODO: initAirPollutionRatio and initWaterSupplyRatio are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initNoisePollutionRatio));
            add(Preparation.filterMap(initPollutionRatio));
        }};
    }
}
