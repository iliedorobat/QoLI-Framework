package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.InteractionsDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class InteractionsDAOImpl implements InteractionsDAO {
    private static final String HIGH_SATIS_LEVEL = "HIGH";
    private static final String RELATIONSHIPS_SATISFACTION = "RELSAT";
    private static final String[] GROUPS = {
            "FAM",      // Family and relatives
            "FRD"       // Friends
    };
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

    public StringBuilder getRelationshipsSatisfaction() {
        return FetcherUtils.getSatisfactionRatio(HIGH_SATIS_LEVEL, RELATIONSHIPS_SATISFACTION);
    }
}
