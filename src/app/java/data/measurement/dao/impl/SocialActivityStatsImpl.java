package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.MathUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.SocialActivityStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.Map;
import java.util.TreeMap;

public class SocialActivityStatsImpl implements SocialActivityStatsDAO {
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
            NP_FIN_LIVE_RATIO = {"FIN", "AC522A", "TOTAL", "Y_GE16", "T", "PC"},
            NP_FIN_CULT_RATIO = {"FIN", "AC523H", "TOTAL", "Y_GE16", "T", "PC"},
            NP_FIN_SPORT_RATIO = {"FIN", "AC525", "TOTAL", "Y_GE16", "T", "PC"},

            NP_NNB_CIN_RATIO = {"NNB", "AC521", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_LIVE_RATIO = {"NNB", "AC522A", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_CULT_RATIO = {"NNB", "AC523H", "TOTAL", "Y_GE16", "T", "PC"},
            NP_NNB_SPORT_RATIO = {"NNB", "AC525", "TOTAL", "Y_GE16", "T", "PC"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            askingRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.ASKING_RATIO + JSON_EXT,
            discussionRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.DISCUSSION_RATIO + JSON_EXT,
            gettingTogetherRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.GETTING_TOGETHER_RATIO + JSON_EXT,
            nonParticipationRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.NON_PARTICIPATION_RATIO + JSON_EXT,
            socialActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + JSON_EXT,
            voluntaryActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + JSON_EXT;

    private static final Map<String, Number>
            initAskingRatio = Initializer.initConsolidatedList(ASKING_RATIO, askingRatioPath),
            initDiscussionRatio = Initializer.initConsolidatedList(DISCUSSION_RATIO, discussionRatioPath),
            initSocialActivitiesRatio = Initializer.initConsolidatedList(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatioPath),
            initVoluntaryActivitiesRatio = Initializer.initConsolidatedList(VOLUNTARY_ACTIVITIES_RATIO, voluntaryActivitiesRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initGettingTogetherFamRatio = Initializer.initConsolidatedList(GETTING_TOGETHER_FAM_RATIO, gettingTogetherRatioPath),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedList(GETTING_TOGETHER_FRD_RATIO, gettingTogetherRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedList(NP_FIN_CIN_RATIO, nonParticipationRatioPath),
            initNpFinLiveRatio = Initializer.initConsolidatedList(NP_FIN_LIVE_RATIO, nonParticipationRatioPath),
            initNpFinCultRatio = Initializer.initConsolidatedList(NP_FIN_CULT_RATIO, nonParticipationRatioPath),
            initNpFinSportRatio = Initializer.initConsolidatedList(NP_FIN_SPORT_RATIO, nonParticipationRatioPath),

            // Intermediate data which should be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedList(NP_NNB_CIN_RATIO, nonParticipationRatioPath),
            initNpNnbLiveRatio = Initializer.initConsolidatedList(NP_NNB_LIVE_RATIO, nonParticipationRatioPath),
            initNpNnbCultRatio = Initializer.initConsolidatedList(NP_NNB_CULT_RATIO, nonParticipationRatioPath),
            initNpNnbSportRatio = Initializer.initConsolidatedList(NP_NNB_SPORT_RATIO, nonParticipationRatioPath);

    public Map<String, Number> calculateDimension() {
//        Print.printVariation(Statistics.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                askingRatio = Preparation.prepareData(initAskingRatio),
                discussionRatio = Preparation.prepareData(initDiscussionRatio),
                gettingTogetherRatio = consolidateGettingTogetherRatio(),
                nonParticipationRatio = consolidateNonParticipationRatio(),
                socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio),
                voluntaryActivitiesRatio = Preparation.prepareData(initVoluntaryActivitiesRatio);

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double product = askingRatio.get(key).doubleValue()
                        * discussionRatio.get(key).doubleValue()
                        * socialActivitiesRatio.get(key).doubleValue()
                        * voluntaryActivitiesRatio.get(key).doubleValue()
                        * gettingTogetherRatio.get(key).doubleValue()
                        * nonParticipationRatio.get(key).doubleValue();
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Aggregate the "Getting Together Ratios" into a single ratio
     * @return An ordered map with aggregated data
     */
    private Map<String, Number> consolidateGettingTogetherRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                gettingTogetherFamRatio = Preparation.prepareData(initGettingTogetherFamRatio),
                gettingTogetherFrdRatio = Preparation.prepareData(initGettingTogetherFrdRatio);

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double product = gettingTogetherFamRatio.get(key).doubleValue()
                        * gettingTogetherFrdRatio.get(key).doubleValue();
                Number value = MathUtils.getSquareValue(product, 2);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     * @return An ordered map with aggregated data
     */
    private Map<String, Number> consolidateNonParticipationRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                npFinCinRatio = Preparation.prepareData(initNpFinCinRatio),
                npFinLiveRatio = Preparation.prepareData(initNpFinLiveRatio),
                npFinCultRatio = Preparation.prepareData(initNpFinCultRatio),
                npFinSportRatio = Preparation.prepareData(initNpFinSportRatio),

                npNnbCinRatio = Preparation.prepareData(initNpNnbCinRatio),
                npNnbLiveRatio = Preparation.prepareData(initNpNnbLiveRatio),
                npNnbCultRatio = Preparation.prepareData(initNpNnbCultRatio),
                npNnbSportRatio = Preparation.prepareData(initNpNnbSportRatio);

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = MapUtils.generateKey(code, year);
                double product = npFinCinRatio.get(key).doubleValue()
                        * npFinLiveRatio.get(key).doubleValue()
                        * npFinCultRatio.get(key).doubleValue()
                        * npFinSportRatio.get(key).doubleValue()
                        * npNnbCinRatio.get(key).doubleValue()
                        * npNnbLiveRatio.get(key).doubleValue()
                        * npNnbCultRatio.get(key).doubleValue()
                        * npNnbSportRatio.get(key).doubleValue();
                Number value = MathUtils.getSquareValue(product, 8);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
