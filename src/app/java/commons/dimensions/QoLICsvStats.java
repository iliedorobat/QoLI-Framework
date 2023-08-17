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

        Print.printChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI", direction);
        Print.printChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education", direction);
        Print.printChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment", direction);
        Print.printChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR", direction);
        Print.printChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health", direction);
        Print.printChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions", direction);
        Print.printChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure", direction);
        Print.printChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA", direction);
        Print.printChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC", direction);
        Print.printChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp", direction);
        Print.printChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety", direction);
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

        Print.printChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "QoLI", direction);
        Print.printChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "Education", direction);
        Print.printChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "Environment", direction);
        Print.printChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "GBR", direction);
        Print.printChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "Health", direction);
        Print.printChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "Interactions", direction);
        Print.printChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "Leisure", direction);
        Print.printChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "PMA", direction);
        Print.printChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "MLC", direction);
        Print.printChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "Overall Exp", direction);
        Print.printChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "Safety", direction);
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

        CsvStatsUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI", direction);
        CsvStatsUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education", direction);
        CsvStatsUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment", direction);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR", direction);
        CsvStatsUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health", direction);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions", direction);
        CsvStatsUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure", direction);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA", direction);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC", direction);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp", direction);
        CsvStatsUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety", direction);
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

        CsvStatsUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "QoLI", direction);
        CsvStatsUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "Education", direction);
        CsvStatsUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "Environment", direction);
        CsvStatsUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "GBR", direction);
        CsvStatsUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "Health", direction);
        CsvStatsUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "Interactions", direction);
        CsvStatsUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "Leisure", direction);
        CsvStatsUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "PMA", direction);
        CsvStatsUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "MLC", direction);
        CsvStatsUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "Overall Exp", direction);
        CsvStatsUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "Safety", direction);
    }
}
