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
     * Years: 1992-2018
     *
     * Comments: NUTS 2 regions => lfst_r_lfp2act
     *
     * @return
     */
    StringBuilder getActivePopulation();

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
    //TODO: avg free hours per week (Work-life balance)
    StringBuilder getAvgWorkHours2007();

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhun2<br/>
     * Years: 2008-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    //TODO: avg free hours per week (Work-life balance)
    StringBuilder getAvgWorkHours2008();

    /**
     * Employment rates<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of people aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ergaed<br/>
     * Years: 1983-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2emprt
     *
     * @return
     */
    StringBuilder getEmploymentRatio();

    /**
     * Involuntary part-time employment<br/>
     * Proxy for underemployment (working less than one is able and willing)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total part-time employment aged 15-64 years (%)<br/>
     * Dataset: lfsa_eppgai<br/>
     * Years: <br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getInvoluntaryPartTimeRatio();

    /**
     * Long-term unemployment rates - annual average (the ratio of people who have been
     * unemployed for at least a year to the total size of the labour force)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of labour force aged 15-74 years (%)<br/>
     * Dataset: une_ltu_a<br/>
     * Years: 1996-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu2ltu<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getLongTermUnemploymentRatio();

    /**
     * Employed persons working at nights<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total employment aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ewpnig<br/>
     * Years: 1992-2018<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getNightsRatio();

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
     * Years: 1980-2017
     *
     * @return
     */
    //TODO: researchers ratio = researchers / active population * 10.000
    StringBuilder getResearchers();

    /**
     * Temporary contracts - annual data<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of total employment (%)<br/>
     * Dataset: lfsi_pt_a<br/>
     * Years: 1993-2018<br/><br/>
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
     * Years: 1983-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu3rt<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    StringBuilder getUnemploymentRatio();
}
