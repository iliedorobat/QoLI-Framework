package app.java.data.fetch.dao;

/**
 * Living conditions
 */
public interface MaterialLivingDAO {
    /**
     * Population living in a dwelling with a leaking roof, damp walls, floors
     * or foundation, or rot in window frames of floor<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho01<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getDwellingIssuesRatio();

    /**
     * Inability to make ends meet<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of the population living in households, by difficulty of making ends meet (%)<br/>
     * Dataset: ilc_mdes09<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getEndMeetInabilityRatio();

    /**
     * Percentage of the population rating their financial satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    StringBuilder getFinancialSatisfaction();

    /**
     * Proportion of the population having income of 130% of median income or more<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_di20<br/>
     * Years: 2003-2020
     *
     * @return
     */
    StringBuilder getHighIncomeRatio();

    /**
     * Inequality of income distribution (S80/S20 income quintile share ratio) by selected age group<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: income quintile share ratio (%)<br/>
     * Dataset: ilc_di11<br/>
     * Years: 1995-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getIncomeQuintileRatio();

    /**
     * Population having neither a bath, nor a shower, nor indoor flushing toilet in their household<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho05<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLackOfBathsRatio();

    /**
     * People living in households with very low work intensity<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of total population aged 0 to 59 years (%)<br/>
     * Dataset: ilc_lvhl11<br/>
     * Years: 2003-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => ilc_lvhl21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLowWorkIntensityRatio();

    /**
     * Severe material deprivation rate<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddd11<br/>
     * Years: 2003-2020<br/><br/>
     *
     * Comments: NUTS regions => ilc_mddd21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getMaterialDeprivationRatio();

    /**
     * Median income<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: Purchasing Power Standard (number)<br/>
     * Dataset: ilc_di03<br/>
     * Years: 1995-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => nama_10r_2hhinc
     *
     * @deprecated deprecated in favour of getPurchasingRatio
     * @return
     */
    StringBuilder getMedianIncome();

    /**
     * Overcrowding rate<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho05a<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getOverOccupiedRatio();

    /**
     * At-risk-of-poverty rate (cut-off point: 60% of median equivalised income after social transfers)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_li03<br/>
     * Years: 1995-2020<br/><br/>
     *
     * Comments: NUTS regions => ilc_li41<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getPovertyRiskRatio();

    /**
     * Population connected to public water supply<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: env_wat_pop<br/>
     * Years: 1990; 1995; 2000-2019
     *
     * @deprecated no dataset: IT; LV; SI; UK
     * @return
     */
    StringBuilder getPublicWaterRatio();

    /**
     * Purchasing Power Standard (PPS)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percent of the EU28 countries (%)<br/>
     * Dataset: nama_10_pc<br/>
     * Years: 1975-2021
     *
     * Comments: NUTS 2 regions => nama_10r_2gdp
     *
     * @return
     */
    StringBuilder getPpsRatio();

    /**
     * Share of people living in under-occupied dwellings<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho50a<br/>
     * Years: 2003-2020
     *
     * @return
     */
    StringBuilder getUnderOccupiedRatio();
}
