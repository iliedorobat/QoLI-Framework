package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SocialActivityStats {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    // The lists of queried values
    private static final String[]
            ASKING_RATIO = {"TOTAL", "Y_GE16", "T", "PC"},
            DISCUSSION_RATIO = {"TOTAL", "Y_GE16", "T", "PC"},
            SOCIAL_ACTIVITIES_RATIO = {"GE1", "TOTAL", "TOTAL", "TOTAL", "PC"},
            VOLUNTARY_ACTIVITIES_RATIO = {"TOTAL", "AC41A", "Y_GE16", "T", "PC"},

            GETTING_TOGETHER_FAM_RATIO = {"WEEK", "FAM", "TOTAL", "Y_GE16", "T", "PC"},
            GETTING_TOGETHER_FRD_RATIO = {"WEEK", "FRD", "TOTAL", "Y_GE16", "T", "PC"},
    
            NP_FIN_CIN_RATIO = {"FIN", "AC521", "TOTAL", "Y_GE16", "T", "PC"},
            NP_FIN_CULT_RATIO = {"FIN", "AC523H", "TOTAL", "Y_GE16", "T", "PC"},
            NP_FIN_LIVE_RATIO = {"FIN", "AC522A", "TOTAL", "Y_GE16", "T", "PC"},
            NP_FIN_SPORT_RATIO = {"FIN", "AC525", "TOTAL", "Y_GE16", "T", "PC"},

            NP_NNB_CIN_RATIO = {"NNB", "AC521", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_CULT_RATIO = {"NNB", "AC523H", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_LIVE_RATIO = {"NNB", "AC522A", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_SPORT_RATIO = {"NNB", "AC525", "TOTAL", "Y_GE16", "T", "PC"};

    private static final String
            askingRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.ASKING_RATIO + Constants.JSON_EXTENSION,
            discussionRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.DISCUSSION_RATIO + Constants.JSON_EXTENSION,
            gettingTogetherRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.GETTING_TOGETHER_RATIO + Constants.JSON_EXTENSION,
            nonParticipationRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.NON_PARTICIPATION_RATIO + Constants.JSON_EXTENSION,
            socialActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + Constants.JSON_EXTENSION,
            voluntaryActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + Constants.JSON_EXTENSION;

    private static final Map<String, Number>
            initAskingRatio = Initializer.initConsolidatedMap(ASKING_RATIO, askingRatioPath),
            initDiscussionRatio = Initializer.initConsolidatedMap(DISCUSSION_RATIO, discussionRatioPath),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatioPath),
            initVoluntaryActivitiesRatio = Initializer.initConsolidatedMap(VOLUNTARY_ACTIVITIES_RATIO, voluntaryActivitiesRatioPath),

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
            initNpNnbSportRatio = Initializer.initConsolidatedMap(NP_NNB_SPORT_RATIO, nonParticipationRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                askingRatio = Preparation.prepareData(initAskingRatio),
                discussionRatio = Preparation.prepareData(initDiscussionRatio),
                gettingTogetherRatio = consolidateGettingTogetherRatio(),
                nonParticipationRatio = consolidateNonParticipationRatio(),
                socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio),
                voluntaryActivitiesRatio = Preparation.prepareData(initVoluntaryActivitiesRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < EU28_MEMBERS.length; i++) {
                String code = EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedNonParticipationRatio = MathUtils.percentageReverseRatio(nonParticipationRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(askingRatio, key)
                        * MathUtils.percentageSafetyDouble(discussionRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedNonParticipationRatio)
                        * MathUtils.percentageSafetyDouble(socialActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(voluntaryActivitiesRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(askingRatio, true));
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
            add(Preparation.filterMap(initSocialActivitiesRatio));
            add(Preparation.filterMap(initVoluntaryActivitiesRatio));
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
            for (int i = 0; i < EU28_MEMBERS.length; i++) {
                String code = EU28_MEMBERS[i];
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
            for (int i = 0; i < EU28_MEMBERS.length; i++) {
                String code = EU28_MEMBERS[i];
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
                Number value = MathUtils.getSquareValue(product, 8);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
