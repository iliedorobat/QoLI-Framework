package app.java.data.collect;

import app.java.data.fetch.dao.HealthDAO;
import app.java.data.fetch.dao.impl.HealthDAOImpl;

public class HealthCollector {
    private static HealthDAO healthDAO = new HealthDAOImpl();

    public static void dataCollector() {
        StringBuilder
                lifeExpectancy = healthDAO.getLifeExpectancy(),
                healthyLifeYears = healthDAO.getHealthyLifeYears(),
                healthyLifeRatio = healthDAO.getHealthyLifeRatio(),
                longHealthIssueRatio = healthDAO.getLongHealthIssueRatio(),
                unmetMedicalStatus = healthDAO.getUnmetMedicalStatus(),
                unmetDentalStatus = healthDAO.getUnmetDentalStatus(),
                bodyMassIndex = healthDAO.getBodyMassIndex(),
                smokersRatio = healthDAO.getSmokersRatio(),
                alcoholicRatio = healthDAO.getAlcoholicRatio(),
                fVRatio = healthDAO.getFVRatio(),
                physicalActivities = healthDAO.getPhysicalActivities(),
                hospitalBeds = healthDAO.getHospitalBeds(),
                workAccidents = healthDAO.getWorkAccidents(),
                healthPersonnel = healthDAO.getHealthPersonnel();
    }
}
