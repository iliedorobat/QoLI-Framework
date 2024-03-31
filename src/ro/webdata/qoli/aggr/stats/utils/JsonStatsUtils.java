package ro.webdata.qoli.aggr.stats.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIPaths;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JsonStatsUtils {
    /**
     * Export the prepared chart data to a JSON file
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of series ("country" or "region")
     * @param directoryName Directory name of the target dimension/indicator
     * @param preparedIndicators A map containing indicators which make up the target dimension
     * @param calculateIndicators Calculate or not the indicators that make up the QoLI dimensions
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     */
    public static void writeJsonData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            HashMap<String, Map<String, Number>> preparedIndicators,
            boolean calculateIndicators,
            int startYear,
            int endYear
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats = generateJsonData(entries, membersList, seriesType, startYear, endYear);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            StringBuilder data = new StringBuilder(objectMapper.writeValueAsString(stats));
            String seriesDirectory = QoLIPaths.getSeriesDirectory(seriesType);
            String fullPath = String.join(File.separator, Constants.PREPARED_DATASET_PATH, "json", seriesDirectory);
            FileUtils.writeToFile(data, fullPath, directoryName, Constants.JSON_EXTENSION);

            if (calculateIndicators && preparedIndicators != null) {
                preparedIndicators.forEach((indicatorName, value) -> {
                    TreeMap<String, TreeMap<Integer, Number>> indicatorStats = generateJsonData(preparedIndicators.get(indicatorName), membersList, seriesType, startYear, endYear);
                    try {
                        StringBuilder indicatorData = new StringBuilder(objectMapper.writeValueAsString(indicatorStats));
                        String indicatorFullPath = String.join(File.separator, fullPath, directoryName);
                        FileUtils.writeToFile(indicatorData, indicatorFullPath, indicatorName, Constants.JSON_EXTENSION);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate TreeMap data that can be exported as a JSON
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The series type
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     *
     * @return Data prepared to be exported as a JSON
     */
    public static TreeMap<String, TreeMap<Integer, Number>> generateJsonData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            int startYear,
            int endYear
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats = new TreeMap<>();
        Map<String, Number> data = StatsUtils.getEntries(entries, seriesType);

        for (String code : membersList) {
            TreeMap<Integer, Number> itemStats = new TreeMap<>();

            for (int year = startYear; year <= endYear; year++) {
                itemStats.put(year, StatsUtils.getValue(data, code, year));
            }
            stats.put(code, itemStats);
        }

        return stats;
    }
}
