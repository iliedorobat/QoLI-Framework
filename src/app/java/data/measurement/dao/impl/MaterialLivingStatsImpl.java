package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.MaterialLivingStatsDAO;

import java.util.Map;

public class MaterialLivingStatsImpl implements MaterialLivingStatsDAO {
    // The lists of queried values
    private static final String[]
            DWELLING_ISSUES_RATIO = {"TOTAL", "TOTAL", "PC", "T", "TOTAL"},
            END_MEET_INABILITY_RATIO = {"PC", "EM_D", "TOTAL", "TOTAL"}, // with difficulty
            END_MEET_INABILITY_GD_RATIO = {"PC", "EM_GD", "TOTAL", "TOTAL"}, // with great difficulty
            HIGH_INCOME_RATIO = {"PC", "LI_GE130MD", "T", "TOTAL"},
            INCOME_QUINTILE_RATIO = {"S80_S20", "TOTAL", "T"},
            INCOME_QUINTILE_LESS_65_RATIO = {"S80_S20", "Y_LT65", "T"},
            INCOME_QUINTILE_OVER_65_RATIO = {"S80_S20", "Y_GE65", "T"},
            LACK_OF_BATHS_RATIO = {"PC", "TOTAL", "TOTAL", "T", "TOTAL"},
            MATERIAL_DEPRIVATION_RATIO = {"PC", "TOTAL", "T"},
            MEDIAN_INCOME = {"TOTAL", "T", "MED_E", "PPS"},
            OVER_OCCUPIED_RATIO = {"PC", "TOTAL", "TOTAL", "T"},
            POVERTY_RISK_RATIO = {"LI_R_MD60", "TOTAL"},
            PUBLIC_WATER_RATIO = {"POP_PWS", "PC"},
            PURCHASING_RATIO = {"PC_EU28_HAB_MEUR_CP", "B1GQ"},
            UNDER_OCCUPIED_RATIO = {"PC", "TOTAL", "T", "TOTAL"},
            WORK_INTENSITY_RATIO = {"PC_Y_LT60", "Y_LT60", "T"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            dwellingIssuesRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.DWELLING_ISSUES_RATIO + JSON_EXT,
            endMeetInabilityRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.END_MEET_INABILITY_RATIO + JSON_EXT,
            highIncomeRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.HIGH_INCOME_RATIO + JSON_EXT,
            incomeQuintileRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.INCOME_QUINTILE_RATIO + JSON_EXT,
            lackOfBathsRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.LACK_OF_BATHS_RATIO + JSON_EXT,
            materialDeprivationRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MATERIAL_DEPRIVATION_RATIO + JSON_EXT,
            medianIncomePath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MEDIAN_INCOME + JSON_EXT,
            overOccupiedRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.OVER_OCCUPIED_RATIO + JSON_EXT,
            povertyRiskRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.POVERTY_RISK_RATIO + JSON_EXT,
            publicWaterRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PUBLIC_WATER_RATIO + JSON_EXT,
            purchasingRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PURCHASING_RATIO + JSON_EXT,
            underOccupiedRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.UNDER_OCCUPIED_RATIO + JSON_EXT,
            workIntensityRatioPath = FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.WORK_INTENSITY_RATIO + JSON_EXT;

    private static final Map<String, Number>
            dwellingIssuesRatio = MeasureUtils.consolidateList(DWELLING_ISSUES_RATIO, dwellingIssuesRatioPath),
            endMeetInabilityRatio = MeasureUtils.consolidateList(END_MEET_INABILITY_RATIO, endMeetInabilityRatioPath),
            endMeetInabilityGdRatio = MeasureUtils.consolidateList(END_MEET_INABILITY_GD_RATIO, endMeetInabilityRatioPath),
            highIncomeRatio = MeasureUtils.consolidateList(HIGH_INCOME_RATIO, highIncomeRatioPath),
            incomeQuintileRatio = MeasureUtils.consolidateList(INCOME_QUINTILE_RATIO, incomeQuintileRatioPath),
            incomeQuintileLess65Ratio = MeasureUtils.consolidateList(INCOME_QUINTILE_LESS_65_RATIO, incomeQuintileRatioPath),
            incomeQuintileOver65Ratio = MeasureUtils.consolidateList(INCOME_QUINTILE_OVER_65_RATIO, incomeQuintileRatioPath),
            lackOfBathsRatio = MeasureUtils.consolidateList(LACK_OF_BATHS_RATIO, lackOfBathsRatioPath),
            materialDeprivationRatio = MeasureUtils.consolidateList(MATERIAL_DEPRIVATION_RATIO, materialDeprivationRatioPath),
            medianIncome = MeasureUtils.consolidateList(MEDIAN_INCOME, medianIncomePath),
            overOccupiedRatio = MeasureUtils.consolidateList(OVER_OCCUPIED_RATIO, overOccupiedRatioPath),
            povertyRiskRatio = MeasureUtils.consolidateList(POVERTY_RISK_RATIO, povertyRiskRatioPath),
            publicWaterRatio = MeasureUtils.consolidateList(PUBLIC_WATER_RATIO, publicWaterRatioPath),
            purchasingRatio = MeasureUtils.consolidateList(PURCHASING_RATIO, purchasingRatioPath),
            underOccupiedRatio = MeasureUtils.consolidateList(UNDER_OCCUPIED_RATIO, underOccupiedRatioPath),
            workIntensityRatio = MeasureUtils.consolidateList(WORK_INTENSITY_RATIO, workIntensityRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(incomeQuintileRatioPath);
    }
}
