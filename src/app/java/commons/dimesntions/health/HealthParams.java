package app.java.commons.dimesntions.health;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class HealthParams {
    private static MultiValuedMap<String, String> getConsumptionParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getUnmetHealthParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
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
            put(ParamsConst.QUANTILE, "TOTAL");
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
            put(ParamsConst.N_PORTION, "GE5");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getHealthPersonnelParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getHealthPersonnelParams(String role) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.ISCO_08, role);
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.LEVELS, "VG_G");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeYearsParams() {
        return new HashSetValuedHashMap<>() {{
            // Healthy life years in absolute value at birth
            put(ParamsConst.INDIC_HE, "HLY_0");
            put(ParamsConst.SEX, ParamsValues.SEX.get("female"));
            put(ParamsConst.SEX, ParamsValues.SEX.get("male"));
        }};
    }

    public static MultiValuedMap<String, String> getHealthyLifeYearsParams(String sex) {
        return new HashSetValuedHashMap<>() {{
            // Healthy life years in absolute value at birth
            put(ParamsConst.INDIC_HE, "HLY_0");
            put(ParamsConst.SEX, sex);
        }};
    }

    public static MultiValuedMap<String, String> getHospitalBedsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FACILITY, "HBEDT");
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    public static MultiValuedMap<String, String> getLifeExpectancyParams() {
        return new HashSetValuedHashMap<>() {{
            // Life expectancy for people less than 1 year
            put(ParamsConst.AGE, "Y_LT1");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "YR");
        }};
    }

    // TODO: issue => issues
    public static MultiValuedMap<String, String> getLongHealthIssueParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getPhysicalActivitiesParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getSmokersParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.QUANTILE, "TOTAL");
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
            put(ParamsConst.INJURY, "TOTAL");
            put(ParamsConst.SEVERITY, "D_GE4");
            put(ParamsConst.UNIT, "NR");
        }};
    }
}
