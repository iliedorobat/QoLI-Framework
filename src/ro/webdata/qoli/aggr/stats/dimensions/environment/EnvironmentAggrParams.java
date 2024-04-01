package ro.webdata.qoli.aggr.stats.dimensions.environment;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentAggrParams {
    public static final String ENVIRONMENT = "environment";

    public static final String AIR_POLLUTION_RATIO = "airPollutionRatio";
    public static final String AIR_POLLUTION_NH_3_RATIO = "airPollutionNh3Ratio";
    public static final String AIR_POLLUTION_CH_4_RATIO = "airPollutionCh4Ratio";
    public static final String AIR_POLLUTION_CO_RATIO = "airPollutionCoRatio";
    public static final String AIR_POLLUTION_NMVOC_RATIO = "airPollutionNmvocRatio";
    public static final String AIR_POLLUTION_NOX_RATIO = "airPollutionNoxRatio";
    public static final String AIR_POLLUTION_PM_2_5_RATIO = "airPollutionPm2_5Ratio";
    public static final String AIR_POLLUTION_PM_10_RATIO = "airPollutionPm10Ratio";
    public static final String NOISE_POLLUTION_RATIO = "noisePollutionRatio";
    public static final String POLLUTION_RATIO = "pollutionRatio";
    public static final String WATER_SUPPLY_RATIO = "waterSupplyRatio";

    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, "Air Pollution Ratio");
        put(NOISE_POLLUTION_RATIO, "Noise Pollution Ratio");
        put(POLLUTION_RATIO, "Pollution Ratio");
        put(WATER_SUPPLY_RATIO, "Water Supply Ratio");
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, true);
        put(NOISE_POLLUTION_RATIO, true);
        put(POLLUTION_RATIO, true);
        put(WATER_SUPPLY_RATIO, false);
    }};
}
