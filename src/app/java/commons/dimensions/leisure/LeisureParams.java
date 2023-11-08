package app.java.commons.dimensions.leisure;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class LeisureParams {
    public static final String[] VOLUNTARY_ACTIVITIES = {
            ParamsValues.ACL00_LEISURE.get("formal"),
            ParamsValues.ACL00_LEISURE.get("informal")
    };

    public static MultiValuedMap<String, String> getSocialActivitiesParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.DEG_URB, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.FREQUENCY, "GE1");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getNonParticipationParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.ACL_00, ParamsValues.ACL00_INTERACTIONS);
        FetcherUtils.addParams(params, ParamsConst.REASON, ParamsValues.REASON);
        return params;
    }

    public static MultiValuedMap<String, String> getNonParticipationParams(String type, String reason) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.ACL_00, type);
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.REASON, reason);
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
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

    public static MultiValuedMap<String, String> getVoluntaryActivitiesParams(String activity) {
        String[] VOLUNTARY_ACTIVITIES = { activity };
        return CommonParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES);
    }

    public static final MultiValuedMap<String, String>
            FORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("formal")),
            INFORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("informal")),
            SATISFACTION_RATIO_PARAMS = getTimeSpentSatisfactionParams(),
            SOCIAL_ACTIVITIES_RATIO_PARAMS = getSocialActivitiesParams(),

            NP_FIN_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("financial")),
            NP_FIN_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("financial")),
            NP_FIN_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("financial")),
            NP_FIN_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("financial")),

            NP_NNB_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("away")),
            NP_NNB_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("away")),
            NP_NNB_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("away")),
            NP_NNB_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("away"));
}
