package app.java.commons.dimensions.education;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.education.EducationPaths.*;

public class EducationCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getDigitalSkillsRatio(), FilePathConst.EDUCATION_PATH, DIGITAL_SKILLS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getDropoutRatio(), FilePathConst.EDUCATION_PATH, DROPOUT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEarlyEducationRatio(), FilePathConst.EDUCATION_PATH, EARLY_EDU_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEducationRatio(), FilePathConst.EDUCATION_PATH, EDU_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getInactiveYoungRatio(), FilePathConst.EDUCATION_PATH, INACTIVE_YOUNG_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNoKnownForeignLangRatio(), FilePathConst.EDUCATION_PATH, NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPupilsRatio2012(), FilePathConst.EDUCATION_PATH, PUPILS_RATIO_2012_FILE_NAME);
        FileUtils.writeToJSONFile(getPupilsRatio2013(), FilePathConst.EDUCATION_PATH, PUPILS_RATIO_2013_FILE_NAME);
        FileUtils.writeToJSONFile(getTrainingRatio(), FilePathConst.EDUCATION_PATH, TRAINING_RATIO_FILE_NAME);
    }

    /**
     * Individuals (aged 16-74) having at least basic digital skills by sex<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: sdg_04_70<br/>
     * Years: 2015-2019
     *
     * @return
     */
    private static StringBuilder getDigitalSkillsRatio() {
        return Fetcher.fetchData("sdg_04_70", EducationParams.getDigitalSkillsParams());
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
