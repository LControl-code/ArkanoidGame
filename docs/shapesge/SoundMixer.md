# SoundMixer

**Package:** `fri.shapesge`

## Description

Used to control the game sound mixer. This class provides global audio controls for managing music and sound effects volumes across the entire application.

## Methods

### Global Volume Control

#### `static void setMasterVolume(double volume)`

Sets the master volume level for all audio in the application.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `static double getMasterVolume()`

Gets the current master volume level.

**Returns:** the master volume level (0.0 to 1.0)

### Music Volume Control

#### `static void setMusicVolume(double volume)`

Sets the volume level for all music tracks.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `static double getMusicVolume()`

Gets the current music volume level.

**Returns:** the music volume level (0.0 to 1.0)

### Sound Effects Volume Control

#### `static void setSoundEffectsVolume(double volume)`

Sets the volume level for all sound effects.

**Parameters:**

- `volume` - volume level from 0.0 (mute) to 1.0 (maximum)

#### `static double getSoundEffectsVolume()`

Gets the current sound effects volume level.

**Returns:** the sound effects volume level (0.0 to 1.0)

### Mute Control

#### `static void muteAll()`

Mutes all audio (music and sound effects).

#### `static void unmuteAll()`

Unmutes all audio, restoring previous volume levels.

#### `static void muteMusic()`

Mutes only music, leaving sound effects audible.

#### `static void unmuteMusic()`

Unmutes music, restoring previous music volume.

#### `static void muteSoundEffects()`

Mutes only sound effects, leaving music audible.

#### `static void unmuteSoundEffects()`

Unmutes sound effects, restoring previous sound effects volume.

#### `static boolean isMuted()`

Checks if all audio is muted.

**Returns:** `true` if all audio is muted, `false` otherwise

## Usage Example

```java
import fri.shapesge.SoundMixer;

public class AudioSettings {

    public static void initializeAudio() {
        // Set initial volumes
        SoundMixer.setMasterVolume(0.8);   // 80% master volume
        SoundMixer.setMusicVolume(0.6);    // 60% music volume
        SoundMixer.setSoundEffectsVolume(0.7); // 70% sound effects volume
    }

    public static void toggleMute() {
        if (SoundMixer.isMuted()) {
            SoundMixer.unmuteAll();
        } else {
            SoundMixer.muteAll();
        }
    }

    public static void increaseMasterVolume() {
        double currentVolume = SoundMixer.getMasterVolume();
        if (currentVolume < 1.0) {
            SoundMixer.setMasterVolume(Math.min(1.0, currentVolume + 0.1));
        }
    }

    public static void decreaseMasterVolume() {
        double currentVolume = SoundMixer.getMasterVolume();
        if (currentVolume > 0.0) {
            SoundMixer.setMasterVolume(Math.max(0.0, currentVolume - 0.1));
        }
    }
}
```

## Settings Menu Example

```java
import fri.shapesge.*;

public class SettingsMenu {
    private TextBlock masterVolumeLabel;
    private TextBlock musicVolumeLabel;
    private TextBlock sfxVolumeLabel;

    public SettingsMenu() {
        // Create volume labels
        masterVolumeLabel = new TextBlock("Master: 80%", 50, 100);
        musicVolumeLabel = new TextBlock("Music: 60%", 50, 150);
        sfxVolumeLabel = new TextBlock("SFX: 70%", 50, 200);

        masterVolumeLabel.makeVisible();
        musicVolumeLabel.makeVisible();
        sfxVolumeLabel.makeVisible();
    }

    public void onKeyPress(String key) {
        switch (key) {
            case "1": // Increase master volume
                adjustVolume("master", 0.1);
                break;
            case "2": // Decrease master volume
                adjustVolume("master", -0.1);
                break;
            case "3": // Increase music volume
                adjustVolume("music", 0.1);
                break;
            case "4": // Decrease music volume
                adjustVolume("music", -0.1);
                break;
            case "5": // Increase SFX volume
                adjustVolume("sfx", 0.1);
                break;
            case "6": // Decrease SFX volume
                adjustVolume("sfx", -0.1);
                break;
            case "m": // Toggle mute
                SoundMixer.muteAll();
                break;
            case "u": // Unmute
                SoundMixer.unmuteAll();
                break;
        }
        updateLabels();
    }

    private void adjustVolume(String type, double delta) {
        double newVolume;
        switch (type) {
            case "master":
                newVolume = clamp(SoundMixer.getMasterVolume() + delta);
                SoundMixer.setMasterVolume(newVolume);
                break;
            case "music":
                newVolume = clamp(SoundMixer.getMusicVolume() + delta);
                SoundMixer.setMusicVolume(newVolume);
                break;
            case "sfx":
                newVolume = clamp(SoundMixer.getSoundEffectsVolume() + delta);
                SoundMixer.setSoundEffectsVolume(newVolume);
                break;
        }
    }

    private double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    private void updateLabels() {
        int masterPercent = (int)(SoundMixer.getMasterVolume() * 100);
        int musicPercent = (int)(SoundMixer.getMusicVolume() * 100);
        int sfxPercent = (int)(SoundMixer.getSoundEffectsVolume() * 100);

        masterVolumeLabel.changeText("Master: " + masterPercent + "%");
        musicVolumeLabel.changeText("Music: " + musicPercent + "%");
        sfxVolumeLabel.changeText("SFX: " + sfxPercent + "%");
    }
}
```

## Game with Audio Controls Example

```java
import fri.shapesge.*;

public class GameWithAudioControls {
    private Manager manager;
    private Music backgroundMusic;
    private SoundEffect jumpSound;

    public GameWithAudioControls() {
        // Initialize audio settings
        SoundMixer.setMasterVolume(0.8);
        SoundMixer.setMusicVolume(0.5);
        SoundMixer.setSoundEffectsVolume(0.7);

        // Create audio objects
        backgroundMusic = new Music("music/game.mp3");
        backgroundMusic.play(true);

        jumpSound = new SoundEffect("sounds/jump.wav");

        manager = new Manager();
        manager.manageObject(this);
    }

    public void onKeyPress(String key) {
        if (key.equals("m")) {
            // Toggle mute all audio
            if (SoundMixer.isMuted()) {
                SoundMixer.unmuteAll();
            } else {
                SoundMixer.muteAll();
            }
        } else if (key.equals("n")) {
            // Toggle mute music only
            SoundMixer.muteMusic();
        } else if (key.equals("b")) {
            // Toggle mute sound effects only
            SoundMixer.muteSoundEffects();
        } else if (key.equals("space")) {
            jumpSound.play();
        }
    }
}
```

## Notes

- SoundMixer provides global controls that affect all audio in the application
- All methods are static, so no instance of SoundMixer needs to be created
- Master volume affects both music and sound effects
- Individual music and sound effects volumes are multiplied by the master volume
- For example: If master = 0.8 and music = 0.5, effective music volume = 0.4
- Muting preserves volume settings, so unmuting restores previous levels
- Changes to SoundMixer settings affect all Music and SoundEffect objects immediately
- Useful for implementing options menus and quick mute functionality
- Consider saving user's volume preferences to a file for persistence between sessions
