package ro.webdata.qoli.aggr.stats.dimensions.mainActivity;

import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.io.File;

public class MainActivityPaths {
    public static final String MAIN_ACTIVITY_FILE_NAME = "mainActivity";

    public static final String AVG_WORK_HOURS_2007_FILE_NAME = "avgWorkHours2007";
    public static final String AVG_WORK_HOURS_2008_FILE_NAME = "avgWorkHours2008";
    public static final String EMPLOYMENT_RATIO_FILE_NAME = "employmentRatio";
    public static final String INACTIVE_POPULATION_RATIO_FILE_NAME = "inactivePopulationRatio";
    public static final String INVOLUNTARY_PART_TIME_RATIO_FILE_NAME = "involuntaryPartTimeRatio";
    public static final String JOB_SATISFACTION_FILE_NAME = "jobSatisfaction";
    public static final String LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME = "longTermUnemploymentRatio";
    public static final String LOW_WAGE_EARNERS_RATIO_FILE_NAME = "lowWageEarnersRatio";
    public static final String LOW_WORK_INTENSITY_RATIO_FILE_NAME = "lowWorkIntensityRatio";
    public static final String RESEARCHERS_FILE_NAME = "researchers";
    public static final String TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME = "temporaryEmploymentRatio";
    public static final String UNEMPLOYMENT_RATIO_FILE_NAME = "unemploymentRatio";
    public static final String WORKING_FLEXIBILITY_RATIO_FILE_NAME = "flexibilityRatio";
    public static final String WORKING_NIGHTS_RATIO_FILE_NAME = "nightsRatio";

    public static final String MAIN_ACTIVITY_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, MAIN_ACTIVITY_FILE_NAME);

    private static String generatePath(String fileName) {
        return MAIN_ACTIVITY_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            AVG_WORK_HOURS_2007_PATH = generatePath(AVG_WORK_HOURS_2007_FILE_NAME),
            AVG_WORK_HOURS_2008_PATH = generatePath(AVG_WORK_HOURS_2008_FILE_NAME),
            EMPLOYMENT_RATIO_PATH = generatePath(EMPLOYMENT_RATIO_FILE_NAME),
            INACTIVE_POPULATION_RATIO_PATH = generatePath(INACTIVE_POPULATION_RATIO_FILE_NAME),
            INVOLUNTARY_PART_TIME_RATIO_PATH = generatePath(INVOLUNTARY_PART_TIME_RATIO_FILE_NAME),
            JOB_SATISFACTION_PATH = generatePath(JOB_SATISFACTION_FILE_NAME),
            LONG_TERM_UNEMPLOYMENT_RATIO_PATH = generatePath(LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME),
            LOW_WAGE_EARNINGS_RATIO_PATH = generatePath(LOW_WAGE_EARNERS_RATIO_FILE_NAME),
            LOW_WORK_INTENSITY_RATIO_PATH = generatePath(LOW_WORK_INTENSITY_RATIO_FILE_NAME),
            RESEARCHERS_PATH = generatePath(RESEARCHERS_FILE_NAME),
            TEMPORARY_EMPLOYMENT_RATIO_PATH = generatePath(TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME),
            UNEMPLOYMENT_RATIO_PATH = generatePath(UNEMPLOYMENT_RATIO_FILE_NAME),
            WORKING_FLEXIBILITY_RATIO_PATH = generatePath(WORKING_FLEXIBILITY_RATIO_FILE_NAME),
            WORKING_NIGHTS_RATIO_PATH = generatePath(WORKING_NIGHTS_RATIO_FILE_NAME);
}
