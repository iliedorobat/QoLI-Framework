package app.java.commons.dimensions.leisure;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
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

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("formalVoluntaryRatio", formalVoluntaryRatio);
        put("informalVoluntaryRatio", informalVoluntaryRatio);

        put("satisfactionRatio", satisfactionRatio);
        put("socialActivitiesRatio", socialActivitiesRatio);
        put("nonParticipationRatio", nonParticipationRatio);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Formal Voluntary Ratio", Preparation.filterMap(initFormalVoluntaryRatio));
            put("Informal Voluntary Ratio", Preparation.filterMap(initInformalVoluntaryRatio));
            put("Non Participation Fin Cinema Ratio", Preparation.filterMap(initNpFinCinRatio));
            put("Non Participation Fin Culture Ratio", Preparation.filterMap(initNpFinCultRatio));
            put("Non Participation Fin Live Ratio", Preparation.filterMap(initNpFinLiveRatio));
            put("Non Participation Fin Sport Ratio", Preparation.filterMap(initNpFinSportRatio));
            put("Non Participation Nnb Cinema Ratio", Preparation.filterMap(initNpNnbCinRatio));
            put("Non Participation Nnb Culture Ratio", Preparation.filterMap(initNpNnbCultRatio));
            put("Non Participation Nnb Live Ratio", Preparation.filterMap(initNpNnbLiveRatio));
            put("Non Participation Nnb Sport Ratio", Preparation.filterMap(initNpNnbSportRatio));
            put("Satisfaction Ratio", Preparation.filterMap(initSatisfactionRatio));
            put("Social Activities Ratio", Preparation.filterMap(initSocialActivitiesRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + DimensionNames.LEISURE)) {
            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_CIN_RATIO))
                Print.printChartData(npFinCinRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_CIN_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_CULT_RATIO))
                Print.printChartData(npFinCultRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_CULT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_LIVE_RATIO))
                Print.printChartData(npFinLiveRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_LIVE_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_SPORT_RATIO))
                Print.printChartData(npFinSportRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_SPORT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_CIN_RATIO))
                Print.printChartData(npNnbCinRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_CIN_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_CULT_RATIO))
                Print.printChartData(npNnbCultRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_CULT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_LIVE_RATIO))
                Print.printChartData(npNnbLiveRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_LIVE_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_SPORT_RATIO))
                Print.printChartData(npNnbSportRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_SPORT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.FORMAL_VOLUNTARY_RATIO))
                Print.printChartData(formalVoluntaryRatio, EU28_MEMBERS, seriesType, IndicatorNames.FORMAL_VOLUNTARY_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.INFORMAL_VOLUNTARY_RATIO))
                Print.printChartData(informalVoluntaryRatio, EU28_MEMBERS, seriesType, IndicatorNames.INFORMAL_VOLUNTARY_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.SATISFACTION_RATIO))
                Print.printChartData(satisfactionRatio, EU28_MEMBERS, seriesType, IndicatorNames.SATISFACTION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.SOCIAL_ACTIVITIES_RATIO))
                Print.printChartData(socialActivitiesRatio, EU28_MEMBERS, seriesType, IndicatorNames.SOCIAL_ACTIVITIES_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NON_PARTICIPATION_RATIO))
                Print.printChartData(nonParticipationRatio, EU28_MEMBERS, seriesType, IndicatorNames.NON_PARTICIPATION_RATIO, direction);
        }
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
