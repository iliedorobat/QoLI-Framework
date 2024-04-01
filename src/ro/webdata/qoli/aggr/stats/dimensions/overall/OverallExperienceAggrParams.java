package ro.webdata.qoli.aggr.stats.dimensions.overall;

import java.util.HashMap;
import java.util.Map;

public class OverallExperienceAggrParams {
    public static final String OVERALL_EXPERIENCE = "overallExperience";

    public static final String HAPPINESS_ALWAYS_RATIO = "happinessAlwaysRatio";
    public static final String HAPPINESS_MOST_TIME_RATIO = "happinessMostOfTheTimeRatio";
    public static final String HAPPINESS_RATIO = "happinessRatio";
    public static final String HIGH_SATISFACTION_RATIO = "highSatisfactionRatio";

    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
        put(HAPPINESS_RATIO, "Happiness Ratio");
        put(HIGH_SATISFACTION_RATIO, "High Satisfaction Ratio");
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(HAPPINESS_RATIO, false);
        put(HIGH_SATISFACTION_RATIO, false);
    }};
}
