#!/bin/bash

WORKPLACE_PATH="/home/$USER/workplace"
AUTOMATION_PATH="$WORKPLACE_PATH/automation"

$AUTOMATION_PATH/elife_build.sh
$AUTOMATION_PATH/elife_run.sh
