 WORKPLACE_PATH="/home/idorobat/workplace"
 AUTOMATION_PATH="$WORKPLACE_PATH/automation"
 QOLI_FRAMEWORK_PATH="$WORKPLACE_PATH/QoLI-Framework"

 mkdir -p $AUTOMATION_PATH

 sudo cp $QOLI_FRAMEWORK_PATH/src/main/bash/certbot_post_renewal_hook.sh $AUTOMATION_PATH/certbot_post_renewal_hook.sh
 sudo certbot renew --deploy-hook "$AUTOMATION_PATH/certbot_post_renewal_hook.sh"
