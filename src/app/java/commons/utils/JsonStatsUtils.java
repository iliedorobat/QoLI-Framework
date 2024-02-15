package app.java.commons.utils;

import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JsonStatsUtils {
    /**
     * Generate TreeMap data that can be exported as a JSON
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @return Data prepared to be exported as a JSON
     */
    private static TreeMap<String, TreeMap<Integer, Number>> generateJsonData(
            Map<String, Number> entries,
            String[] membersList
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats = new TreeMap<>();
        int length = membersList.length;

        for (int i = 0; i < length; i++) {
            String code = membersList[i];
            TreeMap<Integer, Number> itemStats = new TreeMap<>();

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = code + "_" + year;
                Number value = entries.get(key);
                itemStats.put(year, value);
            }
            stats.put(code, itemStats);
        }

        return stats;
    }

    /**
     * Export the prepared chart data to a JSON file
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of series ("country" or "region")
     * @param directoryName Directory name of the target dimension/indicator
     * @param preparedIndicators A map containing indicators which make up the target dimension
     */
    public static void writeJsonData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            HashMap<String, Map<String, Number>> preparedIndicators
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats = JsonStatsUtils.generateJsonData(entries, membersList);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            StringBuilder data = new StringBuilder(
                    objectMapper.writeValueAsString(stats)
            );
            String seriesDirectory = CsvStatsUtils.getSeriesDirectory(seriesType);
            String fullPath = String.join(File.separator, Constants.PREPARED_DATASET_PATH, "json", seriesDirectory);
            FileUtils.writeToFile(data, fullPath, directoryName, Constants.JSON_EXTENSION);

            // TODO:
//            if (preparedIndicators != null) {
//                preparedIndicators.forEach((indicatorName, value) -> {
//                    // TODO: check
//                    String indicatorFullPath = fullPath + StatsUtils.getDimensionSubPath(dimensionName);
//                    FileUtils.writeToFile(data, indicatorFullPath, indicatorName, Constants.JSON_EXTENSION);
//                });
//            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
