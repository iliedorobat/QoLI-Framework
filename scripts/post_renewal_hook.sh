#!/bin/bash

set -e

SSL_DIR="$HOME/workplace/ssl/life-index.eu"

# Update FastAPI certificates
install -o $USER -g $USER -m 0644 \
    /etc/letsencrypt/live/life-index.eu/fullchain.pem \
    "$SSL_DIR/fullchain.pem"

install -o $USER -g $USER -m 0600 \
    /etc/letsencrypt/live/life-index.eu/privkey.pem \
    "$SSL_DIR/privkey.pem"

# Reload Nginx
systemctl reload nginx

# Restart FastAPI
pm2 restart life-index
