package app.java.commons.dimensions.environment;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.environment.EnvironmentPaths.*;

public class EnvironmentCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAirPollutionRatio(), FilePathConst.ENVIRONMENT_PATH, AIR_POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNoisePollutionRatio(), FilePathConst.ENVIRONMENT_PATH, NOISE_POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPollutionRatio(), FilePathConst.ENVIRONMENT_PATH, POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWaterSupplyRatio(), FilePathConst.ENVIRONMENT_PATH, WATER_SUPPLY_RATIO_FILE_NAME);
    }

    /**
     * Exposure to air pollution (Particulates < 2.5 µm & Particulates < 10 µm)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: sdg_11_50<br/>
     * Years: 2000-2019<br/><br/>
     *
     * <b>Lack of data: MT</b><br/><br/>
     * <b>GREATER IS WORSE!</b>
     * @return
     */
    private static StringBuilder getAirPollutionRatio() {
        return Fetcher.fetchData("sdg_11_50", EnvironmentParams.getAirPollutionParams());
    }

    /**
     * Noise from neighbours or from the street - EU-SILC survey<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw01<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNoisePollutionRatio() {
        return Fetcher.fetchData("ilc_mddw01", EnvironmentParams.getNoisePollutionParams());
    }

    /**
     * Pollution, grime or other environmental problems (e.g.: smoke, dust,
     * unpleasant smells or polluted water) - EU-SILC survey<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw02<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPollutionRatio() {
        return Fetcher.fetchData("ilc_mddw02", EnvironmentParams.getPollutionParams());
    }

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_wat_pop<br/>
     * Years: 1990; 1995; 2000-2019<br/><br/>
     *
     * Comment: NUTS 2 regions => env_watpop_r2<br/>
     * <b>Lack of data: IT; LV; SI; UK</b>
     * @return
     */
    private static StringBuilder getWaterSupplyRatio() {
        return Fetcher.fetchData("env_wat_pop", EnvironmentParams.getWaterSupplyParams());
    }
}
