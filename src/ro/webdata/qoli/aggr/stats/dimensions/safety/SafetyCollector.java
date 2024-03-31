package ro.webdata.qoli.aggr.stats.dimensions.safety;

import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;
import ro.webdata.qoli.aggr.stats.constants.Constants;

public class SafetyCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getCrimeRatio(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.CRIME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getOffences(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.OFFENCES_FILE_NAME);
        FileUtils.writeToJSONFile(getPensionPps(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.PENSION_PPS_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialProtectionPps(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.SOCIAL_PROTECTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnexpectedRatio(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.UNEXPECTED_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNonPaymentRatio(), SafetyPaths.SAFETY_RAW_PATH, SafetyPaths.NON_PAYMENT_RATIO_FILE_NAME);
    }

    /**
     * Share of the population who perceived there was crime, violence or vandalism in the area where they live<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddw03<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2020; 2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getCrimeRatio() {
        return Fetcher.fetchData("ilc_mddw03", SafetyParams.CRIME_RATIO_PARAMS);
    }

    /**
     * Share of the population in arrears on mortgage or rent, utility bills or hire purchase<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdes05<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNonPaymentRatio() {
        return Fetcher.fetchData("ilc_mdes05", SafetyParams.NON_PAYMENT_RATIO_PARAMS);
    }

    /**
     * Recorded offences by offence category (Assault, Robbery, Burglary, Theft)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: crim_off_cat<br/>
     * Years: 2008-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getOffences() {
        return Fetcher.fetchData("crim_off_cat", SafetyParams.OFFENCES_PARAMS, Constants.EU28_MEMBERS_EXTENDED);
    }

    /**
     * Pensions expenditure level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: PPS per inhabitant (number)<br/>
     * Dataset: spr_exp_pens<br/>
     * Years: 1990-2021
     *
     * @return
     */
    private static StringBuilder getPensionPps() {
        return Fetcher.fetchData("spr_exp_pens", SafetyParams.PENSION_PPS_PARAMS);
    }

    /**
     * Social protection expenditure level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: PPS per inhabitant (number)<br/>
     * Dataset: spr_exp_sum<br/>
     * Years: 1990-2021
     *
     * @return
     */
    private static StringBuilder getSocialProtectionPps() {
        return Fetcher.fetchData("spr_exp_sum", SafetyParams.SOCIAL_PROTECTION_PPS_PARAMS);
    }

    /**
     * Share of the population unable to face unexpected financial expenses<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdes04<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnexpectedRatio() {
        return Fetcher.fetchData("ilc_mdes04", SafetyParams.UNEXPECTED_RATIO_PARAMS);
    }
}
