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
     * Years: 2003-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getCrimeRatio();

    /**
     * Recorded offences by offence category - police data (Assault, Robbery, Burglary, Theft)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: crim_off_cat<br/>
     * Years: 2008-2016<br/><br/>
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
     * Years: 1990-2016
     *
     * @return
     */
    StringBuilder getPensionRatio();

    /**
     * Social protection expenditure level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: PPS per inhabitant (number)<br/>
     * Dataset: spr_exp_sum<br/>
     * Table: tps00098<br/>
     * Years: 1990-2016
     *
     * @return
     */
    StringBuilder getSocialProtectionRatio();

    /**
     * Share of the population unable to face unexpected financial expenses<br/><br/>
     *
     * Aggregation: country<br/>
     * Dataset: ilc_mdes04<br/>
     * Data type: percentage (%)<br/>
     * Years: 2003-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return StringBuilder
     */
    StringBuilder getUnexpectedRatio();

    /**
     * Share of the population in arrears on mortgage or rent, utility bills or hire purchase<br/><br/>
     *
     * Aggregation: country<br/>
     * Dataset: ilc_mdes05<br/>
     * Data type: percentage (%)<br/>
     * Years: 2003-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getUnpaidRatio();
}
