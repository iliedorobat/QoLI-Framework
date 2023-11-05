package app.java.commons.dimensions.safety;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.constants.Constants.EU28_MEMBERS_EXTENDED;
import static app.java.commons.dimensions.safety.SafetyPaths.*;

public class SafetyCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getCrimeRatio(), FilePathConst.SAFETY_PATH, CRIME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getOffences(), FilePathConst.SAFETY_PATH, OFFENCES_FILE_NAME);
        FileUtils.writeToJSONFile(getPensionPps(), FilePathConst.SAFETY_PATH, PENSION_PPS_FILE_NAME);
        FileUtils.writeToJSONFile(getSocialProtectionPps(), FilePathConst.SAFETY_PATH, SOCIAL_PROTECTION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnexpectedRatio(), FilePathConst.SAFETY_PATH, UNEXPECTED_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNonPaymentRatio(), FilePathConst.SAFETY_PATH, NON_PAYMENT_RATIO_FILE_NAME);
    }

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
    private static StringBuilder getCrimeRatio() {
        return Fetcher.fetchData("ilc_mddw03", SafetyParams.getCrimeParams());
    }

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
    private static StringBuilder getNonPaymentRatio() {
        return Fetcher.fetchData("ilc_mdes05", SafetyParams.getNonPaymentParams());
    }

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
    private static StringBuilder getOffences() {
        return Fetcher.fetchData("crim_off_cat", SafetyParams.getOffencesParams(), EU28_MEMBERS_EXTENDED);
    }

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
    private static StringBuilder getPensionPps() {
        return Fetcher.fetchData("spr_exp_pens", SafetyParams.getPensionPpsParams());
    }

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
    private static StringBuilder getSocialProtectionPps() {
        return Fetcher.fetchData("spr_exp_sum", SafetyParams.getSocialProtectionPpsParams());
    }

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
    private static StringBuilder getUnexpectedRatio() {
        return Fetcher.fetchData("ilc_mdes04", SafetyParams.getUnexpectedParams());
    }
}
