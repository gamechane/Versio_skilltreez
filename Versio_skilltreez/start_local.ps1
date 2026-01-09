$ErrorActionPreference = "Stop"

# 1. Setup Java
# Try to find java in PATH, otherwise use the specific path we found
if (-not (Get-Command "java" -ErrorAction SilentlyContinue)) {
    $knownJavaPath = "C:\Program Files\Microsoft\jdk-17.0.17.10-hotspot\bin"
    if (Test-Path $knownJavaPath) {
        Write-Host "Adding Java to PATH: $knownJavaPath" -ForegroundColor Yellow
        $env:Path = "$knownJavaPath;$env:Path"
        # Set JAVA_HOME (required for Maven Wrapper)
        $env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-17.0.17.10-hotspot"
        Write-Host "Set JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Yellow
    }
    else {
        Write-Error "CRITICAL: Java not found in PATH or standard locations."
        exit 1
    }
}
Write-Host "Java Version check:" -ForegroundColor Green
java -version

# 2. Check other tools
if (-not (Get-Command "npm" -ErrorAction SilentlyContinue)) {
    Write-Error "CRITICAL: npm is not installed. Please install Node.js."
    exit 1
}

try {
    # 3. Database Credentials
    Write-Host "`nDatabase Configuration" -ForegroundColor Cyan
    Write-Host "Enter the password for the 'postgres' user (to connect to 'gamechane' db)."
    $dbPassword = Read-Host -Prompt "Password" -AsSecureString
    $plainPassword = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto([System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($dbPassword))

    # 4. Start Backend (using Maven Wrapper)
    Write-Host "`nStarting Backend (Maven Wrapper)..." -ForegroundColor Cyan
    Set-Location "backend"
    
    # Use mvnw.cmd on Windows
    if (-not (Test-Path "mvnw.cmd")) {
        Write-Error "mvnw.cmd not found in backend directory!"
        exit 1
    }

    $mvnCommand = "/c .\mvnw.cmd spring-boot:run ""-Dspring-boot.run.jvmArguments=-Dspring.datasource.password=$plainPassword"""
    Start-Process -FilePath "cmd" -ArgumentList $mvnCommand -WorkingDirectory "."

    # 5. Start Frontend
    Write-Host "Starting Frontend (Vite)..." -ForegroundColor Cyan
    Set-Location "..\frontend"
    Start-Process -FilePath "cmd" -ArgumentList "/c npm run dev" -WorkingDirectory "."

    Write-Host "`nSUCCESS! Application is starting." -ForegroundColor Green
    Write-Host "Backend API: http://localhost:8080"
    Write-Host "Frontend UI: http://localhost:5173"
    
}
catch {
    Write-Error $_
    Read-Host "Press Enter to exit..."
}
