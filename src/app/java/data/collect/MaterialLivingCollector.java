package app.java.data.collect;

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
    }
}
