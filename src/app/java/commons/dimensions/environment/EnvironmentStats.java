package app.java.commons.dimensions.environment;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EnvironmentStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            NOISE_POLLUTION_RATIO = EnvironmentParams.getNoisePollutionParams(),
            PM2_5_POLLUTION_RATIO = EnvironmentParams.getAirPollutionParams(ParamsValues.AIRPOL.get("PM2_5")),
            PM10_POLLUTION_RATIO = EnvironmentParams.getAirPollutionParams(ParamsValues.AIRPOL.get("PM10")),
            POLLUTION_RATIO = EnvironmentParams.getPollutionParams(),
            WATER_SUPPLY_RATIO = EnvironmentParams.getWaterSupplyParams();

    private static final String
            airPollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.AIR_POLLUTION_RATIO + JSON_EXTENSION,
            noisePollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.NOISE_POLLUTION_RATIO + JSON_EXTENSION,
            pollutionRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.POLLUTION_RATIO + JSON_EXTENSION,
            waterSupplyRatioPath = FilePathConst.ENVIRONMENT_PATH + FileNameConst.WATER_SUPPLY_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initNoisePollutionRatio = Initializer.initConsolidatedMap(NOISE_POLLUTION_RATIO, noisePollutionRatioPath),
            initPm2_5PollutionRatio = Initializer.initConsolidatedMap(PM2_5_POLLUTION_RATIO, airPollutionRatioPath),
            initPm10PollutionRatio = Initializer.initConsolidatedMap(PM10_POLLUTION_RATIO, airPollutionRatioPath),
            initPollutionRatio = Initializer.initConsolidatedMap(POLLUTION_RATIO, pollutionRatioPath),
            initWaterSupplyRatio = Initializer.initConsolidatedMap(WATER_SUPPLY_RATIO, waterSupplyRatioPath);

    public static final Map<String, Number>
            noisePollutionRatio = Preparation.prepareData(initNoisePollutionRatio),
            pm2_5PollutionRatio = Preparation.prepareData(initPm2_5PollutionRatio),
            pm10PollutionRatio = Preparation.prepareData(initPm10PollutionRatio),
            pollutionRatio = Preparation.prepareData(initPollutionRatio),
            waterSupplyRatio = Preparation.prepareData(initWaterSupplyRatio);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        reversedNoisePollutionRatio = MathUtils.percentageReverseRatio(noisePollutionRatio, key),
                        reversedPm2_5PollutionRatio = MathUtils.percentageReverseRatio(pm2_5PollutionRatio, key),
                        reversedPm10PollutionRatio = MathUtils.percentageReverseRatio(pm10PollutionRatio, key),
                        reversedPollutionRatio = MathUtils.percentageReverseRatio(pollutionRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(reversedNoisePollutionRatio)
                        * MathUtils.percentageSafetyDouble(reversedPm2_5PollutionRatio)
                        * MathUtils.percentageSafetyDouble(reversedPm10PollutionRatio)
                        * MathUtils.percentageSafetyDouble(reversedPollutionRatio)
                        * MathUtils.percentageSafetyDouble(waterSupplyRatio, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(initAirPollutionRatio, true));
//        Print.print(initAirPollutionRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initNoisePollutionRatio));
            add(Preparation.filterMap(initPm2_5PollutionRatio));
            add(Preparation.filterMap(initPm10PollutionRatio));
            add(Preparation.filterMap(initPollutionRatio));
            add(Preparation.filterMap(initWaterSupplyRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType) {
        if (args.contains("--dimension=" + DimensionNames.ENVIRONMENT)) {
            if (args.contains("--indicator=" + IndicatorNames.NOISE_POLLUTION_RATIO))
                Print.printChartData(noisePollutionRatio, EU28_MEMBERS, seriesType, IndicatorNames.NOISE_POLLUTION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.PM_2_5_POLLUTION_RATIO))
                Print.printChartData(pm2_5PollutionRatio, EU28_MEMBERS, seriesType, IndicatorNames.PM_2_5_POLLUTION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.PM_10_POLLUTION_RATIO))
                Print.printChartData(pm10PollutionRatio, EU28_MEMBERS, seriesType, IndicatorNames.PM_10_POLLUTION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.POLLUTION_RATIO))
                Print.printChartData(pollutionRatio, EU28_MEMBERS, seriesType, IndicatorNames.POLLUTION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.WATER_SUPPLY_RATIO))
                Print.printChartData(waterSupplyRatio, EU28_MEMBERS, seriesType, IndicatorNames.WATER_SUPPLY_RATIO);
        }
    }
}
