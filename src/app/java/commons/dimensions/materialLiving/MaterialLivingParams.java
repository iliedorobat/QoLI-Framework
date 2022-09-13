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
    private static MultiValuedMap<String, String> getDwellingsOccupationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    private static MultiValuedMap<String, String> getHomeConditionsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
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
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsConst.SUBJNMON, ParamsValues.SUBJNMON);
        return params;
    }

    public static MultiValuedMap<String, String> getEndMeetInabilityParams(String difficulty) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.SUBJNMON, difficulty);
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
            put(ParamsConst.INDIC_IL, "LI_GE130MD");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getIncomeQuintileParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "RAT");
        }};
        FetcherUtils.addParams(params, ParamsConst.AGE, ParamsValues.AGE);
        return params;
    }

    public static MultiValuedMap<String, String> getIncomeQuintileParams(String age) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, age);
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
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC_Y_LT60");
        }};
    }

    public static MultiValuedMap<String, String> getMaterialDeprivationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getMedianIncome() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
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
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INDIC_IL, "LI_R_MD60");
        }};
    }

    public static MultiValuedMap<String, String> getUnderOccupiedParams() {
        return getDwellingsOccupationParams();
    }
}
