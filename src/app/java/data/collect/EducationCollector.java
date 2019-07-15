package app.java.data.collect;

import app.java.data.fetch.dao.EducationDAO;
import app.java.data.fetch.dao.impl.EducationDAOImpl;

public class EducationCollector {
    private static EducationDAO educationDAO = new EducationDAOImpl();

    public static void dataCollector() {
        StringBuilder
                educationRatio = educationDAO.getEducationRatio(EducationDAOImpl.EDUCATION_LEVELS),
                earlyEducationRatio = educationDAO.getEarlyEducationRatio(),
                leaversRatio = educationDAO.getLeaversRatio(),
                excludedRatio = educationDAO.getExcludedRatio(),
                trainingRatio = educationDAO.getTrainingRatio(),
                digitalSkillsRatio = educationDAO.getDigitalSkillsRatio(),
                zeroForeignLangRatio = educationDAO.getZeroForeignLangRatio(),
                pupilsRatio2012 = educationDAO.getPupilsRatio2012(),
                pupilsRatio2013 = educationDAO.getPupilsRatio2013();
    }
}
