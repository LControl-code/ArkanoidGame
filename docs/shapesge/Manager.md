# Manager

**Package:** `fri.shapesge`

## Description

Sends messages to managed objects as defined in spge.ini. The Manager class is the core game loop controller that handles timing, keyboard input, and manages game objects. It provides the framework for creating interactive applications and games.

## Methods

### Game Loop Management

#### `void manageObject(Object object)`

Registers an object to be managed by the Manager. The object will receive tick updates and can respond to keyboard events.

**Parameters:**

- `object` - the object to be managed (typically your game controller or main game class)

#### `void stopManaging(Object object)`

Removes an object from management, stopping it from receiving updates.

**Parameters:**

- `object` - the object to stop managing

### Timing Methods

#### `void setTickInterval(int milliseconds)`

Sets the interval between tick updates in milliseconds. This controls the game loop speed.

**Parameters:**

- `milliseconds` - the time between ticks (e.g., 16ms ≈ 60 FPS, 33ms ≈ 30 FPS)

**Example:**

```java
manager.setTickInterval(16); // Approximately 60 FPS
```

#### `int getTickInterval()`

Returns the current tick interval in milliseconds.

**Returns:** the tick interval

### Canvas Management

#### `void setCanvasSize(int width, int height)`

Sets the size of the game canvas.

**Parameters:**

- `width` - the width of the canvas in pixels
- `height` - the height of the canvas in pixels

#### `int getCanvasWidth()`

Returns the width of the canvas.

**Returns:** the canvas width in pixels

#### `int getCanvasHeight()`

Returns the height of the canvas.

**Returns:** the canvas height in pixels

#### `void setCanvasBackground(String color)`

Sets the background color of the canvas.

**Parameters:**

- `color` - the background color as a string

### Game State Methods

#### `void pause()`

Pauses the game loop, stopping tick updates.

#### `void resume()`

Resumes the game loop after being paused.

#### `boolean isPaused()`

Checks if the game is currently paused.

**Returns:** `true` if paused, `false` otherwise

## Manager Events

Objects managed by the Manager can implement specific methods that will be called automatically:

### `void tick()`

Called at regular intervals based on the tick interval. This is where you update game logic.

**Example:**

```java
public void tick() {
    ball.move();
    checkCollisions();
    updateScore();
}
```

### Keyboard Event Methods

#### `void onKeyPress(String key)`

Called when a key is pressed down.

**Parameters:**

- `key` - the key that was pressed (e.g., "left", "right", "space", "a", "w")

#### `void onKeyRelease(String key)`

Called when a key is released.

**Parameters:**

- `key` - the key that was released

## Usage Example

```java
import fri.shapesge.*;

public class MyGame {
    private Manager manager;
    private Circle ball;
    private int velocityX;
    private int velocityY;

    public MyGame() {
        this.manager = new Manager();

        // Set up canvas
        manager.setCanvasSize(800, 600);
        manager.setCanvasBackground("black");

        // Set game speed to 60 FPS
        manager.setTickInterval(16);

        // Create game objects
        ball = new Circle(20);
        ball.changePosition(400, 300);
        ball.changeColor("white");
        ball.makeVisible();

        velocityX = 5;
        velocityY = 3;

        // Start managing this game
        manager.manageObject(this);
    }

    // Called every tick
    public void tick() {
        // Update ball position
        ball.moveHorizontal(velocityX);
        ball.moveVertical(velocityY);

        // Bounce off walls
        if (ball.getX() <= 0 || ball.getX() >= 800) {
            velocityX *= -1;
        }
        if (ball.getY() <= 0 || ball.getY() >= 600) {
            velocityY *= -1;
        }
    }

    // Handle keyboard input
    public void onKeyPress(String key) {
        if (key.equals("space")) {
            manager.pause();
        }
        if (key.equals("r")) {
            manager.resume();
        }
    }

    public void onKeyRelease(String key) {
        // Handle key release if needed
    }

    public static void main(String[] args) {
        new MyGame();
    }
}
```

## Supported Key Names

Common key names that can be used with keyboard events:

- Arrow keys: `"left"`, `"right"`, `"up"`, `"down"`
- Letters: `"a"`, `"b"`, `"c"`, ..., `"z"`
- Numbers: `"0"`, `"1"`, `"2"`, ..., `"9"`
- Special keys: `"space"`, `"enter"`, `"escape"`, `"shift"`, `"ctrl"`

## Notes

- Only one Manager instance should be created per application
- Objects must implement `tick()`, `onKeyPress()`, and `onKeyRelease()` methods to receive events
- The tick interval directly affects game speed and smoothness
- Lower tick intervals (e.g., 16ms) provide smoother animation but require more processing power
- The Manager handles the game loop automatically once an object is registered
- Configuration can be customized in the spge.ini file
