package app.java.data.fetch;

import app.java.commons.constants.EnvConst;
import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;

public class FetcherUtils {
    public static String getGeoParams() {
        String output = "";

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            output += "geo=" + EU28_MEMBERS[i];

            if (i < EU28_MEMBERS.length - 1) {
                output += "&";
            }
        }

        return output;
    }

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

    /**
     * Get the main parameters
     *
     * @return
     */
    public static MultiValuedMap<String, String> getMainHttpParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put(ParamsConst.LANG, "en");

        if (EnvConst.IS_TESTING) {
            params.put(ParamsConst.GEO, "RO");
            params.put(ParamsConst.TIME, "2015");
        } else {
            addParams(params, ParamsConst.GEO, EU28_MEMBERS);
        }

        return params;
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
