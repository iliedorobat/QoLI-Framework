package app.java.data.fetch.dao;

/**
 * Overall life satisfaction in the context of quality of life
 */
public interface OverallExperienceDAO {
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
}
