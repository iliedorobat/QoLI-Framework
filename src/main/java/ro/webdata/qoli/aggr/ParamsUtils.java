package ro.webdata.qoli.aggr;

import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParamsUtils {
    public static boolean contains(List<String> pairs, String comparator) {
        for (String pair : pairs) {
            String[] values = pair.split("=");

            // E.g.: --calculateIndicators
            if (values.length == 1) {
                String key = values[0];
                return comparator.equals(key);
            }
            // E.g.: --calculate=true
            else if (values.length > 1) {
                String key = values[0];
                String value = values[1];

                if (comparator.equals(key)) {
                    return Boolean.valueOf(value);
                }
            }
        }

        return false;
    }

    public static List<String> getAggregations(List<String> pairs) {
        for (String pair : pairs) {
            String[] values = pair.split("=");
            String key = values[0];

            if (key.equals("--aggr") & values.length > 1) {
                String value = values[1];

                String[] aggrList = value.substring(1, value.length() - 1).split(",");
                return Arrays.asList(aggrList);
            }
        }

        return new ArrayList<>();
    }

    public static String getDirection(List<String> pairs) {
        if (pairs.contains("--direction=" + Constants.DIRECTION_ROW)) {
            return Constants.DIRECTION_ROW;
        }
        return Constants.DIRECTION_COLUMN;
    }

    public static String getSeriesType(List<String> pairs) {
        if (pairs.contains("--seriesType=" + Constants.SERIES_TYPE_COUNTRY)) {
            return Constants.SERIES_TYPE_COUNTRY;
        }
        if (pairs.contains("--seriesType=" + Constants.SERIES_TYPE_REGION)) {
            return Constants.SERIES_TYPE_REGION;
        }
        return null;
    }

    public static int getYear(List<String> pairs, String comparator) {
        for (String pair : pairs) {
            String[] values = pair.split("=");
            String key = values[0];

            if (key.equals(comparator) & values.length > 1) {
                String value = values[1];
                return Integer.parseInt(value);
            }
        }

        if (comparator.equals("--startYear"))
            return EnvConst.MIN_YEAR;

        if (comparator.equals("--endYear"))
            return EnvConst.MAX_YEAR;

        return -1;
    }
}
