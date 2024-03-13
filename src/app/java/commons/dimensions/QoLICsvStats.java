package app.java.commons.dimensions;

import app.java.commons.Print;
import app.java.commons.dimensions.education.EducationStats;
import app.java.commons.dimensions.environment.EnvironmentStats;
import app.java.commons.dimensions.gov.GovRightsStats;
import app.java.commons.dimensions.health.HealthStats;
import app.java.commons.dimensions.leisureInteract.LeisureInteractStats;
import app.java.commons.dimensions.mainActivity.MainActivityStats;
import app.java.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.commons.dimensions.overall.OverallExperienceStats;
import app.java.commons.dimensions.safety.SafetyStats;
import app.java.commons.utils.CsvStatsUtils;

import java.util.List;

import static app.java.commons.constants.Constants.*;
import static app.java.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static app.java.commons.dimensions.QoLIStats.DATA_BY_COUNTRIES;
import static app.java.commons.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static app.java.commons.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static app.java.commons.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static app.java.commons.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static app.java.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static app.java.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static app.java.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME;
import static app.java.commons.dimensions.safety.SafetyPaths.SAFETY_FILE_NAME;

public class QoLICsvStats {
    public static void printDimensions(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + QOLI_FILE_NAME)) {
            if (seriesType.equals(SERIES_TYPE_COUNTRY)) {
                printCountriesData(direction);
            } else if (seriesType.equals(SERIES_TYPE_REGION)) {
                printRegionsData(direction);
            }
        }
    }

    public static void writeDimensions(String direction, boolean calculateIndicators) {
        writeDataByCountries(direction, calculateIndicators);
        writeDataByRegions(direction, calculateIndicators);
    }

    private static void printCountriesData(String direction) {
        System.out.println("\n----------- " + SERIES_TYPE_COUNTRY +" DATA -----------\n");
        printData(EU28_MEMBERS, SERIES_TYPE_COUNTRY, direction);
        System.out.println("--------------------------------------");
    }

    private static void printRegionsData(String direction) {
        System.out.println("\n----------- " + SERIES_TYPE_REGION +" DATA -----------\n");
        printData(EU28_REGIONS, SERIES_TYPE_REGION, direction);
        System.out.println("--------------------------------------");
    }

    private static void writeDataByCountries(String direction, boolean calculateIndicators) {
        writeData(EU28_MEMBERS, SERIES_TYPE_COUNTRY, direction, calculateIndicators);
    }

    private static void writeDataByRegions(String direction, boolean calculateIndicators) {
        writeData(EU28_REGIONS, SERIES_TYPE_REGION, direction, calculateIndicators);
    }

    private static void printData(String[] membersList, String seriesType, String direction) {
        Print.printChartData(DATA_BY_COUNTRIES.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction);
        Print.printChartData(DATA_BY_COUNTRIES.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, direction);
    }

    private static void writeData(String[] membersList, String seriesType, String direction, boolean calculateIndicators) {
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, direction, null, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction, LeisureInteractStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(DATA_BY_COUNTRIES.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
