package ro.webdata.qoli.aggr.stats.dimensions.overall;

import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;

import static ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceParams.HAPPINESS_RATIO_PARAMS;
import static ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceParams.HIGH_SATISFACTION_RATIO_PARAMS;

/**
 * Overall life satisfaction in the context of quality of life
 */
public class OverallExperienceCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getHappinessRatio(), OverallExperiencePaths.OVERALL_EXPERIENCE_RAW_PATH, OverallExperiencePaths.HAPPINESS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHighSatisfactionRatio(), OverallExperiencePaths.OVERALL_EXPERIENCE_RAW_PATH, OverallExperiencePaths.HIGH_SATISFACTION_RATIO_FILE_NAME);
    }

    /**
     * Percentage of the population being happy always or most of the time in the last 4 weeks<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw08<br/>
     * Years: 2013; 2018; 2022
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
     * Dataset: ilc_pw05b<br/>
     * Years: 2013; 2018; 2022
     *
     * @return
     */
    private static StringBuilder getHighSatisfactionRatio() {
        return Fetcher.fetchSatisfactionRatio(HIGH_SATISFACTION_RATIO_PARAMS);
    }
}
