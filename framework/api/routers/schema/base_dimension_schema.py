from dataclasses import dataclass, field, InitVar

from framework.api.routers.schema.base_indicator_schema import BaseIndicatorSchema


@dataclass
class BaseDimensionSchema:
    filename: str
    label: str
    allowed_params: InitVar[dict[str, str]]
    aggregators: list[BaseIndicatorSchema] = field(default_factory=list)

    def __post_init__(self, allowed_params: dict[str, str]):
        self.aggregators = [
            BaseIndicatorSchema(ind_key, ind_label)
            for ind_key, ind_label in allowed_params.items()
        ]

        # Sort indicators alphabetically by label
        self.aggregators.sort(key=lambda item: item.label)
