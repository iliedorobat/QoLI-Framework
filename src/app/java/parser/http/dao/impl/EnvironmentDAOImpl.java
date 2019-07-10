package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.EnvironmentDAO;

import java.util.Map;

public class EnvironmentDAOImpl implements EnvironmentDAO {
    public StringBuilder getPollutionRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddw02", params);
    }

    public StringBuilder getAirPollutionRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("airpol", "PM10");
        return DataFetcher.fetchData("sdg_11_50", params);
    }

    public StringBuilder getNoiseRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddw01", params);
    }

    public StringBuilder getWaterSupplyRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return DataFetcher.fetchData("env_wat_pop", params);
    }
}
