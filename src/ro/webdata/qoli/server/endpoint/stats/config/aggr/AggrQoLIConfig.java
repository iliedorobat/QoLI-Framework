package ro.webdata.qoli.server.endpoint.stats.config.aggr;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AggrQoLIConfig {
    @JsonProperty("checked")
    boolean checked = true;

    @JsonProperty("filename")
    String filename = "qoli";

    @JsonProperty("label")
    String label = "QoLI";

    @JsonProperty("aggregators")
    List<AggrDimensionConfig> aggregators = new ArrayList<>();

    public AggrQoLIConfig() {
        for (Map.Entry<String, Map<String, String>> item : QoLIAggrParams.AGGR_DIMENSION_LABELS.entrySet()) {
            String dimKey = item.getKey();
            String dimLabel = QoLIAggrParams.AGGR_PARAMS_LABELS.get(dimKey);
            Map<String, String> dimAllowedParams = item.getValue();

            AggrDimensionConfig dimConfig = new AggrDimensionConfig(dimKey, dimLabel, dimAllowedParams);
            dimConfig.aggregators.sort(new AggrIndicatorsOrder());

            aggregators.add(dimConfig);
        }

        aggregators.sort(new AggrDimensionsOrder());
    }
}

class AggrDimensionsOrder implements Comparator<AggrDimensionConfig> {
    @Override
    public int compare(AggrDimensionConfig dimension1, AggrDimensionConfig dimension2) {
        return dimension1.label.compareTo(dimension2.label);
    }
}

class AggrIndicatorsOrder implements Comparator<AggrIndicatorConfig> {
    @Override
    public int compare(AggrIndicatorConfig indicator1, AggrIndicatorConfig indicator2) {
        return indicator1.label.compareTo(indicator2.label);
    }
}
