package app.java.data.collect;

import app.java.commons.Constants;
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

        TextUtils.writeToJSONFile(purchasingRatio, Constants.MATERIAL_LIVING_PATH + "purchasingRatio");
        TextUtils.writeToJSONFile(medianIncome, Constants.MATERIAL_LIVING_PATH + "medianIncome");
        TextUtils.writeToJSONFile(incomeQuintileRatio, Constants.MATERIAL_LIVING_PATH + "incomeQuintileRatio");
        TextUtils.writeToJSONFile(povertyRiskRatio, Constants.MATERIAL_LIVING_PATH + "povertyRiskRatio");
        TextUtils.writeToJSONFile(highIncomeRatio, Constants.MATERIAL_LIVING_PATH + "highIncomeRatio");
        TextUtils.writeToJSONFile(materialDeprivationRatio, Constants.MATERIAL_LIVING_PATH + "materialDeprivationRatio");
        TextUtils.writeToJSONFile(endMeetInabilityRatio, Constants.MATERIAL_LIVING_PATH + "endMeetInabilityRatio");
        TextUtils.writeToJSONFile(underOccupiedRatio, Constants.MATERIAL_LIVING_PATH + "underOccupiedRatio");
        TextUtils.writeToJSONFile(overOccupiedRatio, Constants.MATERIAL_LIVING_PATH + "overOccupiedRatio");
        TextUtils.writeToJSONFile(dwellingIssuesRatio, Constants.MATERIAL_LIVING_PATH + "dwellingIssuesRatio");
        TextUtils.writeToJSONFile(lackOfBathsRatio, Constants.MATERIAL_LIVING_PATH + "lackOfBathsRatio");
        TextUtils.writeToJSONFile(workIntensityRatio, Constants.MATERIAL_LIVING_PATH + "workIntensityRatio");
        TextUtils.writeToJSONFile(publicWaterRatio, Constants.MATERIAL_LIVING_PATH + "publicWaterRatio");
    }
}
