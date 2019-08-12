package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.SocialActivityDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    private static final String[] RECREATIONAL_ACTIVITIES = {
            "AC521",    // Cinema
            "AC522A",   // Live performances (theatre, concerts, ballet)
            "AC523H",   // Cultural sites (historical monuments, museums, art galleries or archaeological sites)
            "AC525"     // Sports events
    };
    private static final String[] RECREATIONAL_REASONS = {
            "FIN",      // Financial reasons
            "NINT",     // No interest
            "NNB",      // None in the neighbourhood
            "OTH"       // Other
    };
    private static final String[] VOLUNTARY_ACTIVITIES = {
            "AC41A",    // Formal voluntary activities
            "AC42A"     // Informal voluntary activities
    };
    private static final String[] GROUPS = {
            "FAM",      // Family and relatives
            "FRD"       // Friends
    };

    public StringBuilder getAskingRatio() {
        return FetcherUtils.getSupportiveRatio("ilc_scp15");
    }

    public StringBuilder getDiscussionRatio() {
        return FetcherUtils.getSupportiveRatio("ilc_scp17");
    }

    public StringBuilder getGettingTogetherRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.FREQUENCY, "WEEK");
        FetcherUtils.addParams(params, ParamsConst.IND_TYPE, GROUPS);
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_scp09", params);
    }

    public StringBuilder getNonParticipationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.ACL_00, RECREATIONAL_ACTIVITIES);
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        FetcherUtils.addParams(params, ParamsConst.REASON, RECREATIONAL_REASONS);
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_scp05", params);
    }

    public StringBuilder getSocialActivitiesRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.DEG_URB, "TOTAL");
        params.put(ParamsConst.FREQUENCY, "GE1");
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_scp02", params);
    }

    public StringBuilder getVoluntaryActivitiesRatio() {
        return FetcherUtils.getActivePeopleRatio(VOLUNTARY_ACTIVITIES);
    }
}
