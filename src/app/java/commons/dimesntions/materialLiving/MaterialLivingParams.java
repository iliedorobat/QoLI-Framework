package app.java.commons.dimesntions.materialLiving;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class MaterialLivingParams {
    private static final String[] END_MEET_DIFFICULTIES = {
            "EM_GD",    // households making ends meet with great difficulty
            "EM_D"      // households making ends meet with difficulty
    };
    private static final String[] QUINTILE_AGES = {
            "TOTAL",
            "Y_GE65",
            "Y_LT65"
    };

    public static MultiValuedMap<String, String> getDwellingsOccupationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    private static MultiValuedMap<String, String> getHomeConditionsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getDwellingIssuesParams() {
        return getHomeConditionsParams();
    }

    public static MultiValuedMap<String, String> getEndMeetInabilityParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        FetcherUtils.addParams(params, ParamsConst.SUBJNMON, END_MEET_DIFFICULTIES);
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getFinancialSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("FINANCIAL")
        );
    }

    public static MultiValuedMap<String, String> getHighIncomeParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "LI_GE130MD");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getIncomeQuintileParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.AGE, QUINTILE_AGES);
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "RAT");
        return params;
    }

    public static MultiValuedMap<String, String> getLackOfBathsParams() {
        return getHomeConditionsParams();
    }

    public static MultiValuedMap<String, String> getLowWorkIntensityParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_LT60");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_Y_LT60");
        return params;
    }

    public static MultiValuedMap<String, String> getMaterialDeprivationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getMedianIncome() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "MED_E");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PPS");
        return params;
    }

    public static MultiValuedMap<String, String> getOverOccupiedParams() {
        return getDwellingsOccupationParams();
    }

    public static MultiValuedMap<String, String> getPovertyRiskParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INDIC_IL, "LI_R_MD60");
        return params;
    }

    public static MultiValuedMap<String, String> getPublicWaterParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WAT_PROC, "POP_PWS");
        return params;
    }

    public static MultiValuedMap<String, String> getPpsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.NA_ITEM, "B1GQ");
        params.put(ParamsConst.UNIT, "PC_EU28_HAB_MEUR_CP");
        return params;
    }

    public static MultiValuedMap<String, String> getUnderOccupiedParams() {
        return getDwellingsOccupationParams();
    }
}
