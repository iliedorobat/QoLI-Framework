package app.java;

import app.java.commons.Print;
import app.java.commons.dimensions.QoLIStats;
import app.java.commons.dimensions.common.CommonStats;
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
import app.java.data.LocalParser;
import app.java.data.fetch.DataCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static app.java.commons.constants.Constants.SERIES_TYPE_COUNTRY;
import static app.java.commons.constants.Constants.SERIES_TYPE_REGION;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);

        boolean collect = args.length == 0 || contains(list, "--collect");
        boolean compare = contains(list, "--compare");
        boolean calculate = args.length == 0 || contains(list, "--calculate");
        boolean print = args.length == 0 || contains(list, "--print");

        if (collect) {
            // 1. Collect the datasets;
            DataCollector.collectData();
        }

        if (compare) {
            // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
            Print.printDimensionStatus(CommonStats.getInitList(), "Common info");
            Print.printDimensionStatus(MaterialLivingStats.getInitList(), "MLC");
            Print.printDimensionStatus(MainActivityStats.getInitList(), "PMA");
            Print.printDimensionStatus(HealthStats.getInitList(), "Health");
            Print.printDimensionStatus(EducationStats.getInitList(), "Education");
            Print.printDimensionStatus(InteractionsStats.getInitList(), "Interactions");
            Print.printDimensionStatus(LeisureStats.getInitList(), "Leisure");
            Print.printDimensionStatus(SafetyStats.getInitList(), "Safety");
            Print.printDimensionStatus(GovRightsStats.getInitList(), "GBR");
            Print.printDimensionStatus(EnvironmentStats.getInitList(), "Environment");
            Print.printDimensionStatus(OverallExperienceStats.getInitList(), "Overall Exp");
        }

        if (calculate) {
            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            QoLIStats.writeDimensions();
        }

        if (print) {
            String seriesType = getSeriesType(list);

            if (seriesType != null) {
                // 4. Print the QoLI and the QoLI dimensions values
                QoLIStats.printDimensions(list, seriesType);

                // 5. Print a specific indicator
                EducationStats.printIndicators(list, seriesType);
                EnvironmentStats.printIndicators(list, seriesType);
                GovRightsStats.printIndicators(list, seriesType);
                HealthStats.printIndicators(list, seriesType);
                InteractionsStats.printIndicators(list, seriesType);
                LeisureStats.printIndicators(list, seriesType);
                MainActivityStats.printIndicators(list, seriesType);
                MaterialLivingStats.printIndicators(list, seriesType);
                OverallExperienceStats.printIndicators(list, seriesType);
                SafetyStats.printIndicators(list, seriesType);
            }
        }
    }

    private static String getSeriesType(List<String> args) {
        if (args.contains("--seriesType=" + SERIES_TYPE_COUNTRY)) {
            return SERIES_TYPE_COUNTRY;
        }
        if (args.contains("--seriesType=" + SERIES_TYPE_REGION)) {
            return SERIES_TYPE_REGION;
        }
        return null;
    }

    private static boolean contains(List<String> list, String comparator) {
        for (String item : list) {
            if (item.contains(comparator))
                return true;
        }

        return false;
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        ArrayList<String> localKeys = LocalParser.getDimensionOrderedKeys(filePath);
        System.out.println(localKeys);
    }
}
