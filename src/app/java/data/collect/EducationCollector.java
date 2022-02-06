package app.java.data.collect;

import app.java.commons.utils.FileUtils;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.fetch.dao.EducationDAO;
import app.java.data.fetch.dao.impl.EducationDAOImpl;

public class EducationCollector {
    private static final EducationDAO educationDAO = new EducationDAOImpl();

    public static void dataCollector() {
        StringBuilder
                digitalSkillsRatio = educationDAO.getDigitalSkillsRatio(),
                earlyEducationRatio = educationDAO.getEarlyEducationRatio(),
                educationRatio = educationDAO.getEducationRatio(),
                inactiveYoungRatio = educationDAO.getInactiveYoungRatio(),
                leaversRatio = educationDAO.getDropoutRatio(),
                pupilsRatio2012 = educationDAO.getPupilsRatio2012(),
                pupilsRatio2013 = educationDAO.getPupilsRatio2013(),
                trainingRatio = educationDAO.getTrainingRatio(),
                zeroForeignLangRatio = educationDAO.getNoKnownForeignLangRatio();

        FileUtils.writeToJSONFile(digitalSkillsRatio, FilePathConst.EDUCATION_PATH, FileNameConst.DIGITAL_SKILLS_RATIO);
        FileUtils.writeToJSONFile(earlyEducationRatio, FilePathConst.EDUCATION_PATH, FileNameConst.EARLY_EDU_RATIO);
        FileUtils.writeToJSONFile(educationRatio, FilePathConst.EDUCATION_PATH, FileNameConst.EDU_RATIO);
        FileUtils.writeToJSONFile(inactiveYoungRatio, FilePathConst.EDUCATION_PATH, FileNameConst.INACTIVE_YOUNG_RATIO);
        FileUtils.writeToJSONFile(leaversRatio, FilePathConst.EDUCATION_PATH, FileNameConst.DROPOUT_RATIO);
        FileUtils.writeToJSONFile(pupilsRatio2012, FilePathConst.EDUCATION_PATH, FileNameConst.PUPILS_RATIO_2012);
        FileUtils.writeToJSONFile(pupilsRatio2013, FilePathConst.EDUCATION_PATH, FileNameConst.PUPILS_RATIO_2013);
        FileUtils.writeToJSONFile(trainingRatio, FilePathConst.EDUCATION_PATH, FileNameConst.TRAINING_RATIO);
        FileUtils.writeToJSONFile(zeroForeignLangRatio, FilePathConst.EDUCATION_PATH, FileNameConst.NO_KNOWN_FOREIGN_LANG_RATIO);
    }
}
