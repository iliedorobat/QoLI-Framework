package app.java.parser;

import app.java.commons.Constants;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class ParserUtils {
    public static MultiValuedMap<String, String> getMainHttpParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put("lang", "en");

        if (Constants.IS_TESTING) {
            params.put("geo", "RO");
            params.put("time", "2015");
        }

        return params;
    }

    /**
     * Add new parameters into the params list
     * @param params The parameters list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     */
    public static void addParams(MultiValuedMap<String, String> params, String[] values, String propertyName) {
        for (int i = 0; i < values.length; i++) {
            params.put(propertyName, values[i]);
        }
    }
}
