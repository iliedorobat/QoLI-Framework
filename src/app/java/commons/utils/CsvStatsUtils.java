package app.java.commons.utils;

import app.java.commons.constants.EnvConst;
import app.java.commons.dimensions.QoLIPaths;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import static app.java.commons.constants.Constants.*;

public class CsvStatsUtils {
    private static final int ROUNDING_PLACES = 2;

    /**
     * Generate a list with the relative or the absolute individual variations (@ro: variatile individuale
     * relative ori absolute) calculated as between two adjacent terms for the same country code:<br/>
     *      * (entry[i] / entry[i-1] * 100) - 100;
     *      * entry[i] - entry[i-1].
     *
     * @param mainMap The initialized map (see Initializer.initMap);
     * @param isRelative Specify if the calculated variation is relative (or absolute)
     *
     * @return The list of the individual variations
     */
    public static Map<String, List<Number>> generateVariation(
            Map<String, Number> mainMap,
            boolean isRelative
    ) {
        Map<String, List<Number>> deviationList = new LinkedHashMap<>();

        for (String code : EU28_MEMBERS) {
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
     * @return Data prepared for use in Word charts
     */
    public static StringBuilder generateChartData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            String direction
    ) {
        if (direction.equals(DIRECTION_ROW)) {
            return generateChartRows(entries, membersList, seriesType, directoryName);
        }

        return generateChartColumns(entries, membersList, seriesType, directoryName);
    }

    /**
     * Generate CSV data that can be imported into Word charts
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param directoryName Directory name of the target dimension/indicator
     * @return Data prepared for use in Word charts
     */
    private static StringBuilder generateChartColumns(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName
    ) {
        String header = "--- " + seriesType + " --- " + directoryName + " ---" +
                "\nCountries" + CSV_SEPARATOR;
        StringBuilder output = new StringBuilder(header);
        int length = membersList.length;

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            output.append(year);

            if (year < EnvConst.MAX_YEAR) {
                output.append(CSV_SEPARATOR);
            }
        }
        output.append("\n");

        for (int i = 0; i < length; i++) {
            String code = membersList[i];
            StringBuilder line = new StringBuilder(code + CSV_SEPARATOR);

            for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
                String key = code + "_" + year;
                Number value = entries.get(key);

                DecimalFormat df = new DecimalFormat("#,###.####", new DecimalFormatSymbols(Locale.ENGLISH));
                line.append(df.format(value));

                if (year < EnvConst.MAX_YEAR) {
                    line.append(CSV_SEPARATOR);
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
     * @return Data prepared for use in Word charts
     */
    private static StringBuilder generateChartRows(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName
    ) {
        String header = "--- " + seriesType + " --- " + directoryName + " ---" +
                "\nYears" + CSV_SEPARATOR;
        StringBuilder output = new StringBuilder(header);
        int length = membersList.length;

        for (int i = 0; i < length; i++) {
            String code = membersList[i];
            output.append(code);

            if (i < length - 1)
                output.append(CSV_SEPARATOR);
        }
        output.append("\n");

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            StringBuilder line = new StringBuilder(year + CSV_SEPARATOR);

            for (int i = 0; i < length; i++) {
                String code = membersList[i];
                String key = code + "_" + year;
                Number value = entries.get(key);

                line.append(value);

                if (i < length - 1)
                    line.append(CSV_SEPARATOR);
            }
            output.append(line).append("\n");
        }

        return output;
    }

    /**
     * Export the prepared chart data to a CSV file
     * @param entries The map with target dimension data
     * @param membersList The list of countries/regions
     * @param seriesType The type of series ("country" or "region")
     * @param directoryName Directory name of the target dimension/indicator
     * @param direction Display years on rows or columns (ROW or COLUMN)
     * @param preparedIndicators A map containing indicators which make up the target dimension
     * @param calculateIndicators Calculate or not the indicators that make up the QoLI dimensions
     */
    public static void writeChartData(
            Map<String, Number> entries,
            String[] membersList,
            String seriesType,
            String directoryName,
            String direction,
            HashMap<String, Map<String, Number>> preparedIndicators,
            boolean calculateIndicators
    ) {
        StringBuilder sb = CsvStatsUtils.generateChartData(entries, membersList, seriesType, directoryName, direction);
        String seriesDirectory = QoLIPaths.getSeriesDirectory(seriesType);
        String fullPath = String.join(File.separator, PREPARED_DATASET_PATH, "csv", seriesDirectory);
        FileUtils.writeToFile(sb, fullPath, directoryName, CSV_EXTENSION);

        if (calculateIndicators && preparedIndicators != null) {
            preparedIndicators.forEach((indicatorName, value) -> {
                String indicatorFullPath = String.join(File.separator, fullPath, directoryName);
                FileUtils.writeToFile(sb, indicatorFullPath, indicatorName, CSV_EXTENSION);
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
