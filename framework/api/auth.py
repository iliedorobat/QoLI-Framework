from fastapi import Depends, HTTPException, status
from fastapi.security import HTTPBasic, HTTPBasicCredentials

from framework.config.env_state import EnvState


security = HTTPBasic()


def authenticate(
        credentials: HTTPBasicCredentials = Depends(security)
) -> None:

    if not _is_authorized(credentials.username, credentials.password):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="User cannot access the resource.",
            headers={"WWW-Authenticate": "Basic"},
        )


def _is_authorized(username: str, password: str) -> bool:
    return (
            username == EnvState.AUTH_USER
            and password == EnvState.AUTH_PASSWORD
    )
