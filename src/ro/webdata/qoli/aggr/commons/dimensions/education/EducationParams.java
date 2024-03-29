package app.java.aggr.commons.dimensions.education;

import app.java.aggr.commons.constants.ParamsNames;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class EducationParams {
    public static final MultiValuedMap<String, String> DIGITAL_SKILLS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_IS, "I_DSK2_BAB");
        put(ParamsNames.IND_TYPE, "IND_TOTAL");
        put(ParamsNames.UNIT, "PC_IND");
    }};

    public static final MultiValuedMap<String, String> DROPOUT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y18-24");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
        put(ParamsNames.WORKING_STATUS, "POP");
    }};

    public static final MultiValuedMap<String, String> EARLY_EDUCATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> EDUCATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "ED3-8");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INACTIVE_YOUNG_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y15-29");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.TRAINING, "NO_FE_NO_NFE");
        put(ParamsNames.UNIT, "PC");
        put(ParamsNames.WORKING_STATUS, "NEMP");
    }};

    public static final MultiValuedMap<String, String> NO_KNOWN_FOREIGN_LANG_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y25-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.N_LANG, "0");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> PUPILS_RATIO_2012_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_ED, "ST1_1");
    }};

    public static final MultiValuedMap<String, String> PUPILS_RATIO_2013_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "ED1-3");
        put(ParamsNames.UNIT, "RT");
    }};

    public static final MultiValuedMap<String, String> TRAINING_LAST_MONTH_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y25-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.ISCED_11, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> TRAINING_LAST_YEAR_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y25-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.TRAINING, "FE_NFE");     // Formal and non-formal education and training
        put(ParamsNames.UNIT, "PC");
    }};
}
