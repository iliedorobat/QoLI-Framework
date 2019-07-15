package app.java.data.fetch.dao.impl;

import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.EnvironmentDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class EnvironmentDAOImpl implements EnvironmentDAO {
    public StringBuilder getPollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return Fetcher.fetchData("ilc_mddw02", params);
    }

    public StringBuilder getAirPollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("airpol", "PM10");
        return Fetcher.fetchData("sdg_11_50", params);
    }

    public StringBuilder getNoisePollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return Fetcher.fetchData("ilc_mddw01", params);
    }

    public StringBuilder getWaterSupplyRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return Fetcher.fetchData("env_wat_pop", params);
    }
}
