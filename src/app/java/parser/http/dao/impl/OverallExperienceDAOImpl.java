package app.java.parser.http.dao.impl;

import app.java.parser.http.dao.CommonDAO;
import app.java.parser.http.dao.OverallExperienceDAO;

public class OverallExperienceDAOImpl implements OverallExperienceDAO {
    public StringBuilder getHighSatisfactionRatio() {
        CommonDAO commonDAO = new CommonDAOImpl();
        return commonDAO.getSatisfactionRatio("HIGH", "LIFESAT");
    }
}
