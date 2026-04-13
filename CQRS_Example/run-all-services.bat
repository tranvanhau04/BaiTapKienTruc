@echo off
REM Script to run all CQRS services

echo.
echo ============================================
echo   CQRS Order Management System
echo   Starting All Services
echo ============================================
echo.

REM Check if Maven is installed
mvn --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and add it to your PATH
    pause
    exit /b 1
)

echo Starting services...
echo.

REM Start Command Service
echo [1/3] Starting Command Service (Port 8081)...
start "Command Service - 8081" cmd /k "cd order-command-service && mvn spring-boot:run"
timeout /t 3

REM Start Query Service
echo [2/3] Starting Query Service (Port 8082)...
start "Query Service - 8082" cmd /k "cd order-query-service && mvn spring-boot:run"
timeout /t 3

REM Start UI Service
echo [3/3] Starting UI Service (Port 8080)...
start "UI Service - 8080" cmd /k "cd order-ui-service && mvn spring-boot:run"

echo.
echo ============================================
echo   All services are starting...
echo ============================================
echo.
echo Services will be available at:
echo   - UI Service:      http://localhost:8080
echo   - Command Service: http://localhost:8081
echo   - Query Service:   http://localhost:8082
echo.
echo Press any key to continue...
pause

