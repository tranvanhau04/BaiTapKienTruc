# PowerShell script to build and run all CQRS services

function Test-MavenInstalled {
    try {
        $null = mvn --version
        return $true
    }
    catch {
        return $false
    }
}

function Build-Service {
    param(
        [string]$ServiceName,
        [string]$ServicePath,
        [int]$ServiceNumber,
        [int]$TotalServices
    )

    Write-Host "[[$ServiceNumber/$TotalServices]] Building $ServiceName..." -ForegroundColor Cyan

    Push-Location $ServicePath
    & mvn clean install

    if ($LASTEXITCODE -ne 0) {
        Write-Host "ERROR: Failed to build $ServiceName" -ForegroundColor Red
        Pop-Location
        return $false
    }

    Pop-Location
    Write-Host "$ServiceName built successfully!" -ForegroundColor Green
    return $true
}

function Run-Service {
    param(
        [string]$ServiceName,
        [string]$ServicePath,
        [int]$Port
    )

    Write-Host "Starting $ServiceName (Port $Port)..." -ForegroundColor Yellow

    $process = Start-Process -FilePath "cmd.exe" `
        -ArgumentList "/c cd $ServicePath && mvn spring-boot:run" `
        -WindowStyle Normal `
        -PassThru `
        -NoNewWindow:$false

    Write-Host "$ServiceName started with PID: $($process.Id)" -ForegroundColor Green
    return $process
}

function Main {
    Clear-Host

    Write-Host "==========================================" -ForegroundColor Magenta
    Write-Host "CQRS Order Management System" -ForegroundColor Magenta
    Write-Host "==========================================" -ForegroundColor Magenta
    Write-Host ""

    # Check Maven
    if (-not (Test-MavenInstalled)) {
        Write-Host "ERROR: Maven is not installed or not in PATH" -ForegroundColor Red
        Write-Host "Please install Maven from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
        Read-Host "Press Enter to exit"
        exit 1
    }

    Write-Host "Maven found. Proceeding with build..." -ForegroundColor Green
    Write-Host ""

    # Build services
    $script:BuildSuccess = $true

    if (-not (Build-Service "Command Service" "order-command-service" 1 3)) {
        $script:BuildSuccess = $false
    }
    Write-Host ""

    if (-not (Build-Service "Query Service" "order-query-service" 2 3)) {
        $script:BuildSuccess = $false
    }
    Write-Host ""

    if (-not (Build-Service "UI Service" "order-ui-service" 3 3)) {
        $script:BuildSuccess = $false
    }

    if (-not $script:BuildSuccess) {
        Write-Host "Build failed!" -ForegroundColor Red
        Read-Host "Press Enter to exit"
        exit 1
    }

    Write-Host ""
    Write-Host "==========================================" -ForegroundColor Green
    Write-Host "All services built successfully!" -ForegroundColor Green
    Write-Host "==========================================" -ForegroundColor Green
    Write-Host ""

    # Ask to run services
    $runNow = Read-Host "Do you want to run services now? (Y/n)"

    if ($runNow -ne "n" -and $runNow -ne "N") {
        Write-Host ""
        Write-Host "Starting all services..." -ForegroundColor Cyan
        Write-Host ""

        $commandService = Run-Service "Command Service" "order-command-service" 8081
        Start-Sleep -Seconds 3

        $queryService = Run-Service "Query Service" "order-query-service" 8082
        Start-Sleep -Seconds 3

        $uiService = Run-Service "UI Service" "order-ui-service" 8080

        Write-Host ""
        Write-Host "==========================================" -ForegroundColor Green
        Write-Host "All services started!" -ForegroundColor Green
        Write-Host "==========================================" -ForegroundColor Green
        Write-Host ""
        Write-Host "Services available at:" -ForegroundColor Cyan
        Write-Host "  UI Service:      http://localhost:8080" -ForegroundColor Yellow
        Write-Host "  Command Service: http://localhost:8081" -ForegroundColor Yellow
        Write-Host "  Query Service:   http://localhost:8082" -ForegroundColor Yellow
        Write-Host ""

        Write-Host "Opening UI in default browser..." -ForegroundColor Cyan
        Start-Process "http://localhost:8080"

        Write-Host ""
        Write-Host "Press Ctrl+C to stop all services" -ForegroundColor Yellow
    }
    else {
        Write-Host ""
        Write-Host "To start services manually, run:" -ForegroundColor Cyan
        Write-Host "  - order-command-service:  mvn spring-boot:run" -ForegroundColor Yellow
        Write-Host "  - order-query-service:    mvn spring-boot:run" -ForegroundColor Yellow
        Write-Host "  - order-ui-service:       mvn spring-boot:run" -ForegroundColor Yellow
    }
}

# Run main function
Main

