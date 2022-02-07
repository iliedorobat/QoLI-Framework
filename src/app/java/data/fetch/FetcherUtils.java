package app.java.data.fetch;

import app.java.commons.constants.EnvConst;
import app.java.commons.constants.ParamsConst;
import app.java.commons.utils.MapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class FetcherUtils {
    /**
     * Add new parameters to the params list
     * @param params The parameters list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, String[] values) {
        for (String value : values) {
            params.put(propertyName, value);
        }
    }

    // TODO: documentation:
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, HashMap<String, String> valuesMap) {
        ArrayList<String> values = new ArrayList<>(valuesMap.values());
        for (String value : values) {
            params.put(propertyName, value);
        }
    }

    // TODO: documentation: Filter the keys added by getMainHttpParams and return the list of unique keys
    public static ArrayList<String> getFilteredParamsKeys(MultiValuedMap<String, String> params) {
        ArrayList<String> paramsKeys = MapUtils.getUniqueKeys(params);
        paramsKeys.remove(ParamsConst.LANG);
        paramsKeys.remove(ParamsConst.GEO);
        return paramsKeys;
    }

    /**
     * Get the main parameters
     *
     * @return
     */
    public static MultiValuedMap<String, String> getMainHttpParams(String[] countries) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put(ParamsConst.LANG, "en");

        if (EnvConst.IS_TESTING) {
            params.put(ParamsConst.GEO, "RO");
            params.put(ParamsConst.TIME, "2015");
        } else {
            addParams(params, ParamsConst.GEO, countries);
        }

        return params;
    }

    // TODO: documentation:
    public static MultiValuedMap<String, String> consolidateHttpParams(MultiValuedMap<String, String> indicatorParams, String[] countries) {
        MultiValuedMap<String, String> httpParams = getMainHttpParams(countries);
        httpParams.putAll(indicatorParams);
        return httpParams;
    }

    /**
     * Percentage of the population rating their satisfaction as high, medium or low<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @return
     */
    public static StringBuilder getSatisfactionRatio(MultiValuedMap<String, String> params) {
        return Fetcher.fetchData("ilc_pw05", params);
    }

    /**
     * Participation in formal or informal voluntary activities or active citizenship<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015
     *
     */
    public static StringBuilder getActivePeopleRatio(MultiValuedMap<String, String> params) {
        return Fetcher.fetchData("ilc_scp19", params);
    }

}
