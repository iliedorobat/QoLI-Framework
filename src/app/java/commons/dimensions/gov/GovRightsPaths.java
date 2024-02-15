package app.java.commons.dimensions.gov;

import app.java.commons.constants.Constants;

import java.io.File;

import static app.java.commons.constants.Constants.CSV_EXTENSION;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class GovRightsPaths {
    public static final String GOVERNANCE_FILE_NAME = "governance";

    public static final String CITIZENSHIP_RATIO_FILE_NAME = "citizenshipRatio";
    public static final String EMPLOYMENT_RATIO_BY_SEX_FILE_NAME = "employmentRatioBySex";
    public static final String FEMALE_EMPLOYMENT_RATIO_FILE_NAME = "femaleEmploymentRatio";
    public static final String MALE_EMPLOYMENT_RATIO_FILE_NAME = "maleEmploymentRatio";
    public static final String GENDER_EMPLOYMENT_GAP_FILE_NAME = "genderEmploymentGap";
    public static final String GENDER_PAY_GAP_FILE_NAME = "genderPayGap";
    public static final String POPULATION_LEGTST_TRUST_RATIO_FILE_NAME = "populationLegtstTrustRatio";
    public static final String POPULATION_OTHERS_TRUST_RATIO_FILE_NAME = "populationOthersTrustRatio";
    public static final String POPULATION_PLCTST_TRUST_RATIO_FILE_NAME = "populationPlctstTrustRatio";
    public static final String POPULATION_PLTTST_TRUST_RATIO_FILE_NAME = "populationPlttstTrustRatio";
    public static final String POPULATION_TRUST_FILE_NAME = "populationTrustRatio";
    public static final String VOTER_TURNOUT_FILE_NAME = "voterTurnout";

    public static final String GOV_RIGHTS_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, GOVERNANCE_FILE_NAME);

    private static String generatePath(String fileName) {
        return GOV_RIGHTS_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            CITIZENSHIP_RATIO_PATH = generatePath(CITIZENSHIP_RATIO_FILE_NAME),
            EMPLOYMENT_RATIO_PATH = generatePath(EMPLOYMENT_RATIO_BY_SEX_FILE_NAME),
            GENDER_PAY_GAP_PATH = generatePath(GENDER_PAY_GAP_FILE_NAME),
            POPULATION_TRUST_PATH = generatePath(POPULATION_TRUST_FILE_NAME),
            VOTER_TURNOUT_PATH = GOV_RIGHTS_RAW_PATH + File.separator + VOTER_TURNOUT_FILE_NAME + CSV_EXTENSION;
}
