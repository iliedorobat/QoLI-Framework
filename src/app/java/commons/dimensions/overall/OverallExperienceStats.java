package app.java.commons.dimensions.overall;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
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

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("happinessRatio", happinessRatio);
        put("highSatisfactionRatio", highSatisfactionRatio);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Happiness (Always) Ratio", Preparation.filterMap(initHappinessAlwaysRatio));
            put("Happiness (Most of the time) Ratio", Preparation.filterMap(initHappinessMostOfTheTimeRatio));
            put("High Satisfaction Ratio", Preparation.filterMap(initHighSatisfactionRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.HAPPINESS_ALWAYS_RATIO, happinessAlwaysRatio);
            put(IndicatorNames.HAPPINESS_MOST_OF_THE_TIME_RATIO, happinessMostOfTheTimeRatio);
            put(IndicatorNames.HIGH_SATISFACTION_RATIO, highSatisfactionRatio);
        }};

        Print.printChartData(args, indicators, OVERALL_EXPERIENCE_FILE_NAME, EU28_MEMBERS, seriesType, direction);
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
