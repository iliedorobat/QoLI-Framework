package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.CommonDAO;
import app.java.parser.http.dao.EnvironmentDAO;

import java.util.Map;

public class EnvironmentDAOImpl implements EnvironmentDAO {
    public StringBuilder getNoiseRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddw01", params);
    }

    public StringBuilder getPollutionRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddw02", params);
    }

    //TODO: remove (in SocialActivity should be calculated overall life satisfaction)
    public StringBuilder getHighSatisfactionRatio() {
        CommonDAO commonDAO = new CommonDAOImpl();
        return commonDAO.getSatisfactionRatio("HIGH", "LIVENVSAT");
    }

    public StringBuilder getWaterSupplyRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("nace_r2", "TOTAL");
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return DataFetcher.fetchData("env_watpop_r2", params);
    }
}
