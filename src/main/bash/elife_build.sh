#!/bin/bash

WORKPLACE_PATH="/home/$USER/workplace"
QOLI_FRAMEWORK_PATH="$WORKPLACE_PATH/QoLI-Framework"

cd $QOLI_FRAMEWORK_PATH && ./gradlew clean build && ./gradlew copyProd && ./gradlew copyEnv
