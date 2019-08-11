package app.java.data.fetch.dao.impl;

import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.GovRightsDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class GovRightsDAOImpl implements GovRightsDAO {
    public StringBuilder getActiveCitizenship() {
        String[] activities = {"AC43A"};
        return FetcherUtils.getActivePeopleRatio(activities);
    }

    public StringBuilder getEmploymentGap() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y20-64");
        params.put("indic_em", "EMP_LFS");
        params.put("sex", "F");
        params.put("sex", "M");
        params.put("unit", "PC_POP");
        return Fetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getGenderPayGap() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("nace_r2", "B-S_X_O");
        params.put("unit", "PC");
        return Fetcher.fetchData("earn_gr_gpgr2", params);
    }

    public StringBuilder getPopulationTrustRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("indic_wb", "LEGTST"); // legal system
        params.put("indic_wb", "PLCTST"); // police
        params.put("indic_wb", "PLTTST"); // political system
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "RTG");
        return Fetcher.fetchData("ilc_pw03", params);
    }
}
