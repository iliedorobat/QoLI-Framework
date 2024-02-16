package app.java;

import app.java.commons.Print;
import app.java.commons.dimensions.QoLICsvStats;
import app.java.commons.dimensions.QoLIJsonStats;
import app.java.commons.dimensions.auxiliary.AuxiliaryStats;
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

import static app.java.commons.constants.Constants.*;
import static app.java.commons.constants.EnvConst.MAX_YEAR;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);

        boolean collect = args.length == 0 || contains(list, "--collect");
        boolean compare = contains(list, "--compare");
        boolean indStatus = contains(list, "--indicatorStatus");
        boolean calculate = args.length == 0 || contains(list, "--calculate");
        boolean calculateIndicators = args.length == 0 || contains(list, "--calculateIndicators");
        boolean print = args.length == 0 || contains(list, "--print");
        int targetYear = MAX_YEAR;

        if (collect) {
            // 1. Collect the datasets;
            DataCollector.collectData();
        }

        if (compare) {
            // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
            Print.printDimensionsStatus(targetYear, indStatus);
        }

        if (calculate) {
            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            String direction = getDirection(list);
            QoLICsvStats.writeDimensions(direction, calculateIndicators);
            QoLIJsonStats.writeDimensions(calculateIndicators);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
                // 4. Print the QoLI and the QoLI dimensions values
                QoLICsvStats.printDimensions(list, seriesType, direction);

                // 5. Print a specific indicator
                EducationStats.printIndicators(list, seriesType, direction);
                EnvironmentStats.printIndicators(list, seriesType, direction);
                GovRightsStats.printIndicators(list, seriesType, direction);
                HealthStats.printIndicators(list, seriesType, direction);
                InteractionsStats.printIndicators(list, seriesType, direction);
                LeisureStats.printIndicators(list, seriesType, direction);
                MainActivityStats.printIndicators(list, seriesType, direction);
                MaterialLivingStats.printIndicators(list, seriesType, direction);
                OverallExperienceStats.printIndicators(list, seriesType, direction);
                SafetyStats.printIndicators(list, seriesType, direction);
            }
        }
    }

    private static boolean contains(List<String> pairs, String comparator) {
        for (String pair : pairs) {
            String[] values = pair.split("=");

            // E.g.: --calculateIndicators
            if (values.length == 1) {
                String key = values[0];
                return comparator.equals(key);
            }
            // E.g.: --calculate=true
            else if (values.length > 1) {
                String key = values[0];
                String value = values[1];

                if (comparator.equals(key)) {
                    return Boolean.valueOf(value);
                }
            }
        }

        return false;
    }

    private static String getDirection(List<String> pairs) {
        if (pairs.contains("--direction=" + DIRECTION_ROW)) {
            return DIRECTION_ROW;
        }
        return DIRECTION_COLUMN;
    }

    private static String getSeriesType(List<String> pairs) {
        if (pairs.contains("--seriesType=" + SERIES_TYPE_COUNTRY)) {
            return SERIES_TYPE_COUNTRY;
        }
        if (pairs.contains("--seriesType=" + SERIES_TYPE_REGION)) {
            return SERIES_TYPE_REGION;
        }
        return null;
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        ArrayList<String> localKeys = LocalParser.getDimensionOrderedKeys(filePath);
        System.out.println(localKeys);
    }
}
