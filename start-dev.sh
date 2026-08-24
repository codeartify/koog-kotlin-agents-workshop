#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

if [[ ! -f "$ROOT_DIR/.env" ]]; then
  echo "No .env file found. Copy .env.example to .env and add GOOGLE_API_KEY to enable the agent."
fi

cd "$ROOT_DIR"
exec docker compose up --build
