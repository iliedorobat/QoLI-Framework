package app.java.commons.dimensions.environment;

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

    public static final MultiValuedMap<String, String>
            NOISE_POLLUTION_RATIO_PARAMS = EnvironmentParams.getNoisePollutionParams(),
            PM2_5_POLLUTION_RATIO_PARAMS = EnvironmentParams.getAirPollutionParams(ParamsValues.AIRPOL.get("PM2_5")),
            PM10_POLLUTION_RATIO_PARAMS = EnvironmentParams.getAirPollutionParams(ParamsValues.AIRPOL.get("PM10")),
            POLLUTION_RATIO_PARAMS = EnvironmentParams.getPollutionParams(),
            WATER_SUPPLY_RATIO_PARAMS = EnvironmentParams.getWaterSupplyParams();
}
