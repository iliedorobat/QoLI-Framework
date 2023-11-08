package app.java.commons.dimensions.health;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class HealthParams {
    private static MultiValuedMap<String, String> getUnmetHealthParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.REASON, "TOOEFW");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getAlcoholicParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.C_BIRTH, "NAT");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.FREQUENCY, "DAY");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getBodyMassIndexParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.BMI, "BMI_GE25"); // Overweight
            put(ParamsConst.BMI, "BMI_GE30"); // Obese
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.QUANT_INC, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getBodyMassIndexParams(String bmi) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.BMI, bmi);
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getFVParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.N_PORTION, "GE5");
            put(ParamsConst.QUANT_INC, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getHealthPersonnelParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            // TODO: add ISCO_08?
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getHealthPersonnelParams(String role) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ISCO_08, role);
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.LEVELS, "VG_G");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeYearsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            // Healthy life years in absolute value at birth
            put(ParamsConst.INDIC_HE, "HLY_0");
            // FIXME: put(ParamsConst.SEX, "T");
            put(ParamsConst.SEX, "F");
            put(ParamsConst.SEX, "M");
            put(ParamsConst.UNIT, "YR");
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeYearsParams(String sex) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            // Healthy life years in absolute value at birth
            put(ParamsConst.INDIC_HE, "HLY_0");
            put(ParamsConst.SEX, sex);
        }};
    }

    public static MultiValuedMap<String, String> getHospitalBedsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FACILITY, "HBEDT");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getLifeExpectancyParams() {
        return new HashSetValuedHashMap<>() {{
            // Life expectancy for people less than 1 year
            put(ParamsConst.AGE, "Y_LT1");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "YR");
        }};
    }

    public static MultiValuedMap<String, String> getLongHealthIssuesParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getPhysicalActivitiesParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getSmokersParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.QUANT_INC, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.SMOKING, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getUnmetDentalParams() {
        return getUnmetHealthParams();
    }

    public static MultiValuedMap<String, String> getUnmetMedicalParams() {
        return getUnmetHealthParams();
    }

    public static MultiValuedMap<String, String> getWorkAccidentsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.INJURY, "TOTAL");
            put(ParamsConst.NACE_R2, "A_C-N");
            put(ParamsConst.SEVERITY, "D_GE4");
            put(ParamsConst.UNIT, "NR");
        }};
    }

    public static final MultiValuedMap<String, String>
            ALCOHOLIC_RATIO_PARAMS = getAlcoholicParams(),
            BMI_OVERWEIGHT_PARAMS = getBodyMassIndexParams(ParamsValues.BMI.get("overweight")),
            BMI_OBESE_PARAMS = getBodyMassIndexParams(ParamsValues.BMI.get("obese")),
            FRUITS_VEGETABLES_RATIO_PARAMS = getFVParams(),
            HEALTHY_LIFE_RATIO_PARAMS = getHealthyLifeParams(),
            HEALTHY_LIFE_YEARS_FEMALE_PARAMS = getHealthyLifeYearsParams("F"),
            HEALTHY_LIFE_YEARS_MALE_PARAMS = getHealthyLifeYearsParams("M"),
            HOSPITAL_BEDS_PARAMS = getHospitalBedsParams(),
            LIFE_EXPECTANCY_PARAMS = getLifeExpectancyParams(),
            LONG_HEALTH_ISSUE_RATIO_PARAMS = getLongHealthIssuesParams(),
            PERSONNEL_DENTISTS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("dentists")),
            PERSONNEL_DOCTORS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("doctors")),
            PERSONNEL_NURSES_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("nurses")),
            PERSONNEL_PHARMA_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("pharmacists")),
            PERSONNEL_THERAPISTS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("physiotherapists")),
            PHYSICAL_ACTIVITIES_RATIO_PARAMS = getPhysicalActivitiesParams(),
            SMOKERS_RATIO_PARAMS = getSmokersParams(),
            UNMET_DENTAL_RATIO_PARAMS = getUnmetDentalParams(),
            UNMET_MEDICAL_RATIO_PARAMS = getUnmetMedicalParams(),
            WORK_ACCIDENTS_PARAMS = getWorkAccidentsParams();
}
