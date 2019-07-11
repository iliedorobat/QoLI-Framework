package app.java.parser.http.dao;

/**
 * Social interactions in environmental areas
 */
public interface SocialActivityDAO {
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
    StringBuilder getSocialActivitiesRatio();

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
     * @param reasons The list of reasons for non-participation:<br/>
     *               - FIN: Financial reasons;<br/>
     *               - NNB: None in the neighbourhood.
     * @return
     */
    StringBuilder getNonParticipationRatio(String[] reasons);

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
    StringBuilder getVoluntaryActivitiesRatio();

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
}
