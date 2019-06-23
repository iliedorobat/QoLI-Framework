package app.java.parser.http.dao;

public interface EnvironmentDAO {
//    /**
//     * Exposure to air pollution by particulate matter (source: EEA) (sdg_11_50)
//     *
//     * @return
//     */
//    //TODO: download CSV table:
//    StringBuilder getAirPollutionRatio();

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
     * Percentage of the population rating their environment satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @return
     */
    StringBuilder getHighSatisfactionRatio();

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: NUTS 2 regions <br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_watpop_r2<br/>
     * Years: 2000-2013
     *
     * @return
     */
    StringBuilder getWaterSupplyRatio();
}
