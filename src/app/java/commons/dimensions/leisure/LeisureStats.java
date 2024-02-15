package app.java.commons.dimensions.leisure;

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
import static app.java.commons.constants.Constants.PERCENTAGE_SAFETY_THRESHOLD;
import static app.java.commons.dimensions.leisure.LeisureParams.*;
import static app.java.commons.dimensions.leisure.LeisurePaths.*;

public class LeisureStats {
    private static final Map<String, Number>
            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO_PARAMS, SATISFACTION_RATIO_PATH),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(SOCIAL_ACTIVITIES_RATIO_PARAMS, SOCIAL_ACTIVITIES_RATIO_PATH),
            initFormalVoluntaryRatio = Initializer.initConsolidatedMap(FORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH),
            initInformalVoluntaryRatio = Initializer.initConsolidatedMap(INFORMAL_VOLUNTARY_RATIO_PARAMS, VOLUNTARY_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedMap(NP_FIN_CIN_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpFinCultRatio = Initializer.initConsolidatedMap(NP_FIN_CULT_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpFinLiveRatio = Initializer.initConsolidatedMap(NP_FIN_LIVE_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpFinSportRatio = Initializer.initConsolidatedMap(NP_FIN_SPORT_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),

            // Intermediate data to be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedMap(NP_NNB_CIN_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpNnbCultRatio = Initializer.initConsolidatedMap(NP_NNB_CULT_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpNnbLiveRatio = Initializer.initConsolidatedMap(NP_NNB_LIVE_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH),
            initNpNnbSportRatio = Initializer.initConsolidatedMap(NP_NNB_SPORT_RATIO_PARAMS, NON_PARTICIPATION_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate date used to calculate nonParticipationRatio
            npFinCinRatio = Preparation.prepareData(initNpFinCinRatio),
            npFinCultRatio = Preparation.prepareData(initNpFinCultRatio),
            npFinLiveRatio = Preparation.prepareData(initNpFinLiveRatio),
            npFinSportRatio = Preparation.prepareData(initNpFinSportRatio),

            // Intermediate date used to calculate nonParticipationRatio
            npNnbCinRatio = Preparation.prepareData(initNpNnbCinRatio),
            npNnbCultRatio = Preparation.prepareData(initNpNnbCultRatio),
            npNnbLiveRatio = Preparation.prepareData(initNpNnbLiveRatio),
            npNnbSportRatio = Preparation.prepareData(initNpNnbSportRatio),

            formalVoluntaryRatio = Preparation.prepareData(initFormalVoluntaryRatio),
            informalVoluntaryRatio = Preparation.prepareData(initInformalVoluntaryRatio),

            satisfactionRatio = Preparation.prepareData(initSatisfactionRatio),
            socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio),
            nonParticipationRatio = consolidateNonParticipationRatio();

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initFormalVoluntaryRatio));
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initInformalVoluntaryRatio));
        put(NON_PARTICIPATION_FIN_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCinRatio));
        put(NON_PARTICIPATION_FIN_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinCultRatio));
        put(NON_PARTICIPATION_FIN_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpFinLiveRatio));
        put(NON_PARTICIPATION_FIN_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpFinSportRatio));
        put(NON_PARTICIPATION_NNB_CINEMA_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCinRatio));
        put(NON_PARTICIPATION_NNB_CULTURE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbCultRatio));
        put(NON_PARTICIPATION_NNB_LIVE_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbLiveRatio));
        put(NON_PARTICIPATION_NNB_SPORT_RATIO_FILE_NAME, Preparation.filterMap(initNpNnbSportRatio));
        put(SOCIAL_ACTIVITIES_RATIO_FILE_NAME, Preparation.filterMap(initSocialActivitiesRatio));
        put(TIME_SPENT_SATISFACTION_FILE_NAME, Preparation.filterMap(initSatisfactionRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(FORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, formalVoluntaryRatio);
        put(INFORMAL_VOLUNTARY_ACTIVITIES_RATIO_FILE_NAME, informalVoluntaryRatio);
        put(NON_PARTICIPATION_FIN_CINEMA_RATIO_FILE_NAME, npFinCinRatio);
        put(NON_PARTICIPATION_FIN_CULTURE_RATIO_FILE_NAME, npFinCultRatio);
        put(NON_PARTICIPATION_FIN_LIVE_RATIO_FILE_NAME, npFinLiveRatio);
        put(NON_PARTICIPATION_FIN_SPORT_RATIO_FILE_NAME, npFinSportRatio);
        put(NON_PARTICIPATION_NNB_CINEMA_RATIO_FILE_NAME, npNnbCinRatio);
        put(NON_PARTICIPATION_NNB_CULTURE_RATIO_FILE_NAME, npNnbCultRatio);
        put(NON_PARTICIPATION_NNB_LIVE_RATIO_FILE_NAME, npNnbLiveRatio);
        put(NON_PARTICIPATION_NNB_SPORT_RATIO_FILE_NAME, npNnbSportRatio);
        put(SOCIAL_ACTIVITIES_RATIO_FILE_NAME, socialActivitiesRatio);
        put(TIME_SPENT_SATISFACTION_FILE_NAME, satisfactionRatio);
        put(NON_PARTICIPATION_RATIO_FILE_NAME, nonParticipationRatio);
    }};


    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(socialActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(formalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(informalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(nonParticipationRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, LEISURE_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    /**
     * Aggregate the "Non Participation Ratios" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateNonParticipationRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(npFinCinRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinCultRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinLiveRatio, key)
                        * MathUtils.percentageSafetyDouble(npFinSportRatio, key)

                        * MathUtils.percentageSafetyDouble(npNnbCinRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbCultRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbLiveRatio, key)
                        * MathUtils.percentageSafetyDouble(npNnbSportRatio, key);

                // Subtract 101 because of adding it before by using MathUtils.percentageSafetyDouble method
                Number value = MathUtils.getSquareValue(product, 8) - PERCENTAGE_SAFETY_THRESHOLD;
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
