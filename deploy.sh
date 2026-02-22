#!/bin/bash

# Deployment script voor Gamechane VPS
# Gebruik: ./deploy.sh

set -e

echo "🚀 Starting deployment..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if .env exists
if [ ! -f .env ]; then
    echo -e "${RED}⚠️  .env file not found.${NC}"
    if [ -f env.example ]; then
        echo -e "${YELLOW}Creating .env from env.example...${NC}"
        cp env.example .env
        echo -e "${YELLOW}Please edit .env and fill in the required values!${NC}"
        exit 1
    else
        echo -e "${RED}Please create a .env file based on env.example${NC}"
        exit 1
    fi
fi

# Validate required environment variables
echo "🔍 Validating environment variables..."
source .env

REQUIRED_VARS=("DATABASE_URL" "DATABASE_USERNAME" "DATABASE_PASSWORD" "JWT_SECRET")
MISSING_VARS=()

for var in "${REQUIRED_VARS[@]}"; do
    if [ -z "${!var}" ] || [[ "${!var}" == *"your_"* ]] || [[ "${!var}" == *"here"* ]]; then
        MISSING_VARS+=("$var")
    fi
done

if [ ${#MISSING_VARS[@]} -ne 0 ]; then
    echo -e "${RED}❌ Missing or incomplete environment variables:${NC}"
    printf '%s\n' "${MISSING_VARS[@]}"
    echo -e "${YELLOW}Please update your .env file with proper values.${NC}"
    exit 1
fi

# Validate JWT_SECRET length
if [ ${#JWT_SECRET} -lt 32 ]; then
    echo -e "${RED}❌ JWT_SECRET must be at least 32 characters long!${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Environment variables validated${NC}"

# Check Node.js version
echo "📋 Checking Node.js version..."
NODE_VERSION=$(node -v 2>/dev/null || echo "not installed")
echo "   Node.js: $NODE_VERSION"

# Check Java version
echo "📋 Checking Java version..."
JAVA_VERSION=$(java -version 2>&1 | head -n 1 || echo "not installed")
echo "   Java: $JAVA_VERSION"

# Check Maven version
echo "📋 Checking Maven version..."
MAVEN_VERSION=$(mvn -version 2>&1 | head -n 1 || echo "not installed")
echo "   Maven: $MAVEN_VERSION"

# Build frontend
echo ""
echo "📦 Building frontend..."
cd frontend

if [ ! -f package.json ]; then
    echo -e "${RED}❌ package.json not found in frontend directory!${NC}"
    exit 1
fi

npm install
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Frontend npm install failed!${NC}"
    exit 1
fi

npm run build
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Frontend build failed!${NC}"
    exit 1
fi

# Verify frontend build
if [ ! -d "dist" ]; then
    echo -e "${RED}❌ Frontend dist directory not found after build!${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Frontend build complete${NC}"
cd ..

# Build backend
echo ""
echo "🔨 Building backend..."
cd backend

mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Backend build failed!${NC}"
    exit 1
fi

# Verify backend build
JAR_FILE="target/skilltreez-1.0.jar"
if [ ! -f "$JAR_FILE" ]; then
    echo -e "${RED}❌ Backend JAR file not found: $JAR_FILE${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Backend build complete${NC}"
cd ..

echo ""
echo -e "${GREEN}✅ Build complete!${NC}"
echo ""
echo "📝 Next steps:"
echo "   1. Test the application:"
echo "      java -jar backend/target/skilltreez-1.0.jar"
echo ""
echo "   2. Or restart the systemd service:"
echo "      sudo systemctl restart gamechane"
echo ""
echo "   3. Check service status:"
echo "      sudo systemctl status gamechane"
echo ""
echo "   4. View logs:"
echo "      sudo journalctl -u gamechane -f"


