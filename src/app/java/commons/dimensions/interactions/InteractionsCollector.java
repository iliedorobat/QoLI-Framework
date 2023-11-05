package app.java.commons.dimensions.interactions;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.interactions.InteractionsPaths.*;

public class InteractionsCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAskingRatio(), FilePathConst.INTERACTIONS_PATH, ASKING_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getDiscussionRatio(), FilePathConst.INTERACTIONS_PATH, DISCUSSION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getGettingTogetherRatio(), FilePathConst.INTERACTIONS_PATH, GETTING_TOGETHER_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getRelationshipsSatisfaction(), FilePathConst.INTERACTIONS_PATH, RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME);
    }

    /**
     * Persons (16 years or over) who have someone to ask for help
     * (moral, material or financial) from family, relatives, friends or neighbours<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp15<br/>
     * Years: 2013; 2015
     *
     * @return
     */
    private static StringBuilder getAskingRatio() {
        return Fetcher.fetchData("ilc_scp15", InteractionsParams.getAskingParams());
    }

    /**
     * Persons (16 years or over) who have someone to discuss personal matters<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp17<br/>
     * Years: 2013; 2015
     *
     * @return
     */
    private static StringBuilder getDiscussionRatio() {
        return Fetcher.fetchData("ilc_scp17", InteractionsParams.getDiscussionParams());
    }

    /**
     * People (16 years or over) getting together with family, relatives or friends at least once a week<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp09<br/>
     * Years: 2015
     *
     * @return
     */
    private static StringBuilder getGettingTogetherRatio() {
        return Fetcher.fetchData("ilc_scp09", InteractionsParams.getGettingTogetherParams());
    }

    /**
     * Percentage of the population rating their personal relationships' satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getRelationshipsSatisfaction() {
        return Fetcher.fetchSatisfactionRatio(InteractionsParams.getRelationshipsSatisfactionParams());
    }
}
