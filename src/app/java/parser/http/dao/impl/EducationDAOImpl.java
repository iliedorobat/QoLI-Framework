package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.EducationDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class EducationDAOImpl implements EducationDAO {
    public static final String[] EDUCATION_LEVELS = {
            "ED5-8", // Tertiary education (levels 5-8)
            "ED3_4", // Upper secondary and post-secondary non-tertiary education (levels 3 and 4)
            "ED0-2"  // Less than primary, primary and lower secondary education (levels 0-2)
    };

    public StringBuilder getEducationRatio(String[] eduLevel) {
        try {
            Errors.throwNewError(EDUCATION_LEVELS, eduLevel, "education levels");

            MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
            ParserUtils.addParams(params, eduLevel, "isced11");
            params.put("age", "Y15-64");
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("edat_lfs_9903", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getEarlyEducationRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("educ_uoe_enra10", params);
    }

    public StringBuilder getLeaversRatioJSON() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y18-24");
        params.put("sex", "T");
        params.put("unit", "PC");
        params.put("wstatus", "POP");
        return DataFetcher.fetchData("edat_lfse_14", params);
    }

    public StringBuilder getExcludedRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y18-24");
        params.put("sex", "T");
        params.put("typtrai", "NO_FED_NFE");
        params.put("unit", "PC");
        params.put("wstatus", "NEMP");
        return DataFetcher.fetchData("edat_lfse_20", params);
    }

    public StringBuilder getTrainingRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y25-64");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("trng_lfs_02", params);
    }

    public StringBuilder getDigitalSkillsRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("indic_is", "I_DSK_BAB");
        params.put("ind_type", "IND_TOTAL");
        params.put("unit", "PC_IND");
        return DataFetcher.fetchData("tepsr_sp410", params);
    }

    public StringBuilder getZeroForeignLangRatioJSON() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y25-64");
        params.put("n_lang", "0");
        params.put("unit", "PC");
        return DataFetcher.fetchData("edat_aes_l22", params);
    }

    public StringBuilder getPupilsRatio2012() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("indic_ed", "ST1_1");
        return DataFetcher.fetchData("educ_iste", params);
    }

    public StringBuilder getPupilsRatio2013() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("isced11", "ED1-3");
        params.put("unit", "RT");
        return DataFetcher.fetchData("educ_uoe_perp04", params);
    }
}
