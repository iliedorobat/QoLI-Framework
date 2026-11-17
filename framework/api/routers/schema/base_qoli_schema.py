from dataclasses import dataclass, field

from framework.api.routers.schema.base_dimension_schema import BaseDimensionSchema
from framework.stats.dimensions.qoli_aggr_params import IND_DIMENSION_LABELS, IND_PARAMS_LABELS


@dataclass
class BaseQoLISchema:
    filename: str = "qoli"
    label: str = "QoLI"
    aggregators: list[BaseDimensionSchema] = field(default_factory=list)

    def __post_init__(self):
        for dim_key, dim_allowed_params in IND_DIMENSION_LABELS.items():
            dim_label = IND_PARAMS_LABELS.get(dim_key)

            dim_config = BaseDimensionSchema(
                filename=dim_key,
                label=dim_label,
                allowed_params=dim_allowed_params,
            )

            self.aggregators.append(dim_config)

        # Sort dimensions alphabetically by label
        self.aggregators.sort(key=lambda item: item.label)
