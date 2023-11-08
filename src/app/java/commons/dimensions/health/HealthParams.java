package app.java.commons.dimensions.health;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class HealthParams {
    public static final MultiValuedMap<String, String> ALCOHOLIC_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.C_BIRTH, "NAT");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.FREQUENCY, "DAY");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> BMI_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.BMI, "BMI_GE25"); // Overweight
        put(ParamsConst.BMI, "BMI_GE30"); // Obese
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.QUANT_INC, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> BMI_OVERWEIGHT_PARAMS =
            getBodyMassIndexParams(ParamsValues.BMI.get("overweight"));

    public static final MultiValuedMap<String, String> BMI_OBESE_PARAMS =
            getBodyMassIndexParams(ParamsValues.BMI.get("obese"));

    public static MultiValuedMap<String, String> FRUITS_VEGETABLES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.N_PORTION, "GE5");
        put(ParamsConst.QUANT_INC, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            HEALTH_PERSONNEL_PARAMS = getHealthPersonnelParams(),
            PERSONNEL_DENTISTS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("dentists")),
            PERSONNEL_DOCTORS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("doctors")),
            PERSONNEL_NURSES_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("nurses")),
            PERSONNEL_PHARMA_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("pharmacists")),
            PERSONNEL_THERAPISTS_PARAMS = getHealthPersonnelParams(ParamsValues.ISCO08.get("physiotherapists"));

    public static final MultiValuedMap<String, String> HEALTHY_LIFE_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_GE16");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.LEVELS, "VG_G");
        put(ParamsConst.QUANTILE, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> HEALTHY_LIFE_YEARS_PARAMS = getHealthyLifeYearsParams();

    public static final MultiValuedMap<String, String> HOSPITAL_BEDS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FACILITY, "HBEDT");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.UNIT, "P_HTHAB");
    }};

    public static final MultiValuedMap<String, String> LIFE_EXPECTANCY_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_LT1"); // Life expectancy for people less than 1 year
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "YR");
    }};

    public static final MultiValuedMap<String, String> LONG_HEALTH_ISSUE_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_GE16");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.QUANTILE, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> PHYSICAL_ACTIVITIES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "TOTAL");
        put(ParamsConst.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> SMOKERS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.QUANT_INC, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.SMOKING, "TOTAL");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNMET_DENTAL_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_GE16");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.QUANTILE, "TOTAL");
        put(ParamsConst.REASON, "TOOEFW");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNMET_MEDICAL_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_GE16");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.QUANTILE, "TOTAL");
        put(ParamsConst.REASON, "TOOEFW");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WORK_ACCIDENTS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INJURY, "TOTAL");
        put(ParamsConst.NACE_R2, "A_C-N");
        put(ParamsConst.SEVERITY, "D_GE4");
        put(ParamsConst.UNIT, "NR");
    }};

    private static MultiValuedMap<String, String> getBodyMassIndexParams(String bmi) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.BMI, bmi);
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getHealthPersonnelParams() {
        HashSetValuedHashMap params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
        FetcherUtils.addParams(params, ParamsConst.ISCO_08, ParamsValues.ISCO08);
        return params;
    }

    private static MultiValuedMap<String, String> getHealthPersonnelParams(String role) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ISCO_08, role);
            put(ParamsConst.UNIT, "P_HTHAB");
        }};
    }

    private static MultiValuedMap<String, String> getHealthyLifeYearsParams() {
        HashSetValuedHashMap params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.INDIC_HE, "HLY_0"); // Healthy life years in absolute value at birth
            put(ParamsConst.UNIT, "YR");
        }};
        FetcherUtils.addParams(params, ParamsConst.SEX, ParamsValues.SEX);
        return params;
    }
}
