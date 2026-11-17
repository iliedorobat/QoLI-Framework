import os
from datetime import datetime
from pathlib import Path

from dotenv import load_dotenv


# Environment configuration
_PROJECT_ROOT = Path(__file__).resolve().parents[2]
_ENV_PATH = _PROJECT_ROOT / ".env"

load_dotenv(_ENV_PATH)


def _get_bool(name: str, default: bool = False) -> bool:
    return os.getenv(name, str(default)).strip().lower() == "true"


class EnvState:
    # Authentication
    AUTH_USER: str | None = os.getenv("AUTH_USER")
    AUTH_PASSWORD: str | None = os.getenv("AUTH_PASSWORD")

    # Server configuration
    HOST_ADDRESS: str | None = os.getenv("HOST_ADDRESS")
    IS_PRODUCTION: bool = _get_bool("IS_PRODUCTION")
    IS_TESTING: bool = _get_bool("IS_TESTING")

    # Server ports
    LOCAL_PORT: int = 8027
    NGINX_PORT: int = 443

    # CORS configuration
    CORS_ORIGINS: list[str] = [
        origin.strip()
        for origin in os.getenv(
            "CORS_ORIGINS",
            "http://localhost:4200,http://localhost:3000,https://life-index.eu,https://www.life-index.eu"
        ).split(",")
        if origin.strip()
    ]

    # Temporal configuration
    MIN_YEAR: int = 2004
    MAX_YEAR: int = datetime.now().year - 1

    INIT_MAP_MIN_YEAR: int = MIN_YEAR
    INIT_MAP_MAX_YEAR: int = MAX_YEAR


__all__ = [
    "EnvState",
]
