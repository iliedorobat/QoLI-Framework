package app.java.commons.dimensions.interactions;

import app.java.commons.constants.ParamsNames;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_TYPES_PARAMS;

public class InteractionsParams {
    public static final MultiValuedMap<String, String> ASKING_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> DISCUSSION_PARAMS_RATIO = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            GETTING_TOGETHER_RATIO_PARAMS = getGettingTogetherParams(),
            GETTING_TOGETHER_FAM_RATIO_PARAMS = getGettingTogetherParams(ParamsValues.IND_TYPE.get("family")),
            GETTING_TOGETHER_FRD_RATIO_PARAMS = getGettingTogetherParams(ParamsValues.IND_TYPE.get("friends"));

    public static final MultiValuedMap<String, String> SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("RELATIONSHIPS")
    );

    private static MultiValuedMap<String, String> getGettingTogetherParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.FREQUENCY, "WEEK");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.IND_TYPE, ParamsValues.IND_TYPE);
        return params;
    }

    private static MultiValuedMap<String, String> getGettingTogetherParams(String type) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.FREQUENCY, "WEEK");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.IND_TYPE, type);
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
    }
}
