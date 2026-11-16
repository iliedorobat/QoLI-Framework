from dataclasses import dataclass, field

from framework.api.routers.config.aggr_dimension_config import AggrDimensionConfig
from framework.stats.dimensions.qoli_aggr_params import AGGR_DIMENSION_LABELS, AGGR_PARAMS_LABELS


@dataclass
class AggrQoLIConfig:
    checked: bool = True
    filename: str = "qoli"
    label: str = "QoLI"
    aggregators: list[AggrDimensionConfig] = field(default_factory=list)

    def __post_init__(self):
        for dim_key, dim_allowed_params in AGGR_DIMENSION_LABELS.items():
            dim_label = AGGR_PARAMS_LABELS.get(dim_key)

            dim_config = AggrDimensionConfig(
                dim_key,
                dim_label,
                dim_allowed_params,
            )

            # Sort indicators alphabetically by label
            dim_config.aggregators.sort(key=lambda item: item.label)

            self.aggregators.append(dim_config)

        # Sort dimensions alphabetically by label
        self.aggregators.sort(key=lambda item: item.label)
