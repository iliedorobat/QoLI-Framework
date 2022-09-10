package app.java.commons.dimesntions.overall;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

/**
 * Overall life satisfaction in the context of quality of life
 */
public class OverallExperienceCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getHappinessRatio(), FilePathConst.OVERALL_EXPERIENCE_PATH, FileNameConst.HAPPINESS_RATIO);
        FileUtils.writeToJSONFile(getHighSatisfactionRatio(), FilePathConst.OVERALL_EXPERIENCE_PATH, FileNameConst.HIGH_SATISFACTION_RATIO);
    }

    /**
     * Percentage of the population being happy always or most of the time in the last 4 weeks<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw08<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getHappinessRatio() {
        return Fetcher.fetchData("ilc_pw08", OverallExperienceParams.getHappinessParams());
    }

    /**
     * Percentage of the population rating their overall life satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getHighSatisfactionRatio() {
        return Fetcher.fetchSatisfactionRatio(OverallExperienceParams.getHighSatisfactionParams());
    }
}
