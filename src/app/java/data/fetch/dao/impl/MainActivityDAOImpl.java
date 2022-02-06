package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.MainActivityDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class MainActivityDAOImpl implements MainActivityDAO {
    private static final String HIGH_SATIS_LEVEL = "HIGH";
    private static final String JOB_SATIS = "JOBSAT";

    public static final String[] WORK_ACTIVITIES = {
            "nace_r1", // "lfsa_ewhuna" dataset
            "nace_r2"  // "lfsa_ewhun2" dataset
    };
    public static final String[] WORK_DATASET = {
            "lfsa_ewhuna", // 1983-2008
            "lfsa_ewhun2"  // 2008-2018
    };

    public StringBuilder getActivePopulationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.INDIC_EM, "ACT");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_POP");
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
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("lfsa_ergaed", params);
    }

    public StringBuilder getInactivePopulationRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_POP");
        return Fetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getInvoluntaryPartTimeRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("lfsa_eppgai", params);
    }

    public StringBuilder getJobSatisfaction() {
        return FetcherUtils.getSatisfactionRatio(HIGH_SATIS_LEVEL, JOB_SATIS);
    }

    public StringBuilder getLongTermUnemploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-74");
        params.put(ParamsConst.INDIC_EM, "LTU");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_ACT");
        return Fetcher.fetchData("une_ltu_a", params);
    }

    public StringBuilder getLowWageEarnersRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.AGE, "TOTAL");
//        params.put(ParamsConst.SIZECLAS, "GE10");
        return Fetcher.fetchData("earn_ses_pub1a", params);
    }

    public StringBuilder getOverQualifiedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.MGSTATUS, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("lfso_14loq", params);
    }

    public StringBuilder getResearchers() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.PROF_POS, "TOTAL");
        params.put(ParamsConst.SECT_PERF, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "FTE");
        return Fetcher.fetchData("rd_p_persocc", params);
    }

    public StringBuilder getTemporaryEmploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC_EMP");
        params.put(ParamsConst.WORK_TIME, "TEMP");
        return Fetcher.fetchData("lfsi_pt_a", params);
    }

    public StringBuilder getUnemploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-74");
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("lfsa_urgaed", params);
    }

    public StringBuilder getWorkingNightsRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y15-64");
        params.put(ParamsConst.FREQUENCY, "USU");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "PC");
        params.put(ParamsConst.WORKING_STATUS, "EMP");
        return Fetcher.fetchData("lfsa_ewpnig", params);
    }
}
