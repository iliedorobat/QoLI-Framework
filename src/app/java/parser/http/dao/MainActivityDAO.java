package app.java.parser.http.dao;

/**
 * Professional life
 */
public interface MainActivityDAO {
    //TODO: Unemployment rate by educational level ???
    //TODO: Health and safety at work (accidental injuries at work) ???

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
     * Involuntary part-time employment<br/><br/>
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
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhuna | lfsa_ewhun2<br/>
     * Years: 1983-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @param activity The classification of economic activities<br/>
     *                 - NACE_R1: for years between 1983-2008;<br/>
     *                 - NACE_R2: for years between 2008-2018.
     *
     * @return
     */
    //TODO: avg free hours per week (Work-life balance)
    StringBuilder getAvgWorkHours(String activity);

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
}
