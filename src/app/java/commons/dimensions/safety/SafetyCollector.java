package app.java.commons.dimensions.safety;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.constants.Constants.EU28_MEMBERS_EXTENDED;
import static app.java.commons.dimensions.safety.SafetyParams.*;
import static app.java.commons.dimensions.safety.SafetyPaths.*;

public class SafetyCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getCrimeRatio(), SAFETY_RAW_PATH, CRIME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getOffences(), SAFETY_RAW_PATH, OFFENCES_FILE_NAME);
        FileUtils.writeToJSONFile(getPensionPps(), SAFETY_RAW_PATH, PENSION_PPS_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialProtectionPps(), SAFETY_RAW_PATH, SOCIAL_PROTECTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnexpectedRatio(), SAFETY_RAW_PATH, UNEXPECTED_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNonPaymentRatio(), SAFETY_RAW_PATH, NON_PAYMENT_RATIO_FILE_NAME);
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
        return Fetcher.fetchData("ilc_mddw03", CRIME_RATIO_PARAMS);
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
        return Fetcher.fetchData("ilc_mdes05", NON_PAYMENT_RATIO_PARAMS);
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
        return Fetcher.fetchData("crim_off_cat", OFFENCES_PARAMS, EU28_MEMBERS_EXTENDED);
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
        return Fetcher.fetchData("spr_exp_pens", PENSION_PPS_PARAMS);
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
        return Fetcher.fetchData("spr_exp_sum", SOCIAL_PROTECTION_PPS_PARAMS);
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
        return Fetcher.fetchData("ilc_mdes04", UNEXPECTED_RATIO_PARAMS);
    }
}
