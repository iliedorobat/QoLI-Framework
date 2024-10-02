#!/bin/bash
DOMAIN="webdata.ro"
CERT_PATH="/etc/letsencrypt/live/$DOMAIN"
WORKSPACE_PATH="/home/idorobat/workspace"
PKCS12_PATH="$WORKSPACE_PATH/keystore.p12"
KEYSTORE_PWD="SunnyDay"  # Password for the PKCS12 file

openssl pkcs12 -export \
  -in "$CERT_PATH/fullchain.pem" \
  -inkey "$CERT_PATH/privkey.pem" \
  -out "$PKCS12_PATH" \
  -name "keystore" \
  -password pass:"$KEYSTORE_PWD"

keytool -importkeystore \
  -srckeystore "$WORKSPACE_PATH/keystore.p12" \
  -srcstoretype PKCS12 \
  -destkeystore "$WORKSPACE_PATH/keystore.jks" \
  -deststoretype JKS

# sudo certbot renew --deploy-hook "$WORKSPACE_PATH/QoLI-Framework/src/main/bash/certbot_post_renewal_hook.sh"
