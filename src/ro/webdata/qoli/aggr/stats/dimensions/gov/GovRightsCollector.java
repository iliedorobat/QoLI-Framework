package ro.webdata.qoli.aggr.stats.dimensions.gov;

import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;

import java.io.File;

/**
 * Factors which influence the life of the population from the perspective
 * of the governing, law making and equality of opportunity
 */
public class GovRightsCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getCitizenshipRatio(), GovRightsPaths.GOV_RIGHTS_RAW_PATH, GovRightsPaths.CITIZENSHIP_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getGenderEmpGap(), GovRightsPaths.GOV_RIGHTS_RAW_PATH, GovRightsPaths.GENDER_EMP_GAP_FILE_NAME);
        FileUtils.writeToJSONFile(getGenderPayGap(), GovRightsPaths.GOV_RIGHTS_RAW_PATH, GovRightsPaths.GENDER_PAY_GAP_FILE_NAME);
        FileUtils.writeToJSONFile(getPopulationTrust(), GovRightsPaths.GOV_RIGHTS_RAW_PATH, GovRightsPaths.POPULATION_TRUST_FILE_NAME);
        FileUtils.writeToJSONFile(getPopulationTrustOther(), GovRightsPaths.GOV_RIGHTS_RAW_PATH, GovRightsPaths.POPULATION_TRUST_OTHER_FILE_NAME);
        writeVoterTurnout();
    }

    /**
     * Voter turnout (the parliamentary elections)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: https://www.idea.int/data-tools/data/voter-turnout
     * Note: International IDEA
     */
    private static void writeVoterTurnout() {
        String FILE_URL = "https://www.idea.int/data-tools/export?type=region_only&themeId=293&world=all&loc=home";
        String FILE_PATH = GovRightsPaths.GOV_RIGHTS_RAW_PATH;
        String FILE_NAME = GovRightsPaths.VOTER_TURNOUT_FILE_NAME;
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
        return Fetcher.fetchData("ilc_scp19", GovRightsParams.CITIZENSHIP_RATIO_PARAMS);
    }

    /**
     * The difference between the employment rates of working-age men and women (defined here as those aged 20-64)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: tesem060<br/>
     * Years: 2005-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getGenderEmpGap() {
        return Fetcher.fetchData("tesem060", GovRightsParams.GENDER_EMP_GAP_PARAMS);
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
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getGenderPayGap() {
        return Fetcher.fetchData("earn_gr_gpgr2", GovRightsParams.GENDER_PAY_GAP_PARAMS);
    }

    /**
     * Average rating of population trust (16 years or over) in police, legal system and political system<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: rating (number between 0-10)<br/>
     * Dataset: ilc_pw03b<br/>
     * Years: 2013
     *
     * @return
     */
    private static StringBuilder getPopulationTrust() {
        return Fetcher.fetchData("ilc_pw03b", GovRightsParams.POPULATION_TRUST_PARAMS);
    }

    /**
     * Average rating of population trust (16 years or over) in other system<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: rating (number between 0-10)<br/>
     * Dataset: ilc_pw03<br/>
     * Years: 2013; 2018; 2021-2023
     *
     * @return
     */
    private static StringBuilder getPopulationTrustOther() {
        return Fetcher.fetchData("ilc_pw03", GovRightsParams.POPULATION_TRUST_OTHER_PARAMS);
    }
}
