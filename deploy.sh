#!/bin/bash
set -e

echo "=== Deploying TODO application ==="

cd /home/pmt/web/TODOListSpringBoot

echo "=== Pulling latest changes ==="
git pull --ff-only origin main

echo "=== Building and starting containers ==="
docker compose up -d --build

echo "=== Deployment complete ==="
docker compose ps
