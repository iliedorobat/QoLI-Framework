package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
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

        TextUtils.writeToJSONFile(educationRatio, FilePathConst.EDUCATION_PATH + FileNameConst.EDU_RATIO);
        TextUtils.writeToJSONFile(earlyEducationRatio, FilePathConst.EDUCATION_PATH + FileNameConst.EARLY_EDU_RATIO);
        TextUtils.writeToJSONFile(leaversRatio, FilePathConst.EDUCATION_PATH + FileNameConst.LEAVERS_RATIO);
        TextUtils.writeToJSONFile(excludedRatio, FilePathConst.EDUCATION_PATH + FileNameConst.EXCLUDED_RATIO);
        TextUtils.writeToJSONFile(trainingRatio, FilePathConst.EDUCATION_PATH + FileNameConst.TRAINING_RATIO);
        TextUtils.writeToJSONFile(digitalSkillsRatio, FilePathConst.EDUCATION_PATH + FileNameConst.DIGITAL_SKILLS_RATIO);
        TextUtils.writeToJSONFile(zeroForeignLangRatio, FilePathConst.EDUCATION_PATH + FileNameConst.ZERO_FOREIGN_LANG_RATIO);
        TextUtils.writeToJSONFile(pupilsRatio2012, FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2012);
        TextUtils.writeToJSONFile(pupilsRatio2013, FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2013);
    }
}
