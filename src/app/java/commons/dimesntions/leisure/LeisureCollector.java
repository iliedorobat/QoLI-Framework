package app.java.commons.dimesntions.leisure;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;

public class LeisureCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getSocialActivitiesRatio(), FilePathConst.LEISURE_PATH, FileNameConst.SOCIAL_ACTIVITIES_RATIO);
        FileUtils.writeToJSONFile(getTimeSpentSatisfaction(), FilePathConst.LEISURE_PATH, FileNameConst.TIME_SPENT_SATISFACTION);
        FileUtils.writeToJSONFile(getVoluntaryActivitiesRatio(), FilePathConst.LEISURE_PATH, FileNameConst.VOLUNTARY_ACTIVITIES_RATIO);
    }

    /**
     * Participation in any cultural or sport activities in the last 12 months<br/>
     * Population aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp02<br/>
     * Years: 2006; 2015<br/><br/>
     *
     * Comments: NUTS 2 regions => ilc_scp01
     *
     * @return
     */
    private static StringBuilder getSocialActivitiesRatio() {
        return Fetcher.fetchData("ilc_scp02", LeisureParams.getSocialActivitiesParams());
    }

    /**
     * Percentage of the population rating their time spent satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getTimeSpentSatisfaction() {
        return FetcherUtils.getSatisfactionRatio(LeisureParams.getTimeSpentSatisfactionParams());
    }

    /**
     * Participation in formal or informal voluntary activities<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015
     *
     * @return
     */
    private static StringBuilder getVoluntaryActivitiesRatio() {
        return FetcherUtils.getActivePeopleRatio(LeisureParams.getVoluntaryActivitiesParams());
    }
}
