package app.java.aggr.commons.dimensions.environment;

import app.java.aggr.commons.constants.ParamsNames;
import app.java.aggr.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.aggr.commons.constants.ParamsValues.AIR_POL;

public class EnvironmentParams {
    private static final String[] AIR_POL_TYPES = {
            AIR_POL.get("ammonia"),
            AIR_POL.get("carbonMonoxide"),
            AIR_POL.get("methane"),
            AIR_POL.get("nonMethane"),
            AIR_POL.get("nitrogenOxides"),
            AIR_POL.get("PM2_5"),
            AIR_POL.get("PM10")
    };

    public static final MultiValuedMap<String, String> AIR_POLLUTION_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL_TYPES);

    public static final MultiValuedMap<String, String> AIR_POLLUTION_CH4_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("methane"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_CO_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("carbonMonoxide"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_NMVOC_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("nonMethane"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_NH3_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("ammonia"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_NOX_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("nitrogenOxides"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_PM_2_5_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("PM2_5"));
    public static final MultiValuedMap<String, String> AIR_POLLUTION_PM_10_RATIO_PARAMS = getAirPollutionRatioParams(AIR_POL.get("PM10"));

    public static final MultiValuedMap<String, String> NOISE_POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POLLUTION_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> WATER_SUPPLY_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.UNIT, "PC");
        put(ParamsNames.WAT_PROC, "POP_PWS");
    }};

    private static MultiValuedMap<String, String> getAirPollutionRatioParams(String pollutionType) {
        String[] pollutionTypes = new String[] { pollutionType };
        return getAirPollutionRatioParams(pollutionTypes);
    }

    private static MultiValuedMap<String, String> getAirPollutionRatioParams(String[] pollutionTypes) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.NACE_R2, "TOTAL");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.UNIT, "KG_HAB");    // Kilograms per capita
        }};
        FetcherUtils.addParams(params, ParamsNames.AIR_POL, pollutionTypes);
        return params;
    }
}
