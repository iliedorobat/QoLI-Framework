package app.java.parser.http.dao;

/**
 * Measurements for calculation of the quality of the environment
 */
public interface EnvironmentDAO {
    /**
     * Pollution, grime or other environmental problems - EU-SILC survey<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw02<br/>
     * Years: 2003-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getPollutionRatio();

    /**
     * Exposure to air pollution (Particulates < 10µm)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: sdg_11_50<br/>
     * Years: 2000-2017<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getAirPollutionRatio();

    /**
     * Noise from neighbours or from the street - EU-SILC survey<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw01<br/>
     * Years: 2003-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getNoiseRatio();

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_wat_pop<br/>
     * Years: 2000-2013<br/><br/>
     *
     * Comment: NUTS 2 regions => env_watpop_r2
     *
     * @return
     */
    StringBuilder getWaterSupplyRatio();
}
