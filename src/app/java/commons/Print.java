package app.java.commons;

import app.java.commons.constants.Constants;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.StatsUtils;
import app.java.commons.dimesntions.common.CommonStats;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Print {
    public static void printChartData(Map<String, Number> entries, String[] membersList, String dimensionName) {
        StringBuilder output = StatsUtils.generateChartData(entries, membersList, dimensionName);
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

    public static void printDataInconsistencies() {
        printDimensionStatus(CommonStats.getInitList(), "Common info");
        printDimensionStatus(MaterialLivingStats.getInitList(), "MLC");
        printDimensionStatus(MainActivityStats.getInitList(), "PMA");
        printDimensionStatus(HealthStats.getInitList(), "Health");
        printDimensionStatus(EducationStats.getInitList(), "Education");
        printDimensionStatus(InteractionsStats.getInitList(), "Interactions");
        printDimensionStatus(LeisureStats.getInitList(), "Leisure");
        printDimensionStatus(SafetyStats.getInitList(), "Safety");
        printDimensionStatus(GovRightsStats.getInitList(), "GBR");
        printDimensionStatus(EnvironmentStats.getInitList(), "Environment");
        printDimensionStatus(OverallExperienceStats.getInitList(), "Overall Exp");
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

    private static void printDimensionStatus(ArrayList<Map<String, Number>> list, String dimension) {
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

        System.out.println(dimension + ":"
                + "\n\tAvailable: " + available
                + "\n\tExpected: " + expected);
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
