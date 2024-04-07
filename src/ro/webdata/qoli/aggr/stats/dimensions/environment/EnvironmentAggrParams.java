package ro.webdata.qoli.aggr.stats.dimensions.environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.KG_PER_CAPITA;
import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PERCENT;

public class EnvironmentAggrParams {
    public static final String ENVIRONMENT = "environment";

    public static final String AIR_POLLUTION_RATIO = ENVIRONMENT + ":airPollutionRatio";
    public static final String AIR_POLLUTION_NH_3_RATIO = ENVIRONMENT + ":airPollutionNh3Ratio";
    public static final String AIR_POLLUTION_CH_4_RATIO = ENVIRONMENT + ":airPollutionCh4Ratio";
    public static final String AIR_POLLUTION_CO_RATIO = ENVIRONMENT + ":airPollutionCoRatio";
    public static final String AIR_POLLUTION_NMVOC_RATIO = ENVIRONMENT + ":airPollutionNmvocRatio";
    public static final String AIR_POLLUTION_NOX_RATIO = ENVIRONMENT + ":airPollutionNoxRatio";
    public static final String AIR_POLLUTION_PM_2_5_RATIO = ENVIRONMENT + ":airPollutionPm2_5Ratio";
    public static final String AIR_POLLUTION_PM_10_RATIO = ENVIRONMENT + ":airPollutionPm10Ratio";
    public static final String NOISE_POLLUTION_RATIO = ENVIRONMENT + ":noisePollutionRatio";
    public static final String POLLUTION_RATIO = ENVIRONMENT + ":pollutionRatio";
    public static final String WATER_SUPPLY_RATIO = ENVIRONMENT + ":waterSupplyRatio";

    public static final Map<String, String> ALLOWED_PARAM_LABELS = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, "Air Pollution Ratio");
        put(NOISE_POLLUTION_RATIO, "Noise Pollution Ratio");
        put(POLLUTION_RATIO, "Pollution Ratio");
        put(WATER_SUPPLY_RATIO, "Water Supply Ratio");
    }};

    public static final Map<String, String> ALLOWED_PARAM_UNITS = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, KG_PER_CAPITA);
        put(NOISE_POLLUTION_RATIO, PERCENT);
        put(POLLUTION_RATIO, PERCENT);
        put(WATER_SUPPLY_RATIO, PERCENT);
    }};

    public static final List<String> ALLOWED_PARAMS = List.copyOf(ALLOWED_PARAM_LABELS.keySet());

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, true);
        put(NOISE_POLLUTION_RATIO, true);
        put(POLLUTION_RATIO, true);
        put(WATER_SUPPLY_RATIO, false);
    }};
}
