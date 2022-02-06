package app.java.data.fetch.dao;

/**
 * Economic and physical security
 */
public interface SafetyDAO {
    /**
     * Share of the population who perceived there was crime, violence or vandalism in the area where they live<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw03<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getCrimeRatio();

    /**
     * Share of the population in arrears on mortgage or rent, utility bills or hire purchase<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdes05<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getNonPaymentRatio();

    /**
     * Recorded offences by offence category - police data (Assault, Robbery, Burglary, Theft)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: crim_off_cat<br/>
     * Years: 2008-2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getOffences();

    /**
     * Pensions expenditure level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: PPS per inhabitant (number)<br/>
     * Dataset: spr_exp_pens<br/>
     * Table: tps00103<br/>
     * Years: 2008-2019
     *
     * @return
     */
    StringBuilder getPensionPps();

    /**
     * Social protection expenditure level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: PPS per inhabitant (number)<br/>
     * Dataset: spr_exp_sum<br/>
     * Table: tps00098<br/>
     * Years: 2008-2019
     *
     * @return
     */
    StringBuilder getSocialProtectionPps();

    /**
     * Share of the population unable to face unexpected financial expenses<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdes04<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return StringBuilder
     */
    StringBuilder getUnexpectedRatio();
}
