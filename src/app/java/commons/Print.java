package app.java.commons;

import app.java.commons.constants.Constants;

import java.util.List;
import java.util.Map;

public class Print {
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
}
