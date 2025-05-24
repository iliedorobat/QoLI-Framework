package ro.webdata.qoli.aggr.data.stats;

import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.MathUtils;

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
     * @param populationMap Total population (CommonStats.population)
     * @param initMap The initialized map (see Initializer.initMap)
     * @return An ordered map with aggregated data
     */
    public static Map<String, Number> preparePerThousandInhabitant(Map<String, Number> populationMap, Map<String, Number> initMap) {
        Map<String, Number> generatedMap = new TreeMap<>(new MapOrder());
        Map<String, Number> preparedMap = Preparation.prepareData(initMap);

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                Number value = MathUtils.generatePerThousandInhabitants(populationMap, key, preparedMap.get(key).doubleValue());
                generatedMap.put(key, value);
            }
        }

        return generatedMap;
    }

    /**
     * Transform all values into values per ten thousand inhabitants
     *
     * @param populationMap Total population (CommonStats.population)
     * @param initMap The initialized map (see Initializer.initMap)
     * @return An ordered map with aggregated data
     */
    public static Map<String, Number> preparePerTenThousandInhabitants(Map<String, Number> populationMap, Map<String, Number> initMap) {
        Map<String, Number> generatedMap = new TreeMap<>(new MapOrder());
        Map<String, Number> preparedMap = prepareData(initMap);

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                Number value = MathUtils.generatePerTenThousandInhabitants(populationMap, key, preparedMap.get(key).doubleValue());
                generatedMap.put(key, value);
            }
        }

        return generatedMap;
    }

    /**
     * *** used on offences ratio ***<br/><br/>
     * Fill the missing values.
     *
     * @param mainMap The initialized map (see Initializer.initMap)
     * @param countryCodes The list of country codes
     * @return Prepared map
     */
    public static Map<String, Number> prepareData(Map<String, Number> mainMap, String[] countryCodes) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (Map.Entry<String, Number> entry : mainMap.entrySet()) {
            preparedMap.put(entry.getKey(), entry.getValue());
        }

        for (String code : countryCodes) {
            fillRightNullValues(preparedMap, code);
            fillLeftNullValues(preparedMap, code);
            fillNullValues(mainMap, preparedMap, code);
        }

        return filterMap(preparedMap);
    }

    /**
     * Add values for the years that have null values for a specified country code.
     *
     * @param mainMap The initialized map (see Initializer.initMap)
     * @param preparedMap The prepared map containing all entries
     * @param code The country code for which the ratio is calculated
     */
    private static void fillNullValues(
            Map<String, Number> mainMap,
            Map<String, Number> preparedMap,
            String code
    ) {
        for (int year = EnvState.INIT_MAP_MIN_YEAR; year <= EnvState.INIT_MAP_MAX_YEAR; year++) {
            String key = MapUtils.generateKey(code, year);
            Number value = preparedMap.get(key);
            Double euAverage = calculateEuAverage(mainMap, year);
            addKeyValue(preparedMap, key, value, euAverage);
        }

        // E.g.: Health => alcoholic ratio
        fillRightNullValues(preparedMap, code);
        fillLeftNullValues(preparedMap, code);
    }

    /**
     * Calculate the share of PPS compared to EU average.
     *
     * @param entries The map containing prepared data for a specific dimension
     * @return Map containing the PPS Ratio
     */
    public static Map<String, Number> preparePpsRatio(Map<String, Number> entries) {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                Number value = Preparation.calculatePpsRatio(entries, code, year);
                preparedMap.put(key, value);
            }
        }

        return preparedMap;
    }

    /**
     * Calculate the share of PPS compared to EU average.
     *
     * @param entries The map containing prepared data for a specific dimension
     * @param code The country code for which the ratio is calculated
     * @param year The year for which the ratio is calculated
     * @return PPS Ratio
     */
    private static Double calculatePpsRatio(Map<String, Number> entries, String code, int year) {
        Double euAverage = calculateEuAverage(entries, year);
        String key = MapUtils.generateKey(code, year);
        Number value = entries.get(key);

        if (euAverage == null) {
            throw new Error("The \"euAverage\" variable is null.");
        }

        return value.doubleValue() / euAverage * 100;
    }

    /**
     * Calculate the average of the EU
     *
     * @param mainMap The initialized map (see Initializer.initMap)
     * @param year The year for which the average is calculated
     * @return Average of the EU
     */
    public static Double calculateEuAverage(Map<String, Number> mainMap, int year) {
        int count = 0;
        double sum = 0;

        for (Map.Entry<String, Number> entry : mainMap.entrySet()) {
            Number entryValue = entry.getValue();
            String entryKey = entry.getKey();

            if (entryValue != null && entryKey != null) {
                String entryYear = entryKey.split(Constants.KEY_SEPARATOR)[1];

                if (Integer.parseInt(entryYear) == year) {
                    sum += entryValue.doubleValue();
                    count ++;
                }
            }
        }

        if (count == 0)
            return null;

        return sum / count;
    }

    /**
     * Add values for next years that have null values for a specified country code<br/>
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
     *
     * @param preparedMap The prepared map containing all entries
     * @param code The country code for which the processing is being done
     */
    private static void fillRightNullValues(
            Map<String, Number> preparedMap,
            String code
    ) {
        Number prevValue = null;

        for (int year = EnvState.INIT_MAP_MIN_YEAR; year <= EnvState.INIT_MAP_MAX_YEAR; year++) {
            String key = MapUtils.generateKey(code, year);
            Number value = preparedMap.get(key);

            addKeyValue(preparedMap, key, value, prevValue);

            if (value != null)
                prevValue = value;
        }
    }

    /**
     * Add values for previous years that have null values for a specified country code<br/>
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
     *
     * @param preparedMap The prepared map containing all entries
     * @param code The country code for which the processing is being done
     */
    private static void fillLeftNullValues(
            Map<String, Number> preparedMap,
            String code
    ) {
        Number nextValue = null;

        for (int year = EnvState.INIT_MAP_MAX_YEAR; year >= EnvState.INIT_MAP_MIN_YEAR; year--) {
            String key = MapUtils.generateKey(code, year);
            Number value = preparedMap.get(key);

            addKeyValue(preparedMap, key, value, nextValue);

            if (value != null)
                nextValue = value;
        }
    }

    /**
     * Fill the output map only with data for the analyzed period
     *
     * @param preparedMap The prepared map containing all entries
     * @return Filtered map by the analyzed period
     */
    public static Map<String, Number> filterMap(Map<String, Number> preparedMap) {
        Map<String, Number> filteredMap = new TreeMap<>(new MapOrder());

        for (Map.Entry<String, Number> entry : preparedMap.entrySet()) {
            int year = MapUtils.getEntryYear(entry);

            if (year >= EnvState.MIN_YEAR & year <= EnvState.MAX_YEAR) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredMap;
    }

    /**
     * Add an entry in the analyzed map if it's missing
     *
     * @param map The map
     * @param key The key
     * @param value The value
     * @param savedValue A buffer value:<br/>
     *                  * the previous value for the case of "fillRightNullValues";<br/>
     *                  * the last value for the case of "fillLeftNullValues"
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
