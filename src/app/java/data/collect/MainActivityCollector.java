package app.java.data.collect;

import app.java.commons.Constants;
import app.java.commons.TextUtils;
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

        TextUtils.writeToJSONFile(employmentRatio, Constants.MAIN_ACTIVITY_PATH + "employmentRatio");
        TextUtils.writeToJSONFile(temporaryEmploymentRatio, Constants.MAIN_ACTIVITY_PATH + "temporaryEmploymentRatio");
        TextUtils.writeToJSONFile(involuntaryPartTimeRatio, Constants.MAIN_ACTIVITY_PATH + "involuntaryPartTimeRatio");
        TextUtils.writeToJSONFile(overQualifiedRatio, Constants.MAIN_ACTIVITY_PATH + "overQualifiedRatio");
        TextUtils.writeToJSONFile(avgWorkHours2007, Constants.MAIN_ACTIVITY_PATH + "avgWorkHours2007");
        TextUtils.writeToJSONFile(avgWorkHours2008, Constants.MAIN_ACTIVITY_PATH + "avgWorkHours2008");
        TextUtils.writeToJSONFile(nightsRatio, Constants.MAIN_ACTIVITY_PATH + "nightsRatio");
        TextUtils.writeToJSONFile(unemploymentRatio, Constants.MAIN_ACTIVITY_PATH + "unemploymentRatio");
        TextUtils.writeToJSONFile(longTermUnemploymentRatio, Constants.MAIN_ACTIVITY_PATH + "longTermUnemploymentRatio");
        TextUtils.writeToJSONFile(activePopulation, Constants.MAIN_ACTIVITY_PATH + "activePopulation");
        TextUtils.writeToJSONFile(researchers, Constants.MAIN_ACTIVITY_PATH + "researchers");
    }
}
