package ro.webdata.qoli.aggr.commons.dimensions.environment;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.Print;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;
import ro.webdata.qoli.aggr.commons.utils.MapUtils;
import ro.webdata.qoli.aggr.commons.utils.MathUtils;
import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(EnvironmentPaths.AIR_POLLUTION_Nh3_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionNh3Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_CH4_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionCh4Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_CO_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionCoRatio));
        put(EnvironmentPaths.AIR_POLLUTION_NMVOC_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionNmvocRatio));
        put(EnvironmentPaths.AIR_POLLUTION_NOX_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionNoxRatio));
        put(EnvironmentPaths.AIR_POLLUTION_PM_2_5_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionPm2_5Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_PM_10_RATIO_FILE_NAME, Preparation.filterMap(initAirPollutionPm10Ratio));
        put(EnvironmentPaths.NOISE_POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initNoisePollutionRatio));
        put(EnvironmentPaths.POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(initPollutionRatio));
        put(EnvironmentPaths.WATER_SUPPLY_RATIO_FILE_NAME, Preparation.filterMap(initWaterSupplyRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(EnvironmentPaths.AIR_POLLUTION_Nh3_RATIO_FILE_NAME, Preparation.filterMap(airPollutionNh3Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_CH4_RATIO_FILE_NAME, Preparation.filterMap(airPollutionCh4Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_CO_RATIO_FILE_NAME, Preparation.filterMap(airPollutionCoRatio));
        put(EnvironmentPaths.AIR_POLLUTION_NMVOC_RATIO_FILE_NAME, Preparation.filterMap(airPollutionNmvocRatio));
        put(EnvironmentPaths.AIR_POLLUTION_NOX_RATIO_FILE_NAME, Preparation.filterMap(airPollutionNoxRatio));
        put(EnvironmentPaths.AIR_POLLUTION_PM_2_5_RATIO_FILE_NAME, Preparation.filterMap(airPollutionPm2_5Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_PM_10_RATIO_FILE_NAME, Preparation.filterMap(airPollutionPm10Ratio));
        put(EnvironmentPaths.AIR_POLLUTION_RATIO_FILE_NAME, Preparation.filterMap(airPollutionRatio));
        put(EnvironmentPaths.NOISE_POLLUTION_RATIO_FILE_NAME, noisePollutionRatio);
        put(EnvironmentPaths.POLLUTION_RATIO_FILE_NAME, pollutionRatio);
        put(EnvironmentPaths.WATER_SUPPLY_RATIO_FILE_NAME, waterSupplyRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(waterSupplyRatio, key)
                        * MathUtils.percentageSafetyDouble(airPollutionRatio, key, true)
                        * MathUtils.percentageSafetyDouble(noisePollutionRatio, key, true)
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
        Print.printChartData(args, preparedIndicators, EnvironmentPaths.ENVIRONMENT_FILE_NAME, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, EnvironmentPaths.ENVIRONMENT_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Aggregate the "Pollution Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
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
