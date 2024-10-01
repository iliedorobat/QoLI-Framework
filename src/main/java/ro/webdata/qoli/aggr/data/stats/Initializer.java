package ro.webdata.qoli.aggr.data.stats;

import org.apache.commons.collections4.MultiValuedMap;
import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Basic methods for initializing lists (add "null" value for the missing keys)
 */
public class Initializer {
    /**
     * Create a new sorted map with values for all the possible keys for a LEVERAGE PERIOD
     * OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required
     * if in the analyzed period there is no data for a country code)<br/>
     * If the key is missing form the original map, set a default value (<b>null</b>)<br/>
     * A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)
     *
     * @param map The map
     * @return A new sorted map with no missing keys
     */
    public static Map<String, Number> initMap(
            Map<String, Number> map
    ) {
        return Initializer.initMap(map, Constants.EU28_MEMBERS);
    }

    /**
     * Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
     * PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required
     * if in the analyzed period there is no data for a country code)<br/>
     * If the key is missing form the original map, set a default value (<b>null</b>)<br/>
     * A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)
     *
     * @param mapsList The list of maps
     * @return A new sorted map with no missing keys
     */
    public static Map<String, Number> initConsolidatedMaps(
            ArrayList<Map<String, Number>> mapsList
    ) {
        Map<String, Number> consolidatedList = MergeUtils.consolidateMaps(mapsList);
        return initMap(consolidatedList);
    }

    /**
     * Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
     * PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required
     * if in the analyzed period there are any data for a country code)<br/>
     *
     * @param params The global allowed query values (the allowed query values
     *                           excepting the year and the country code)
     * @param filePath The full access path to the desired file
     * @return A new sorted consolidated map with no missing keys
     */
    public static Map<String, Number> initConsolidatedMap(
            MultiValuedMap<String, String> params,
            String filePath
    ) {
        return initConsolidatedMap(params, filePath, Constants.EU28_MEMBERS);
    }

    /**
     * *** used on offences ratio ***<br/><br/>
     *
     * Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
     * PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required
     * if in the analyzed period there are any data for a country code)<br/>
     *
     * @param params The global allowed query values (the allowed query values
     *                           excepting the year and the country code)
     * @param filePath The full access path to the desired file
     * @param countryCodes The list of country codes
     * @return A new sorted consolidated map with no missing keys
     */
    public static Map<String, Number> initConsolidatedMap(
            MultiValuedMap<String, String> params,
            String filePath,
            String[] countryCodes
    ) {
        Map<String, Number> consolidatedList = MergeUtils.consolidateMap(params, filePath);
        return initMap(consolidatedList, countryCodes);
    }

    /**
     * Create a new sorted map with values for all the possible keys for a LEVERAGE PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required if in the
     * analyzed period there is no data for a country code)<br/>
     * If the key is missing form the original map, set a default value (<b>null</b>)<br/>
     * A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)
     *
     * @param originalMap The original input map
     * @param countryCodes The list of country codes
     * @return A new sorted map with no missing keys
     */
    public static Map<String, Number> initMap(
            Map<String, Number> originalMap,
            String[] countryCodes
    ) {
        Map<String, Number> initMap = new TreeMap<>(new MapOrder());

        for (String code : countryCodes) {
            for (int year = EnvState.INIT_MAP_MIN_YEAR; year <= EnvState.INIT_MAP_MAX_YEAR; year++) {
                initEmptyData(originalMap, initMap, code, year);
            }
        }

        return initMap;
    }

    /**
     * Add a default value for the key that is missing from the original map
     *
     * @param originalMap The original input map
     * @param initMap The new map which is built by adding all the key-value pairs for the analysed period
     * @param code The country code for which the calculation is done
     * @param year The year for which the calculation is done
     */
    private static void initEmptyData(
            Map<String, Number> originalMap,
            Map<String, Number> initMap,
            String code,
            int year
    ) {
        String key = MapUtils.generateKey(code, year);

        for (Map.Entry<String, Number> entry : originalMap.entrySet()) {
            String entryKey = entry.getKey();

            if (entryKey.equals(key)) {
                initMap.put(key, entry.getValue());
            }
        }

        initMap.putIfAbsent(key, null);
    }
}
