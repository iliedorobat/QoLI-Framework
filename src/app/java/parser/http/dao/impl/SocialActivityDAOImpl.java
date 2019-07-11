package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.DataUtils;
import app.java.parser.http.dao.SocialActivityDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    public static final String[] REASONS = {"FIN", "NNB"};

    public StringBuilder getSocialActivitiesRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
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

            MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
//        params.put("acl00", "AC521");  // Cinema
//        params.put("acl00", "AC522A"); // Live performances
//        params.put("acl00", "AC523H"); // Cultural sites
//        params.put("acl00", "AC525");  // Sports events
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            ParserUtils.addParams(params, reasons, "reason");
            params.put("sex", "T");
            params.put("unit", "PC");
            return DataFetcher.fetchData("ilc_scp05", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getGettingTogetherRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
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
        return DataUtils.getActivePeopleRatio(activities);
    }

    public StringBuilder getAskingRatio() {
        return DataUtils.getSupportiveRatio("ilc_scp15");
    }

    public StringBuilder getDiscussionRatio() {
        return DataUtils.getSupportiveRatio("ilc_scp17");
    }
}
