#!/bin/bash

DOMAIN="webdata.ro"
CERT_PATH="/etc/letsencrypt/live/$DOMAIN"
WORKPLACE_PATH="/var/www/$DOMAIN/certs"
PKCS12_PATH="$WORKPLACE_PATH/keystore.p12"
KEYSTORE_PWD="SunnyDay"  # Password for the PKCS12 file

mkdir -p $WORKPLACE_PATH

openssl pkcs12 -export \
  -in "$CERT_PATH/fullchain.pem" \
  -inkey "$CERT_PATH/privkey.pem" \
  -out "$PKCS12_PATH" \
  -name "keystore" \
  -password pass:"$KEYSTORE_PWD"

keytool -importkeystore \
  -srckeystore "$WORKPLACE_PATH/keystore.p12" \
  -srcstoretype PKCS12 \
  -srcstorepass $KEYSTORE_PWD \
  -destkeystore "$WORKPLACE_PATH/keystore.jks" \
  -deststoretype JKS \
  -deststorepass $KEYSTORE_PWD

sudo chown idorobat -R $WORKPLACE_PATH
