package app.java.commons.dimesntions;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
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
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.StatsUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.*;

public class QoLIStats {
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

    public static void printDimensions(List<String> args, String seriesType) {
        if (args.contains("--dimension=" + DimensionNames.QOLI)) {
            if (seriesType.equals(SERIES_TYPE_COUNTRY)) {
                printCountries();
            } else if (seriesType.equals(SERIES_TYPE_REGION)) {
                printRegions();
            }
        }
    }

    public static void writeDimensions() {
        writeCountries();
        writeRegions();
    }

    private static void printCountries() {
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

    private static void printRegions() {
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

    private static void writeCountries() {
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

        StatsUtils.writeChartData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI");
        StatsUtils.writeChartData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education");
        StatsUtils.writeChartData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment");
        StatsUtils.writeChartData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR");
        StatsUtils.writeChartData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health");
        StatsUtils.writeChartData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions");
        StatsUtils.writeChartData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure");
        StatsUtils.writeChartData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA");
        StatsUtils.writeChartData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC");
        StatsUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp");
        StatsUtils.writeChartData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety");
    }

    private static void writeRegions() {
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

        StatsUtils.writeChartData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, "QoLI");
        StatsUtils.writeChartData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, "Education");
        StatsUtils.writeChartData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, "Environment");
        StatsUtils.writeChartData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, "GBR");
        StatsUtils.writeChartData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, "Health");
        StatsUtils.writeChartData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, "Interactions");
        StatsUtils.writeChartData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, "Leisure");
        StatsUtils.writeChartData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, "PMA");
        StatsUtils.writeChartData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, "MLC");
        StatsUtils.writeChartData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, "Overall Exp");
        StatsUtils.writeChartData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, "Safety");
    }
}
