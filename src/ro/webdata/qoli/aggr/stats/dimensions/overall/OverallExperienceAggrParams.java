package ro.webdata.qoli.aggr.stats.dimensions.overall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PERCENT;

public class OverallExperienceAggrParams {
    public static final String OVERALL_EXPERIENCE = "overallExperience";

    public static final String HAPPINESS_ALWAYS_RATIO = OVERALL_EXPERIENCE + ":happinessAlwaysRatio";
    public static final String HAPPINESS_MOST_TIME_RATIO = OVERALL_EXPERIENCE + ":happinessMostOfTheTimeRatio";
    public static final String HAPPINESS_RATIO = OVERALL_EXPERIENCE + ":happinessRatio";
    public static final String HIGH_SATISFACTION_RATIO = OVERALL_EXPERIENCE + ":highSatisfactionRatio";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(HAPPINESS_RATIO, "Happiness Ratio");
        put(HIGH_SATISFACTION_RATIO, "High Satisfaction Ratio");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(HAPPINESS_RATIO, PERCENT);
        put(HIGH_SATISFACTION_RATIO, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATES = new HashMap<>() {{
        put(HAPPINESS_RATIO, false);
        put(HIGH_SATISFACTION_RATIO, false);
    }};

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO, "Happy All The Time");
        put(HAPPINESS_MOST_TIME_RATIO, "Happy Must of The Time");
        put(HIGH_SATISFACTION_RATIO, "High Satisfaction Ratio");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO, PERCENT);
        put(HAPPINESS_MOST_TIME_RATIO, PERCENT);
        put(HIGH_SATISFACTION_RATIO, PERCENT);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATES = new HashMap<>() {{
        put(HAPPINESS_ALWAYS_RATIO, false);
        put(HAPPINESS_MOST_TIME_RATIO, false);
        put(HIGH_SATISFACTION_RATIO, false);
    }};
}
