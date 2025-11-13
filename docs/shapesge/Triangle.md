# Triangle

**Package:** `fri.shapesge`

## Description

A triangle that can be manipulated and that draws itself on a canvas. This class provides functionality to create, position, resize, rotate, and color triangles for use in graphical applications and games.

## Constructors

### `Triangle()`

Creates a new triangle with default size and position at the origin.

### `Triangle(int size)`

Creates a new equilateral triangle with the specified size at the default position.

**Parameters:**

- `size` - the base width of the triangle in pixels

### `Triangle(int x, int y, int width, int height)`

Creates a new triangle at the specified position with the given dimensions.

**Parameters:**

- `x` - the x-coordinate of the triangle's base center
- `y` - the y-coordinate of the triangle's base
- `width` - the base width of the triangle in pixels
- `height` - the height of the triangle in pixels

## Methods

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the triangle to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the triangle horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the triangle vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the triangle's position.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the triangle's position.

**Returns:** the y-coordinate

### Size Methods

#### `void changeSize(int newWidth, int newHeight)`

Changes the dimensions of the triangle.

**Parameters:**

- `newWidth` - the new base width in pixels
- `newHeight` - the new height in pixels

#### `int getWidth()`

Returns the current base width of the triangle.

**Returns:** the width in pixels

#### `int getHeight()`

Returns the current height of the triangle.

**Returns:** the height in pixels

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the triangle.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the triangle.

**Returns:** the color as a string

#### `void makeVisible()`

Makes the triangle visible on the canvas.

#### `void makeInvisible()`

Makes the triangle invisible on the canvas.

#### `boolean isVisible()`

Checks if the triangle is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.Triangle;

public class TriangleExample {
    public static void main(String[] args) {
        // Create a triangle with width 60 and height 50
        Triangle triangle = new Triangle(100, 100, 60, 50);

        // Change its color to purple
        triangle.changeColor("purple");

        // Make it visible
        triangle.makeVisible();

        // Resize it
        triangle.changeSize(80, 70);

        // Move it around
        triangle.moveHorizontal(40);
        triangle.moveVertical(20);
    }
}
```

## Notes

- The triangle is typically drawn pointing upward by default
- The position refers to the base center of the triangle
- Color names are case-insensitive
- The triangle must be made visible to appear on the canvas
- Useful for creating arrows, pointers, and decorative elements in games
