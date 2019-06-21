package app.java.parser.http.dao;

import java.util.Map;

public interface MainActivityDAO {
    /**
     * Average annual population to calculate regional GDP data<br/><br/>
     *
     * Aggregation: NUTS 3 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: nama_10r_3popgdp<br/>
     * Years: 2000-2017
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getAvgPopulationJSON(Map<String, String> params);

    /**
     * Average number of usual weekly hours of work in main job<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfst_r_lfe2ehour<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getAvgWorkHoursJSON(Map<String, String> params);

    /**
     * Economically active population<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfp2act<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getActivePopulationJSON(Map<String, String> params);

    /**
     * Employment<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfe2emp<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getEmplymentJSON(Map<String, String> params);

    /**
     * Employment rates<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfst_r_lfe2emprt<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getEmploymentRateJSON(Map<String, String> params);

    /**
     * Long-term unemployment (12 months and more)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: thousand persons (number)<br/>
     * Dataset: lfst_r_lfu2ltu<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getLongTermUnmploymentJSON(Map<String, String> params);

    /**
     * Unemployment rates<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfst_r_lfu3rt<br/>
     * Years: 1999-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getUnmploymentRateJSON(Map<String, String> params);

    /**
     * Gross domestic product (GDP) at current market prices<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: million euro (number)<br/>
     * Dataset: nama_10r_2gdp<br/>
     * Years: 2000-2017
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getGDPJSON(Map<String, String> params);
}
