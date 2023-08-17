package app.java.commons;

import app.java.commons.constants.Constants;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.CsvStatsUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Print {
    public static void printChartData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String dimensionName,
            String direction
    ) {
        StringBuilder output = CsvStatsUtils.generateChartData(entries, membersList, seriesType, dimensionName, direction);
        System.out.println(output);
    }

    public static void print(Map<String, Number> entries, boolean printNull) {
        String prevCode = null;

        for (Map.Entry<String, Number> entry : entries.entrySet()) {
            // Print an empty line if the country code is different than the previous one
            String code = MapUtils.getEntryCode(entry);
            if (prevCode != null && !prevCode.equals(code))
                System.out.println();
            prevCode = code;

            print(entry, printNull);
        }
    }

    public static void print(Map.Entry<String, Number> entry, boolean printNull) {
        if (printNull)
            System.out.println(entry);
        else if (entry.getValue() != null)
            System.out.println(entry);
    }

    public static void print(Map<String, Number> entries, boolean printNull, String code) {
        for (Map.Entry<String, Number> entry : entries.entrySet()) {
            if (entry.getKey().contains(code + Constants.KEY_SEPARATOR))
                print(entry, printNull);
        }
    }

    public static void printVariation(Map<String, List<Number>> entries) {
        for (Map.Entry<String, List<Number>> entry : entries.entrySet()) {
            System.out.println(entry);
        }
    }

    public static void printVariation(Map<String, List<Number>> entries, String code) {
        for (Map.Entry<String, List<Number>> entry : entries.entrySet()) {
            if (entry.getKey().contains(code + Constants.KEY_SEPARATOR))
                System.out.println(entry);
        }
    }

    /** @deprecated */
    public static void printDimensionStatus(ArrayList<Map<String, Number>> list, String dimensionName) {
        int expected = 0;
        int available = 0;

        for (Map<String, Number> entries : list) {
            for (Map.Entry<String, Number> entry : entries.entrySet()) {
                Number value = entry.getValue();
                expected += 1;
                if (value != null)
                    available += 1;
            }
        }

        System.out.println(dimensionName + ":"
                + "\n\tAvailable: " + available
                + "\n\tExpected: " + expected);
    }

    private static Map<String, Number> filterMap(Map<String, Number> preparedMap, int targetYear) {
        Map<String, Number> filteredMap = new TreeMap<>(new MapOrder());

        for (Map.Entry<String, Number> entry : preparedMap.entrySet()) {
            int year = MapUtils.getEntryYear(entry);

            if (year == targetYear) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredMap;
    }

    public static void printDimensionStatus(TreeMap<String, Map<String, Number>> map, String dimensionName, int targetYear, boolean printIntermediateStatus) {
        int available = 0;
        int expected = 0;

        for (Map.Entry<String, Map<String, Number>> indicatorMap : map.entrySet()) {
            Map<String, Number> values = indicatorMap.getValue();
            String indicatorName = indicatorMap.getKey();
            if (targetYear > -1) {
                values = filterMap(values, targetYear);
            }

            int indicatorAvailable = 0;
            int indicatorExpected = values.size();

            for (Map.Entry<String, Number> entry : values.entrySet()) {
                Number value = entry.getValue();
                if (value != null)
                    indicatorAvailable += 1;
            }

            available += indicatorAvailable;
            expected += indicatorExpected;

            if (printIntermediateStatus) {
                System.out.println("\t" + indicatorName + ":"
                        + "\n\t\tAvailable: " + indicatorAvailable
                        + "\n\t\tMissing: " + (indicatorExpected - indicatorAvailable));
            }
        }

        System.out.println(dimensionName + ":"
                + "\n\tAvailable: " + available
                + "\n\tMissing: " + (expected - available)
                + "\n------------------------");
    }

    public static String formatNumber(double number) {
        Locale locale = new Locale("en", "US");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');

//        symbols.setDecimalSeparator(',');
//        symbols.setGroupingSeparator('.');

        String pattern = "#,###.0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);

        return decimalFormat.format(number);
    }
}
