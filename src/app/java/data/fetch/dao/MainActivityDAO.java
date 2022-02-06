package app.java.data.fetch.dao;

/**
 * Professional life
 */
public interface MainActivityDAO {
    /**
     * Economically active population<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the population aged 15-64 years who are economically active (%)<br/>
     * Dataset: lfsi_emp_a<br/>
     * Years: 1992-2020
     *
     * Comments: NUTS 2 regions => lfst_r_lfp2act
     *
     * @return
     */
    StringBuilder getActivePopulationRatio();

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhuna<br/>
     * Years: 1983-2008<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getAvgWorkHours2007();

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhun2<br/>
     * Years: 2008-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getAvgWorkHours2008();

    /**
     * Employment rates<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of people aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ergaed<br/>
     * Years: 1983-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2emprt
     *
     * @return
     */
    StringBuilder getEmploymentRatio();

    /**
     * Economically inactive population<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the population aged 15-64 years who were economically inactive (%)<br/>
     * Dataset: lfsa_ipga<br/>
     * Years: 1983-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getInactivePopulationRatio();

    /**
     * Involuntary part-time employment<br/>
     * Proxy for underemployment (working less than one is able and willing)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total part-time employment aged 15-64 years (%)<br/>
     * Dataset: lfsa_eppgai<br/>
     * Years: <br/><br/>
     * Years: 1983-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getInvoluntaryPartTimeRatio();

    /**
     * Percentage of the population rating their job satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    StringBuilder getJobSatisfaction();

    /**
     * Long-term unemployment rates - annual average (the ratio of people who have been
     * unemployed for at least a year to the total size of the labour force)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of labour force aged 15-74 years (%)<br/>
     * Dataset: une_ltu_a<br/>
     * Years: 1996-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu2ltu<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLongTermUnemploymentRatio();

    /**
     * Low-wage earners as a proportion of all employees (excluding apprentices)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of employees earning two thirds or less of national median earnings (%)<br/>
     * Dataset: earn_ses_pub1a<br/>
     * Years: 2006; 2010; 2014; 2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLowWageEarnersRatio();

    /**
     * Self-declared over-qualified employees<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total employees aged from 15 to 64 years (%)<br/>
     * Dataset: lfso_14loq<br/>
     * Years: 2014<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @deprecated no dataset: DK; IE; NL
     * @return
     */
    StringBuilder getOverQualifiedRatio();

    /**
     * Total researchers full-time equivalent<br/>
     * FTE (Full-time equivalent) corresponds to one year's work by one person
     * (for example, a person who devotes 40 % of his time to R&D is counted as 0.4 FTE<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: rd_p_persocc<br/>
     * Years: 1980-2020
     *
     * @return
     */
    StringBuilder getResearchers();

    /**
     * Temporary contracts - annual data<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of total employment (%)<br/>
     * Dataset: lfsi_pt_a<br/>
     * Years: 1993-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getTemporaryEmploymentRatio();

    /**
     * Unemployment rates<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of labour force aged 15-74 years (%)<br/>
     * Dataset: lfsa_urgaed<br/>
     * Years: 1984-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu3rt<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getUnemploymentRatio();

    /**
     * Employed persons working at nights<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total employment aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ewpnig<br/>
     * Years: 1992-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getWorkingNightsRatio();
}
