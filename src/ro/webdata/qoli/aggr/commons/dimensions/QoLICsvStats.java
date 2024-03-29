package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.Print;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.commons.utils.CsvStatsUtils;
import ro.webdata.qoli.aggr.commons.constants.Constants;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationPaths;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentPaths;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsPaths;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthPaths;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperiencePaths;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyPaths;

import java.util.List;

import static ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;

public class QoLICsvStats {
    public static void printDimensions(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + QoLIPaths.QOLI_FILE_NAME)) {
            if (seriesType.equals(Constants.SERIES_TYPE_COUNTRY)) {
                printCountriesData(direction);
            } else if (seriesType.equals(Constants.SERIES_TYPE_REGION)) {
                printRegionsData(direction);
            }
        }
    }

    public static void writeDimensions(String direction, boolean calculateIndicators) {
        writeDataByCountries(direction, calculateIndicators);
        writeDataByRegions(direction, calculateIndicators);
    }

    private static void printCountriesData(String direction) {
        System.out.println("\n----------- " + Constants.SERIES_TYPE_COUNTRY +" DATA -----------\n");
        printData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, direction);
        System.out.println("--------------------------------------");
    }

    private static void printRegionsData(String direction) {
        System.out.println("\n----------- " + Constants.SERIES_TYPE_REGION +" DATA -----------\n");
        printData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, direction);
        System.out.println("--------------------------------------");
    }

    private static void writeDataByCountries(String direction, boolean calculateIndicators) {
        writeData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, direction, calculateIndicators);
    }

    private static void writeDataByRegions(String direction, boolean calculateIndicators) {
        writeData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, direction, calculateIndicators);
    }

    private static void printData(String[] membersList, String seriesType, String direction) {
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(QoLIPaths.QOLI_FILE_NAME), membersList, seriesType, QoLIPaths.QOLI_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(EducationPaths.EDUCATION_FILE_NAME), membersList, seriesType, EducationPaths.EDUCATION_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(EnvironmentPaths.ENVIRONMENT_FILE_NAME), membersList, seriesType, EnvironmentPaths.ENVIRONMENT_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(GovRightsPaths.GOVERNANCE_FILE_NAME), membersList, seriesType, GovRightsPaths.GOVERNANCE_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(HealthPaths.HEALTH_FILE_NAME), membersList, seriesType, HealthPaths.HEALTH_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, direction);
        Print.printChartData(QoLIStats.DATA_BY_COUNTRIES.get(SafetyPaths.SAFETY_FILE_NAME), membersList, seriesType, SafetyPaths.SAFETY_FILE_NAME, direction);
    }

    private static void writeData(String[] membersList, String seriesType, String direction, boolean calculateIndicators) {
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(QoLIPaths.QOLI_FILE_NAME), membersList, seriesType, QoLIPaths.QOLI_FILE_NAME, direction, null, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(EducationPaths.EDUCATION_FILE_NAME), membersList, seriesType, EducationPaths.EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(EnvironmentPaths.ENVIRONMENT_FILE_NAME), membersList, seriesType, EnvironmentPaths.ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(GovRightsPaths.GOVERNANCE_FILE_NAME), membersList, seriesType, GovRightsPaths.GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(HealthPaths.HEALTH_FILE_NAME), membersList, seriesType, HealthPaths.HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction, LeisureInteractStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(QoLIStats.DATA_BY_COUNTRIES.get(SafetyPaths.SAFETY_FILE_NAME), membersList, seriesType, SafetyPaths.SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
