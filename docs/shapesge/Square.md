# Square

**Package:** `fri.shapesge`

## Description

A square that can be manipulated and that draws itself on a canvas. This class is a specialized rectangle where width and height are always equal.

## Constructors

### `Square()`

Creates a new square with default size and position at the origin.

### `Square(int size)`

Creates a new square with the specified size at the default position.

**Parameters:**

- `size` - the side length of the square in pixels

### `Square(int x, int y, int size)`

Creates a new square at the specified position with the given size.

**Parameters:**

- `x` - the x-coordinate of the square's top-left corner
- `y` - the y-coordinate of the square's top-left corner
- `size` - the side length of the square in pixels

## Methods

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the square to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the square horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the square vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the square's top-left corner.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the square's top-left corner.

**Returns:** the y-coordinate

### Size Methods

#### `void changeSize(int newSize)`

Changes the size of the square.

**Parameters:**

- `newSize` - the new side length in pixels

#### `int getSize()`

Returns the current size of the square.

**Returns:** the side length in pixels

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the square.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the square.

**Returns:** the color as a string

#### `void makeVisible()`

Makes the square visible on the canvas.

#### `void makeInvisible()`

Makes the square invisible on the canvas.

#### `boolean isVisible()`

Checks if the square is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.Square;

public class SquareExample {
    public static void main(String[] args) {
        // Create a square with size 50
        Square square = new Square(50);

        // Position it at coordinates (100, 100)
        square.changePosition(100, 100);

        // Change its color to yellow
        square.changeColor("yellow");

        // Make it visible
        square.makeVisible();

        // Resize it
        square.changeSize(70);

        // Move it
        square.moveHorizontal(20);
    }
}
```

## Notes

- The square's position refers to its top-left corner
- A square maintains equal width and height at all times
- Color names are case-insensitive
- The square must be made visible to appear on the canvas
- Useful for creating tiles, grid-based games, and uniform game elements
