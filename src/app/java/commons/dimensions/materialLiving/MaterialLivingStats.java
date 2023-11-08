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
            dwellingIssuesRatio = Preparation.prepareData(initDwellingIssuesRatio),

            endMeetInabilityDRatio = Preparation.prepareData(initEndMeetInabilityDRatio),
            endMeetInabilityGdRatio = Preparation.prepareData(initEndMeetInabilityGdRatio),
            endMeetInabilityRatio = prepareEndMeedInabilityRatio(),

            financialSatisfactionRatio = Preparation.prepareData(initFinancialSatisfactionRatio),
            highIncomeRatio = Preparation.prepareData(initHighIncomeRatio),
            incomeQuintileRatio = Preparation.prepareData(initIncomeQuintileRatio),
            medianIncomePps = Preparation.prepareData(initMedianIncomePps),

            lackOfBathsRatio = Preparation.prepareData(initLackOfBathsRatio),
            lowWorkIntensityRatio = Preparation.prepareData(initLowWorkIntensityRatio),
            materialDeprivationRatio = Preparation.prepareData(initMaterialDeprivationRatio),
            overOccupiedRatio = Preparation.prepareData(initOverOccupiedRatio),
            povertyRiskRatio = Preparation.prepareData(initPovertyRiskRatio),
            underOccupiedRatio = Preparation.prepareData(initUnderOccupiedRatio);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        medianIncomePpsRatio = Preparation.consolidatePpsRatio(medianIncomePps, code, year),
                        reverseDwellingIssuesRatio = MathUtils.percentageReverseRatio(dwellingIssuesRatio, key),
                        reverseEndMeetInabilityRatio = MathUtils.percentageReverseRatio(endMeetInabilityRatio, key),
                        reverseIncomeQuintileRatio = MathUtils.percentageReverseRatio(incomeQuintileRatio, key),
                        reverseLackOfBathsRatio = MathUtils.percentageReverseRatio(lackOfBathsRatio, key),
                        reverseLowWorkIntensityRatio = MathUtils.percentageReverseRatio(lowWorkIntensityRatio, key),
                        reverseMaterialDeprivationRatio = MathUtils.percentageReverseRatio(materialDeprivationRatio, key),
                        reverseOverOccupiedRatio = MathUtils.percentageReverseRatio(overOccupiedRatio, key),
                        reversePovertyRiskRatio = MathUtils.percentageReverseRatio(povertyRiskRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(financialSatisfactionRatio, key)
                        * MathUtils.percentageSafetyDouble(highIncomeRatio, key)
                        * MathUtils.percentageSafetyDouble(medianIncomePpsRatio)
                        * MathUtils.percentageSafetyDouble(underOccupiedRatio, key)
                        * MathUtils.percentageSafetyDouble(reverseDwellingIssuesRatio)
                        * MathUtils.percentageSafetyDouble(reverseEndMeetInabilityRatio)
                        * MathUtils.percentageSafetyDouble(reverseIncomeQuintileRatio)
                        * MathUtils.percentageSafetyDouble(reverseLackOfBathsRatio)
                        * MathUtils.percentageSafetyDouble(reverseLowWorkIntensityRatio)
                        * MathUtils.percentageSafetyDouble(reverseMaterialDeprivationRatio)
                        * MathUtils.percentageSafetyDouble(reverseOverOccupiedRatio)
                        * MathUtils.percentageSafetyDouble(reversePovertyRiskRatio);

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
        if (args.contains("--dimension=" + DimensionNames.MATERIAL_LIVING_CONDITIONS)) {
            if (args.contains("--indicator=" + IndicatorNames.DWELLING_ISSUES_RATIO))
                Print.printChartData(dwellingIssuesRatio, EU28_MEMBERS, seriesType, IndicatorNames.DWELLING_ISSUES_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.END_MEET_INABILITY_RATIO))
                Print.printChartData(endMeetInabilityRatio, EU28_MEMBERS, seriesType, IndicatorNames.END_MEET_INABILITY_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.END_MEET_INABILITY_D_RATIO))
                Print.printChartData(endMeetInabilityDRatio, EU28_MEMBERS, seriesType, IndicatorNames.END_MEET_INABILITY_D_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.END_MEET_INABILITY_GD_RATIO))
                Print.printChartData(endMeetInabilityGdRatio, EU28_MEMBERS, seriesType, IndicatorNames.END_MEET_INABILITY_GD_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.FINANCIAL_SATISFACTION_RATIO))
                Print.printChartData(financialSatisfactionRatio, EU28_MEMBERS, seriesType, IndicatorNames.FINANCIAL_SATISFACTION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.HIGH_INCOME_RATIO))
                Print.printChartData(highIncomeRatio, EU28_MEMBERS, seriesType, IndicatorNames.HIGH_INCOME_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.INCOME_QUINTILE_RATIO))
                Print.printChartData(incomeQuintileRatio, EU28_MEMBERS, seriesType, IndicatorNames.INCOME_QUINTILE_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.LACK_OF_BATHS_RATIO))
                Print.printChartData(lackOfBathsRatio, EU28_MEMBERS, seriesType, IndicatorNames.LACK_OF_BATHS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.LOW_WORK_INTENSITY_RATIO))
                Print.printChartData(lowWorkIntensityRatio, EU28_MEMBERS, seriesType, IndicatorNames.LOW_WORK_INTENSITY_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.MATERIAL_DEPRIVATION_RATIO))
                Print.printChartData(materialDeprivationRatio, EU28_MEMBERS, seriesType, IndicatorNames.MATERIAL_DEPRIVATION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.MEDIAN_INCOME_PPS))
                Print.printChartData(medianIncomePps, EU28_MEMBERS, seriesType, IndicatorNames.MEDIAN_INCOME_PPS, direction);

            if (args.contains("--indicator=" + IndicatorNames.OVER_OCCUPIED_RATIO))
                Print.printChartData(overOccupiedRatio, EU28_MEMBERS, seriesType, IndicatorNames.OVER_OCCUPIED_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.POVERTY_RISK_RATIO))
                Print.printChartData(povertyRiskRatio, EU28_MEMBERS, seriesType, IndicatorNames.POVERTY_RISK_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.UNDER_OCCUPIED_RATIO))
                Print.printChartData(underOccupiedRatio, EU28_MEMBERS, seriesType, IndicatorNames.UNDER_OCCUPIED_RATIO, direction);
        }
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
