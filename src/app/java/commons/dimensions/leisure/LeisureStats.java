package app.java.commons.dimensions.leisure;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.interactions.InteractionsParams;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class LeisureStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            FORMAL_VOLUNTARY_RATIO = LeisureParams.getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("formal")),
            INFORMAL_VOLUNTARY_RATIO = LeisureParams.getVoluntaryActivitiesParams(ParamsValues.ACL00_LEISURE.get("informal")),
            SATISFACTION_RATIO = LeisureParams.getTimeSpentSatisfactionParams(),
            SOCIAL_ACTIVITIES_RATIO = LeisureParams.getSocialActivitiesParams(),

            NP_FIN_CIN_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("financial")),
            NP_FIN_CULT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("financial")),
            NP_FIN_LIVE_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("financial")),
            NP_FIN_SPORT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("financial")),

            NP_NNB_CIN_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("cinema"), ParamsValues.REASON.get("away")),
            NP_NNB_CULT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("culture"), ParamsValues.REASON.get("away")),
            NP_NNB_LIVE_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("live"), ParamsValues.REASON.get("away")),
            NP_NNB_SPORT_RATIO = InteractionsParams.getNonParticipationParams(ParamsValues.ACL00_INTERACTIONS.get("sports"), ParamsValues.REASON.get("away"));

    private static final String
            nonParticipationRatioPath = FilePathConst.INTERACTIONS_PATH + FileNameConst.NON_PARTICIPATION_RATIO + JSON_EXTENSION,
            satisfactionRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.TIME_SPENT_SATISFACTION + JSON_EXTENSION,
            socialActivitiesRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.SOCIAL_ACTIVITIES_RATIO + JSON_EXTENSION,
            voluntaryRatioPath = FilePathConst.LEISURE_PATH + FileNameConst.VOLUNTARY_ACTIVITIES_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initSatisfactionRatio = Initializer.initConsolidatedMap(SATISFACTION_RATIO, satisfactionRatioPath),
            initSocialActivitiesRatio = Initializer.initConsolidatedMap(SOCIAL_ACTIVITIES_RATIO, socialActivitiesRatioPath),
            initFormalVoluntaryRatio = Initializer.initConsolidatedMap(FORMAL_VOLUNTARY_RATIO, voluntaryRatioPath),
            initInformalVoluntaryRatio = Initializer.initConsolidatedMap(INFORMAL_VOLUNTARY_RATIO, voluntaryRatioPath),

            // Intermediate data to be consolidated into a single indicator
            initNpFinCinRatio = Initializer.initConsolidatedMap(NP_FIN_CIN_RATIO, nonParticipationRatioPath),
            initNpFinCultRatio = Initializer.initConsolidatedMap(NP_FIN_CULT_RATIO, nonParticipationRatioPath),
            initNpFinLiveRatio = Initializer.initConsolidatedMap(NP_FIN_LIVE_RATIO, nonParticipationRatioPath),
            initNpFinSportRatio = Initializer.initConsolidatedMap(NP_FIN_SPORT_RATIO, nonParticipationRatioPath),

            // Intermediate data to be consolidated into a single indicator
            initNpNnbCinRatio = Initializer.initConsolidatedMap(NP_NNB_CIN_RATIO, nonParticipationRatioPath),
            initNpNnbCultRatio = Initializer.initConsolidatedMap(NP_NNB_CULT_RATIO, nonParticipationRatioPath),
            initNpNnbLiveRatio = Initializer.initConsolidatedMap(NP_NNB_LIVE_RATIO, nonParticipationRatioPath),
            initNpNnbSportRatio = Initializer.initConsolidatedMap(NP_NNB_SPORT_RATIO, nonParticipationRatioPath);

    public static final Map<String, Number>
            npFinCinRatio = Preparation.prepareData(initNpFinCinRatio),
            npFinCultRatio = Preparation.prepareData(initNpFinCultRatio),
            npFinLiveRatio = Preparation.prepareData(initNpFinLiveRatio),
            npFinSportRatio = Preparation.prepareData(initNpFinSportRatio),

            npNnbCinRatio = Preparation.prepareData(initNpNnbCinRatio),
            npNnbCultRatio = Preparation.prepareData(initNpNnbCultRatio),
            npNnbLiveRatio = Preparation.prepareData(initNpNnbLiveRatio),
            npNnbSportRatio = Preparation.prepareData(initNpNnbSportRatio),

            formalVoluntaryRatio = Preparation.prepareData(initFormalVoluntaryRatio),
            informalVoluntaryRatio = Preparation.prepareData(initInformalVoluntaryRatio),
            compactVoluntaryRatio = consolidateVoluntaryRatio(),

            satisfactionRatio = Preparation.prepareData(initSatisfactionRatio),
            socialActivitiesRatio = Preparation.prepareData(initSocialActivitiesRatio),
            nonParticipationRatio = consolidateNonParticipationRatio();


    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double reversedNonParticipationRatio = MathUtils.percentageReverseRatio(nonParticipationRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(satisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(socialActivitiesRatio, key)
                        * MathUtils.percentageSafetyDouble(compactVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedNonParticipationRatio);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(askingRatio, true));
//        Print.print(askingRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initFormalVoluntaryRatio));
            add(Preparation.filterMap(initInformalVoluntaryRatio));
            add(Preparation.filterMap(initNpFinCinRatio));
            add(Preparation.filterMap(initNpFinCultRatio));
            add(Preparation.filterMap(initNpFinLiveRatio));
            add(Preparation.filterMap(initNpFinSportRatio));
            add(Preparation.filterMap(initNpNnbCinRatio));
            add(Preparation.filterMap(initNpNnbCultRatio));
            add(Preparation.filterMap(initNpNnbLiveRatio));
            add(Preparation.filterMap(initNpNnbSportRatio));
            add(Preparation.filterMap(initSatisfactionRatio));
            add(Preparation.filterMap(initSocialActivitiesRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType) {
        if (args.contains("--dimension=" + DimensionNames.LEISURE)) {
            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_CIN_RATIO))
                Print.printChartData(npFinCinRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_CIN_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_CULT_RATIO))
                Print.printChartData(npFinCultRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_CULT_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_LIVE_RATIO))
                Print.printChartData(npFinLiveRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_LIVE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_FIN_SPORT_RATIO))
                Print.printChartData(npFinSportRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_FIN_SPORT_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_CIN_RATIO))
                Print.printChartData(npNnbCinRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_CIN_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_CULT_RATIO))
                Print.printChartData(npNnbCultRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_CULT_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_LIVE_RATIO))
                Print.printChartData(npNnbLiveRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_LIVE_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NP_NNB_SPORT_RATIO))
                Print.printChartData(npNnbSportRatio, EU28_MEMBERS, seriesType, IndicatorNames.NP_NNB_SPORT_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.FORMAL_VOLUNTARY_RATIO))
                Print.printChartData(formalVoluntaryRatio, EU28_MEMBERS, seriesType, IndicatorNames.FORMAL_VOLUNTARY_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.INFORMAL_VOLUNTARY_RATIO))
                Print.printChartData(informalVoluntaryRatio, EU28_MEMBERS, seriesType, IndicatorNames.INFORMAL_VOLUNTARY_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.SATISFACTION_RATIO))
                Print.printChartData(satisfactionRatio, EU28_MEMBERS, seriesType, IndicatorNames.SATISFACTION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.SOCIAL_ACTIVITIES_RATIO))
                Print.printChartData(socialActivitiesRatio, EU28_MEMBERS, seriesType, IndicatorNames.SOCIAL_ACTIVITIES_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.NON_PARTICIPATION_RATIO))
                Print.printChartData(nonParticipationRatio, EU28_MEMBERS, seriesType, IndicatorNames.NON_PARTICIPATION_RATIO);
            if (args.contains("--indicator=" + IndicatorNames.COMPACT_VOLUNTARY_RATIO))
                Print.printChartData(compactVoluntaryRatio, EU28_MEMBERS, seriesType, IndicatorNames.COMPACT_VOLUNTARY_RATIO);
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

    /**
     * Aggregate the "Formal Voluntary Ratio" and "Informal Voluntary Ratio" into a single ratio
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> consolidateVoluntaryRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(formalVoluntaryRatio, key)
                        * MathUtils.percentageSafetyDouble(informalVoluntaryRatio, key);

                Number value = MathUtils.getSquareValue(product, 2);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
