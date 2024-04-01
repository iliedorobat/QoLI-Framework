package ro.webdata.qoli.aggr.stats.dimensions.leisureInteract;

import java.util.HashMap;
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

    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, "Green Areas Satisfaction Ratio");
        put(ASKING_RATIO, "Asking Ratio");
        put(DISCUSSION_RATIO, "Discussion Ratio");
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, "Formal Voluntary Ratio");
        put(FREQUENCY_CONTACT_FAM_RATIO, "Frequency Contact Ratio (Family)");
        put(FREQUENCY_CONTACT_FRD_RATIO, "Frequency Contact Ratio (Friends)");
        put(GETTING_TOGETHER_FAM_RATIO, "Getting Together Ratio (Family)");
        put(GETTING_TOGETHER_FRD_RATIO, "Getting Together Ratio (Friends)");
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, "Informal Voluntary Ratio");
        put(RELATIONSHIPS_SATISFACTION_RATIO, "Relationships Satisfaction Ratio");
        put(SOCIAL_ACTIVITIES_NP_RATIO, "Non-Participation in Social Activities Ratio");
        put(SOCIAL_ACTIVITIES_RATIO, "Social Activities Ratio");
        put(TIME_SPENT_SATISFACTION, "Time Satisfaction Ratio");
        put(VOLUNTARY_ACTIVITIES_NP_RATIO, "Non-Participation in Voluntary Activities Ratio");
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
