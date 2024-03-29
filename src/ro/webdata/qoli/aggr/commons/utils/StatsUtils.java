package ro.webdata.qoli.aggr.commons.utils;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.constants.Constants;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

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
        return seriesType.equals(Constants.SERIES_TYPE_REGION)
                ? StatsUtils.aggregateRegions(entries)
                : entries;
    }

    public static Number getValue(Map<String, Number> entries, String code, int year) {
        String key = code + "_" + year;
        Number value = entries.get(key);

        // Handle offences data
        if (value == null && code.equals("UK")) {
            double ukSum = 0;

            ukSum += entries.get("UKC-L" + "_" + year).doubleValue();
            ukSum += entries.get("UKM" + "_" + year).doubleValue();
            ukSum += entries.get("UKN" + "_" + year).doubleValue();

            value = ukSum;
        }

        return value;
    }
}
