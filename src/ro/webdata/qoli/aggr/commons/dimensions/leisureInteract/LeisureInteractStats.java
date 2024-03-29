package ro.webdata.qoli.aggr.commons.dimensions.leisureInteract;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.Print;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;
import ro.webdata.qoli.aggr.commons.utils.MapUtils;
import ro.webdata.qoli.aggr.commons.utils.MathUtils;
import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        put(LeisureInteractPaths.AREA_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initAreaSatisfactionRatio));
        put(LeisureInteractPaths.ASKING_RATIO_FILE_NAME, Preparation.filterMap(initAskingRatio));
        put(LeisureInteractPaths.DISCUSSION_RATIO_FILE_NAME, Preparation.filterMap(initDiscussionRatio));
        put(LeisureInteractPaths.FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initFormalVoluntaryRatio));
        put(LeisureInteractPaths.FREQUENCY_CONTACT_FAM_RATIO_FILE_NAME, Preparation.filterMap(initFrequencyContactFamRatio));
        put(LeisureInteractPaths.FREQUENCY_CONTACT_FRD_RATIO_FILE_NAME, Preparation.filterMap(initFrequencyContactFrdRatio));
        put(LeisureInteractPaths.GETTING_TOGETHER_FAM_RATIO_FILE_NAME, Preparation.filterMap(initGettingTogetherFamRatio));
        put(LeisureInteractPaths.GETTING_TOGETHER_FRD_RATIO_FILE_NAME, Preparation.filterMap(initGettingTogetherFrdRatio));
        put(LeisureInteractPaths.INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initInformalVoluntaryRatio));
        put(LeisureInteractPaths.RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initRelSatisfactionRatio));
        put(LeisureInteractPaths.SOCIAL_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initSocialActivitiesRatio));
        put(LeisureInteractPaths.TIME_SPENT_SATISFACTION_FILE_NAME, Preparation.filterMap(initTimeSatisfactionRatio));

        put(LeisureInteractPaths.NP_FIN_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCinRatio));
        put(LeisureInteractPaths.NP_FIN_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCultRatio));
        put(LeisureInteractPaths.NP_FIN_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinLiveRatio));
        put(LeisureInteractPaths.NP_FIN_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpFinSportRatio));

        put(LeisureInteractPaths.NP_NNB_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCinRatio));
        put(LeisureInteractPaths.NP_NNB_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCultRatio));
        put(LeisureInteractPaths.NP_NNB_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbLiveRatio));
        put(LeisureInteractPaths.NP_NNB_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbSportRatio));

        put(LeisureInteractPaths.NP_NO_INTEREST_FORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpNoInterestFormalRatio));
        put(LeisureInteractPaths.NP_NO_INTEREST_INFORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpNoInterestInformalRatio));
        put(LeisureInteractPaths.NP_TIME_FORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpTimeFormalRatio));
        put(LeisureInteractPaths.NP_TIME_INFORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpTimeInformalRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(LeisureInteractPaths.AREA_SATISFACTION_RATIO_FILE_NAME, areaSatisfactionRatio);
        put(LeisureInteractPaths.ASKING_RATIO_FILE_NAME, askingRatio);
        put(LeisureInteractPaths.DISCUSSION_RATIO_FILE_NAME, discussionRatio);
        put(LeisureInteractPaths.FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, formalVoluntaryRatio);
        put(LeisureInteractPaths.FREQUENCY_CONTACT_FAM_RATIO_FILE_NAME, frequencyContactFamRatio);
        put(LeisureInteractPaths.FREQUENCY_CONTACT_FRD_RATIO_FILE_NAME, frequencyContactFrdRatio);
        put(LeisureInteractPaths.GETTING_TOGETHER_FAM_RATIO_FILE_NAME, gettingTogetherFamRatio);
        put(LeisureInteractPaths.GETTING_TOGETHER_FRD_RATIO_FILE_NAME, gettingTogetherFrdRatio);
        put(LeisureInteractPaths.INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, informalVoluntaryRatio);
        put(LeisureInteractPaths.RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME, relSatisfactionRatio);
        put(LeisureInteractPaths.SOCIAL_ACTIVITIES_RATIO_FILE_NAME, socialActivitiesRatio);
        put(LeisureInteractPaths.TIME_SPENT_SATISFACTION_FILE_NAME, timeSatisfactionRatio);

        put(LeisureInteractPaths.SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME, socialActivitiesNpRatio);
        put(LeisureInteractPaths.VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME, voluntaryActivitiesNpRatio);

        put(LeisureInteractPaths.NP_FIN_CINEMA_RATIO_FILE_NAME, npFinCinRatio);
        put(LeisureInteractPaths.NP_FIN_CULTURE_RATIO_FILE_NAME, npFinCultRatio);
        put(LeisureInteractPaths.NP_FIN_LIVE_RATIO_FILE_NAME, npFinLiveRatio);
        put(LeisureInteractPaths.NP_FIN_SPORT_RATIO_FILE_NAME, npFinSportRatio);

        put(LeisureInteractPaths.NP_NNB_CINEMA_RATIO_FILE_NAME, npNnbCinRatio);
        put(LeisureInteractPaths.NP_NNB_CULTURE_RATIO_FILE_NAME, npNnbCultRatio);
        put(LeisureInteractPaths.NP_NNB_LIVE_RATIO_FILE_NAME, npNnbLiveRatio);
        put(LeisureInteractPaths.NP_NNB_SPORT_RATIO_FILE_NAME, npNnbSportRatio);

        put(LeisureInteractPaths.NP_NO_INTEREST_FORMAL_RATIO_FILE_NAME, npNoInterestFormalRatio);
        put(LeisureInteractPaths.NP_NO_INTEREST_INFORMAL_RATIO_FILE_NAME, npNoInterestInformalRatio);
        put(LeisureInteractPaths.NP_TIME_FORMAL_RATIO_FILE_NAME, npTimeFormalRatio);
        put(LeisureInteractPaths.NP_TIME_INFORMAL_RATIO_FILE_NAME, npTimeInformalRatio);
    }};


    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(areaSatisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(askingRatio, key)
                        * MathUtils.percentageSafetyDouble(discussionRatio, key)
                        * MathUtils.percentageSafetyDouble(frequencyContactFamRatio, key)
                        * MathUtils.percentageSafetyDouble(frequencyContactFrdRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherFamRatio, key)
                        * MathUtils.percentageSafetyDouble(gettingTogetherFrdRatio, key)
                        * MathUtils.percentageSafetyDouble(formalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(informalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(relSatisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(socialActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(timeSatisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(socialActivitiesNpRatio, key, true)
                        * MathUtils.percentageSafetyDouble(voluntaryActivitiesNpRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
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

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
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
