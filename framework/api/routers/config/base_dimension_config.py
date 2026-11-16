from dataclasses import dataclass, field, InitVar

from framework.api.routers.config.base_indicator_config import BaseIndicatorConfig


@dataclass
class BaseDimensionConfig:
    filename: str
    label: str
    allowed_params: InitVar[dict[str, str]]
    aggregators: list[BaseIndicatorConfig] = field(default_factory=list)

    def __post_init__(self, allowed_params: dict[str, str]):
        self.aggregators = [
            BaseIndicatorConfig(ind_key, ind_label)
            for ind_key, ind_label in allowed_params.items()
        ]

        # Sort indicators alphabetically by label
        self.aggregators.sort(key=lambda item: item.label)
