package app.java.data.fetch.dao.impl;

import app.java.data.fetch.dao.OverallExperienceDAO;
import app.java.data.fetch.FetcherUtils;

public class OverallExperienceDAOImpl implements OverallExperienceDAO {
    /**
     * Percentage of the population rating their satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @return
     */
    public StringBuilder getHighSatisfactionRatio() {
        return FetcherUtils.getSatisfactionRatio("HIGH", "LIFESAT");
    }
}
