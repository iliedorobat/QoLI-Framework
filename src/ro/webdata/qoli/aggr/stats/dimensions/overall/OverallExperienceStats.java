package ro.webdata.qoli.aggr.stats.dimensions.overall;

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

import static ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceAggrParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceParams.*;

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

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO, Preparation.filterMap(initHappinessAlwaysRatio));
        put(HAPPINESS_MOST_TIME_RATIO, Preparation.filterMap(initHappinessMostOfTheTimeRatio));
        put(HIGH_SATISFACTION_RATIO, Preparation.filterMap(initHighSatisfactionRatio));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(HAPPINESS_RATIO, happinessRatio);
        put(HIGH_SATISFACTION_RATIO, highSatisfactionRatio);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO, happinessAlwaysRatio);
        put(HAPPINESS_MOST_TIME_RATIO, happinessMostOfTheTimeRatio);
        put(HIGH_SATISFACTION_RATIO, highSatisfactionRatio);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, OVERALL_EXPERIENCE, AGGR_PARAMS, AGGR_REVERSED_STATE, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, OVERALL_EXPERIENCE, IND_PARAMS, IND_REVERSED_STATE, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, OVERALL_EXPERIENCE, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, OVERALL_EXPERIENCE, targetYear, indStatus);
    }

    // Get the proportion of population who declared they are happy always/most of the time
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
