package app.java.commons.dimensions.mainActivity;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.mainActivity.MainActivityParams.*;
import static app.java.commons.dimensions.mainActivity.MainActivityPaths.*;

public class MainActivityCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAvgWorkHours2007(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, AVG_WORK_HOURS_2007_FILE_NAME);
        FileUtils.writeToJSONFile(getAvgWorkHours2008(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, AVG_WORK_HOURS_2008_FILE_NAME);
        FileUtils.writeToJSONFile(getEmploymentRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, EMPLOYMENT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getInactivePopulationRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, INACTIVE_POPULATION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getInvoluntaryPartTimeRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, INVOLUNTARY_PART_TIME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getJobSatisfaction(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, JOB_SATISFACTION_FILE_NAME);
        FileUtils.writeToJSONFile(getLongTermUnemploymentRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLowWageEarnersRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, LOW_WAGE_EARNERS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLowWorkIntensityRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, LOW_WORK_INTENSITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getResearchers(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, RESEARCHERS_FILE_NAME);
        FileUtils.writeToJSONFile(getTemporaryEmploymentRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnemploymentRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, UNEMPLOYMENT_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWorkingFlexibilityRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, WORKING_FLEXIBILITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWorkingNightsRatio(), MainActivityPaths.MAIN_ACTIVITY_RAW_PATH, WORKING_NIGHTS_RATIO_FILE_NAME);
    }

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhuna<br/>
     * Years: 1992-2008<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getAvgWorkHours2007() {
        return Fetcher.fetchData("lfsa_ewhuna", AVG_WORK_HOURS_2007_PARAMS);
    }

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhun2<br/>
     * Years: 2008-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getAvgWorkHours2008() {
        return Fetcher.fetchData("lfsa_ewhun2", AVG_WORK_HOURS_2008_PARAMS);
    }

    /**
     * Employment rates<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of people aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ergaed<br/>
     * Years: 1983-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2emprt
     *
     * @return
     */
    private static StringBuilder getEmploymentRatio() {
        return Fetcher.fetchData("lfsa_ergaed", EMPLOYMENT_RATIO_PARAMS);
    }

    /**
     * Economically inactive population<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the population aged 15-64 years who were economically inactive (%)<br/>
     * Dataset: lfsa_ipga<br/>
     * Years: 1983-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getInactivePopulationRatio() {
        return Fetcher.fetchData("lfsa_ipga", INACTIVE_POPULATION_RATIO_PARAMS);
    }

    /**
     * Involuntary part-time employment<br/>
     * Proxy for underemployment (working less than one is able and willing)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total part-time employment aged 15-64 years (%)<br/>
     * Dataset: lfsa_eppgai<br/>
     * Years: 1983-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getInvoluntaryPartTimeRatio() {
        return Fetcher.fetchData("lfsa_eppgai", INVOLUNTARY_PART_TIME_RATIO_PARAMS);
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
        return Fetcher.fetchSatisfactionRatio(JOB_SATISFACTION_PARAMS);
    }

    /**
     * Long-term unemployment rates - annual average (the ratio of people who have been
     * unemployed for at least a year to the total size of the labour force)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of labour force aged 15-74 years (%)<br/>
     * Dataset: une_ltu_a<br/>
     * Years: 2003-2023<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu2ltu<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLongTermUnemploymentRatio() {
        return Fetcher.fetchData("une_ltu_a", LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS);
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
        return Fetcher.fetchData("earn_ses_pub1a", LOW_WAGE_EARNINGS_RATIO_PARAMS);
    }

    /**
     * People living in households with very low work intensity<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of total population aged 0 to 59 years (%)<br/>
     * Dataset: ilc_lvhl11<br/>
     * Years: 2003-2023<br/><br/>
     *
     * Comments: NUTS 2 regions => ilc_lvhl21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLowWorkIntensityRatio() {
        return Fetcher.fetchData("ilc_lvhl11", LOW_WORK_INTENSITY_RATIO_PARAMS);
    }

    /**
     * Total researchers full-time equivalent<br/>
     * FTE (Full-time equivalent) corresponds to one year's work by one person
     * (for example, a person who devotes 40 % of his time to R&D is counted as 0.4 FTE<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: rd_p_persocc<br/>
     * Years: 1980-2022
     *
     * @return
     */
    private static StringBuilder getResearchers() {
        return Fetcher.fetchData("rd_p_persocc", RESEARCHERS_PARAMS);
    }

    /**
     * Temporary contracts - annual data<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of total employment (%)<br/>
     * Dataset: lfsi_pt_a<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getTemporaryEmploymentRatio() {
        return Fetcher.fetchData("lfsi_pt_a", TEMPORARY_EMPLOYMENT_RATIO_PARAMS);
    }

    /**
     * Unemployment rates<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of labour force aged 15-74 years (%)<br/>
     * Dataset: lfsa_urgaed<br/>
     * Years: 1983-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfu3rt<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnemploymentRatio() {
        return Fetcher.fetchData("lfsa_urgaed", UNEMPLOYMENT_RATIO_PARAMS);
    }


    /**
     * Persons in employment by working time flexibility<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: thousands persons aged from 15 to 74 years (%)<br/>
     * Dataset: lfso_19fxwt01<br/>
     * Years: 2019
     *
     * @return
     */
    private static StringBuilder getWorkingFlexibilityRatio() {
        return Fetcher.fetchData("lfso_19fxwt01", WORKING_FLEXIBILITY_RATIO_PARAMS);
    }

    /**
     * Employed persons working at nights<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage of the total employment aged from 15 to 64 years (%)<br/>
     * Dataset: lfsa_ewpnig<br/>
     * Years: 1992-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getWorkingNightsRatio() {
        return Fetcher.fetchData("lfsa_ewpnig", WORKING_NIGHTS_RATIO_PARAMS);
    }
}
