package ro.webdata.qoli.aggr.commons.dimensions.overall;

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

import static ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceParams.*;

public class OverallExperienceStats {
    private static final Map<String, Number>
            initHappinessAlwaysRatio = Initializer.initConsolidatedMap(HAPPINESS_ALWAYS_RATIO_PARAMS, OverallExperiencePaths.HAPPINESS_RATIO_PATH),
            initHappinessMostOfTheTimeRatio = Initializer.initConsolidatedMap(HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS, OverallExperiencePaths.HAPPINESS_RATIO_PATH),
            initHighSatisfactionRatio = Initializer.initConsolidatedMap(HIGH_SATISFACTION_RATIO_PARAMS, OverallExperiencePaths.HIGH_SATISFACTION_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate date used to calculate happinessRatio
            happinessAlwaysRatio = Preparation.prepareData(initHappinessAlwaysRatio),
            happinessMostOfTheTimeRatio = Preparation.prepareData(initHappinessMostOfTheTimeRatio),

            happinessRatio = prepareHappinessRatio(),
            highSatisfactionRatio = Preparation.prepareData(initHighSatisfactionRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(OverallExperiencePaths.HAPPINESS_ALWAYS_RATIO_FILE_NAME, Preparation.filterMap(initHappinessAlwaysRatio));
        put(OverallExperiencePaths.HAPPINESS_MOST_OF_THE_TIME_RATIO_FILE_NAME, Preparation.filterMap(initHappinessMostOfTheTimeRatio));
        put(OverallExperiencePaths.HIGH_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initHighSatisfactionRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(OverallExperiencePaths.HAPPINESS_RATIO_FILE_NAME, happinessRatio);
        put(OverallExperiencePaths.HAPPINESS_ALWAYS_RATIO_FILE_NAME, happinessAlwaysRatio);
        put(OverallExperiencePaths.HAPPINESS_MOST_OF_THE_TIME_RATIO_FILE_NAME, happinessMostOfTheTimeRatio);
        put(OverallExperiencePaths.HIGH_SATISFACTION_RATIO_FILE_NAME, highSatisfactionRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(happinessRatio, key)
                        * MathUtils.percentageSafetyDouble(highSatisfactionRatio, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, targetYear, indStatus);
    }

    private static Map<String, Number> prepareHappinessRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double alwaysHappy = happinessAlwaysRatio.get(key).doubleValue();
                double mostOfTheTimeHappy = happinessMostOfTheTimeRatio.get(key).doubleValue();

                Number value = alwaysHappy + mostOfTheTimeHappy;
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
