package app.java.commons.dimensions.mainActivity;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.dimensions.common.CommonStats;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.MergeUtils;
import app.java.data.stats.Preparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.mainActivity.MainActivityParams.*;
import static app.java.commons.dimensions.mainActivity.MainActivityPaths.*;

public class MainActivityStats {
    /** 12 hours * 7 days */
    private static final int CORRECTION_WORKING_HOURS = 12 * 7;

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
            avgWorkHours = Preparation.prepareData(initAvgWorkHoursList),
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

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        correctedAvgWorkHours = CORRECTION_WORKING_HOURS - avgWorkHours.get(key).doubleValue(),
                        reversedInactivePopulationRatio = MathUtils.percentageReverseRatio(inactivePopulationRatio, key),
                        reversedInvoluntaryPartTimeRatio = MathUtils.percentageReverseRatio(involuntaryPartTimeRatio, key),
                        reversedLongTermUnemploymentRatio = MathUtils.percentageReverseRatio(longTermUnemploymentRatio, key),
                        reversedLowWageEarningsRatio = MathUtils.percentageReverseRatio(lowWageEarningsRatio, key),
                        reversedOverQualifiedRatio = MathUtils.percentageReverseRatio(overQualifiedRatio, key),
                        reversedTemporaryEmploymentRatio = MathUtils.percentageReverseRatio(temporaryEmploymentRatio, key),
                        reversedUnemploymentRatio = MathUtils.percentageReverseRatio(unemploymentRatio, key),
                        reversedWorkingNightsRatio = MathUtils.percentageReverseRatio(workingNightsRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(correctedAvgWorkHours)
                        * MathUtils.percentageSafetyDouble(employmentRatio, key)
                        * MathUtils.percentageSafetyDouble(jobSatisfaction, key)
                        * MathUtils.percentageSafetyDouble(researchersRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedInactivePopulationRatio)
                        * MathUtils.percentageSafetyDouble(reversedInvoluntaryPartTimeRatio)
                        * MathUtils.percentageSafetyDouble(reversedLongTermUnemploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedLowWageEarningsRatio)
                        * MathUtils.percentageSafetyDouble(reversedOverQualifiedRatio)
                        * MathUtils.percentageSafetyDouble(reversedTemporaryEmploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnemploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedWorkingNightsRatio);

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
        if (args.contains("--dimension=" + DimensionNames.MAIN_ACTIVITY)) {
            if (args.contains("--indicator=" + IndicatorNames.AVG_WORK_HOURS))
                Print.printChartData(avgWorkHours, EU28_MEMBERS, seriesType, IndicatorNames.AVG_WORK_HOURS, direction);

            if (args.contains("--indicator=" + IndicatorNames.EMPLOYMENT_RATIO))
                Print.printChartData(employmentRatio, EU28_MEMBERS, seriesType, IndicatorNames.EMPLOYMENT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.INACTIVE_POPULATION_RATIO))
                Print.printChartData(inactivePopulationRatio, EU28_MEMBERS, seriesType, IndicatorNames.INACTIVE_POPULATION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.INVOLUNTARY_PART_TIME_RATIO))
                Print.printChartData(involuntaryPartTimeRatio, EU28_MEMBERS, seriesType, IndicatorNames.INVOLUNTARY_PART_TIME_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.JOB_SATISFACTION))
                Print.printChartData(jobSatisfaction, EU28_MEMBERS, seriesType, IndicatorNames.JOB_SATISFACTION, direction);

            if (args.contains("--indicator=" + IndicatorNames.LONG_TERM_UNEMPLOYMENT_RATIO))
                Print.printChartData(longTermUnemploymentRatio, EU28_MEMBERS, seriesType, IndicatorNames.LONG_TERM_UNEMPLOYMENT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.LOW_WAGE_EARNINGS_RATIO))
                Print.printChartData(lowWageEarningsRatio, EU28_MEMBERS, seriesType, IndicatorNames.LOW_WAGE_EARNINGS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.OVER_QUALIFIED_RATIO))
                Print.printChartData(overQualifiedRatio, EU28_MEMBERS, seriesType, IndicatorNames.OVER_QUALIFIED_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.RESEARCHERS_RATIO))
                Print.printChartData(researchersRatio, EU28_MEMBERS, seriesType, IndicatorNames.RESEARCHERS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.TEMPORARY_EMPLOYMENT_RATIO))
                Print.printChartData(temporaryEmploymentRatio, EU28_MEMBERS, seriesType, IndicatorNames.TEMPORARY_EMPLOYMENT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.UNEMPLOYMENT_RATIO))
                Print.printChartData(unemploymentRatio, EU28_MEMBERS, seriesType, IndicatorNames.UNEMPLOYMENT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.WORKING_NIGHTS_RATIO))
                Print.printChartData(workingNightsRatio, EU28_MEMBERS, seriesType, IndicatorNames.WORKING_NIGHTS_RATIO, direction);
        }
    }
}
