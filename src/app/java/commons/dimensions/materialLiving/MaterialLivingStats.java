package app.java.commons.dimensions.materialLiving;

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
import static app.java.commons.dimensions.materialLiving.MaterialLivingParams.*;
import static app.java.commons.dimensions.materialLiving.MaterialLivingPaths.*;

public class MaterialLivingStats {
    private static final Map<String, Number>
            initDwellingIssuesRatio = Initializer.initConsolidatedMap(DWELLING_ISSUES_RATIO_PARAMS, DWELLING_ISSUES_RATIO_PATH),
            initEndMeetInabilityDRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_D_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH),
            initEndMeetInabilityGdRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_GD_RATIO_PARAMS, END_MEET_INABILITY_RATIO_PATH),
            initFinancialSatisfactionRatio = Initializer.initConsolidatedMap(FINANCIAL_SATISFACTION_PARAMS, FINANCIAL_SATISFACTION_PATH),
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
            highIncomeRatio = Preparation.prepareData(initHighIncomeRatio),
            incomeQuintileRatio = Preparation.prepareData(initIncomeQuintileRatio),
            lackOfBathsRatio = Preparation.prepareData(initLackOfBathsRatio),
            lowWorkIntensityRatio = Preparation.prepareData(initLowWorkIntensityRatio),
            materialDeprivationRatio = Preparation.prepareData(initMaterialDeprivationRatio),
            medianIncomePpsRatio = Preparation.preparePpsRatio(medianIncomePps),
            overOccupiedRatio = Preparation.prepareData(initOverOccupiedRatio),
            povertyRiskRatio = Preparation.prepareData(initPovertyRiskRatio),
            underOccupiedRatio = Preparation.prepareData(initUnderOccupiedRatio);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("dwellingIssuesRatio", dwellingIssuesRatio);
        put("endMeetInabilityRatio", endMeetInabilityRatio);
        put("financialSatisfactionRatio", financialSatisfactionRatio);
        put("highIncomeRatio", highIncomeRatio);
        put("incomeQuintileRatio", incomeQuintileRatio);
        put("lackOfBathsRatio", lackOfBathsRatio);
        put("lowWorkIntensityRatio", lowWorkIntensityRatio);
        put("materialDeprivationRatio", materialDeprivationRatio);
        put("medianIncomePpsRatio", medianIncomePpsRatio);
        put("overOccupiedRatio", overOccupiedRatio);
        put("povertyRiskRatio", povertyRiskRatio);
        put("underOccupiedRatio", underOccupiedRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(financialSatisfactionRatio, key)
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Dwelling Issues Ratio", Preparation.filterMap(initDwellingIssuesRatio));
            put("End Meet Inability Difficulty Ratio", Preparation.filterMap(initEndMeetInabilityDRatio));
            put("End Meet Inability Great Difficulty Ratio", Preparation.filterMap(initEndMeetInabilityGdRatio));
            put("Financial Satisfaction Ratio", Preparation.filterMap(initFinancialSatisfactionRatio));
            put("High Income Ratio", Preparation.filterMap(initHighIncomeRatio));
            put("Income Quintile Ratio", Preparation.filterMap(initIncomeQuintileRatio));
            put("Lack Of Baths Ratio", Preparation.filterMap(initLackOfBathsRatio));
            put("Low Work Intensity Ratio", Preparation.filterMap(initLowWorkIntensityRatio));
            put("Material Deprivation Ratio", Preparation.filterMap(initMaterialDeprivationRatio));
            put("Median Income PPS", Preparation.filterMap(initMedianIncomePps));
            put("Over Occupied Ratio", Preparation.filterMap(initOverOccupiedRatio));
            put("Poverty Risk Ratio", Preparation.filterMap(initPovertyRiskRatio));
            put("Under Occupied Ratio", Preparation.filterMap(initUnderOccupiedRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.DWELLING_ISSUES_RATIO, dwellingIssuesRatio);
            put(IndicatorNames.END_MEET_INABILITY_RATIO, endMeetInabilityRatio);
            put(IndicatorNames.END_MEET_INABILITY_D_RATIO, endMeetInabilityDRatio);
            put(IndicatorNames.END_MEET_INABILITY_GD_RATIO, endMeetInabilityGdRatio);
            put(IndicatorNames.FINANCIAL_SATISFACTION_RATIO, financialSatisfactionRatio);
            put(IndicatorNames.HIGH_INCOME_RATIO, highIncomeRatio);
            put(IndicatorNames.INCOME_QUINTILE_RATIO, incomeQuintileRatio);
            put(IndicatorNames.LACK_OF_BATHS_RATIO, lackOfBathsRatio);
            put(IndicatorNames.LOW_WORK_INTENSITY_RATIO, lowWorkIntensityRatio);
            put(IndicatorNames.MATERIAL_DEPRIVATION_RATIO, materialDeprivationRatio);
            put(IndicatorNames.MEDIAN_INCOME_PPS, medianIncomePps);
            put(IndicatorNames.OVER_OCCUPIED_RATIO, overOccupiedRatio);
            put(IndicatorNames.POVERTY_RISK_RATIO, povertyRiskRatio);
            put(IndicatorNames.UNDER_OCCUPIED_RATIO, underOccupiedRatio);
        }};

        Print.printChartData(args, indicators, DimensionNames.MATERIAL_LIVING_CONDITIONS, EU28_MEMBERS, seriesType, direction);
    }

    // proportion of population who can bear the expenses of basic needs with difficulty or with great difficulty
    private static Map<String, Number> prepareEndMeedInabilityRatio() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double value = 0
                        + endMeetInabilityDRatio.get(key).doubleValue()
                        + endMeetInabilityGdRatio.get(key).doubleValue();

                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
