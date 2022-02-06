package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.MaterialLivingDAO;
import app.java.data.fetch.dao.impl.MaterialLivingDAOImpl;

public class MaterialLivingCollector {
    private static final MaterialLivingDAO materialLivingDAO = new MaterialLivingDAOImpl();

    public static void dataCollector() {
        StringBuilder
                dwellingIssuesRatio = materialLivingDAO.getDwellingIssuesRatio(),
                endMeetInabilityRatio = materialLivingDAO.getEndMeetInabilityRatio(),
                financialSatisfaction = materialLivingDAO.getFinancialSatisfaction(),
                highIncomeRatio = materialLivingDAO.getHighIncomeRatio(),
                incomeQuintileRatio = materialLivingDAO.getIncomeQuintileRatio(),
                lackOfBathsRatio = materialLivingDAO.getLackOfBathsRatio(),
                materialDeprivationRatio = materialLivingDAO.getMaterialDeprivationRatio(),
                medianIncome = materialLivingDAO.getMedianIncome(),
                overOccupiedRatio = materialLivingDAO.getOverOccupiedRatio(),
                povertyRiskRatio = materialLivingDAO.getPovertyRiskRatio(),
                publicWaterRatio = materialLivingDAO.getPublicWaterRatio(),
                purchasingRatio = materialLivingDAO.getPpsRatio(),
                underOccupiedRatio = materialLivingDAO.getUnderOccupiedRatio(),
                workIntensityRatio = materialLivingDAO.getLowWorkIntensityRatio();

        FileUtils.writeToJSONFile(dwellingIssuesRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.DWELLING_ISSUES_RATIO);
        FileUtils.writeToJSONFile(endMeetInabilityRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.END_MEET_INABILITY_RATIO);
        FileUtils.writeToJSONFile(financialSatisfaction, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.FINANCIAL_SATISFACTION);
        FileUtils.writeToJSONFile(highIncomeRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.HIGH_INCOME_RATIO);
        FileUtils.writeToJSONFile(incomeQuintileRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.INCOME_QUINTILE_RATIO);
        FileUtils.writeToJSONFile(lackOfBathsRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.LACK_OF_BATHS_RATIO);
        FileUtils.writeToJSONFile(materialDeprivationRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.MATERIAL_DEPRIVATION_RATIO);
        FileUtils.writeToJSONFile(medianIncome, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.MEDIAN_INCOME);
        FileUtils.writeToJSONFile(overOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.OVER_OCCUPIED_RATIO);
        FileUtils.writeToJSONFile(povertyRiskRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.POVERTY_RISK_RATIO);
        FileUtils.writeToJSONFile(publicWaterRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.PUBLIC_WATER_RATIO);
        FileUtils.writeToJSONFile(purchasingRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.PPS_RATIO);
        FileUtils.writeToJSONFile(underOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.UNDER_OCCUPIED_RATIO);
        FileUtils.writeToJSONFile(workIntensityRatio, FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.WORK_INTENSITY_RATIO);
    }
}
