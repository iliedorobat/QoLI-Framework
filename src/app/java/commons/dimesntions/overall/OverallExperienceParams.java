package app.java.commons.dimesntions.overall;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class OverallExperienceParams {
    public static MultiValuedMap<String, String> getHappinessParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.SEX, "T");
        return params;
    }

    public static MultiValuedMap<String, String> getHighSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("LIFE")
        );
    }
}
