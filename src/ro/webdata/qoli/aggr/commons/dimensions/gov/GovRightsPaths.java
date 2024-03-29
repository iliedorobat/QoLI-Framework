package ro.webdata.qoli.aggr.commons.dimensions.gov;

import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.io.File;

public class GovRightsPaths {
    public static final String GOVERNANCE_FILE_NAME = "governance";

    public static final String CITIZENSHIP_RATIO_FILE_NAME = "citizenshipRatio";
    public static final String GENDER_EMP_GAP_FILE_NAME = "genderEmpGap";
    public static final String GENDER_PAY_GAP_FILE_NAME = "genderPayGap";
    public static final String POPULATION_LEGTST_TRUST_RATIO_FILE_NAME = "populationLegtstTrustRatio";
    public static final String POPULATION_OTHERS_TRUST_RATIO_FILE_NAME = "populationOthersTrustRatio";
    public static final String POPULATION_PLCTST_TRUST_RATIO_FILE_NAME = "populationPlctstTrustRatio";
    public static final String POPULATION_PLTTST_TRUST_RATIO_FILE_NAME = "populationPlttstTrustRatio";
    public static final String POPULATION_TRUST_FILE_NAME = "populationTrustRatio";
    public static final String VOTER_TURNOUT_EU_PARLIAMENT_FILE_NAME = "voterTurnoutEuParliament";
    public static final String VOTER_TURNOUT_PARLIAMENTARY_FILE_NAME = "voterTurnoutParliamentary";
    public static final String VOTER_TURNOUT_PRESIDENTIAL_FILE_NAME = "voterTurnoutPresidential";
    public static final String VOTER_TURNOUT_FILE_NAME = "voterTurnout";

    public static final String GOV_RIGHTS_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, GOVERNANCE_FILE_NAME);

    private static String generatePath(String fileName) {
        return GOV_RIGHTS_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            CITIZENSHIP_RATIO_PATH = generatePath(CITIZENSHIP_RATIO_FILE_NAME),
            GENDER_EMP_GAP_PATH = generatePath(GENDER_EMP_GAP_FILE_NAME),
            GENDER_PAY_GAP_PATH = generatePath(GENDER_PAY_GAP_FILE_NAME),
            POPULATION_TRUST_PATH = generatePath(POPULATION_TRUST_FILE_NAME),
            VOTER_TURNOUT_PATH = GOV_RIGHTS_RAW_PATH + File.separator + VOTER_TURNOUT_FILE_NAME + Constants.CSV_EXTENSION;
}
