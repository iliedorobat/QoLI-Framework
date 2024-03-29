package ro.webdata.qoli.aggr.commons.dimensions.materialLiving;

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

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(MaterialLivingPaths.DWELLING_ISSUES_RATIO_FILE_NAME, Preparation.filterMap(initDwellingIssuesRatio));
        put(MaterialLivingPaths.END_MEET_INABILITY_D_RATIO_FILE_NAME, Preparation.filterMap(initEndMeetInabilityDRatio));
        put(MaterialLivingPaths.END_MEET_INABILITY_GD_RATIO_FILE_NAME, Preparation.filterMap(initEndMeetInabilityGdRatio));
        put(MaterialLivingPaths.FINANCIAL_SATISFACTION_FILE_NAME, Preparation.filterMap(initFinancialSatisfactionRatio));
        put(MaterialLivingPaths.GDP_PER_CAPITA_PPS_RATIO_FILE_NAME, Preparation.filterMap(initGdpPerCapitaPpsRatio));
        put(MaterialLivingPaths.HIGH_INCOME_RATIO_FILE_NAME, Preparation.filterMap(initHighIncomeRatio));
        put(MaterialLivingPaths.INCOME_QUINTILE_RATIO_FILE_NAME, Preparation.filterMap(initIncomeQuintileRatio));
        put(MaterialLivingPaths.LACK_OF_BATHS_RATIO_FILE_NAME, Preparation.filterMap(initLackOfBathsRatio));
        put(MaterialLivingPaths.LOW_WORK_INTENSITY_RATIO_FILE_NAME, Preparation.filterMap(initLowWorkIntensityRatio));
        put(MaterialLivingPaths.MATERIAL_DEPRIVATION_RATIO_FILE_NAME, Preparation.filterMap(initMaterialDeprivationRatio));
        put(MaterialLivingPaths.MEDIAN_INCOME_PPS_FILE_NAME, Preparation.filterMap(initMedianIncomePps));
        put(MaterialLivingPaths.OVER_OCCUPIED_RATIO_FILE_NAME, Preparation.filterMap(initOverOccupiedRatio));
        put(MaterialLivingPaths.POVERTY_RISK_RATIO_FILE_NAME, Preparation.filterMap(initPovertyRiskRatio));
        put(MaterialLivingPaths.UNDER_OCCUPIED_RATIO_FILE_NAME, Preparation.filterMap(initUnderOccupiedRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(MaterialLivingPaths.DWELLING_ISSUES_RATIO_FILE_NAME, dwellingIssuesRatio);
        put(MaterialLivingPaths.END_MEET_INABILITY_RATIO_FILE_NAME, endMeetInabilityRatio);
        put(MaterialLivingPaths.END_MEET_INABILITY_D_RATIO_FILE_NAME, endMeetInabilityDRatio);
        put(MaterialLivingPaths.END_MEET_INABILITY_GD_RATIO_FILE_NAME, endMeetInabilityGdRatio);
        put(MaterialLivingPaths.FINANCIAL_SATISFACTION_FILE_NAME, financialSatisfactionRatio);
        put(MaterialLivingPaths.GDP_PER_CAPITA_PPS_RATIO_FILE_NAME, gdpPerCapitaPpsRatio);
        put(MaterialLivingPaths.HIGH_INCOME_RATIO_FILE_NAME, highIncomeRatio);
        put(MaterialLivingPaths.INCOME_QUINTILE_RATIO_FILE_NAME, incomeQuintileRatio);
        put(MaterialLivingPaths.LACK_OF_BATHS_RATIO_FILE_NAME, lackOfBathsRatio);
        put(MaterialLivingPaths.LOW_WORK_INTENSITY_RATIO_FILE_NAME, lowWorkIntensityRatio);
        put(MaterialLivingPaths.MATERIAL_DEPRIVATION_RATIO_FILE_NAME, materialDeprivationRatio);
        put(MaterialLivingPaths.MEDIAN_INCOME_PPS_FILE_NAME, medianIncomePps);
        put(MaterialLivingPaths.OVER_OCCUPIED_RATIO_FILE_NAME, overOccupiedRatio);
        put(MaterialLivingPaths.POVERTY_RISK_RATIO_FILE_NAME, povertyRiskRatio);
        put(MaterialLivingPaths.UNDER_OCCUPIED_RATIO_FILE_NAME, underOccupiedRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(financialSatisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(gdpPerCapitaPpsRatio, key)
                        * MathUtils.percentageSafetyDouble(highIncomeRatio, key)
                        * MathUtils.percentageSafetyDouble(medianIncomePpsRatio, key)
                        * MathUtils.percentageSafetyDouble(underOccupiedRatio, key)
                        * MathUtils.percentageSafetyDouble(dwellingIssuesRatio, key, true)
                        * MathUtils.percentageSafetyDouble(endMeetInabilityRatio, key, true)
                        * MathUtils.percentageSafetyDouble(incomeQuintileRatio, key, true)
                        * MathUtils.percentageSafetyDouble(lackOfBathsRatio, key, true)
                        * MathUtils.percentageSafetyDouble(lowWorkIntensityRatio, key, true)
                        * MathUtils.percentageSafetyDouble(materialDeprivationRatio, key, true)
                        * MathUtils.percentageSafetyDouble(overOccupiedRatio, key, true)
                        * MathUtils.percentageSafetyDouble(povertyRiskRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(lackOfBathsRatio, true));
//        Print.print(purchasingRatio, true);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Get the proportion of population who can bear the expenses of basic needs with difficulty or with great difficulty
     *
     * @return An ordered map with aggregated data
     */
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
