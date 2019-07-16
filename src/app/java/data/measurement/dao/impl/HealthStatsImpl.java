package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.dao.HealthStatsDAO;

public class HealthStatsImpl implements HealthStatsDAO {
    private static String JSON_EXT = Constants.JSON_EXTENSION;
    private static String
            alcoholicRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.ALCOHOLIC_RATIO + JSON_EXT,
            bodyMassIndexPath = FilePathConst.HEALTH_PATH + FileNameConst.BODY_MASS_INDEX + JSON_EXT,
            fruitsVegetablesRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.FRUITS_VEGETABLES_RATIO + JSON_EXT,
            healthPersonnelPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTH_PERSONNEL + JSON_EXT,
            healthyLifeRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_RATIO + JSON_EXT,
            healthyLifeYearsPath = FilePathConst.HEALTH_PATH + FileNameConst.HEALTHY_LIFE_YEARS + JSON_EXT,
            hospitalBedsPath = FilePathConst.HEALTH_PATH + FileNameConst.HOSPITAL_BEDS + JSON_EXT,
            lifeExpectancyPath = FilePathConst.HEALTH_PATH + FileNameConst.LIFE_EXPECTANCY + JSON_EXT,
            longHealthIssueRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.LONG_HEALTH_ISSUE_RATIO + JSON_EXT,
            physicalActivitiesPath = FilePathConst.HEALTH_PATH + FileNameConst.PHYSICAL_ACTIVITIES + JSON_EXT,
            smokersRatioPath = FilePathConst.HEALTH_PATH + FileNameConst.SMOKERS_RATIO + JSON_EXT,
            unmetDentalStatusPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_DENTAL_STATUS + JSON_EXT,
            unmetMedicalStatusPath = FilePathConst.HEALTH_PATH + FileNameConst.UNMET_MEDICAL_STATUS + JSON_EXT,
            workAccidentsPath = FilePathConst.HEALTH_PATH + FileNameConst.WORK_ACCIDENTS + JSON_EXT;
}
