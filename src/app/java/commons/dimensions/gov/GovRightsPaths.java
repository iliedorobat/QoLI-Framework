package app.java.commons.dimensions.gov;

import app.java.commons.constants.FilePathConst;

import static app.java.commons.constants.Constants.CSV_EXTENSION;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class GovRightsPaths {
    public static final String CITIZENSHIP_RATIO_FILE_NAME = "citizenshipRatio";
    public static final String EMPLOYMENT_RATIO_BY_SEX_FILE_NAME = "employmentRatioBySex";
    public static final String GENDER_PAY_GAP_FILE_NAME = "genderPayGap";
    public static final String POPULATION_TRUST_FILE_NAME = "populationTrustRatio";
    public static final String VOTER_TURNOUT_FILE_NAME = "voterTurnout";

    private static String generatePath(String fileName) {
        return FilePathConst.GOV_RIGHTS_PATH + fileName + JSON_EXTENSION;
    }

    public static final String
            CITIZENSHIP_RATIO_PATH = generatePath(CITIZENSHIP_RATIO_FILE_NAME),
            EMPLOYMENT_RATIO_PATH = generatePath(EMPLOYMENT_RATIO_BY_SEX_FILE_NAME),
            GENDER_PAY_GAP_PATH = generatePath(GENDER_PAY_GAP_FILE_NAME),
            POPULATION_TRUST_PATH = generatePath(POPULATION_TRUST_FILE_NAME),
            VOTER_TURNOUT_PATH = FilePathConst.GOV_RIGHTS_PATH + VOTER_TURNOUT_FILE_NAME + CSV_EXTENSION;
}
