package app.java.aggr.commons.dimensions;

import static app.java.aggr.commons.constants.Constants.SERIES_TYPE_REGION;

public class QoLIPaths {
    public static final String QOLI_FILE_NAME = "qoli";

    /**
     * Get the directory name of the input series type
     * @param seriesType The series type
     * @return The directory name of the input series type
     */
    public static String getSeriesDirectory(String seriesType) {
        if (seriesType.equals(SERIES_TYPE_REGION)) {
            return "regions";
        }
        return "countries";
    }
}
