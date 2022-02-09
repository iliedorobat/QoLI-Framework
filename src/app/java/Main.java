package app.java;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
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
import app.java.commons.utils.FileUtils;
import app.java.commons.utils.MapUtils;
import app.java.data.LocalParser;
import app.java.data.fetch.DataCollector;

import java.util.*;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.EU28_REGIONS;

public class Main {
    public static void main(String[] args) {
        // 1. Collect the datasets;   The Voter Turnout dataset needs to be manually
        // downloaded from https://www.idea.int/data-tools/data/voter-turnout
         DataCollector.collectData();

//        // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
//        Print.printDataInconsistencies();

        // 4. Write the QoLI and the QoLI dimensions values to disk
        writeChartData();

        // 5. Print the QoLI and the QoLI dimensions values
        printCountries();

        Map<String, Number> qoliList = QoLIStats.generateIndicatorList();
        printRegions(qoliList);
    }

    private static void writeChartData() {
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

        FileUtils.writeChartData(qoliList, EU28_MEMBERS, "QoLI");
        FileUtils.writeChartData(educationStats, EU28_MEMBERS, "Education");
        FileUtils.writeChartData(environmentStats, EU28_MEMBERS, "Environment");
        FileUtils.writeChartData(govRightsStats, EU28_MEMBERS, "GBR");
        FileUtils.writeChartData(healthStats, EU28_MEMBERS, "Health");
        FileUtils.writeChartData(interactionsStats, EU28_MEMBERS, "Interactions");
        FileUtils.writeChartData(leisureStats, EU28_MEMBERS, "Leisure");
        FileUtils.writeChartData(mainActivityStats, EU28_MEMBERS, "PMA");
        FileUtils.writeChartData(materialLivingStats, EU28_MEMBERS, "MLC");
        FileUtils.writeChartData(overallExperienceStats, EU28_MEMBERS, "Overall Exp");
        FileUtils.writeChartData(safetyStats, EU28_MEMBERS, "Safety");
    }

    private static void printCountries() {
        System.out.println("----------- COUNTRIES DATA -----------\n");
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

        Print.printChartData(qoliList, EU28_MEMBERS, "QoLI");
        Print.printChartData(educationStats, EU28_MEMBERS, "Education");
        Print.printChartData(environmentStats, EU28_MEMBERS, "Environment");
        Print.printChartData(govRightsStats, EU28_MEMBERS, "GBR");
        Print.printChartData(healthStats, EU28_MEMBERS, "Health");
        Print.printChartData(interactionsStats, EU28_MEMBERS, "Interactions");
        Print.printChartData(leisureStats, EU28_MEMBERS, "Leisure");
        Print.printChartData(mainActivityStats, EU28_MEMBERS, "PMA");
        Print.printChartData(materialLivingStats, EU28_MEMBERS, "MLC");
        Print.printChartData(overallExperienceStats, EU28_MEMBERS, "Overall Exp");
        Print.printChartData(safetyStats, EU28_MEMBERS, "Safety");
        System.out.println("\n--------------------------------------");
    }

    private static void printRegions(Map<String, Number> entries) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            double easternCounter = 0,
                    northernCounter = 0,
                    southernCounter = 0,
                    westernCounter = 0;
            double easternSum = 0,
                    northernSum = 0,
                    southernSum = 0,
                    westernSum = 0;

            for (Map.Entry<String, Number> entry : entries.entrySet()) {
                String entryCode = MapUtils.getEntryCode(entry);
                Integer entryYear = MapUtils.getEntryYear(entry);
                Number entryValue = entry.getValue();

                if (entryYear == year) {
                    if (Arrays.asList(Constants.EU_EASTERN_MEMBERS).contains(entryCode)) {
                        easternSum += entryValue.doubleValue();
                        easternCounter++;
                    }
                    if (Arrays.asList(Constants.EU_NORTHERN_MEMBERS).contains(entryCode)) {
                        northernSum += entryValue.doubleValue();
                        northernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_SOUTHERN_MEMBERS).contains(entryCode)) {
                        southernSum += entryValue.doubleValue();
                        southernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_WESTERN_MEMBERS).contains(entryCode)) {
                        westernSum += entryValue.doubleValue();
                        westernCounter++;
                    }
                }
            }

            consolidatedList.put(MapUtils.generateKey("EU_EASTERN", year), easternSum / easternCounter);
            consolidatedList.put(MapUtils.generateKey("EU_NORTHERN", year), northernSum / northernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_SOUTHERN", year), southernSum / southernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_WESTERN", year), westernSum / westernCounter);
        }

        Print.printChartData(consolidatedList, EU28_REGIONS, "regions");
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        ArrayList<String> localKeys = LocalParser.getDimensionOrderedKeys(filePath);
        System.out.println(localKeys);
    }
}
