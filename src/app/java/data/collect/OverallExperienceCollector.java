package app.java.data.collect;

import app.java.data.fetch.dao.OverallExperienceDAO;
import app.java.data.fetch.dao.impl.OverallExperienceDAOImpl;

public class OverallExperienceCollector {
    private static OverallExperienceDAO overallExperienceDAO = new OverallExperienceDAOImpl();

    public static void dataCollector() {
        StringBuilder highSatisfactionRatio = overallExperienceDAO.getHighSatisfactionRatio();
    }
}
