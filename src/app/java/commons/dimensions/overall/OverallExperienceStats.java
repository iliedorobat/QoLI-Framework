package app.java.commons.dimensions.overall;

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
import static app.java.commons.dimensions.overall.OverallExperienceParams.*;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.*;

public class OverallExperienceStats {
    private static final Map<String, Number>
            initHappinessAlwaysRatio = Initializer.initConsolidatedMap(HAPPINESS_ALWAYS_RATIO_PARAMS, HAPPINESS_RATIO_PATH),
            initHappinessMostOfTheTimeRatio = Initializer.initConsolidatedMap(HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS, HAPPINESS_RATIO_PATH),
            initHighSatisfactionRatio = Initializer.initConsolidatedMap(HIGH_SATISFACTION_RATIO_PARAMS, HIGH_SATISFACTION_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate date used to calculate happinessRatio
            happinessAlwaysRatio = Preparation.prepareData(initHappinessAlwaysRatio),
            happinessMostOfTheTimeRatio = Preparation.prepareData(initHappinessMostOfTheTimeRatio),

            happinessRatio = prepareHappinessRatio(),
            highSatisfactionRatio = Preparation.prepareData(initHighSatisfactionRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO_FILE_NAME, Preparation.filterMap(initHappinessAlwaysRatio));
        put(HAPPINESS_MOST_OF_THE_TIME_RATIO_FILE_NAME, Preparation.filterMap(initHappinessMostOfTheTimeRatio));
        put(HIGH_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initHighSatisfactionRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(HAPPINESS_RATIO_FILE_NAME, happinessRatio);
        put(HAPPINESS_ALWAYS_RATIO_FILE_NAME, happinessAlwaysRatio);
        put(HAPPINESS_MOST_OF_THE_TIME_RATIO_FILE_NAME, happinessMostOfTheTimeRatio);
        put(HIGH_SATISFACTION_RATIO_FILE_NAME, highSatisfactionRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
        Print.printChartData(args, preparedIndicators, OVERALL_EXPERIENCE_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    private static Map<String, Number> prepareHappinessRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
