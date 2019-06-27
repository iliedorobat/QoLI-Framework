package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.CommonDAO;
import app.java.parser.http.dao.GovRightsDAO;

import java.util.Map;

public class GovRightsDAOImpl implements GovRightsDAO {
    private static CommonDAO commonDAO = new CommonDAOImpl();

    public StringBuilder getActiveCitizenship() {
        String[] activities = {"AC43A"};
        return commonDAO.getActivePeopleRatio(activities);
    }

    public StringBuilder getPopulationTrustRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
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
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y20-64");
        params.put("indic_em", "EMP_LFS");
        params.put("sex", "F");
        params.put("sex", "M");
        params.put("unit", "PC_POP");
        return DataFetcher.fetchData("lfsi_emp_a", params);
    }

    public StringBuilder getGenderPayGap() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("nace_r2", "B-S_X_O");
        params.put("unit", "PC");
        return DataFetcher.fetchData("earn_gr_gpgr2", params);
    }
}
