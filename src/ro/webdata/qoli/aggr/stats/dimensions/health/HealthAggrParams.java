package ro.webdata.qoli.aggr.stats.dimensions.health;

import java.util.HashMap;
import java.util.Map;

public class HealthAggrParams {
    public static final String HEALTH = "health";

    public static final String BODY_MASS_INDEX = "bodyMassIndex";
    public static final String DEPRESSIVE_MAJOR_RATIO = "depressiveMajorRatio";
    public static final String DEPRESSIVE_NORMAL_RATIO = "depressiveNormalRatio";
    public static final String DEPRESSIVE_OTHER_RATIO = "depressiveOtherRatio";
    public static final String DEPRESSIVE_RATIO = "depressiveRatio";
    public static final String HEALTHY_LIFE_RATIO = "healthyLifeRatio";
    public static final String HEALTHY_LIFE_YEARS = "healthyLifeYears";
    public static final String HOSPITAL_BEDS = "hospitalBeds";
    public static final String LIFE_EXPECTANCY = "lifeExpectancy";
    public static final String LONG_HEALTH_ISSUES_RATIO = "longHealthIssuesRatio";
    public static final String NON_ALCOHOLIC_RATIO = "nonAlcoholicRatio";
    public static final String NON_FRUITS_VEGETABLES_RATIO = "nonFruitsVegetablesRatio";
    public static final String PERSONNEL_DENTISTS = "personnelDentists";
    public static final String PERSONNEL_DOCTORS = "personnelDoctors";
    public static final String PERSONNEL_NURSES = "personnelNurses";
    public static final String PERSONNEL_PHARMACISTS = "personnelPharmacists";
    public static final String PERSONNEL_THERAPISTS = "personnelTherapists";
    public static final String PERSONNEL_TOTAL = "personnelTotal";
    public static final String PHYSICAL_ACTIVITIES_RATIO = "physicalActivitiesRatio";
    public static final String SMOKERS_RATIO = "smokersRatio";
    public static final String UNMET_DENTAL_RATIO = "unmetDentalStatus";
    public static final String UNMET_MEDICAL_RATIO = "unmetMedicalStatus";
    public static final String WORK_ACCIDENTS = "workAccidents";

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
