package app.java.commons.dimensions.environment;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.environment.EnvironmentParams.*;
import static app.java.commons.dimensions.environment.EnvironmentPaths.*;

public class EnvironmentCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getNoisePollutionRatio(), ENVIRONMENT_RAW_PATH, NOISE_POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPollutionRatio(), ENVIRONMENT_RAW_PATH, POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWaterSupplyRatio(), ENVIRONMENT_RAW_PATH, WATER_SUPPLY_RATIO_FILE_NAME);
    }

    /**
     * Noise from neighbours or from the street<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw01<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNoisePollutionRatio() {
        return Fetcher.fetchData("ilc_mddw01", NOISE_POLLUTION_RATIO_PARAMS);
    }

    /**
     * Pollution, grime or other environmental problems (e.g.: smoke, dust,
     * unpleasant smells or polluted water)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw02<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPollutionRatio() {
        return Fetcher.fetchData("ilc_mddw02", POLLUTION_RATIO_PARAMS);
    }

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_wat_pop<br/>
     * Years: 1990; 1995; 2000-2021<br/><br/>
     *
     * Comment: NUTS 2 regions => env_watpop_r2<br/>
     * <b>Lack of data: IT; LV; SI; UK</b>
     * @return
     */
    private static StringBuilder getWaterSupplyRatio() {
        return Fetcher.fetchData("env_wat_pop", WATER_SUPPLY_RATIO_PARAMS);
    }
}
