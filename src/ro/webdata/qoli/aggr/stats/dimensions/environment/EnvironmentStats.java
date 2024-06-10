package ro.webdata.qoli.aggr.stats.dimensions.environment;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.*;

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

            // Aggregate the "Pollution Ratios" into a single ratio
            airPollutionRatio = StatsUtils.calculateGeometricMean(
                    new ArrayList<>() {{
                        add(airPollutionCh4Ratio);
                        add(airPollutionCoRatio);
                        add(airPollutionNmvocRatio);
                        add(airPollutionNh3Ratio);
                        add(airPollutionNoxRatio);
                        add(airPollutionPm2_5Ratio);
                        add(airPollutionPm10Ratio);
                    }}
            ),
            noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
            pollutionRatio = Preparation.prepareData(initPollutionRatio),
            waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
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

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, airPollutionNh3Ratio);
        put(AIR_POLLUTION_CH_4_RATIO, airPollutionCh4Ratio);
        put(AIR_POLLUTION_CO_RATIO, airPollutionCoRatio);
        put(AIR_POLLUTION_NMVOC_RATIO, airPollutionNmvocRatio);
        put(AIR_POLLUTION_NOX_RATIO, airPollutionNoxRatio);
        put(AIR_POLLUTION_PM_2_5_RATIO, airPollutionPm2_5Ratio);
        put(AIR_POLLUTION_PM_10_RATIO, airPollutionPm10Ratio);
        put(AIR_POLLUTION_RATIO, airPollutionRatio);
        put(NOISE_POLLUTION_RATIO, noisePollutionRatio);
        put(POLLUTION_RATIO, pollutionRatio);
        put(WATER_SUPPLY_RATIO, waterSupplyRatio);
    }};

    public static Map<String, Number> generateStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, ENVIRONMENT, ALLOWED_PARAMS, IS_REVERSED, preparedIndicators);
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, ENVIRONMENT, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, ENVIRONMENT, targetYear, indStatus);
    }
}
