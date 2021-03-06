package app.java.commons.utils;

import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatsUtils {
    private static final int EU28_MEMBERS_LENGTH = Constants.EU28_MEMBERS.length;
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
     * @return
     */
    public static Map<String, List<Number>> generateVariation(
            Map<String, Number> mainMap,
            boolean isRelative
    ) {
        Map<String, List<Number>> deviationList = new LinkedHashMap<>();

        for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
            String code = Constants.EU28_MEMBERS[i];
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
     * @param dimensionName The name of the target dimension
     */
    public static StringBuilder generateChartData(Map<String, Number> entries, String dimensionName) {
        String header = "--- " + dimensionName + " ---" +
                "\nYears" + Constants.CSV_SEPARATOR;
        StringBuilder output = new StringBuilder(header);

        for (int i = 0; i < EU28_MEMBERS_LENGTH; i++) {
            String code = Constants.EU28_MEMBERS[i];
            output.append(code);

            if (i < EU28_MEMBERS_LENGTH - 1)
                output.append(Constants.CSV_SEPARATOR);
        }
        output.append("\n");

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            StringBuilder line = new StringBuilder(year + Constants.CSV_SEPARATOR);
            for (int i = 0; i < EU28_MEMBERS_LENGTH; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = code + "_" + year;
                Number value = entries.get(key);

                line.append(value);

                if (i < EU28_MEMBERS_LENGTH - 1)
                    line.append(Constants.CSV_SEPARATOR);
            }
            output.append(line).append("\n");
        }

        return output;
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
