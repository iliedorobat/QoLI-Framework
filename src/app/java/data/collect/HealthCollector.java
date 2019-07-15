package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
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

        TextUtils.writeToJSONFile(lifeExpectancy, FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY);
        TextUtils.writeToJSONFile(healthyLifeYears, FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS);
        TextUtils.writeToJSONFile(healthyLifeRatio, FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO);
        TextUtils.writeToJSONFile(longHealthIssueRatio, FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUE_RATIO);
        TextUtils.writeToJSONFile(unmetMedicalStatus, FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_STATUS);
        TextUtils.writeToJSONFile(unmetDentalStatus, FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_STATUS);
        TextUtils.writeToJSONFile(bodyMassIndex, FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX);
        TextUtils.writeToJSONFile(smokersRatio, FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO);
        TextUtils.writeToJSONFile(alcoholicRatio, FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO);
        TextUtils.writeToJSONFile(fVRatio, FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO);
        TextUtils.writeToJSONFile(physicalActivities, FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES);
        TextUtils.writeToJSONFile(hospitalBeds, FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS);
        TextUtils.writeToJSONFile(workAccidents, FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS);
        TextUtils.writeToJSONFile(healthPersonnel, FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL);
    }
}
