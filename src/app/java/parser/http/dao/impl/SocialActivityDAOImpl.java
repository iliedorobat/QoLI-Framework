package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.CommonDAO;
import app.java.parser.http.dao.SocialActivityDAO;

import java.util.Map;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    private static CommonDAO commonDAO = new CommonDAOImpl();

    public StringBuilder getSocialActivitiesRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y_GE16");
        params.put("frequenc", "GE1");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_scp01", params);
    }

    public StringBuilder getVoluntaryActivitiesRatio() {
        String[] activities = {"AC41A", "AC42A"};
        return commonDAO.getActivePeopleRatio(activities);
    }

    public StringBuilder getHighSatisfactionRatio() {
        CommonDAO commonDAO = new CommonDAOImpl();
        return commonDAO.getSatisfactionRatio("HIGH", "LIFESAT");
    }

    public StringBuilder getGettingTogetherRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y_GE16");
        params.put("frequenc", "WEEK");
        params.put("isced11", "TOTAL");
        params.put("ind_type", "FAM"); // Family and relatives
        params.put("ind_type", "FRD"); // Friends
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_scp09", params);
    }
}
