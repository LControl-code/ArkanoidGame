# Music

**Package:** `fri.shapesge`

## Description

Represents background music in the game. This class provides functionality to load, play, pause, and control background music tracks for your game or application.

## Constructors

### `Music(String filename)`

Creates a new Music object by loading an audio file.

**Parameters:**

- `filename` - the path to the music file (supports MP3, WAV, OGG formats)

## Methods

### Playback Control

#### `void play()`

Starts playing the music from the beginning.

#### `void play(boolean loop)`

Starts playing the music with optional looping.

**Parameters:**

- `loop` - if `true`, the music will loop continuously; if `false`, it plays once

#### `void pause()`

Pauses the music playback. Can be resumed with `resume()`.

#### `void resume()`

Resumes music playback after being paused.

#### `void stop()`

Stops the music playback and resets to the beginning.

#### `boolean isPlaying()`

Checks if the music is currently playing.

**Returns:** `true` if playing, `false` otherwise

#### `boolean isPaused()`

Checks if the music is currently paused.

**Returns:** `true` if paused, `false` otherwise

### Volume Control

#### `void setVolume(double volume)`

Sets the volume level for the music.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `double getVolume()`

Gets the current volume level.

**Returns:** the volume level (0.0 to 1.0)

### Advanced Control

#### `void setLoop(boolean loop)`

Sets whether the music should loop continuously.

**Parameters:**

- `loop` - `true` to enable looping, `false` to play once

#### `boolean isLooping()`

Checks if the music is set to loop.

**Returns:** `true` if looping is enabled, `false` otherwise

## Usage Example

```java
import fri.shapesge.Music;

public class MusicExample {
    private Music backgroundMusic;
    private Music menuMusic;

    public MusicExample() {
        // Load background music for gameplay
        backgroundMusic = new Music("music/background.mp3");
        backgroundMusic.setVolume(0.7); // 70% volume
        backgroundMusic.play(true); // Loop continuously

        // Load menu music
        menuMusic = new Music("music/menu.mp3");
        menuMusic.setVolume(0.5);
    }

    public void startGame() {
        menuMusic.stop();
        backgroundMusic.play(true);
    }

    public void pauseGame() {
        backgroundMusic.pause();
    }

    public void resumeGame() {
        backgroundMusic.resume();
    }

    public void returnToMenu() {
        backgroundMusic.stop();
        menuMusic.play(true);
    }
}
```

## Game State Music Example

```java
import fri.shapesge.*;

public class GameWithMusic {
    private Music gameMusic;
    private Music victoryMusic;
    private Music gameOverMusic;
    private Manager manager;
    private boolean gameActive;

    public GameWithMusic() {
        manager = new Manager();

        // Load different music tracks
        gameMusic = new Music("sounds/gameplay.mp3");
        victoryMusic = new Music("sounds/victory.mp3");
        gameOverMusic = new Music("sounds/gameover.mp3");

        // Start with game music
        gameMusic.setVolume(0.6);
        gameMusic.play(true);
        gameActive = true;

        manager.manageObject(this);
    }

    public void tick() {
        // Game logic
        if (checkWinCondition()) {
            onVictory();
        } else if (checkLoseCondition()) {
            onGameOver();
        }
    }

    private void onVictory() {
        if (gameActive) {
            gameMusic.stop();
            victoryMusic.setVolume(0.8);
            victoryMusic.play(false); // Play once
            gameActive = false;
        }
    }

    private void onGameOver() {
        if (gameActive) {
            gameMusic.stop();
            gameOverMusic.setVolume(0.7);
            gameOverMusic.play(false); // Play once
            gameActive = false;
        }
    }

    public void onKeyPress(String key) {
        if (key.equals("m")) {
            // Toggle mute
            if (gameMusic.getVolume() > 0) {
                gameMusic.setVolume(0.0);
            } else {
                gameMusic.setVolume(0.6);
            }
        }
    }
}
```

## Supported Audio Formats

- **MP3** - Compressed format, good for longer tracks (recommended)
- **WAV** - Uncompressed format, larger file size
- **OGG** - Compressed format, good alternative to MP3

## Notes

- Music files should be placed in your project directory or subdirectory (e.g., "music/", "sounds/")
- Use relative paths from your project root
- MP3 is recommended for background music due to good compression and quality
- Only one music track can play at a time per Music object
- Set looping to `true` for continuous background music
- Lower volume (0.5-0.7) is often appropriate for background music
- Consider providing a mute/unmute option for players
- Music continues playing even when the game is paused unless explicitly paused
- Large music files may increase your application's file size
