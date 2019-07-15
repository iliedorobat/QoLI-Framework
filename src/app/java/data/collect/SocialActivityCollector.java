package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.TextUtils;
import app.java.data.fetch.dao.SocialActivityDAO;
import app.java.data.fetch.dao.impl.SocialActivityDAOImpl;

public class SocialActivityCollector {
    private static SocialActivityDAO socialActivityDAO = new SocialActivityDAOImpl();

    public static void dataCollector() {
        StringBuilder socialActivitiesRatio = socialActivityDAO.getSocialActivitiesRatio(),
                nonParticipationRatio = socialActivityDAO.getNonParticipationRatio(SocialActivityDAOImpl.REASONS),
                gettingTogetherRatio = socialActivityDAO.getGettingTogetherRatio(),
                voluntaryActivitiesRatio = socialActivityDAO.getVoluntaryActivitiesRatio(),
                askingRatio = socialActivityDAO.getAskingRatio(),
                discussionRatio = socialActivityDAO.getDiscussionRatio();

        TextUtils.writeToJSONFile(socialActivitiesRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO);
        TextUtils.writeToJSONFile(nonParticipationRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.NON_PARTICIPATION_RATIO);
        TextUtils.writeToJSONFile(gettingTogetherRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.GETTING_TOGETHER_RATIO);
        TextUtils.writeToJSONFile(voluntaryActivitiesRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO);
        TextUtils.writeToJSONFile(askingRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.ASKING_RATIO);
        TextUtils.writeToJSONFile(discussionRatio, FilePathConst.SOCIALIZING_PATH + FileNameConst.DISCUSSION_RATIO);
    }
}
