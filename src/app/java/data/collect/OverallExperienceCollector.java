package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.OverallExperienceDAO;
import app.java.data.fetch.dao.impl.OverallExperienceDAOImpl;

public class OverallExperienceCollector {
    private static final OverallExperienceDAO overallExperienceDAO = new OverallExperienceDAOImpl();

    public static void dataCollector() {
        StringBuilder highSatisfactionRatio = overallExperienceDAO.getHighSatisfactionRatio();

        FileUtils.writeToJSONFile(highSatisfactionRatio, FilePathConst.OVERALL_EXPERIENCE_PATH + FileNameConst.HIGH_SATISFACTION_RATIO);
    }
}
