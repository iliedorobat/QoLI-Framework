package ro.webdata.qoli.aggr.stats.dimensions.environment;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentAggrParams.*;

public class EnvironmentStats {
    private static final Map<String, Number>
            // Intermediate data which will be grouped into a single indicator
            initAirPollutionNh3Ratio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_NH3_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionCh4Ratio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_CH4_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionCoRatio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_CO_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionNmvocRatio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_NMVOC_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionNoxRatio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_NOX_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionPm2_5Ratio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_PM_2_5_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),
            initAirPollutionPm10Ratio = Initializer.initConsolidatedMap(EnvironmentParams.AIR_POLLUTION_PM_10_RATIO_PARAMS, EnvironmentPaths.AIR_POLLUTION_RATIO_PATH),

            initNoisePollutionRatio = Initializer.initConsolidatedMap(EnvironmentParams.NOISE_POLLUTION_RATIO_PARAMS, EnvironmentPaths.NOISE_POLLUTION_RATIO_PATH),
            initPollutionRatio = Initializer.initConsolidatedMap(EnvironmentParams.POLLUTION_RATIO_PARAMS, EnvironmentPaths.POLLUTION_RATIO_PATH),
            initWaterSupplyRatio = Initializer.initConsolidatedMap(EnvironmentParams.WATER_SUPPLY_RATIO_PARAMS, EnvironmentPaths.WATER_SUPPLY_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate airPollutionRatio
            airPollutionNh3Ratio = Preparation.prepareData(initAirPollutionNh3Ratio),
            airPollutionCh4Ratio = Preparation.prepareData(initAirPollutionCh4Ratio),
            airPollutionCoRatio = Preparation.prepareData(initAirPollutionCoRatio),
            airPollutionNmvocRatio = Preparation.prepareData(initAirPollutionNmvocRatio),
            airPollutionNoxRatio = Preparation.prepareData(initAirPollutionNoxRatio),
            airPollutionPm2_5Ratio = Preparation.prepareData(initAirPollutionPm2_5Ratio),
            airPollutionPm10Ratio = Preparation.prepareData(initAirPollutionPm10Ratio),

            airPollutionRatio = prepareAirPollutionRatio(),
            noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
            pollutionRatio = Preparation.prepareData(initPollutionRatio),
            waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio);

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, Preparation.filterMap(initAirPollutionNh3Ratio));
        put(AIR_POLLUTION_CH_4_RATIO, Preparation.filterMap(initAirPollutionCh4Ratio));
        put(AIR_POLLUTION_CO_RATIO, Preparation.filterMap(initAirPollutionCoRatio));
        put(AIR_POLLUTION_NMVOC_RATIO, Preparation.filterMap(initAirPollutionNmvocRatio));
        put(AIR_POLLUTION_NOX_RATIO, Preparation.filterMap(initAirPollutionNoxRatio));
        put(AIR_POLLUTION_PM_2_5_RATIO, Preparation.filterMap(initAirPollutionPm2_5Ratio));
        put(AIR_POLLUTION_PM_10_RATIO, Preparation.filterMap(initAirPollutionPm10Ratio));
        put(NOISE_POLLUTION_RATIO, Preparation.filterMap(initNoisePollutionRatio));
        put(POLLUTION_RATIO, Preparation.filterMap(initPollutionRatio));
        put(WATER_SUPPLY_RATIO, Preparation.filterMap(initWaterSupplyRatio));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, airPollutionRatio);
        put(NOISE_POLLUTION_RATIO, noisePollutionRatio);
        put(POLLUTION_RATIO, pollutionRatio);
        put(WATER_SUPPLY_RATIO, waterSupplyRatio);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, airPollutionNh3Ratio);
        put(AIR_POLLUTION_CH_4_RATIO, airPollutionCh4Ratio);
        put(AIR_POLLUTION_CO_RATIO, airPollutionCoRatio);
        put(AIR_POLLUTION_NMVOC_RATIO, airPollutionNmvocRatio);
        put(AIR_POLLUTION_NOX_RATIO, airPollutionNoxRatio);
        put(AIR_POLLUTION_PM_2_5_RATIO, airPollutionPm2_5Ratio);
        put(AIR_POLLUTION_PM_10_RATIO, airPollutionPm10Ratio);
        put(NOISE_POLLUTION_RATIO, noisePollutionRatio);
        put(POLLUTION_RATIO, pollutionRatio);
        put(WATER_SUPPLY_RATIO, waterSupplyRatio);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, ENVIRONMENT, AGGR_PARAMS, AGGR_REVERSED_STATES, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, ENVIRONMENT, IND_PARAMS, IND_REVERSED_STATES, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, ENVIRONMENT, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, ENVIRONMENT, targetYear, indStatus);
    }

    // Aggregate the "Pollution Ratios" into a single ratio
    private static Map<String, Number> prepareAirPollutionRatio() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueCh4 = airPollutionCh4Ratio.get(key).doubleValue();
                double valueCo = airPollutionCoRatio.get(key).doubleValue();
                double valueNmvoc = airPollutionNmvocRatio.get(key).doubleValue();
                double valueNh3 = airPollutionNh3Ratio.get(key).doubleValue();
                double valueNox = airPollutionNoxRatio.get(key).doubleValue();
                double valuePm2_5 = airPollutionPm2_5Ratio.get(key).doubleValue();
                double valuePm10 = airPollutionPm10Ratio.get(key).doubleValue();

                double product = 1
                        * valueCh4
                        * valueCo
                        * valueNmvoc
                        * valueNh3
                        * valueNox
                        * valuePm2_5
                        * valuePm10;

                Number value = Math.pow(product, 1.0/7);
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
