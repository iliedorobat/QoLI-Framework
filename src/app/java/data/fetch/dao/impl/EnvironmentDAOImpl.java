package app.java.data.fetch.dao.impl;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.EnvironmentDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class EnvironmentDAOImpl implements EnvironmentDAO {
    private static final String[] PARTICLES_DIAMETERS = {
            "PM2_5",
            "PM10"
    };

    public StringBuilder getAirPollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.AIR_POLLUTION, PARTICLES_DIAMETERS);
        return Fetcher.fetchData("sdg_11_50", params);
    }

    public StringBuilder getNoisePollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mddw01", params);
    }

    public StringBuilder getPollutionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mddw02", params);
    }

    public StringBuilder getWaterSupplyRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.WAT_PROC, "POP_PWS");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("env_wat_pop", params);
    }
}
