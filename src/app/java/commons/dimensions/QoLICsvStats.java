package app.java.commons.dimensions;

import app.java.commons.Print;
import app.java.commons.dimensions.aggrQoli.QoLIPaths;
import app.java.commons.dimensions.aggrQoli.QoLIStats;
import app.java.commons.dimensions.education.EducationPaths;
import app.java.commons.dimensions.education.EducationStats;
import app.java.commons.dimensions.environment.EnvironmentPaths;
import app.java.commons.dimensions.environment.EnvironmentStats;
import app.java.commons.dimensions.gov.GovRightsPaths;
import app.java.commons.dimensions.gov.GovRightsStats;
import app.java.commons.dimensions.health.HealthPaths;
import app.java.commons.dimensions.health.HealthStats;
import app.java.commons.dimensions.interactions.InteractionsPaths;
import app.java.commons.dimensions.interactions.InteractionsStats;
import app.java.commons.dimensions.leisure.LeisurePaths;
import app.java.commons.dimensions.leisure.LeisureStats;
import app.java.commons.dimensions.mainActivity.MainActivityPaths;
import app.java.commons.dimensions.mainActivity.MainActivityStats;
import app.java.commons.dimensions.materialLiving.MaterialLivingPaths;
import app.java.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.commons.dimensions.overall.OverallExperiencePaths;
import app.java.commons.dimensions.overall.OverallExperienceStats;
import app.java.commons.dimensions.safety.SafetyPaths;
import app.java.commons.dimensions.safety.SafetyStats;
import app.java.commons.utils.CsvStatsUtils;

import java.util.List;
import java.util.Map;

import static app.java.commons.constants.Constants.*;

