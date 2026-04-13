#!/bin/bash
# Linux/Mac Startup Script for CQRS Services

echo ""
echo "=========================================="
echo "CQRS Order Management System"
echo "Startup Script for Linux/Mac"
echo "=========================================="
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed"
    echo "Install Maven from: https://maven.apache.org/download.cgi"
    exit 1
fi

echo "Maven found. Starting services..."
echo ""

# Function to run a service
run_service() {
    local SERVICE_NAME=$1
    local SERVICE_PATH=$2
    local PORT=$3

    echo "Starting $SERVICE_NAME (Port $PORT)..."
    (cd "$SERVICE_PATH" && mvn spring-boot:run) &
    echo "Background PID: $!"
}

# Start services
run_service "Command Service" "order-command-service" 8081
sleep 3

run_service "Query Service" "order-query-service" 8082
sleep 3

run_service "UI Service" "order-ui-service" 8080

echo ""
echo "=========================================="
echo "All services started!"
echo "=========================================="
echo ""
echo "Services available at:"
echo "  - UI Service:      http://localhost:8080"
echo "  - Command Service: http://localhost:8081"
echo "  - Query Service:   http://localhost:8082"
echo ""
echo "Press Ctrl+C to stop all services"
echo ""

# Keep the script running
wait

