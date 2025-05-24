#!/bin/bash

WORKPLACE_PATH="/home/$USER/workplace"
AUTOMATION_PATH="$WORKPLACE_PATH/automation"
QOLI_FRAMEWORK_PATH="$WORKPLACE_PATH/QoLI-Framework"

mkdir -p $AUTOMATION_PATH

sudo cp $QOLI_FRAMEWORK_PATH/src/main/bash/elife_build.sh $AUTOMATION_PATH/elife_build.sh
sudo cp $QOLI_FRAMEWORK_PATH/src/main/bash/elife_run.sh $AUTOMATION_PATH/elife_run.sh
sudo cp $QOLI_FRAMEWORK_PATH/src/main/bash/elife_startup.sh $AUTOMATION_PATH/elife_startup.sh
