package app.java.data.collect;

import app.java.commons.Constants;
import app.java.commons.TextUtils;
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

        TextUtils.writeToJSONFile(educationRatio, Constants.EDUCATION_PATH + "educationRatio");
        TextUtils.writeToJSONFile(earlyEducationRatio, Constants.EDUCATION_PATH + "earlyEducationRatio");
        TextUtils.writeToJSONFile(leaversRatio, Constants.EDUCATION_PATH + "leaversRatio");
        TextUtils.writeToJSONFile(excludedRatio, Constants.EDUCATION_PATH + "excludedRatio");
        TextUtils.writeToJSONFile(trainingRatio, Constants.EDUCATION_PATH + "trainingRatio");
        TextUtils.writeToJSONFile(digitalSkillsRatio, Constants.EDUCATION_PATH + "digitalSkillsRatio");
        TextUtils.writeToJSONFile(zeroForeignLangRatio, Constants.EDUCATION_PATH + "zeroForeignLangRatio");
        TextUtils.writeToJSONFile(pupilsRatio2012, Constants.EDUCATION_PATH + "pupilsRatio2012");
        TextUtils.writeToJSONFile(pupilsRatio2013, Constants.EDUCATION_PATH + "pupilsRatio2013");
    }
}
