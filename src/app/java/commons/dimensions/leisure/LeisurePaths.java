package app.java.commons.dimensions.leisure;

import app.java.commons.constants.FilePathConst;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class LeisurePaths {
    public static final String LEISURE_FILE_NAME = "leisure";

    public static final String FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "formalVoluntaryActivitiesRatio";
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
    public static final String SOCIAL_ACTIVITIES_RATIO_FILE_NAME = "socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION_FILE_NAME = "timeSpentSatisfaction";
    public static final String VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME = "voluntaryActivitiesRatio";

    public static final String LEISURE_RAW_PATH = String.join(File.separator, FilePathConst.RAW_DATASET_PATH, LEISURE_FILE_NAME);

    private static String generatePath(String fileName) {
        return LEISURE_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            NON_PARTICIPATION_RATIO_PATH = generatePath(NON_PARTICIPATION_RATIO_FILE_NAME),
            SATISFACTION_RATIO_PATH = generatePath(TIME_SPENT_SATISFACTION_FILE_NAME),
            SOCIAL_ACTIVITIES_RATIO_PATH = generatePath(SOCIAL_ACTIVITIES_RATIO_FILE_NAME),
            VOLUNTARY_RATIO_PATH = generatePath(VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME);
}
