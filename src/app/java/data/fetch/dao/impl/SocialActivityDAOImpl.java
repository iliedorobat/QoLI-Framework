package app.java.data.fetch.dao.impl;

import app.java.commons.Errors;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.SocialActivityDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    private static final String[] REASONS = {
            "FIN",  // Financial reasons
            "NINT", // No interest
            "NNB",  // None in the neighbourhood
            "OTH"   // Other
    };
    private static final String[] ACTIVITIES_TYPES = {
            "AC521",  // Cinema
            "AC522A", // Live performances (theatre, concerts, ballet)
            "AC523H", // Cultural sites (historical monuments, museums, art galleries or archaeological sites)
            "AC525"   // Sports events
    };

    public StringBuilder getSocialActivitiesRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("deg_urb", "TOTAL");
        params.put("frequenc", "GE1");
        params.put("hhtyp", "TOTAL");
        params.put("quantile", "TOTAL");
        params.put("unit", "PC");
        return Fetcher.fetchData("ilc_scp02", params);
    }

    public StringBuilder getNonParticipationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ACTIVITIES_TYPES, "acl00");
        params.put("age", "Y_GE16");
        params.put("isced11", "TOTAL");
        FetcherUtils.addParams(params, REASONS, "reason");
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("ilc_scp05", params);
    }

    public StringBuilder getGettingTogetherRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("frequenc", "WEEK");
        params.put("isced11", "TOTAL");
        params.put("ind_type", "FAM"); // Family and relatives
        params.put("ind_type", "FRD"); // Friends
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("ilc_scp09", params);
    }

    public StringBuilder getVoluntaryActivitiesRatio() {
        String[] activities = {"AC41A", "AC42A"};
        return FetcherUtils.getActivePeopleRatio(activities);
    }

    public StringBuilder getAskingRatio() {
        return FetcherUtils.getSupportiveRatio("ilc_scp15");
    }

    public StringBuilder getDiscussionRatio() {
        return FetcherUtils.getSupportiveRatio("ilc_scp17");
    }
}
