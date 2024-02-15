package app.java.commons.dimensions.environment;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.environment.EnvironmentParams.*;
import static app.java.commons.dimensions.environment.EnvironmentPaths.*;

public class EnvironmentStats {
    private static final Map<String, Number>
            initNoisePollutionRatio = Initializer.initConsolidatedMap(NOISE_POLLUTION_RATIO_PARAMS, NOISE_POLLUTION_RATIO_PATH),
            initPm2_5PollutionRatio = Initializer.initConsolidatedMap(PM2_5_POLLUTION_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH),
            initPm10PollutionRatio = Initializer.initConsolidatedMap(PM10_POLLUTION_RATIO_PARAMS, AIR_POLLUTION_RATIO_PATH),
            initPollutionRatio = Initializer.initConsolidatedMap(POLLUTION_RATIO_PARAMS, POLLUTION_RATIO_PATH),
            initWaterSupplyRatio = Initializer.initConsolidatedMap(WATER_SUPPLY_RATIO_PARAMS, WATER_SUPPLY_RATIO_PATH);

    public static final Map<String, Number>
            noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
            pm2_5PollutionRatio = Preparation.prepareData(initPm2_5PollutionRatio),
            pm10PollutionRatio = Preparation.prepareData(initPm10PollutionRatio),
            pollutionRatio = Preparation.prepareData(initPollutionRatio),
            waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(NOISE_POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initNoisePollutionRatio));
        put(PM_2_5_POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initPm2_5PollutionRatio));
        put(PM_10_POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initPm10PollutionRatio));
        put(POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initPollutionRatio));
        put(WATER_SUPPLY_RATIO_FILE_NAME, Preparation.filterMap(initWaterSupplyRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(NOISE_POLLUTION_RATIO_FILE_NAME, noisePollutionRatio);
        put(PM_2_5_POLLUTION_RATIO_FILE_NAME, pm2_5PollutionRatio);
        put(PM_10_POLLUTION_RATIO_FILE_NAME, pm10PollutionRatio);
        put(POLLUTION_RATIO_FILE_NAME, pollutionRatio);
        put(WATER_SUPPLY_RATIO_FILE_NAME, waterSupplyRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(waterSupplyRatio, key)
                        * MathUtils.percentageSafetyDouble(noisePollutionRatio, key, true)
                        * MathUtils.percentageSafetyDouble(pm2_5PollutionRatio, key, true)
                        * MathUtils.percentageSafetyDouble(pm10PollutionRatio, key, true)
                        * MathUtils.percentageSafetyDouble(pollutionRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(initAirPollutionRatio, true));
//        Print.print(initAirPollutionRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, ENVIRONMENT_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }
}
