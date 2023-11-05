package app.java.commons.dimensions.overall;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.overall.OverallExperienceParams.HAPPINESS_RATIO_PARAMS;
import static app.java.commons.dimensions.overall.OverallExperienceParams.HIGH_SATISFACTION_RATIO_PARAMS;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.HAPPINESS_RATIO_PATH;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.HIGH_SATISFACTION_RATIO_PATH;

public class OverallExperienceStats {
    private static final Map<String, Number>
            initHappinessRatio = Initializer.initConsolidatedMap(HAPPINESS_RATIO_PARAMS, HAPPINESS_RATIO_PATH),
            initHighSatisfactionRatio = Initializer.initConsolidatedMap(HIGH_SATISFACTION_RATIO_PARAMS, HIGH_SATISFACTION_RATIO_PATH);

    public static final Map<String, Number>
            happinessRatio = Preparation.prepareData(initHappinessRatio),
            highSatisfactionRatio = Preparation.prepareData(initHighSatisfactionRatio);

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
            put("Happiness Ratio", Preparation.filterMap(initHappinessRatio));
            put("High Satisfaction Ratio", Preparation.filterMap(initHighSatisfactionRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + DimensionNames.OVERALL_EXPERIENCE)) {
            if (args.contains("--indicator=" + IndicatorNames.HAPPINESS_RATIO))
                Print.printChartData(happinessRatio, EU28_MEMBERS, seriesType, IndicatorNames.HAPPINESS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.HIGH_SATISFACTION_RATIO))
                Print.printChartData(highSatisfactionRatio, EU28_MEMBERS, seriesType, IndicatorNames.HIGH_SATISFACTION_RATIO, direction);
        }
    }
}
