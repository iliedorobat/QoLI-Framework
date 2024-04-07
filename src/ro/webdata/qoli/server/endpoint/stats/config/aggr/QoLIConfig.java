package ro.webdata.qoli.server.endpoint.stats.config.aggr;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class QoLIConfig {
    @JsonProperty("checked")
    boolean checked = true;

    @JsonProperty("filename")
    String filename = "qoli";

    @JsonProperty("label")
    String label = "QoLI";

    @JsonProperty("aggregators")
    List<DimensionConfig> aggregators = new ArrayList<>();

    public QoLIConfig() {
        for (Map.Entry<String, Map<String, String>> item : QoLIAggrParams.AGGR_DIMENSION_LABELS.entrySet()) {
            String dimKey = item.getKey();
            String dimLabel = QoLIAggrParams.AGGR_PARAMS_LABELS.get(dimKey);
            Map<String, String> dimAllowedParams = item.getValue();

            DimensionConfig dimConfig = new DimensionConfig(dimKey, dimLabel, dimAllowedParams);
            dimConfig.aggregators.sort(new IndicatorsOrder());

            aggregators.add(dimConfig);
        }

        aggregators.sort(new DimensionsOrder());
    }
}

class DimensionsOrder implements Comparator<DimensionConfig> {
    @Override
    public int compare(DimensionConfig dimension1, DimensionConfig dimension2) {
        return dimension1.label.compareTo(dimension2.label);
    }
}

class IndicatorsOrder implements Comparator<IndicatorConfig> {
    @Override
    public int compare(IndicatorConfig indicator1, IndicatorConfig indicator2) {
        return indicator1.label.compareTo(indicator2.label);
    }
}
