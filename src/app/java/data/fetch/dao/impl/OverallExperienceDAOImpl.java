package app.java.data.fetch.dao.impl;

import app.java.data.fetch.dao.OverallExperienceDAO;
import app.java.data.fetch.FetcherUtils;

public class OverallExperienceDAOImpl implements OverallExperienceDAO {
    private static final String HIGH_SATIS_LEVEL = "HIGH";
    private static final String OVERALL_LIFE_SATIS = "LIFESAT";

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
        return FetcherUtils.getSatisfactionRatio(HIGH_SATIS_LEVEL, OVERALL_LIFE_SATIS);
    }
}
