package app.java.aggr.commons.dimensions.materialLiving;

import app.java.aggr.commons.MapOrder;
import app.java.aggr.commons.Print;
import app.java.aggr.commons.constants.EnvConst;
import app.java.aggr.commons.utils.MapUtils;
import app.java.aggr.commons.utils.MathUtils;
import app.java.aggr.data.stats.Initializer;
import app.java.aggr.data.stats.Preparation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.aggr.commons.constants.Constants.EU28_MEMBERS;
import static app.java.aggr.commons.dimensions.materialLiving.MaterialLivingParams.*;
import static app.java.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.*;

public class MaterialLivingStats {
    private static final Map<String, Number>
            initDwellingIssuesRatio = Initializer.initConsolidatedMap(DWELLING_ISSUES_RATIO_PARAMS, DWELLING_ISSUES_RATIO_PATH),
            initEndMeetInabilityDRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_D_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH),
            initEndMeetInabilityGdRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_GD_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH),
            initFinancialSatisfactionRatio = Initializer.initConsolidatedMap(FINANCIAL_SATISFACTION_PARAMS, FINANCIAL_SATISFACTION_PATH),
            initGdpPerCapitaPpsRatio = Initializer.initConsolidatedMap(GDP_PER_CAPITA_PPS_RATIO_PARAMS, GDP_PER_CAPITA_PPS_RATIO_PATH),
            initHighIncomeRatio = Initializer.initConsolidatedMap(HIGH_INCOME_RATIO_PARAMS, HIGH_INCOME_RATIO_PATH),
            initIncomeQuintileRatio = Initializer.initConsolidatedMap(INCOME_QUINTILE_RATIO_PARAMS, INCOME_QUINTILE_RATIO_PATH),
            initLackOfBathsRatio = Initializer.initConsolidatedMap(LACK_OF_BATHS_RATIO_PARAMS, LACK_OF_BATHS_RATIO_PATH),
            initLowWorkIntensityRatio = Initializer.initConsolidatedMap(LOW_WORK_INTENSITY_RATIO_PARAMS, LOW_WORK_INTENSITY_RATIO_PATH),
            initMaterialDeprivationRatio = Initializer.initConsolidatedMap(MATERIAL_DEPRIVATION_RATIO_PARAMS, MATERIAL_DEPRIVATION_RATIO_PATH),
            initMedianIncomePps = Initializer.initConsolidatedMap(MEDIAN_INCOME_PPS_PARAMS, MEDIAN_INCOME_PPS_PATH),
            initOverOccupiedRatio = Initializer.initConsolidatedMap(OVER_OCCUPIED_RATIO_PARAMS, OVER_OCCUPIED_RATIO_PATH),
            initPovertyRiskRatio = Initializer.initConsolidatedMap(POVERTY_RISK_RATIO_PARAMS, POVERTY_RISK_RATIO_PATH),
            initUnderOccupiedRatio = Initializer.initConsolidatedMap(UNDER_OCCUPIED_RATIO_PARAMS, UNDER_OCCUPIED_RATIO_PATH);

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
        put(DWELLING_ISSUES_RATIO_FILE_NAME, Preparation.filterMap(initDwellingIssuesRatio));
        put(END_MEET_INABILITY_D_RATIO_FILE_NAME, Preparation.filterMap(initEndMeetInabilityDRatio));
        put(END_MEET_INABILITY_GD_RATIO_FILE_NAME, Preparation.filterMap(initEndMeetInabilityGdRatio));
        put(FINANCIAL_SATISFACTION_FILE_NAME, Preparation.filterMap(initFinancialSatisfactionRatio));
        put(GDP_PER_CAPITA_PPS_RATIO_FILE_NAME, Preparation.filterMap(initGdpPerCapitaPpsRatio));
        put(HIGH_INCOME_RATIO_FILE_NAME, Preparation.filterMap(initHighIncomeRatio));
        put(INCOME_QUINTILE_RATIO_FILE_NAME, Preparation.filterMap(initIncomeQuintileRatio));
        put(LACK_OF_BATHS_RATIO_FILE_NAME, Preparation.filterMap(initLackOfBathsRatio));
        put(LOW_WORK_INTENSITY_RATIO_FILE_NAME, Preparation.filterMap(initLowWorkIntensityRatio));
        put(MATERIAL_DEPRIVATION_RATIO_FILE_NAME, Preparation.filterMap(initMaterialDeprivationRatio));
        put(MEDIAN_INCOME_PPS_FILE_NAME, Preparation.filterMap(initMedianIncomePps));
        put(OVER_OCCUPIED_RATIO_FILE_NAME, Preparation.filterMap(initOverOccupiedRatio));
        put(POVERTY_RISK_RATIO_FILE_NAME, Preparation.filterMap(initPovertyRiskRatio));
        put(UNDER_OCCUPIED_RATIO_FILE_NAME, Preparation.filterMap(initUnderOccupiedRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(DWELLING_ISSUES_RATIO_FILE_NAME, dwellingIssuesRatio);
        put(END_MEET_INABILITY_RATIO_FILE_NAME, endMeetInabilityRatio);
        put(END_MEET_INABILITY_D_RATIO_FILE_NAME, endMeetInabilityDRatio);
        put(END_MEET_INABILITY_GD_RATIO_FILE_NAME, endMeetInabilityGdRatio);
        put(FINANCIAL_SATISFACTION_FILE_NAME, financialSatisfactionRatio);
        put(GDP_PER_CAPITA_PPS_RATIO_FILE_NAME, gdpPerCapitaPpsRatio);
        put(HIGH_INCOME_RATIO_FILE_NAME, highIncomeRatio);
        put(INCOME_QUINTILE_RATIO_FILE_NAME, incomeQuintileRatio);
        put(LACK_OF_BATHS_RATIO_FILE_NAME, lackOfBathsRatio);
        put(LOW_WORK_INTENSITY_RATIO_FILE_NAME, lowWorkIntensityRatio);
        put(MATERIAL_DEPRIVATION_RATIO_FILE_NAME, materialDeprivationRatio);
        put(MEDIAN_INCOME_PPS_FILE_NAME, medianIncomePps);
        put(OVER_OCCUPIED_RATIO_FILE_NAME, overOccupiedRatio);
        put(POVERTY_RISK_RATIO_FILE_NAME, povertyRiskRatio);
        put(UNDER_OCCUPIED_RATIO_FILE_NAME, underOccupiedRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
        Print.printChartData(args, preparedIndicators, LIVING_CONDITIONS_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, LIVING_CONDITIONS_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Get the proportion of population who can bear the expenses of basic needs with difficulty or with great difficulty
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareEndMeedInabilityRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
