package app.java.commons.dimesntions.environment;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;

public class EnvironmentParams {
    private static final String[] PARTICLES_DIAMETERS = {
            "PM2_5",
            "PM10"
    };

    public static MultiValuedMap<String, String> getAirPollutionParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.AIR_POLLUTION, PARTICLES_DIAMETERS);
        return params;
    }

    public static MultiValuedMap<String, String> getNoisePollutionParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getPollutionParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getWaterSupplyParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.WAT_PROC, "POP_PWS");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }
}
