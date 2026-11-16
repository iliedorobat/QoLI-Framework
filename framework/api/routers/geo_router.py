from typing import Annotated

from fastapi import APIRouter, Query, HTTPException

from framework.common.constants import EU28_MEMBERS_NAME, NON_EU_MEMBERS_NAME

router = APIRouter(
    prefix="/qoli/api/v2/geo",
    responses={404: {"description": "Not found"}},
)

@router.get("/")
async def get_geo(
        entity_type: Annotated[str, Query(alias="entityType")],
):
    # E.g.: http://localhost:8027/qoli/api/v2/geo?entityType=EU
    # E.g.: http://localhost:8027/qoli/api/v2/geo?entityType=NON_EU
    if entity_type == "EU":
        return EU28_MEMBERS_NAME
    elif entity_type == "NON_EU":
        return NON_EU_MEMBERS_NAME
    else:
        raise HTTPException(
            status_code=500,
            detail=f"<entityType> parameter does not support the value <{entity_type}>."
        )
