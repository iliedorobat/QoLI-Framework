package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.EducationDAO;

import java.util.Arrays;
import java.util.Map;

public class EducationDAOImpl implements EducationDAO {
    public StringBuilder getPupilsRatio2012() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("indic_ed", "ST1_1");
        return DataFetcher.fetchData("educ_iste", params);
    }

    public StringBuilder getPupilsRatio2013() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("isced11", "ED1-3");
        params.put("unit", "RT");
        return DataFetcher.fetchData("educ_uoe_perp04", params);
    }

    public StringBuilder getStudentsRatioJSON2012() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("indic_ed", "R04_1");
        return DataFetcher.fetchData("educ_regind", params);
    }

    public StringBuilder getStudentsRatioJSON2013() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("unit", "RT");
        return DataFetcher.fetchData("educ_uoe_enrt05", params);
    }

    public StringBuilder getExcludedRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y18-24");
        params.put("sex", "T");
        params.put("typtrai", "NO_FED_NFE");
        params.put("unit", "PC");
        params.put("wstatus", "NEMP");
        return DataFetcher.fetchData("edat_lfse_22", params);
    }

    public StringBuilder getLeaversRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y18-24");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("edat_lfse_16", params);
    }

    public StringBuilder getEducationRatioJSON(String education) {
        String[] educationLevels = {"ED5-8", "ED3_4", "ED3-8", "ED0-2"};

        try {
            if (Arrays.asList(educationLevels).indexOf(education) == -1) {
                throw new Exception("The passed education level is not one of the accepted ones" +
                        "\n (" + Arrays.toString(educationLevels) + ")");
            }

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            params.put("age", "Y25-64");
            params.put("isced11", education);
            params.put("sex", "T");
            params.put("unit", "PC");
            return DataFetcher.fetchData("edat_lfse_04", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getTrainingRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y25-64");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("trng_lfs_02", params);
    }

    public StringBuilder getZeroForeignLangRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y25-64");
        params.put("n_lang", "0");
        params.put("unit", "PC");
        return DataFetcher.fetchData("edat_aes_l22", params);
    }
}
