package app.java.commons.dimensions.mainActivity;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

public class MainActivityCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAvgWorkHours2007(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.AVG_WORK_HOURS_2007);
        FileUtils.writeToJSONFile(getAvgWorkHours2008(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.AVG_WORK_HOURS_2008);
        FileUtils.writeToJSONFile(getEmploymentRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(getInactivePopulationRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.INACTIVE_POPULATION_RATIO);
        FileUtils.writeToJSONFile(getInvoluntaryPartTimeRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.INVOLUNTARY_PART_TIME_RATIO);
        FileUtils.writeToJSONFile(getJobSatisfaction(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.JOB_SATISFACTION);
        FileUtils.writeToJSONFile(getLongTermUnemploymentRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(getLowWageEarnersRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.LOW_WAGE_EARNERS_RATIO);
        FileUtils.writeToJSONFile(getOverQualifiedRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.OVER_QUALIFIED_RATIO);
        FileUtils.writeToJSONFile(getResearchers(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.RESEARCHERS);
        FileUtils.writeToJSONFile(getTemporaryEmploymentRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.TEMPORARY_EMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(getUnemploymentRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.UNEMPLOYMENT_RATIO);
        FileUtils.writeToJSONFile(getWorkingNightsRatio(), FilePathConst.MAIN_ACTIVITY_PATH, FileNameConst.WORKING_NIGHTS_RATIO);
    }

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
    private static StringBuilder getAvgWorkHours2007() {
        return Fetcher.fetchData("lfsa_ewhuna", MainActivityParams.getAvgWorkHoursParams2007());
    }

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
    private static StringBuilder getAvgWorkHours2008() {
        return Fetcher.fetchData("lfsa_ewhun2", MainActivityParams.getAvgWorkHoursParams2008());
    }

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
    private static StringBuilder getEmploymentRatio() {
        return Fetcher.fetchData("lfsa_ergaed", MainActivityParams.getEmploymentParams());
    }

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
    private static StringBuilder getInactivePopulationRatio() {
        return Fetcher.fetchData("lfsa_ipga", MainActivityParams.getInactivePopulationParams());
    }

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
    private static StringBuilder getInvoluntaryPartTimeRatio() {
        return Fetcher.fetchData("lfsa_eppgai", MainActivityParams.getInvoluntaryPartTimeParams());
    }

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
    private static StringBuilder getJobSatisfaction() {
        return Fetcher.fetchSatisfactionRatio(MainActivityParams.getJobSatisfactionParams());
    }

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
    private static StringBuilder getLongTermUnemploymentRatio() {
        return Fetcher.fetchData("une_ltu_a", MainActivityParams.getLongTermUnemploymentParams());
    }

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
    private static StringBuilder getLowWageEarnersRatio() {
        return Fetcher.fetchData("earn_ses_pub1a", MainActivityParams.getLowWageEarnersParams());
    }

    /**
     * Self-declared over-qualified employees<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total employees aged from 15 to 64 years (%)<br/>
     * Dataset: lfso_14loq<br/>
     * Years: 2014<br/><br/>
     *
     * <b>Lack of data: DK; IE; NL</b><br/<br/>
     * <b>GREATER IS WORSE!</b>
     * @return
     */
    private static StringBuilder getOverQualifiedRatio() {
        // TODO: https://ec.europa.eu/eurostat/web/experimental-statistics/skills
        return Fetcher.fetchData("lfso_14loq", MainActivityParams.getOverQualifiedParams());
    }

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
    private static StringBuilder getResearchers() {
        return Fetcher.fetchData("rd_p_persocc", MainActivityParams.getResearchersParams());
    }

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
    private static StringBuilder getTemporaryEmploymentRatio() {
        return Fetcher.fetchData("lfsi_pt_a", MainActivityParams.getTemporaryEmploymentParams());
    }

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
    private static StringBuilder getUnemploymentRatio() {
        return Fetcher.fetchData("lfsa_urgaed", MainActivityParams.getUnemploymentParams());
    }

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
    private static StringBuilder getWorkingNightsRatio() {
        return Fetcher.fetchData("lfsa_ewpnig", MainActivityParams.getWorkingNightsParams());
    }
}
