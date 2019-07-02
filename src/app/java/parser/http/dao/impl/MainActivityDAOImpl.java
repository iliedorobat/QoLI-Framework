package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.MainActivityDAO;

import java.util.Map;

public class MainActivityDAOImpl implements MainActivityDAO {
    public StringBuilder getEmploymentRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("lfsa_ergaed", params);
    }

    public StringBuilder getTemporaryEmploymentRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("sex", "T");
        params.put("unit", "PC_EMP");
        params.put("worktime", "TEMP");
        return DataFetcher.fetchData("lfsi_pt_a", params);
    }

    public StringBuilder getInvoluntaryPartTimeRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("sex", "T");
        params.put("unit", "PC_EMP");
        return DataFetcher.fetchData("lfsa_eppgai", params);
    }

    public StringBuilder getOverQualifiedRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("isced11", "TOTAL");
        params.put("mgstatus", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("lfso_14loq", params);
    }

    public StringBuilder getAvgWorkHours(String activity) {
        String[] ACTIVITIES = {"nace_r1", "nace_r2"};

        try {
            Errors.throwNewError(ACTIVITIES, activity, "classification of economic activities");

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            params.put(activity, "TOTAL");
            params.put("age", "Y15-64");
            params.put("sex", "T");
            params.put("unit", "HR");
            params.put("worktime", "FT");
            params.put("wstatus", "EMP");
            return DataFetcher.fetchData("lfst_r_lfe2ehour", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getNightsRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("frequenc", "USU");
        params.put("sex", "T");
        params.put("unit", "PC");
        params.put("wstatus", "EMP");
        return DataFetcher.fetchData("lfsa_ewpnig", params);
    }

    public StringBuilder getUnemploymentRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-74");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("lfsa_urgaed", params);
    }

    public StringBuilder getLongTermUnemploymentRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-74");
        params.put("indic_em", "LTU");
        params.put("sex", "T");
        params.put("unit", "PC_ACT");
        return DataFetcher.fetchData("une_ltu_a", params);
    }

    public StringBuilder getActivePopulation() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y15-64");
        params.put("indic_em", "ACT");
        params.put("sex", "T");
        params.put("unit", "PC_POP");
        return DataFetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getResearchers() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("prof_pos", "TOTAL");
        params.put("sectperf", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "FTE");
        return DataFetcher.fetchData("rd_p_persocc", params);
    }
}
