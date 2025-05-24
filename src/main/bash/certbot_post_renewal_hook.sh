#!/bin/bash

DOMAIN="life-index.eu"
CERT_PATH="/etc/letsencrypt/live/$DOMAIN"
WORKPLACE_PATH="/var/www/$DOMAIN/certs"
PKCS12_PATH="$WORKPLACE_PATH/keystore.p12"
KEY_STORE_PASS="KEY_STORE_PASS_VALUE"  # Password for the PKCS12 file

mkdir -p $WORKPLACE_PATH

openssl pkcs12 -export \
  -in "$CERT_PATH/fullchain.pem" \
  -inkey "$CERT_PATH/privkey.pem" \
  -out "$PKCS12_PATH" \
  -name "keystore" \
  -password pass:"$KEY_STORE_PASS"

keytool -importkeystore \
  -srckeystore "$WORKPLACE_PATH/keystore.p12" \
  -srcstoretype PKCS12 \
  -srcstorepass $KEY_STORE_PASS \
  -destkeystore "$WORKPLACE_PATH/keystore.jks" \
  -deststoretype JKS \
  -deststorepass $KEY_STORE_PASS

sudo chown "$USER" -R $WORKPLACE_PATH
