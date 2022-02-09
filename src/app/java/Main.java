package app.java;

import app.java.commons.utils.ChartData;
import app.java.data.LocalParser;
import app.java.data.fetch.DataCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Collect the datasets;   The Voter Turnout dataset needs to be manually
        // downloaded from https://www.idea.int/data-tools/data/voter-turnout
         DataCollector.collectData();

//        // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
//        Print.printDataInconsistencies();

        // 4. Write the QoLI and the QoLI dimensions values to disk
        ChartData.writeCountries();
        ChartData.writeRegions();

        // 5. Print the QoLI and the QoLI dimensions values
        ChartData.printCountries();
        ChartData.printRegions();
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        ArrayList<String> localKeys = LocalParser.getDimensionOrderedKeys(filePath);
        System.out.println(localKeys);
    }
}
