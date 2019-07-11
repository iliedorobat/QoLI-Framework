package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.DataUtils;
import app.java.parser.http.dao.GovRightsDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class GovRightsDAOImpl implements GovRightsDAO {
    public StringBuilder getActiveCitizenship() {
        String[] activities = {"AC43A"};
        return DataUtils.getActivePeopleRatio(activities);
    }

    public StringBuilder getPopulationTrustRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("indic_wb", "LEGTST"); // legal system
        params.put("indic_wb", "PLCTST"); // police
        params.put("indic_wb", "PLTTST"); // political system
        params.put("isced11", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "RTG");
        return DataFetcher.fetchData("ilc_pw03", params);
    }

    public StringBuilder getEmploymentGap() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y20-64");
        params.put("indic_em", "EMP_LFS");
        params.put("sex", "F");
        params.put("sex", "M");
        params.put("unit", "PC_POP");
        return DataFetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getGenderPayGap() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("nace_r2", "B-S_X_O");
        params.put("unit", "PC");
        return DataFetcher.fetchData("earn_gr_gpgr2", params);
    }
}
