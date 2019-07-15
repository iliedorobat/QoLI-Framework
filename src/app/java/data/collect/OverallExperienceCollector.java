package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.TextUtils;
import app.java.data.fetch.dao.OverallExperienceDAO;
import app.java.data.fetch.dao.impl.OverallExperienceDAOImpl;

public class OverallExperienceCollector {
    private static OverallExperienceDAO overallExperienceDAO = new OverallExperienceDAOImpl();

    public static void dataCollector() {
        StringBuilder highSatisfactionRatio = overallExperienceDAO.getHighSatisfactionRatio();

        TextUtils.writeToJSONFile(highSatisfactionRatio, FilePathConst.OVERAL_EXPERIENCE_PATH + FileNameConst.HIGH_SATISFACTION_RATIO);
    }
}
