#!/bin/bash

sudo certbot renew --deploy-hook "systemctl reload nginx"
