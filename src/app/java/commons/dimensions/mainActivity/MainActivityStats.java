package app.java.commons.dimensions.mainActivity;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.common.CommonStats;
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
            researchersRatio = Preparation.preparePerTenThousandInhabitants(CommonStats.population, initResearchers),
            temporaryEmploymentRatio = Preparation.prepareData(initTemporaryEmploymentRatio),
            unemploymentRatio = Preparation.prepareData(initUnemploymentRatio),
            workingNightsRatio = Preparation.prepareData(initWorkingNightsRatio);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("avgRemainedWorkHours", avgRemainedWorkHours);
        put("employmentRatio", employmentRatio);
        put("inactivePopulationRatio", inactivePopulationRatio);
        put("involuntaryPartTimeRatio", involuntaryPartTimeRatio);
        put("jobSatisfaction", jobSatisfaction);
        put("longTermUnemploymentRatio", longTermUnemploymentRatio);
        put("lowWageEarningsRatio", lowWageEarningsRatio);
        put("overQualifiedRatio", overQualifiedRatio);
        put("researchersRatio", researchersRatio);
        put("temporaryEmploymentRatio", temporaryEmploymentRatio);
        put("unemploymentRatio", unemploymentRatio);
        put("workingNightsRatio", workingNightsRatio);
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Avg Work Hours List", Preparation.filterMap(initAvgWorkHoursList));
            put("Employment Ratio", Preparation.filterMap(initEmploymentRatio));
            put("Inactive Population Ratio", Preparation.filterMap(initInactivePopulationRatio));
            put("Involuntary Part-Time Ratio", Preparation.filterMap(initInvoluntaryPartTimeRatio));
            put("Job Satisfaction", Preparation.filterMap(initJobSatisfaction));
            put("Long Term Unemployment Ratio", Preparation.filterMap(initLongTermUnemploymentRatio));
            put("LowWage Earnings Ratio", Preparation.filterMap(initLowWageEarningsRatio));
            put("Over Qualified Ratio", Preparation.filterMap(initOverQualifiedRatio));
            put("Researchers", Preparation.filterMap(initResearchers));
            put("Temporary Employment Ratio", Preparation.filterMap(initTemporaryEmploymentRatio));
            put("Unemployment Ratio", Preparation.filterMap(initUnemploymentRatio));
            put("Working Nights Ratio", Preparation.filterMap(initWorkingNightsRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.AVG_WORK_HOURS, avgWorkHours);
            put(IndicatorNames.EMPLOYMENT_RATIO, employmentRatio);
            put(IndicatorNames.INACTIVE_POPULATION_RATIO, inactivePopulationRatio);
            put(IndicatorNames.INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatio);
            put(IndicatorNames.JOB_SATISFACTION, jobSatisfaction);
            put(IndicatorNames.LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatio);
            put(IndicatorNames.LOW_WAGE_EARNINGS_RATIO, lowWageEarningsRatio);
            put(IndicatorNames.OVER_QUALIFIED_RATIO, overQualifiedRatio);
            put(IndicatorNames.RESEARCHERS_RATIO, researchersRatio);
            put(IndicatorNames.TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatio);
            put(IndicatorNames.UNEMPLOYMENT_RATIO, unemploymentRatio);
            put(IndicatorNames.WORKING_NIGHTS_RATIO, workingNightsRatio);
        }};

        Print.printChartData(args, indicators, FilePathConst.MAIN_ACTIVITY_DIR, EU28_MEMBERS, seriesType, direction);
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
