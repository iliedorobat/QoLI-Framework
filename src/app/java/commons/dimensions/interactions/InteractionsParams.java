package app.java.commons.dimensions.interactions;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class InteractionsParams {
    public static MultiValuedMap<String, String> getAskingParams() {
        return CommonParams.getSupportiveParams();
    }

    public static MultiValuedMap<String, String> getDiscussionParams() {
        return CommonParams.getSupportiveParams();
    }

    public static MultiValuedMap<String, String> getGettingTogetherParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.FREQUENCY, "WEEK");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.IND_TYPE, ParamsValues.IND_TYPE);
        return params;
    }

    public static MultiValuedMap<String, String> getGettingTogetherParams(String type) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.FREQUENCY, "WEEK");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.IND_TYPE, type);
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getRelationshipsSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("RELATIONSHIPS")
        );
    }

    public static final MultiValuedMap<String, String>
            ASKING_RATIO_PARAMS = getAskingParams(),
            DISCUSSION_PARAMS_RATIO = getDiscussionParams(),
            GETTING_TOGETHER_FAM_RATIO_PARAMS = getGettingTogetherParams(ParamsValues.IND_TYPE.get("family")),
            GETTING_TOGETHER_FRD_RATIO_PARAMS = getGettingTogetherParams(ParamsValues.IND_TYPE.get("friends")),
            SATISFACTION_RATIO_PARAMS = getRelationshipsSatisfactionParams();
}
