package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.MaterialLivingDAO;

import java.util.Map;

public class MaterialLivingDAOImpl implements MaterialLivingDAO {
    public StringBuilder getPurchasingRate() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("na_item", "B1GQ");
        params.put("unit", "CP_PPS_HAB");
        return DataFetcher.fetchData("nama_10r_2gdp", params);
    }

    public StringBuilder getMedianIncome() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("indic_il", "MED_E");
        params.put("sex", "T");
        params.put("unit", "PPS");
        return DataFetcher.fetchData("ilc_di03", params);
    }

    public StringBuilder getIncomeQuintileRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("age", "Y_GE65");
        params.put("age", "Y_LT65");
        params.put("indic_il", "S80_S20");
        params.put("sex", "T");
        return DataFetcher.fetchData("ilc_di11", params);
    }

    public StringBuilder getPovertyRiskRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("indic_il", "LI_R_MD60");
        return DataFetcher.fetchData("ilc_li03", params);
    }

    public StringBuilder getHighIncomeRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("indic_il", "LI_GE130MD");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_di20", params);
    }

    public StringBuilder getMaterialDeprivationRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddd11", params);
    }

    public StringBuilder getEndMeetInabilityRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("subjnmon", "EM_GD"); // households making ends meet with great difficulty
        params.put("subjnmon", "EM_D");  // households making ends meet with difficulty
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdes09", params);
    }

    /**
     * Share of people living in under-occupied / overcrowding dwellings
     *
     * @param dataset The under-occupied / overcrowding dataset:<br/>
     *                - ilc_lvho50a: under-occupied;<br/>
     *                - ilc_lvho05a: overcrowding.
     * @return
     */
    private StringBuilder getOccupiedRatio(String dataset) {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData(dataset, params);
    }

    public StringBuilder getUnderOccupiedRatio() {
        return getOccupiedRatio("ilc_lvho50a");
    }

    public StringBuilder getOverOccupiedRatio() {
        return getOccupiedRatio("ilc_lvho05a");
    }

    public StringBuilder getDwellingIssuesRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdho01", params);
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

    public StringBuilder getWorkIntensityRatio() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "Y_LT60");
        params.put("sex", "T");
        params.put("unit", "PC_Y_LT60");
        return DataFetcher.fetchData("ilc_lvhl11", params);
    }

    public StringBuilder getPublicWaterRatioJSON() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return DataFetcher.fetchData("env_wat_pop", params);
    }
}
