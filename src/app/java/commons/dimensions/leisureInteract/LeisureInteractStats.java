package app.java.commons.dimensions.leisureInteract;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.leisureInteract.LeisureInteractParams.*;
import static app.java.commons.dimensions.leisureInteract.LeisureInteractPaths.*;

public class LeisureInteractStats {
    private static final Map<String, Number>
            initAreaSatisfactionRatio = Initializer.initConsolidatedMap(AREA_SATISFACTION_RATIO_PARAMS, AREA_SATISFACTION_RATIO_PATH),
            initAskingRatio = Initializer.initConsolidatedMap(ASKING_RATIO_PARAMS, ASKING_RATIO_PATH),
            initDiscussionRatio = Initializer.initConsolidatedMap(DISCUSSION_PARAMS_RATIO, DISCUSSION_RATIO_PATH),
            initFormalVoluntaryRatio = Initializer.initConsolidatedMap(FORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH),
            initFrequencyContactFamRatio = Initializer.initConsolidatedMap(INTERACTIONS_FAM_PARAMS, FREQUENCY_CONTACT_RATIO_PATH),
            initFrequencyContactFrdRatio = Initializer.initConsolidatedMap(INTERACTIONS_FRD_PARAMS, FREQUENCY_CONTACT_RATIO_PATH),
            initGettingTogetherFamRatio = Initializer.initConsolidatedMap(INTERACTIONS_FAM_PARAMS, GETTING_TOGETHER_RATIO_PATH),
            initGettingTogetherFrdRatio = Initializer.initConsolidatedMap(INTERACTIONS_FRD_PARAMS, GETTING_TOGETHER_RATIO_PATH),
            initInformalVoluntaryRatio = Initializer.initConsolidatedMap(INFORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH),
            initRelSatisfactionRatio = Initializer.initConsolidatedMap(REL_SATISFACTION_RATIO_PARAMS, REL_SATISFACTION_RATIO_PATH),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(SOCIAL_ACTIVITIES_RATIO_PARAMS, SOCIAL_ACTIVITIES_RATIO_PATH),
            initTimeSatisfactionRatio = Initializer.initConsolidatedMap(TIME_SATISFACTION_RATIO_PARAMS, TIME_SATISFACTION_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedMap(NP_FIN_CIN_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinCultRatio = Initializer.initConsolidatedMap(NP_FIN_CULT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinLiveRatio = Initializer.initConsolidatedMap(NP_FIN_LIVE_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpFinSportRatio = Initializer.initConsolidatedMap(NP_FIN_SPORT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedMap(NP_NNB_CIN_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbCultRatio = Initializer.initConsolidatedMap(NP_NNB_CULT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbLiveRatio = Initializer.initConsolidatedMap(NP_NNB_LIVE_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),
            initNpNnbSportRatio = Initializer.initConsolidatedMap(NP_NNB_SPORT_RATIO_PARAMS, SOCIAL_ACTIVITIES_NP_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpNoInterestFormalRatio = Initializer.initConsolidatedMap(NP_NO_INTEREST_FORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpNoInterestInformalRatio = Initializer.initConsolidatedMap(NP_NO_INTEREST_INFORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpTimeFormalRatio = Initializer.initConsolidatedMap(NP_TIME_FORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH),
            initNpTimeInformalRatio = Initializer.initConsolidatedMap(NP_TIME_INFORMAL_RATIO_PARAMS, VOLUNTARY_ACTIVITIES_NP_RATIO_PATH);

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
        put(AREA_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initAreaSatisfactionRatio));
        put(ASKING_RATIO_FILE_NAME, Preparation.filterMap(initAskingRatio));
        put(DISCUSSION_RATIO_FILE_NAME, Preparation.filterMap(initDiscussionRatio));
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initFormalVoluntaryRatio));
        put(FREQUENCY_CONTACT_FAM_RATIO_FILE_NAME, Preparation.filterMap(initFrequencyContactFamRatio));
        put(FREQUENCY_CONTACT_FRD_RATIO_FILE_NAME, Preparation.filterMap(initFrequencyContactFrdRatio));
        put(GETTING_TOGETHER_FAM_RATIO_FILE_NAME, Preparation.filterMap(initGettingTogetherFamRatio));
        put(GETTING_TOGETHER_FRD_RATIO_FILE_NAME, Preparation.filterMap(initGettingTogetherFrdRatio));
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initInformalVoluntaryRatio));
        put(RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME, Preparation.filterMap(initRelSatisfactionRatio));
        put(SOCIAL_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initSocialActivitiesRatio));
        put(TIME_SPENT_SATISFACTION_FILE_NAME, Preparation.filterMap(initTimeSatisfactionRatio));

        put(NP_FIN_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCinRatio));
        put(NP_FIN_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCultRatio));
        put(NP_FIN_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinLiveRatio));
        put(NP_FIN_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpFinSportRatio));

