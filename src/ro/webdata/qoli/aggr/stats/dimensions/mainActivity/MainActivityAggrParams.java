package ro.webdata.qoli.aggr.stats.dimensions.mainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.*;

public class MainActivityAggrParams {
    public static final String MAIN_ACTIVITY = "mainActivity";

    public static final String AVG_WORK_HOURS = MAIN_ACTIVITY + ":avgWorkHours";
    public static final String AVG_REMAINED_WORK_HOURS = MAIN_ACTIVITY + ":avgRemainedWorkHours";
    public static final String EMPLOYMENT_RATIO = MAIN_ACTIVITY + ":employmentRatio";
    public static final String INACTIVE_POPULATION_RATIO = MAIN_ACTIVITY + ":inactivePopulationRatio";
    public static final String INVOLUNTARY_PART_TIME_RATIO = MAIN_ACTIVITY + ":involuntaryPartTimeRatio";
    public static final String JOB_SATISFACTION = MAIN_ACTIVITY + ":jobSatisfaction";
    public static final String LONG_TERM_UNEMPLOYMENT_RATIO = MAIN_ACTIVITY + ":longTermUnemploymentRatio";
    public static final String LOW_WAGE_EARNERS_RATIO = MAIN_ACTIVITY + ":lowWageEarnersRatio";
    public static final String LOW_WORK_INTENSITY_RATIO = MAIN_ACTIVITY + ":lowWorkIntensityRatio";
    public static final String RESEARCHERS_RATIO = MAIN_ACTIVITY + ":researchersRatio";
    public static final String TEMPORARY_EMPLOYMENT_RATIO = MAIN_ACTIVITY + ":temporaryEmploymentRatio";
    public static final String UNEMPLOYMENT_RATIO = MAIN_ACTIVITY + ":unemploymentRatio";
    public static final String WORKING_FLEXIBILITY_FULL_RATIO = MAIN_ACTIVITY + ":workingFlexibilityFullRatio";
    public static final String WORKING_FLEXIBILITY_RESTRICTIVE_RATIO = MAIN_ACTIVITY + ":workingFlexibilityRestrictiveRatio";
    public static final String WORKING_FLEXIBILITY_TOTAL_RATIO = MAIN_ACTIVITY + ":workingFlexibilityTotalRatio";
    public static final String WORKING_FLEXIBILITY_RATIO = MAIN_ACTIVITY + ":workingFlexibilityRatio";
    public static final String WORKING_NIGHTS_RATIO = MAIN_ACTIVITY + ":workingNightsRatio";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(AVG_REMAINED_WORK_HOURS, "Average Remained Work Hours Ratio");
        put(EMPLOYMENT_RATIO, "Employment Ratio");
        put(INACTIVE_POPULATION_RATIO, "Inactive Population Ratio");
        put(INVOLUNTARY_PART_TIME_RATIO, "Involuntary Part-Time Ratio");
        put(JOB_SATISFACTION, "Job Satisfaction");
        put(LONG_TERM_UNEMPLOYMENT_RATIO, "Long Term Unemployment Ratio");
        put(LOW_WAGE_EARNERS_RATIO, "Low Wage Earners Ratio");
        put(LOW_WORK_INTENSITY_RATIO, "Low Work Intensity Ratio");
        put(RESEARCHERS_RATIO, "Researchers Ratio");
        put(TEMPORARY_EMPLOYMENT_RATIO, "Temporary Employment Ratio");
        put(UNEMPLOYMENT_RATIO, "Unemployment Ratio");
        put(WORKING_FLEXIBILITY_RATIO, "Working Flexibility Ratio");
        put(WORKING_NIGHTS_RATIO, "Working Nights Ratio");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(AVG_REMAINED_WORK_HOURS, HOURS); // TODO: avg working hours?
        put(EMPLOYMENT_RATIO, PERCENT);
        put(INACTIVE_POPULATION_RATIO, PERCENT);
        put(INVOLUNTARY_PART_TIME_RATIO, PERCENT);
        put(JOB_SATISFACTION, PERCENT);
        put(LONG_TERM_UNEMPLOYMENT_RATIO, PERCENT);
        put(LOW_WAGE_EARNERS_RATIO, PERCENT);
        put(LOW_WORK_INTENSITY_RATIO, PERCENT);
        put(RESEARCHERS_RATIO, PER_10_THOUSAND_INHABITANTS);
        put(TEMPORARY_EMPLOYMENT_RATIO, PERCENT);
        put(UNEMPLOYMENT_RATIO, PERCENT);
        put(WORKING_FLEXIBILITY_RATIO, PERCENT);
        put(WORKING_NIGHTS_RATIO, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
        put(AVG_REMAINED_WORK_HOURS, false);
        put(EMPLOYMENT_RATIO, false);
        put(INACTIVE_POPULATION_RATIO, true);
        put(INVOLUNTARY_PART_TIME_RATIO, true);
        put(LONG_TERM_UNEMPLOYMENT_RATIO, true);
        put(JOB_SATISFACTION, false);
        put(LOW_WAGE_EARNERS_RATIO, true);
        put(LOW_WORK_INTENSITY_RATIO, true);
        put(RESEARCHERS_RATIO, false);
        put(TEMPORARY_EMPLOYMENT_RATIO, true);
        put(UNEMPLOYMENT_RATIO, true);
        put(WORKING_FLEXIBILITY_RATIO, false);
        put(WORKING_NIGHTS_RATIO, true);
    }};
}
