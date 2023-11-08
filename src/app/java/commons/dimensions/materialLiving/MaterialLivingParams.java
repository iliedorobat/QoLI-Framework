package app.java.commons.dimensions.materialLiving;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimensions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MaterialLivingParams {
    private static String[] END_MEETS_DIFFICULTY_LEVELS = {
            ParamsValues.SUBJNMON.get("greatDifficulty"),
            ParamsValues.SUBJNMON.get("difficulty")
    };

    private static MultiValuedMap<String, String> getDwellingsOccupationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

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

    public static MultiValuedMap<String, String> getDwellingIssuesParams() {
        return getHomeConditionsParams();
    }

    public static MultiValuedMap<String, String> getEndMeetInabilityParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.SUBJNMON, END_MEETS_DIFFICULTY_LEVELS);
        return params;
    }

    public static MultiValuedMap<String, String> getEndMeetInabilityParams(String difficultyLevel) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SUBJNMON, difficultyLevel);
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getFinancialSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("FINANCIAL")
        );
    }

    public static MultiValuedMap<String, String> getHighIncomeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.INDIC_IL, "LI_GE130MD");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getIncomeQuintileParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "RAT");
        }};
        FetcherUtils.addParams(params, ParamsConst.AGE, ParamsValues.AGE);
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

    public static MultiValuedMap<String, String> getLackOfBathsParams() {
        return getHomeConditionsParams();
    }

    public static MultiValuedMap<String, String> getLowWorkIntensityParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_LT60");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC_Y_LT60");
        }};
    }

    public static MultiValuedMap<String, String> getMaterialDeprivationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getMedianIncome() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.INDIC_IL, "MED_E");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PPS");
        }};
    }

    public static MultiValuedMap<String, String> getOverOccupiedParams() {
        return getDwellingsOccupationParams();
    }

    public static MultiValuedMap<String, String> getPovertyRiskParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INDIC_IL, "LI_R_MD60");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getUnderOccupiedParams() {
        return getDwellingsOccupationParams();
    }

    public static final MultiValuedMap<String, String>
            DWELLING_ISSUES_RATIO_PARAMS = getDwellingIssuesParams(),
            END_MEET_INABILITY_D_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("difficulty")),
            END_MEET_INABILITY_GD_RATIO_PARAMS = getEndMeetInabilityParams(ParamsValues.SUBJNMON.get("greatDifficulty")),
            FINANCIAL_SATISFACTION_PARAMS = getFinancialSatisfactionParams(),
            HIGH_INCOME_RATIO_PARAMS = getHighIncomeParams(),
            INCOME_QUINTILE_RATIO_PARAMS = getIncomeQuintileParams(ParamsValues.AGE.get("total")),
            INCOME_QUINTILE_LESS_65_RATIO_PARAMS = getIncomeQuintileParams(ParamsValues.AGE.get("lower_65")),
            INCOME_QUINTILE_OVER_65_RATIO_PARAMS = getIncomeQuintileParams(ParamsValues.AGE.get("over_65")),
            LACK_OF_BATHS_RATIO_PARAMS = getLackOfBathsParams(),
            LOW_WORK_INTENSITY_RATIO_PARAMS = getLowWorkIntensityParams(),
            MATERIAL_DEPRIVATION_RATIO_PARAMS = getMaterialDeprivationParams(),
            MEDIAN_INCOME_PPS_PARAMS = getMedianIncome(),
            OVER_OCCUPIED_RATIO_PARAMS = getOverOccupiedParams(),
            POVERTY_RISK_RATIO_PARAMS = getPovertyRiskParams(),
            UNDER_OCCUPIED_RATIO_PARAMS = getUnderOccupiedParams();
}
