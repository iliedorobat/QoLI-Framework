package ro.webdata.qoli.server.endpoint.stats.config.aggr;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DimensionConfig {
    @JsonProperty("checked")
    boolean checked = true;

    @JsonProperty("filename")
    String filename;

    @JsonProperty("label")
    String label;

    @JsonProperty("aggregators")
    List<IndicatorConfig> aggregators = new ArrayList<>();

    public DimensionConfig(String filename, String label, Map<String, String> allowedParams) {
        this.filename = filename;
        this.label = label;

        for (Map.Entry<String, String> param : allowedParams.entrySet()) {
            String indKey = param.getKey();
            String indLabel = param.getValue();
            IndicatorConfig indConfig = new IndicatorConfig(indKey, indLabel);

            aggregators.add(indConfig);
        }
    }
}
