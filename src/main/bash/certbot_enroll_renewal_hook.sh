 WORKPLACE_PATH="/home/idorobat/workplace"

 sudo certbot renew --deploy-hook "$WORKPLACE_PATH/QoLI-Framework/src/main/bash/certbot_post_renewal_hook.sh"