package app.java.commons.dimensions.mainActivity;

import app.java.commons.constants.ParamsNames;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_TYPES_PARAMS;

public class MainActivityParams {
    public static final MultiValuedMap<String, String> AVG_WORK_HOURS_2007_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.NACE_R1, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "HR");
        put(ParamsNames.WORK_TIME, "TOTAL");
        put(ParamsNames.WORKING_STATUS, "EMP");
    }};

    public static final MultiValuedMap<String, String> AVG_WORK_HOURS_2008_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.NACE_R2, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "HR");
        put(ParamsNames.WORK_TIME, "TOTAL");
        put(ParamsNames.WORKING_STATUS, "EMP");
    }};

    public static final MultiValuedMap<String, String> EMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INACTIVE_POPULATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INVOLUNTARY_PART_TIME_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> JOB_SATISFACTION_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("JOB")
    );

    public static final MultiValuedMap<String, String> LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-74");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_EM, "LTU");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC_ACT");
    }};

    public static final MultiValuedMap<String, String> LOW_WAGE_EARNINGS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SIZECLAS, "GE10");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> OVER_QUALIFIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.MGSTATUS, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> RESEARCHERS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.PROF_POS, "TOTAL");
        put(ParamsNames.SECT_PERF, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "FTE");
    }};

    public static final MultiValuedMap<String, String> TEMPORARY_EMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC_EMP");
        put(ParamsNames.WORKING_STATUS, "EMP_TEMP");
    }};

    public static final MultiValuedMap<String, String> UNEMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-74");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WORKING_NIGHTS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.FREQUENCY, "USU");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
        put(ParamsNames.WORKING_STATUS, "EMP");
    }};
}
