package app.java.commons.dimensions.environment;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class EnvironmentParams {
    public static final MultiValuedMap<String, String> AIR_POLLUTION_PARAMS = getAirPollutionParams();

    public static final MultiValuedMap<String, String> PM2_5_POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AIR_POLLUTION, "PM2_5");
        put(ParamsConst.FREQ, "A");
    }};

    public static final MultiValuedMap<String, String> PM10_POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.AIR_POLLUTION, "PM10");
        put(ParamsConst.FREQ, "A");
    }};

    public static final MultiValuedMap<String, String> NOISE_POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.HHTYP, "TOTAL");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.HHTYP, "TOTAL");
        put(ParamsConst.INC_GRP, "TOTAL");
        put(ParamsConst.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WATER_SUPPLY_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsConst.FREQ, "A");
        put(ParamsConst.UNIT, "PC");
        put(ParamsConst.WAT_PROC, "POP_PWS");
    }};

    private static MultiValuedMap<String, String> getAirPollutionParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
        }};
        FetcherUtils.addParams(params, ParamsConst.AIR_POLLUTION, ParamsValues.AIRPOL);
        return params;
    }
}
