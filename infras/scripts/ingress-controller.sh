#!/bin/bash
helm install  cert-manager jetstack/cert-manager  --namespace cert-manager  --create-namespace  --version v1.14.4  --set installCRDs=true
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm upgrade --install ingress-nginx ingress-nginx/ingress-nginx \
    --version 4.7.1 \
    --namespace ingress-basic \
    --create-namespace \
    --set controller.replicaCount=1 \
    --set controller.service.annotations."service\.beta\.kubernetes\.io/azure-load-balancer-health-probe-request-path"=/healthz \
    --set controller.service.externalTrafficPolicy=Cluster