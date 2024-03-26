package app.java.commons.dimensions.auxiliary;

import app.java.commons.Errors;
import app.java.commons.constants.ParamsNames;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;

import static app.java.commons.constants.ParamsValues.ACL00_LEISURE;

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
            Errors.throwNewError(ACL00_LEISURE, activities, "type of people activities");

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
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.INDIC_WB, wellBeing);
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.LEV_SATIS, satisfactionLevel);
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
    }
}
