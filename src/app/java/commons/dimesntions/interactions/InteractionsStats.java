package app.java.commons.dimesntions.interactions;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class InteractionsStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            ASKING_RATIO = InteractionsParams.getAskingParams(),
            DISCUSSION_RATIO = InteractionsParams.getDiscussionParams(),
            GETTING_TOGETHER_FAM_RATIO = InteractionsParams.getGettingTogetherParams(ParamsValues.IND_TYPE.get("family")),
            GETTING_TOGETHER_FRD_RATIO = InteractionsParams.getGettingTogetherParams(ParamsValues.IND_TYPE.get("friends")),
            SATISFACTION_RATIO = InteractionsParams.getRelationshipsSatisfactionParams();

    private static final String
            askingRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.ASKING_RATIO + JSON_EXTENSION,
            discussionRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.DISCUSSION_RATIO + JSON_EXTENSION,
            gettingTogetherRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.GETTING_TOGETHER_RATIO + JSON_EXTENSION,
            satisfactionRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.RELATIONSHIPS_SATISFACTION_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initAskingRatio = Initializer.initConsolidatedMap(ASKING_RATIO, askingRatioPath),
            initDiscussionRatio = Initializer.initConsolidatedMap(DISCUSSION_RATIO, discussionRatioPath),

            // Intermediate data to be consolidated into a single indicator
            initGettingTogetherFamRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FAM_RATIO, gettingTogetherRatioPath),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FRD_RATIO, gettingTogetherRatioPath),
            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO, satisfactionRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                askingRatio = Preparation.prepareData(initAskingRatio),
                discussionRatio = Preparation.prepareData(initDiscussionRatio),
                gettingTogetherRatio = consolidateGettingTogetherRatio(),
                satisfactionRatio = Preparation.prepareData(initSatisfactionRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(askingRatio, key)
                        * MathUtils.percentageSafetyDouble(discussionRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherRatio, key)
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initAskingRatio));
            add(Preparation.filterMap(initDiscussionRatio));
            add(Preparation.filterMap(initGettingTogetherFamRatio));
            add(Preparation.filterMap(initGettingTogetherFrdRatio));
            add(Preparation.filterMap(initSatisfactionRatio));
        }};
    }

    /**
     * Aggregate the "Getting Together Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateGettingTogetherRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                gettingTogetherFamRatio = Preparation.prepareData(initGettingTogetherFamRatio),
                gettingTogetherFrdRatio = Preparation.prepareData(initGettingTogetherFrdRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(gettingTogetherFamRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherFrdRatio, key);

                Number value = MathUtils.getSquareValue(product, 2);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
