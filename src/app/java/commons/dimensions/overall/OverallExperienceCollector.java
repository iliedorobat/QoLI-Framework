package app.java.commons.dimensions.overall;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.overall.OverallExperienceParams.HAPPINESS_RATIO_PARAMS;
import static app.java.commons.dimensions.overall.OverallExperienceParams.HIGH_SATISFACTION_RATIO_PARAMS;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.*;

/**
 * Overall life satisfaction in the context of quality of life
 */
public class OverallExperienceCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getHappinessRatio(), OVERALL_EXPERIENCE_RAW_PATH, HAPPINESS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHighSatisfactionRatio(), OVERALL_EXPERIENCE_RAW_PATH, HIGH_SATISFACTION_RATIO_FILE_NAME);
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
        return Fetcher.fetchData("ilc_pw08", HAPPINESS_RATIO_PARAMS);
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
        return Fetcher.fetchSatisfactionRatio(HIGH_SATISFACTION_RATIO_PARAMS);
    }
}
