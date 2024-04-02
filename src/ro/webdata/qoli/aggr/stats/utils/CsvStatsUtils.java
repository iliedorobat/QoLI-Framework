package ro.webdata.qoli.aggr.stats.utils;

import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIPaths;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class CsvStatsUtils {
    private static final int ROUNDING_PLACES = 2;

    /**
     * Generate a list with the relative or the absolute individual variations (@ro: variatile individuale
     * relative ori absolute) calculated as between two adjacent terms for the same country code:<br/>
     *      * (entry[i] / entry[i-1] * 100) - 100;
     *      * entry[i] - entry[i-1].
     *
     * @param mainMap The initialized map (see Initializer.initMap)
     * @param isRelative Specify if the calculated variation is relative (or absolute)
     *
     * @return The list of the individual variations
     */
    public static Map<String, List<Number>> generateVariation(
            Map<String, Number> mainMap,
            boolean isRelative
    ) {
        Map<String, List<Number>> deviationList = new LinkedHashMap<>();

        for (String code : Constants.EU28_MEMBERS) {
            List<Number> countryDeviationList = new LinkedList<>();
            Object[] entries = mainMap.entrySet().toArray();

            // Start from second record
            for (int j = 1; j < entries.length; j++) {
                Map.Entry<String, Number> entry = (Map.Entry<String, Number>) entries[j];
                String entryCode = MapUtils.getEntryCode(entry);
                Number entryValue = entry.getValue();

                Map.Entry<String, Number> prevEntry = (Map.Entry<String, Number>) entries[j-1];
                String prevEntryCode = MapUtils.getEntryCode(prevEntry);
                Number prevEntryValue = prevEntry.getValue();

                assert entryCode != null;
                assert prevEntryCode != null;
                if (entryCode.equals(code) && prevEntryCode.equals(code)) {
                    Number deviation = calculateVariation(
                            entryValue, prevEntryValue, isRelative
                    );
                    countryDeviationList.add(deviation);
                }
            }

            deviationList.put(code, countryDeviationList);
        }

        return deviationList;
    }

    /**
     * Generate CSV data that can be imported into Word charts
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param directoryName Directory name of the target dimension/indicator
     * @param direction Display years on rows or columns (ROW or COLUMN)
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Data prepared for use in Word charts
     */
    public static StringBuilder generateChartData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            String direction,
            int startYear,
            int endYear
    ) {
        Map<String, Number> data = StatsUtils.getEntries(entries, seriesType);

        if (direction.equals(Constants.DIRECTION_ROW)) {
            return generateChartRows(data, membersList, seriesType, directoryName, startYear, endYear);
        }

        return generateChartColumns(data, membersList, seriesType, directoryName, startYear, endYear);
    }

    /**
     * Generate CSV data that can be imported into Word charts
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param directoryName Directory name of the target dimension/indicator
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Data prepared for use in Word charts
     */
    private static StringBuilder generateChartColumns(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            int startYear,
            int endYear
    ) {
        String header = "--- " + seriesType + " --- " + directoryName + " ---" +
                "\nCountries" + Constants.CSV_SEPARATOR;
        StringBuilder output = new StringBuilder(header);

        for (int year = startYear; year <= endYear; year++) {
            output.append(year);

            if (year < endYear) {
                output.append(Constants.CSV_SEPARATOR);
            }
        }
        output.append("\n");

        for (String code : membersList) {
            StringBuilder line = new StringBuilder(code + Constants.CSV_SEPARATOR);

            for (int year = startYear; year <= endYear; year++) {
                Number value = StatsUtils.generateJsonValue(entries, code, year);
                DecimalFormat df = new DecimalFormat("#,###.####", new DecimalFormatSymbols(Locale.ENGLISH));
                line.append(df.format(value));

                if (year < endYear) {
                    line.append(Constants.CSV_SEPARATOR);
                }
            }
            output.append(line).append("\n");
        }

        return output;
    }

    /**
     * Generate CSV data that can be imported into Word charts
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param directoryName Directory name of the target dimension/indicator
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Data prepared for use in Word charts
     */
    private static StringBuilder generateChartRows(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            int startYear,
            int endYear
    ) {
        String header = "--- " + seriesType + " --- " + directoryName + " ---" +
                "\nYears" + Constants.CSV_SEPARATOR;
        StringBuilder output = new StringBuilder(header);
        int length = membersList.length;

        for (int i = 0; i < length; i++) {
            String code = membersList[i];
            output.append(code);

            if (i < length - 1)
                output.append(Constants.CSV_SEPARATOR);
        }
        output.append("\n");

        for (int year = startYear; year <= endYear; year++) {
            StringBuilder line = new StringBuilder(year + Constants.CSV_SEPARATOR);

            for (int i = 0; i < length; i++) {
                String code = membersList[i];
                String key = code + "_" + year;
                Number value = entries.get(key);

                line.append(value);

                if (i < length - 1)
                    line.append(Constants.CSV_SEPARATOR);
            }
            output.append(line).append("\n");
        }

        return output;
    }

    /**
     * Export the prepared chart data to a CSV file
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param directoryName Directory name of the target dimension/indicator
     * @param direction Display years on rows or columns (ROW or COLUMN)
     * @param preparedIndicators A map containing indicators which make up the target dimension
     * @param calculateIndicators Calculate or not the indicators that make up the QoLI dimensions
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     */
    public static void writeChartData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            String direction,
            HashMap<String, Map<String, Number>> preparedIndicators,
            boolean calculateIndicators,
            int startYear,
            int endYear
    ) {
        StringBuilder sb = CsvStatsUtils.generateChartData(entries, membersList, seriesType, directoryName, direction, startYear, endYear);
        String seriesDirectory = QoLIPaths.getSeriesDirectory(seriesType);
        String fullPath = String.join(File.separator, Constants.PREPARED_DATASET_PATH, "csv", seriesDirectory);
        FileUtils.writeToFile(sb, fullPath, directoryName, Constants.CSV_EXTENSION);

        if (calculateIndicators && preparedIndicators != null) {
            preparedIndicators.forEach((indicatorName, value) -> {
                StringBuilder indicatorSb = CsvStatsUtils.generateChartData(preparedIndicators.get(indicatorName), membersList, seriesType, directoryName, direction, startYear, endYear);
                String indicatorFullPath = String.join(File.separator, fullPath, directoryName);
                FileUtils.writeToFile(indicatorSb, indicatorFullPath, indicatorName, Constants.CSV_EXTENSION);
            });
        }
    }

    /**
     * Calculate the variation between two numbers
     *
     * @param entryValue The current value
     * @param prevEntryValue The previous value
     * @param isRelative Specify if the calculated variation is relative (or absolute)
     *
     * @return The variation between current value and the previous one:<br/>
     *      * a positive value means that the current values increased compared to the previous value;<br/>
     *      * a negative value means that the current values decreased compared to the previous value.
     */
    private static Number calculateVariation(Number entryValue, Number prevEntryValue, boolean isRelative) {
        Number deviation = null;

        if (entryValue != null & prevEntryValue != null) {
            if (isRelative) {
                double ratio = entryValue.doubleValue() / prevEntryValue.doubleValue() * 100;
                double diff = ratio - 100;
                deviation = MathUtils.round(diff, ROUNDING_PLACES);
            } else {
                double diff = entryValue.doubleValue() - prevEntryValue.doubleValue();
                deviation = MathUtils.round(diff, ROUNDING_PLACES);
            }
        }

        return deviation;
    }
}
