package ro.webdata.qoli.aggr.stats.dimensions.mainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityAggrParams {
    public static final String MAIN_ACTIVITY = "mainActivity";

    public static final String AVG_WORK_HOURS = "avgWorkHours";
    public static final String AVG_REMAINED_WORK_HOURS = "avgRemainedWorkHours";
    public static final String EMPLOYMENT_RATIO = "employmentRatio";
    public static final String INACTIVE_POPULATION_RATIO = "inactivePopulationRatio";
    public static final String INVOLUNTARY_PART_TIME_RATIO = "involuntaryPartTimeRatio";
    public static final String JOB_SATISFACTION = "jobSatisfaction";
    public static final String LONG_TERM_UNEMPLOYMENT_RATIO = "longTermUnemploymentRatio";
    public static final String LOW_WAGE_EARNERS_RATIO = "lowWageEarnersRatio";
    public static final String LOW_WORK_INTENSITY_RATIO = "lowWorkIntensityRatio";
    public static final String RESEARCHERS_RATIO = "researchersRatio";
    public static final String TEMPORARY_EMPLOYMENT_RATIO = "temporaryEmploymentRatio";
    public static final String UNEMPLOYMENT_RATIO = "unemploymentRatio";
    public static final String WORKING_FLEXIBILITY_FULL_RATIO = "workingFlexibilityFullRatio";
    public static final String WORKING_FLEXIBILITY_RESTRICTIVE_RATIO = "workingFlexibilityRestrictiveRatio";
    public static final String WORKING_FLEXIBILITY_TOTAL_RATIO = "workingFlexibilityTotalRatio";
    public static final String WORKING_FLEXIBILITY_RATIO = "workingFlexibilityRatio";
    public static final String WORKING_NIGHTS_RATIO = "workingNightsRatio";

    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(AVG_REMAINED_WORK_HOURS);
        add(EMPLOYMENT_RATIO);
        add(INACTIVE_POPULATION_RATIO);
        add(INVOLUNTARY_PART_TIME_RATIO);
        add(LONG_TERM_UNEMPLOYMENT_RATIO);
        add(JOB_SATISFACTION);
        add(LOW_WAGE_EARNERS_RATIO);
        add(LOW_WORK_INTENSITY_RATIO);
        add(RESEARCHERS_RATIO);
        add(TEMPORARY_EMPLOYMENT_RATIO);
        add(UNEMPLOYMENT_RATIO);
        add(WORKING_FLEXIBILITY_RATIO);
        add(WORKING_NIGHTS_RATIO);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
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
