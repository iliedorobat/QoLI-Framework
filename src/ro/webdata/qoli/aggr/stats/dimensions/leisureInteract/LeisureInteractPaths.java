package ro.webdata.qoli.aggr.commons.dimensions.leisureInteract;

import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.io.File;

public class LeisureInteractPaths {
    public static final String LEISURE_INTERACT_FILE_NAME = "leisureInteractions";

    public static final String AREA_SATISFACTION_RATIO_FILE_NAME = "areaSatisfactionRatio";
    public static final String ASKING_RATIO_FILE_NAME = "askingRatio";
    public static final String DISCUSSION_RATIO_FILE_NAME = "discussionRatio";
    public static final String FREQUENCY_CONTACT_RATIO_FILE_NAME = "frequencyContactRatio";
    public static final String GETTING_TOGETHER_RATIO_FILE_NAME = "gettingTogetherRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME = "relationshipsSatisfactionRatio";
    public static final String SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME = "socialActivitiesNpRatio";
    public static final String SOCIAL_ACTIVITIES_RATIO_FILE_NAME = "socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION_FILE_NAME = "timeSpentSatisfaction";
    public static final String VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME = "voluntaryActivitiesNpRatio";
    public static final String VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "voluntaryActivitiesRatio";

    public static final String LEISURE_INTERACT_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, LEISURE_INTERACT_FILE_NAME);

    private static String generatePath(String fileName) {
        return LEISURE_INTERACT_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            AREA_SATISFACTION_RATIO_PATH = generatePath(AREA_SATISFACTION_RATIO_FILE_NAME),
            ASKING_RATIO_PATH = generatePath(ASKING_RATIO_FILE_NAME),
            DISCUSSION_RATIO_PATH = generatePath(DISCUSSION_RATIO_FILE_NAME),
            FREQUENCY_CONTACT_RATIO_PATH = generatePath(FREQUENCY_CONTACT_RATIO_FILE_NAME),
            GETTING_TOGETHER_RATIO_PATH = generatePath(GETTING_TOGETHER_RATIO_FILE_NAME),
            REL_SATISFACTION_RATIO_PATH = generatePath(RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME),
            TIME_SATISFACTION_RATIO_PATH = generatePath(TIME_SPENT_SATISFACTION_FILE_NAME),
            SOCIAL_ACTIVITIES_NP_RATIO_PATH = generatePath(SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME),
            SOCIAL_ACTIVITIES_RATIO_PATH = generatePath(SOCIAL_ACTIVITIES_RATIO_FILE_NAME),
            VOLUNTARY_ACTIVITIES_NP_RATIO_PATH = generatePath(VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME),
            VOLUNTARY_RATIO_PATH = generatePath(VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME);
}
