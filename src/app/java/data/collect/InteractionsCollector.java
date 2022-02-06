package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.InteractionsDAO;
import app.java.data.fetch.dao.impl.InteractionsDAOImpl;

public class InteractionsCollector {
    private static final InteractionsDAO interactionsDAO = new InteractionsDAOImpl();

    public static void dataCollector() {
        StringBuilder
                askingRatio = interactionsDAO.getAskingRatio(),
                discussionRatio = interactionsDAO.getDiscussionRatio(),
                gettingTogetherRatio = interactionsDAO.getGettingTogetherRatio(),
                nonParticipationRatio = interactionsDAO.getNonParticipationRatio(),
                relationshipsSatisfactionRatio = interactionsDAO.getRelationshipsSatisfaction();

        FileUtils.writeToJSONFile(askingRatio, FilePathConst.INTERACTIONS_PATH, FileNameConst.ASKING_RATIO);
        FileUtils.writeToJSONFile(discussionRatio, FilePathConst.INTERACTIONS_PATH, FileNameConst.DISCUSSION_RATIO);
        FileUtils.writeToJSONFile(gettingTogetherRatio, FilePathConst.INTERACTIONS_PATH, FileNameConst.GETTING_TOGETHER_RATIO);
        FileUtils.writeToJSONFile(nonParticipationRatio, FilePathConst.INTERACTIONS_PATH, FileNameConst.NON_PARTICIPATION_RATIO);
        FileUtils.writeToJSONFile(relationshipsSatisfactionRatio, FilePathConst.INTERACTIONS_PATH, FileNameConst.RELATIONSHIPS_SATISFACTION_RATIO);
    }
}
