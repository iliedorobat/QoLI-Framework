package app.java;

import app.java.commons.ChartData;
import app.java.commons.Print;
import app.java.data.LocalParser;
import app.java.data.fetch.DataCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List arrayList = Arrays.asList(args);

        boolean collect = args.length == 0 || arrayList.contains("--collect");
        boolean compare = arrayList.contains("--compare");
        boolean calculate = args.length == 0 || arrayList.contains("--calculate");
        boolean print = args.length == 0 || arrayList.contains("--print");

        if (collect) {
            // 1. Collect the datasets;
            DataCollector.collectData();
        }

        if (compare) {
            // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
            Print.printDataInconsistencies();
        }

        if (calculate) {
            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            ChartData.writeCountries();
            ChartData.writeRegions();
        }

        if (print) {
            // 4. Print the QoLI and the QoLI dimensions values
            ChartData.printCountries();
            ChartData.printRegions();
        }
    }

    // For testing
    public static void print(String filePath) {
        Map<List<String>, Number> entries = LocalParser.readJSONFile(filePath);
        System.out.println(entries);

        ArrayList<String> localKeys = LocalParser.getDimensionOrderedKeys(filePath);
        System.out.println(localKeys);
    }
}
