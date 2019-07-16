package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.SafetyStatsDAO;

public class SafetyStatsImpl implements SafetyStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String
            crimeRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO + JSON_EXT,
            offencesPath = FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES + JSON_EXT,
            pensionRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.PENSION_RATIO + JSON_EXT,
            socialProtectionRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO + JSON_EXT,
            unexpectedRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO + JSON_EXT,
            unpaidRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNPAID_RATIO + JSON_EXT;
}
