 WORKSPACE_PATH="/home/idorobat/workspace"

 sudo certbot renew --deploy-hook "$WORKSPACE_PATH/QoLI-Framework/src/main/bash/certbot_post_renewal_hook.sh"