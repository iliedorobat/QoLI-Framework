WORKPLACE_PATH="/home/idorobat/workplace"
AUTOMATION_PATH="$WORKPLACE_PATH/automation"
QOLI_FRAMEWORK_PATH="$WORKPLACE_PATH/QoLI-Framework"

mkdir -p $AUTOMATION_PATH

sudo cp $QOLI_FRAMEWORK_PATH/src/main/bash/eLife_startup.sh $AUTOMATION_PATH/eLife_startup.sh
