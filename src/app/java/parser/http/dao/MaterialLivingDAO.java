package app.java.parser.http.dao;

import java.util.Map;

public interface MaterialLivingDAO {
    //TODO: ??? Regional economic accounts > Income of households by NUTS 2 regions (nama_10r_2hhinc)

    /**
     * People at risk of poverty or social exclusion<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_peps11<br/>
     * Years: 2003-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getPovertyRiskJSON(Map<String, String> params);

    /**
     * Description: People living in households with very low work intensity
     * by NUTS regions (population aged 0 to 59 years)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%) of total population aged less than 60<br/>
     * Dataset: ilc_lvhl21<br/>
     * Years: 2003-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getWorkIntensityJSON(Map<String, String> params);

    /**
     * Description: Severe material deprivation rate by NUTS regions<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddd21<br/>
     * Years: 2003-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getSevereMaterialDeprivationJSON(Map<String, String> params);

    /**
     * Description: At-risk-of-poverty rate by NUTS regions<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_li41<br/>
     * Years: 2003-2018
     *
     * @param params The list with parameters key-value pairs
     * @return
     */
    StringBuilder getPovertyRateJSON(Map<String, String> params);
}
