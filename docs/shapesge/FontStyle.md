# FontStyle

**Package:** `fri.shapesge`

**Type:** Enum

## Description

Possible font styles for text rendering. This enum defines the different styles that can be applied to text displayed using the TextBlock class.

## Enum Constants

### `PLAIN`

Regular text with no special styling. This is the default font style.

**Usage:**

```java
textBlock.changeFontStyle(FontStyle.PLAIN);
```

### `BOLD`

Bold text with increased weight, making it appear darker and thicker.

**Usage:**

```java
textBlock.changeFontStyle(FontStyle.BOLD);
```

### `ITALIC`

Italic text with slanted characters, typically used for emphasis.

**Usage:**

```java
textBlock.changeFontStyle(FontStyle.ITALIC);
```

### `BOLD_ITALIC`

Text that is both bold and italic, combining both styling effects.

**Usage:**

```java
textBlock.changeFontStyle(FontStyle.BOLD_ITALIC);
```

## Usage Example

```java
import fri.shapesge.TextBlock;
import fri.shapesge.FontStyle;

public class FontStyleExample {
    public static void main(String[] args) {
        // Create text blocks with different styles
        TextBlock plainText = new TextBlock("This is plain text", 50, 50);
        plainText.changeFontStyle(FontStyle.PLAIN);
        plainText.makeVisible();

        TextBlock boldText = new TextBlock("This is bold text", 50, 100);
        boldText.changeFontStyle(FontStyle.BOLD);
        boldText.makeVisible();

        TextBlock italicText = new TextBlock("This is italic text", 50, 150);
        italicText.changeFontStyle(FontStyle.ITALIC);
        italicText.makeVisible();

        TextBlock boldItalicText = new TextBlock("This is bold italic text", 50, 200);
        boldItalicText.changeFontStyle(FontStyle.BOLD_ITALIC);
        boldItalicText.makeVisible();
    }
}
```

## Game UI Example

```java
import fri.shapesge.*;

public class GameUI {
    private TextBlock titleText;
    private TextBlock scoreLabel;
    private TextBlock scoreValue;
    private TextBlock instructions;

    public GameUI() {
        // Title - large, bold
        titleText = new TextBlock("ARKANOID", 300, 50);
        titleText.changeFontSize(48);
        titleText.changeFontStyle(FontStyle.BOLD);
        titleText.changeColor("yellow");
        titleText.makeVisible();

        // Score label - bold
        scoreLabel = new TextBlock("SCORE:", 20, 30);
        scoreLabel.changeFontSize(20);
        scoreLabel.changeFontStyle(FontStyle.BOLD);
        scoreLabel.changeColor("white");
        scoreLabel.makeVisible();

        // Score value - plain
        scoreValue = new TextBlock("0", 120, 30);
        scoreValue.changeFontSize(20);
        scoreValue.changeFontStyle(FontStyle.PLAIN);
        scoreValue.changeColor("white");
        scoreValue.makeVisible();

        // Instructions - italic
        instructions = new TextBlock("Press SPACE to start", 250, 500);
        instructions.changeFontSize(16);
        instructions.changeFontStyle(FontStyle.ITALIC);
        instructions.changeColor("gray");
        instructions.makeVisible();
    }

    public void updateScore(int score) {
        scoreValue.changeText(String.valueOf(score));
    }
}
```

## Menu System Example

```java
import fri.shapesge.*;

public class MenuSystem {
    private TextBlock[] menuItems;
    private int selectedIndex;

    public MenuSystem() {
        String[] options = {"Start Game", "Options", "Exit"};
        menuItems = new TextBlock[options.length];
        selectedIndex = 0;

        for (int i = 0; i < options.length; i++) {
            menuItems[i] = new TextBlock(options[i], 300, 200 + i * 50);
            menuItems[i].changeFontSize(24);
            menuItems[i].changeColor("white");
            menuItems[i].makeVisible();
        }

        updateSelection();
    }

    public void moveSelectionUp() {
        if (selectedIndex > 0) {
            selectedIndex--;
            updateSelection();
        }
    }

    public void moveSelectionDown() {
        if (selectedIndex < menuItems.length - 1) {
            selectedIndex++;
            updateSelection();
        }
    }

    private void updateSelection() {
        for (int i = 0; i < menuItems.length; i++) {
            if (i == selectedIndex) {
                // Selected item - bold and yellow
                menuItems[i].changeFontStyle(FontStyle.BOLD);
                menuItems[i].changeColor("yellow");
            } else {
                // Unselected item - plain and white
                menuItems[i].changeFontStyle(FontStyle.PLAIN);
                menuItems[i].changeColor("white");
            }
        }
    }
}
```

## Common Use Cases by Style

### PLAIN

- Regular body text
- Unselected menu items
- Score values
- General information

### BOLD

- Titles and headings
- Selected menu items
- Important labels (Score, Lives, Level)
- Emphasized information
- Game over messages

### ITALIC

- Subtitles
- Instructions
- Hints and tips
- Flavor text
- Credits

### BOLD_ITALIC

- Super emphasized text
- Special announcements
- Achievement notifications
- Critical warnings

## Notes

- FontStyle is an enum, so use it with the syntax `FontStyle.CONSTANT_NAME`
- Font styles can be combined with different font sizes and colors for varied text effects
- Bold text takes up slightly more space than plain text
- Italic text may appear slightly narrower and slanted to the right
- Not all fonts may support all styles equally well
- Use bold for emphasis and readability in game UIs
- Use italic sparingly for special cases to avoid reducing readability
- The effectiveness of each style depends on the chosen font family
