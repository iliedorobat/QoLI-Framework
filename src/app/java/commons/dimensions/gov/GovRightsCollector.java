package app.java.commons.dimensions.gov;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;
import org.apache.commons.collections4.MultiValuedMap;

import java.io.File;

/**
 * Factors which influence the life of the population from the perspective
 * of the governing, law making and equality of opportunity
 */
public class GovRightsCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getCitizenshipRatio(), FilePathConst.GOV_RIGHTS_PATH, FileNameConst.CITIZENSHIP_RATIO);
        FileUtils.writeToJSONFile(getEmploymentRatio(), FilePathConst.GOV_RIGHTS_PATH, FileNameConst.EMPLOYMENT_RATIO_BY_SEX);
        FileUtils.writeToJSONFile(getGenderPayGap(), FilePathConst.GOV_RIGHTS_PATH, FileNameConst.GENDER_PAY_GAP);
        FileUtils.writeToJSONFile(getPopulationTrust(), FilePathConst.GOV_RIGHTS_PATH, FileNameConst.POPULATION_TRUST);
        writeVoterTurnout();
    }

    // https://www.idea.int/data-tools/data/voter-turnout
    private static void writeVoterTurnout() {
        String FILE_URL = "https://www.idea.int/sites/default/files/tmp/idea_export_40_620c368ac79e4.xls";
        String FILE_PATH = FilePathConst.GOV_RIGHTS_PATH;
        String FILE_NAME = FileNameConst.VOTER_TURNOUT;
        FileUtils.downloadExcelFile(FILE_URL, FILE_PATH, FILE_NAME, Constants.XLS_EXTENSION);
        FileUtils.convertXlsToCsv(FILE_PATH, FILE_NAME, Constants.XLS_EXTENSION);

        // Remove the XLS file after it has been converted to CSV
        File file = new File(FILE_PATH + FILE_NAME + Constants.XLS_EXTENSION);
        file.delete();
    }

    /**
     * Active citizenship participation<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015
     *
     * @return
     */
    private static StringBuilder getCitizenshipRatio() {
        MultiValuedMap<String, String> params = GovRightsParams.getCitizenshipParams();
        return Fetcher.fetchData("ilc_scp19", params);
    }

    /**
     * Employment (from 20 to 64 years) by sex - annual data<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfsi_emp_a<br/>
     * Years: 1992-2020
     *
     * @return
     */
    private static StringBuilder getEmploymentRatio() {
        return Fetcher.fetchData("lfsi_emp_a", GovRightsParams.getEmploymentParams());
    }

    /**
     * Gender pay gap in unadjusted form by NACE Rev. 2 activity - structure of earnings survey methodology<br/>
     * Industry, construction and services (except public administration, defense, compulsory social security)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: earn_gr_gpgr2<br/>
     * Years: 2007-2019<br/><br/>
     *
     * @return
     */
    private static StringBuilder getGenderPayGap() {
        return Fetcher.fetchData("earn_gr_gpgr2", GovRightsParams.getGenderPayGapParams());
    }

    /**
     * Average rating of population trust (16 years or over) in police, legal system and political system<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: rating (number 0-10)<br/>
     * Dataset: ilc_pw03<br/>
     * Years: 2013
     *
     * @return
     */
    private static StringBuilder getPopulationTrust() {
        return Fetcher.fetchData("ilc_pw03", GovRightsParams.getPopulationTrustParams());
    }
}
