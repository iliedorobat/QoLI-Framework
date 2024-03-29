package app.java.aggr.commons.dimensions.overall;

import app.java.aggr.commons.constants.ParamsNames;
import app.java.aggr.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.aggr.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.aggr.commons.constants.ParamsValues.*;

public class OverallExperienceParams {
    private static final String[] LEVELS = {
            HAPPINESS_LEVELS.get("always"),
            HAPPINESS_LEVELS.get("most")
    };

    public static final MultiValuedMap<String, String>
            HAPPINESS_RATIO_PARAMS = getHappinessParams(LEVELS),
            HAPPINESS_ALWAYS_RATIO_PARAMS = getHappinessParams(HAPPINESS_LEVELS.get("always")),
            HAPPINESS_MOST_OF_THE_TIME_RATIO_PARAMS = getHappinessParams(HAPPINESS_LEVELS.get("most"));

    public static final MultiValuedMap<String, String> HIGH_SATISFACTION_RATIO_PARAMS = AuxiliaryParams.getSatisfactionParams(
            SATISFACTION_LEVELS.get("high"),
            SATISFACTION_TYPES.get("overall")
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
