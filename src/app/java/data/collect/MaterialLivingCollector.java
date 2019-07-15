package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.TextUtils;
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

        TextUtils.writeToJSONFile(purchasingRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PURCHASING_RATIO);
        TextUtils.writeToJSONFile(medianIncome, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MEDIAN_INCOME);
        TextUtils.writeToJSONFile(incomeQuintileRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.INCOME_QUINTILE_RATIO);
        TextUtils.writeToJSONFile(povertyRiskRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.POVERTY_RISK_RATIO);
        TextUtils.writeToJSONFile(highIncomeRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.HIGH_INCOME_RATIO);
        TextUtils.writeToJSONFile(materialDeprivationRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.MATERIAL_DEPRIVATION_RATIO);
        TextUtils.writeToJSONFile(endMeetInabilityRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.END_MEET_INABILITY_RATIO);
        TextUtils.writeToJSONFile(underOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.UNDER_OCCUPIED_RATIO);
        TextUtils.writeToJSONFile(overOccupiedRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.OVER_OCCUPIED_RATIO);
        TextUtils.writeToJSONFile(dwellingIssuesRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.DWELLING_ISSUES_RATIO);
        TextUtils.writeToJSONFile(lackOfBathsRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.LACK_OF_BATHS_RATIO);
        TextUtils.writeToJSONFile(workIntensityRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.WORK_INTENSITY_RATIO);
        TextUtils.writeToJSONFile(publicWaterRatio, FilePathConst.MATERIAL_LIVING_PATH + FileNameConst.PUBLIC_WATER_RATIO);
    }
}
