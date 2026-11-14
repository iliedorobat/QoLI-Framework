import os

import uvicorn
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from framework.api.routers.stats_router import router as stats_router

PORT = 8020
app = FastAPI()

# Configure CORS - equivalent to the Java CORSFilter
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Access-Control-Allow-Origin
    allow_methods=["GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"],  # Access-Control-Allow-Methods
    allow_headers=[
        "Origin",
        "Content-Type",
        "Accept",
        "access-control-allow-headers",
        "access-control-allow-methods",
        "access-control-allow-origin",
    ],  # Access-Control-Allow-Headers
    # allow_credentials=True,  # Uncomment if needed (can't use with allow_origins=["*"])
)

# Include the stats router
app.include_router(stats_router)


@app.get("/")
def root():
    print("Endpoint PID:", os.getpid())
    return "Success!"


if __name__ == "__main__":
    print("Starting FastAPI")
    uvicorn.run(
        "framework.api.server:app",
        host="0.0.0.0",
        reload=True,
        port=PORT,
    )
