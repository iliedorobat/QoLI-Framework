package app.java.parser.http.dao;

public interface MaterialLivingDAO {
    /**
     * People at risk of poverty or social exclusion<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_peps11<br/>
     * Years: 2003-2018
     *
     * @return
     */
    StringBuilder getPovertyRiskJSON();

    /**
     * Description: People living in households with very low work intensity
     * by NUTS regions (population aged 0 to 59 years)<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%) of total population aged less than 60<br/>
     * Dataset: ilc_lvhl21<br/>
     * Years: 2003-2018
     *
     * @return
     */
    StringBuilder getWorkIntensityJSON();

    /**
     * Description: Severe material deprivation rate by NUTS regions<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddd21<br/>
     * Years: 2003-2018
     *
     * @return
     */
    StringBuilder getSevereMaterialDeprivationJSON();

    /**
     * Description: At-risk-of-poverty rate by NUTS regions<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_li41<br/>
     * Years: 2003-2018
     *
     * @return
     */
    StringBuilder getPovertyRateJSON();

    /**
     * Population having neither a bath, nor a shower, nor indoor flushing toilet in their household<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho05<br/>
     * Years: 2003-2018
     *
     * @return
     */
    StringBuilder getLackOfBathsRatioJSON();

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_wat_pop<br/>
     * Years: 2000-2015
     *
     * @return
     */
    StringBuilder getPublicWaterRatioJSON();
}
