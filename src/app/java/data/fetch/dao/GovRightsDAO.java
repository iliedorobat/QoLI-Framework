package app.java.data.fetch.dao;

/**
 * Factors which influence the life of population from the perspective
 * of the governing, law making and equality of opportunity
 */
public interface GovRightsDAO {
    //TODO: this dataset should be manually downloaded from
    // https://www.idea.int/data-tools/data/voter-turnout

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
    StringBuilder getActiveCitizenship();

    /**
     * Employment (from 20 to 64 years) by sex - annual data<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: lfsi_emp_a<br/>
     * Years: 1992-2018
     *
     * @return
     */
    StringBuilder getEmploymentRatio();

    /**
     * Gender pay gap in unadjusted form by NACE Rev. 2 activity - structure of earnings survey methodology<br/>
     * Industry, construction and services (except public administration, defense, compulsory social security)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: earn_gr_gpgr2<br/>
     * Years: 2007-2017<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getGenderPayGap();

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
    StringBuilder getPopulationTrust();
}
