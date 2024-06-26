package ro.webdata.qoli.aggr.stats.dimensions.leisureInteract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PERCENT;

public class LeisureInteractAggrParams {
    public static final String LEISURE_INTERACT = "leisureInteractions";

    public static final String AREA_SATISFACTION_RATIO = LEISURE_INTERACT + ":areaSatisfactionRatio";
    public static final String ASKING_RATIO = LEISURE_INTERACT + ":askingRatio";
    public static final String DISCUSSION_RATIO = LEISURE_INTERACT + ":discussionRatio";
    public static final String FORMAL_VOLUNTARY_ACTIVITIES_RATIO = LEISURE_INTERACT + ":formalVoluntaryActivitiesRatio";
    public static final String FREQUENCY_CONTACT_FAM_RATIO = LEISURE_INTERACT + ":frequencyContactFamRatio";
    public static final String FREQUENCY_CONTACT_FRD_RATIO = LEISURE_INTERACT + ":frequencyContactFrdRatio";
    public static final String FREQUENCY_CONTACT_RATIO = LEISURE_INTERACT + ":frequencyContactRatio";
    public static final String GETTING_TOGETHER_FAM_RATIO = LEISURE_INTERACT + ":gettingTogetherFamRatio";
    public static final String GETTING_TOGETHER_FRD_RATIO = LEISURE_INTERACT + ":gettingTogetherFrdRatio";
    public static final String GETTING_TOGETHER_RATIO = LEISURE_INTERACT + ":gettingTogetherRatio";
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
    public static final String NP_TIME_INFORMAL_RATIO = LEISURE_INTERACT + ":npTimeInformalRatio";
    public static final String NP_RATIO = LEISURE_INTERACT + ":npRatio";
    public static final String PARTICIPATION_RATIO = LEISURE_INTERACT + ":participationRatio";
    public static final String RELATIONSHIPS_SATISFACTION_RATIO = LEISURE_INTERACT + ":relationshipsSatisfactionRatio";
    public static final String SOCIAL_ACTIVITIES_RATIO = LEISURE_INTERACT + ":socialActivitiesRatio";
    public static final String TIME_SPENT_SATISFACTION = LEISURE_INTERACT + ":timeSpentSatisfaction";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, "Green Areas Satisfaction Ratio");
        put(ASKING_RATIO, "Asking Ratio");
        put(DISCUSSION_RATIO, "Discussion Ratio");
        put(FREQUENCY_CONTACT_RATIO, "Frequency Contact Ratio");
        put(GETTING_TOGETHER_RATIO, "Getting Together Ratio");
        put(NP_RATIO, "Non-Participation in Social Activities & Voluntaries Activities Ratio");
        put(PARTICIPATION_RATIO, "Participation Ratio");
        put(RELATIONSHIPS_SATISFACTION_RATIO, "Relationships Satisfaction Ratio");
        put(TIME_SPENT_SATISFACTION, "Time Satisfaction Ratio");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, PERCENT);
        put(ASKING_RATIO, PERCENT);
        put(DISCUSSION_RATIO, PERCENT);
        put(FREQUENCY_CONTACT_RATIO, PERCENT);
        put(GETTING_TOGETHER_RATIO, PERCENT);
        put(NP_RATIO, PERCENT);
        put(PARTICIPATION_RATIO, PERCENT);
        put(RELATIONSHIPS_SATISFACTION_RATIO, PERCENT);
        put(TIME_SPENT_SATISFACTION, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATES = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, false);
        put(ASKING_RATIO, false);
        put(DISCUSSION_RATIO, false);
        put(FREQUENCY_CONTACT_RATIO, false);
        put(GETTING_TOGETHER_RATIO, false);
        put(NP_RATIO, true);
        put(PARTICIPATION_RATIO, false);
        put(RELATIONSHIPS_SATISFACTION_RATIO, false);
        put(TIME_SPENT_SATISFACTION, false);
    }};

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, "Green Areas Satisfaction Ratio");
        put(ASKING_RATIO, "Asking Ratio");
        put(DISCUSSION_RATIO, "Discussion Ratio");
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, "Formal Voluntary Ratio");
        put(FREQUENCY_CONTACT_FAM_RATIO, "Frequency Contact Ratio (Family)");
        put(FREQUENCY_CONTACT_FRD_RATIO, "Frequency Contact Ratio (Friends)");
        put(GETTING_TOGETHER_FAM_RATIO, "Getting Together Ratio (Family)");
        put(GETTING_TOGETHER_FRD_RATIO, "Getting Together Ratio (Friends)");
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, "Informal Voluntary Ratio");
        put(NP_FIN_CINEMA_RATIO, "Non-Participation in Cinema for financial reasons");
        put(NP_FIN_CULTURE_RATIO, "Non-Participation in Cultural Sites for financial reasons");
        put(NP_FIN_LIVE_RATIO, "Non-Participation in Live Performances for financial reasons");
        put(NP_FIN_SPORT_RATIO, "Non-Participation in Sports Events for financial reasons");
        put(NP_NNB_CINEMA_RATIO, "Non-Participation in Cinema due to lack of interest");
        put(NP_NNB_CULTURE_RATIO, "Non-Participation in Cultural Sites due to lack of interest");
        put(NP_NNB_LIVE_RATIO, "Non-Participation in Live Performances due to lack of interest");
        put(NP_NNB_SPORT_RATIO, "Non-Participation in Sports Events due to lack of interest");
        put(NP_NO_INTEREST_FORMAL_RATIO, "Non-Participation in Formal Voluntary Activities due to lack of interest");
        put(NP_NO_INTEREST_INFORMAL_RATIO, "Non-Participation in Informal Voluntary Activities due to lack of interest");
        put(NP_TIME_FORMAL_RATIO, "Non-Participation in Formal Voluntary Activities due to lack of time");
        put(NP_TIME_INFORMAL_RATIO, "Non-Participation in Informal Voluntary Activities due to lack of time");
        put(RELATIONSHIPS_SATISFACTION_RATIO, "Relationships Satisfaction Ratio");
        put(SOCIAL_ACTIVITIES_RATIO, "Social Activities Ratio");
        put(TIME_SPENT_SATISFACTION, "Time Satisfaction Ratio");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, PERCENT);
        put(ASKING_RATIO, PERCENT);
        put(DISCUSSION_RATIO, PERCENT);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, PERCENT);
        put(FREQUENCY_CONTACT_FAM_RATIO, PERCENT);
        put(FREQUENCY_CONTACT_FRD_RATIO, PERCENT);
        put(GETTING_TOGETHER_FAM_RATIO, PERCENT);
        put(GETTING_TOGETHER_FRD_RATIO, PERCENT);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, PERCENT);
        put(NP_FIN_CINEMA_RATIO, PERCENT);
        put(NP_FIN_CULTURE_RATIO, PERCENT);
        put(NP_FIN_LIVE_RATIO, PERCENT);
        put(NP_FIN_SPORT_RATIO, PERCENT);
        put(NP_NNB_CINEMA_RATIO, PERCENT);
        put(NP_NNB_CULTURE_RATIO, PERCENT);
        put(NP_NNB_LIVE_RATIO, PERCENT);
        put(NP_NNB_SPORT_RATIO, PERCENT);
        put(NP_NO_INTEREST_FORMAL_RATIO, PERCENT);
        put(NP_NO_INTEREST_INFORMAL_RATIO, PERCENT);
        put(NP_TIME_FORMAL_RATIO, PERCENT);
        put(NP_TIME_INFORMAL_RATIO, PERCENT);
        put(RELATIONSHIPS_SATISFACTION_RATIO, PERCENT);
        put(SOCIAL_ACTIVITIES_RATIO, PERCENT);
        put(TIME_SPENT_SATISFACTION, PERCENT);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATES = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, false);
        put(ASKING_RATIO, false);
        put(DISCUSSION_RATIO, false);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, false);
        put(FREQUENCY_CONTACT_FAM_RATIO, false);
        put(FREQUENCY_CONTACT_FRD_RATIO, false);
        put(GETTING_TOGETHER_FAM_RATIO, false);
        put(GETTING_TOGETHER_FRD_RATIO, false);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, false);
        put(NP_FIN_CINEMA_RATIO, true);
        put(NP_FIN_CULTURE_RATIO, true);
        put(NP_FIN_LIVE_RATIO, true);
        put(NP_FIN_SPORT_RATIO, true);
        put(NP_NNB_CINEMA_RATIO, true);
        put(NP_NNB_CULTURE_RATIO, true);
        put(NP_NNB_LIVE_RATIO, true);
        put(NP_NNB_SPORT_RATIO, true);
        put(NP_NO_INTEREST_FORMAL_RATIO, true);
        put(NP_NO_INTEREST_INFORMAL_RATIO, true);
        put(NP_TIME_FORMAL_RATIO, true);
        put(NP_TIME_INFORMAL_RATIO, true);
        put(RELATIONSHIPS_SATISFACTION_RATIO, false);
        put(SOCIAL_ACTIVITIES_RATIO, false);
        put(TIME_SPENT_SATISFACTION, false);
    }};
}
