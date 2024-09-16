package ro.webdata.qoli.aggr.stats.dimensions.auxiliary;

import ro.webdata.qoli.aggr.stats.Errors;
import ro.webdata.qoli.aggr.stats.constants.ParamsNames;
import ro.webdata.qoli.aggr.data.fetch.FetcherUtils;
import ro.webdata.qoli.aggr.stats.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class AuxiliaryParams {
    public static final MultiValuedMap<String, String> LOW_WORK_INTENSITY_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y_LT60");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "PC_Y_LT60");
    }};

    public static final MultiValuedMap<String, String> POPULATION_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "TOTAL");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SEX, "T");
        put(ParamsNames.UNIT, "NR");
    }};

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
            Errors.throwNewError(ParamsValues.ACL00, activities, "type of people activities");

            MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
                put(ParamsNames.AGE, "Y_GE16");
                put(ParamsNames.FREQ, "A");
                put(ParamsNames.ISCED_11, "TOTAL");
                put(ParamsNames.SEX, "T");
                put(ParamsNames.UNIT, "PC");
            }};
            FetcherUtils.addParams(params, ParamsNames.ACL_00, activities);
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param satisfactionLevel The satisfaction level:<br/>
     *                   - HIGH: high;<br/>
     *                   - MED: medium;<br/>
     *                   - LOW: low;
     *
     * @param satisfactionType The type of calculated well-being:<br/>
     *                  - ACCOM: Satisfaction with accommodation;<br/>
     *                  - COM: Satisfaction with commuting time;<br/>
     *                  - FIN: Satisfaction with financial situation;<br/>
     *                  - JOB: Job satisfaction;<br/>
     *                  - LIVENV: Satisfaction with living environment;<br/>
     *                  - MEANLIFE: Meaning of life;<br/>
     *                  - PER_RELS: Satisfaction with personal relationships;<br/>
     *                  - REC_GA: Satisfaction with recreational and green areas;<br/>
     *                  - TIME: Satisfaction with time use;
     *
     * @return
     */
    public static MultiValuedMap<String, String> getSatisfactionParams(String satisfactionLevel, String satisfactionType) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.LEV_SATIS, satisfactionLevel);
            put(ParamsNames.LIFE_SAT, satisfactionType);
            put(ParamsNames.SEX, "T");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.UNIT, "PC");
        }};
    }
}