        put(NP_NNB_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCinRatio));
        put(NP_NNB_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCultRatio));
        put(NP_NNB_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbLiveRatio));
        put(NP_NNB_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbSportRatio));

        put(NP_NO_INTEREST_FORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpNoInterestFormalRatio));
        put(NP_NO_INTEREST_INFORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpNoInterestInformalRatio));
        put(NP_TIME_FORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpTimeFormalRatio));
        put(NP_TIME_INFORMAL_RATIO_FILE_NAME, Preparation.filterMap(initNpTimeInformalRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(AREA_SATISFACTION_RATIO_FILE_NAME, areaSatisfactionRatio);
        put(ASKING_RATIO_FILE_NAME, askingRatio);
        put(DISCUSSION_RATIO_FILE_NAME, discussionRatio);
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, formalVoluntaryRatio);
        put(FREQUENCY_CONTACT_FAM_RATIO_FILE_NAME, frequencyContactFamRatio);
        put(FREQUENCY_CONTACT_FRD_RATIO_FILE_NAME, frequencyContactFrdRatio);
        put(GETTING_TOGETHER_FAM_RATIO_FILE_NAME, gettingTogetherFamRatio);
        put(GETTING_TOGETHER_FRD_RATIO_FILE_NAME, gettingTogetherFrdRatio);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, informalVoluntaryRatio);
        put(RELATIONSHIPS_SATISFACTION_RATIO_FILE_NAME, relSatisfactionRatio);
        put(SOCIAL_ACTIVITIES_RATIO_FILE_NAME, socialActivitiesRatio);
        put(TIME_SPENT_SATISFACTION_FILE_NAME, timeSatisfactionRatio);

        put(SOCIAL_ACTIVITIES_NP_RATIO_FILE_NAME, socialActivitiesNpRatio);
        put(VOLUNTARY_ACTIVITIES_NP_RATIO_FILE_NAME, voluntaryActivitiesNpRatio);

        put(NP_FIN_CINEMA_RATIO_FILE_NAME, npFinCinRatio);
        put(NP_FIN_CULTURE_RATIO_FILE_NAME, npFinCultRatio);
        put(NP_FIN_LIVE_RATIO_FILE_NAME, npFinLiveRatio);
        put(NP_FIN_SPORT_RATIO_FILE_NAME, npFinSportRatio);

        put(NP_NNB_CINEMA_RATIO_FILE_NAME, npNnbCinRatio);
        put(NP_NNB_CULTURE_RATIO_FILE_NAME, npNnbCultRatio);
        put(NP_NNB_LIVE_RATIO_FILE_NAME, npNnbLiveRatio);
        put(NP_NNB_SPORT_RATIO_FILE_NAME, npNnbSportRatio);

        put(NP_NO_INTEREST_FORMAL_RATIO_FILE_NAME, npNoInterestFormalRatio);
        put(NP_NO_INTEREST_INFORMAL_RATIO_FILE_NAME, npNoInterestInformalRatio);
        put(NP_TIME_FORMAL_RATIO_FILE_NAME, npTimeFormalRatio);
        put(NP_TIME_INFORMAL_RATIO_FILE_NAME, npTimeInformalRatio);
    }};


    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
        Print.printChartData(args, preparedIndicators, LEISURE_INTERACT_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateSocialActivitiesNpRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
            for (String code : EU28_MEMBERS) {
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
