from dataclasses import dataclass, field, InitVar

from framework.api.routers.schema.aggr_indicator_schema import AggrIndicatorSchema


@dataclass
class AggrDimensionSchema:
    filename: str
    label: str
    allowed_params: InitVar[dict[str, str]]
    checked: bool = True
    aggregators: list[AggrIndicatorSchema] = field(default_factory=list)

    def __post_init__(self, allowed_params: dict[str, str]):
        self.aggregators = [
            AggrIndicatorSchema(ind_key, ind_label)
            for ind_key, ind_label in allowed_params.items()
        ]

        self.aggregators.sort(key=lambda item: item.label)
