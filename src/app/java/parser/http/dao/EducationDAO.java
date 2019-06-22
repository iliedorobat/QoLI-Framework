package app.java.parser.http.dao;

import java.util.Map;

public interface EducationDAO {
    /**
     * Ratio of pupils and students to teachers for primary and secondary education (levels 1-3)<br/><br/>
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
     * Ratio of pupils and students to teachers for primary and secondary education (levels 1-3)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_perp04<br/>
     * Years: 2013-2017
     *
     * @return
     */
    StringBuilder getPupilsRatio2013();

    /**
     * Ratio of the proportion of students (ISCED 5-6) over the proportion of the population<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_regind<br/>
     * Years: 1998-2012
     *
     * @return
     */
    StringBuilder getStudentsRatioJSON2012();

    /**
     * Ratio of the proportion of tertiary students over the proportion of the population<br/><br/>
     *
     * Aggregation: NUTS2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: educ_uoe_enrt05<br/>
     * Years: 2013-2017
     *
     * @return
     */
    StringBuilder getStudentsRatioJSON2013();

    /**
     * Young people (from 18 to 24 years) neither in employment nor in education and training<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: NEET rates (%)<br/>
     * Dataset: edat_lfse_22<br/>
     * Years: 2000-2018
     *
     * @return
     */
    StringBuilder getExcludedRatioJSON();

    /**
     * Early leavers (from 18 to 24 years) from education and training<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_16<br/>
     * Years: 2000-2018
     *
     * @return
     */
    StringBuilder getLeaversRatioJSON();

    /**
     * Population aged 25-64 by the attended education level<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_lfse_04<br/>
     * Years: 2000-2018
     *
     * @param education The education level according to ISCED 2011:<br/>
     *              - ED5-8: Tertiary education (levels 5-8);<br/>
     *              - ED3_4: Upper secondary and post-secondary non-tertiary education (levels 3 and 4);<br/>
     *              - ED3-8: Upper secondary, post-secondary non-tertiary and tertiary education (levels 3-8);<br/>
     *              - ED0-2: Less than primary, primary and lower secondary education (levels 0-2).
     * @return
     */
    StringBuilder getEducationRatioJSON(String education);

    /**
     * Participation rate in education and training (last 4 weeks - from 25 to 64 years)<br/><br/>
     *
     * Aggregation: countries<br/>
     * Data type: percentage (%)<br/>
     * Dataset: trng_lfs_02<br/>
     * Years: 2004-2018
     *
     * @return
     */
    StringBuilder getTrainingRatioJSON();

    /**
     * Proportion of people (from 25 to 64 years) who don't know any foreign language (self-reported)<br/><br/>
     *
     * Aggregation: countries<br/>
     * Data type: percentage (%)<br/>
     * Dataset: edat_aes_l22<br/>
     * Years: 2007; 2011; 2016
     *
     * @return
     */
    StringBuilder getZeroForeignLangRatioJSON();
}
