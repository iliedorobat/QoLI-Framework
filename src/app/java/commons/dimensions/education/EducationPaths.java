package app.java.commons.dimensions.education;

import app.java.commons.constants.FilePathConst;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EducationPaths {
    public static final String EDUCATION_FILE_NAME = "education";

    public static final String DIGITAL_SKILLS_RATIO_FILE_NAME = "digitalSkillsRatio";
    public static final String DROPOUT_RATIO_FILE_NAME = "dropoutRatio";
    public static final String EARLY_EDU_RATIO_FILE_NAME = "earlyEducationRatio";
    public static final String EDU_RATIO_FILE_NAME = "educationRatio";
    public static final String INACTIVE_YOUNG_RATIO_FILE_NAME = "inactiveYoungRatio";
    public static final String NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME = "zeroForeignLangRatio";
    public static final String PUPILS_RATIO_2012_FILE_NAME = "pupilsRatio2012";
    public static final String PUPILS_RATIO_2013_FILE_NAME = "pupilsRatio2013";
    public static final String TRAINING_RATIO_FILE_NAME = "trainingRatio";

    public static final String EDUCATION_RAW_PATH = String.join(File.separator, FilePathConst.RAW_DATASET_PATH, EDUCATION_FILE_NAME);

    private static String generatePath(String fileName) {
        return EDUCATION_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            DIGITAL_SKILLS_RATIO_PATH = generatePath(DIGITAL_SKILLS_RATIO_FILE_NAME),
            DROPOUT_RATIO_PATH = generatePath(DROPOUT_RATIO_FILE_NAME),
            EARLY_EDUCATION_RATIO_PATH = generatePath(EARLY_EDU_RATIO_FILE_NAME),
            EDUCATION_RATIO_PATH = generatePath(EDU_RATIO_FILE_NAME),
            INACTIVE_YOUNG_RATIO_PATH = generatePath(INACTIVE_YOUNG_RATIO_FILE_NAME),
            NO_KNOWN_FOREIGN_LANG_RATIO_PATH = generatePath(NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME),
            PUPILS_RATIO_2012_PATH = generatePath(PUPILS_RATIO_2012_FILE_NAME),
            PUPILS_RATIO_2013_PATH = generatePath(PUPILS_RATIO_2013_FILE_NAME),
            TRAINING_RATIO_PATH = generatePath(TRAINING_RATIO_FILE_NAME);
}
