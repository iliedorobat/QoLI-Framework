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

public class MainActivityStats {
    /** 12 hours * 7 days */
    private static final int MAX_LEGAL_WORK_HOURS = 12 * 7;

    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            avgWorkHours2007 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2007_PARAMS, AVG_WORK_HOURS_2007_PATH),
            avgWorkHours2008 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2008_PARAMS, AVG_WORK_HOURS_2008_PATH);
    private static final ArrayList<Map<String, Number>> avgWorkHoursList = new ArrayList<>();
    static {
        avgWorkHoursList.add(avgWorkHours2007);
        avgWorkHoursList.add(avgWorkHours2008);
    }

    private static final Map<String, Number>
            initAvgWorkHoursList = Initializer.initConsolidatedMaps(avgWorkHoursList),
            initEmploymentRatio = Initializer.initConsolidatedMap(EMPLOYMENT_RATIO_PARAMS, EMPLOYMENT_RATIO_PATH),
            initInactivePopulationRatio = Initializer.initConsolidatedMap(INACTIVE_POPULATION_RATIO_PARAMS, INACTIVE_POPULATION_RATIO_PATH),
            initInvoluntaryPartTimeRatio = Initializer.initConsolidatedMap(INVOLUNTARY_PART_TIME_RATIO_PARAMS, INVOLUNTARY_PART_TIME_RATIO_PATH),
            initJobSatisfaction = Initializer.initConsolidatedMap(JOB_SATISFACTION_PARAMS, JOB_SATISFACTION_PATH),
            initLongTermUnemploymentRatio = Initializer.initConsolidatedMap(LONG_TERM_UNEMPLOYMENT_RATIO_PARAMS, LONG_TERM_UNEMPLOYMENT_RATIO_PATH),
            initLowWageEarningsRatio = Initializer.initConsolidatedMap(LOW_WAGE_EARNINGS_RATIO_PARAMS, LOW_WAGE_EARNINGS_RATIO_PATH),
            initOverQualifiedRatio = Initializer.initConsolidatedMap(OVER_QUALIFIED_RATIO_PARAMS, OVER_QUALIFIED_RATIO_PATH),
            initResearchers = Initializer.initConsolidatedMap(RESEARCHERS_PARAMS, RESEARCHERS_PATH),
            initTemporaryEmploymentRatio = Initializer.initConsolidatedMap(TEMPORARY_EMPLOYMENT_RATIO_PARAMS, TEMPORARY_EMPLOYMENT_RATIO_PATH),
            initUnemploymentRatio = Initializer.initConsolidatedMap(UNEMPLOYMENT_RATIO_PARAMS, UNEMPLOYMENT_RATIO_PATH),
            initWorkingNightsRatio = Initializer.initConsolidatedMap(WORKING_NIGHTS_RATIO_PARAMS, WORKING_NIGHTS_RATIO_PATH);

    public static final Map<String, Number>
            // Intermediate data used to calculate avgRemainedWorkHours
            avgWorkHours = Preparation.prepareData(initAvgWorkHoursList),

            avgRemainedWorkHours = prepareAvgWorkHours(avgWorkHours),
            employmentRatio = Preparation.prepareData(initEmploymentRatio),
            inactivePopulationRatio = Preparation.prepareData(initInactivePopulationRatio),
            involuntaryPartTimeRatio = Preparation.prepareData(initInvoluntaryPartTimeRatio),
            jobSatisfaction = Preparation.prepareData(initJobSatisfaction),
            longTermUnemploymentRatio = Preparation.prepareData(initLongTermUnemploymentRatio),
            lowWageEarningsRatio = Preparation.prepareData(initLowWageEarningsRatio),
            overQualifiedRatio = Preparation.prepareData(initOverQualifiedRatio),
            researchersRatio = Preparation.preparePerTenThousandInhabitants(AuxiliaryStats.population, initResearchers),
            temporaryEmploymentRatio = Preparation.prepareData(initTemporaryEmploymentRatio),
            unemploymentRatio = Preparation.prepareData(initUnemploymentRatio),
            workingNightsRatio = Preparation.prepareData(initWorkingNightsRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(AVG_WORK_HOURS_FILE_NAME, Preparation.filterMap(initAvgWorkHoursList));
        put(EMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initEmploymentRatio));
        put(INACTIVE_POPULATION_RATIO_FILE_NAME, Preparation.filterMap(initInactivePopulationRatio));
        put(INVOLUNTARY_PART_TIME_RATIO_FILE_NAME, Preparation.filterMap(initInvoluntaryPartTimeRatio));
        put(JOB_SATISFACTION_FILE_NAME, Preparation.filterMap(initJobSatisfaction));
        put(LONG_TERM_UNEMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initLongTermUnemploymentRatio));
        put(LOW_WAGE_EARNERS_RATIO_FILE_NAME, Preparation.filterMap(initLowWageEarningsRatio));
        put(OVER_QUALIFIED_RATIO_FILE_NAME, Preparation.filterMap(initOverQualifiedRatio));
        put(RESEARCHERS_FILE_NAME, Preparation.filterMap(initResearchers));
        put(TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initTemporaryEmploymentRatio));
        put(UNEMPLOYMENT_RATIO_FILE_NAME, Preparation.filterMap(initUnemploymentRatio));
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
        put(OVER_QUALIFIED_RATIO_FILE_NAME, overQualifiedRatio);
        put(RESEARCHERS_FILE_NAME, researchersRatio);
        put(TEMPORARY_EMPLOYMENT_RATIO_FILE_NAME, temporaryEmploymentRatio);
        put(UNEMPLOYMENT_RATIO_FILE_NAME, unemploymentRatio);
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
                        * MathUtils.percentageSafetyDouble(overQualifiedRatio, key, true)
                        * MathUtils.percentageSafetyDouble(temporaryEmploymentRatio, key, true)
                        * MathUtils.percentageSafetyDouble(unemploymentRatio, key, true)
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

    /**
     * Extract the number of working hours left for a person from a legal maximum of 12 hours/day
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> prepareAvgWorkHours(Map<String, Number> workHours) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                Number value = MAX_LEGAL_WORK_HOURS - workHours.get(key).doubleValue();
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }
}
