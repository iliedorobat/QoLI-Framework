package ro.webdata.qoli.server.endpoint.stats.config.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDimensionConfig {
    @JsonProperty("filename")
    String filename;

    @JsonProperty("label")
    String label;

    @JsonProperty("aggregators")
    List<BaseIndicatorConfig> aggregators = new ArrayList<>();

    public BaseDimensionConfig(String filename, String label, Map<String, String> allowedParams) {
        this.filename = filename;
        this.label = label;

        for (Map.Entry<String, String> param : allowedParams.entrySet()) {
            String indKey = param.getKey();
            String indLabel = param.getValue();
            BaseIndicatorConfig indConfig = new BaseIndicatorConfig(indKey, indLabel);

            aggregators.add(indConfig);
        }
    }
}
