package ro.webdata.qoli.aggr.stats.dimensions.education;

import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.io.File;

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
    public static final String TRAINING_LAST_MONTH_RATIO_FILE_NAME = "trainingLastMonthRatio";
    public static final String TRAINING_LAST_YEAR_RATIO_FILE_NAME = "trainingLastYearRatio";

    public static final String EDUCATION_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, EDUCATION_FILE_NAME);

    private static String generatePath(String fileName) {
        return EDUCATION_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
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
            TRAINING_LAST_MONTH_RATIO_PATH = generatePath(TRAINING_LAST_MONTH_RATIO_FILE_NAME),
            TRAINING_LAST_YEAR_RATIO_PATH = generatePath(TRAINING_LAST_YEAR_RATIO_FILE_NAME);
}
