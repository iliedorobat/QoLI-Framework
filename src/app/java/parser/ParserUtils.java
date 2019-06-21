package app.java.parser;

import app.java.commons.Constants;

import java.util.HashMap;
import java.util.Map;

public class ParserUtils {
    public static Map<String, String> getGeneralHttpParams() {
        Map<String, String> params = new HashMap<>();
        params.put("lang", "en");

        if (Constants.IS_TESTING) {
            params.put("geo", "RO");
            params.put("time", "2015");
        }

        return params;
    }
}
