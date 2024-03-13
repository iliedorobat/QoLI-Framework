package app.java.commons.dimensions.leisureInteract;

import app.java.commons.constants.ParamsNames;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_TYPES_PARAMS;

public class LeisureInteractParams {
    private static final String[] VOLUNTARY_ACTIVITIES = {
            ParamsValues.ACL00_LEISURE.get("formal"),
            ParamsValues.ACL00_LEISURE.get("informal")
    };

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

    public static final MultiValuedMap<String, String>
            NON_PARTICIPATION_PARAMS = getNonParticipationParams(),

            NP_FIN_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("financial")),
            NP_FIN_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("financial")),
            NP_FIN_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("financial")),
            NP_FIN_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("financial")),

            NP_NNB_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("away")),
            NP_NNB_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("away")),
            NP_NNB_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("away")),
            NP_NNB_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("away"));

    public static final MultiValuedMap<String, String> SOCIAL_ACTIVITIES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.DEG_URB, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.FREQUENCY, "GE1");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> TIME_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("TIME_SPENT")
    );

    public static final MultiValuedMap<String, String> REL_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("RELATIONSHIPS")
    );

    public static MultiValuedMap<String, String>
            VOLUNTARY_RATIO_PARAMS = AuxiliaryParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES),
            FORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("formal")),
            INFORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("informal"));

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

    private static MultiValuedMap<String, String> getNonParticipationParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.ACL_00, ParamsValues.ACL00_INTERACTIONS);
        FetcherUtils.addParams(params, ParamsNames.REASON, ParamsValues.REASON);
        return params;
    }

    private static MultiValuedMap<String, String> getNonParticipationParams(String type, String reason) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.ACL_00, type);
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.REASON, reason);
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getVoluntaryActivitiesParams(String activity) {
        String[] VOLUNTARY_ACTIVITIES = { activity };
        return AuxiliaryParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES);
    }
}
