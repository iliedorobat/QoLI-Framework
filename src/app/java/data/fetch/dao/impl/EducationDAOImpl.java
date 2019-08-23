package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.EducationDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class EducationDAOImpl implements EducationDAO {
    public static final String[] EDUCATION_LEVELS = {
            "ED5-8", // Tertiary education (levels 5-8)
            "ED3_4", // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
            "ED0-2"  // Less than primary, primary and lower secondary education (levels 0-2)
    };

    public StringBuilder getDigitalSkillsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INDIC_IS, "I_DSK_BAB");
        params.put(ParamsConst.IND_TYPE, "IND_TOTAL");
        params.put(ParamsConst.UNIT, "PC_IND");
        return Fetcher.fetchData("tepsr_sp410", params);
    }

    public StringBuilder getEarlyEducationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("educ_uoe_enra10", params);
    }

    public StringBuilder getEducationRatio() {
            MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
            params.put(ParamsConst.AGE, "Y15-64");
            FetcherUtils.addParams(params, ParamsConst.ISCED_11, EDUCATION_LEVELS);
            params.put(ParamsConst.SEX, "T");
            params.put(ParamsConst.UNIT, "PC");
            return Fetcher.fetchData("edat_lfs_9903", params);
    }

    public StringBuilder getExcludedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y18-24");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.TYPTRAI, "NO_FED_NFE");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "NEMP");
        return Fetcher.fetchData("edat_lfse_20", params);
    }

    public StringBuilder getNoKnownForeignLangRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y25-64");
        params.put(ParamsConst.N_LANG, "0");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("edat_aes_l22", params);
    }

    public StringBuilder getPupilsRatio2012() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.INDIC_ED, "ST1_1");
        return Fetcher.fetchData("educ_iste", params);
    }

    public StringBuilder getPupilsRatio2013() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.ISCED_11, "ED1-3");
        params.put(ParamsConst.UNIT, "RT");
        return Fetcher.fetchData("educ_uoe_perp04", params);
    }

    public StringBuilder getSchoolDropoutRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y18-24");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "POP");
        return Fetcher.fetchData("edat_lfse_14", params);
    }

    public StringBuilder getTrainingRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y25-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("trng_lfs_02", params);
    }
}
