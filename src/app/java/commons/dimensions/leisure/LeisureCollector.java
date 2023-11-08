package app.java.commons.dimensions.leisure;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.leisure.LeisureParams.*;
import static app.java.commons.dimensions.leisure.LeisurePaths.*;

public class LeisureCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getNonParticipationRatio(), FilePathConst.LEISURE_PATH, NON_PARTICIPATION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialActivitiesRatio(), FilePathConst.LEISURE_PATH, SOCIAL_ACTIVITIES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getTimeSpentSatisfaction(), FilePathConst.LEISURE_PATH, TIME_SPENT_SATISFACTION_FILE_NAME);
        FileUtils.writeToJSONFile(getVoluntaryActivitiesRatio(), FilePathConst.LEISURE_PATH, VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME);
    }

    /**
     * Non-participation in cultural activities or sports events during the previous 12 months
     * due to financial reasons or due to a lack of facilities<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp05<br/>
     * Years: 2015<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNonParticipationRatio() {
        return Fetcher.fetchData("ilc_scp05", NON_PARTICIPATION_PARAMS);
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
        return Fetcher.fetchData("ilc_scp02", SOCIAL_ACTIVITIES_RATIO_PARAMS);
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
        return Fetcher.fetchSatisfactionRatio(SATISFACTION_RATIO_PARAMS);
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
        return Fetcher.fetchActivePeopleRatio(VOLUNTARY_RATIO_PARAMS);
    }
}
