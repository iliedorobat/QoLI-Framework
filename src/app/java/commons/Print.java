package app.java.commons;

import app.java.commons.constants.Constants;
import app.java.commons.utils.MapUtils;
import app.java.data.measurement.dao.*;
import app.java.data.measurement.dao.impl.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Print {
    private static String CSV_SEPARATOR = Constants.CSV_SEPARATOR;

    public static void printCSV(Map<String, Number> entries, String dimensionName) {
        System.out.println("dimension name" + CSV_SEPARATOR + "country code" + CSV_SEPARATOR + "year" + CSV_SEPARATOR + "value");

        for (Map.Entry<String, Number> entry : entries.entrySet()) {
            String code = MapUtils.getEntryCode(entry);
            Integer year = MapUtils.getEntryYear(entry);
            Number value = entry.getValue();
            System.out.println(dimensionName + CSV_SEPARATOR + code + CSV_SEPARATOR + year + CSV_SEPARATOR + formatNumber(value.doubleValue()));
        }
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
        EducationStatsDAO educationStatsDAO = new EducationStatsImpl();
        EnvironmentStatsDAO environmentStatsDAO = new EnvironmentStatsImpl();
        GovRightsStatsDAO govRightsStatsDAO = new GovRightsStatsImpl();
        HealthStatsDAO healthStatsDAO = new HealthStatsImpl();
        MainActivityStatsDAO mainActivityStatsDAO = new MainActivityStatsImpl();
        MaterialLivingStatsDAO materialLivingStatsDAO = new MaterialLivingStatsImpl();
        SafetyStatsDAO safetyStatsDAO = new SafetyStatsImpl();
        SocialActivityStatsDAO socialActivityStatsDAO = new SocialActivityStatsImpl();
        OverallExperienceStatsDAO overallExperienceStatsDAO = new OverallExperienceStatsImpl();

        printDimensionStatus(materialLivingStatsDAO.getInitList(), "MLC");
        printDimensionStatus(mainActivityStatsDAO.getInitList(), "PMA");
        printDimensionStatus(healthStatsDAO.getInitList(), "Health");
        printDimensionStatus(educationStatsDAO.getInitList(), "Education");
        printDimensionStatus(socialActivityStatsDAO.getInitList(), "LSI");
        printDimensionStatus(safetyStatsDAO.getInitList(), "Safety");
        printDimensionStatus(govRightsStatsDAO.getInitList(), "GBR");
        printDimensionStatus(environmentStatsDAO.getInitList(), "Environment");
        printDimensionStatus(overallExperienceStatsDAO.getInitList(), "Overall Exp");
        printDimensionStatus(GeneralStats.getInitList(), "General info");
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

        for (int i = 0; i < list.size(); i++) {
            Map<String, Number> entries = list.get(i);
            for (Map.Entry<String, Number> entry : entries.entrySet()) {
                Number value = entry.getValue();
                expected += 1;
                if (value != null)
                    available +=1;
            }
        }

        System.out.println(dimension + ":"
                + "\n\tAvailable: " + available
                + "\n\tExpected: " + expected);
    }

    private static String formatNumber(double number) {
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
