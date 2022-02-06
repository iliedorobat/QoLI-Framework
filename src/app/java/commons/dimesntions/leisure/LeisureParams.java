package app.java.commons.dimesntions.leisure;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class LeisureParams {
    public static final String[] VOLUNTARY_ACTIVITIES = {
            "AC41A",    // Formal voluntary activities
            "AC42A"     // Informal voluntary activities
    };

    public static MultiValuedMap<String, String> getSocialActivitiesParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.DEG_URB, "TOTAL");
        params.put(ParamsConst.FREQUENCY, "GE1");
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.QUANTILE, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getTimeSpentSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("TIME_SPENT")
        );
    }

    public static MultiValuedMap<String, String> getVoluntaryActivitiesParams() {
        return CommonParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES);
    }
}
