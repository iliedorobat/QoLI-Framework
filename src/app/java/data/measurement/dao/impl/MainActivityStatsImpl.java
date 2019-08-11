package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.MathUtils;
import app.java.commons.Print;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.GeneralStats;
import app.java.data.measurement.dao.MainActivityStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MainActivityStatsImpl implements MainActivityStatsDAO {
    /**
     * 12 hours * 7 days
     */
    private static final int CORRECTION_WORKING_HOURS = 12 * 7;

    // The lists of queried values
    private static final String[]
            ACTIVE_POPULATION = {"Y15-64", "PC_POP", "T", "ACT"},
            AVG_WORK_HOURS_2007 = {"HR", "T", "FT", "EMP", "TOTAL"},
            AVG_WORK_HOURS_2008 = {"HR", "T", "FT", "EMP", "TOTAL"},
            EMPLOYMENT_RATIO = {"PC", "T", "Y15-64", "TOTAL"},
            INVOLUNTARY_PART_TIME_RATIO = {"PC", "T", "Y15-64"},
            LONG_TERM_UNEMPLOYMENT_RATIO = {"LTU", "Y15-74", "T", "PC_ACT"},
            NIGHTS_RATIO = {"PC", "T", "Y15-64", "EMP", "USU"},
            OVER_QUALIFIED_RATIO = {"PC", "TOTAL", "TOTAL", "Y15-64", "T"},
            RESEARCHERS = {"TOTAL", "TOTAL", "T", "FTE"},
            TEMPORARY_EMPLOYMENT_RATIO = {"TEMP", "Y15-64", "PC_EMP", "T"},
            UNEMPLOYMENT_RATIO = {"PC", "T", "Y15-74", "TOTAL"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            activePopulationPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.ACTIVE_POPULATION + JSON_EXT,
            avgWorkHours2007Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2007 + JSON_EXT,
            avgWorkHours2008Path = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.AVG_WORK_HOURS_2008 + JSON_EXT,
            employmentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.EMPLOYMENT_RATIO + JSON_EXT,
            involuntaryPartTimeRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.INVOLUNTARY_PART_TIME_RATIO + JSON_EXT,
            longTermUnemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.LONG_TERM_UNEMPLOYMENT_RATIO + JSON_EXT,
            nightsRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.NIGHTS_RATIO + JSON_EXT,
            overQualifiedRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.OVER_QUALIFIED_RATIO + JSON_EXT,
            researchersPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.RESEARCHERS + JSON_EXT,
            temporaryEmploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.TEMPORARY_EMPLOYMENT_RATIO + JSON_EXT,
            unemploymentRatioPath = FilePathConst.MAIN_ACTIVITY_PATH + FileNameConst.UNEMPLOYMENT_RATIO + JSON_EXT;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            avgWorkHours2007 = MeasureUtils.consolidateList(AVG_WORK_HOURS_2007, avgWorkHours2007Path),
            avgWorkHours2008 = MeasureUtils.consolidateList(AVG_WORK_HOURS_2008, avgWorkHours2008Path);
    private static final ArrayList<Map<String, Number>> avgWorkHoursList = new ArrayList<>();
    static {
        avgWorkHoursList.add(avgWorkHours2007);
        avgWorkHoursList.add(avgWorkHours2008);
    }

    private static final Map<String, Number>
            initActivePopulation = Initializer.initConsolidatedList(ACTIVE_POPULATION, activePopulationPath),
            initAvgWorkHoursList = Initializer.initConsolidatedMaps(avgWorkHoursList),
            initEmploymentRatio = Initializer.initConsolidatedList(EMPLOYMENT_RATIO, employmentRatioPath),
            initInvoluntaryPartTimeRatio = Initializer.initConsolidatedList(INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatioPath),
            initLongTermUnemploymentRatio = Initializer.initConsolidatedList(LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatioPath),
            initNightsRatio = Initializer.initConsolidatedList(NIGHTS_RATIO, nightsRatioPath),
            initOverQualifiedRatio = Initializer.initConsolidatedList(OVER_QUALIFIED_RATIO, overQualifiedRatioPath),
            initResearchers = Initializer.initConsolidatedList(RESEARCHERS, researchersPath),
            initTemporaryEmploymentRatio = Initializer.initConsolidatedList(TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatioPath),
            initUnemploymentRatio = Initializer.initConsolidatedList(UNEMPLOYMENT_RATIO, unemploymentRatioPath);

    public Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                activePopulation = Preparation.prepareData(initActivePopulation), // not used
                avgWorkHours = Preparation.prepareData(initAvgWorkHoursList),
                employmentRatio = Preparation.prepareData(initEmploymentRatio),
                involuntaryPartTimeRatio = Preparation.prepareData(initInvoluntaryPartTimeRatio),
                longTermUnemploymentRatio = Preparation.prepareData(initLongTermUnemploymentRatio),
                nightsRatio = Preparation.prepareData(initNightsRatio),
                overQualifiedRatio = Preparation.prepareData(initOverQualifiedRatio), // no data
                researchersRatio = getResearcherRatio(),
                temporaryEmploymentRatio = Preparation.prepareData(initTemporaryEmploymentRatio),
                unemploymentRatio = Preparation.prepareData(initUnemploymentRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double correctedAvgWorkHours = CORRECTION_WORKING_HOURS - avgWorkHours.get(key).doubleValue(),
                        reversedInvoluntaryPartTimeRatio = MathUtils.percentageReverseRatio(involuntaryPartTimeRatio, key),
                        reversedLongTermUnemploymentRatio = MathUtils.percentageReverseRatio(longTermUnemploymentRatio, key),
                        reversedNightsRatio = MathUtils.percentageReverseRatio(nightsRatio, key),
                        reversedTemporaryEmploymentRatio = MathUtils.percentageReverseRatio(temporaryEmploymentRatio, key),
                        reversedUnemploymentRatio = MathUtils.percentageReverseRatio(unemploymentRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(correctedAvgWorkHours)
                        * MathUtils.percentageSafetyDouble(employmentRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedInvoluntaryPartTimeRatio)
                        * MathUtils.percentageSafetyDouble(reversedLongTermUnemploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedNightsRatio)
                        * MathUtils.percentageSafetyDouble(researchersRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedTemporaryEmploymentRatio)
                        * MathUtils.percentageSafetyDouble(reversedUnemploymentRatio);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(pupilsRatio, true));
//        Print.print(initPupilsRatio, false);

        return consolidatedList;
    }

    /**
     * Transform the number of researchers into researchers ratio indicator
     * (the number of researchers per ten thousand inhabitant)
     *
     * @return An ordered map with aggregated data
     */
    private static Map<String, Number> getResearcherRatio() {
        Map<String, Number> researchersRatio = new TreeMap<>(new MapOrder());
        Map<String, Number> researchersMap = Preparation.prepareData(initResearchers);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double researchers = researchersMap.get(key).doubleValue();

                Number value = MathUtils.generateTenThousandPerInhabitant(key, researchers);
                researchersRatio.put(key, value);
            }
        }

        return researchersRatio;
    }
}
