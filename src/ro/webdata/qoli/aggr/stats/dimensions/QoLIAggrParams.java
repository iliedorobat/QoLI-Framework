package ro.webdata.qoli.aggr.stats.dimensions;

import ro.webdata.qoli.aggr.stats.dimensions.education.EducationAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyAggrParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QoLIAggrParams {
    public static final String QOLI = "QoLI";

    public static final String EDUCATION = EducationAggrParams.EDUCATION;
    public static final String ENVIRONMENT = EnvironmentAggrParams.ENVIRONMENT;
    public static final String GOVERNANCE = GovRightsAggrParams.GOVERNANCE;
    public static final String HEALTH = HealthAggrParams.HEALTH;
    public static final String LEISURE_INTERACT = LeisureInteractAggrParams.LEISURE_INTERACT;
    public static final String MAIN_ACTIVITY = MainActivityAggrParams.MAIN_ACTIVITY;
    public static final String LIVING_CONDITIONS = MaterialLivingAggrParams.LIVING_CONDITIONS;
    public static final String OVERALL_EXPERIENCE = OverallExperienceAggrParams.OVERALL_EXPERIENCE;
    public static final String SAFETY = SafetyAggrParams.SAFETY;

    public static final Map<String, String> ALLOWED_PARAM_LABELS = new HashMap<>() {{
        put(EDUCATION, "Education");
        put(ENVIRONMENT, "Natural and Living Environment");
        put(GOVERNANCE, "Governance and Basic Rights");
        put(HEALTH, "Health");
        put(LEISURE_INTERACT, "Leisure and Social Interactions");
        put(LIVING_CONDITIONS, "Material Living Conditions");
        put(MAIN_ACTIVITY, "Productive or Main Activity");
        put(OVERALL_EXPERIENCE, "Overall Experience of Life");
        put(SAFETY, "Economic and Physical Safety");
    }};

    public static final List<String> ALLOWED_PARAMS = List.copyOf(ALLOWED_PARAM_LABELS.keySet());

    public static final Map<String, Map<String, String>> ALLOWED_DIMENSION_LABELS = new HashMap<>() {{
        put(EDUCATION, EducationAggrParams.ALLOWED_PARAM_LABELS);
        put(ENVIRONMENT, EnvironmentAggrParams.ALLOWED_PARAM_LABELS);
        put(GOVERNANCE, GovRightsAggrParams.ALLOWED_PARAM_LABELS);
        put(HEALTH, HealthAggrParams.ALLOWED_PARAM_LABELS);
        put(LEISURE_INTERACT, LeisureInteractAggrParams.ALLOWED_PARAM_LABELS);
        put(MAIN_ACTIVITY, MainActivityAggrParams.ALLOWED_PARAM_LABELS);
        put(LIVING_CONDITIONS, MaterialLivingAggrParams.ALLOWED_PARAM_LABELS);
        put(OVERALL_EXPERIENCE, OverallExperienceAggrParams.ALLOWED_PARAM_LABELS);
        put(SAFETY, SafetyAggrParams.ALLOWED_PARAM_LABELS);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(EDUCATION, false);
        put(ENVIRONMENT, false);
        put(GOVERNANCE, false);
        put(HEALTH, false);
        put(LEISURE_INTERACT, false);
        put(LIVING_CONDITIONS, false);
        put(MAIN_ACTIVITY, false);
        put(OVERALL_EXPERIENCE, false);
        put(SAFETY, false);
    }};
}
