from dataclasses import dataclass, field

from framework.api.routers.config.base_dimension_config import BaseDimensionConfig
from framework.stats.dimensions.qoli_aggr_params import IND_DIMENSION_LABELS, IND_PARAMS_LABELS


@dataclass
class BaseQoLIConfig:
    filename: str = "qoli"
    label: str = "QoLI"
    aggregators: list[BaseDimensionConfig] = field(default_factory=list)

    def __post_init__(self):
        for dim_key, dim_allowed_params in IND_DIMENSION_LABELS.items():
            dim_label = IND_PARAMS_LABELS.get(dim_key)

            dim_config = BaseDimensionConfig(
                filename=dim_key,
                label=dim_label,
                allowed_params=dim_allowed_params,
            )

            self.aggregators.append(dim_config)

        # Sort dimensions alphabetically by label
        self.aggregators.sort(key=lambda item: item.label)
