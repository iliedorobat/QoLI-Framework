package app.java.data.collect;

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
    }
}
