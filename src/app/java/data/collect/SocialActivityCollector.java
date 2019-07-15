package app.java.data.collect;

import app.java.commons.Constants;
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

        TextUtils.writeToJSONFile(socialActivitiesRatio, Constants.SOCIALIZING_PATH + "socialActivitiesRatio");
        TextUtils.writeToJSONFile(nonParticipationRatio, Constants.SOCIALIZING_PATH + "nonParticipationRatio");
        TextUtils.writeToJSONFile(gettingTogetherRatio, Constants.SOCIALIZING_PATH + "gettingTogetherRatio");
        TextUtils.writeToJSONFile(voluntaryActivitiesRatio, Constants.SOCIALIZING_PATH + "voluntaryActivitiesRatio");
        TextUtils.writeToJSONFile(askingRatio, Constants.SOCIALIZING_PATH + "askingRatio");
        TextUtils.writeToJSONFile(discussionRatio, Constants.SOCIALIZING_PATH + "discussionRatio");
    }
}
