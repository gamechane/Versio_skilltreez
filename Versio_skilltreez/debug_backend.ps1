$ErrorActionPreference = "Stop"

# 1. Setup Java Environment
$knownJavaPath = "C:\Program Files\Microsoft\jdk-17.0.17.10-hotspot"
$env:JAVA_HOME = $knownJavaPath
$env:Path = "$knownJavaPath\bin;$env:Path"

Write-Host "Java Configured:" -ForegroundColor Green
java -version

# 2. Ask for Password
Write-Host "`nEnter Database Password :" -ForegroundColor Cyan
$dbPassword = Read-Host -Prompt "Password" -AsSecureString
$plainPassword = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto([System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($dbPassword))

# 3. Run Backend Directly
Set-Location "backend"
Write-Host "`nStarting Backend... (Control+C to stop)" -ForegroundColor Cyan

# Fixed quoting:
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.jvmArguments=-Dspring.datasource.password=$plainPassword"
