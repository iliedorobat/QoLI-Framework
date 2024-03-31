package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.Print;
import ro.webdata.qoli.aggr.commons.constants.Constants;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.commons.dimensions.QoLIAggrParams.*;
import static ro.webdata.qoli.aggr.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyPaths.SAFETY_FILE_NAME;

public class QoLICsvStats {
    public static void printDimensions(List<String> args, String seriesType, HashMap<String, Map<String, Number>> dataByCountries, String direction) {
        if (args.contains("--dimension=" + QOLI_FILE_NAME)) {
            if (seriesType.equals(Constants.SERIES_TYPE_COUNTRY)) {
                printCountriesData(dataByCountries, direction);
            } else if (seriesType.equals(Constants.SERIES_TYPE_REGION)) {
                printRegionsData(dataByCountries, direction);
            }
        }
    }

    public static void writeDimensions(HashMap<String, Map<String, Number>> dataByCountries, String direction, boolean calculateIndicators) {
        writeDataByCountries(dataByCountries, direction, calculateIndicators);
        writeDataByRegions(dataByCountries, direction, calculateIndicators);
    }

    private static void printCountriesData(HashMap<String, Map<String, Number>> dataByCountries, String direction) {
        System.out.println("\n----------- " + Constants.SERIES_TYPE_COUNTRY +" DATA -----------\n");
        printData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, dataByCountries, direction);
        System.out.println("--------------------------------------");
    }

    private static void printRegionsData(HashMap<String, Map<String, Number>> dataByCountries, String direction) {
        System.out.println("\n----------- " + Constants.SERIES_TYPE_REGION +" DATA -----------\n");
        printData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, dataByCountries, direction);
        System.out.println("--------------------------------------");
    }

    private static void writeDataByCountries(HashMap<String, Map<String, Number>> dataByCountries, String direction, boolean calculateIndicators) {
        writeData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, dataByCountries, direction, calculateIndicators);
    }

    private static void writeDataByRegions(HashMap<String, Map<String, Number>> dataByCountries, String direction, boolean calculateIndicators) {
        writeData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, dataByCountries, direction, calculateIndicators);
    }

    private static void printData(String[] membersList, String seriesType, HashMap<String, Map<String, Number>> dataByCountries, String direction) {
        Print.printChartData(dataByCountries.get(QOLI), membersList, seriesType, QOLI_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(EDUCATION), membersList, seriesType, EDUCATION_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(ENVIRONMENT), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(GOVERNANCE), membersList, seriesType, GOVERNANCE_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(HEALTH), membersList, seriesType, HEALTH_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(LEISURE_INTERACT), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(LIVING_CONDITIONS), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(MAIN_ACTIVITY), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(OVERALL_EXPERIENCE), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction);
        Print.printChartData(dataByCountries.get(SAFETY), membersList, seriesType, SAFETY_FILE_NAME, direction);
    }

    private static void writeData(String[] membersList, String seriesType, HashMap<String, Map<String, Number>> dataByCountries, String direction, boolean calculateIndicators) {
        CsvStatsUtils.writeChartData(dataByCountries.get(QOLI), membersList, seriesType, QOLI_FILE_NAME, direction, null, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(EDUCATION), membersList, seriesType, EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(ENVIRONMENT), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(GOVERNANCE), membersList, seriesType, GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(HEALTH), membersList, seriesType, HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(LEISURE_INTERACT), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, direction, LeisureInteractStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(LIVING_CONDITIONS), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(MAIN_ACTIVITY), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(OVERALL_EXPERIENCE), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators, calculateIndicators);
        CsvStatsUtils.writeChartData(dataByCountries.get(SAFETY), membersList, seriesType, SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
