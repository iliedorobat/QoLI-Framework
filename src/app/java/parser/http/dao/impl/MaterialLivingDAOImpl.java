package app.java.parser.http.dao.impl;

import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.MaterialLivingDAO;

import java.util.Map;

public class MaterialLivingDAOImpl implements MaterialLivingDAO {
    public StringBuilder getPovertyRiskJSON(Map<String, String> params) {
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_peps11", params);
    }

    public StringBuilder getWorkIntensityJSON(Map<String, String> params) {
        params.put("unit", "PC_Y_LT60");
        return DataFetcher.fetchData("ilc_lvhl21", params);
    }

    public StringBuilder getSevereMaterialDeprivationJSON(Map<String, String> params) {
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddd21", params);
    }

    public StringBuilder getPovertyRateJSON(Map<String, String> params) {
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_li41", params);
    }
}
