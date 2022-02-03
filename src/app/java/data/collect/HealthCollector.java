package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.HealthDAO;
import app.java.data.fetch.dao.impl.HealthDAOImpl;

public class HealthCollector {
    private static final HealthDAO healthDAO = new HealthDAOImpl();

    public static void dataCollector() {
        StringBuilder
                lifeExpectancy = healthDAO.getLifeExpectancy(),
                healthyLifeYears = healthDAO.getHealthyLifeYears(),
                healthyLifeRatio = healthDAO.getHealthyLifeRatio(),
                longHealthIssueRatio = healthDAO.getLongHealthIssueRatio(),
                unmetMedicalStatus = healthDAO.getUnmetMedicalRatio(),
                unmetDentalStatus = healthDAO.getUnmetDentalRatio(),
                bodyMassIndex = healthDAO.getBodyMassIndex(),
                smokersRatio = healthDAO.getSmokersRatio(),
                alcoholicRatio = healthDAO.getAlcoholicRatio(),
                fVRatio = healthDAO.getFVRatio(),
                physicalActivities = healthDAO.getPhysicalActivities(),
                hospitalBeds = healthDAO.getHospitalBeds(),
                workAccidents = healthDAO.getWorkAccidents(),
                healthPersonnel = healthDAO.getHealthPersonnel();

        FileUtils.writeToJSONFile(lifeExpectancy, FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY);
        FileUtils.writeToJSONFile(healthyLifeYears, FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS);
        FileUtils.writeToJSONFile(healthyLifeRatio, FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO);
        FileUtils.writeToJSONFile(longHealthIssueRatio, FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUE_RATIO);
        FileUtils.writeToJSONFile(unmetMedicalStatus, FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_RATIO);
        FileUtils.writeToJSONFile(unmetDentalStatus, FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_RATIO);
        FileUtils.writeToJSONFile(bodyMassIndex, FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX);
        FileUtils.writeToJSONFile(smokersRatio, FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO);
        FileUtils.writeToJSONFile(alcoholicRatio, FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO);
        FileUtils.writeToJSONFile(fVRatio, FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO);
        FileUtils.writeToJSONFile(physicalActivities, FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES);
        FileUtils.writeToJSONFile(hospitalBeds, FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS);
        FileUtils.writeToJSONFile(workAccidents, FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS);
        FileUtils.writeToJSONFile(healthPersonnel, FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL);
    }
}
