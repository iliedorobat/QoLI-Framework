#!/bin/bash

WORKPLACE_PATH="/home/idorobat/workplace"
QOLI_FRAMEWORK_PATH="$WORKPLACE_PATH/QoLI-Framework"

cd $QOLI_FRAMEWORK_PATH && ./gradlew clean build && ./gradlew copyDeps
