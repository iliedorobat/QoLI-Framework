from dataclasses import asdict
from typing import Annotated

from fastapi import APIRouter, Query, HTTPException

from framework.api.routers.schema.aggr_qoli_schema import AggrQoLISchema
from framework.api.routers.schema.base_qoli_schema import BaseQoLISchema
from framework.api.routers.stats_router import AnalysisType

router = APIRouter(
    prefix="/qoli/api/v2/stats/config",
    responses={404: {"description": "Not found"}},
)

@router.get("")
async def get_config(
        analysis_type: Annotated[AnalysisType, Query(alias="analysisType")],
):
    if analysis_type == AnalysisType.AGGREGATE:
        # E.g.: http://localhost:8027/qoli/api/v2/stats/config?analysisType=aggregate
        return asdict(AggrQoLISchema())
    elif analysis_type == AnalysisType.INDIVIDUALLY:
        # E.g.: http://localhost:8027/qoli/api/v2/stats/config?analysisType=individually
        return asdict(BaseQoLISchema())
    else:
        raise HTTPException(
            status_code=500,
            detail="The API does not support <analysisType> parameter."
        )
