#!/bin/bash

set -e

# Project configuration
#TODO:
#PROJECT_DIR="/var/www/life-index.eu/server"
PROJECT_DIR="$HOME/workplace/QoLI-Framework-test"
VENV_DIR="$PROJECT_DIR/.venv"
PYTHON="$VENV_DIR/bin/python"
SERVER_MODULE="framework.api.server"

# Move to project directory
cd "$PROJECT_DIR" || exit 1

# Check virtual environment
if [ ! -x "$PYTHON" ]; then
    echo "ERROR: Python virtual environment not found: $VENV_DIR"
    exit 1
fi

# Load environment variables, if available
if [ -f "$PROJECT_DIR/.env" ]; then
    set -a
    source "$PROJECT_DIR/.env"
    set +a
fi

# Start FastAPI
echo "Starting eLIFE API..."

exec "$PYTHON" -m uvicorn "$SERVER_MODULE:app" \
    --host "${HOST:-127.0.0.1}" \
    --port "${PORT:-8027}" \
    --reload "${RELOAD:-false}"
