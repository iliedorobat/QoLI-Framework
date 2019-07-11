package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.DataUtils;
import app.java.parser.http.dao.MaterialLivingDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class MaterialLivingDAOImpl implements MaterialLivingDAO {
    public StringBuilder getPurchasingRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("na_item", "B1GQ");
//        params.put("unit", "CP_PPS_HAB");
        params.put("unit", "PC_EU28_HAB_MEUR_CP");
        return DataFetcher.fetchData("nama_10r_2gdp", params);
    }

    public StringBuilder getMedianIncome() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("indic_il", "MED_E");
        params.put("sex", "T");
        params.put("unit", "PPS");
        return DataFetcher.fetchData("ilc_di03", params);
    }

    public StringBuilder getIncomeQuintileRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("age", "Y_GE65");
        params.put("age", "Y_LT65");
        params.put("indic_il", "S80_S20");
        params.put("sex", "T");
        return DataFetcher.fetchData("ilc_di11", params);
    }

    public StringBuilder getPovertyRiskRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("indic_il", "LI_R_MD60");
        return DataFetcher.fetchData("ilc_li03", params);
    }

    public StringBuilder getHighIncomeRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("indic_il", "LI_GE130MD");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_di20", params);
    }

    public StringBuilder getMaterialDeprivationRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddd11", params);
    }

    public StringBuilder getEndMeetInabilityRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("subjnmon", "EM_GD"); // households making ends meet with great difficulty
        params.put("subjnmon", "EM_D");  // households making ends meet with difficulty
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdes09", params);
    }

    public StringBuilder getUnderOccupiedRatio() {
        MultiValuedMap<String, String> params = DataUtils.getWorkOccupationParams();
        return DataFetcher.fetchData("ilc_lvho50a", params);
    }

    public StringBuilder getOverOccupiedRatio() {
        MultiValuedMap<String, String> params = DataUtils.getWorkOccupationParams();
        return DataFetcher.fetchData("ilc_lvho05a", params);
    }

    public StringBuilder getDwellingIssuesRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdho01", params);
    }

    public StringBuilder getLackOfBathsRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdho05", params);
    }

    public StringBuilder getWorkIntensityRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "Y_LT60");
        params.put("sex", "T");
        params.put("unit", "PC_Y_LT60");
        return DataFetcher.fetchData("ilc_lvhl11", params);
    }

    public StringBuilder getPublicWaterRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("wat_proc", "POP_PWS");
        params.put("unit", "PC");
        return DataFetcher.fetchData("env_wat_pop", params);
    }
}
