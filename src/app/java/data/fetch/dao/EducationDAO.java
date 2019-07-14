package app.java.data.fetch.dao;

/**
 * Education level
 */
public interface EducationDAO {
    /**
     * Population (from 15 to 64 years) by educational attainment level<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfs_9903<br/>
     * Years: 2004-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_04
     *
     * @param eduLevel The list of education levels:<br/>
     *              - ED5-8: Tertiary education (levels 5-8);<br/>
     *              - ED3_4: Upper secondary and post-secondary non-tertiary education (levels 3 and 4);<br/>
     *              - ED0-2: Less than primary, primary and lower secondary education (levels 0-2).
     *
     * @return
     */
    StringBuilder getEducationRatio(String[] eduLevel);

    /**
     * Participation in early childhood education:<br/> pupils aged between
     * 4 years old and the starting age of compulsory education<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_enra10<br/>
     * Years: 1998-2017
     *
     * @return
     */
    StringBuilder getEarlyEducationRatio();

    /**
     * Early leavers (from 18 to 24 years) from education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_14<br/>
     * Years: 1992-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_16<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLeaversRatioJSON();

    /**
     * Young people (from 18 to 24 years) neither in employment nor in education and training<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: NEET rates (%)<br/>
     * Dataset: edat_lfse_20<br/>
     * Years: 2000-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => edat_lfse_22<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getExcludedRatio();

    /**
     * Participation rate in education and training (last 4 weeks - from 25 to 64 years)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: trng_lfs_02<br/>
     * Years: 2004-2018
     *
     * @return
     */
    StringBuilder getTrainingRatio();

    /**
     * Individuals (aged 16-74) who have basic or above basic overall digital skills by sex<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: tepsr_sp410<br/>
     * Years: 2015-2017
     *
     * @return
     */
    StringBuilder getDigitalSkillsRatio();

    /**
     * Proportion of people (from 25 to 64 years) who don't know any foreign language (self-reported)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_aes_l22<br/>
     * Years: 2007; 2011; 2016
     *
     * @return
     */
    StringBuilder getZeroForeignLangRatioJSON();

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
    StringBuilder getPupilsRatio2012();

    /**
     * Ratio of pupils to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_perp04<br/>
     * Years: 2013-2017
     *
     * @return
     */
    StringBuilder getPupilsRatio2013();
}
