package ro.webdata.qoli.server.endpoint.stats.config.aggr;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AggrDimensionConfig {
    @JsonProperty("checked")
    boolean checked = true;

    @JsonProperty("filename")
    String filename;

    @JsonProperty("label")
    String label;

    @JsonProperty("aggregators")
    List<AggrIndicatorConfig> aggregators = new ArrayList<>();

    public AggrDimensionConfig(String filename, String label, Map<String, String> allowedParams) {
        this.filename = filename;
        this.label = label;

        for (Map.Entry<String, String> param : allowedParams.entrySet()) {
            String indKey = param.getKey();
            String indLabel = param.getValue();
            AggrIndicatorConfig indConfig = new AggrIndicatorConfig(indKey, indLabel);

            aggregators.add(indConfig);
        }
    }
}
