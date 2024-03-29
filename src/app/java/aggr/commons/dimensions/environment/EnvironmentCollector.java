package app.java.aggr.commons.dimensions.environment;

import app.java.aggr.commons.utils.FileUtils;
import app.java.aggr.data.fetch.Fetcher;

import static app.java.aggr.commons.dimensions.environment.EnvironmentParams.*;
import static app.java.aggr.commons.dimensions.environment.EnvironmentPaths.*;

public class EnvironmentCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getAirPollutionRatio(), ENVIRONMENT_RAW_PATH, AIR_POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNoisePollutionRatio(), ENVIRONMENT_RAW_PATH, NOISE_POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPollutionRatio(), ENVIRONMENT_RAW_PATH, POLLUTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWaterSupplyRatio(), ENVIRONMENT_RAW_PATH, WATER_SUPPLY_RATIO_FILE_NAME);
    }

    /**
     * Exposure to air pollution:<br/>
     *  - Particulates < 2.5 µm<br/>
     *  - Particulates < 10 µm)<br/>
     *  - Acidifying gas emissions (NH3, NOX)<br/>
     *  - Ozone precursors (CH4, CO, NMVOC, NOX)<br/><br/>
     *
     *  https://ec.europa.eu/eurostat/statistics-explained/index.php?title=Air_pollution_statistics_-_air_emissions_accounts
     *  <br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: Kilograms per capita<br/>
     * Dataset: env_ac_ainah_r2<br/>
     * Years: 1995-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getAirPollutionRatio() {
        return Fetcher.fetchData("env_ac_ainah_r2", AIR_POLLUTION_RATIO_PARAMS);
    }

    /**
     * Noise from neighbours or from the street<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw01<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2020; 2023<br/><br/>
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
     * Years: 2003-2020; 2023<br/><br/>
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
