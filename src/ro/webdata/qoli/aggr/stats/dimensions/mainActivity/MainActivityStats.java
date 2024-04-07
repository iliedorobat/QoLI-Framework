package ro.webdata.qoli.aggr.stats.dimensions.mainActivity;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.MergeUtils;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryStats;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityAggrParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityPaths.*;

public class MainActivityStats {
    /** 12 hours * 7 days */
    private static final int MAX_LEGAL_WORK_HOURS = 12 * 7;

    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            avgWorkHours2007 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2007_PARAMS, AVG_WORK_HOURS_2007_PATH),
            avgWorkHours2008 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2008_PARAMS, AVG_WORK_HOURS_2008_PATH);
    private static final ArrayList<Map<String, Number>> avgWorkHoursList = new ArrayList<>() {{
        add(avgWorkHours2007);
        add(avgWorkHours2008);
    }};

    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            initFlexibilityFullRatio = Initializer.initConsolidatedMap(WORKING_FLEXIBILITY_FULL_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH),
            initFlexibilityRestrictiveRatio = Initializer.initConsolidatedMap(WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH),
            initFlexibilityTotalRatio = Initializer.initConsolidatedMap(WORKING_FLEXIBILITY_TOTAL_RATIO_PARAMS, WORKING_FLEXIBILITY_RATIO_PATH);

    private static final Map<String, Number>
            initAvgWorkHoursList = Initializer.initConsolidatedMaps(avgWorkHoursList),
            initEmploymentRatio = Initializer.initConsolidatedMap(EMPLOYMENT_RATIO_PARAMS, EMPLOYMENT_RATIO_PATH),
            initInactivePopulationRatio = Initializer.initConsolidatedMap(INACTIVE_POPULATION_RATIO_PARAMS, INACTIVE_POPULATION_RATIO_PATH),
            initInvoluntaryPartTimeRatio = Initializer.initConsolidatedMap(INVOLUNTARY_PART_TIME_RATIO_PARAMS, INVOLUNTARY_PART_TIME_RATIO_PATH),
            initJobSatisfaction = Initializer.initConsolidatedMap(JOB_SATISFACTION_PARAMS, JOB_SATISFACTION_PATH),
            initLongTermUnemploymentRatio = Initializer.initConsolidatedMap(LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS, LONG_TERM_UNEMPLOYMENT_RATIO_PATH),
            initLowWageEarningsRatio = Initializer.initConsolidatedMap(LOW_WAGE_EARNINGS_RATIO_PARAMS, LOW_WAGE_EARNINGS_RATIO_PATH),
            initLowWorkIntensityRatio = Initializer.initConsolidatedMap(LOW_WORK_INTENSITY_RATIO_PARAMS, LOW_WORK_INTENSITY_RATIO_PATH),
            initResearchers = Initializer.initConsolidatedMap(RESEARCHERS_PARAMS, RESEARCHERS_PATH),
            initTemporaryEmploymentRatio = Initializer.initConsolidatedMap(TEMPORARY_EMPLOYMENT_RATIO_PARAMS, TEMPORARY_EMPLOYMENT_RATIO_PATH),
            initUnemploymentRatio = Initializer.initConsolidatedMap(UNEMPLOYMENT_RATIO_PARAMS, UNEMPLOYMENT_RATIO_PATH),
            initWorkingNightsRatio = Initializer.initConsolidatedMap(WORKING_NIGHTS_RATIO_PARAMS, WORKING_NIGHTS_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate avgRemainedWorkHours
            avgWorkHours = Preparation.prepareData(initAvgWorkHoursList),

            // Intermediate data used to calculate workingFlexibilityRatio
            flexibilityFullRatio = Preparation.prepareData(initFlexibilityFullRatio),
            flexibilityRestrictiveRatio = Preparation.prepareData(initFlexibilityRestrictiveRatio),
            flexibilityTotalRatio = Preparation.prepareData(initFlexibilityTotalRatio),

            avgRemainedWorkHours = prepareAvgRemainedWorkHours(),
            employmentRatio = Preparation.prepareData(initEmploymentRatio),
            inactivePopulationRatio = Preparation.prepareData(initInactivePopulationRatio),
            involuntaryPartTimeRatio = Preparation.prepareData(initInvoluntaryPartTimeRatio),
            jobSatisfaction = Preparation.prepareData(initJobSatisfaction),
            longTermUnemploymentRatio = Preparation.prepareData(initLongTermUnemploymentRatio),
            lowWageEarningsRatio = Preparation.prepareData(initLowWageEarningsRatio),
            lowWorkIntensityRatio = Preparation.prepareData(initLowWorkIntensityRatio),
            researchersRatio = Preparation.preparePerTenThousandInhabitants(AuxiliaryStats.population, initResearchers),
            temporaryEmploymentRatio = Preparation.prepareData(initTemporaryEmploymentRatio),
            unemploymentRatio = Preparation.prepareData(initUnemploymentRatio),
            workingFlexibilityRatio = prepareWorkingFlexibilityRatio(),
            workingNightsRatio = Preparation.prepareData(initWorkingNightsRatio);

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(AVG_WORK_HOURS, Preparation.filterMap(initAvgWorkHoursList));
        put(EMPLOYMENT_RATIO, Preparation.filterMap(initEmploymentRatio));
        put(INACTIVE_POPULATION_RATIO, Preparation.filterMap(initInactivePopulationRatio));
        put(INVOLUNTARY_PART_TIME_RATIO, Preparation.filterMap(initInvoluntaryPartTimeRatio));
        put(JOB_SATISFACTION, Preparation.filterMap(initJobSatisfaction));
        put(LONG_TERM_UNEMPLOYMENT_RATIO, Preparation.filterMap(initLongTermUnemploymentRatio));
        put(LOW_WAGE_EARNERS_RATIO, Preparation.filterMap(initLowWageEarningsRatio));
        put(LOW_WORK_INTENSITY_RATIO, Preparation.filterMap(initLowWorkIntensityRatio));
        put(RESEARCHERS_RATIO, Preparation.filterMap(initResearchers));
        put(TEMPORARY_EMPLOYMENT_RATIO, Preparation.filterMap(initTemporaryEmploymentRatio));
        put(UNEMPLOYMENT_RATIO, Preparation.filterMap(initUnemploymentRatio));
        put(WORKING_FLEXIBILITY_FULL_RATIO, Preparation.filterMap(initFlexibilityFullRatio));
        put(WORKING_FLEXIBILITY_RESTRICTIVE_RATIO, Preparation.filterMap(initFlexibilityRestrictiveRatio));
        put(WORKING_FLEXIBILITY_TOTAL_RATIO, Preparation.filterMap(initFlexibilityTotalRatio));
        put(WORKING_NIGHTS_RATIO, Preparation.filterMap(initWorkingNightsRatio));
    }};

    public static final Map<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(AVG_WORK_HOURS, avgWorkHours);
        put(AVG_REMAINED_WORK_HOURS, avgRemainedWorkHours);
        put(EMPLOYMENT_RATIO, employmentRatio);
        put(INACTIVE_POPULATION_RATIO, inactivePopulationRatio);
        put(INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatio);
        put(JOB_SATISFACTION, jobSatisfaction);
        put(LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatio);
        put(LOW_WAGE_EARNERS_RATIO, lowWageEarningsRatio);
        put(LOW_WORK_INTENSITY_RATIO, lowWorkIntensityRatio);
        put(RESEARCHERS_RATIO, researchersRatio);
        put(TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatio);
        put(UNEMPLOYMENT_RATIO, unemploymentRatio);
        put(WORKING_FLEXIBILITY_FULL_RATIO, flexibilityFullRatio);
        put(WORKING_FLEXIBILITY_RESTRICTIVE_RATIO, flexibilityRestrictiveRatio);
        put(WORKING_FLEXIBILITY_TOTAL_RATIO, flexibilityTotalRatio);
        put(WORKING_FLEXIBILITY_RATIO, workingFlexibilityRatio);
        put(WORKING_NIGHTS_RATIO, workingNightsRatio);
    }};

    public static Map<String, Number> generateStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, MAIN_ACTIVITY, AGGR_PARAMS, AGGR_REVERSED_STATE, preparedIndicators);
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, MAIN_ACTIVITY, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, MAIN_ACTIVITY, targetYear, indStatus);
    }

    // Extract the number of working hours left for a person from a legal maximum of 12 hours/day
    private static Map<String, Number> prepareAvgRemainedWorkHours() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                Number value = MAX_LEGAL_WORK_HOURS - avgWorkHours.get(key).doubleValue();
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }

    // Get the proportion of the population who has flexible time schedule
    private static Map<String, Number> prepareWorkingFlexibilityRatio() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double valueFull = flexibilityFullRatio.get(key).doubleValue();
                double valueRestrictive = flexibilityRestrictiveRatio.get(key).doubleValue();
                double valueTotal = flexibilityTotalRatio.get(key).doubleValue();

                Number value = (valueFull + valueRestrictive) / valueTotal * 100;
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
