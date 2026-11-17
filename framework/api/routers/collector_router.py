from typing import Annotated

from fastapi import APIRouter, Depends, Query, HTTPException

from framework.api.auth import authenticate
from framework.common.constants import DIRECTION_COLUMN
from framework.config.env_state import EnvState
from framework.stats.data_collector import collect_data
from framework.stats.dimensions.qoli_stats import QoLIStats
from framework.stats.qoli_csv_stats import QoLICsvStats
from framework.stats.qoli_json_stats import QoLIJsonStats

router = APIRouter(
    prefix="/qoli/api/v2/stats/collect",
    responses={404: {"description": "Not found"}},
    dependencies=[Depends(authenticate)],
)

@router.get("")
async def collect_data_api(
        aggr_list: Annotated[list[str] | None, Query(alias="aggr")] = None,
        calc_ind: Annotated[bool | None, Query(alias="calculateIndicators")] = False,
):
    # E.g.: http://localhost:8027/qoli/api/v2/stats/collect
    try:
        collect_data()

        start_year = EnvState.MIN_YEAR
        end_year = EnvState.MAX_YEAR

        data_by_countries = QoLIStats.prepare_extended_dimensions(aggr_list, None, start_year, end_year)
        QoLICsvStats.write_dimensions(data_by_countries, DIRECTION_COLUMN, calc_ind, start_year, end_year)
        QoLIJsonStats.write_dimensions(data_by_countries, calc_ind, start_year, end_year)

        return {
            "detail": "Download successful!"
        }
    except:
        raise HTTPException(
            status_code=500,
            detail="Something went wrong getting data. Please contact the administrator."
        )
