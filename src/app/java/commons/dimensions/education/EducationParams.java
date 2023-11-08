package app.java.commons.dimensions.education;

import app.java.commons.constants.ParamsConst;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class EducationParams {
    public static final MultiValuedMap<String, String> DIGITAL_SKILLS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INDIC_IS, "I_DSK2_BAB");
        put(ParamsConst.IND_TYPE, "IND_TOTAL");
        put(ParamsConst.UNIT, "PC_IND");
    }};

    public static final MultiValuedMap<String, String> DROPOUT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y18-24");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
        put(ParamsConst.WORKING_STATUS, "POP");
    }};

    public static final MultiValuedMap<String, String> EARLY_EDUCATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> EDUCATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y15-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "ED3-8");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INACTIVE_YOUNG_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y18-24");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.TRAINING, "NO_FE_NO_NFE");
        put(ParamsConst.UNIT, "PC");
        put(ParamsConst.WORKING_STATUS, "NEMP");
    }};

    public static final MultiValuedMap<String, String> NO_KNOWN_FOREIGN_LANG_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y25-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.N_LANG, "0");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> PUPILS_RATIO_2012_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INDIC_ED, "ST1_1");
    }};

    public static final MultiValuedMap<String, String> PUPILS_RATIO_2013_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "ED1-3");
        put(ParamsConst.UNIT, "RT");
    }};

    public static final MultiValuedMap<String, String> TRAINING_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y25-64");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.ISCED_11, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};
}
