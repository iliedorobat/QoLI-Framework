package app.java.parser.http.dao;

public interface SocialActivityDAO {
    /**
     * Participation in any cultural or sport activities in the last 12 months<br/>
     * Population aged 16 years or over<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp01<br/>
     * Years: 2006; 2015
     *
     * @return
     */
    StringBuilder getSocialActivitiesRatio();

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
     * Percentage of the population rating their overall life satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @return
     */
    StringBuilder getHighSatisfactionRatio();

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
}
