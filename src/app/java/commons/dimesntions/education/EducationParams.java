package app.java.commons.dimesntions.education;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;

public class EducationParams {
    public static final String[] EDUCATION_LEVELS = {
            "ED5-8", // Tertiary education (levels 5-8)
            "ED3_4", // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
            "ED0-2"  // Less than primary, primary and lower secondary education (levels 0-2)
    };

    public static MultiValuedMap<String, String> getDigitalSkillsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INDIC_IS, "I_DSK_BAB");
        params.put(ParamsConst.IND_TYPE, "IND_TOTAL");
        params.put(ParamsConst.UNIT, "PC_IND");
        return params;
    }

    public static MultiValuedMap<String, String> getDropoutParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y18-24");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "POP");
        return params;
    }

    public static MultiValuedMap<String, String> getEarlyEducationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getEducationParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        FetcherUtils.addParams(params, ParamsConst.ISCED_11, EDUCATION_LEVELS);
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getInactiveYoungParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y18-24");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.TYPTRAI, "NO_FE_NO_NFE");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "NEMP");
        return params;
    }

    public static MultiValuedMap<String, String> getNoKnownForeignLangParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y25-64");
        params.put(ParamsConst.N_LANG, "0");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getPupilsParams2012() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INDIC_ED, "ST1_1");
        return params;
    }

    public static MultiValuedMap<String, String> getPupilsParams2013() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.ISCED_11, "ED1-3");
        params.put(ParamsConst.UNIT, "RT");
        return params;
    }

    public static MultiValuedMap<String, String> getTrainingParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y25-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }
}
