package app.java.commons.dimensions.materialLiving;

import app.java.commons.constants.ParamsNames;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;

import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.SATISFACTION_TYPES_PARAMS;

public class MaterialLivingParams {
    private static final String[] END_MEETS_DIFFICULTY_LEVELS = {
            ParamsValues.SUBJNMON.get("greatDifficulty"),
            ParamsValues.SUBJNMON.get("difficulty")
    };

    private static HashMap<String, String> QUINTILE_AGES = new HashMap<>() {{
        put("less_65", "Y_LT65");   // less than 65 years
        put("over_65", "Y_GE65");   // 65 years or over
        put("total", "TOTAL");      // Total
    }};

    public static final MultiValuedMap<String, String> DWELLING_ISSUES_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            END_MEET_INABILITY_RATIO_PARAMS = getEndMeetInabilityParams(),
            END_MEET_INABILITY_D_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("difficulty")),
            END_MEET_INABILITY_GD_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("greatDifficulty"));

    public static final MultiValuedMap<String, String> FINANCIAL_SATISFACTION_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS_PARAMS.get("HIGH"),
            SATISFACTION_TYPES_PARAMS.get("FINSAT")
    );

    public static final MultiValuedMap<String, String> GDP_PER_CAPITA_PPS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.PPP_CAT, "GDP");
        put(ParamsNames.NA_ITEM, "VI_PPS_EU27_2020_HAB");
        put(ParamsNames.FREQ, "A");
    }};

    public static final MultiValuedMap<String, String> HIGH_INCOME_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_IL, "LI_GE130MD");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> INCOME_QUINTILE_RATIO_PARAMS = getIncomeQuintileParams();

    public static final MultiValuedMap<String, String> LACK_OF_BATHS_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> LOW_WORK_INTENSITY_RATIO_PARAMS = AuxiliaryParams.LOW_WORK_INTENSITY_RATIO_PARAMS;

    public static final MultiValuedMap<String, String> MATERIAL_DEPRIVATION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> MEDIAN_INCOME_PPS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_IL, "MED_E");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PPS");
    }};

    public static final MultiValuedMap<String, String> OVER_OCCUPIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POVERTY_RISK_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INDIC_IL, "LI_R_MD60");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> UNDER_OCCUPIED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC");
    }};

    private static MultiValuedMap<String, String> getEndMeetInabilityParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.HHTYP, "TOTAL");
            put(ParamsNames.INC_GRP, "TOTAL");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.SUBJNMON, END_MEETS_DIFFICULTY_LEVELS);
        return params;
    }

    private static MultiValuedMap<String, String> getEndMeetInabilityParams(String difficultyLevel) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.HHTYP, "TOTAL");
            put(ParamsNames.INC_GRP, "TOTAL");
            put(ParamsNames.SUBJNMON, difficultyLevel);
            put(ParamsNames.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getIncomeQuintileParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "RAT");
        }};
        FetcherUtils.addParams(params, ParamsNames.AGE, QUINTILE_AGES);
        return params;
    }
}
