package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.constants.Constants;

public class QoLIPaths {
    public static final String QOLI_FILE_NAME = "qoli";

    /**
     * Get the directory name of the input series type
     * @param seriesType The series type
     * @return The directory name of the input series type
     */
    public static String getSeriesDirectory(String seriesType) {
        if (seriesType.equals(Constants.SERIES_TYPE_REGION)) {
            return "regions";
        }
        return "countries";
    }
}
