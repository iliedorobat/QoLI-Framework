package ro.webdata.qoli.aggr.stats.dimensions.leisureInteract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.GEO_MEAN_PERCENT_POP;
import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PERCENT;

public class LeisureInteractAggrParams {
    public static final String LEISURE_INTERACT = "leisureInteractions";

    public static final String AREA_SATISFACTION_RATIO = LEISURE_INTERACT + ":areaSatisfactionRatio";
    public static final String ASKING_RATIO = LEISURE_INTERACT + ":askingRatio";
    public static final String DISCUSSION_RATIO = LEISURE_INTERACT + ":discussionRatio";
    public static final String FORMAL_VOLUNTARY_ACTIVITIES_RATIO = LEISURE_INTERACT + ":formalVoluntaryActivitiesRatio";
    public static final String FREQUENCY_CONTACT_FAM_RATIO = LEISURE_INTERACT + ":frequencyContactFamRatio";
    public static final String FREQUENCY_CONTACT_FRD_RATIO = LEISURE_INTERACT + ":frequencyContactFrdRatio";
    public static final String GETTING_TOGETHER_FAM_RATIO = LEISURE_INTERACT + ":gettingTogetherFamRatio";
    public static final String GETTING_TOGETHER_FRD_RATIO = LEISURE_INTERACT + ":gettingTogetherFrdRatio";
    public static final String INFORMAL_VOLUNTARY_ACTIVITIES_RATIO = LEISURE_INTERACT + ":informalVoluntaryActivitiesRatio";
    public static final String NP_FIN_CINEMA_RATIO = LEISURE_INTERACT + ":npFinCinemaRatio";
    public static final String NP_FIN_CULTURE_RATIO = LEISURE_INTERACT + ":npFinCultureRatio";
    public static final String NP_FIN_LIVE_RATIO = LEISURE_INTERACT + ":npFinLiveRatio";
    public static final String NP_FIN_SPORT_RATIO = LEISURE_INTERACT + ":npFinSportRatio";
    public static final String NP_NNB_CINEMA_RATIO = LEISURE_INTERACT + ":npNnbCinemaRatio";
    public static final String NP_NNB_CULTURE_RATIO = LEISURE_INTERACT + ":npNnbCultureRatio";
    public static final String NP_NNB_LIVE_RATIO = LEISURE_INTERACT + ":npNnbLiveRatio";
    public static final String NP_NNB_SPORT_RATIO = LEISURE_INTERACT + ":npNnbSportRatio";
    public static final String NP_NO_INTEREST_FORMAL_RATIO = LEISURE_INTERACT + ":npNoInterestFormalRatio";
    public static final String NP_NO_INTEREST_INFORMAL_RATIO = LEISURE_INTERACT + ":npNoInterestInformalRatio";
    public static final String NP_TIME_FORMAL_RATIO = LEISURE_INTERACT + ":npTimeFormalRatio";
    public static final String NP_TIME_INFORMAL_RATIO = LEISURE_INTERACT + ":npTIMEInformalRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO = LEISURE_INTERACT + ":relationshipsSatisfactionRatio";
    public static final String SOCIAL_ACTIVITIES_NP_RATIO = LEISURE_INTERACT + ":socialActivitiesNpRatio";
    public static final String SOCIAL_ACTIVITIES_RATIO = LEISURE_INTERACT + ":socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION = LEISURE_INTERACT + ":timeSpentSatisfaction";
    public static final String VOLUNTARY_ACTIVITIES_NP_RATIO = LEISURE_INTERACT + ":voluntaryActivitiesNpRatio";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
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

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, PERCENT);
        put(ASKING_RATIO, PERCENT);
        put(DISCUSSION_RATIO, PERCENT);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, PERCENT);
        put(FREQUENCY_CONTACT_FAM_RATIO, PERCENT);
        put(FREQUENCY_CONTACT_FRD_RATIO, PERCENT);
        put(GETTING_TOGETHER_FAM_RATIO, PERCENT);
        put(GETTING_TOGETHER_FRD_RATIO, PERCENT);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, PERCENT);
        put(RELATIONSHIPS_SATISFACTION_RATIO, PERCENT);
        put(SOCIAL_ACTIVITIES_NP_RATIO, GEO_MEAN_PERCENT_POP);
        put(SOCIAL_ACTIVITIES_RATIO, PERCENT);
        put(TIME_SPENT_SATISFACTION, PERCENT);
        put(VOLUNTARY_ACTIVITIES_NP_RATIO, GEO_MEAN_PERCENT_POP);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
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
