# Image

**Package:** `fri.shapesge`

## Description

The Image class represents a bitmap image that can be drawn on the canvas. It provides functionality to load, display, position, resize, and manipulate images in your game or application.

## Constructors

### `Image()`

Creates a new empty image.

### `Image(String filename)`

Creates a new image by loading it from a file.

**Parameters:**

- `filename` - the path to the image file (supports common formats: PNG, JPG, GIF, BMP)

### `Image(ImageData imageData)`

Creates a new image from ImageData.

**Parameters:**

- `imageData` - the ImageData object containing the bitmap data

### `Image(String filename, int x, int y)`

Creates a new image from a file at the specified position.

**Parameters:**

- `filename` - the path to the image file
- `x` - the x-coordinate of the image's top-left corner
- `y` - the y-coordinate of the image's top-left corner

## Methods

### Loading Methods

#### `void loadImage(String filename)`

Loads an image from a file.

**Parameters:**

- `filename` - the path to the image file

#### `void setImageData(ImageData imageData)`

Sets the image content using ImageData.

**Parameters:**

- `imageData` - the ImageData object

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the image to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the image horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the image vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the image's top-left corner.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the image's top-left corner.

**Returns:** the y-coordinate

### Size Methods

#### `void changeSize(int newWidth, int newHeight)`

Resizes the image to the specified dimensions.

**Parameters:**

- `newWidth` - the new width in pixels
- `newHeight` - the new height in pixels

#### `void scale(double factor)`

Scales the image by a factor (1.0 = original size, 2.0 = double size, 0.5 = half size).

**Parameters:**

- `factor` - the scaling factor

#### `int getWidth()`

Returns the current width of the image.

**Returns:** the width in pixels

#### `int getHeight()`

Returns the current height of the image.

**Returns:** the height in pixels

### Appearance Methods

#### `void makeVisible()`

Makes the image visible on the canvas.

#### `void makeInvisible()`

Makes the image invisible on the canvas.

#### `boolean isVisible()`

Checks if the image is currently visible.

**Returns:** `true` if visible, `false` otherwise

#### `void setTransparency(double alpha)`

Sets the transparency level of the image.

**Parameters:**

- `alpha` - transparency value from 0.0 (fully transparent) to 1.0 (fully opaque)

### Rotation Methods

#### `void rotate(double degrees)`

Rotates the image by the specified angle.

**Parameters:**

- `degrees` - the rotation angle in degrees (positive = clockwise)

#### `void setRotation(double degrees)`

Sets the absolute rotation angle of the image.

**Parameters:**

- `degrees` - the rotation angle in degrees

## Usage Example

```java
import fri.shapesge.Image;

public class ImageExample {
    public static void main(String[] args) {
        // Load a background image
        Image background = new Image("background.png");
        background.changePosition(0, 0);
        background.makeVisible();

        // Load a player sprite
        Image player = new Image("player.png", 100, 200);
        player.scale(0.5); // Make it half size
        player.makeVisible();

        // Load an enemy sprite
        Image enemy = new Image("enemy.png");
        enemy.changePosition(400, 150);
        enemy.setTransparency(0.8); // Slightly transparent
        enemy.makeVisible();

        // Rotate an object
        Image arrow = new Image("arrow.png", 300, 300);
        arrow.rotate(45); // Rotate 45 degrees
        arrow.makeVisible();
    }
}
```

## Game Development Example

```java
import fri.shapesge.*;

public class SpriteGame {
    private Image playerSprite;
    private Manager manager;

    public SpriteGame() {
        manager = new Manager();

        // Load player sprite
        playerSprite = new Image("sprites/player.png");
        playerSprite.changePosition(400, 500);
        playerSprite.scale(2.0); // Double size
        playerSprite.makeVisible();

        manager.manageObject(this);
    }

    public void tick() {
        // Game logic here
    }

    public void onKeyPress(String key) {
        if (key.equals("left")) {
            playerSprite.moveHorizontal(-5);
        }
        if (key.equals("right")) {
            playerSprite.moveHorizontal(5);
        }
    }
}
```

## Supported Image Formats

- PNG (recommended for sprites with transparency)
- JPG/JPEG (good for backgrounds)
- GIF (supports animation in some implementations)
- BMP (basic bitmap format)

## Notes

- Image files should be placed in your project directory or a subdirectory (e.g., "images/", "sprites/")
- Use relative paths from your project root
- PNG format is recommended for game sprites due to transparency support
- Large images may impact performance; consider resizing images externally
- The image's position refers to its top-left corner
- Scaling preserves the aspect ratio if only one dimension is specified
- Rotation occurs around the image's center point
- Transparency affects rendering order and performance
