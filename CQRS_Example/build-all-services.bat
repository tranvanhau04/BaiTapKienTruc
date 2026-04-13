@echo off
REM Script to build all CQRS services

echo.
echo ============================================
echo   Building CQRS Order Management System
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

REM Build Command Service
echo [1/3] Building Command Service...
cd order-command-service
call mvn clean install
if errorlevel 1 (
    echo ERROR: Failed to build Command Service
    pause
    exit /b 1
)
cd ..
echo Command Service built successfully!
echo.

REM Build Query Service
echo [2/3] Building Query Service...
cd order-query-service
call mvn clean install
if errorlevel 1 (
    echo ERROR: Failed to build Query Service
    pause
    exit /b 1
)
cd ..
echo Query Service built successfully!
echo.

REM Build UI Service
echo [3/3] Building UI Service...
cd order-ui-service
call mvn clean install
if errorlevel 1 (
    echo ERROR: Failed to build UI Service
    pause
    exit /b 1
)
cd ..
echo UI Service built successfully!
echo.

echo ============================================
echo   All services built successfully!
echo ============================================
echo.
echo Next steps:
echo   1. Run: run-all-services.bat
echo   2. Access UI at: http://localhost:8080
echo.
pause

