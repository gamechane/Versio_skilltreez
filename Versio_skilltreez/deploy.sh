#!/bin/bash

# Deployment script voor Gamechane VPS
# Gebruik: ./deploy.sh

set -e

echo "🚀 Starting deployment..."

# Check if .env exists
if [ ! -f .env ]; then
    echo "⚠️  .env file not found. Please create one based on .env.example"
    exit 1
fi

# Load environment variables
export $(cat .env | grep -v '^#' | xargs)

echo "📦 Building frontend..."
cd frontend
npm install
npm run build
cd ..

echo "🔨 Building backend..."
cd backend
mvn clean package -DskipTests
cd ..

echo "✅ Build complete!"
echo ""
echo "To start the application, run:"
echo "  java -jar backend/target/skilltreez-1.0.jar"
echo ""
echo "Or use systemd service:"
echo "  sudo systemctl restart gamechane"


