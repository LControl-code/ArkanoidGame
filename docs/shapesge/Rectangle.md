# Rectangle

**Package:** `fri.shapesge`

## Description

A rectangle that can be manipulated and that draws itself on a canvas. This class provides functionality to create, position, resize, and color rectangles for use in graphical applications and games.

## Constructors

### `Rectangle()`

Creates a new rectangle with default size and position at the origin.

### `Rectangle(int width, int height)`

Creates a new rectangle with the specified dimensions at the default position.

**Parameters:**

- `width` - the width of the rectangle in pixels
- `height` - the height of the rectangle in pixels

### `Rectangle(int x, int y, int width, int height)`

Creates a new rectangle at the specified position with the given dimensions.

**Parameters:**

- `x` - the x-coordinate of the rectangle's top-left corner
- `y` - the y-coordinate of the rectangle's top-left corner
- `width` - the width of the rectangle in pixels
- `height` - the height of the rectangle in pixels

## Methods

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the rectangle to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the rectangle horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the rectangle vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the rectangle's top-left corner.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the rectangle's top-left corner.

**Returns:** the y-coordinate

### Size Methods

#### `void changeSize(int newWidth, int newHeight)`

Changes the dimensions of the rectangle.

**Parameters:**

- `newWidth` - the new width in pixels
- `newHeight` - the new height in pixels

#### `int getWidth()`

Returns the current width of the rectangle.

**Returns:** the width in pixels

#### `int getHeight()`

Returns the current height of the rectangle.

**Returns:** the height in pixels

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the rectangle.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the rectangle.

**Returns:** the color as a string

#### `void makeVisible()`

Makes the rectangle visible on the canvas.

#### `void makeInvisible()`

Makes the rectangle invisible on the canvas.

#### `boolean isVisible()`

Checks if the rectangle is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.Rectangle;

public class RectangleExample {
    public static void main(String[] args) {
        // Create a rectangle with width 100 and height 60
        Rectangle rect = new Rectangle(100, 60);

        // Position it at coordinates (50, 50)
        rect.changePosition(50, 50);

        // Change its color to blue
        rect.changeColor("blue");

        // Make it visible
        rect.makeVisible();

        // Resize it
        rect.changeSize(120, 80);

        // Move it around
        rect.moveHorizontal(30);
        rect.moveVertical(-20);
    }
}
```

## Notes

- The rectangle's position refers to its top-left corner
- Color names are case-insensitive
- The rectangle must be made visible to appear on the canvas
- All drawing operations are rendered on the canvas managed by the shapesge framework
- Useful for creating paddles, bricks, walls, and platforms in games
