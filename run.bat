@echo off
REM Run script for Arkanoid Game (Windows)

REM Change to script directory
cd /d "%~dp0"

REM Check if compiled
if not exist "bin\ArkanoidGame.class" (
    echo Game not compiled. Compiling now...
    javac -cp lib/shapesGE-2.1.0.jar -d bin src/*.java
    if errorlevel 1 (
        echo Compilation failed!
        pause
        exit /b 1
    )
    echo Compilation successful!
)

REM Run the game
echo Starting Arkanoid Game...
java -cp "bin;lib/shapesGE-2.1.0.jar" ArkanoidGame
pause
