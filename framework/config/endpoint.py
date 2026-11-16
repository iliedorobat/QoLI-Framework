from framework.config.env_state import EnvState


class Endpoint:
    PROTOCOL = "https" if EnvState.IS_PRODUCTION else "http"

    DOMAIN = (
        EnvState.HOST_ADDRESS
        if EnvState.IS_PRODUCTION
        else "localhost"
    )

    HOST = "127.0.0.1" if EnvState.IS_PRODUCTION else "0.0.0.0"

    PORT = (
        EnvState.NGINX_PORT
        if EnvState.IS_PRODUCTION
        else EnvState.LOCAL_PORT
    )

    URI_PATTERN = "{protocol}://{domain}{port_suffix}/"

    BASE_URI = URI_PATTERN.format(
        protocol=PROTOCOL,
        domain=DOMAIN,
        port_suffix="" if PORT in (80, 443) else f":{PORT}",
    )


__all__ = [
    "Endpoint",
]
