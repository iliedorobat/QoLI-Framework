package app.java.data.measurement.preparation;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.data.measurement.MeasureUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Basic methods for initializing lists (add "null" value for the missing keys)
 */
public class Initializer {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    /**
     * Create a new sorted consolidated map with values for all the possible keys for a LEVERAGE
     * PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required
     * if in the analyzed period there are any data for a country code)<br/>
     *
     * @param globalParamsValues The global allowed query values (the allowed query values
     *                           excepting the year and the country code)
     * @param filePath The full access path to the desired file
     * @return A new sorted consolidated map with no missing keys
     */
    public static Map<String, Number> initConsolidatedList(String[] globalParamsValues, String filePath) {
        Map<String, Number> consolidatedList = MeasureUtils.consolidateList(globalParamsValues, filePath);
        return initMap(consolidatedList);
    }

    /**
     * Create a new sorted map with values for all the possible keys for a LEVERAGE PERIOD OF TIME<br/>
     * <b>A LEVERAGE PERIOD OF TIME is an extended period of the analyzed period</b> (required if in the
     * analyzed period there are any data for a country code)<br/>
     * If the key is missing form the original map, set a default value (<b>null</b>)<br/>
     * A key is composed by the country code and the year (e.g.: AT_2010; RO_2015 etc.)
     *
     * @param originalMap The original input map
     * @return A new sorted map with no missing keys
     */
    public static Map<String, Number> initMap(Map<String, Number> originalMap) {
        Map<String, Number> initMap = new TreeMap<>(new MapOrder());

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int year = EnvConst.INIT_MAP_MIN_YEAR; year <= EnvConst.INIT_MAP_MAX_YEAR; year++) {
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
     * @param code The current country code
     * @param year The current year
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
