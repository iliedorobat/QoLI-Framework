from enum import Enum
from typing import Annotated

from fastapi import APIRouter, Query, HTTPException

from framework.common.constants import EU28_MEMBERS, SERIES_TYPE_COUNTRY
from framework.stats.common.stats_utils import StatsUtils
from framework.stats.dimensions.qoli_stats import QoLIStats

router = APIRouter(
    prefix="/qoli/api/v2/stats",
    responses={404: {"description": "Not found"}},
)

class AnalysisType(Enum):
    AGGREGATE = "aggregate"
    INDIVIDUALLY = "individually"

@router.get("")
async def get_stats(
        aggr_list: Annotated[list[str], Query(alias="aggr")],
        analysis_type: Annotated[AnalysisType, Query(alias="analysisType")],
        start_year: Annotated[int, Query(alias="startYear")],
        end_year: Annotated[int, Query(alias="endYear")],
        country_code: Annotated[list[str] | None, Query(alias="countryCode")] = None,
):
    if analysis_type == AnalysisType.AGGREGATE:
        # E.g.: http://localhost:8027/qoli/api/v2/stats?analysisType=aggregate&aggr=education:dropoutRatio&aggr=health:health:bodyMassIndex&startYear=2020&endYear=2022
        return StatsUtils.filter_stats(
            QoLIStats.generate_aggr_stats(aggr_list, country_code, start_year, end_year),
            None,
            EU28_MEMBERS,
            SERIES_TYPE_COUNTRY,
            start_year,
            end_year,
        )

    elif analysis_type == AnalysisType.INDIVIDUALLY:
        # E.g.: http://localhost:8027/qoli/api/v2/stats?analysisType=individually&aggr=education:dropoutRatio&startYear=2020&endYear=2022
        if aggr_list is None or len(aggr_list) == 0:
            raise HTTPException(
                status_code=500,
                detail="The API requires <aggr> parameter."
            )

        if len(aggr_list) > 1:
            raise HTTPException(
                status_code=500,
                detail="The API does not support multiple <aggr> parameters."
            )

        aggr_value = aggr_list[0]
        if aggr_value not in QoLIStats.base_indicators.keys():
            raise HTTPException(
                status_code=500,
                detail=f"<aggr> parameter does not support the value <{aggr_value}>."
            )

        country_list = QoLIStats.get_country_list(country_code)
        return StatsUtils.filter_stats(
            QoLIStats.base_indicators[aggr_value],
            QoLIStats.raw_indicators_time_range[aggr_value],
            country_list,
            SERIES_TYPE_COUNTRY,
            start_year,
            end_year,
        )
    else:
        raise HTTPException(
            status_code=500,
            detail=f"<analysisType> parameter does not support the value <{analysis_type}>."
        )
