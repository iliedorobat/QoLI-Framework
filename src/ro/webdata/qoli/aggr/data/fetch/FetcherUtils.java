package ro.webdata.qoli.aggr.data.fetch;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.constants.ParamsNames;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FetcherUtils {
    /**
     * Add new parameters to the "params" list
     *
     * @param params List of parameters
     * @param propertyName The name of the added property
     * @param values The list with values that should be added
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, String[] values) {
        for (String value : values) {
            params.put(propertyName, value);
        }
    }
    /**
     * Add new parameters to the "params" list
     *
     * @param params List of parameters
     * @param propertyName The name of the added property
     * @param values The list with values that should be added
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, Collection<String> values) {
        for (String value : values) {
            params.put(propertyName, value);
        }
    }


    /**
     * Add new parameters to the "params" list
     *
     * @param params List of parameters
     * @param propertyName The name of the added property
     * @param valuesMap Map containing target properties
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, HashMap<String, String> valuesMap) {
        ArrayList<String> values = new ArrayList<>(valuesMap.values());
        for (String value : values) {
            params.put(propertyName, value);
        }
    }

    /**
     * Filter the keys added by getMainHttpParams and return a list of unique keys.
     *
     * @param params List of parameters
     * @return Unique keys from which "lang" and "geo" were filtered out
     */
    public static ArrayList<String> filterParamsKeys(MultiValuedMap<String, String> params) {
        ArrayList<String> paramsKeys = MapUtils.getUniqueKeys(params);
        paramsKeys.remove(ParamsNames.LANG);
        paramsKeys.remove(ParamsNames.GEO);
        return paramsKeys;
    }

    /**
     * Consolidate the main parameters and the query parameters specific to the analyzed indicator into a single entity.
     *
     * @param indicatorParams The map containing the query parameters specific to the analyzed indicator
     * @param countryCodes The list of country codes
     * @return Map containing both the main query parameters and the query parameters specific to the analyzed indicator
     */
    public static MultiValuedMap<String, String> consolidateHttpParams(MultiValuedMap<String, String> indicatorParams, String[] countryCodes) {
        MultiValuedMap<String, String> httpParams = prepareMainHttpParams(countryCodes);
        httpParams.putAll(indicatorParams);
        return httpParams;
    }

    /**
     * Prepare the main params ("geo" for production and "geo" & "time" for testing).
     *
     * @return Map containing the main params
     */
    public static MultiValuedMap<String, String> prepareMainHttpParams(String[] countries) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put(ParamsNames.LANG, "en");

        if (EnvConst.IS_TESTING) {
            params.put(ParamsNames.GEO, "RO");
            params.put(ParamsNames.TIME, "2015");
        } else {
            addParams(params, ParamsNames.GEO, countries);
        }

        return params;
    }
}
