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
                alcoholicRatio = healthDAO.getAlcoholicRatio(),
                bodyMassIndexRatio = healthDAO.getBodyMassIndexRatio(),
                fVRatio = healthDAO.getFVRatio(),
                lifeExpectancy = healthDAO.getLifeExpectancy(),
                longHealthIssueRatio = healthDAO.getLongHealthIssueRatio(),
                healthPersonnelRatio = healthDAO.getHealthPersonnelRatio(),
                healthyLifeRatio = healthDAO.getHealthyLifeRatio(),
                hospitalBedsRatio = healthDAO.getHospitalBedsRatio(),
                healthyLifeYears = healthDAO.getHealthyLifeYears(),
                physicalActivitiesRatio = healthDAO.getPhysicalActivitiesRatio(),
                smokersRatio = healthDAO.getSmokersRatio(),
                unmetDentalRatio = healthDAO.getUnmetDentalRatio(),
                unmetMedicalRatio = healthDAO.getUnmetMedicalRatio(),
                workAccidents = healthDAO.getWorkAccidents();

        FileUtils.writeToJSONFile(alcoholicRatio, FilePathConst.HEALTH_PATH, FileNameConst.ALCOHOLIC_RATIO);
        FileUtils.writeToJSONFile(bodyMassIndexRatio, FilePathConst.HEALTH_PATH, FileNameConst.BODY_MASS_INDEX);
        FileUtils.writeToJSONFile(fVRatio, FilePathConst.HEALTH_PATH, FileNameConst.FRUITS_VEGETABLES_RATIO);
        FileUtils.writeToJSONFile(lifeExpectancy, FilePathConst.HEALTH_PATH, FileNameConst.LIFE_EXPECTANCY);
        FileUtils.writeToJSONFile(longHealthIssueRatio, FilePathConst.HEALTH_PATH, FileNameConst.LONG_HEALTH_ISSUE_RATIO);
        FileUtils.writeToJSONFile(healthyLifeRatio, FilePathConst.HEALTH_PATH, FileNameConst.HEALTHY_LIFE_RATIO);
        FileUtils.writeToJSONFile(healthPersonnelRatio, FilePathConst.HEALTH_PATH, FileNameConst.HEALTH_PERSONNEL);
        FileUtils.writeToJSONFile(healthyLifeYears, FilePathConst.HEALTH_PATH, FileNameConst.HEALTHY_LIFE_YEARS);
        FileUtils.writeToJSONFile(hospitalBedsRatio, FilePathConst.HEALTH_PATH, FileNameConst.HOSPITAL_BEDS);
        FileUtils.writeToJSONFile(physicalActivitiesRatio, FilePathConst.HEALTH_PATH, FileNameConst.PHYSICAL_ACTIVITIES);
        FileUtils.writeToJSONFile(smokersRatio, FilePathConst.HEALTH_PATH, FileNameConst.SMOKERS_RATIO);
        FileUtils.writeToJSONFile(unmetDentalRatio, FilePathConst.HEALTH_PATH, FileNameConst.UNMET_DENTAL_RATIO);
        FileUtils.writeToJSONFile(unmetMedicalRatio, FilePathConst.HEALTH_PATH, FileNameConst.UNMET_MEDICAL_RATIO);
        FileUtils.writeToJSONFile(workAccidents, FilePathConst.HEALTH_PATH, FileNameConst.WORK_ACCIDENTS);
    }
}
