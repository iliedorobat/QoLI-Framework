package app.java.commons.dimesntions.overall;

import app.java.commons.constants.ParamsConst;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class OverallExperienceParams {
    public static MultiValuedMap<String, String> getHappinessParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.UNIT, "PC");
            put(ParamsConst.SEX, "T");
        }};
    }

    public static MultiValuedMap<String, String> getHighSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("LIFE")
        );
    }
}
