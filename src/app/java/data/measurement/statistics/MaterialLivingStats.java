package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.commons.utils.StatsUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class MaterialLivingStats {
    // Queried params values
    private static final String[]
            DWELLING_ISSUES_RATIO = {"TOTAL", "TOTAL", "PC", "T", "TOTAL"},
            END_MEET_INABILITY_RATIO = {"PC", "EM_D", "TOTAL", "TOTAL"}, // with difficulty
            END_MEET_INABILITY_GD_RATIO = {"PC", "EM_GD", "TOTAL", "TOTAL"}, // with great difficulty
            FINANCIAL_SATISFACTION = {"PC", "HIGH", "TOTAL", "FINSAT", "T", "Y_GE16"},
            HIGH_INCOME_RATIO = {"PC", "LI_GE130MD", "T", "TOTAL"},
            INCOME_QUINTILE_RATIO = {"RAT", "TOTAL", "T"},
            INCOME_QUINTILE_LESS_65_RATIO = {"RAT", "Y_LT65", "T"},
            INCOME_QUINTILE_OVER_65_RATIO = {"RAT", "Y_GE65", "T"},
            LACK_OF_BATHS_RATIO = {"PC", "TOTAL", "TOTAL", "T", "TOTAL"},
            LOW_WORK_INTENSITY_RATIO = {"PC_Y_LT60", "Y_LT60", "T"},
            MATERIAL_DEPRIVATION_RATIO = {"PC", "TOTAL", "T"},
            MEDIAN_INCOME = {"TOTAL", "T", "MED_E", "PPS"},
            OVER_OCCUPIED_RATIO = {"PC", "TOTAL", "TOTAL", "T"},
            POVERTY_RISK_RATIO = {"LI_R_MD60", "TOTAL"},
            PUBLIC_WATER_RATIO = {"POP_PWS", "PC"},
            PPS_RATIO = {"PC_EU28_HAB_MEUR_CP", "B1GQ"}, // TODO: check the data
            UNDER_OCCUPIED_RATIO = {"PC", "TOTAL", "T", "TOTAL"};

    private static final String
            dwellingIssuesRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.DWELLING_ISSUES_RATIO + JSON_EXTENSION,
            endMeetInabilityRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.END_MEET_INABILITY_RATIO + JSON_EXTENSION,
            financialSatisfactionPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.FINANCIAL_SATISFACTION + JSON_EXTENSION,
            highIncomeRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.HIGH_INCOME_RATIO + JSON_EXTENSION,
            incomeQuintileRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.INCOME_QUINTILE_RATIO + JSON_EXTENSION,
            lackOfBathsRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.LACK_OF_BATHS_RATIO + JSON_EXTENSION,
            materialDeprivationRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MATERIAL_DEPRIVATION_RATIO + JSON_EXTENSION,
            medianIncomePath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MEDIAN_INCOME + JSON_EXTENSION,
            overOccupiedRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.OVER_OCCUPIED_RATIO + JSON_EXTENSION,
            povertyRiskRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.POVERTY_RISK_RATIO + JSON_EXTENSION,
            publicWaterRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PUBLIC_WATER_RATIO + JSON_EXTENSION,
            ppsRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PPS_RATIO + JSON_EXTENSION,
            underOccupiedRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.UNDER_OCCUPIED_RATIO + JSON_EXTENSION,
            workIntensityRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.WORK_INTENSITY_RATIO + JSON_EXTENSION;

    private static final Map<String, Number>
            initDwellingIssuesRatio = Initializer.initConsolidatedMap(DWELLING_ISSUES_RATIO, dwellingIssuesRatioPath),
            initEndMeetInabilityRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_RATIO, endMeetInabilityRatioPath),
            initEndMeetInabilityGdRatio = Initializer.initConsolidatedMap(END_MEET_INABILITY_GD_RATIO, endMeetInabilityRatioPath),
            initFinancialSatisfactionPath = Initializer.initConsolidatedMap(FINANCIAL_SATISFACTION, financialSatisfactionPath),
            initHighIncomeRatio = Initializer.initConsolidatedMap(HIGH_INCOME_RATIO, highIncomeRatioPath),
            initIncomeQuintileRatio = Initializer.initConsolidatedMap(INCOME_QUINTILE_RATIO, incomeQuintileRatioPath),
            initIncomeQuintileLess65Ratio = Initializer.initConsolidatedMap(INCOME_QUINTILE_LESS_65_RATIO, incomeQuintileRatioPath),
            initIncomeQuintileOver65Ratio = Initializer.initConsolidatedMap(INCOME_QUINTILE_OVER_65_RATIO, incomeQuintileRatioPath),
            initLackOfBathsRatio = Initializer.initConsolidatedMap(LACK_OF_BATHS_RATIO, lackOfBathsRatioPath),
            initLowWorkIntensityRatio = Initializer.initConsolidatedMap(LOW_WORK_INTENSITY_RATIO, workIntensityRatioPath),
            initMaterialDeprivationRatio = Initializer.initConsolidatedMap(MATERIAL_DEPRIVATION_RATIO, materialDeprivationRatioPath),
            initMedianIncome = Initializer.initConsolidatedMap(MEDIAN_INCOME, medianIncomePath),
            initOverOccupiedRatio = Initializer.initConsolidatedMap(OVER_OCCUPIED_RATIO, overOccupiedRatioPath),
            initPovertyRiskRatio = Initializer.initConsolidatedMap(POVERTY_RISK_RATIO, povertyRiskRatioPath),
            initPublicWaterRatio = Initializer.initConsolidatedMap(PUBLIC_WATER_RATIO, publicWaterRatioPath),
            initPpsRatio = Initializer.initConsolidatedMap(PPS_RATIO, ppsRatioPath),
            initUnderOccupiedRatio = Initializer.initConsolidatedMap(UNDER_OCCUPIED_RATIO, underOccupiedRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                dwellingIssuesRatio = Preparation.prepareData(initDwellingIssuesRatio),
                endMeetInabilityRatio = consolidateEndMeetInabilityRatio(),
                financialSatisfactionPath = Preparation.prepareData(initFinancialSatisfactionPath),
                highIncomeRatio = Preparation.prepareData(initHighIncomeRatio),
                incomeQuintileRatio = Preparation.prepareData(initIncomeQuintileRatio),
                incomeQuintileLess65Ratio = Preparation.prepareData(initIncomeQuintileLess65Ratio), // FIXME: not used
                incomeQuintileOver65Ratio = Preparation.prepareData(initIncomeQuintileOver65Ratio), // FIXME: not used
                lackOfBathsRatio = Preparation.prepareData(initLackOfBathsRatio), // FIXME: no data
                lowWorkIntensityRatio = Preparation.prepareData(initLowWorkIntensityRatio),
                materialDeprivationRatio = Preparation.prepareData(initMaterialDeprivationRatio),
                medianIncome = Preparation.prepareData(initMedianIncome), // highIncomeRatio is a better index
                overOccupiedRatio = Preparation.prepareData(initOverOccupiedRatio),
                povertyRiskRatio = Preparation.prepareData(initPovertyRiskRatio),
                publicWaterRatio = Preparation.prepareData(initPublicWaterRatio), // FIXME: no data
                ppsRatio = Preparation.prepareData(initPpsRatio), // TODO: check the data
                underOccupiedRatio = Preparation.prepareData(initUnderOccupiedRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                // FIXME: Inequality of income distribution, by age: incomeQuintileOver65Ratio/incomeQuintileLess65Ratio
                double
                        reverseDwellingIssuesRatio = MathUtils.percentageReverseRatio(dwellingIssuesRatio, key),
                        reverseEndMeetInabilityRatio = MathUtils.percentageReverseRatio(endMeetInabilityRatio, key),
                        reverseIncomeQuintileRatio = MathUtils.percentageReverseRatio(incomeQuintileRatio, key),
                        reverseLackOfBathsRatio = MathUtils.percentageReverseRatio(lackOfBathsRatio, key),
                        reverseLowWorkIntensityRatio = MathUtils.percentageReverseRatio(lowWorkIntensityRatio, key),
                        reverseMaterialDeprivationRatio = MathUtils.percentageReverseRatio(materialDeprivationRatio, key),
                        reverseOverOccupiedRatio = MathUtils.percentageReverseRatio(overOccupiedRatio, key),
                        reversePovertyRiskRatio = MathUtils.percentageReverseRatio(povertyRiskRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(financialSatisfactionPath, key)
                        * MathUtils.percentageSafetyDouble(highIncomeRatio, key)
                        * MathUtils.percentageSafetyDouble(ppsRatio, key)
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
                if (Double.isNaN((Double) value)) {
                    System.err.println(code);
                }
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(lackOfBathsRatio, true));
//        Print.print(purchasingRatio, true);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        //TODO: initIncomeQuintileLess65Ratio, initIncomeQuintileOver65Ratio
        // initMedianIncome, initPublicWaterRatio are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initDwellingIssuesRatio));
            add(Preparation.filterMap(initEndMeetInabilityRatio));
            add(Preparation.filterMap(initEndMeetInabilityGdRatio));
            add(Preparation.filterMap(initFinancialSatisfactionPath));
            add(Preparation.filterMap(initHighIncomeRatio));
            add(Preparation.filterMap(initLackOfBathsRatio));
            add(Preparation.filterMap(initLowWorkIntensityRatio));
            add(Preparation.filterMap(initIncomeQuintileRatio));
            add(Preparation.filterMap(initMaterialDeprivationRatio));
            add(Preparation.filterMap(initOverOccupiedRatio));
            add(Preparation.filterMap(initPovertyRiskRatio));
            add(Preparation.filterMap(initPpsRatio));
            add(Preparation.filterMap(initUnderOccupiedRatio));
        }};
    }

    /**
     * Aggregate the end meet inability "standard" ratio and the end meet inability
     * "great difficulty" ratio into a single indicator (the sum of these).
     *
     * @return A new sorted map which contains the consolidated indicator
     */
    private static Map<String, Number> consolidateEndMeetInabilityRatio() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());
        Map<String, Number>
                endMeetInabilityRatio = Preparation.prepareData(initEndMeetInabilityRatio),
                endMeetInabilityGdRatio = Preparation.prepareData(initEndMeetInabilityGdRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                double value = endMeetInabilityRatio.get(key).doubleValue()
                        + endMeetInabilityGdRatio.get(key).doubleValue();
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
