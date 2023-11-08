package app.java.commons.dimensions.materialLiving;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MaterialLivingParams {
    private static final String[] END_MEETS_DIFFICULTY_LEVELS = {
            ParamsValues.SUBJNMON.get("greatDifficulty"),
            ParamsValues.SUBJNMON.get("difficulty")
    };

    private static HashMap<String, String> QUINTILE_AGES = new HashMap<>() {{
        put("lower_18", "Y_LT18");   // Less than 18 years
        put("18_to_64", "Y18-64");  // Between 18-64 years
        put("over_65", "Y_GE65");   // 65 years or over
        put("total", "TOTAL");      // Total
    }};

    private static MultiValuedMap<String, String> getHomeConditionsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static final MultiValuedMap<String, String> DWELLING_ISSUES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.HHTYP, "TOTAL");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            END_MEET_INABILITY_RATIO_PARAMS = getEndMeetInabilityParams(),
            END_MEET_INABILITY_D_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("difficulty")),
            END_MEET_INABILITY_GD_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("greatDifficulty"));

    public static final MultiValuedMap<String, String> FINANCIAL_SATISFACTION_PARAMS = CommonParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("FINANCIAL")
    );

    public static final MultiValuedMap<String, String> HIGH_INCOME_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INDIC_IL, "LI_GE130MD");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            INCOME_QUINTILE_RATIO_PARAMS = getIncomeQuintileParams(),
            INCOME_QUINTILE_LESS_65_RATIO_PARAMS = getIncomeQuintileParams(QUINTILE_AGES.get("18_to_64")),
            INCOME_QUINTILE_OVER_65_RATIO_PARAMS = getIncomeQuintileParams(QUINTILE_AGES.get("over_65"));

    public static final MultiValuedMap<String, String> LACK_OF_BATHS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.HHTYP, "TOTAL");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> LOW_WORK_INTENSITY_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "Y_LT60");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC_Y_LT60");
    }};

    public static final MultiValuedMap<String, String> MATERIAL_DEPRIVATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> MEDIAN_INCOME_PPS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INDIC_IL, "MED_E");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PPS");
    }};

    public static final MultiValuedMap<String, String> OVER_OCCUPIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POVERTY_RISK_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.HHTYP, "TOTAL");
        put(ParamsConst.INDIC_IL, "LI_R_MD60");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNDER_OCCUPIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AGE, "TOTAL");
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.SEX, "T");
        put(ParamsConst.UNIT, "PC");
    }};

    private static MultiValuedMap<String, String> getEndMeetInabilityParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.SUBJNMON, END_MEETS_DIFFICULTY_LEVELS);
        return params;
    }

    private static MultiValuedMap<String, String> getEndMeetInabilityParams(String difficultyLevel) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SUBJNMON, difficultyLevel);
            put(ParamsConst.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getIncomeQuintileParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "RAT");
        }};
        FetcherUtils.addParams(params, ParamsConst.AGE, QUINTILE_AGES);
        return params;
    }

    public static MultiValuedMap<String, String> getIncomeQuintileParams(String age) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, age);
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "RAT");
        }};
    }
}
