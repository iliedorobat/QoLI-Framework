package ro.webdata.qoli.aggr.stats.dimensions.health;

import java.util.HashMap;
import java.util.Map;

public class HealthAggrParams {
    public static final String HEALTH = "health";

    public static final String BODY_MASS_INDEX = HEALTH + ":bodyMassIndex";
    public static final String DEPRESSIVE_MAJOR_RATIO = HEALTH + ":depressiveMajorRatio";
    public static final String DEPRESSIVE_NORMAL_RATIO = HEALTH + ":depressiveNormalRatio";
    public static final String DEPRESSIVE_OTHER_RATIO = HEALTH + ":depressiveOtherRatio";
    public static final String DEPRESSIVE_RATIO = HEALTH + ":depressiveRatio";
    public static final String HEALTHY_LIFE_RATIO = HEALTH + ":healthyLifeRatio";
    public static final String HEALTHY_LIFE_YEARS = HEALTH + ":healthyLifeYears";
    public static final String HOSPITAL_BEDS = HEALTH + ":hospitalBeds";
    public static final String LIFE_EXPECTANCY = HEALTH + ":lifeExpectancy";
    public static final String LONG_HEALTH_ISSUES_RATIO = HEALTH + ":longHealthIssuesRatio";
    public static final String NON_ALCOHOLIC_RATIO = HEALTH + ":nonAlcoholicRatio";
    public static final String NON_FRUITS_VEGETABLES_RATIO = HEALTH + ":nonFruitsVegetablesRatio";
    public static final String PERSONNEL_DENTISTS = HEALTH + ":personnelDentists";
    public static final String PERSONNEL_DOCTORS = HEALTH + ":personnelDoctors";
    public static final String PERSONNEL_NURSES = HEALTH + ":personnelNurses";
    public static final String PERSONNEL_PHARMACISTS = HEALTH + ":personnelPharmacists";
    public static final String PERSONNEL_THERAPISTS = HEALTH + ":personnelTherapists";
    public static final String PERSONNEL_TOTAL = HEALTH + ":personnelTotal";
    public static final String PHYSICAL_ACTIVITIES_RATIO = HEALTH + ":physicalActivitiesRatio";
    public static final String SMOKERS_RATIO = HEALTH + ":smokersRatio";
    public static final String UNMET_DENTAL_RATIO = HEALTH + ":unmetDentalStatus";
    public static final String UNMET_MEDICAL_RATIO = HEALTH + ":unmetMedicalStatus";
    public static final String WORK_ACCIDENTS = HEALTH + ":workAccidents";

    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
        put(BODY_MASS_INDEX, "Body Mass Index");
        put(DEPRESSIVE_RATIO, "Depressive Ratio");
        put(HEALTHY_LIFE_RATIO, "Healthy Life Ratio");
        put(HEALTHY_LIFE_YEARS, "Healthy Life Years");
        put(HOSPITAL_BEDS, "Hospital Beds");
        put(LIFE_EXPECTANCY, "Life Expectancy at Birth");
        put(LONG_HEALTH_ISSUES_RATIO, "Long Health Issues Ratio");
        put(NON_ALCOHOLIC_RATIO, "Non-Alcoholic Ratio");
        put(NON_FRUITS_VEGETABLES_RATIO, "Non-Fruits Vegetables Ratio");
        put(PERSONNEL_TOTAL, "Health Personnel Ratio");
        put(PHYSICAL_ACTIVITIES_RATIO, "Physical Activities Ratio");
        put(SMOKERS_RATIO, "Smokers Ratio");
        put(UNMET_DENTAL_RATIO, "Unmet Dental Ratio");
        put(UNMET_MEDICAL_RATIO, "Unmet Medical Ratio");
        put(WORK_ACCIDENTS, "Work Accidents Ratio");
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(BODY_MASS_INDEX, false);
        put(DEPRESSIVE_RATIO, true);
        put(HEALTHY_LIFE_RATIO, false);
        put(HEALTHY_LIFE_YEARS, false);
        put(HOSPITAL_BEDS, false);
        put(LIFE_EXPECTANCY, false);
        put(LONG_HEALTH_ISSUES_RATIO, true);
        put(NON_ALCOHOLIC_RATIO, false);
        put(NON_FRUITS_VEGETABLES_RATIO, true);
        put(PERSONNEL_TOTAL, false);
        put(PHYSICAL_ACTIVITIES_RATIO, false);
        put(SMOKERS_RATIO, true);
        put(UNMET_DENTAL_RATIO, true);
        put(UNMET_MEDICAL_RATIO, true);
        put(WORK_ACCIDENTS, true);
    }};
}
