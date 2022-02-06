package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.LeisureDAO;
import app.java.data.fetch.dao.impl.LeisureDAOImpl;

public class LeisureCollector {
    private static final LeisureDAO leisureDAO = new LeisureDAOImpl();

    public static void dataCollector() {
        StringBuilder
                socialActivitiesRatio = leisureDAO.getSocialActivitiesRatio(),
                timeSpentSatisfaction = leisureDAO.getTimeSpentSatisfaction(),
                voluntaryActivitiesRatio = leisureDAO.getVoluntaryActivitiesRatio();

        FileUtils.writeToJSONFile(socialActivitiesRatio, FilePathConst.LEISURE_PATH, FileNameConst.SOCIAL_ACTIVITIES_RATIO);
        FileUtils.writeToJSONFile(timeSpentSatisfaction, FilePathConst.LEISURE_PATH, FileNameConst.TIME_SPENT_SATISFACTION);
        FileUtils.writeToJSONFile(voluntaryActivitiesRatio, FilePathConst.LEISURE_PATH, FileNameConst.VOLUNTARY_ACTIVITIES_RATIO);
    }
}
