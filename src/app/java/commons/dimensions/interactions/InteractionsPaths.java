package app.java.commons.dimensions.interactions;

import app.java.commons.constants.Constants;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class InteractionsPaths {
    public static final String INTERACTIONS_FILE_NAME = "interactions";

    public static final String ASKING_RATIO_FILE_NAME = "askingRatio";
    public static final String DISCUSSION_RATIO_FILE_NAME = "discussionRatio";
    public static final String GETTING_TOGETHER_RATIO_FILE_NAME = "gettingTogetherRatio";
    public static final String GETTING_TOGETHER_FAM_RATIO_FILE_NAME = "gettingTogetherFamRatio";
    public static final String GETTING_TOGETHER_FRD_RATIO_FILE_NAME = "gettingTogetherFrdRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME = "relationshipsSatisfactionRatio";

    public static final String INTERACTIONS_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, INTERACTIONS_FILE_NAME);

    private static String generatePath(String fileName) {
        return INTERACTIONS_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            ASKING_RATIO_PATH = generatePath(ASKING_RATIO_FILE_NAME),
            DISCUSSION_RATIO_PATH = generatePath(DISCUSSION_RATIO_FILE_NAME),
            GETTING_TOGETHER_RATIO_PATH = generatePath(GETTING_TOGETHER_RATIO_FILE_NAME),
            SATISFACTION_RATIO_PATH = generatePath(RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME);
}
