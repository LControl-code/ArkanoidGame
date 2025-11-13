# Circle

**Package:** `fri.shapesge`

## Description

A circle that can be manipulated and that draws itself on a canvas. This class provides functionality to create, position, resize, and color circles for use in graphical applications and games.

## Constructors

### `Circle()`

Creates a new circle with default size and position at the origin.

### `Circle(int diameter)`

Creates a new circle with the specified diameter at the default position.

**Parameters:**

- `diameter` - the diameter of the circle in pixels

### `Circle(int x, int y, int diameter)`

Creates a new circle at the specified position with the given diameter.

**Parameters:**

- `x` - the x-coordinate of the circle's center
- `y` - the y-coordinate of the circle's center
- `diameter` - the diameter of the circle in pixels

## Methods

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the circle to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the circle horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the circle vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the circle's center.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the circle's center.

**Returns:** the y-coordinate

### Size Methods

#### `void changeDiameter(int newDiameter)`

Changes the diameter of the circle.

**Parameters:**

- `newDiameter` - the new diameter in pixels

#### `int getDiameter()`

Returns the current diameter of the circle.

**Returns:** the diameter in pixels

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the circle.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the circle.

**Returns:** the color as a string

#### `void makeVisible()`

Makes the circle visible on the canvas.

#### `void makeInvisible()`

Makes the circle invisible on the canvas.

#### `boolean isVisible()`

Checks if the circle is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.Circle;

public class CircleExample {
    public static void main(String[] args) {
        // Create a circle with diameter 50
        Circle circle = new Circle(50);

        // Position it at coordinates (100, 100)
        circle.changePosition(100, 100);

        // Change its color to red
        circle.changeColor("red");

        // Make it visible
        circle.makeVisible();

        // Move it around
        circle.moveHorizontal(50);
        circle.moveVertical(30);
    }
}
```

## Notes

- The circle's position refers to its center point
- Color names are case-insensitive
- The circle must be made visible to appear on the canvas
- All drawing operations are rendered on the canvas managed by the shapesge framework
