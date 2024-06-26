package ro.webdata.qoli.aggr.stats.dimensions;

import ro.webdata.qoli.aggr.stats.constants.Constants;

public class QoLIPaths {
    public static final String QOLI_FILE_NAME = "qoli";

    /**
     * Get the directory name of the input series type
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @return The directory name of the input series type
     */
    public static String getSeriesDirectory(String seriesType) {
        if (seriesType.equals(Constants.SERIES_TYPE_REGION)) {
            return "regions";
        }
        return "countries";
    }
}
