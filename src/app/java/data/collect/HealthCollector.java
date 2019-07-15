package app.java.data.collect;

import app.java.commons.Constants;
import app.java.commons.TextUtils;
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

        TextUtils.writeToJSONFile(lifeExpectancy, Constants.HEALTH_PATH + "lifeExpectancy");
        TextUtils.writeToJSONFile(healthyLifeYears, Constants.HEALTH_PATH + "healthyLifeYears");
        TextUtils.writeToJSONFile(healthyLifeRatio, Constants.HEALTH_PATH + "healthyLifeRatio");
        TextUtils.writeToJSONFile(longHealthIssueRatio, Constants.HEALTH_PATH + "longHealthIssueRatio");
        TextUtils.writeToJSONFile(unmetMedicalStatus, Constants.HEALTH_PATH + "unmetMedicalStatus");
        TextUtils.writeToJSONFile(unmetDentalStatus, Constants.HEALTH_PATH + "unmetDentalStatus");
        TextUtils.writeToJSONFile(bodyMassIndex, Constants.HEALTH_PATH + "bodyMassIndex");
        TextUtils.writeToJSONFile(smokersRatio, Constants.HEALTH_PATH + "smokersRatio");
        TextUtils.writeToJSONFile(alcoholicRatio, Constants.HEALTH_PATH + "alcoholicRatio");
        TextUtils.writeToJSONFile(fVRatio, Constants.HEALTH_PATH + "fVRatio");
        TextUtils.writeToJSONFile(physicalActivities, Constants.HEALTH_PATH + physicalActivities);
        TextUtils.writeToJSONFile(hospitalBeds, Constants.HEALTH_PATH + "hospitalBeds");
        TextUtils.writeToJSONFile(workAccidents, Constants.HEALTH_PATH + "workAccidents");
        TextUtils.writeToJSONFile(healthPersonnel, Constants.HEALTH_PATH + "healthPersonnel");
    }
}
