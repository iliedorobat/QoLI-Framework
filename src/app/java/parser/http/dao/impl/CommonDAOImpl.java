package app.java.parser.http.dao.impl;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.CommonDAO;

import java.util.Map;

public class CommonDAOImpl implements CommonDAO {
    public static final String[] SATIS_LEVEL = {
            "HIGH",
            "MED",
            "LOW"
    };
    public static final String[] WEL_BEING_TYPE = {
            "ACCSAT",
            "COMSAT",
            "FINSAT",
            "JOBSAT",
            "GREENSAT",
            "LIFESAT",
            "LIVENVSAT",
            "MEANLIFE",
            "RELSAT",
            "TIMESAT"
    };
    public static final String[] ACTIVITIES_TYPE = {
            "AC41A",
            "AC42A",
            "AC43A"
    };
    public static final String[] SUPPORTIVE_API_NAMES = {
            "ilc_scp15",
            "ilc_scp17"
    };

    /**
     * Add new parameters into the params list
     * @param params The params list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     */
    public static void addParams(Map<String, String> params, String[] values, String propertyName) {
        for (int i = 0; i < values.length; i++) {
            params.put(propertyName, values[i]);
        }
    }

    public StringBuilder getSatisfactionRatio(String satisLevel, String wellBeing) {
        try {
            Errors.throwNewError(SATIS_LEVEL, satisLevel, "satisfaction levels");
            Errors.throwNewError(WEL_BEING_TYPE, wellBeing, "well being levels");

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            params.put("age", "Y_GE16");
            params.put("indic_wb", wellBeing);
            params.put("isced11", "TOTAL");
            params.put("lev_satis", satisLevel);
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("ilc_pw05", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getActivePeopleRatio(String[] activity) {
        try {
            Errors.throwNewError(ACTIVITIES_TYPE, activity, "type of people activities");

            //TODO: check
            //TODO: use addParams
            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            for (int i = 0; i < activity.length; i++) {
                params.put("acl00", activity[i]);
            }
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("ilc_scp19", params);
        } catch (Exception e) {
            return null;
        }
    }

    public StringBuilder getSupportiveRatio(String apiName) {
        try {
            Errors.throwNewError(SUPPORTIVE_API_NAMES, apiName, "API names");

            Map<String, String> params = ParserUtils.getGeneralHttpParams();
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("set", "T");
            params.put("unit", "PC");
            return DataFetcher.fetchData(apiName, params);
        } catch (Exception e) {
            return null;
        }
    }
}
