package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.MaterialLivingDAO;

import java.util.Map;

public class MaterialLivingDAOImpl implements MaterialLivingDAO {
    //TODO: ??? Regional economic accounts > Income of households by NUTS 2 regions (nama_10r_2hhinc)
    
    public StringBuilder getPovertyRiskJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_peps11", params);
    }

    public StringBuilder getWorkIntensityJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("unit", "PC_Y_LT60");
        return DataFetcher.fetchData("ilc_lvhl21", params);
    }

    public StringBuilder getSevereMaterialDeprivationJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddd21", params);
    }

    public StringBuilder getPovertyRateJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_li41", params);
    }

    public StringBuilder getLackOfBathsRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdho05", params);
    }

    public StringBuilder getPublicWaterRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return DataFetcher.fetchData("env_wat_pop", params);
    }
}
