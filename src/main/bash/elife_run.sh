#!/bin/bash
WORKPLACE_PATH="/var/www/life-index.eu/server"

java -Xms512m -Xmx4096m -cp "$WORKPLACE_PATH/elife.jar:$WORKPLACE_PATH/libs/*" ro.webdata.qoli.Main --server
