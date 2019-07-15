package app.java.data.collect;

import app.java.data.fetch.dao.MainActivityDAO;
import app.java.data.fetch.dao.impl.MainActivityDAOImpl;

public class MainActivityCollector {
    private static MainActivityDAO mainActivityDAO = new MainActivityDAOImpl();

    public static void dataCollector() {
        StringBuilder
                employmentRatio = mainActivityDAO.getEmploymentRatio(),
                temporaryEmploymentRatio = mainActivityDAO.getTemporaryEmploymentRatio(),
                involuntaryPartTimeRatio = mainActivityDAO.getInvoluntaryPartTimeRatio(),
                overQualifiedRatio = mainActivityDAO.getOverQualifiedRatio(),
                avgWorkHours2007 = mainActivityDAO.getAvgWorkHours2007(),
                avgWorkHours2008 = mainActivityDAO.getAvgWorkHours2008(),
                nightsRatio = mainActivityDAO.getNightsRatio(),
                unemploymentRatio = mainActivityDAO.getUnemploymentRatio(),
                longTermUnemploymentRatio = mainActivityDAO.getLongTermUnemploymentRatio(),
                activePopulation = mainActivityDAO.getActivePopulation(),
                researchers = mainActivityDAO.getResearchers();
    }
}
