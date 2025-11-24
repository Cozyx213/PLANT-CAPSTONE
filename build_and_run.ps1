<#
PowerShell helper to compile and run the Java project on Windows.

Usage (PowerShell):
  Open PowerShell (preferably as the user who installed Java), cd to the project root and run:
    .\build_and_run.ps1

This script:
 - checks for `javac` and `java` on PATH and warns if missing
 - compiles all .java files under src\main\java into an `out` directory
 - runs the main class `com.plantfarmlogger.Main`
#>

Write-Host "Running Windows build_and_run.ps1" -ForegroundColor Cyan

# Check for javac and java
$javac = Get-Command javac -ErrorAction SilentlyContinue
$java = Get-Command java -ErrorAction SilentlyContinue

if (-not $javac -or -not $java) {
    Write-Host "ERROR: 'javac' and/or 'java' not found on PATH." -ForegroundColor Red
    Write-Host "Fix options: (a) Install a JDK on Windows and add its \bin to PATH, or (b) run the existing build_and_run.sh inside WSL after installing OpenJDK there." -ForegroundColor Yellow
    Write-Host "Verify by running: java -version and javac -version" -ForegroundColor Yellow
    exit 1
}

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Definition
$srcDir = Join-Path $projectRoot "src\main\java"
$outDir = Join-Path $projectRoot "out"

if (-Not (Test-Path $srcDir)) {
    Write-Host "Source directory not found: $srcDir" -ForegroundColor Red
    exit 1
}

# Clean/create out dir
if (Test-Path $outDir) { Remove-Item $outDir -Recurse -Force }
New-Item -ItemType Directory -Path $outDir | Out-Null

# Find .java files
$javaFiles = Get-ChildItem -Path $srcDir -Recurse -Filter *.java | ForEach-Object { $_.FullName }
if ($javaFiles.Count -eq 0) {
    Write-Host "No .java files found under $srcDir" -ForegroundColor Red
    exit 1
}

Write-Host "Compiling Java sources..." -ForegroundColor Green
& javac -d $outDir $javaFiles
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed (javac returned $LASTEXITCODE)." -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "Compilation succeeded. Running main class com.plantfarmlogger.Main" -ForegroundColor Green
& java -cp $outDir com.plantfarmlogger.Main
