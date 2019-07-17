package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.SocialActivityStatsDAO;

import java.util.Map;

public class SocialActivityStatsImpl implements SocialActivityStatsDAO {
    // The lists of queried values
    private static final String[]
            ASKING_RATIO = {"TOTAL", "Y_GE16", "F", "PC"},
            DISCUSSION_RATIO = {"TOTAL", "Y_GE16", "F", "PC"},
            GETTING_TOGETHER_FAM_RATIO = {"WEEK", "FAM", "TOTAL", "Y_GE16", "T", "PC"},
            GETTING_TOGETHER_FRD_RATIO = {"WEEK", "FRD", "TOTAL", "Y_GE16", "T", "PC"},
            NON_PARTICIPATION_FIN_RATIO = {"FIN", "AC521", "TOTAL", "Y_GE16", "T", "PC"},
            NON_PARTICIPATION_NNB_RATIO = {"NNB", "AC521", "TOTAL", "Y_GE16", "T", "PC"},
            SOCIAL_ACTIVITIES_RATIO = {"GE1", "TOTAL", "TOTAL", "TOTAL", "PC"},
            VOLUNTARY_ACTIVITIES_RATIO = {"TOTAL", "AC41A", "Y_GE16", "T", "PC"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            askingRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.ASKING_RATIO + JSON_EXT,
            discussionRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.DISCUSSION_RATIO + JSON_EXT,
            gettingTogetherRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.GETTING_TOGETHER_RATIO + JSON_EXT,
            nonParticipationRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.NON_PARTICIPATION_RATIO + JSON_EXT,
            socialActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + JSON_EXT,
            voluntaryActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + JSON_EXT;

    private static final Map<String, Number>
            askingRatio = MeasureUtils.consolidateList(ASKING_RATIO, askingRatioPath),
            discussionRatio = MeasureUtils.consolidateList(DISCUSSION_RATIO, discussionRatioPath),
            gettingTogetherFamRatio = MeasureUtils.consolidateList(GETTING_TOGETHER_FAM_RATIO, gettingTogetherRatioPath),
            gettingTogetherFrdRatio = MeasureUtils.consolidateList(GETTING_TOGETHER_FRD_RATIO, gettingTogetherRatioPath),
            //TODO: treat the acl00 parameter
//            nonParticipationFinRatio = MeasureUtils.consolidateList(NON_PARTICIPATION_FIN_RATIO, nonParticipationRatioPath),
//            nonParticipationNnbRatio = MeasureUtils.consolidateList(NON_PARTICIPATION_NNB_RATIO, nonParticipationRatioPath),
            socialActivitiesRatio = MeasureUtils.consolidateList(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatioPath),
            voluntaryActivitiesRatio = MeasureUtils.consolidateList(VOLUNTARY_ACTIVITIES_RATIO, voluntaryActivitiesRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(nonParticipationRatioPath);
    }
}