public class QoLICsvStats {
    public static void printDimensions(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + QoLIPaths.QOLI_FILE_NAME)) {
            if (seriesType.equals(SERIES_TYPE_COUNTRY)) {
                printData(EU28_MEMBERS, SERIES_TYPE_COUNTRY, direction);
            } else if (seriesType.equals(SERIES_TYPE_REGION)) {
                printData(EU28_REGIONS, SERIES_TYPE_REGION, direction);
            }
        }
    }

    public static void writeDimensions(String direction) {
        writeDataByCountries(direction);
        writeDataByRegions(direction);
    }

    private static void printData(String[] membersList, String seriesType, String direction) {
        System.out.println("\n----------- " + seriesType +" DATA -----------\n");
        Map<String, Number>
                qoliList = QoLIStats.generateIndicatorList(),
                educationStats = EducationStats.generateDimensionList(),
                environmentStats = EnvironmentStats.generateDimensionList(),
                govRightsStats = GovRightsStats.generateDimensionList(),
                healthStats = HealthStats.generateDimensionList(),
                interactionsStats = InteractionsStats.generateDimensionList(),
                leisureStats = LeisureStats.generateDimensionList(),
                mainActivityStats = MainActivityStats.generateDimensionList(),
                materialLivingStats = MaterialLivingStats.generateDimensionList(),
                overallExperienceStats = OverallExperienceStats.generateDimensionList(),
                safetyStats = SafetyStats.generateDimensionList();

        Print.printChartData(qoliList, membersList, seriesType, QoLIPaths.QOLI_FILE_NAME, direction);
        Print.printChartData(educationStats, membersList, seriesType, EducationPaths.EDUCATION_FILE_NAME, direction);
        Print.printChartData(environmentStats, membersList, seriesType, EnvironmentPaths.ENVIRONMENT_FILE_NAME, direction);
        Print.printChartData(govRightsStats, membersList, seriesType, GovRightsPaths.GOVERNANCE_FILE_NAME, direction);
        Print.printChartData(healthStats, membersList, seriesType, HealthPaths.HEALTH_FILE_NAME, direction);
        Print.printChartData(interactionsStats, membersList, seriesType, InteractionsPaths.INTERACTIONS_FILE_NAME, direction);
        Print.printChartData(leisureStats, membersList, seriesType, LeisurePaths.LEISURE_FILE_NAME, direction);
        Print.printChartData(mainActivityStats, membersList, seriesType, MainActivityPaths.MAIN_ACTIVITY_FILE_NAME, direction);
        Print.printChartData(materialLivingStats, membersList, seriesType, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, direction);
        Print.printChartData(overallExperienceStats, membersList, seriesType, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, direction);
        Print.printChartData(safetyStats, membersList, seriesType, SafetyPaths.SAFETY_FILE_NAME, direction);
        System.out.println("--------------------------------------");
    }

    private static void writeDataByCountries(String direction) {
        Map<String, Number>
                qoliList = QoLIStats.generateIndicatorList(),
                educationStats = EducationStats.generateDimensionList(),
                environmentStats = EnvironmentStats.generateDimensionList(),
                govRightsStats = GovRightsStats.generateDimensionList(),
                healthStats = HealthStats.generateDimensionList(),
                interactionsStats = InteractionsStats.generateDimensionList(),
                leisureStats = LeisureStats.generateDimensionList(),
                mainActivityStats = MainActivityStats.generateDimensionList(),
                materialLivingStats = MaterialLivingStats.generateDimensionList(),
                overallExperienceStats = OverallExperienceStats.generateDimensionList(),
                safetyStats = SafetyStats.generateDimensionList();

        CsvStatsUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, QoLIPaths.QOLI_FILE_NAME, direction, null);
        CsvStatsUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EducationPaths.EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators);
        CsvStatsUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EnvironmentPaths.ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, GovRightsPaths.GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, HealthPaths.HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, InteractionsPaths.INTERACTIONS_FILE_NAME, direction, InteractionsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LeisurePaths.LEISURE_FILE_NAME, direction, LeisureStats.preparedIndicators);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MainActivityPaths.MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators);
        CsvStatsUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, SafetyPaths.SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators);
    }
    private static void writeDataByRegions(String direction) {
        Map<String, Number>
                qoliList = QoLIStats.aggregateRegions(QoLIStats.generateIndicatorList()),
                educationStats = QoLIStats.aggregateRegions(EducationStats.generateDimensionList()),
                environmentStats = QoLIStats.aggregateRegions(EnvironmentStats.generateDimensionList()),
                govRightsStats = QoLIStats.aggregateRegions(GovRightsStats.generateDimensionList()),
                healthStats = QoLIStats.aggregateRegions(HealthStats.generateDimensionList()),
                interactionsStats = QoLIStats.aggregateRegions(InteractionsStats.generateDimensionList()),
                leisureStats = QoLIStats.aggregateRegions(LeisureStats.generateDimensionList()),
                mainActivityStats = QoLIStats.aggregateRegions(MainActivityStats.generateDimensionList()),
                materialLivingStats = QoLIStats.aggregateRegions(MaterialLivingStats.generateDimensionList()),
                overallExperienceStats = QoLIStats.aggregateRegions(OverallExperienceStats.generateDimensionList()),
                safetyStats = QoLIStats.aggregateRegions(SafetyStats.generateDimensionList());

        CsvStatsUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, QoLIPaths.QOLI_FILE_NAME, direction, null);
        CsvStatsUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, EducationPaths.EDUCATION_FILE_NAME, direction, EducationStats.preparedIndicators);
        CsvStatsUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, EnvironmentPaths.ENVIRONMENT_FILE_NAME, direction, EnvironmentStats.preparedIndicators);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, GovRightsPaths.GOVERNANCE_FILE_NAME, direction, GovRightsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, HealthPaths.HEALTH_FILE_NAME, direction, HealthStats.preparedIndicators);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, InteractionsPaths.INTERACTIONS_FILE_NAME, direction, InteractionsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, LeisurePaths.LEISURE_FILE_NAME, direction, LeisureStats.preparedIndicators);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, MainActivityPaths.MAIN_ACTIVITY_FILE_NAME, direction, MainActivityStats.preparedIndicators);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, direction, MaterialLivingStats.preparedIndicators);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, direction, OverallExperienceStats.preparedIndicators);
        CsvStatsUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, SafetyPaths.SAFETY_FILE_NAME, direction, SafetyStats.preparedIndicators);
    }
}
