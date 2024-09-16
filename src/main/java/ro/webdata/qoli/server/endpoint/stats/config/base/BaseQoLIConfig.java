package ro.webdata.qoli.server.endpoint.stats.config.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BaseQoLIConfig {
    @JsonProperty("filename")
    String filename = "qoli";

    @JsonProperty("label")
    String label = "QoLI";

    @JsonProperty("aggregators")
    List<BaseDimensionConfig> aggregators = new ArrayList<>();

    public BaseQoLIConfig() {
        for (Map.Entry<String, Map<String, String>> item : QoLIAggrParams.IND_DIMENSION_LABELS.entrySet()) {
            String dimKey = item.getKey();
            String dimLabel = QoLIAggrParams.IND_PARAMS_LABELS.get(dimKey);
            Map<String, String> dimAllowedParams = item.getValue();

            BaseDimensionConfig dimConfig = new BaseDimensionConfig(dimKey, dimLabel, dimAllowedParams);
            dimConfig.aggregators.sort(new BaseIndicatorsOrder());

            aggregators.add(dimConfig);
        }

        aggregators.sort(new BaseDimensionsOrder());
    }
}

class BaseDimensionsOrder implements Comparator<BaseDimensionConfig> {
    @Override
    public int compare(BaseDimensionConfig dimension1, BaseDimensionConfig dimension2) {
        return dimension1.label.compareTo(dimension2.label);
    }
}

class BaseIndicatorsOrder implements Comparator<BaseIndicatorConfig> {
    @Override
    public int compare(BaseIndicatorConfig indicator1, BaseIndicatorConfig indicator2) {
        return indicator1.label.compareTo(indicator2.label);
    }
}
