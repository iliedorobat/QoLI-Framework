package ro.webdata.qoli.aggr.stats.dimensions.overall;

import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.io.File;

public class OverallExperiencePaths {
    public static final String OVERALL_EXPERIENCE_FILE_NAME = "overallExperience";

    public static final String HAPPINESS_RATIO_FILE_NAME = "happinessRatio";
    public static final String HIGH_SATISFACTION_RATIO_FILE_NAME = "highSatisfactionRatio";

    public static final String OVERALL_EXPERIENCE_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, OVERALL_EXPERIENCE_FILE_NAME);

    private static String generatePath(String fileName) {
        return OVERALL_EXPERIENCE_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            HAPPINESS_RATIO_PATH = generatePath(HAPPINESS_RATIO_FILE_NAME),
            HIGH_SATISFACTION_RATIO_PATH = generatePath(HIGH_SATISFACTION_RATIO_FILE_NAME);
}
