#!/bin/bash
WORKPLACE_PATH="/var/www/qoli.webdata.ro/server"

java -Xms512m -Xmx4096m -jar $WORKPLACE_PATH/qoli.jar --server
