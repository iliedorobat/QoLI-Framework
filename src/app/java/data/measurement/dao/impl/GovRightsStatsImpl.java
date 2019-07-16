package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.GovRightsStatsDAO;

public class GovRightsStatsImpl implements GovRightsStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String CSV_EXT = Constants.CSV_EXTENSION;
    private static String
            activeCitizenshipPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.ACTIVE_CITIZENSHIP + JSON_EXT,
            employmentGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT_GAP + JSON_EXT,
            genderPayGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP + JSON_EXT,
            populationTrustRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST_RATIO + JSON_EXT,
            voterTurnoutPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.VOTER_TURNOUT + CSV_EXT;
}
