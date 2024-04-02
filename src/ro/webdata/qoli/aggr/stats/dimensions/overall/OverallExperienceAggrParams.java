package ro.webdata.qoli.aggr.stats.dimensions.overall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverallExperienceAggrParams {
    public static final String OVERALL_EXPERIENCE = "overallExperience";

    public static final String HAPPINESS_ALWAYS_RATIO = OVERALL_EXPERIENCE + ":happinessAlwaysRatio";
    public static final String HAPPINESS_MOST_TIME_RATIO = OVERALL_EXPERIENCE + ":happinessMostOfTheTimeRatio";
    public static final String HAPPINESS_RATIO = OVERALL_EXPERIENCE + ":happinessRatio";
    public static final String HIGH_SATISFACTION_RATIO = OVERALL_EXPERIENCE + ":highSatisfactionRatio";

    public static final Map<String, String> ALLOWED_PARAM_LABELS = new HashMap<>() {{
        put(HAPPINESS_RATIO, "Happiness Ratio");
        put(HIGH_SATISFACTION_RATIO, "High Satisfaction Ratio");
    }};

    public static final List<String> ALLOWED_PARAMS = List.copyOf(ALLOWED_PARAM_LABELS.keySet());

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(HAPPINESS_RATIO, false);
        put(HIGH_SATISFACTION_RATIO, false);
    }};
}
