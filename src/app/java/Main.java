package app.java;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.data.measurement.statistics.QoLIStats;
import app.java.data.measurement.statistics.*;
import app.java.data.parse.LocalParser;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        // 1. Write a file to disk
//        GeneralDAO dao = new GeneralDAOImpl();
//        StringBuilder sb = dao.getPopulation();
//        FileUtils.writeToJSONFile(sb, FilePathConst.DATASET_PATH + FileNameConst.POPULATION);
//
//        // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
//        Print.printDataInconsistencies();

        // 3. Get QoLI and the QoLI dimensions statistics
        Map<String, Number>
                qoliList = QoLIStats.generateIndicatorList(),
                educationStats = EducationStats.generateDimensionList(),
                environmentStats = EnvironmentStats.generateDimensionList(),
                govRightsStats = GovRightsStats.generateDimensionList(),
                healthStats = HealthStats.generateDimensionList(),
                mainActivityStats = MainActivityStats.generateDimensionList(),
                materialLivingStats = MaterialLivingStats.generateDimensionList(),
                overallExperienceStats = OverallExperienceStats.generateDimensionList(),
                safetyStats = SafetyStats.generateDimensionList(),
                socialActivityStats = SocialActivityStats.generateDimensionList();

//        // 4. Write the QoLI and the QoLI dimensions values to disk
//        FileUtils.writeChartData(qoliList, "QoLI");
//        FileUtils.writeChartData(educationStats, "Education");
//        FileUtils.writeChartData(environmentStats, "Environment");
//        FileUtils.writeChartData(govRightsStats, "GBR");
//        FileUtils.writeChartData(healthStats, "Health");
//        FileUtils.writeChartData(mainActivityStats, "PMA");
//        FileUtils.writeChartData(materialLivingStats, "MLC");
//        FileUtils.writeChartData(overallExperienceStats, "Overall Exp");
//        FileUtils.writeChartData(safetyStats, "Safety");
//        FileUtils.writeChartData(socialActivityStats, "LSI");
//
//        // 5. Print the QoLI and the QoLI dimensions values
        Print.printChartData(qoliList, "QoLI");
//        Print.printChartData(educationStats, "Education");
//        Print.printChartData(environmentStats, "Environment");
//        Print.printChartData(govRightsStats, "GBR");
//        Print.printChartData(healthStats, "Health");
//        Print.printChartData(mainActivityStats, "PMA");
//        Print.printChartData(materialLivingStats, "MLC");
//        Print.printChartData(overallExperienceStats, "Overall Exp");
//        Print.printChartData(safetyStats, "Safety");
//        Print.printChartData(socialActivityStats, "LSI");
//
//        printRegions(qoliList);
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
                    if (Arrays.asList(Constants.EU_EASTERN_MEMBERS).indexOf(entryCode) != -1) {
                        easternSum += entryValue.doubleValue();
                        easternCounter++;
                    }
                    if (Arrays.asList(Constants.EU_NORTHERN_MEMBERS).indexOf(entryCode) != -1) {
                        northernSum += entryValue.doubleValue();
                        northernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_SOUTHERN_MEMBERS).indexOf(entryCode) != -1) {
                        southernSum += entryValue.doubleValue();
                        southernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_WESTERN_MEMBERS).indexOf(entryCode) != -1) {
                        westernSum += entryValue.doubleValue();
                        westernCounter++;
                    }
                }
            }

            consolidatedList.put(MapUtils.generateKey("EASTERN", year), easternSum / easternCounter);
            consolidatedList.put(MapUtils.generateKey("NORTHERN", year), northernSum / northernCounter);
            consolidatedList.put(MapUtils.generateKey("SOUTHERN", year), southernSum / southernCounter);
            consolidatedList.put(MapUtils.generateKey("WESTERN", year), westernSum / westernCounter);
        }

        Print.printChartData(consolidatedList, "regions");
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        Set<String> dimensions = LocalParser.getDimensionsOrder(filePath);
        System.out.println(dimensions);
    }
}
