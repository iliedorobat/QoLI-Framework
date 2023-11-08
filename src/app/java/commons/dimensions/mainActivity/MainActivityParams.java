package app.java.commons.dimensions.mainActivity;

import app.java.commons.constants.ParamsConst;
import app.java.commons.dimensions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MainActivityParams {
    public static final MultiValuedMap<String, String> AVG_WORK_HOURS_2007_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.NACE_R1, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "HR");
        put(ParamsConst.WORK_TIME, "TOTAL");
        put(ParamsConst.WORKING_STATUS, "EMP");
    }};

    public static final MultiValuedMap<String, String> AVG_WORK_HOURS_2008_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.NACE_R2, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "HR");
        put(ParamsConst.WORK_TIME, "TOTAL");
        put(ParamsConst.WORKING_STATUS, "EMP");
    }};

    public static final MultiValuedMap<String, String> EMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INACTIVE_POPULATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INVOLUNTARY_PART_TIME_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> JOB_SATISFACTION_PARAMS = CommonParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("JOB")
    );

    public static final MultiValuedMap<String, String> LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-74");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INDIC_EM, "LTU");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC_ACT");
    }};

    public static final MultiValuedMap<String, String> LOW_WAGE_EARNINGS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SIZECLAS, "GE10");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> OVER_QUALIFIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "TOTAL");
        put(ParamsConst.MGSTATUS, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> RESEARCHERS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.PROF_POS, "TOTAL");
        put(ParamsConst.SECT_PERF, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "FTE");
    }};

    public static final MultiValuedMap<String, String> TEMPORARY_EMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC_EMP");
        put(ParamsConst.WORKING_STATUS, "EMP_TEMP");
    }};

    public static final MultiValuedMap<String, String> UNEMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-74");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WORKING_NIGHTS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.FREQUENCY, "USU");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
        put(ParamsConst.WORKING_STATUS, "EMP");
    }};
}
