# Arkanoid Game - Setup and Running Instructions

## Quick Start

### Linux/Mac
```bash
cd /home/user/ArkanoidGame
./run.sh
```

### Windows
```cmd
cd C:\path\to\ArkanoidGame
run.bat
```

## Manual Setup

### 1. Project Structure
Your project should have this structure:
```
ArkanoidGame/          <-- YOU RUN FROM HERE
├── spge.ini          <-- MUST BE HERE (configuration file)
├── src/              <-- Source files
│   ├── ArkanoidGame.java
│   ├── Ball.java
│   ├── Paddle.java
│   ├── Brick.java
│   ├── BrickGrid.java
│   ├── Collision.java
│   └── GameState.java
├── lib/
│   └── shapesGE-2.1.0.jar
├── bin/              <-- Compiled classes
└── run.sh            <-- Convenience script
```

### 2. Compile (if not already compiled)
```bash
# From the ArkanoidGame/ directory:
javac -cp lib/shapesGE-2.1.0.jar -d bin src/*.java
```

### 3. Run the Game
```bash
# From the ArkanoidGame/ directory:
java -cp "bin:lib/shapesGE-2.1.0.jar" ArkanoidGame
```

**Windows users**: Use semicolon instead of colon:
```cmd
java -cp "bin;lib/shapesGE-2.1.0.jar" ArkanoidGame
```

## Important Notes

### ⚠️ Working Directory
**The game MUST be run from the project root directory** (`ArkanoidGame/`). This is because:
1. The `spge.ini` configuration file must be in the working directory
2. The shapesge framework looks for `spge.ini` in the current working directory
3. If `spge.ini` is not found, the game will use default settings (wrong canvas size, etc.)

### ✅ Correct
```bash
cd /home/user/ArkanoidGame
java -cp "bin:lib/shapesGE-2.1.0.jar" ArkanoidGame
```

### ❌ Incorrect
```bash
cd /home/user/ArkanoidGame/bin
java -cp ".:../lib/shapesGE-2.1.0.jar" ArkanoidGame  # Won't find spge.ini!
```

## Configuration File (spge.ini)

The `spge.ini` file in the project root configures:
- **Canvas size**: 800x600 pixels
- **Background color**: Black
- **Frame rate**: 60 FPS
- **Keyboard mappings**: LEFT, RIGHT, SPACE, R

If you want to customize the game, edit `spge.ini`:
```ini
[Window]
Width = 800
Height = 600
Background = black
FPS = 60
```

## Troubleshooting

### "Could not find or load main class ArkanoidGame"
- Make sure you're in the `ArkanoidGame/` directory
- Check that `bin/ArkanoidGame.class` exists
- Verify the classpath includes both `bin` and `lib/shapesGE-2.1.0.jar`

### Game uses wrong canvas size or colors
- Make sure `spge.ini` is in the **current working directory**
- Run the game from the project root, not from `bin/` or `src/`

### Compilation errors
- Ensure Java SDK is installed (Java 8 or higher)
- Verify `lib/shapesGE-2.1.0.jar` exists
- Check that all source files are in `src/`

## Game Controls

| Key | Action |
|-----|--------|
| **LEFT Arrow** | Move paddle left |
| **RIGHT Arrow** | Move paddle right |
| **SPACE** | Pause/Resume game |
| **R** | Restart game |

## System Requirements

- Java Runtime Environment (JRE) 8 or higher
- Display resolution: At least 800x600
- Keyboard for input

## Running from IDE

If you're using an IDE (Eclipse, IntelliJ, VS Code):

1. **Set working directory** to the project root (`ArkanoidGame/`)
2. **Add library**: `lib/shapesGE-2.1.0.jar` to classpath
3. **Run**: `ArkanoidGame.java` main method
4. **Verify**: `spge.ini` is in the working directory

### IntelliJ IDEA
- Run → Edit Configurations → Working directory: `/home/user/ArkanoidGame`

### Eclipse
- Run → Run Configurations → Arguments tab → Working directory: `/home/user/ArkanoidGame`

### VS Code
Add to `.vscode/launch.json`:
```json
{
    "cwd": "${workspaceFolder}"
}
```

---

**Enjoy the game!** 🎮
