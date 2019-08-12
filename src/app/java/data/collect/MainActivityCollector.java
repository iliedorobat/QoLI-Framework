package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
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

        FileUtils.writeToJSONFile(employmentRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(temporaryEmploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.TEMPORARY_EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(involuntaryPartTimeRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.INVOLUNTARY_PART_TIME_RATIO);
        FileUtils.writeToJSONFile(overQualifiedRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.OVER_QUALIFIED_RATIO);
        FileUtils.writeToJSONFile(avgWorkHours2007, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2007);
        FileUtils.writeToJSONFile(avgWorkHours2008, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2008);
        FileUtils.writeToJSONFile(nightsRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.NIGHTS_RATIO);
        FileUtils.writeToJSONFile(unemploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(longTermUnemploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(activePopulation, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.ACTIVE_POPULATION);
        FileUtils.writeToJSONFile(researchers, FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.RESEARCHERS);
    }
}
