#!/bin/bash
WORKPLACE_PATH="/var/www/elife.webdata.ro/server"

java -Xms512m -Xmx4096m -jar $WORKPLACE_PATH/elife.jar --server
