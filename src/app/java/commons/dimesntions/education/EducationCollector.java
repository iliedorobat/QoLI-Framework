package app.java.commons.dimesntions.education;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

public class EducationCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getDigitalSkillsRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.DIGITAL_SKILLS_RATIO);
        FileUtils.writeToJSONFile(getDropoutRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.DROPOUT_RATIO);
        FileUtils.writeToJSONFile(getEarlyEducationRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.EARLY_EDU_RATIO);
        FileUtils.writeToJSONFile(getEducationRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.EDU_RATIO);
        FileUtils.writeToJSONFile(getInactiveYoungRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.INACTIVE_YOUNG_RATIO);
        FileUtils.writeToJSONFile(getNoKnownForeignLangRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.NO_KNOWN_FOREIGN_LANG_RATIO);
        FileUtils.writeToJSONFile(getPupilsRatio2012(), FilePathConst.EDUCATION_PATH, FileNameConst.PUPILS_RATIO_2012);
        FileUtils.writeToJSONFile(getPupilsRatio2013(), FilePathConst.EDUCATION_PATH, FileNameConst.PUPILS_RATIO_2013);
        FileUtils.writeToJSONFile(getTrainingRatio(), FilePathConst.EDUCATION_PATH, FileNameConst.TRAINING_RATIO);
    }

    /**
     * Individuals (aged 16-74) who have basic or above basic overall digital skills by sex<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: tepsr_sp410<br/>
     * Years: 2015-2019
     *
     * @return
     */
    private static StringBuilder getDigitalSkillsRatio() {
        return Fetcher.fetchData("tepsr_sp410", EducationParams.getDigitalSkillsParams());
    }

    /**
     * Early leavers (from 18 to 24 years) from education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_14<br/>
     * Years: 1992-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_16<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getDropoutRatio() {
        return Fetcher.fetchData("edat_lfse_14", EducationParams.getDropoutParams());
    }

    /**
     * Participation in early childhood education:<br/> pupils aged between
     * 4 years old and the starting age of compulsory education<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_enra10<br/>
     * Years: 1998-2019
     *
     * @return
     */
    private static StringBuilder getEarlyEducationRatio() {
        return Fetcher.fetchData("educ_uoe_enra10", EducationParams.getEarlyEducationParams());
    }

    /**
     * Population (from 15 to 64 years) by educational attainment level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfs_9903<br/>
     * Years: 2004-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_04
     *
     * @return
     */
    private static StringBuilder getEducationRatio() {
        return Fetcher.fetchData("edat_lfs_9903", EducationParams.getEducationParams());
    }

    /**
     * Young people (from 18 to 24 years) neither in employment nor in education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: NEET rates (%)<br/>
     * Dataset: edat_lfse_20<br/>
     * Years: 2000-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_22<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getInactiveYoungRatio() {
        return Fetcher.fetchData("edat_lfse_20", EducationParams.getInactiveYoungParams());
    }

    /**
     * Proportion of people (from 25 to 64 years) who don't know any foreign language (self-reported)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_aes_l22<br/>
     * Years: 2007; 2011; 2016
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNoKnownForeignLangRatio() {
        return Fetcher.fetchData("edat_aes_l22", EducationParams.getNoKnownForeignLangParams());
    }

    /**
     * Ratio of pupils to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_iste<br/>
     * Years: 1998-2012
     *
     * @return
     */
    private static StringBuilder getPupilsRatio2012() {
        return Fetcher.fetchData("educ_iste", EducationParams.getPupilsParams2012());
    }

    /**
     * Ratio of pupils to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_perp04<br/>
     * Years: 2013-2019
     *
     * @return
     */
    private static StringBuilder getPupilsRatio2013() {
        return Fetcher.fetchData("educ_uoe_perp04", EducationParams.getPupilsParams2013());
    }

    /**
     * Participation rate in education and training (last 4 weeks - from 25 to 64 years)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: trng_lfs_02<br/>
     * Years: 2004-2020
     *
     * @return
     */
    private static StringBuilder getTrainingRatio() {
        return Fetcher.fetchData("trng_lfs_02", EducationParams.getTrainingParams());
    }
}
