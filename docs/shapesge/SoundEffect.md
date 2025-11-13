# SoundEffect

**Package:** `fri.shapesge`

## Description

Represents a sound effect in WAVE format. This class provides functionality to load and play short audio clips for game events like collisions, jumps, power-ups, and other sound effects.

## Constructors

### `SoundEffect(String filename)`

Creates a new SoundEffect by loading a WAVE audio file.

**Parameters:**

- `filename` - the path to the sound effect file (WAV format)

## Methods

### Playback Control

#### `void play()`

Plays the sound effect once from the beginning.

#### `void play(double volume)`

Plays the sound effect with a specific volume level.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `void stop()`

Stops all currently playing instances of this sound effect.

#### `boolean isPlaying()`

Checks if the sound effect is currently playing.

**Returns:** `true` if playing, `false` otherwise

### Volume Control

#### `void setVolume(double volume)`

Sets the default volume level for this sound effect.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `double getVolume()`

Gets the current default volume level.

**Returns:** the volume level (0.0 to 1.0)

## Usage Example

```java
import fri.shapesge.SoundEffect;

public class SoundEffectExample {
    private SoundEffect jumpSound;
    private SoundEffect coinSound;
    private SoundEffect explosionSound;

    public SoundEffectExample() {
        // Load sound effects
        jumpSound = new SoundEffect("sounds/jump.wav");
        coinSound = new SoundEffect("sounds/coin.wav");
        explosionSound = new SoundEffect("sounds/explosion.wav");

        // Set volumes
        jumpSound.setVolume(0.7);
        coinSound.setVolume(0.5);
        explosionSound.setVolume(0.9);
    }

    public void onPlayerJump() {
        jumpSound.play();
    }

    public void onCoinCollected() {
        coinSound.play();
    }

    public void onExplosion() {
        explosionSound.play();
    }
}
```

## Arkanoid Game Example

```java
import fri.shapesge.*;

public class ArkanoidGame {
    private Manager manager;
    private Circle ball;
    private Rectangle paddle;

    // Sound effects
    private SoundEffect paddleHitSound;
    private SoundEffect brickHitSound;
    private SoundEffect wallHitSound;
    private SoundEffect loseLifeSound;

    public ArkanoidGame() {
        manager = new Manager();

        // Load sound effects
        paddleHitSound = new SoundEffect("sounds/paddle_hit.wav");
        brickHitSound = new SoundEffect("sounds/brick_break.wav");
        wallHitSound = new SoundEffect("sounds/wall_bounce.wav");
        loseLifeSound = new SoundEffect("sounds/lose_life.wav");

        // Set volumes
        paddleHitSound.setVolume(0.6);
        brickHitSound.setVolume(0.7);
        wallHitSound.setVolume(0.4);
        loseLifeSound.setVolume(0.8);

        // Create game objects
        ball = new Circle(20);
        paddle = new Rectangle(100, 20);

        manager.manageObject(this);
    }

    public void tick() {
        // Check collisions
        if (ballHitsPaddle()) {
            paddleHitSound.play();
            // Bounce ball logic
        }

        if (ballHitsBrick()) {
            brickHitSound.play();
            // Destroy brick logic
        }

        if (ballHitsWall()) {
            wallHitSound.play();
            // Bounce ball logic
        }

        if (ballFallsOffScreen()) {
            loseLifeSound.play();
            // Lose life logic
        }
    }

    private boolean ballHitsPaddle() {
        // Collision detection logic
        return false;
    }

    private boolean ballHitsBrick() {
        return false;
    }

    private boolean ballHitsWall() {
        return false;
    }

    private boolean ballFallsOffScreen() {
        return false;
    }
}
```

## Multiple Sound Instances

```java
public class MultiSoundExample {
    private SoundEffect laserSound;

    public MultiSoundExample() {
        laserSound = new SoundEffect("sounds/laser.wav");
        laserSound.setVolume(0.5);
    }

    public void fireLaser() {
        // Multiple simultaneous plays are usually supported
        laserSound.play();
    }

    public void fireRapidLasers() {
        // Fire multiple lasers quickly
        for (int i = 0; i < 5; i++) {
            laserSound.play();
            // Small delay between shots
        }
    }
}
```

## Notes

- Sound effects are designed for short audio clips (typically under 2 seconds)
- WAV format is required for sound effects
- Multiple instances of the same sound effect can often play simultaneously
- Use Music class for longer background tracks
- Sound effects should be placed in your project directory or subdirectory (e.g., "sounds/")
- Use relative paths from your project root
- Keep sound effect files small for better performance
- Common sound effect types for games:
  - Collision sounds (bounce, hit, break)
  - Action sounds (jump, shoot, collect)
  - UI sounds (click, hover, select)
  - Environmental sounds (explosion, splash, footstep)
- Consider providing a sound effects volume control separate from music
- Too many simultaneous sound effects can cause audio mixing issues
