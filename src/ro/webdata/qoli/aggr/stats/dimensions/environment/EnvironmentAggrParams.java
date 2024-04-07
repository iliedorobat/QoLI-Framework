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

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, "Air Pollution Ratio");
        put(NOISE_POLLUTION_RATIO, "Noise Pollution Ratio");
        put(POLLUTION_RATIO, "Pollution Ratio");
        put(WATER_SUPPLY_RATIO, "Water Supply Ratio");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, KG_PER_CAPITA);
        put(NOISE_POLLUTION_RATIO, PERCENT);
        put(POLLUTION_RATIO, PERCENT);
        put(WATER_SUPPLY_RATIO, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
        put(AIR_POLLUTION_RATIO, true);
        put(NOISE_POLLUTION_RATIO, true);
        put(POLLUTION_RATIO, true);
        put(WATER_SUPPLY_RATIO, false);
    }};

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, "Air Pollution (NH3) Ratio");
        put(AIR_POLLUTION_CH_4_RATIO, "Air Pollution (CH4) Ratio");
        put(AIR_POLLUTION_CO_RATIO, "Air Pollution (CO) Ratio");
        put(AIR_POLLUTION_NMVOC_RATIO, "Air Pollution (NMVOC) Ratio");
        put(AIR_POLLUTION_NOX_RATIO, "Air Pollution (NOX) Ratio");
        put(AIR_POLLUTION_PM_2_5_RATIO, "Air Pollution (PM2.5) Ratio");
        put(AIR_POLLUTION_PM_10_RATIO, "Air Pollution (PM10) Ratio");
        put(NOISE_POLLUTION_RATIO, "Noise Pollution Ratio");
        put(POLLUTION_RATIO, "Pollution Ratio");
        put(WATER_SUPPLY_RATIO, "Water Supply Ratio");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_CH_4_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_CO_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_NMVOC_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_NOX_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_PM_2_5_RATIO, KG_PER_CAPITA);
        put(AIR_POLLUTION_PM_10_RATIO, KG_PER_CAPITA);
        put(NOISE_POLLUTION_RATIO, PERCENT);
        put(POLLUTION_RATIO, PERCENT);
        put(WATER_SUPPLY_RATIO, PERCENT);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATE = new HashMap<>() {{
        put(AIR_POLLUTION_NH_3_RATIO, true);
        put(AIR_POLLUTION_CH_4_RATIO, true);
        put(AIR_POLLUTION_CO_RATIO, true);
        put(AIR_POLLUTION_NMVOC_RATIO, true);
        put(AIR_POLLUTION_NOX_RATIO, true);
        put(AIR_POLLUTION_PM_2_5_RATIO, true);
        put(AIR_POLLUTION_PM_10_RATIO, true);
        put(NOISE_POLLUTION_RATIO, true);
        put(POLLUTION_RATIO, true);
        put(WATER_SUPPLY_RATIO, false);
    }};
}
