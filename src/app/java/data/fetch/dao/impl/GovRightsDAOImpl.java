package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.GovRightsDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class GovRightsDAOImpl implements GovRightsDAO {
    public StringBuilder getActiveCitizenship() {
        String[] activities = {"AC43A"};
        return FetcherUtils.getActivePeopleRatio(activities);
    }

    public StringBuilder getEmploymentRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y20-64");
        params.put(ParamsConst.INDIC_EM, "EMP_LFS");
        params.put(ParamsConst.SEX, "F");
        params.put(ParamsConst.SEX, "M");
        params.put(ParamsConst.UNIT, "PC_POP");
        return Fetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getGenderPayGap() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.NACE_R2, "B-S_X_O");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("earn_gr_gpgr2", params);
    }

    public StringBuilder getPopulationTrust() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        params.put(ParamsConst.INDIC_WB, "LEGTST"); // legal system
        params.put(ParamsConst.INDIC_WB, "PLCTST"); // police
        params.put(ParamsConst.INDIC_WB, "PLTTST"); // political system
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "RTG");
        return Fetcher.fetchData("ilc_pw03", params);
    }
}
