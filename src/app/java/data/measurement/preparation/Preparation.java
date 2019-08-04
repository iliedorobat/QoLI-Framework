package app.java.data.measurement.preparation;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.data.measurement.MeasureUtils;

import java.util.Map;
import java.util.TreeMap;

public class Preparation {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    /**
     * Add values for the years that have null values<br/>
     *
     * E.g.:
     * <pre>
     *  {
     *      EU28_2011=null,
     *      EU28_2012=null,
     *      EU28_2013=93.3,
     *      EU28_2014=null,
     *      EU28_2015=94.4,
     *      EU28_2016=null,
     *      EU28_2017=null
     *  }
     * </pre>
     * in the first processing layer, data will be transformed to:
     * <pre>
     *  {
     *      EU28_2011=null,
     *      EU28_2012=null,
     *      EU28_2013=93.3,
     *      EU28_2014=93.3,
     *      EU28_2015=94.4,
     *      EU28_2016=94.4,
     *      EU28_2017=94.4
     *  }
     * </pre>
     * in the second processing layer, data will be transformed to:
     * <pre>
     *  {
     *      EU28_2011=93.3,
     *      EU28_2012=93.3,
     *      EU28_2013=93.3,
     *      EU28_2014=93.3,
     *      EU28_2015=94.4,
     *      EU28_2016=94.4,
     *      EU28_2017=94.4
     *  }
     * </pre>
     *
     * @param mainMap The initialized map (see Initializer.initMap) if it's at the first processing
     *                or the prepared map
     * @return Prepared map without null values
     */
    public static Map<String, Number> prepareData(Map<String, Number> mainMap) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        //TODO: for EU28 make a real average
        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];
            replaceRightNullValues(mainMap, preparedMap, code);
            replaceLeftNullValues(preparedMap, preparedMap, code);
        }

        return preparedMap;
    }

    /**
     * Add values for the next years that have null values for a specified country code<br/>
     * E.g.:
     * <pre>
     *  {
     *      EU28_2011=null,
     *      EU28_2012=null,
     *      EU28_2013=93.3,
     *      EU28_2014=null,
     *      EU28_2015=94.4,
     *      EU28_2016=null,
     *      EU28_2017=null
     *  }
     * </pre>
     * will be processed to:
     * <pre>
     *  {
     *      EU28_2011=null,
     *      EU28_2012=null,
     *      EU28_2013=93.3,
     *      EU28_2014=93.3,
     *      EU28_2015=94.4,
     *      EU28_2016=94.4,
     *      EU28_2017=94.4
     *  }
     * </pre>
     * @param mainMap The initialized map (see Initializer.initMap) if it's at the first processing
     *                or the prepared map
     * @param preparedMap
     * @param code The country code for which is made the processing
     */
    private static void replaceRightNullValues(
            Map<String, Number> mainMap,
            Map<String, Number> preparedMap,
            String code
    ) {
        Number prevValue = null;

        for (int year = EnvConst.INIT_MAP_MIN_YEAR; year <= EnvConst.INIT_MAP_MAX_YEAR; year++) {
            String key = MeasureUtils.generateKey(code, year);
            Number value = mainMap.get(key);

            addKeyValue(mainMap, preparedMap, key, year, value, prevValue);

            if (value != null)
                prevValue = value;
        }
    }

    /**
     * Add values for the next years that have null values for a specified country code<br/>
     * E.g.:
     * <pre>
     *  {
     *      EU28_2011=null,
     *      EU28_2012=null,
     *      EU28_2013=93.3,
     *      EU28_2014=null,
     *      EU28_2015=94.4,
     *      EU28_2016=null,
     *      EU28_2017=null
     *  }
     * </pre>
     * will be processed to:
     * <pre>
     *  {
     *      EU28_2011=93.3,
     *      EU28_2012=93.3,
     *      EU28_2013=93.3,
     *      EU28_2014=94.4,
     *      EU28_2015=94.4,
     *      EU28_2016=null,
     *      EU28_2017=null
     *  }
     * </pre>
     * @param mainMap The initialized map (see Initializer.initMap) if it's at the first processing
     *                or the prepared map
     * @param preparedMap
     * @param code The country code for which is made the processing
     */
    private static void replaceLeftNullValues(
            Map<String, Number> mainMap,
            Map<String, Number> preparedMap,
            String code
    ) {
        Number lastValue = null;

        for (int year = EnvConst.INIT_MAP_MAX_YEAR; year >= EnvConst.INIT_MAP_MIN_YEAR; year--) {
            String key = MeasureUtils.generateKey(code, year);
            Number value = mainMap.get(key);

            addKeyValue(mainMap, preparedMap, key, year, value, lastValue);

            if (value != null)
                lastValue = value;
        }
    }

    private static void addKeyValue(
            Map<String, Number> mainMap,
            Map<String, Number> preparedMap,
            String key,
            int year,
            Number value,
            Number prevValue
    ) {
        // Fill the output map only with data for analyzed period
        if (year >= EnvConst.MIN_YEAR & year <= EnvConst.MAX_YEAR) {
            if (mainMap.get(key) == null) {
                if (value != null)
                    preparedMap.put(key, value);
                else if (prevValue != null)
                    preparedMap.put(key, prevValue);
            }
        }
    }
}
