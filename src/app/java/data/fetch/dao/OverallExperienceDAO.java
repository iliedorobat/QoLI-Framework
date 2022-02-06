package app.java.data.fetch.dao;

/**
 * Overall life satisfaction in the context of quality of life
 */
public interface OverallExperienceDAO {
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
    StringBuilder getHappinessRatio();

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
    StringBuilder getHighSatisfactionRatio();
}
