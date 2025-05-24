#!/bin/bash
WORKPLACE_PATH="/var/www/elife.webdata.ro/server"

java -Xms512m -Xmx4096m -cp "$WORKPLACE_PATH/elife.jar:$WORKPLACE_PATH/libs/*" ro.webdata.qoli.Main --server
