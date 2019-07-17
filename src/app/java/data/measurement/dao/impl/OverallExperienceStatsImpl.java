package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.OverallExperienceStatsDAO;

import java.util.Map;

public class OverallExperienceStatsImpl implements OverallExperienceStatsDAO {
    // The list of queried values
    private static final String[]
            HIGH_SATISFACTION_RATIO = {"PC", "HIGH", "TOTAL", "LIFESAT", "T", "Y_GE16"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String highSatisfactionRatioPath = FilePathConst.OVERALL_EXPERIENCE_PATH
            + FileNameConst.HIGH_SATISFACTION_RATIO + JSON_EXT;

    private static final Map<String, Number>
            highSatisfactionRatio = MeasureUtils.consolidateList(HIGH_SATISFACTION_RATIO, highSatisfactionRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(highSatisfactionRatioPath);
    }
}
