#!/bin/bash

set -e

# Project configuration
PROJECT_DIR="$HOME/workplace/QoLI-Framework"
VENV_DIR="$PROJECT_DIR/.venv"
PYTHON="$VENV_DIR/bin/python"
SERVER_MODULE="framework.api.server"

# SSL configuration
SSL_DIR="$HOME/workplace/ssl/life-index.eu"
SSL_CERTFILE="$SSL_DIR/fullchain.pem"
SSL_KEYFILE="$SSL_DIR/privkey.pem"

# Move to project directory
cd "$PROJECT_DIR" || exit 1

# Check virtual environment
if [ ! -x "$PYTHON" ]; then
    echo "ERROR: Python virtual environment not found: $VENV_DIR"
    exit 1
fi

# Check for the existence of certificates
if [ ! -r "$SSL_CERTFILE" ] || [ ! -r "$SSL_KEYFILE" ]; then
    echo "ERROR: SSL certificates not found or not accessible."
    exit 1
fi

# Load environment variables, if available
if [ -f "$PROJECT_DIR/.env" ]; then
    set -a
    source "$PROJECT_DIR/.env"
    set +a
fi

# Start FastAPI
echo "Starting life-index API..."

exec "$PYTHON" -m uvicorn "$SERVER_MODULE:app" \
    --host "${HOST:-147.79.115.139}" \
    --port "${PORT:-8443}" \
    --ssl-certfile "$SSL_CERTFILE" \
    --ssl-keyfile "$SSL_KEYFILE"
