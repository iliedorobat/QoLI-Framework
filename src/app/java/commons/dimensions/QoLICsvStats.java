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

import java.util.List;
import java.util.Map;

import static app.java.commons.constants.Constants.*;
import static app.java.commons.constants.FilePathConst.*;

public class QoLICsvStats {
    public static void printDimensions(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + QOLI_DIR)) {
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

        Print.printChartData(qoliList, membersList, seriesType, QOLI_DIR, direction);
        Print.printChartData(educationStats, membersList, seriesType, EDUCATION_DIR, direction);
        Print.printChartData(environmentStats, membersList, seriesType, ENVIRONMENT_DIR, direction);
        Print.printChartData(govRightsStats, membersList, seriesType, GOVERNANCE_DIR, direction);
        Print.printChartData(healthStats, membersList, seriesType, HEALTH_DIR, direction);
        Print.printChartData(interactionsStats, membersList, seriesType, INTERACTIONS_DIR, direction);
        Print.printChartData(leisureStats, membersList, seriesType, LEISURE_DIR, direction);
        Print.printChartData(mainActivityStats, membersList, seriesType, MAIN_ACTIVITY_DIR, direction);
        Print.printChartData(materialLivingStats, membersList, seriesType, LIVING_CONDITIONS_DIR, direction);
        Print.printChartData(overallExperienceStats, membersList, seriesType, OVERALL_EXPERIENCE_DIR, direction);
        Print.printChartData(safetyStats, membersList, seriesType, SAFETY_DIR, direction);
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

        CsvStatsUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, QOLI_DIR, direction, null);
        CsvStatsUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EDUCATION_DIR, direction, EducationStats.preparedIndicators);
        CsvStatsUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, ENVIRONMENT_DIR, direction, EnvironmentStats.preparedIndicators);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, GOVERNANCE_DIR, direction, GovRightsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, HEALTH_DIR, direction, HealthStats.preparedIndicators);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, INTERACTIONS_DIR, direction, InteractionsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LEISURE_DIR, direction, LeisureStats.preparedIndicators);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MAIN_ACTIVITY_DIR, direction, MainActivityStats.preparedIndicators);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LIVING_CONDITIONS_DIR, direction, MaterialLivingStats.preparedIndicators);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, OVERALL_EXPERIENCE_DIR, direction, OverallExperienceStats.preparedIndicators);
        CsvStatsUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, SAFETY_DIR, direction, SafetyStats.preparedIndicators);
    }
    private static void writeDataByRegions(String direction) {
        Map<String, Number>
                qoliList = CsvStatsUtils.aggregateRegions(QoLIStats.generateIndicatorList()),
                educationStats = CsvStatsUtils.aggregateRegions(EducationStats.generateDimensionList()),
                environmentStats = CsvStatsUtils.aggregateRegions(EnvironmentStats.generateDimensionList()),
                govRightsStats = CsvStatsUtils.aggregateRegions(GovRightsStats.generateDimensionList()),
                healthStats = CsvStatsUtils.aggregateRegions(HealthStats.generateDimensionList()),
                interactionsStats = CsvStatsUtils.aggregateRegions(InteractionsStats.generateDimensionList()),
                leisureStats = CsvStatsUtils.aggregateRegions(LeisureStats.generateDimensionList()),
                mainActivityStats = CsvStatsUtils.aggregateRegions(MainActivityStats.generateDimensionList()),
                materialLivingStats = CsvStatsUtils.aggregateRegions(MaterialLivingStats.generateDimensionList()),
                overallExperienceStats = CsvStatsUtils.aggregateRegions(OverallExperienceStats.generateDimensionList()),
                safetyStats = CsvStatsUtils.aggregateRegions(SafetyStats.generateDimensionList());

        CsvStatsUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, QOLI_DIR, direction, null);
        CsvStatsUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, EDUCATION_DIR, direction, EducationStats.preparedIndicators);
        CsvStatsUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, ENVIRONMENT_DIR, direction, EnvironmentStats.preparedIndicators);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, GOVERNANCE_DIR, direction, GovRightsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, HEALTH_DIR, direction, HealthStats.preparedIndicators);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, INTERACTIONS_DIR, direction, InteractionsStats.preparedIndicators);
        CsvStatsUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, LEISURE_DIR, direction, LeisureStats.preparedIndicators);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, MAIN_ACTIVITY_DIR, direction, MainActivityStats.preparedIndicators);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, LIVING_CONDITIONS_DIR, direction, MaterialLivingStats.preparedIndicators);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, OVERALL_EXPERIENCE_DIR, direction, OverallExperienceStats.preparedIndicators);
        CsvStatsUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, SAFETY_DIR, direction, SafetyStats.preparedIndicators);
    }
}
