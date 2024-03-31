package ro.webdata.qoli.aggr.stats.dimensions.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(AIR_POLLUTION_RATIO);
        add(NOISE_POLLUTION_RATIO);
        add(POLLUTION_RATIO);
        add(WATER_SUPPLY_RATIO);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, true);
        put(NOISE_POLLUTION_RATIO, true);
        put(POLLUTION_RATIO, true);
        put(WATER_SUPPLY_RATIO, false);
    }};
}
