package app.java.commons.dimesntions.common;

import app.java.commons.Errors;
import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;

public class CommonParams {
    public static final String[] ACTIVITIES_TYPES = {
            "AC41A", // Formal voluntary activities
            "AC42A", // Informal voluntary activities
            "AC43A"  // Active citizenship
    };

    public static HashMap<String, String> SATISFACTION_LEVELS_PARAMS = new HashMap<>();
    public static HashMap<String, String> SATISFACTION_TYPES_PARAMS = new HashMap<>();

    static {
        SATISFACTION_LEVELS_PARAMS.put("HIGH", "HIGH");
        SATISFACTION_LEVELS_PARAMS.put("LOW", "LOW");
        SATISFACTION_LEVELS_PARAMS.put("MED", "MED");

        SATISFACTION_TYPES_PARAMS.put("FINANCIAL", "FINSAT");
        SATISFACTION_TYPES_PARAMS.put("JOB", "JOBSAT");
        SATISFACTION_TYPES_PARAMS.put("LIFE", "LIFESAT");
        SATISFACTION_TYPES_PARAMS.put("RELATIONSHIPS", "RELSAT");
        SATISFACTION_TYPES_PARAMS.put("TIME_SPENT", "TIMESAT");
    }

    /**
     * @param activities The activity types:<br/>
     *                 - AC41A: Formal volontary activities;<br/>
     *                 - AC42A: Informal volontary activities;<br/>
     *                 - AC43A: Active citizenship.
     *
     * @return
     */
    public static MultiValuedMap<String, String> getActivePeopleParams(String[] activities) {
        try {
            Errors.throwNewError(ACTIVITIES_TYPES, activities, "type of people activities");

            MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
                put(ParamsConst.AGE, "Y_GE16");
                put(ParamsConst.ISCED_11, "TOTAL");
                put(ParamsConst.SEX, "T");
                put(ParamsConst.UNIT, "PC");
            }};
            FetcherUtils.addParams(params, ParamsConst.ACL_00, activities);
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    public static MultiValuedMap<String, String> getPopulationParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "NR");
        }};
    }

    /**
     * @param satisfactionLevel The satisfaction level:<br/>
     *                   - HIGH: high;<br/>
     *                   - MED: medium;<br/>
     *                   - LOW: low;
     *
     * @param wellBeing The type of calculated well-being:<br/>
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
    public static MultiValuedMap<String, String> getSatisfactionParams(String satisfactionLevel, String wellBeing) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.INDIC_WB, wellBeing);
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.LEV_SATIS, satisfactionLevel);
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getSupportiveParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, "T");
            put(ParamsConst.UNIT, "PC");
        }};
    }
}
