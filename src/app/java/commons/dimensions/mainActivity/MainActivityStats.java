package app.java.commons.dimensions.mainActivity;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.dimensions.auxiliary.AuxiliaryStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.MergeUtils;
import app.java.data.stats.Preparation;

import java.util.*;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.mainActivity.MainActivityParams.*;
import static app.java.commons.dimensions.mainActivity.MainActivityPaths.*;
import static app.java.commons.dimensions.materialLiving.MaterialLivingPaths.LOW_WORK_INTENSITY_RATIO_FILE_NAME;

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

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(AVG_WORK_HOURS_FILE_NAME, Preparation.filterMap(initAvgWorkHoursList));
        put(EMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initEmploymentRatio));
        put(INACTIVE_POPULATION_RATIO_FILE_NAME, Preparation.filterMap(initInactivePopulationRatio));
        put(INVOLUNTARY_PART_TIME_RATIO_FILE_NAME, Preparation.filterMap(initInvoluntaryPartTimeRatio));
        put(JOB_SATISFACTION_FILE_NAME, Preparation.filterMap(initJobSatisfaction));
        put(LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initLongTermUnemploymentRatio));
        put(LOW_WAGE_EARNERS_RATIO_FILE_NAME, Preparation.filterMap(initLowWageEarningsRatio));
        put(LOW_WORK_INTENSITY_RATIO_FILE_NAME, Preparation.filterMap(initLowWorkIntensityRatio));
        put(RESEARCHERS_FILE_NAME, Preparation.filterMap(initResearchers));
        put(TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initTemporaryEmploymentRatio));
        put(UNEMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initUnemploymentRatio));
        put(WORKING_FLEXIBILITY_FULL_RATIO_FILE_NAME, Preparation.filterMap(initFlexibilityFullRatio));
        put(WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_FILE_NAME, Preparation.filterMap(initFlexibilityRestrictiveRatio));
        put(WORKING_FLEXIBILITY_TOTAL_RATIO_FILE_NAME, Preparation.filterMap(initFlexibilityTotalRatio));
        put(WORKING_NIGHTS_RATIO_FILE_NAME, Preparation.filterMap(initWorkingNightsRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(AVG_WORK_HOURS_FILE_NAME, avgWorkHours);
        put(AVG_REMAINED_WORK_HOURS_FILE_NAME, avgRemainedWorkHours);
        put(EMPLOYMENT_RATIO_FILE_NAME, employmentRatio);
        put(INACTIVE_POPULATION_RATIO_FILE_NAME, inactivePopulationRatio);
        put(INVOLUNTARY_PART_TIME_RATIO_FILE_NAME, involuntaryPartTimeRatio);
        put(JOB_SATISFACTION_FILE_NAME, jobSatisfaction);
        put(LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME, longTermUnemploymentRatio);
        put(LOW_WAGE_EARNERS_RATIO_FILE_NAME, lowWageEarningsRatio);
        put(LOW_WORK_INTENSITY_RATIO_FILE_NAME, lowWorkIntensityRatio);
        put(RESEARCHERS_FILE_NAME, researchersRatio);
        put(TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME, temporaryEmploymentRatio);
        put(UNEMPLOYMENT_RATIO_FILE_NAME, unemploymentRatio);
        put(WORKING_FLEXIBILITY_FULL_RATIO_FILE_NAME, flexibilityFullRatio);
        put(WORKING_FLEXIBILITY_RESTRICTIVE_RATIO_FILE_NAME, flexibilityRestrictiveRatio);
        put(WORKING_FLEXIBILITY_TOTAL_RATIO_FILE_NAME, flexibilityTotalRatio);
        put(WORKING_FLEXIBILITY_RATIO_FILE_NAME, workingFlexibilityRatio);
        put(WORKING_NIGHTS_RATIO_FILE_NAME, workingNightsRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(avgRemainedWorkHours, key)
                        * MathUtils.percentageSafetyDouble(employmentRatio, key)
                        * MathUtils.percentageSafetyDouble(jobSatisfaction, key)
                        * MathUtils.percentageSafetyDouble(researchersRatio, key)
                        * MathUtils.percentageSafetyDouble(inactivePopulationRatio, key, true)
                        * MathUtils.percentageSafetyDouble(involuntaryPartTimeRatio, key, true)
                        * MathUtils.percentageSafetyDouble(longTermUnemploymentRatio, key, true)
                        * MathUtils.percentageSafetyDouble(lowWageEarningsRatio, key, true)
                        * MathUtils.percentageSafetyDouble(lowWorkIntensityRatio, key, true)
                        * MathUtils.percentageSafetyDouble(temporaryEmploymentRatio, key, true)
                        * MathUtils.percentageSafetyDouble(unemploymentRatio, key, true)
                        * MathUtils.percentageSafetyDouble(workingFlexibilityRatio, key) // TODO: check
                        * MathUtils.percentageSafetyDouble(workingNightsRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(pupilsRatio, true));
//        Print.print(initPupilsRatio, false);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, MAIN_ACTIVITY_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, MAIN_ACTIVITY_FILE_NAME, targetYear, indStatus);
    }

    /**
     * Extract the number of working hours left for a person from a legal maximum of 12 hours/day
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareAvgRemainedWorkHours() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                Number value = MAX_LEGAL_WORK_HOURS - avgWorkHours.get(key).doubleValue();
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }

    /**
     * Get the proportion of the population who has flexible time schedule
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareWorkingFlexibilityRatio() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
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
