package ro.webdata.qoli.server.endpoints.stats.config.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIStats;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceAggrParams;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyAggrParams;

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;

public class BaseIndicatorConfig {
    @JsonProperty("filename")
    String filename;

    @JsonProperty("label")
    String label;

    @JsonProperty("negativeState")
    boolean negativeState;

    @JsonProperty("timeRange")
    ArrayList<Integer> timeRange;

    @JsonProperty("units")
    String units;

    public BaseIndicatorConfig(String dimKey, String key, String label) {
        this.filename = key;
        this.label = label;
        this.negativeState = isReversed(key);
        this.timeRange = extractTimeRange(dimKey, key);
        this.units = getUnits(key);
    }

    private boolean isReversed(String indKey) {
        if (indKey.startsWith(EDUCATION))
            return EducationAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(ENVIRONMENT))
            return EnvironmentAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(GOVERNANCE))
            return GovRightsAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(HEALTH))
            return HealthAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(LEISURE_INTERACT))
            return LeisureInteractAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(MAIN_ACTIVITY))
            return MainActivityAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(LIVING_CONDITIONS))
            return MaterialLivingAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(OVERALL_EXPERIENCE))
            return OverallExperienceAggrParams.IND_REVERSED_STATES.get(indKey);
        else if (indKey.startsWith(SAFETY))
            return SafetyAggrParams.IND_REVERSED_STATES.get(indKey);
        return false;
    }

    private String getUnits(String indKey) {
        if (indKey.startsWith(EDUCATION))
            return EducationAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(ENVIRONMENT))
            return EnvironmentAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(GOVERNANCE))
            return GovRightsAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(HEALTH))
            return HealthAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(LEISURE_INTERACT))
            return LeisureInteractAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(MAIN_ACTIVITY))
            return MainActivityAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(LIVING_CONDITIONS))
            return MaterialLivingAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(OVERALL_EXPERIENCE))
            return OverallExperienceAggrParams.IND_PARAMS_UNITS.get(indKey);
        else if (indKey.startsWith(SAFETY))
            return SafetyAggrParams.IND_PARAMS_UNITS.get(indKey);
        return "";
    }

    public static ArrayList<Integer> extractTimeRange(String dimKey, String indKey) {
        Map<String, Number> rawIndicators = QoLIStats.rawIndicatorsMap.get(dimKey).get(indKey);
        Set<Integer> set = new HashSet<>();

        for (Map.Entry<String, Number> entry : rawIndicators.entrySet()) {
           String key = entry.getKey();
           Number value = entry.getValue();

           if (value != null) {
               String[] pair = key.split("_");
               String year = pair[1];
               set.add(Integer.parseInt(year));
           }
        }

        ArrayList<Integer> timeRange = new ArrayList<>(set);
        Collections.sort(timeRange);
        Collections.reverse(timeRange);

        return timeRange;
    }
}
