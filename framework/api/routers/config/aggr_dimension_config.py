from dataclasses import dataclass, field, InitVar

from framework.api.routers.config.aggr_indicator_config import AggrIndicatorConfig


@dataclass
class AggrDimensionConfig:
    filename: str
    label: str
    allowed_params: InitVar[dict[str, str]]
    checked: bool = True
    aggregators: list[AggrIndicatorConfig] = field(default_factory=list)

    def __post_init__(self, allowed_params: dict[str, str]):
        self.aggregators = [
            AggrIndicatorConfig(ind_key, ind_label)
            for ind_key, ind_label in allowed_params.items()
        ]

        self.aggregators.sort(key=lambda item: item.label)
