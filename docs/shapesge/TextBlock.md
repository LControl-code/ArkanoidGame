# TextBlock

**Package:** `fri.shapesge`

## Description

A text that can be manipulated and that draws itself on a canvas. This class provides functionality to display and manipulate text strings with various fonts, sizes, and colors.

## Constructors

### `TextBlock()`

Creates a new text block with default text and position at the origin.

### `TextBlock(String text)`

Creates a new text block with the specified text at the default position.

**Parameters:**

- `text` - the text string to display

### `TextBlock(String text, int x, int y)`

Creates a new text block at the specified position.

**Parameters:**

- `text` - the text string to display
- `x` - the x-coordinate of the text's position
- `y` - the y-coordinate of the text's position

## Methods

### Text Content Methods

#### `void changeText(String newText)`

Changes the text content displayed.

**Parameters:**

- `newText` - the new text string to display

#### `String getText()`

Returns the current text content.

**Returns:** the text string

### Positioning Methods

#### `void changePosition(int x, int y)`

Moves the text to a new position.

**Parameters:**

- `x` - the new x-coordinate
- `y` - the new y-coordinate

#### `void moveHorizontal(int distance)`

Moves the text horizontally by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = right, negative = left)

#### `void moveVertical(int distance)`

Moves the text vertically by the specified distance.

**Parameters:**

- `distance` - the distance to move (positive = down, negative = up)

#### `int getX()`

Returns the x-coordinate of the text's position.

**Returns:** the x-coordinate

#### `int getY()`

Returns the y-coordinate of the text's position.

**Returns:** the y-coordinate

### Appearance Methods

#### `void changeColor(String color)`

Changes the color of the text.

**Parameters:**

- `color` - the new color as a string (e.g., "red", "blue", "green")

#### `String getColor()`

Returns the current color of the text.

**Returns:** the color as a string

#### `void changeFont(String fontName)`

Changes the font family of the text.

**Parameters:**

- `fontName` - the name of the font (e.g., "Arial", "Courier", "Times")

#### `void changeFontSize(int size)`

Changes the size of the text font.

**Parameters:**

- `size` - the font size in points

#### `void changeFontStyle(FontStyle style)`

Changes the style of the text font.

**Parameters:**

- `style` - the font style (see FontStyle enum)

#### `int getFontSize()`

Returns the current font size.

**Returns:** the font size in points

#### `void makeVisible()`

Makes the text visible on the canvas.

#### `void makeInvisible()`

Makes the text invisible on the canvas.

#### `boolean isVisible()`

Checks if the text is currently visible.

**Returns:** `true` if visible, `false` otherwise

## Usage Example

```java
import fri.shapesge.TextBlock;
import fri.shapesge.FontStyle;

public class TextExample {
    public static void main(String[] args) {
        // Create a text block
        TextBlock scoreText = new TextBlock("Score: 0");

        // Position it
        scoreText.changePosition(50, 30);

        // Style it
        scoreText.changeColor("white");
        scoreText.changeFontSize(24);
        scoreText.changeFontStyle(FontStyle.BOLD);

        // Make it visible
        scoreText.makeVisible();

        // Update the text
        scoreText.changeText("Score: 100");

        // Create another text block for instructions
        TextBlock instructions = new TextBlock("Press SPACE to start", 300, 400);
        instructions.changeColor("yellow");
        instructions.changeFontSize(18);
        instructions.makeVisible();
    }
}
```

## Common Use Cases

### Score Display

```java
TextBlock score = new TextBlock("Score: 0", 10, 20);
score.changeColor("white");
score.changeFontSize(20);
score.makeVisible();

// Update during game
score.changeText("Score: " + currentScore);
```

### Lives Counter

```java
TextBlock lives = new TextBlock("Lives: 3", 10, 50);
lives.changeColor("red");
lives.makeVisible();
```

### Game Over Message

```java
TextBlock gameOver = new TextBlock("GAME OVER", 300, 250);
gameOver.changeColor("red");
gameOver.changeFontSize(48);
gameOver.changeFontStyle(FontStyle.BOLD);
gameOver.makeVisible();
```

## Notes

- The text position refers to the baseline of the leftmost character
- Font names are system-dependent; use common fonts for compatibility
- Color names are case-insensitive
- The text must be made visible to appear on the canvas
- Font size is measured in points (pt)
- Use TextBlock for displaying scores, lives, messages, and UI elements in games
