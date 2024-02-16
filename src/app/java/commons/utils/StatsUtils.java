package app.java.commons.utils;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.SERIES_TYPE_REGION;

public class StatsUtils {
    public static Map<String, Number> aggregateRegions(Map<String, Number> entries) {
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

        return consolidatedList;
    }

    public static Map<String, Number> getEntries(Map<String, Number> entries, String seriesType) {
        return seriesType.equals(SERIES_TYPE_REGION)
                ? StatsUtils.aggregateRegions(entries)
                : entries;
    }
}
