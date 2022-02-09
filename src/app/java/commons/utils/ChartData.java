package app.java.commons.utils;

import app.java.commons.Print;
import app.java.commons.dimesntions.QoLIStats;
import app.java.commons.dimesntions.education.EducationStats;
import app.java.commons.dimesntions.environment.EnvironmentStats;
import app.java.commons.dimesntions.gov.GovRightsStats;
import app.java.commons.dimesntions.health.HealthStats;
import app.java.commons.dimesntions.interactions.InteractionsStats;
import app.java.commons.dimesntions.leisure.LeisureStats;
import app.java.commons.dimesntions.mainActivity.MainActivityStats;
import app.java.commons.dimesntions.materialLiving.MaterialLivingStats;
import app.java.commons.dimesntions.overall.OverallExperienceStats;
import app.java.commons.dimesntions.safety.SafetyStats;

import java.util.Map;

import static app.java.commons.constants.Constants.*;

public class ChartData {
    public static void printCountries() {
        System.out.println("\n----------- COUNTRIES DATA -----------\n");
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

        Print.printChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI");
        Print.printChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education");
        Print.printChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment");
        Print.printChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR");
        Print.printChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health");
        Print.printChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions");
        Print.printChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure");
        Print.printChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA");
        Print.printChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC");
        Print.printChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp");
        Print.printChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety");
        System.out.println("--------------------------------------");
    }

    public static void printRegions() {
        System.out.println("\n----------- REGIONS DATA -----------\n");
        Map<String, Number>
                qoliList = StatsUtils.aggregateRegions(QoLIStats.generateIndicatorList()),
                educationStats = StatsUtils.aggregateRegions(EducationStats.generateDimensionList()),
                environmentStats = StatsUtils.aggregateRegions(EnvironmentStats.generateDimensionList()),
                govRightsStats = StatsUtils.aggregateRegions(GovRightsStats.generateDimensionList()),
                healthStats = StatsUtils.aggregateRegions(HealthStats.generateDimensionList()),
                interactionsStats = StatsUtils.aggregateRegions(InteractionsStats.generateDimensionList()),
                leisureStats = StatsUtils.aggregateRegions(LeisureStats.generateDimensionList()),
                mainActivityStats = StatsUtils.aggregateRegions(MainActivityStats.generateDimensionList()),
                materialLivingStats = StatsUtils.aggregateRegions(MaterialLivingStats.generateDimensionList()),
                overallExperienceStats = StatsUtils.aggregateRegions(OverallExperienceStats.generateDimensionList()),
                safetyStats = StatsUtils.aggregateRegions(SafetyStats.generateDimensionList());

        Print.printChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "QoLI");
        Print.printChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "Education");
        Print.printChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "Environment");
        Print.printChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "GBR");
        Print.printChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "Health");
        Print.printChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "Interactions");
        Print.printChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "Leisure");
        Print.printChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "PMA");
        Print.printChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "MLC");
        Print.printChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "Overall Exp");
        Print.printChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "Safety");
        System.out.println("--------------------------------------");
    }

    public static void writeCountries() {
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

        FileUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI");
        FileUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education");
        FileUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment");
        FileUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR");
        FileUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health");
        FileUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions");
        FileUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure");
        FileUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA");
        FileUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC");
        FileUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp");
        FileUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety");
    }

    public static void writeRegions() {
        Map<String, Number>
                qoliList = StatsUtils.aggregateRegions(QoLIStats.generateIndicatorList()),
                educationStats = StatsUtils.aggregateRegions(EducationStats.generateDimensionList()),
                environmentStats = StatsUtils.aggregateRegions(EnvironmentStats.generateDimensionList()),
                govRightsStats = StatsUtils.aggregateRegions(GovRightsStats.generateDimensionList()),
                healthStats = StatsUtils.aggregateRegions(HealthStats.generateDimensionList()),
                interactionsStats = StatsUtils.aggregateRegions(InteractionsStats.generateDimensionList()),
                leisureStats = StatsUtils.aggregateRegions(LeisureStats.generateDimensionList()),
                mainActivityStats = StatsUtils.aggregateRegions(MainActivityStats.generateDimensionList()),
                materialLivingStats = StatsUtils.aggregateRegions(MaterialLivingStats.generateDimensionList()),
                overallExperienceStats = StatsUtils.aggregateRegions(OverallExperienceStats.generateDimensionList()),
                safetyStats = StatsUtils.aggregateRegions(SafetyStats.generateDimensionList());

        FileUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "QoLI");
        FileUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "Education");
        FileUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "Environment");
        FileUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "GBR");
        FileUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "Health");
        FileUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "Interactions");
        FileUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "Leisure");
        FileUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "PMA");
        FileUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "MLC");
        FileUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "Overall Exp");
        FileUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "Safety");
    }
}
