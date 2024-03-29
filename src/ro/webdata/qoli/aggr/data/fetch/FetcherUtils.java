package app.java.aggr.data.fetch;

import app.java.aggr.commons.constants.EnvConst;
import app.java.aggr.commons.constants.ParamsNames;
import app.java.aggr.commons.utils.MapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FetcherUtils {
    /**
     * Add new parameters to the params list
     * @param params The parameters list
     * @param propertyName The name of the added property
     * @param values The list with values that should be added
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, String[] values) {
        for (String value : values) {
            params.put(propertyName, value);
        }
    }
    /**
     * Add new parameters to the params list
     * @param params The parameters list
     * @param propertyName The name of the added property
     * @param values The list with values that should be added
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, Collection<String> values) {
        for (String value : values) {
            params.put(propertyName, value);
        }
    }


    /**
     * Add new parameters to the params list
     * @param params The parameters list
     * @param propertyName The name of the added property
     * @param valuesMap Map containing target properties
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, HashMap<String, String> valuesMap) {
        ArrayList<String> values = new ArrayList<>(valuesMap.values());
        for (String value : values) {
            params.put(propertyName, value);
        }
    }

    // TODO: documentation: Filter the keys added by getMainHttpParams and return the list of unique keys
    public static ArrayList<String> getFilteredParamsKeys(MultiValuedMap<String, String> params) {
        ArrayList<String> paramsKeys = MapUtils.getUniqueKeys(params);
        paramsKeys.remove(ParamsNames.LANG);
        paramsKeys.remove(ParamsNames.GEO);
        return paramsKeys;
    }

    /**
     * Get the main parameters
     *
     * @return
     */
    public static MultiValuedMap<String, String> getMainHttpParams(String[] countries) {
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

    // TODO: documentation:
    public static MultiValuedMap<String, String> consolidateHttpParams(MultiValuedMap<String, String> indicatorParams, String[] countries) {
        MultiValuedMap<String, String> httpParams = getMainHttpParams(countries);
        httpParams.putAll(indicatorParams);
        return httpParams;
    }
}
