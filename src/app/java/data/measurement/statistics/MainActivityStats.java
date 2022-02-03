package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class MainActivityStats {
    /** 12 hours * 7 days */
    private static final int CORRECTION_WORKING_HOURS = 12 * 7;

    // The lists of queried values
    private static final String[]
            ACTIVE_POPULATION = {"Y15-64", "PC_POP", "T", "ACT"},
            AVG_WORK_HOURS_2007 = {"HR", "T", "FT", "EMP", "TOTAL"},
            AVG_WORK_HOURS_2008 = {"HR", "T", "FT", "EMP", "TOTAL"},
            EMPLOYMENT_RATIO = {"PC", "T", "Y15-64", "TOTAL"},
            INVOLUNTARY_PART_TIME_RATIO = {"PC", "T", "Y15-64"},
            LONG_TERM_UNEMPLOYMENT_RATIO = {"LTU", "Y15-74", "T", "PC_ACT"},
            OVER_QUALIFIED_RATIO = {"PC", "TOTAL", "TOTAL", "Y15-64", "T"},
            RESEARCHERS = {"TOTAL", "TOTAL", "T", "FTE"},
            TEMPORARY_EMPLOYMENT_RATIO = {"TEMP", "Y15-64", "PC_EMP", "T"},
            UNEMPLOYMENT_RATIO = {"PC", "T", "Y15-74", "TOTAL"},
            WORKING_NIGHTS_RATIO = {"PC", "T", "Y15-64", "EMP", "USU"};

    private static final String
            activePopulationPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.ACTIVE_POPULATION + JSON_EXTENSION,
            avgWorkHours2007Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2007 + JSON_EXTENSION,
            avgWorkHours2008Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2008 + JSON_EXTENSION,
            employmentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.EMPLOYMENT_RATIO + JSON_EXTENSION,
            involuntaryPartTimeRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.INVOLUNTARY_PART_TIME_RATIO + JSON_EXTENSION,
            longTermUnemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO + JSON_EXTENSION,
            overQualifiedRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.OVER_QUALIFIED_RATIO + JSON_EXTENSION,
            researchersPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.RESEARCHERS + JSON_EXTENSION,
            temporaryEmploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.TEMPORARY_EMPLOYMENT_RATIO + JSON_EXTENSION,
            unemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.UNEMPLOYMENT_RATIO + JSON_EXTENSION,
            workingNightsRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.WORKING_NIGHTS_RATIO + JSON_EXTENSION;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            avgWorkHours2007 = MeasureUtils.consolidateMap(AVG_WORK_HOURS_2007, avgWorkHours2007Path),
            avgWorkHours2008 = MeasureUtils.consolidateMap(AVG_WORK_HOURS_2008, avgWorkHours2008Path);
    private static final ArrayList<Map<String, Number>> avgWorkHoursList = new ArrayList<>();
    static {
        avgWorkHoursList.add(avgWorkHours2007);
        avgWorkHoursList.add(avgWorkHours2008);
    }

    private static final Map<String, Number>
            initActivePopulation = Initializer.initConsolidatedMap(ACTIVE_POPULATION, activePopulationPath),
            initAvgWorkHoursList = Initializer.initConsolidatedMaps(avgWorkHoursList),
            initEmploymentRatio = Initializer.initConsolidatedMap(EMPLOYMENT_RATIO, employmentRatioPath),
            initInvoluntaryPartTimeRatio = Initializer.initConsolidatedMap(INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatioPath),
            initLongTermUnemploymentRatio = Initializer.initConsolidatedMap(LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatioPath),
            initOverQualifiedRatio = Initializer.initConsolidatedMap(OVER_QUALIFIED_RATIO, overQualifiedRatioPath),
            initResearchers = Initializer.initConsolidatedMap(RESEARCHERS, researchersPath),
            initTemporaryEmploymentRatio = Initializer.initConsolidatedMap(TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatioPath),
            initUnemploymentRatio = Initializer.initConsolidatedMap(UNEMPLOYMENT_RATIO, unemploymentRatioPath),
            initWorkingNightsRatio = Initializer.initConsolidatedMap(WORKING_NIGHTS_RATIO, workingNightsRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                activePopulation = Preparation.prepareData(initActivePopulation), // not used
                avgWorkHours = Preparation.prepareData(initAvgWorkHoursList),
                employmentRatio = Preparation.prepareData(initEmploymentRatio),
                involuntaryPartTimeRatio = Preparation.prepareData(initInvoluntaryPartTimeRatio),
                longTermUnemploymentRatio = Preparation.prepareData(initLongTermUnemploymentRatio),
                overQualifiedRatio = Preparation.prepareData(initOverQualifiedRatio), // no data
                researchersRatio = Preparation.preparePerTenThousandInhabitants(initResearchers),
                temporaryEmploymentRatio = Preparation.prepareData(initTemporaryEmploymentRatio),
                unemploymentRatio = Preparation.prepareData(initUnemploymentRatio),
                workingNightsRatio = Preparation.prepareData(initWorkingNightsRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double correctedAvgWorkHours = CORRECTION_WORKING_HOURS - avgWorkHours.get(key).doubleValue(),
                        reversedInvoluntaryPartTimeRatio = MathUtils.percentageReverseRatio(involuntaryPartTimeRatio, key),
                        reversedLongTermUnemploymentRatio = MathUtils.percentageReverseRatio(longTermUnemploymentRatio, key),
                        reversedTemporaryEmploymentRatio = MathUtils.percentageReverseRatio(temporaryEmploymentRatio, key),
                        reversedUnemploymentRatio = MathUtils.percentageReverseRatio(unemploymentRatio, key),
                        reversedWorkingNightsRatio = MathUtils.percentageReverseRatio(workingNightsRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(correctedAvgWorkHours)
                        * MathUtils.percentageSafetyDouble(employmentRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedInvoluntaryPartTimeRatio)
                        * MathUtils.percentageSafetyDouble(reversedLongTermUnemploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedTemporaryEmploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnemploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedWorkingNightsRatio)
                        * MathUtils.percentageSafetyDouble(researchersRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(pupilsRatio, true));
//        Print.print(initPupilsRatio, false);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        //TODO: initActivePopulation and initOverQualifiedRatio are not used
        return new ArrayList<>() {{
            add(Preparation.filterMap(initAvgWorkHoursList));
            add(Preparation.filterMap(initEmploymentRatio));
            add(Preparation.filterMap(initInvoluntaryPartTimeRatio));
            add(Preparation.filterMap(initLongTermUnemploymentRatio));
            add(Preparation.filterMap(initWorkingNightsRatio));
            add(Preparation.filterMap(initResearchers));
            add(Preparation.filterMap(initTemporaryEmploymentRatio));
            add(Preparation.filterMap(initUnemploymentRatio));
        }};
    }
}
