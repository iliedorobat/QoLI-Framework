package ro.webdata.qoli.aggr.stats.dimensions.leisureInteract;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractAggrParams.*;

public class LeisureInteractStats {
    private static final Map<String, Number>
            initAreaSatisfactionRatio = Initializer.initConsolidatedMap(LeisureInteractParams.AREA_SATISFACTION_RATIO_PARAMS, LeisureInteractPaths.AREA_SATISFACTION_RATIO_PATH),
            initAskingRatio = Initializer.initConsolidatedMap(LeisureInteractParams.ASKING_RATIO_PARAMS, LeisureInteractPaths.ASKING_RATIO_PATH),
            initDiscussionRatio = Initializer.initConsolidatedMap(LeisureInteractParams.DISCUSSION_PARAMS_RATIO, LeisureInteractPaths.DISCUSSION_RATIO_PATH),
            initFormalVoluntaryRatio = Initializer.initConsolidatedMap(LeisureInteractParams.FORMAL_VOLUNTARY_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_RATIO_PATH),
            initFrequencyContactFamRatio = Initializer.initConsolidatedMap(LeisureInteractParams.INTERACTIONS_FAM_PARAMS, LeisureInteractPaths.FREQUENCY_CONTACT_RATIO_PATH),
            initFrequencyContactFrdRatio = Initializer.initConsolidatedMap(LeisureInteractParams.INTERACTIONS_FRD_PARAMS, LeisureInteractPaths.FREQUENCY_CONTACT_RATIO_PATH),
            initGettingTogetherFamRatio = Initializer.initConsolidatedMap(LeisureInteractParams.INTERACTIONS_FAM_PARAMS, LeisureInteractPaths.GETTING_TOGETHER_RATIO_PATH),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedMap(LeisureInteractParams.INTERACTIONS_FRD_PARAMS, LeisureInteractPaths.GETTING_TOGETHER_RATIO_PATH),
            initInformalVoluntaryRatio = Initializer.initConsolidatedMap(LeisureInteractParams.INFORMAL_VOLUNTARY_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_RATIO_PATH),
            initRelSatisfactionRatio = Initializer.initConsolidatedMap(LeisureInteractParams.REL_SATISFACTION_RATIO_PARAMS, LeisureInteractPaths.REL_SATISFACTION_RATIO_PATH),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(LeisureInteractParams.SOCIAL_ACTIVITIES_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_RATIO_PATH),
            initTimeSatisfactionRatio = Initializer.initConsolidatedMap(LeisureInteractParams.TIME_SATISFACTION_RATIO_PARAMS, LeisureInteractPaths.TIME_SATISFACTION_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_FIN_CIN_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinCultRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_FIN_CULT_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinLiveRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_FIN_LIVE_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinSportRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_FIN_SPORT_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NNB_CIN_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbCultRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NNB_CULT_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbLiveRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NNB_LIVE_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbSportRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NNB_SPORT_RATIO_PARAMS, LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpNoInterestFormalRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NO_INTEREST_FORMAL_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpNoInterestInformalRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_NO_INTEREST_INFORMAL_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpTimeFormalRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_TIME_FORMAL_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpTimeInformalRatio = Initializer.initConsolidatedMap(LeisureInteractParams.NP_TIME_INFORMAL_RATIO_PARAMS, LeisureInteractPaths.VOLUNTARY_ACTIVITIES_NP_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate date used to calculate socialActivitiesNpRatio
            npFinCinRatio = Preparation.prepareData(initNpFinCinRatio),
            npFinCultRatio = Preparation.prepareData(initNpFinCultRatio),
            npFinLiveRatio = Preparation.prepareData(initNpFinLiveRatio),
            npFinSportRatio = Preparation.prepareData(initNpFinSportRatio),

            // Intermediate date used to calculate socialActivitiesNpRatio
            npNnbCinRatio = Preparation.prepareData(initNpNnbCinRatio),
            npNnbCultRatio = Preparation.prepareData(initNpNnbCultRatio),
            npNnbLiveRatio = Preparation.prepareData(initNpNnbLiveRatio),
            npNnbSportRatio = Preparation.prepareData(initNpNnbSportRatio),

            // Intermediate date used to calculate voluntaryActivitiesNpRatio
            npNoInterestFormalRatio = Preparation.prepareData(initNpNoInterestFormalRatio),
            npNoInterestInformalRatio = Preparation.prepareData(initNpNoInterestInformalRatio),
            npTimeFormalRatio = Preparation.prepareData(initNpTimeFormalRatio),
            npTimeInformalRatio = Preparation.prepareData(initNpTimeInformalRatio),

            areaSatisfactionRatio = Preparation.prepareData(initAreaSatisfactionRatio),
            askingRatio = Preparation.prepareData(initAskingRatio),
            discussionRatio = Preparation.prepareData(initDiscussionRatio),
            formalVoluntaryRatio = Preparation.prepareData(initFormalVoluntaryRatio),
            frequencyContactFamRatio = Preparation.prepareData(initFrequencyContactFamRatio),
            frequencyContactFrdRatio = Preparation.prepareData(initFrequencyContactFrdRatio),
            gettingTogetherFamRatio = Preparation.prepareData(initGettingTogetherFamRatio),
            gettingTogetherFrdRatio = Preparation.prepareData(initGettingTogetherFrdRatio),
            informalVoluntaryRatio = Preparation.prepareData(initInformalVoluntaryRatio),
            relSatisfactionRatio = Preparation.prepareData(initRelSatisfactionRatio),
            socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio),
            timeSatisfactionRatio = Preparation.prepareData(initTimeSatisfactionRatio),

            socialActivitiesNpRatio = consolidateSocialActivitiesNpRatio(),
            voluntaryActivitiesNpRatio = consolidateVoluntaryActivitiesNpRatio();

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(AREA_SATISFACTION_RATIO, Preparation.filterMap(initAreaSatisfactionRatio));
        put(ASKING_RATIO, Preparation.filterMap(initAskingRatio));
        put(DISCUSSION_RATIO, Preparation.filterMap(initDiscussionRatio));
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, Preparation.filterMap(initFormalVoluntaryRatio));
        put(FREQUENCY_CONTACT_FAM_RATIO, Preparation.filterMap(initFrequencyContactFamRatio));
        put(FREQUENCY_CONTACT_FRD_RATIO, Preparation.filterMap(initFrequencyContactFrdRatio));
        put(GETTING_TOGETHER_FAM_RATIO, Preparation.filterMap(initGettingTogetherFamRatio));
        put(GETTING_TOGETHER_FRD_RATIO, Preparation.filterMap(initGettingTogetherFrdRatio));
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, Preparation.filterMap(initInformalVoluntaryRatio));
        put(RELATIONSHIPS_SATISFACTION_RATIO, Preparation.filterMap(initRelSatisfactionRatio));
        put(SOCIAL_ACTIVITIES_RATIO, Preparation.filterMap(initSocialActivitiesRatio));
        put(TIME_SPENT_SATISFACTION, Preparation.filterMap(initTimeSatisfactionRatio));

        put(NP_FIN_CINEMA_RATIO, Preparation.filterMap(initNpFinCinRatio));
        put(NP_FIN_CULTURE_RATIO, Preparation.filterMap(initNpFinCultRatio));
        put(NP_FIN_LIVE_RATIO, Preparation.filterMap(initNpFinLiveRatio));
        put(NP_FIN_SPORT_RATIO, Preparation.filterMap(initNpFinSportRatio));

        put(NP_NNB_CINEMA_RATIO, Preparation.filterMap(initNpNnbCinRatio));
        put(NP_NNB_CULTURE_RATIO, Preparation.filterMap(initNpNnbCultRatio));
        put(NP_NNB_LIVE_RATIO, Preparation.filterMap(initNpNnbLiveRatio));
        put(NP_NNB_SPORT_RATIO, Preparation.filterMap(initNpNnbSportRatio));

        put(NP_NO_INTEREST_FORMAL_RATIO, Preparation.filterMap(initNpNoInterestFormalRatio));
        put(NP_NO_INTEREST_INFORMAL_RATIO, Preparation.filterMap(initNpNoInterestInformalRatio));
        put(NP_TIME_FORMAL_RATIO, Preparation.filterMap(initNpTimeFormalRatio));
        put(NP_TIME_INFORMAL_RATIO, Preparation.filterMap(initNpTimeInformalRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO, areaSatisfactionRatio);
        put(ASKING_RATIO, askingRatio);
        put(DISCUSSION_RATIO, discussionRatio);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO, formalVoluntaryRatio);
        put(FREQUENCY_CONTACT_FAM_RATIO, frequencyContactFamRatio);
        put(FREQUENCY_CONTACT_FRD_RATIO, frequencyContactFrdRatio);
        put(GETTING_TOGETHER_FAM_RATIO, gettingTogetherFamRatio);
        put(GETTING_TOGETHER_FRD_RATIO, gettingTogetherFrdRatio);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO, informalVoluntaryRatio);
        put(RELATIONSHIPS_SATISFACTION_RATIO, relSatisfactionRatio);
        put(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatio);
        put(TIME_SPENT_SATISFACTION, timeSatisfactionRatio);

        put(SOCIAL_ACTIVITIES_NP_RATIO, socialActivitiesNpRatio);
        put(VOLUNTARY_ACTIVITIES_NP_RATIO, voluntaryActivitiesNpRatio);

        put(NP_FIN_CINEMA_RATIO, npFinCinRatio);
        put(NP_FIN_CULTURE_RATIO, npFinCultRatio);
        put(NP_FIN_LIVE_RATIO, npFinLiveRatio);
        put(NP_FIN_SPORT_RATIO, npFinSportRatio);

        put(NP_NNB_CINEMA_RATIO, npNnbCinRatio);
        put(NP_NNB_CULTURE_RATIO, npNnbCultRatio);
        put(NP_NNB_LIVE_RATIO, npNnbLiveRatio);
        put(NP_NNB_SPORT_RATIO, npNnbSportRatio);

        put(NP_NO_INTEREST_FORMAL_RATIO, npNoInterestFormalRatio);
        put(NP_NO_INTEREST_INFORMAL_RATIO, npNoInterestInformalRatio);
        put(NP_TIME_FORMAL_RATIO, npTimeFormalRatio);
        put(NP_TIME_INFORMAL_RATIO, npTimeInformalRatio);
    }};

    public static Map<String, Number> generateStats() {
        return StatsUtils.generateStats(List.copyOf(ALLOWED_PARAMS.keySet()), IS_REVERSED, preparedIndicators);
    }

    public static Map<String, Number> generateStats(List<String> aggrList) {
        return StatsUtils.generateStats(aggrList, List.copyOf(ALLOWED_PARAMS.keySet()), IS_REVERSED, preparedIndicators);
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, LEISURE_INTERACT, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, LEISURE_INTERACT, targetYear, indStatus);
    }

    // Aggregate the "Non Participation Ratios" into a single ratio
    private static Map<String, Number> consolidateSocialActivitiesNpRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double npCinRatio = npFinCinRatio.get(key).doubleValue() + npNnbCinRatio.get(key).doubleValue();
                double npCultRatio = npFinCultRatio.get(key).doubleValue() + npNnbCultRatio.get(key).doubleValue();
                double npLiveRatio = npFinLiveRatio.get(key).doubleValue() + npNnbLiveRatio.get(key).doubleValue();
                double npSportRatio = npFinSportRatio.get(key).doubleValue() + npNnbSportRatio.get(key).doubleValue();

                double product = 1
                        * npCinRatio
                        * npCultRatio
                        * npLiveRatio
                        * npSportRatio;

                Number value = Math.pow(product, 1.0/4);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    // Aggregate the "Non Participation Ratios" into a single ratio
    private static Map<String, Number> consolidateVoluntaryActivitiesNpRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double npFormalRatio = npNoInterestFormalRatio.get(key).doubleValue() + npTimeFormalRatio.get(key).doubleValue();
                double npInformalRatio = npNoInterestInformalRatio.get(key).doubleValue() + npTimeInformalRatio.get(key).doubleValue();

                double product = 1
                        * npFormalRatio
                        * npInformalRatio;

                Number value = Math.pow(product, 1.0/2);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
