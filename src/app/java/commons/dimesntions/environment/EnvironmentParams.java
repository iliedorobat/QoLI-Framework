package app.java.commons.dimesntions.environment;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class EnvironmentParams {
    public static MultiValuedMap<String, String> getAirPollutionParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        FetcherUtils.addParams(params, ParamsConst.AIR_POLLUTION, ParamsValues.AIRPOL);
        return params;
    }

    public static MultiValuedMap<String, String> getAirPollutionParams(String particlesDiameter) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AIR_POLLUTION, particlesDiameter);
        }};
    }

    public static MultiValuedMap<String, String> getNoisePollutionParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getPollutionParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getWaterSupplyParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.WAT_PROC, "POP_PWS");
            put(ParamsConst.UNIT, "PC");
        }};
    }
}
