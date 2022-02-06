package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.MainActivityDAO;
import app.java.data.fetch.dao.impl.MainActivityDAOImpl;

public class MainActivityCollector {
    private static final MainActivityDAO mainActivityDAO = new MainActivityDAOImpl();

    public static void dataCollector() {
        StringBuilder
                activePopulationRatio = mainActivityDAO.getActivePopulationRatio(),
                avgWorkHours2007 = mainActivityDAO.getAvgWorkHours2007(),
                avgWorkHours2008 = mainActivityDAO.getAvgWorkHours2008(),
                employmentRatio = mainActivityDAO.getEmploymentRatio(),
                inactivePopulationRatio = mainActivityDAO.getInactivePopulationRatio(),
                involuntaryPartTimeRatio = mainActivityDAO.getInvoluntaryPartTimeRatio(),
                jobSatisfaction = mainActivityDAO.getJobSatisfaction(),
                longTermUnemploymentRatio = mainActivityDAO.getLongTermUnemploymentRatio(),
                lowWageEarnersRatio = mainActivityDAO.getLowWageEarnersRatio(),
                overQualifiedRatio = mainActivityDAO.getOverQualifiedRatio(),
                researchers = mainActivityDAO.getResearchers(),
                temporaryEmploymentRatio = mainActivityDAO.getTemporaryEmploymentRatio(),
                unemploymentRatio = mainActivityDAO.getUnemploymentRatio(),
                workingNightsRatio = mainActivityDAO.getWorkingNightsRatio();

        FileUtils.writeToJSONFile(activePopulationRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.ACTIVE_POPULATION_RATIO);
        FileUtils.writeToJSONFile(avgWorkHours2007, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.AVG_WORK_HOURS_2007);
        FileUtils.writeToJSONFile(avgWorkHours2008, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.AVG_WORK_HOURS_2008);
        FileUtils.writeToJSONFile(employmentRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(inactivePopulationRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.INACTIVE_POPULATION_RATIO);
        FileUtils.writeToJSONFile(involuntaryPartTimeRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.INVOLUNTARY_PART_TIME_RATIO);
        FileUtils.writeToJSONFile(jobSatisfaction, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.JOB_SATISFACTION);
        FileUtils.writeToJSONFile(longTermUnemploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(lowWageEarnersRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.LOW_WAGE_EARNERS_RATIO);
        FileUtils.writeToJSONFile(overQualifiedRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.OVER_QUALIFIED_RATIO);
        FileUtils.writeToJSONFile(researchers, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.RESEARCHERS);
        FileUtils.writeToJSONFile(temporaryEmploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.TEMPORARY_EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(unemploymentRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(workingNightsRatio, FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.WORKING_NIGHTS_RATIO);
    }
}
