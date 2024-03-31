package ro.webdata.qoli.aggr.commons.dimensions.overall;

import ro.webdata.qoli.aggr.commons.constants.ParamsNames;
import ro.webdata.qoli.aggr.commons.dimensions.auxiliary.AuxiliaryParams;
import ro.webdata.qoli.aggr.data.fetch.FetcherUtils;
import ro.webdata.qoli.aggr.commons.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class OverallExperienceParams {
    private static final String[] LEVELS = {
            ParamsValues.HAPPINESS_LEVELS.get("always"),
            ParamsValues.HAPPINESS_LEVELS.get("most")
    };

    public static final MultiValuedMap<String, String>
            HAPPINESS_RATIO_PARAMS = getHappinessParams(LEVELS),
            HAPPINESS_ALWAYS_RATIO_PARAMS = getHappinessParams(ParamsValues.HAPPINESS_LEVELS.get("always")),
            HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS = getHappinessParams(ParamsValues.HAPPINESS_LEVELS.get("most"));

    public static final MultiValuedMap<String, String> HIGH_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            ParamsValues.SATISFACTION_LEVELS.get("high"),
            ParamsValues.SATISFACTION_TYPES.get("overall")
    );

    private static MultiValuedMap<String, String> getHappinessParams(String happinessLevel) {
        String[] happinessLevels = new String[] { happinessLevel };
        return getHappinessParams(happinessLevels);
    }

    private static MultiValuedMap<String, String> getHappinessParams(String[] happinessLevels) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "PC");
        }};
        FetcherUtils.addParams(params, ParamsNames.FREQUENCY, happinessLevels);
        return params;
    }
}
