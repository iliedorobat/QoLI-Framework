package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.SocialActivityStatsDAO;

public class SocialActivityStatsImpl implements SocialActivityStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String
            askingRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.ASKING_RATIO + JSON_EXT,
            discussionRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.DISCUSSION_RATIO + JSON_EXT,
            gettingTogetherRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.GETTING_TOGETHER_RATIO + JSON_EXT,
            nonParticipationRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.NON_PARTICIPATION_RATIO + JSON_EXT,
            socialActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + JSON_EXT,
            voluntaryActivitiesRatioPath = FilePathConst.SOCIALIZING_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + JSON_EXT;
}
