package app.java.parser.http;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;

import java.util.Map;

public class Common {
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
     * @param params The parameters list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     */
    public static void addParams(Map<String, String> params, String[] values, String propertyName) {
        for (int i = 0; i < values.length; i++) {
            params.put(propertyName, values[i]);
        }
    }

    /**
     * Percentage of the population rating their satisfaction as high, medium or low<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @param satisLevel The satisfaction level:<br/>
     *                   - HIGH: high;<br/>
     *                   - MED: medium;<br/>
     *                   - LOW: low;
     *
     * @param wellBeing The type of calculated well being:<br/>
     *                  - ACCSAT: Satisfaction with accommodation;<br/>
     *                  - COMSAT: Satisfaction with commuting time;<br/>
     *                  - FINSAT: Satisfaction with financial situation;<br/>
     *                  - GREENSAT: Satisfaction with recreational and green areas;<br/>
     *                  - JOBSAT: Job satisfaction;<br/>
     *                  - LIFESAT: Overall life satisfaction;<br/>
     *                  - LIVENVSAT: Satisfaction with living environment;<br/>
     *                  - MEANLIFE: Meaning of life;<br/>
     *                  - RELSAT: Satisfaction with personal relationships;<br/>
     *                  - TIMESAT: Satisfaction with time use;
     *
     * @return
     */
    public static StringBuilder getSatisfactionRatio(String satisLevel, String wellBeing) {
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

    /**
     * Participation in formal or informal voluntary activities or active citizenship<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015
     *
     * @param activity The activity types:<br/>
     *                 - AC41A: Formal volontary activities;<br/>
     *                 - AC42A: Informal volontary activities;<br/>
     *                 - AC43A: Active citizenship.
     *
     * @return
     */
    public static StringBuilder getActivePeopleRatio(String[] activity) {
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

    /**
     * Get the ratio of persons (16 years or over) who have someone to ask for help
     * or to discuss personal matters<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp15 / ilc_scp17<br/>
     * Years: 2013; 2015
     *
     * @param apiName The API name:<br/>
     *                - ilc_scp15;<br/>
     *                - ilc_scp17.
     * @return
     */
    public static StringBuilder getSupportiveRatio(String apiName) {
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
