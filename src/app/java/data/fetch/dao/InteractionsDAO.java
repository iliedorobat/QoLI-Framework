package app.java.data.fetch.dao;

/**
 * Social interactions
 */
public interface InteractionsDAO {
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
    StringBuilder getAskingRatio();

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
    StringBuilder getDiscussionRatio();

    /**
     * People (16 years or over) getting together with friends at least once a week<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp09<br/>
     * Years: 2015
     *
     * @return
     */
    StringBuilder getGettingTogetherRatio();

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
    StringBuilder getNonParticipationRatio();

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
    StringBuilder getRelationshipsSatisfaction();
}
