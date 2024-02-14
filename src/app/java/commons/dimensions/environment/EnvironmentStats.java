package app.java.commons.dimensions.environment;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.IndicatorNames;
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

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("noisePollutionRatio", noisePollutionRatio);
        put("pm2_5PollutionRatio", pm2_5PollutionRatio);
        put("pm10PollutionRatio", pm10PollutionRatio);
        put("pollutionRatio", pollutionRatio);
        put("waterSupplyRatio", waterSupplyRatio);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Noise Pollution Ratio", Preparation.filterMap(initNoisePollutionRatio));
            put("PM 2.5 Pollution Ratio", Preparation.filterMap(initPm2_5PollutionRatio));
            put("PM 10 Pollution Ratio", Preparation.filterMap(initPm10PollutionRatio));
            put("Pollution Ratio", Preparation.filterMap(initPollutionRatio));
            put("Water Supply Ratio", Preparation.filterMap(initWaterSupplyRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.NOISE_POLLUTION_RATIO, noisePollutionRatio);
            put(IndicatorNames.PM_2_5_POLLUTION_RATIO, pm2_5PollutionRatio);
            put(IndicatorNames.PM_10_POLLUTION_RATIO, pm10PollutionRatio);
            put(IndicatorNames.POLLUTION_RATIO, pollutionRatio);
            put(IndicatorNames.WATER_SUPPLY_RATIO, waterSupplyRatio);
        }};

        Print.printChartData(args, indicators, FilePathConst.ENVIRONMENT_DIR, EU28_MEMBERS, seriesType, direction);
    }
}
