# Ellipse

**Package:** `fri.shapesge`

## Description

An ellipse that can be manipulated and that draws itself on a canvas. This class provides functionality to create, position, resize, and color ellipses with separate control over horizontal and vertical radii.

## Constructors

### `Ellipse()`

Creates a new ellipse with default size and position at the origin.

### `Ellipse(int width, int height)`

Creates a new ellipse with the specified dimensions at the default position.

**Parameters:**

- `width` - the width (horizontal diameter) of the ellipse in pixels
- `height` - the height (vertical diameter) of the ellipse in pixels

### `Ellipse(int x, int y, int width, int height)`

Creates a new ellipse at the specified position with the given dimensions.

**Parameters:**

- `x` - the x-coordinate of the ellipse's center
- `y` - the y-coordinate of the ellipse's center
- `width` - the width of the ellipse in pixels
- `height` - the height of the ellipse in pixels

## Methods

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the ellipse to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the ellipse horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the ellipse vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the ellipse's center.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the ellipse's center.

**Returns:** the y-coordinate

### Size Methods

#### `void changeSize(int newWidth, int newHeight)`

Changes the dimensions of the ellipse.

**Parameters:**

- `newWidth` - the new width in pixels
- `newHeight` - the new height in pixels

#### `int getWidth()`

Returns the current width of the ellipse.

**Returns:** the width in pixels

#### `int getHeight()`

Returns the current height of the ellipse.

**Returns:** the height in pixels

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the ellipse.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the ellipse.

**Returns:** the color as a string

#### `void makeVisible()`

Makes the ellipse visible on the canvas.

#### `void makeInvisible()`

Makes the ellipse invisible on the canvas.

#### `boolean isVisible()`

Checks if the ellipse is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.Ellipse;

public class EllipseExample {
    public static void main(String[] args) {
        // Create an ellipse with width 80 and height 50
        Ellipse ellipse = new Ellipse(80, 50);

        // Position it at coordinates (150, 100)
        ellipse.changePosition(150, 100);

        // Change its color to green
        ellipse.changeColor("green");

        // Make it visible
        ellipse.makeVisible();

        // Resize it to be more circular
        ellipse.changeSize(70, 70);

        // Move it
        ellipse.moveVertical(30);
    }
}
```

## Notes

- The ellipse's position refers to its center point
- When width equals height, the ellipse becomes a circle
- Color names are case-insensitive
- The ellipse must be made visible to appear on the canvas
- Useful for creating balls, planets, and organic shapes in games
