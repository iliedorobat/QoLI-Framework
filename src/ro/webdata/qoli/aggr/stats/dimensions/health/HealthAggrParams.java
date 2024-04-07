package ro.webdata.qoli.aggr.stats.dimensions.health;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.*;

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

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
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

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(BODY_MASS_INDEX, PERCENT);
        put(DEPRESSIVE_RATIO, PERCENT);
        put(HEALTHY_LIFE_RATIO, PERCENT);
        put(HEALTHY_LIFE_YEARS, YEARS);
        put(HOSPITAL_BEDS, PER_MILLION_INHABITANTS);
        put(LIFE_EXPECTANCY, YEARS);
        put(LONG_HEALTH_ISSUES_RATIO, PERCENT);
        put(NON_ALCOHOLIC_RATIO, PERCENT);
        put(NON_FRUITS_VEGETABLES_RATIO, PERCENT);
        put(PERSONNEL_TOTAL, PER_MILLION_INHABITANTS);
        put(PHYSICAL_ACTIVITIES_RATIO, PERCENT);
        put(SMOKERS_RATIO, PERCENT);
        put(UNMET_DENTAL_RATIO, PERCENT);
        put(UNMET_MEDICAL_RATIO, PERCENT);
        put(WORK_ACCIDENTS, PER_THOUSAND_INHABITANTS);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATES = new HashMap<>() {{
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

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(BODY_MASS_INDEX, "Body Mass Index");
        put(DEPRESSIVE_MAJOR_RATIO, "Depressive Symptoms (Major) Ratio");
        put(DEPRESSIVE_NORMAL_RATIO, "Depressive Symptoms (Normal) Ratio");
        put(DEPRESSIVE_OTHER_RATIO, "Depressive Symptoms (Other) Ratio");
        put(HEALTHY_LIFE_RATIO, "Healthy Life Ratio");
        put(HEALTHY_LIFE_YEARS, "Healthy Life Years");
        put(HOSPITAL_BEDS, "Hospital Beds");
        put(LIFE_EXPECTANCY, "Life Expectancy at Birth");
        put(LONG_HEALTH_ISSUES_RATIO, "Long Health Issues Ratio");
        put(NON_ALCOHOLIC_RATIO, "Non-Alcoholic Ratio");
        put(NON_FRUITS_VEGETABLES_RATIO, "Non-Fruits Vegetables Ratio");
        put(PERSONNEL_DENTISTS, "Health Personnel (Dentists) Ratio");
        put(PERSONNEL_DOCTORS, "Health Personnel (Doctors) Ratio");
        put(PERSONNEL_NURSES, "Health Personnel (Nurses and Midwives) Ratio");
        put(PERSONNEL_PHARMACISTS, "Health Personnel (Pharmacists) Ratio");
        put(PERSONNEL_THERAPISTS, "Health Personnel (Therapists) Ratio");
        put(PHYSICAL_ACTIVITIES_RATIO, "Physical Activities Ratio");
        put(SMOKERS_RATIO, "Smokers Ratio");
        put(UNMET_DENTAL_RATIO, "Unmet Dental Ratio");
        put(UNMET_MEDICAL_RATIO, "Unmet Medical Ratio");
        put(WORK_ACCIDENTS, "Work Accidents Ratio");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(BODY_MASS_INDEX, PERCENT);
        put(DEPRESSIVE_MAJOR_RATIO, PERCENT);
        put(DEPRESSIVE_NORMAL_RATIO, PERCENT);
        put(DEPRESSIVE_OTHER_RATIO, PERCENT);
        put(HEALTHY_LIFE_RATIO, PERCENT);
        put(HEALTHY_LIFE_YEARS, YEARS);
        put(HOSPITAL_BEDS, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(LIFE_EXPECTANCY, YEARS);
        put(LONG_HEALTH_ISSUES_RATIO, PERCENT);
        put(NON_ALCOHOLIC_RATIO, PERCENT);
        put(NON_FRUITS_VEGETABLES_RATIO, PERCENT);
        put(PERSONNEL_DENTISTS, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(PERSONNEL_DOCTORS, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(PERSONNEL_NURSES, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(PERSONNEL_PHARMACISTS, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(PERSONNEL_THERAPISTS, PER_HUNDRED_THOUSAND_INHABITANTS);
        put(PHYSICAL_ACTIVITIES_RATIO, PERCENT);
        put(SMOKERS_RATIO, PERCENT);
        put(UNMET_DENTAL_RATIO, PERCENT);
        put(UNMET_MEDICAL_RATIO, PERCENT);
        put(WORK_ACCIDENTS, PER_THOUSAND_INHABITANTS);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATES = new HashMap<>() {{
        put(BODY_MASS_INDEX, false);
        put(DEPRESSIVE_MAJOR_RATIO, true);
        put(DEPRESSIVE_NORMAL_RATIO, true);
        put(DEPRESSIVE_OTHER_RATIO, true);
        put(HEALTHY_LIFE_RATIO, false);
        put(HEALTHY_LIFE_YEARS, false);
        put(HOSPITAL_BEDS, false);
        put(LIFE_EXPECTANCY, false);
        put(LONG_HEALTH_ISSUES_RATIO, true);
        put(NON_ALCOHOLIC_RATIO, false);
        put(NON_FRUITS_VEGETABLES_RATIO, true);
        put(PERSONNEL_DENTISTS, false);
        put(PERSONNEL_DOCTORS, false);
        put(PERSONNEL_NURSES, false);
        put(PERSONNEL_PHARMACISTS, false);
        put(PERSONNEL_THERAPISTS, false);
        put(PHYSICAL_ACTIVITIES_RATIO, false);
        put(SMOKERS_RATIO, true);
        put(UNMET_DENTAL_RATIO, true);
        put(UNMET_MEDICAL_RATIO, true);
        put(WORK_ACCIDENTS, true);
    }};
}
