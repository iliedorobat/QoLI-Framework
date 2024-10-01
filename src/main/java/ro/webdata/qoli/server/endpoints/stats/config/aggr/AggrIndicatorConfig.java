package ro.webdata.qoli.server.endpoints.stats.config.aggr;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyAggrParams;

import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;

public class AggrIndicatorConfig {
    @JsonProperty("checked")
    boolean checked = true;

    @JsonProperty("filename")
    String filename;

    @JsonProperty("label")
    String label;

    @JsonProperty("negativeState")
    boolean negativeState;

    @JsonProperty("units")
    String units;

    public AggrIndicatorConfig(String filename, String label) {
        this.filename = filename;
        this.label = label;
        this.negativeState = isReversed(filename);
        this.units = getUnits(filename);
    }

    private boolean isReversed(String indKey) {
        if (indKey.startsWith(EDUCATION))
            return EducationAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(ENVIRONMENT))
            return EnvironmentAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(GOVERNANCE))
            return GovRightsAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(HEALTH))
            return HealthAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(LEISURE_INTERACT))
            return LeisureInteractAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(MAIN_ACTIVITY))
            return MainActivityAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(LIVING_CONDITIONS))
            return MaterialLivingAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(OVERALL_EXPERIENCE))
            return OverallExperienceAggrParams.AGGR_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(SAFETY))
            return SafetyAggrParams.AGGR_REVERSED_STATES.get(indKey);
        return false;
    }

    private String getUnits(String indKey) {
        if (indKey.startsWith(EDUCATION))
            return EducationAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(ENVIRONMENT))
            return EnvironmentAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(GOVERNANCE))
            return GovRightsAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(HEALTH))
            return HealthAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(LEISURE_INTERACT))
            return LeisureInteractAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(MAIN_ACTIVITY))
            return MainActivityAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(LIVING_CONDITIONS))
            return MaterialLivingAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(OVERALL_EXPERIENCE))
            return OverallExperienceAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(SAFETY))
            return SafetyAggrParams.AGGR_PARAMS_UNITS.get(indKey);
        return "";
    }
}
