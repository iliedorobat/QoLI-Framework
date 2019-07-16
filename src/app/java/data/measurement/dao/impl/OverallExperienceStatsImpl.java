package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.OverallExperienceStatsDAO;

public class OverallExperienceStatsImpl implements OverallExperienceStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String highSatisfactionRatioPath = FilePathConst.OVERALL_EXPERIENCE_PATH
            + FileNameConst.HIGH_SATISFACTION_RATIO + JSON_EXT;
}
