package app.java.commons.dimensions.gov;

import app.java.commons.constants.Constants;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import java.io.File;

import static app.java.commons.dimensions.gov.GovRightsParams.*;
import static app.java.commons.dimensions.gov.GovRightsPaths.*;

/**
 * Factors which influence the life of the population from the perspective
 * of the governing, law making and equality of opportunity
 */
public class GovRightsCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getCitizenshipRatio(), GOV_RIGHTS_RAW_PATH, CITIZENSHIP_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getGenderEmpGap(), GOV_RIGHTS_RAW_PATH, GENDER_EMP_GAP_FILE_NAME);
        FileUtils.writeToJSONFile(getGenderPayGap(), GOV_RIGHTS_RAW_PATH, GENDER_PAY_GAP_FILE_NAME);
        FileUtils.writeToJSONFile(getPopulationTrust(), GOV_RIGHTS_RAW_PATH, POPULATION_TRUST_FILE_NAME);
        writeVoterTurnout();
    }

    // https://www.idea.int/data-tools/data/voter-turnout

    /**
     * Voter turnout<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset:
     * Note: International IDEA
     */
    private static void writeVoterTurnout() {
        String FILE_URL = "https://www.idea.int/sites/default/files/tmp/idea_export_40_620c368ac79e4.xls";
        String FILE_PATH = GOV_RIGHTS_RAW_PATH;
        String FILE_NAME = VOTER_TURNOUT_FILE_NAME;
        FileUtils.downloadExcelFile(FILE_URL, FILE_PATH, FILE_NAME, Constants.XLS_EXTENSION);
        FileUtils.convertXlsToCsv(FILE_PATH, FILE_NAME, Constants.XLS_EXTENSION);

        // Remove the XLS file after it has been converted to CSV
        File file = new File(FILE_PATH + File.separator + FILE_NAME + Constants.XLS_EXTENSION);
        file.delete();
    }

    /**
     * Active citizenship participation (people aged 16 years or over)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015; 2022
     *
     * @return
     */
    private static StringBuilder getCitizenshipRatio() {
        return Fetcher.fetchData("ilc_scp19", CITIZENSHIP_RATIO_PARAMS);
    }

    /**
     * The difference between the employment rates of working-age men and women (defined here as those aged 20-64)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: tesem060<br/>
     * Years: 2005-2023<br/><br/>
     *
     * @return
     */
    private static StringBuilder getGenderEmpGap() {
        return Fetcher.fetchData("tesem060", GENDER_EMP_GAP_PARAMS);
    }

    /**
     * Gender pay gap in unadjusted form by NACE Rev. 2 activity - structure of earnings survey methodology<br/>
     * Industry, construction and services (except public administration, defense, compulsory social security)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: earn_gr_gpgr2<br/>
     * Years: 2007-2022<br/><br/>
     *
     * @return
     */
    private static StringBuilder getGenderPayGap() {
        return Fetcher.fetchData("earn_gr_gpgr2", GENDER_PAY_GAP_PARAMS);
    }

    /**
     * Average rating of population trust (16 years or over) in police, legal system and political system<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: rating (number between 0-10)<br/>
     * Dataset: ilc_pw03<br/>
     * Years: 2013-2015
     *
     * @return
     */
    private static StringBuilder getPopulationTrust() {
        return Fetcher.fetchData("ilc_pw03", POPULATION_TRUST_PARAMS);
    }
}
