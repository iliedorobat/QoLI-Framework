package app.java.commons.dimensions.leisureInteract;

import app.java.commons.constants.Constants;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class LeisureInteractPaths {
    public static final String LEISURE_INTERACT_FILE_NAME = "leisureInteractions";

    public static final String ASKING_RATIO_FILE_NAME = "askingRatio";
    public static final String DISCUSSION_RATIO_FILE_NAME = "discussionRatio";
    public static final String FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "formalVoluntaryActivitiesRatio";
    public static final String GETTING_TOGETHER_RATIO_FILE_NAME = "gettingTogetherRatio";
    public static final String GETTING_TOGETHER_FAM_RATIO_FILE_NAME = "gettingTogetherFamRatio";
    public static final String GETTING_TOGETHER_FRD_RATIO_FILE_NAME = "gettingTogetherFrdRatio";
    public static final String INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "informalVoluntaryActivitiesRatio";
    public static final String NON_PARTICIPATION_RATIO_FILE_NAME = "nonParticipationRatio";
    public static final String NON_PARTICIPATION_FIN_CINEMA_RATIO_FILE_NAME = "nonParticipationFinCinemaRatio";
    public static final String NON_PARTICIPATION_FIN_CULTURE_RATIO_FILE_NAME = "nonParticipationFinCultureRatio";
    public static final String NON_PARTICIPATION_FIN_LIVE_RATIO_FILE_NAME = "nonParticipationFinLiveRatio";
    public static final String NON_PARTICIPATION_FIN_SPORT_RATIO_FILE_NAME = "nonParticipationFinSportRatio";
    public static final String NON_PARTICIPATION_NNB_CINEMA_RATIO_FILE_NAME = "nonParticipationNnbCinemaRatio";
    public static final String NON_PARTICIPATION_NNB_CULTURE_RATIO_FILE_NAME = "nonParticipationNnbCultureRatio";
    public static final String NON_PARTICIPATION_NNB_LIVE_RATIO_FILE_NAME = "nonParticipationNnbLiveRatio";
    public static final String NON_PARTICIPATION_NNB_SPORT_RATIO_FILE_NAME = "nonParticipationNnbSportRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME = "relationshipsSatisfactionRatio";
    public static final String SOCIAL_ACTIVITIES_RATIO_FILE_NAME = "socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION_FILE_NAME = "timeSpentSatisfaction";
    public static final String VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "voluntaryActivitiesRatio";

    public static final String LEISURE_INTERACT_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, LEISURE_INTERACT_FILE_NAME);

    private static String generatePath(String fileName) {
        return LEISURE_INTERACT_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            ASKING_RATIO_PATH = generatePath(ASKING_RATIO_FILE_NAME),
            DISCUSSION_RATIO_PATH = generatePath(DISCUSSION_RATIO_FILE_NAME),
            GETTING_TOGETHER_RATIO_PATH = generatePath(GETTING_TOGETHER_RATIO_FILE_NAME),
            NON_PARTICIPATION_RATIO_PATH = generatePath(NON_PARTICIPATION_RATIO_FILE_NAME),
            REL_SATISFACTION_RATIO_PATH = generatePath(RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME), // TODO: old: SATISFACTION_RATIO_PATH
            TIME_SATISFACTION_RATIO_PATH = generatePath(TIME_SPENT_SATISFACTION_FILE_NAME), // TODO: old: SATISFACTION_RATIO_PATH
            SOCIAL_ACTIVITIES_RATIO_PATH = generatePath(SOCIAL_ACTIVITIES_RATIO_FILE_NAME),
            VOLUNTARY_RATIO_PATH = generatePath(VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME);
}
