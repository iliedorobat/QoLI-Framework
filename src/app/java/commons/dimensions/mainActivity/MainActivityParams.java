package app.java.commons.dimensions.mainActivity;

import app.java.commons.constants.ParamsNames;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.constants.ParamsValues.*;

public class MainActivityParams {
    private static final String[] FLEXIBILITY = {
            WORKING_FLEXIBILITY.get("total"),
            WORKING_FLEXIBILITY.get("personDecision"),
            WORKING_FLEXIBILITY.get("restrictiveDecision")
    };

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
            SATISFACTION_LEVELS.get("high"),
            SATISFACTION_TYPES.get("job")
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

    public static final MultiValuedMap<String, String> LOW_WORK_INTENSITY_RATIO_PARAMS = AuxiliaryParams.LOW_WORK_INTENSITY_RATIO_PARAMS;

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

    public static final MultiValuedMap<String, String> WORKING_FLEXIBILITY_RATIO_PARAMS = getFlexibilityParams(FLEXIBILITY);

    public static final MultiValuedMap<String, String>
            WORKING_FLEXIBILITY_FULL_RATIO_PARAMS = getFlexibilityParams(WORKING_FLEXIBILITY.get("personDecision")),
            WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_PARAMS = getFlexibilityParams(WORKING_FLEXIBILITY.get("restrictiveDecision")),
            WORKING_FLEXIBILITY_TOTAL_RATIO_PARAMS = getFlexibilityParams(WORKING_FLEXIBILITY.get("total"));

    public static final MultiValuedMap<String, String> WORKING_NIGHTS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.FREQUENCY, "USU");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
        put(ParamsNames.WORKING_STATUS, "EMP");
    }};

    private static MultiValuedMap<String, String> getFlexibilityParams(String flexibility) {
        String[] flexibilities = new String[] { flexibility };
        return getFlexibilityParams(flexibilities);
    }

    private static MultiValuedMap<String, String> getFlexibilityParams(String[] flexibilities) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y15-74");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "THS_PER");
            put(ParamsNames.WORKING_STATUS, "EMP");
        }};
        FetcherUtils.addParams(params, ParamsNames.WORKING_TIME_FLEX, flexibilities);
        return params;
    }
}
