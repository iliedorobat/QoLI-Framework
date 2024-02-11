package app.java.commons.dimensions;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
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
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.CsvStatsUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class QoLICsvStats {
    public static Map<String, Number> generateIndicatorList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
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

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double education = educationStats.get(key).doubleValue();
                double environment = environmentStats.get(key).doubleValue();
                double govRights = govRightsStats.get(key).doubleValue();
                double health = healthStats.get(key).doubleValue();
                double interactions = interactionsStats.get(key).doubleValue();
                double leisure = leisureStats.get(key).doubleValue();
                double mainActivity = mainActivityStats.get(key).doubleValue();
                double materialLiving = materialLivingStats.get(key).doubleValue();
                double overallExperience = overallExperienceStats.get(key).doubleValue();
                double safety = safetyStats.get(key).doubleValue();

                double product = 1
                        * education
                        * environment
                        * govRights
                        * health
                        * interactions
                        * leisure
                        * mainActivity
                        * materialLiving
                        * overallExperience
                        * safety;
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(consolidatedList, true));
//        Print.print(consolidatedList, false);

        return consolidatedList;
    }

    public static void printDimensions(List<String> args, String seriesType, String direction) {
        if (args.contains("--dimension=" + DimensionNames.QOLI)) {
            if (seriesType.equals(SERIES_TYPE_COUNTRY)) {
                printCountries(direction);
            } else if (seriesType.equals(SERIES_TYPE_REGION)) {
                printRegions(direction);
            }
        }
    }

    public static void writeDimensions(String direction) {
        writeCountries(direction);
        writeRegions(direction);
    }

    private static void printCountries(String direction) {
        System.out.println("\n----------- COUNTRIES DATA -----------\n");
        Map<String, Number>
                qoliList = QoLICsvStats.generateIndicatorList(),
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

        Print.printChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "qoli", direction);
        Print.printChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "education", direction);
        Print.printChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "environment", direction);
        Print.printChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "governance", direction);
        Print.printChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "health", direction);
        Print.printChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "interactions", direction);
        Print.printChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "leisure", direction);
        Print.printChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "mainActivity", direction);
        Print.printChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "livingConditions", direction);
        Print.printChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "overallExperience", direction);
        Print.printChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "safety", direction);
        System.out.println("--------------------------------------");
    }

    private static void printRegions(String direction) {
        System.out.println("\n----------- REGIONS DATA -----------\n");
        Map<String, Number>
                qoliList = CsvStatsUtils.aggregateRegions(QoLICsvStats.generateIndicatorList()),
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

        Print.printChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "qoli", direction);
        Print.printChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "education", direction);
        Print.printChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "environment", direction);
        Print.printChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "governance", direction);
        Print.printChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "health", direction);
        Print.printChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "interactions", direction);
        Print.printChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "leisure", direction);
        Print.printChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "mainActivity", direction);
        Print.printChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "livingConditions", direction);
        Print.printChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "overallExperience", direction);
        Print.printChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "safety", direction);
        System.out.println("--------------------------------------");
    }

    private static void writeCountries(String direction) {
        Map<String, Number>
                qoliList = QoLICsvStats.generateIndicatorList(),
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

        CsvStatsUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "qoli", direction);
        CsvStatsUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "education", direction);
        CsvStatsUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "environment", direction);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "governance", direction);
        CsvStatsUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "health", direction);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "interactions", direction);
        CsvStatsUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "leisure", direction);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "mainActivity", direction);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "livingConditions", direction);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "overallExperience", direction);
        CsvStatsUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "safety", direction);
    }

    private static void writeRegions(String direction) {
        Map<String, Number>
                qoliList = CsvStatsUtils.aggregateRegions(QoLICsvStats.generateIndicatorList()),
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

        CsvStatsUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "qoli", direction);
        CsvStatsUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "education", direction);
        CsvStatsUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "environment", direction);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "governance", direction);
        CsvStatsUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "health", direction);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "interactions", direction);
        CsvStatsUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "leisure", direction);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "mainActivity", direction);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "livingConditions", direction);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "overallExperience", direction);
        CsvStatsUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "safety", direction);
    }
}
