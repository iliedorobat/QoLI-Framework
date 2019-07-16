package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.FileUtils;
import app.java.data.fetch.dao.MaterialLivingDAO;
import app.java.data.fetch.dao.impl.MaterialLivingDAOImpl;

public class MaterialLivingCollector {
    private static MaterialLivingDAO materialLivingDAO = new MaterialLivingDAOImpl();

    public static void dataCollector() {
        StringBuilder purchasingRatio = materialLivingDAO.getPurchasingRatio(),
                medianIncome = materialLivingDAO.getMedianIncome(),
                incomeQuintileRatio = materialLivingDAO.getIncomeQuintileRatio(),
                povertyRiskRatio = materialLivingDAO.getPovertyRiskRatio(),
                highIncomeRatio = materialLivingDAO.getHighIncomeRatio(),
                materialDeprivationRatio = materialLivingDAO.getMaterialDeprivationRatio(),
                endMeetInabilityRatio = materialLivingDAO.getEndMeetInabilityRatio(),
                underOccupiedRatio = materialLivingDAO.getUnderOccupiedRatio(),
                overOccupiedRatio = materialLivingDAO.getOverOccupiedRatio(),
                dwellingIssuesRatio = materialLivingDAO.getDwellingIssuesRatio(),
                lackOfBathsRatio = materialLivingDAO.getLackOfBathsRatio(),
                workIntensityRatio = materialLivingDAO.getWorkIntensityRatio(),
                publicWaterRatio = materialLivingDAO.getPublicWaterRatio();

        FileUtils.writeToJSONFile(purchasingRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PURCHASING_RATIO);
        FileUtils.writeToJSONFile(medianIncome, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MEDIAN_INCOME);
        FileUtils.writeToJSONFile(incomeQuintileRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.INCOME_QUINTILE_RATIO);
        FileUtils.writeToJSONFile(povertyRiskRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.POVERTY_RISK_RATIO);
        FileUtils.writeToJSONFile(highIncomeRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.HIGH_INCOME_RATIO);
        FileUtils.writeToJSONFile(materialDeprivationRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MATERIAL_DEPRIVATION_RATIO);
        FileUtils.writeToJSONFile(endMeetInabilityRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.END_MEET_INABILITY_RATIO);
        FileUtils.writeToJSONFile(underOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.UNDER_OCCUPIED_RATIO);
        FileUtils.writeToJSONFile(overOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.OVER_OCCUPIED_RATIO);
        FileUtils.writeToJSONFile(dwellingIssuesRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.DWELLING_ISSUES_RATIO);
        FileUtils.writeToJSONFile(lackOfBathsRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.LACK_OF_BATHS_RATIO);
        FileUtils.writeToJSONFile(workIntensityRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.WORK_INTENSITY_RATIO);
        FileUtils.writeToJSONFile(publicWaterRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PUBLIC_WATER_RATIO);
    }
}
