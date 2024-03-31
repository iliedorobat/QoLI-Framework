package ro.webdata.qoli.aggr.stats.dimensions.education;

import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;

import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationPaths.*;

public class EducationCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getDigitalSkillsRatio(), EDUCATION_RAW_PATH, DIGITAL_SKILLS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getDropoutRatio(), EDUCATION_RAW_PATH, DROPOUT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEarlyEducationRatio(), EDUCATION_RAW_PATH, EARLY_EDU_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEducationRatio(), EDUCATION_RAW_PATH, EDU_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getInactiveYoungRatio(), EDUCATION_RAW_PATH, INACTIVE_YOUNG_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNoKnownForeignLangRatio(), EDUCATION_RAW_PATH, NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPupilsRatio2012(), EDUCATION_RAW_PATH, PUPILS_RATIO_2012_FILE_NAME);
        FileUtils.writeToJSONFile(getPupilsRatio2013(), EDUCATION_RAW_PATH, PUPILS_RATIO_2013_FILE_NAME);
        FileUtils.writeToJSONFile(getTrainingLastMonthRatio(), EDUCATION_RAW_PATH, TRAINING_LAST_MONTH_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getTrainingLastYearRatio(), EDUCATION_RAW_PATH, TRAINING_LAST_YEAR_RATIO_FILE_NAME);
    }

    /**
     * Share of individuals (aged 16-74) having at least basic digital skills<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: sdg_04_70<br/>
     * Note: The digital skills indicapotr methodology has changed substantially to reflect the
     *      Digital Competence Framework 2.0. As a consequence, 2021 is the beginning of a new
     *      time series. https://ec.europa.eu/eurostat/cache/metadata/en/sdg_04_70_esmsip2.htm
     * Years: 2021; 2023
     *
     * @return
     */
    private static StringBuilder getDigitalSkillsRatio() {
        return Fetcher.fetchData("sdg_04_70", DIGITAL_SKILLS_PARAMS);
    }

    /**
     * Early leavers (from 18 to 24 years) from education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_14<br/>
     * Years: 1992-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_16<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getDropoutRatio() {
        return Fetcher.fetchData("edat_lfse_14", DROPOUT_RATIO_PARAMS);
    }

    /**
     * Participation in early childhood education:<br/> pupils aged between
     * 3 years old and the starting age of compulsory education at primary
     * level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_enra21<br/>
     * Years: 1998-2021<br/><br/>
     *
     * Comments: NUTS 2 regions => educ_uoe_enra22<br/><br/>
     *
     * @return
     */
    private static StringBuilder getEarlyEducationRatio() {
        return Fetcher.fetchData("educ_uoe_enra21", EARLY_EDUCATION_RATIO_PARAMS);
    }

    /**
     * Population (from 15 to 64 years) by educational attainment level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_03<br/>
     * Years: 1992-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_04
     *
     * @return
     */
    private static StringBuilder getEducationRatio() {
        return Fetcher.fetchData("edat_lfs_9903", EDUCATION_RATIO_PARAMS);
    }

    /**
     * Young people (from 18 to 24 years) neither in employment nor in education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: NEET rates (%)<br/>
     * Dataset: edat_lfse_20<br/>
     * Years: 2000-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_22<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getInactiveYoungRatio() {
        return Fetcher.fetchData("edat_lfse_20", INACTIVE_YOUNG_RATIO_PARAMS);
    }

    /**
     * Proportion of people (from 25 to 64 years) who don't know any foreign language (self-reported)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_aes_l21<br/>
     * Years: 2007; 2011; 2016; 2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getNoKnownForeignLangRatio() {
        return Fetcher.fetchData("edat_aes_l22", NO_KNOWN_FOREIGN_LANG_RATIO_PARAMS);
    }

    /**
     * Ratio of pupils to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: ratio of pupils to teachers (number)<br/>
     * Dataset: educ_iste<br/>
     * Years: 1998-2012<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPupilsRatio2012() {
        return Fetcher.fetchData("educ_iste", PUPILS_RATIO_2012_PARAMS);
    }

    /**
     * Ratio of pupils to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: ratio of pupils to teachers (number)<br/>
     * Dataset: educ_uoe_perp04<br/>
     * Years: 2013-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPupilsRatio2013() {
        return Fetcher.fetchData("educ_uoe_perp04", PUPILS_RATIO_2013_PARAMS);
    }

    /**
     * Participation rate in education and training (last 4 weeks - from 25 to 64 years)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: trng_lfs_02<br/>
     * Years: 2004-2022
     *
     * @return
     */
    private static StringBuilder getTrainingLastMonthRatio() {
        return Fetcher.fetchData("trng_lfs_02", TRAINING_LAST_MONTH_RATIO_PARAMS);
    }

    /**
     * Participation rate in education and training (last 12 months - from 25 to 64 years)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: trng_aes_100<br/>
     * Years: 2007; 2011; 2016; 2022
     *
     * @return
     */
    private static StringBuilder getTrainingLastYearRatio() {
        return Fetcher.fetchData("trng_aes_100", TRAINING_LAST_YEAR_RATIO_PARAMS);
    }
}
