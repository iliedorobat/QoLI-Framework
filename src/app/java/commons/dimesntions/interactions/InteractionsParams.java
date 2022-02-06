package app.java.commons.dimesntions.interactions;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class InteractionsParams {
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

    public static MultiValuedMap<String, String> getAskingParams() {
        return CommonParams.getSupportiveParams();
    }

    public static MultiValuedMap<String, String> getDiscussionParams() {
        return CommonParams.getSupportiveParams();
    }

    public static MultiValuedMap<String, String> getGettingTogetherParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.IND_TYPE, GROUPS);
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.FREQUENCY, "WEEK");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getNonParticipationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.ACL_00, RECREATIONAL_ACTIVITIES);
        FetcherUtils.addParams(params, ParamsConst.REASON, RECREATIONAL_REASONS);
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getRelationshipsSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("RELATIONSHIPS")
        );
    }
}
