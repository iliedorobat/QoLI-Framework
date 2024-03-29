package app.java.aggr.commons.dimensions.health;

import app.java.aggr.commons.constants.ParamsNames;
import app.java.aggr.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static app.java.aggr.commons.constants.ParamsValues.HEALTH_PROBLEMS;
import static app.java.aggr.commons.constants.ParamsValues.ISCO08;

public class HealthParams {
    public static final MultiValuedMap<String, String> BMI_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.BMI, "BMI18P5-24");     // Normal body mass
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.QUANT_INC, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> DEPRESSIVE_RATIO_PARAMS = getDepressiveParams(HEALTH_PROBLEMS.values());

    public static final MultiValuedMap<String, String>
            DEPRESSIVE_NORMAL_RATIO_PARAMS = getDepressiveParams(HEALTH_PROBLEMS.get("depressive")),
            DEPRESSIVE_MAJOR_RATIO_PARAMS = getDepressiveParams(HEALTH_PROBLEMS.get("majorDepressive")),
            DEPRESSIVE_OTHER_RATIO_PARAMS = getDepressiveParams(HEALTH_PROBLEMS.get("otherDepressive"));

    public static final MultiValuedMap<String, String>
            HEALTH_PERSONNEL_PARAMS = getHealthPersonnelParams(ISCO08.values()),
            PERSONNEL_DENTISTS_PARAMS = getHealthPersonnelParams(ISCO08.get("dentists")),
            PERSONNEL_DOCTORS_PARAMS = getHealthPersonnelParams(ISCO08.get("doctors")),
            PERSONNEL_NURSES_PARAMS = getHealthPersonnelParams(ISCO08.get("nurses")),
            PERSONNEL_PHARMA_PARAMS = getHealthPersonnelParams(ISCO08.get("pharmacists")),
            PERSONNEL_THERAPISTS_PARAMS = getHealthPersonnelParams(ISCO08.get("physiotherapists"));

    public static final MultiValuedMap<String, String> HEALTHY_LIFE_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.LEVELS, "VG_G");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> HEALTHY_LIFE_YEARS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_HE, "HLY_0"); // Healthy life years in absolute value at birth
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "YR");
    }};

    public static final MultiValuedMap<String, String> HOSPITAL_BEDS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FACILITY, "HBEDT");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.UNIT, "P_HTHAB");
    }};

    public static final MultiValuedMap<String, String> LIFE_EXPECTANCY_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_LT1"); // Life expectancy for people less than 1 year
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "YR");
    }};

    public static final MultiValuedMap<String, String> LONG_HEALTH_ISSUE_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> NON_ALCOHOLIC_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.FREQUENCY, "NVR_NM12");
        put(ParamsNames.QUANT_INC, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static MultiValuedMap<String, String> NON_FRUITS_VEGETABLES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.N_PORTION, "0");
        put(ParamsNames.QUANT_INC, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> PHYSICAL_ACTIVITIES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.PHYSICAL_ACTIVITY, "MV_AERO_MSC");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> SMOKERS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.QUANT_INC, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.SMOKING, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNMET_DENTAL_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.REASON, "TOOEFW");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNMET_MEDICAL_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_GE16");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.QUANTILE, "TOTAL");
        put(ParamsNames.REASON, "TOOEFW");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WORK_ACCIDENTS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INJURY, "TOTAL");
        put(ParamsNames.NACE_R2, "A_C-N");
        put(ParamsNames.SEVERITY, "D_GE4");
        put(ParamsNames.UNIT, "NR");
    }};

    private static MultiValuedMap<String, String> getHealthPersonnelParams(String role) {
        List<String> roles = new ArrayList<>() {{ add(role); }};
        return getHealthPersonnelParams(roles);
    }

    private static MultiValuedMap<String, String> getHealthPersonnelParams(Collection<String> roles) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.UNIT, "P_HTHAB");
        }};
        FetcherUtils.addParams(params, ParamsNames.ISCO_08, roles);
        return params;
    }

    private static MultiValuedMap<String, String> getDepressiveParams(String healthProblem) {
        List<String> healthProblems = new ArrayList<>() {{ add(healthProblem); }};
        return getDepressiveParams(healthProblems);
    }

    private static MultiValuedMap<String, String> getDepressiveParams(Collection<String> healthProblems) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "TOTAL");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.QUANT_INC, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.HEALTH_PROBLEMS, healthProblems);
        return params;
    }
}
