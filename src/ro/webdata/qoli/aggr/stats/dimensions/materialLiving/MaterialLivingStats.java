package ro.webdata.qoli.aggr.stats.dimensions.materialLiving;

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

import static ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingAggrParams.*;

public class MaterialLivingStats {
    private static final Map<String, Number>
            initDwellingIssuesRatio = Initializer.initConsolidatedMap(MaterialLivingParams.DWELLING_ISSUES_RATIO_PARAMS, MaterialLivingPaths.DWELLING_ISSUES_RATIO_PATH),
            initEndMeetInabilityDRatio = Initializer.initConsolidatedMap(MaterialLivingParams.END_MEET_INABILITY_D_RATIO_PARAMS, MaterialLivingPaths.END_MEET_INABILITY_RATIO_PATH),
            initEndMeetInabilityGdRatio = Initializer.initConsolidatedMap(MaterialLivingParams.END_MEET_INABILITY_GD_RATIO_PARAMS, MaterialLivingPaths.END_MEET_INABILITY_RATIO_PATH),
            initFinancialSatisfactionRatio = Initializer.initConsolidatedMap(MaterialLivingParams.FINANCIAL_SATISFACTION_PARAMS, MaterialLivingPaths.FINANCIAL_SATISFACTION_PATH),
            initGdpPerCapitaPpsRatio = Initializer.initConsolidatedMap(MaterialLivingParams.GDP_PER_CAPITA_PPS_RATIO_PARAMS, MaterialLivingPaths.GDP_PER_CAPITA_PPS_RATIO_PATH),
            initHighIncomeRatio = Initializer.initConsolidatedMap(MaterialLivingParams.HIGH_INCOME_RATIO_PARAMS, MaterialLivingPaths.HIGH_INCOME_RATIO_PATH),
            initIncomeQuintileRatio = Initializer.initConsolidatedMap(MaterialLivingParams.INCOME_QUINTILE_RATIO_PARAMS, MaterialLivingPaths.INCOME_QUINTILE_RATIO_PATH),
            initLackOfBathsRatio = Initializer.initConsolidatedMap(MaterialLivingParams.LACK_OF_BATHS_RATIO_PARAMS, MaterialLivingPaths.LACK_OF_BATHS_RATIO_PATH),
            initLowWorkIntensityRatio = Initializer.initConsolidatedMap(MaterialLivingParams.LOW_WORK_INTENSITY_RATIO_PARAMS, MaterialLivingPaths.LOW_WORK_INTENSITY_RATIO_PATH),
            initMaterialDeprivationRatio = Initializer.initConsolidatedMap(MaterialLivingParams.MATERIAL_DEPRIVATION_RATIO_PARAMS, MaterialLivingPaths.MATERIAL_DEPRIVATION_RATIO_PATH),
            initMedianIncomePps = Initializer.initConsolidatedMap(MaterialLivingParams.MEDIAN_INCOME_PPS_PARAMS, MaterialLivingPaths.MEDIAN_INCOME_PPS_PATH),
            initOverOccupiedRatio = Initializer.initConsolidatedMap(MaterialLivingParams.OVER_OCCUPIED_RATIO_PARAMS, MaterialLivingPaths.OVER_OCCUPIED_RATIO_PATH),
            initPovertyRiskRatio = Initializer.initConsolidatedMap(MaterialLivingParams.POVERTY_RISK_RATIO_PARAMS, MaterialLivingPaths.POVERTY_RISK_RATIO_PATH),
            initUnderOccupiedRatio = Initializer.initConsolidatedMap(MaterialLivingParams.UNDER_OCCUPIED_RATIO_PARAMS, MaterialLivingPaths.UNDER_OCCUPIED_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate medianIncomePpsRatio
            medianIncomePps = Preparation.prepareData(initMedianIncomePps),

            // Intermediate data used to calculate endMeetInabilityRatio
            endMeetInabilityDRatio = Preparation.prepareData(initEndMeetInabilityDRatio),
            endMeetInabilityGdRatio = Preparation.prepareData(initEndMeetInabilityGdRatio),

            dwellingIssuesRatio = Preparation.prepareData(initDwellingIssuesRatio),
            endMeetInabilityRatio = prepareEndMeedInabilityRatio(),
            financialSatisfactionRatio = Preparation.prepareData(initFinancialSatisfactionRatio),
            gdpPerCapitaPpsRatio = Preparation.prepareData(initGdpPerCapitaPpsRatio),
            highIncomeRatio = Preparation.prepareData(initHighIncomeRatio),
            incomeQuintileRatio = Preparation.prepareData(initIncomeQuintileRatio),
            lackOfBathsRatio = Preparation.prepareData(initLackOfBathsRatio),
            lowWorkIntensityRatio = Preparation.prepareData(initLowWorkIntensityRatio),
            materialDeprivationRatio = Preparation.prepareData(initMaterialDeprivationRatio),
            medianIncomePpsRatio = Preparation.preparePpsRatio(medianIncomePps),
            overOccupiedRatio = Preparation.prepareData(initOverOccupiedRatio),
            povertyRiskRatio = Preparation.prepareData(initPovertyRiskRatio),
            underOccupiedRatio = Preparation.prepareData(initUnderOccupiedRatio);

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(DWELLING_ISSUES_RATIO, Preparation.filterMap(initDwellingIssuesRatio));
        put(END_MEET_INABILITY_D_RATIO, Preparation.filterMap(initEndMeetInabilityDRatio));
        put(END_MEET_INABILITY_GD_RATIO, Preparation.filterMap(initEndMeetInabilityGdRatio));
        put(FINANCIAL_SATISFACTION_RATIO, Preparation.filterMap(initFinancialSatisfactionRatio));
        put(GDP_PER_CAPITA_PPS_RATIO, Preparation.filterMap(initGdpPerCapitaPpsRatio));
        put(HIGH_INCOME_RATIO, Preparation.filterMap(initHighIncomeRatio));
        put(INCOME_QUINTILE_RATIO, Preparation.filterMap(initIncomeQuintileRatio));
        put(LACK_OF_BATHS_RATIO, Preparation.filterMap(initLackOfBathsRatio));
        put(LOW_WORK_INTENSITY_RATIO, Preparation.filterMap(initLowWorkIntensityRatio));
        put(MATERIAL_DEPRIVATION_RATIO, Preparation.filterMap(initMaterialDeprivationRatio));
        put(MEDIAN_INCOME_PPS_RATIO, Preparation.filterMap(initMedianIncomePps));
        put(OVER_OCCUPIED_RATIO, Preparation.filterMap(initOverOccupiedRatio));
        put(POVERTY_RISK_RATIO, Preparation.filterMap(initPovertyRiskRatio));
        put(UNDER_OCCUPIED_RATIO, Preparation.filterMap(initUnderOccupiedRatio));
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        put(DWELLING_ISSUES_RATIO, dwellingIssuesRatio);
        put(END_MEET_INABILITY_RATIO, endMeetInabilityRatio);
        put(FINANCIAL_SATISFACTION_RATIO, financialSatisfactionRatio);
        put(GDP_PER_CAPITA_PPS_RATIO, gdpPerCapitaPpsRatio);
        put(HIGH_INCOME_RATIO, highIncomeRatio);
        put(INCOME_QUINTILE_RATIO, incomeQuintileRatio);
        put(LACK_OF_BATHS_RATIO, lackOfBathsRatio);
        put(LOW_WORK_INTENSITY_RATIO, lowWorkIntensityRatio);
        put(MATERIAL_DEPRIVATION_RATIO, materialDeprivationRatio);
        put(MEDIAN_INCOME_PPS_RATIO, medianIncomePpsRatio);
        put(OVER_OCCUPIED_RATIO, overOccupiedRatio);
        put(POVERTY_RISK_RATIO, povertyRiskRatio);
        put(UNDER_OCCUPIED_RATIO, underOccupiedRatio);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        put(DWELLING_ISSUES_RATIO, dwellingIssuesRatio);
        put(END_MEET_INABILITY_D_RATIO, endMeetInabilityDRatio);
        put(END_MEET_INABILITY_GD_RATIO, endMeetInabilityGdRatio);
        put(FINANCIAL_SATISFACTION_RATIO, financialSatisfactionRatio);
        put(GDP_PER_CAPITA_PPS_RATIO, gdpPerCapitaPpsRatio);
        put(HIGH_INCOME_RATIO, highIncomeRatio);
        put(INCOME_QUINTILE_RATIO, incomeQuintileRatio);
        put(LACK_OF_BATHS_RATIO, lackOfBathsRatio);
        put(LOW_WORK_INTENSITY_RATIO, lowWorkIntensityRatio);
        put(MATERIAL_DEPRIVATION_RATIO, materialDeprivationRatio);
        put(MEDIAN_INCOME_PPS_RATIO, medianIncomePpsRatio);
        put(OVER_OCCUPIED_RATIO, overOccupiedRatio);
        put(POVERTY_RISK_RATIO, povertyRiskRatio);
        put(UNDER_OCCUPIED_RATIO, underOccupiedRatio);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, LIVING_CONDITIONS, AGGR_PARAMS, AGGR_REVERSED_STATE, aggrIndicators);
    }

    public static Map<String, Number> generateBaseStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, LIVING_CONDITIONS, IND_PARAMS, IND_REVERSED_STATE, baseIndicators);
    }

    public static void printAggrIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, aggrIndicators, LIVING_CONDITIONS, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, LIVING_CONDITIONS, targetYear, indStatus);
    }

    // Get the proportion of population who can bear the expenses of basic needs with difficulty or with great difficulty
    private static Map<String, Number> prepareEndMeedInabilityRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueDifficulty = endMeetInabilityDRatio.get(key).doubleValue();
                double valueGreatDifficulty = endMeetInabilityGdRatio.get(key).doubleValue();

                double value = 0
                        + valueDifficulty
                        + valueGreatDifficulty;

                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
