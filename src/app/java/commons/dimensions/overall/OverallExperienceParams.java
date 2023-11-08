package app.java.commons.dimensions.overall;

import app.java.commons.constants.ParamsNames;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class OverallExperienceParams {
    private static final String[] HAPPINESS_LEVELS = {
            "ALW",  // Always
            "MOST"  // Most of the time
    };

    public static final MultiValuedMap<String, String> HAPPINESS_RATIO_PARAMS = getHappinessParams();

    public static final MultiValuedMap<String, String> HIGH_SATISFACTION_RATIO_PARAMS = CommonParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("LIFE")
    );

    private static MultiValuedMap<String, String> getHappinessParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.FREQUENCY, HAPPINESS_LEVELS);
        return params;
    }
}
