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
            SATISFACTION_RATIO = InteractionsParams.getRelationshipsSatisfactionParams(),

            NP_FIN_CIN_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("financial")),
            NP_FIN_CULT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("financial")),
            NP_FIN_LIVE_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("financial")),
            NP_FIN_SPORT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("financial")),

            NP_NNB_CIN_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("away")),
            NP_NNB_CULT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("away")),
            NP_NNB_LIVE_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("away")),
            NP_NNB_SPORT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("away"));

    private static final String
            askingRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.ASKING_RATIO + JSON_EXTENSION,
            discussionRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.DISCUSSION_RATIO + JSON_EXTENSION,
            gettingTogetherRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.GETTING_TOGETHER_RATIO + JSON_EXTENSION,
            nonParticipationRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.NON_PARTICIPATION_RATIO + JSON_EXTENSION,
            satisfactionRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.RELATIONSHIPS_SATISFACTION_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initAskingRatio = Initializer.initConsolidatedMap(ASKING_RATIO, askingRatioPath),
            initDiscussionRatio = Initializer.initConsolidatedMap(DISCUSSION_RATIO, discussionRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initGettingTogetherFamRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FAM_RATIO, gettingTogetherRatioPath),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedMap(GETTING_TOGETHER_FRD_RATIO, gettingTogetherRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedMap(NP_FIN_CIN_RATIO, nonParticipationRatioPath),
            initNpFinCultRatio = Initializer.initConsolidatedMap(NP_FIN_CULT_RATIO, nonParticipationRatioPath),
            initNpFinLiveRatio = Initializer.initConsolidatedMap(NP_FIN_LIVE_RATIO, nonParticipationRatioPath),
            initNpFinSportRatio = Initializer.initConsolidatedMap(NP_FIN_SPORT_RATIO, nonParticipationRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedMap(NP_NNB_CIN_RATIO, nonParticipationRatioPath),
            initNpNnbCultRatio = Initializer.initConsolidatedMap(NP_NNB_CULT_RATIO, nonParticipationRatioPath),
            initNpNnbLiveRatio = Initializer.initConsolidatedMap(NP_NNB_LIVE_RATIO, nonParticipationRatioPath),
            initNpNnbSportRatio = Initializer.initConsolidatedMap(NP_NNB_SPORT_RATIO, nonParticipationRatioPath),

            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO, satisfactionRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                askingRatio = Preparation.prepareData(initAskingRatio),
                discussionRatio = Preparation.prepareData(initDiscussionRatio),
                gettingTogetherRatio = consolidateGettingTogetherRatio(),
                nonParticipationRatio = consolidateNonParticipationRatio(),
                satisfactionRatio = Preparation.prepareData(initSatisfactionRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double reversedNonParticipationRatio = MathUtils.percentageReverseRatio(nonParticipationRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(askingRatio, key)
                        * MathUtils.percentageSafetyDouble(discussionRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherRatio, key)
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedNonParticipationRatio);
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
            add(Preparation.filterMap(initNpFinCinRatio));
            add(Preparation.filterMap(initNpFinCultRatio));
            add(Preparation.filterMap(initNpFinLiveRatio));
            add(Preparation.filterMap(initNpFinSportRatio));
            add(Preparation.filterMap(initNpNnbCinRatio));
            add(Preparation.filterMap(initNpNnbCultRatio));
            add(Preparation.filterMap(initNpNnbLiveRatio));
            add(Preparation.filterMap(initNpNnbSportRatio));
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

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateNonParticipationRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                npFinCinRatio = Preparation.prepareData(initNpFinCinRatio),
                npFinCultRatio = Preparation.prepareData(initNpFinCultRatio),
                npFinLiveRatio = Preparation.prepareData(initNpFinLiveRatio),
                npFinSportRatio = Preparation.prepareData(initNpFinSportRatio),

                npNnbCinRatio = Preparation.prepareData(initNpNnbCinRatio),
                npNnbCultRatio = Preparation.prepareData(initNpNnbCultRatio),
                npNnbLiveRatio = Preparation.prepareData(initNpNnbLiveRatio),
                npNnbSportRatio = Preparation.prepareData(initNpNnbSportRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(npFinCinRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinCultRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinLiveRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinSportRatio, key)

                        * MathUtils.percentageSafetyDouble(npNnbCinRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbCultRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbLiveRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbSportRatio, key);

                // Subtract 101 because of adding it before by using MathUtils.percentageSafetyDouble method
                Number value = MathUtils.getSquareValue(product, 8) - PERCENTAGE_SAFETY_THRESHOLD;
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
