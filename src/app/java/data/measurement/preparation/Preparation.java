package app.java.data.measurement.preparation;

import app.java.commons.MapOrder;
import app.java.commons.utils.MapUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MathUtils;

import java.util.Map;
import java.util.TreeMap;

public class Preparation {
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
        return prepareData(mainMap, Constants.EU28_MEMBERS);
    }

    /**
     * Transform all values into values per thousand inhabitants
     *
     * @param initMap The initialized map (see Initializer.initMap)
     * @return An ordered map with aggregated data
     */
    public static Map<String, Number> preparePerThousandInhabitant(Map<String, Number> initMap) {
        Map<String, Number> generatedMap = new TreeMap<>(new MapOrder());
        Map<String, Number> preparedMap = Preparation.prepareData(initMap);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);
                Number value = MathUtils.generateThousandPerInhabitant(key, preparedMap.get(key).doubleValue());
                generatedMap.put(key, value);
            }
        }

        return generatedMap;
    }

    /**
     * Transform the value into a value per thousand inhabitants
     *
     * @param initMap The initialized map (see Initializer.initMap)
     * @return An ordered map with aggregated data
     */
    public static Map<String, Number> preparePerTenThousandInhabitants(Map<String, Number> initMap) {
        Map<String, Number> generatedMap = new TreeMap<>(new MapOrder());
        Map<String, Number> preparedMap = prepareData(initMap);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);
                Number value = MathUtils.generateTenThousandPerInhabitant(key, preparedMap.get(key).doubleValue());
                generatedMap.put(key, value);
            }
        }

        return generatedMap;
    }

    // used for offences ratio
    public static Map<String, Number> prepareData(Map<String, Number> mainMap, String[] countries) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (Map.Entry<String, Number> entry : mainMap.entrySet()) {
            preparedMap.put(entry.getKey(), entry.getValue());
        }

        //TODO: make a real average for EU28
        for (int i = 0; i < countries.length; i++) {
            String code = countries[i];
            replaceRightNullValues(preparedMap, code);
            replaceLeftNullValues(preparedMap, code);
        }

        return filterMap(preparedMap);
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
     * @param preparedMap
     * @param code The country code for which is made the processing
     */
    private static void replaceRightNullValues(
            Map<String, Number> preparedMap,
            String code
    ) {
        Number prevValue = null;

        for (int year = EnvConst.INIT_MAP_MIN_YEAR; year <= EnvConst.INIT_MAP_MAX_YEAR; year++) {
            String key = MapUtils.generateKey(code, year);
            Number value = preparedMap.get(key);

            addKeyValue(preparedMap, key, value, prevValue);

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
     * @param preparedMap
     * @param code The country code for which is made the processing
     */
    private static void replaceLeftNullValues(
            Map<String, Number> preparedMap,
            String code
    ) {
        Number nextValue = null;

        for (int year = EnvConst.INIT_MAP_MAX_YEAR; year >= EnvConst.INIT_MAP_MIN_YEAR; year--) {
            String key = MapUtils.generateKey(code, year);
            Number value = preparedMap.get(key);

            addKeyValue(preparedMap, key, value, nextValue);

            if (value != null)
                nextValue = value;
        }
    }

    /**
     * Fill the output map only with data for the analyzed period
     * @param preparedMap The prepared map with all the entries
     * @return Filtered map by the analyzed period
     */
    public static Map<String, Number> filterMap(Map<String, Number> preparedMap) {
        Map<String, Number> filteredMap = new TreeMap<>(new MapOrder());

        for (Map.Entry<String, Number> entry : preparedMap.entrySet()) {
            int year = MapUtils.getEntryYear(entry);

            if (year >= EnvConst.MIN_YEAR & year <= EnvConst.MAX_YEAR) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredMap;
    }

    /**
     * Add an entry in the analyzed map if it's missing
     * @param map The map
     * @param key The key
     * @param value The value
     * @param savedValue A buffer value:<br/>
     *                  * the previous value for the case of "replaceRightNullValues";<br/>
     *                  * the last value for the case of "replaceLeftNullValues"
     */
    private static void addKeyValue(
            Map<String, Number> map,
            String key,
            Number value,
            Number savedValue
    ) {
        if (map.get(key) == null) {
            if (value != null)
                map.put(key, value);
            else if (savedValue != null)
                map.put(key, savedValue);
        }
    }
}
