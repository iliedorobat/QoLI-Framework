package app.java.parser.http.dao.impl;

import app.java.parser.http.dao.CommonDAO;
import app.java.parser.http.dao.OverallExperienceDAO;

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
        CommonDAO commonDAO = new CommonDAOImpl();
        return commonDAO.getSatisfactionRatio("HIGH", "LIFESAT");
    }
}
