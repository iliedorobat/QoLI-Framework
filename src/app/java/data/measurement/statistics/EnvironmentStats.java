package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EnvironmentStats {
    // The lists of queried values
    private static final String[]
            AIR_POLLUTION_RATIO = {"PM10"},
            NOISE_POLLUTION_RATIO = {"TOTAL", "TOTAL", "PC"},
            POLLUTION_RATIO = {"TOTAL", "TOTAL", "PC"},
            WATER_SUPPLY_RATIO = {"POP_PWS", "PC"};

    private static final String
            airPollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.AIR_POLLUTION_RATIO + JSON_EXTENSION,
            noisePollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.NOISE_POLLUTION_RATIO + JSON_EXTENSION,
            pollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.POLLUTION_RATIO + JSON_EXTENSION,
            waterSupplyRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.WATER_SUPPLY_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initAirPollutionRatio = Initializer.initConsolidatedMap(AIR_POLLUTION_RATIO, airPollutionRatioPath),
            initNoisePollutionRatio = Initializer.initConsolidatedMap(NOISE_POLLUTION_RATIO, noisePollutionRatioPath),
            initPollutionRatio = Initializer.initConsolidatedMap(POLLUTION_RATIO, pollutionRatioPath),
            initWaterSupplyRatio = Initializer.initConsolidatedMap(WATER_SUPPLY_RATIO, waterSupplyRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                airPollutionRatio = Preparation.prepareData(initAirPollutionRatio),  // no data
                noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
                pollutionRatio = Preparation.prepareData(initPollutionRatio),
                waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio); // no data

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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

//        Print.printVariation(StatsUtils.generateVariation(initAirPollutionRatio, true));
//        Print.print(initAirPollutionRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        //TODO: initAirPollutionRatio and initWaterSupplyRatio are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initNoisePollutionRatio));
            add(Preparation.filterMap(initPollutionRatio));
        }};
    }
}
