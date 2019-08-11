package app.java.data.fetch.dao.impl;

import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.MainActivityDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class MainActivityDAOImpl implements MainActivityDAO {
    public static final String[] WORK_ACTIVITIES = {
            "nace_r1", // "lfsa_ewhuna" dataset
            "nace_r2"  // "lfsa_ewhun2" dataset
    };
    public static final String[] WORK_DATASET = {
            "lfsa_ewhuna", // 1983-2008
            "lfsa_ewhun2"  // 2008-2018
    };

    public StringBuilder getActivePopulation() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("indic_em", "ACT");
        params.put("sex", "T");
        params.put("unit", "PC_POP");
        return Fetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getAvgWorkHours2007() {
        return FetcherUtils.getAvgWorkHours(WORK_DATASET[0]);
    }

    public StringBuilder getAvgWorkHours2008() {
        return FetcherUtils.getAvgWorkHours(WORK_DATASET[1]);
    }

    public StringBuilder getEmploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("lfsa_ergaed", params);
    }

    public StringBuilder getInvoluntaryPartTimeRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("lfsa_eppgai", params);
    }

    public StringBuilder getLongTermUnemploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-74");
        params.put("indic_em", "LTU");
        params.put("sex", "T");
        params.put("unit", "PC_ACT");
        return Fetcher.fetchData("une_ltu_a", params);
    }

    public StringBuilder getNightsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("frequenc", "USU");
        params.put("sex", "T");
        params.put("unit", "PC");
        params.put("wstatus", "EMP");
        return Fetcher.fetchData("lfsa_ewpnig", params);
    }

    public StringBuilder getOverQualifiedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("isced11", "TOTAL");
        params.put("mgstatus", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("lfso_14loq", params);
    }

    public StringBuilder getResearchers() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("prof_pos", "TOTAL");
        params.put("sectperf", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "FTE");
        return Fetcher.fetchData("rd_p_persocc", params);
    }

    public StringBuilder getTemporaryEmploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-64");
        params.put("sex", "T");
        params.put("unit", "PC_EMP");
        params.put("worktime", "TEMP");
        return Fetcher.fetchData("lfsi_pt_a", params);
    }

    public StringBuilder getUnemploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y15-74");
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return Fetcher.fetchData("lfsa_urgaed", params);
    }
}
