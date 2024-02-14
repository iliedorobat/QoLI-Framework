package app.java.commons.dimensions;

import app.java.commons.Print;
import app.java.commons.dimensions.education.EducationStats;
import app.java.commons.dimensions.environment.EnvironmentStats;
import app.java.commons.dimensions.gov.GovRightsStats;
import app.java.commons.dimensions.health.HealthStats;
import app.java.commons.dimensions.interactions.InteractionsStats;
import app.java.commons.dimensions.leisure.LeisureStats;
import app.java.commons.dimensions.mainActivity.MainActivityStats;
import app.java.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.commons.dimensions.overall.OverallExperienceStats;
import app.java.commons.dimensions.safety.SafetyStats;
import app.java.commons.utils.CsvStatsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.java.commons.constants.Constants.*;
import static app.java.commons.dimensions.aggrQoli.QoLIPaths.QOLI_FILE_NAME;
import static app.java.commons.dimensions.aggrQoli.QoLIStats.DATA_BY_COUNTRIES;
import static app.java.commons.dimensions.aggrQoli.QoLIStats.DATA_BY_REGIONS;
import static app.java.commons.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static app.java.commons.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static app.java.commons.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static app.java.commons.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static app.java.commons.dimensions.interactions.InteractionsPaths.INTERACTIONS_FILE_NAME;
import static app.java.commons.dimensions.leisure.LeisurePaths.LEISURE_FILE_NAME;
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

    public static void writeDimensions(String direction) {
        writeDataByCountries(direction);
        writeDataByRegions(direction);
    }

    private static void printCountriesData(String direction) {
        System.out.println("\n----------- " + SERIES_TYPE_COUNTRY +" DATA -----------\n");
        printData(DATA_BY_COUNTRIES, EU28_MEMBERS, SERIES_TYPE_COUNTRY, direction);
        System.out.println("--------------------------------------");
    }

    private static void printRegionsData(String direction) {
        System.out.println("\n----------- " + SERIES_TYPE_REGION +" DATA -----------\n");
        printData(DATA_BY_REGIONS, EU28_REGIONS, SERIES_TYPE_REGION, direction);
        System.out.println("--------------------------------------");
    }

    private static void writeDataByCountries(String direction) {
        writeData(DATA_BY_COUNTRIES, EU28_MEMBERS, SERIES_TYPE_COUNTRY, direction);
    }

    private static void writeDataByRegions(String direction) {
        writeData(DATA_BY_REGIONS, EU28_REGIONS, SERIES_TYPE_REGION, direction);
    }

    private static void printData(HashMap<String, Map<String, Number>> indicators, String[] membersList, String seriesType, String direction) {
        Print.printChartData(indicators.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, direction);
        Print.printChartData(indicators.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, direction);
        Print.printChartData(indicators.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction);
        Print.printChartData(indicators.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, direction);
        Print.printChartData(indicators.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, direction);
        Print.printChartData(indicators.get(INTERACTIONS_FILE_NAME), membersList, seriesType, INTERACTIONS_FILE_NAME, direction);
        Print.printChartData(indicators.get(LEISURE_FILE_NAME), membersList, seriesType, LEISURE_FILE_NAME, direction);
        Print.printChartData(indicators.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction);
        Print.printChartData(indicators.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction);
        Print.printChartData(indicators.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction);
        Print.printChartData(indicators.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, direction);
    }

    private static void writeData(HashMap<String, Map<String, Number>> indicators, String[] membersList, String seriesType, String direction) {
        CsvStatsUtils.writeChartData(indicators.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, direction, null);
        CsvStatsUtils.writeChartData(indicators.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(INTERACTIONS_FILE_NAME), membersList, seriesType, INTERACTIONS_FILE_NAME, direction, InteractionsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(LEISURE_FILE_NAME), membersList, seriesType, LEISURE_FILE_NAME, direction, LeisureStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators);
        CsvStatsUtils.writeChartData(indicators.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators);
    }
}
