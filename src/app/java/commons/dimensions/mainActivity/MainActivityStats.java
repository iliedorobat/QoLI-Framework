package app.java.commons.dimensions.mainActivity;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.dimensions.common.CommonStats;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.MergeUtils;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class MainActivityStats {
    /** 12 hours * 7 days */
    private static final int CORRECTION_WORKING_HOURS = 12 * 7;

    // Queried params values
    private static final MultiValuedMap<String, String>
            AVG_WORK_HOURS_2007 = MainActivityParams.getAvgWorkHoursParams2007(),
            AVG_WORK_HOURS_2008 = MainActivityParams.getAvgWorkHoursParams2008(),
            EMPLOYMENT_RATIO = MainActivityParams.getEmploymentParams(),
            INACTIVE_POPULATION_RATIO = MainActivityParams.getInactivePopulationParams(),
            INVOLUNTARY_PART_TIME_RATIO = MainActivityParams.getInvoluntaryPartTimeParams(),
            JOB_SATISFACTION = MainActivityParams.getJobSatisfactionParams(),
            LONG_TERM_UNEMPLOYMENT_RATIO = MainActivityParams.getLongTermUnemploymentParams(),
            LOW_WAGE_EARNINGS_RATIO = MainActivityParams.getLowWageEarnersParams(),
            OVER_QUALIFIED_RATIO = MainActivityParams.getOverQualifiedParams(),
            RESEARCHERS = MainActivityParams.getResearchersParams(),
            TEMPORARY_EMPLOYMENT_RATIO = MainActivityParams.getTemporaryEmploymentParams(),
            UNEMPLOYMENT_RATIO = MainActivityParams.getUnemploymentParams(),
            WORKING_NIGHTS_RATIO = MainActivityParams.getWorkingNightsParams();

    private static final String
            avgWorkHours2007Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2007 + JSON_EXTENSION,
            avgWorkHours2008Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2008 + JSON_EXTENSION,
            employmentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.EMPLOYMENT_RATIO + JSON_EXTENSION,
            inactivePopulationRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.INACTIVE_POPULATION_RATIO + JSON_EXTENSION,
            involuntaryPartTimeRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.INVOLUNTARY_PART_TIME_RATIO + JSON_EXTENSION,
            jobSatisfactionPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.JOB_SATISFACTION + JSON_EXTENSION,
            longTermUnemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO + JSON_EXTENSION,
            lowWageEarningsRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.LOW_WAGE_EARNERS_RATIO + JSON_EXTENSION,
            overQualifiedRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.OVER_QUALIFIED_RATIO + JSON_EXTENSION,
            researchersPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.RESEARCHERS + JSON_EXTENSION,
            temporaryEmploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.TEMPORARY_EMPLOYMENT_RATIO + JSON_EXTENSION,
            unemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.UNEMPLOYMENT_RATIO + JSON_EXTENSION,
            workingNightsRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.WORKING_NIGHTS_RATIO + JSON_EXTENSION;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            avgWorkHours2007 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2007, avgWorkHours2007Path),
            avgWorkHours2008 = MergeUtils.consolidateMap(AVG_WORK_HOURS_2008, avgWorkHours2008Path);
    private static final ArrayList<Map<String, Number>> avgWorkHoursList = new ArrayList<>();
    static {
        avgWorkHoursList.add(avgWorkHours2007);
        avgWorkHoursList.add(avgWorkHours2008);
    }

    private static final Map<String, Number>
            initAvgWorkHoursList = Initializer.initConsolidatedMaps(avgWorkHoursList),
            initEmploymentRatio = Initializer.initConsolidatedMap(EMPLOYMENT_RATIO, employmentRatioPath),
            initInactivePopulationRatio = Initializer.initConsolidatedMap(INACTIVE_POPULATION_RATIO, inactivePopulationRatioPath),
            initInvoluntaryPartTimeRatio = Initializer.initConsolidatedMap(INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatioPath),
            initJobSatisfaction = Initializer.initConsolidatedMap(JOB_SATISFACTION, jobSatisfactionPath),
            initLongTermUnemploymentRatio = Initializer.initConsolidatedMap(LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatioPath),
            initLowWageEarningsRatio = Initializer.initConsolidatedMap(LOW_WAGE_EARNINGS_RATIO, lowWageEarningsRatioPath),
            initOverQualifiedRatio = Initializer.initConsolidatedMap(OVER_QUALIFIED_RATIO, overQualifiedRatioPath),
            initResearchers = Initializer.initConsolidatedMap(RESEARCHERS, researchersPath),
            initTemporaryEmploymentRatio = Initializer.initConsolidatedMap(TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatioPath),
            initUnemploymentRatio = Initializer.initConsolidatedMap(UNEMPLOYMENT_RATIO, unemploymentRatioPath),
            initWorkingNightsRatio = Initializer.initConsolidatedMap(WORKING_NIGHTS_RATIO, workingNightsRatioPath);

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

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initAvgWorkHoursList));
            add(Preparation.filterMap(initEmploymentRatio));
            add(Preparation.filterMap(initInactivePopulationRatio));
            add(Preparation.filterMap(initInvoluntaryPartTimeRatio));
            add(Preparation.filterMap(initJobSatisfaction));
            add(Preparation.filterMap(initLongTermUnemploymentRatio));
            add(Preparation.filterMap(initLowWageEarningsRatio));
            add(Preparation.filterMap(initOverQualifiedRatio));
            add(Preparation.filterMap(initResearchers));
            add(Preparation.filterMap(initTemporaryEmploymentRatio));
            add(Preparation.filterMap(initUnemploymentRatio));
            add(Preparation.filterMap(initWorkingNightsRatio));
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
