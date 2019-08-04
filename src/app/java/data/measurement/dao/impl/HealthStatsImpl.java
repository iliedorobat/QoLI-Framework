package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.HealthStatsDAO;

import java.util.Map;

public class HealthStatsImpl implements HealthStatsDAO {
    // The lists of queried values
    private static final String[]
            ALCOHOLIC_RATIO = {"PC", "DAY", "T", "TOTAL", "NAT"},
            BODY_MASS_INDEX_OVERWEIGHT = {"PC", "BMI_GE25", "TOTAL", "T", "TOTAL"},
            BODY_MASS_INDEX_OBESE = {"PC", "BMI_GE30", "TOTAL", "T", "TOTAL"},
            FRUITS_VEGETABLES_RATIO = {"PC", "GE5", "TOTAL", "T", "TOTAL"},
            HEALTH_PERSONNEL = {"P_HTHAB", "OC221"},
            HEALTHY_LIFE_RATIO = {"PC", "TOTAL", "Y_GE16", "T", "VG_G"},
            HEALTHY_LIFE_YEARS_FEMALE = {"F_0_DFLE"},
            HEALTHY_LIFE_YEARS_MALE = {"M_0_DFLE"},
            HOSPITAL_BEDS = {"P_HTHAB", "HBEDT"},
            LIFE_EXPECTANCY = {"YR", "T", "Y_LT1"},
            LONG_HEALTH_ISSUE_RATIO = {"PC", "TOTAL", "Y_GE16", "T"},
            PHYSICAL_ACTIVITIES = {"PC", "MV_AERO_MSC", "TOTAL", "T", "TOTAL"},
            SMOKERS_RATIO = {"PC", "TOTAL", "TOTAL", "T", "TOTAL"},
            UNMET_DENTAL_STATUS = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            UNMET_MEDICAL_STATUS = {"PC", "TOTAL", "TOOEFW", "Y_GE16", "T"},
            WORK_ACCIDENTS = {"NR", "TOTAL", "D_GE4"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
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

    private static final Map<String, Number>
            alcoholicRatio = MeasureUtils.consolidateList(ALCOHOLIC_RATIO, alcoholicRatioPath),
            bodyMassIndexOverweight = MeasureUtils.consolidateList(BODY_MASS_INDEX_OVERWEIGHT, bodyMassIndexPath),
            bodyMassIndexObese = MeasureUtils.consolidateList(BODY_MASS_INDEX_OBESE, bodyMassIndexPath),
            fruitsVegetablesRatio = MeasureUtils.consolidateList(FRUITS_VEGETABLES_RATIO, fruitsVegetablesRatioPath),
            healthPersonnel = MeasureUtils.consolidateList(HEALTH_PERSONNEL, healthPersonnelPath),
            healthyLifeRatio = MeasureUtils.consolidateList(HEALTHY_LIFE_RATIO, healthyLifeRatioPath),
            healthyLifeYearsFemale = MeasureUtils.consolidateList(HEALTHY_LIFE_YEARS_FEMALE, healthyLifeYearsPath),
            healthyLifeYearsMale = MeasureUtils.consolidateList(HEALTHY_LIFE_YEARS_MALE, healthyLifeYearsPath),
            hospitalBeds = MeasureUtils.consolidateList(HOSPITAL_BEDS, hospitalBedsPath),
            lifeExpectancy = MeasureUtils.consolidateList(LIFE_EXPECTANCY, lifeExpectancyPath),
            longHealthIssueRatio = MeasureUtils.consolidateList(LONG_HEALTH_ISSUE_RATIO, longHealthIssueRatioPath),
            physicalActivities = MeasureUtils.consolidateList(PHYSICAL_ACTIVITIES, physicalActivitiesPath),
            smokersRatio = MeasureUtils.consolidateList(SMOKERS_RATIO, smokersRatioPath),
            unmetDentalStatus = MeasureUtils.consolidateList(UNMET_DENTAL_STATUS, unmetDentalStatusPath),
            unmetMedicalStatus = MeasureUtils.consolidateList(UNMET_MEDICAL_STATUS, unmetMedicalStatusPath),
            workAccidents = MeasureUtils.consolidateList(WORK_ACCIDENTS, workAccidentsPath);

    public void print() {
//        System.out.println(alcoholicRatio);
        MeasureUtils.print(healthyLifeYearsPath);
    }
}
