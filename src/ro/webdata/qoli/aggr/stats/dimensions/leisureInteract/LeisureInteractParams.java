package ro.webdata.qoli.aggr.stats.dimensions.leisureInteract;

import ro.webdata.qoli.aggr.stats.constants.ParamsNames;
import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryParams;
import ro.webdata.qoli.aggr.data.fetch.FetcherUtils;
import ro.webdata.qoli.aggr.stats.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LeisureInteractParams {
    private static final String[] SOCIAL_INTERACTIONS = {
            ParamsValues.ACL00.get("cinema"),
            ParamsValues.ACL00.get("culture"),
            ParamsValues.ACL00.get("live"),
            ParamsValues.ACL00.get("sports")
    };

    private static final String[] VOLUNTARY_ACTIVITIES = {
            ParamsValues.ACL00.get("formal"),
            ParamsValues.ACL00.get("informal")
    };

    public static final MultiValuedMap<String, String> AREA_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            ParamsValues.SATISFACTION_LEVELS.get("high"),
            ParamsValues.SATISFACTION_TYPES.get("greenAreas")
    );

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
            INTERACTIONS_PARAMS = getInteractionsParams(ParamsValues.IND_TYPE.values()),
            INTERACTIONS_FAM_PARAMS = getInteractionsParams(ParamsValues.IND_TYPE.get("family")),
            INTERACTIONS_FRD_PARAMS = getInteractionsParams(ParamsValues.IND_TYPE.get("friends"));

    public static final MultiValuedMap<String, String>
            SOCIAL_ACTIVITIES_NP_PARAMS = getActivitiesNpParams(SOCIAL_INTERACTIONS, ParamsValues.SOCIAL_ACTIVITIES_NP_REASON),

            NP_FIN_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("cinema"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("financial")),
            NP_FIN_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("culture"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("financial")),
            NP_FIN_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("live"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("financial")),
            NP_FIN_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("sports"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("financial")),

            NP_NNB_CIN_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("cinema"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("away")),
            NP_NNB_CULT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("culture"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("away")),
            NP_NNB_LIVE_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("live"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("away")),
            NP_NNB_SPORT_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("sports"), ParamsValues.SOCIAL_ACTIVITIES_NP_REASON.get("away"));

    public static final MultiValuedMap<String, String> SOCIAL_ACTIVITIES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.DEG_URB, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.FREQUENCY, "GE1");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> TIME_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            ParamsValues.SATISFACTION_LEVELS.get("high"),
            ParamsValues.SATISFACTION_TYPES.get("timeSpent")
    );

    public static final MultiValuedMap<String, String> REL_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            ParamsValues.SATISFACTION_LEVELS.get("high"),
            ParamsValues.SATISFACTION_TYPES.get("relationships")
    );

    public static final MultiValuedMap<String, String>
            VOLUNTARY_ACTIVITIES_NP_RATIO_PARAMS = getActivitiesNpParams(VOLUNTARY_ACTIVITIES, ParamsValues.VOLUNTARY_ACTIVITIES_NP_REASON),

            NP_NO_INTEREST_FORMAL_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("formal"), ParamsValues.VOLUNTARY_ACTIVITIES_NP_REASON.get("noInterest")),
            NP_NO_INTEREST_INFORMAL_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("informal"), ParamsValues.VOLUNTARY_ACTIVITIES_NP_REASON.get("noInterest")),

            NP_TIME_FORMAL_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("formal"), ParamsValues.VOLUNTARY_ACTIVITIES_NP_REASON.get("time")),
            NP_TIME_INFORMAL_RATIO_PARAMS = getNonParticipationParams(ParamsValues.ACL00.get("informal"), ParamsValues.VOLUNTARY_ACTIVITIES_NP_REASON.get("time"));

    public static MultiValuedMap<String, String>
            VOLUNTARY_RATIO_PARAMS = AuxiliaryParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES),
            FORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00.get("formal")),
            INFORMAL_VOLUNTARY_RATIO_PARAMS = getVoluntaryActivitiesParams(ParamsValues.ACL00.get("informal"));

    private static MultiValuedMap<String, String> getInteractionsParams(String type) {
        List<String> types = new ArrayList<>() {{ add(type); }};
        return getInteractionsParams(types);
    }

    private static MultiValuedMap<String, String> getInteractionsParams(Collection<String> types) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.FREQUENCY, "WEEK");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.IND_TYPE, types);
        return params;
    }

    private static MultiValuedMap<String, String> getNonParticipationParams(String activity, String reason) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.ACL_00, activity);
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.REASON, reason);
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getActivitiesNpParams(String[] activities, HashMap<String, String> reasons) {
        MultiValuedMap<String, String> params = AuxiliaryParams.getActivePeopleParams(activities);
        FetcherUtils.addParams(params, ParamsNames.REASON, reasons);
        return params;
    }

    private static MultiValuedMap<String, String> getVoluntaryActivitiesParams(String activity) {
        String[] activities = { activity };
        return AuxiliaryParams.getActivePeopleParams(activities);
    }
}
