import logging
import os
from contextlib import asynccontextmanager

import uvicorn
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from framework.api.routers.stats_schema_router import router as stats_schema_router
from framework.api.routers.stats_router import router as stats_router
from framework.config.endpoint import Endpoint
from framework.config.env_state import EnvState

LOGGER = logging.getLogger(__name__)


@asynccontextmanager
async def lifespan(server: FastAPI):
    LOGGER.info("Starting eLIFE API...")
    LOGGER.info("API endpoint: %s", Endpoint.BASE_URI)

    yield

    LOGGER.info("Shutting down eLIFE API...")


def create_app() -> FastAPI:
    server = FastAPI(
        title="eLIFE API",
        lifespan=lifespan,
        docs_url=None,
        redoc_url=None,
        openapi_url=None,
    )

    # CORS configuration
    if EnvState.CORS_ORIGINS:
        server.add_middleware(
            CORSMiddleware,
            allow_origins=EnvState.CORS_ORIGINS,
            allow_methods=[
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS",
                "HEAD",
            ],
            allow_headers=[
                "Origin",
                "Content-Type",
                "Accept",
                "Authorization",
            ],
            allow_credentials=False,
        )

    # Register routers
    server.include_router(stats_router)
    server.include_router(stats_schema_router)

    @server.get("/")
    async def root():
        LOGGER.info("Endpoint PID: %s", os.getpid())
        return "Success!"

    return server


app = create_app()


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)

    LOGGER.info("Starting FastAPI server...")

    uvicorn.run(
        "framework.api.server:app",
        host=Endpoint.HOST,
        port=Endpoint.PORT,
        reload=not EnvState.IS_PRODUCTION,
    )
