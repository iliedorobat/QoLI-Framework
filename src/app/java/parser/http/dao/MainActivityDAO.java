package app.java.parser.http.dao;

public interface MainActivityDAO {
    /**
     * Average annual population to calculate regional GDP data<br/><br/>
     *
     * Aggregation: NUTS 3 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: nama_10r_3popgdp<br/>
     * Years: 2000-2017
     *
     * @return
     */
    StringBuilder getAvgPopulationJSON();

    //TODO: emplyment under 20 - 64 (EVERYWHERE) !!!
    /**
     * Average number of usual weekly hours of work in main job (15 years or over)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfst_r_lfe2ehour<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getAvgWorkHoursJSON();

    /**
     * Economically active population (15 years or over)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfp2act<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getActivePopulationJSON();

    /**
     * Employment (15 years or over)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfe2emp<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getEmploymentJSON();

    /**
     * Employment rates (15 years or over)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfst_r_lfe2emprt<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getEmploymentRateJSON();

    /**
     * Long-term unemployment (12 months and more)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfu2ltu<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getLongTermUnemploymentJSON();

    /**
     * Unemployment rates (15 years or over)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfst_r_lfu3rt<br/>
     * Years: 1999-2018
     *
     * @return
     */
    StringBuilder getUnemploymentRateJSON();

    /**
     * Gross domestic product (GDP) per inhabitant at current market prices<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: euro per inhabitant (number)<br/>
     * Dataset: nama_10r_2gdp<br/>
     * Years: 2000-2017
     *
     * @deprecated replaced by PPS per inhabitant
     * @return
     */
    StringBuilder getGdpJSON();

    /**
     * Purchasing power standard (PPS) per inhabitant in percentage of the EU average<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: nama_10r_2gdp<br/>
     * Years: 2000-2017
     *
     * @return
     */
    StringBuilder getPurchasingRateJSON();
}
