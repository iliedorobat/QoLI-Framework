package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.Common;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.SocialActivityDAO;

import java.util.Map;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    public static final String[] REASONS = {"FIN", "NNB"};

    public StringBuilder getSocialActivitiesRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("deg_urb", "TOTAL");
        params.put("frequenc", "GE1");
        params.put("hhtyp", "TOTAL");
        params.put("quantile", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_scp02", params);
    }

    public StringBuilder getNonParticipationRatio(String[] reasons) {
        try {
            Errors.throwNewError(REASONS, reasons, "reason");

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
//        params.put("acl00", "AC521");  // Cinema
//        params.put("acl00", "AC522A"); // Live performances
//        params.put("acl00", "AC523H"); // Cultural sites
//        params.put("acl00", "AC525");  // Sports events
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            Common.addParams(params, reasons, "reason");
            params.put("sex", "T");
            params.put("unit", "PC");
            return DataFetcher.fetchData("ilc_scp05", params);
        } catch (Exception e) {
            return null;
        }
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

    public StringBuilder getVoluntaryActivitiesRatio() {
        String[] activities = {"AC41A", "AC42A"};
        return Common.getActivePeopleRatio(activities);
    }

    public StringBuilder getAskingRatio() {
        return Common.getSupportiveRatio("ilc_scp15");
    }

    public StringBuilder getDiscussionRatio() {
        return Common.getSupportiveRatio("ilc_scp17");
    }
}
