package ro.webdata.qoli.aggr.commons.dimensions.leisureInteract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeisureInteractAggrParams {
    public static final String LEISURE_INTERACT = "leisureInteractions";

    public static final String AREA_SATISFACTION_RATIO = "areaSatisfactionRatio";
    public static final String ASKING_RATIO = "askingRatio";
    public static final String DISCUSSION_RATIO = "discussionRatio";
    public static final String FORMAL_VOLUNTARY_ACTIVITIES_RATIO = "formalVoluntaryActivitiesRatio";
    public static final String FREQUENCY_CONTACT_FAM_RATIO = "frequencyContactFamRatio";
    public static final String FREQUENCY_CONTACT_FRD_RATIO = "frequencyContactFrdRatio";
    public static final String GETTING_TOGETHER_FAM_RATIO = "gettingTogetherFamRatio";
    public static final String GETTING_TOGETHER_FRD_RATIO = "gettingTogetherFrdRatio";
    public static final String INFORMAL_VOLUNTARY_ACTIVITIES_RATIO = "informalVoluntaryActivitiesRatio";
    public static final String NP_FIN_CINEMA_RATIO = "npFinCinemaRatio";
    public static final String NP_FIN_CULTURE_RATIO = "npFinCultureRatio";
    public static final String NP_FIN_LIVE_RATIO = "npFinLiveRatio";
    public static final String NP_FIN_SPORT_RATIO = "npFinSportRatio";
    public static final String NP_NNB_CINEMA_RATIO = "npNnbCinemaRatio";
    public static final String NP_NNB_CULTURE_RATIO = "npNnbCultureRatio";
    public static final String NP_NNB_LIVE_RATIO = "npNnbLiveRatio";
    public static final String NP_NNB_SPORT_RATIO = "npNnbSportRatio";
    public static final String NP_NO_INTEREST_FORMAL_RATIO = "npNoInterestFormalRatio";
    public static final String NP_NO_INTEREST_INFORMAL_RATIO = "npNoInterestInformalRatio";
    public static final String NP_TIME_FORMAL_RATIO = "npTimeFormalRatio";
    public static final String NP_TIME_INFORMAL_RATIO = "npTIMEInformalRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO = "relationshipsSatisfactionRatio";
    public static final String SOCIAL_ACTIVITIES_NP_RATIO = "socialActivitiesNpRatio";
    public static final String SOCIAL_ACTIVITIES_RATIO = "socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION = "timeSpentSatisfaction";
    public static final String VOLUNTARY_ACTIVITIES_NP_RATIO = "voluntaryActivitiesNpRatio";

    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(AREA_SATISFACTION_RATIO);
        add(ASKING_RATIO);
        add(DISCUSSION_RATIO);
        add(FORMAL_VOLUNTARY_ACTIVITIES_RATIO);
        add(FREQUENCY_CONTACT_FAM_RATIO);
        add(FREQUENCY_CONTACT_FRD_RATIO);
        add(GETTING_TOGETHER_FAM_RATIO);
        add(GETTING_TOGETHER_FRD_RATIO);
        add(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO);
        add(RELATIONSHIPS_SATISFACTION_RATIO);
        add(SOCIAL_ACTIVITIES_NP_RATIO);
        add(SOCIAL_ACTIVITIES_RATIO);
        add(TIME_SPENT_SATISFACTION);
        add(VOLUNTARY_ACTIVITIES_NP_RATIO);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, false);
        put(ASKING_RATIO, false);
        put(DISCUSSION_RATIO, false);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, false);
        put(FREQUENCY_CONTACT_FAM_RATIO, false);
        put(FREQUENCY_CONTACT_FRD_RATIO, false);
        put(GETTING_TOGETHER_FAM_RATIO, false);
        put(GETTING_TOGETHER_FRD_RATIO, false);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, false);
        put(RELATIONSHIPS_SATISFACTION_RATIO, false);
        put(SOCIAL_ACTIVITIES_NP_RATIO, true);
        put(SOCIAL_ACTIVITIES_RATIO, false);
        put(TIME_SPENT_SATISFACTION, false);
        put(VOLUNTARY_ACTIVITIES_NP_RATIO, true);
    }};
}
