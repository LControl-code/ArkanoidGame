#!/bin/bash
# Run script for Arkanoid Game

# Ensure we're in the project root directory
cd "$(dirname "$0")"

# Check if compiled
if [ ! -d "bin" ] || [ ! -f "bin/ArkanoidGame.class" ]; then
    echo "Game not compiled. Compiling now..."
    javac -cp lib/shapesGE-2.1.0.jar -d bin src/*.java
    if [ $? -ne 0 ]; then
        echo "Compilation failed!"
        exit 1
    fi
    echo "Compilation successful!"
fi

# Run the game
echo "Starting Arkanoid Game..."
java -cp "bin:lib/shapesGE-2.1.0.jar" ArkanoidGame
