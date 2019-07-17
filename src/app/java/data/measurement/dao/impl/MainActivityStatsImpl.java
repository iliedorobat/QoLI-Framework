package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.MainActivityStatsDAO;

import java.util.Map;

public class MainActivityStatsImpl implements MainActivityStatsDAO {
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

    private static final Map<String, Number>
            activePopulation = MeasureUtils.consolidateList(ACTIVE_POPULATION, activePopulationPath),
            avgWorkHours2007 = MeasureUtils.consolidateList(AVG_WORK_HOURS_2007, avgWorkHours2007Path),
            avgWorkHours2008 = MeasureUtils.consolidateList(AVG_WORK_HOURS_2008, avgWorkHours2008Path),
            employmentRatio = MeasureUtils.consolidateList(EMPLOYMENT_RATIO, employmentRatioPath),
            involuntaryPartTimeRatio = MeasureUtils.consolidateList(INVOLUNTARY_PART_TIME_RATIO, involuntaryPartTimeRatioPath),
            longTermUnemploymentRatio = MeasureUtils.consolidateList(LONG_TERM_UNEMPLOYMENT_RATIO, longTermUnemploymentRatioPath),
            nightsRatio = MeasureUtils.consolidateList(NIGHTS_RATIO, nightsRatioPath),
            overQualifiedRatio = MeasureUtils.consolidateList(OVER_QUALIFIED_RATIO, overQualifiedRatioPath),
            researchers = MeasureUtils.consolidateList(RESEARCHERS, researchersPath),
            temporaryEmploymentRatio = MeasureUtils.consolidateList(TEMPORARY_EMPLOYMENT_RATIO, temporaryEmploymentRatioPath),
            unemploymentRatio = MeasureUtils.consolidateList(UNEMPLOYMENT_RATIO, unemploymentRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(involuntaryPartTimeRatioPath);
    }
}
