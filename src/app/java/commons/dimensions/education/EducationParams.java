package app.java.commons.dimensions.education;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class EducationParams {
    // TODO: move to ParamsValues
    public static final String[] EDUCATION_LEVELS = {
            "ED5-8", // Tertiary education (levels 5-8)
            "ED3_4", // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
            "ED0-2"  // Less than primary, primary and lower secondary education (levels 0-2)
    };

    public static MultiValuedMap<String, String> getDigitalSkillsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.INDIC_IS, "I_DSK2_BAB");
            put(ParamsConst.IND_TYPE, "IND_TOTAL");
            put(ParamsConst.UNIT, "PC_IND");
        }};
    }

    public static MultiValuedMap<String, String> getDropoutParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y18-24");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
            put(ParamsConst.WORKING_STATUS, "POP");
        }};
    }

    public static MultiValuedMap<String, String> getEarlyEducationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getEducationParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y15-64");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.ISCED_11, EDUCATION_LEVELS);
        return params;
    }

    public static MultiValuedMap<String, String> getInactiveYoungParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y18-24");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.TRAINING, "NO_FE_NO_NFE");
            put(ParamsConst.UNIT, "PC");
            put(ParamsConst.WORKING_STATUS, "NEMP");
        }};
    }

    public static MultiValuedMap<String, String> getNoKnownForeignLangParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y25-64");
            put(ParamsConst.N_LANG, "0");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getPupilsParams2012() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.INDIC_ED, "ST1_1");
        }};
    }

    public static MultiValuedMap<String, String> getPupilsParams2013() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.ISCED_11, "ED1-3");
            put(ParamsConst.UNIT, "RT");
        }};
    }

    public static MultiValuedMap<String, String> getTrainingParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y25-64");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static final MultiValuedMap<String, String>
            DIGITAL_SKILLS_PARAMS = EducationParams.getDigitalSkillsParams(),
            DROPOUT_RATIO_PARAMS = EducationParams.getDropoutParams(),
            EARLY_EDUCATION_RATIO_PARAMS = EducationParams.getEarlyEducationParams(),
            INACTIVE_YOUNG_RATIO_PARAMS = EducationParams.getInactiveYoungParams(),
            NO_KNOWN_FOREIGN_LANG_RATIO_PARAMS = EducationParams.getNoKnownForeignLangParams(),
            PUPILS_RATIO_2012_PARAMS = EducationParams.getPupilsParams2012(),
            PUPILS_RATIO_2013_PARAMS = EducationParams.getPupilsParams2013(),
            STUDENTS_RATIO_PARAMS = EducationParams.getEducationParams(),
            TRAINING_RATIO_PARAMS = EducationParams.getTrainingParams();
}
