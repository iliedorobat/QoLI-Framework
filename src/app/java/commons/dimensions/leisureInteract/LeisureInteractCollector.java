package app.java.commons.dimensions.leisureInteract;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.leisureInteract.LeisureInteractParams.*;
import static app.java.commons.dimensions.leisureInteract.LeisureInteractPaths.*;

public class LeisureInteractCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAskingRatio(), LEISURE_INTERACT_RAW_PATH, ASKING_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getDiscussionRatio(), LEISURE_INTERACT_RAW_PATH, DISCUSSION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getFrequencyContactRatio(), LEISURE_INTERACT_RAW_PATH, FREQUENCY_CONTACT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getGettingTogetherRatio(), LEISURE_INTERACT_RAW_PATH, GETTING_TOGETHER_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getRecreationAreasSatisfaction(), LEISURE_INTERACT_RAW_PATH, AREA_SATISFACTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getRelationshipsSatisfaction(), LEISURE_INTERACT_RAW_PATH, RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialActivitiesNpRatio(), LEISURE_INTERACT_RAW_PATH, SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialActivitiesRatio(), LEISURE_INTERACT_RAW_PATH, SOCIAL_ACTIVITIES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getTimeSpentSatisfaction(), LEISURE_INTERACT_RAW_PATH, TIME_SPENT_SATISFACTION_FILE_NAME);
        FileUtils.writeToJSONFile(getVoluntaryActivitiesNpRatio(), LEISURE_INTERACT_RAW_PATH, VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getVoluntaryActivitiesRatio(), LEISURE_INTERACT_RAW_PATH, VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME);
    }

    /**
     * Persons (16 years or over) who have someone to ask for help
     * (moral, material or financial) from family, relatives, friends or neighbours<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp15<br/>
     * Years: 2013; 2015; 2022
     *
     * @return
     */
    private static StringBuilder getAskingRatio() {
        return Fetcher.fetchData("ilc_scp15", ASKING_RATIO_PARAMS);
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
        return Fetcher.fetchData("ilc_scp17", DISCUSSION_PARAMS_RATIO);
    }

    /**
     * People (16 years or over) getting together with family, relatives or friends at least once a week<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp09<br/>
     * Years: 2015; 2022
     *
     * @return
     */
    private static StringBuilder getGettingTogetherRatio() {
        return Fetcher.fetchData("ilc_scp09", INTERACTIONS_PARAMS);
    }

    /**
     * People (16 years or over) who contact family, relatives or friends at least once a week<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp11<br/>
     * Years: 2015; 2022
     *
     * @return
     */
    private static StringBuilder getFrequencyContactRatio() {
        return Fetcher.fetchData("ilc_scp11", INTERACTIONS_PARAMS);
    }

    /**
     * Percentage of the population rating the satisfaction with recreational and green areas as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getRecreationAreasSatisfaction() {
        return Fetcher.fetchSatisfactionRatio(AREA_SATISFACTION_RATIO_PARAMS);
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
        return Fetcher.fetchSatisfactionRatio(REL_SATISFACTION_RATIO_PARAMS);
    }

    /**
     * Non-participation in cultural activities or sports events during the previous 12 months
     * due to financial reasons or due to a lack of facilities<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp05<br/>
     * Years: 2015; 2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getSocialActivitiesNpRatio() {
        return Fetcher.fetchData("ilc_scp05", SOCIAL_ACTIVITIES_NP_PARAMS);
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
        return Fetcher.fetchSatisfactionRatio(TIME_SATISFACTION_RATIO_PARAMS);
    }

    /**
     * Non-participation in formal or informal voluntary activities during the previous 12 months
     * due to lack of time or due to no interest<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp05<br/>
     * Years: 2015; 2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getVoluntaryActivitiesNpRatio() {
        return Fetcher.fetchData("ilc_scp05", VOLUNTARY_ACTIVITIES_NP_RATIO_PARAMS);
    }

    /**
     * Participation in formal or informal voluntary activities<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015; 2022
     *
     * @return
     */
    private static StringBuilder getVoluntaryActivitiesRatio() {
        return Fetcher.fetchActivePeopleRatio(VOLUNTARY_RATIO_PARAMS);
    }
}
