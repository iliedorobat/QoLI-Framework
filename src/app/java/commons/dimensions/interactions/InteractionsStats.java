package app.java.commons.dimensions.interactions;

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
import static app.java.commons.dimensions.interactions.InteractionsParams.*;
import static app.java.commons.dimensions.interactions.InteractionsPaths.*;

public class InteractionsStats {
    private static final Map<String, Number>
            initAskingRatio = Initializer.initConsolidatedMap(ASKING_RATIO_PARAMS, ASKING_RATIO_PATH),
            initDiscussionRatio = Initializer.initConsolidatedMap(DISCUSSION_PARAMS_RATIO, DISCUSSION_RATIO_PATH),
            initGettingTogetherFamRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FAM_RATIO_PARAMS, GETTING_TOGETHER_RATIO_PATH),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FRD_RATIO_PARAMS, GETTING_TOGETHER_RATIO_PATH),
            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO_PARAMS, SATISFACTION_RATIO_PATH);

    public static final Map<String, Number>
            askingRatio = Preparation.prepareData(initAskingRatio),
            discussionRatio = Preparation.prepareData(initDiscussionRatio),
            gettingTogetherFamRatio = Preparation.prepareData(initGettingTogetherFamRatio),
            gettingTogetherFrdRatio = Preparation.prepareData(initGettingTogetherFrdRatio),
            satisfactionRatio = Preparation.prepareData(initSatisfactionRatio);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("askingRatio", askingRatio);
        put("discussionRatio", discussionRatio);
        put("gettingTogetherFamRatio", gettingTogetherFamRatio);
        put("gettingTogetherFrdRatio", gettingTogetherFrdRatio);
        put("satisfactionRatio", satisfactionRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(askingRatio, key)
                        * MathUtils.percentageSafetyDouble(discussionRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherFamRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherFrdRatio, key)
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Asking Ratio", Preparation.filterMap(initAskingRatio));
            put("Discussion Ratio", Preparation.filterMap(initDiscussionRatio));
            put("Getting Together Family Ratio", Preparation.filterMap(initGettingTogetherFamRatio));
            put("Getting Together Friends Ratio", Preparation.filterMap(initGettingTogetherFrdRatio));
            put("Satisfaction Ratio", Preparation.filterMap(initSatisfactionRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.ASKING_RATIO, askingRatio);
            put(IndicatorNames.DISCUSSION_RATIO, discussionRatio);
            put(IndicatorNames.GETTING_TOGETHER_FAM_RATIO, gettingTogetherFamRatio);
            put(IndicatorNames.GETTING_TOGETHER_FRD_RATIO, gettingTogetherFrdRatio);
            put(IndicatorNames.SATISFACTION_RATIO, satisfactionRatio);
        }};

        Print.printChartData(args, indicators, FilePathConst.INTERACTIONS_DIR, EU28_MEMBERS, seriesType, direction);
    }
}
