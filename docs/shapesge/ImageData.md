# ImageData

**Package:** `fri.shapesge`

## Description

The ImageData class represents a bitmap image that can be drawn using the `Image` class. It provides low-level access to image pixel data, allowing for programmatic image creation and manipulation.

## Constructors

### `ImageData(int width, int height)`

Creates a new blank ImageData with the specified dimensions.

**Parameters:**

- `width` - the width of the image in pixels
- `height` - the height of the image in pixels

### `ImageData(String filename)`

Creates ImageData by loading an image file.

**Parameters:**

- `filename` - the path to the image file

## Methods

### Pixel Manipulation

#### `void setPixel(int x, int y, int red, int green, int blue)`

Sets the color of a specific pixel.

**Parameters:**

- `x` - the x-coordinate of the pixel
- `y` - the y-coordinate of the pixel
- `red` - the red component (0-255)
- `green` - the green component (0-255)
- `blue` - the blue component (0-255)

#### `void setPixel(int x, int y, int red, int green, int blue, int alpha)`

Sets the color and transparency of a specific pixel.

**Parameters:**

- `x` - the x-coordinate of the pixel
- `y` - the y-coordinate of the pixel
- `red` - the red component (0-255)
- `green` - the green component (0-255)
- `blue` - the blue component (0-255)
- `alpha` - the alpha/transparency component (0-255, where 0 = transparent, 255 = opaque)

#### `int[] getPixel(int x, int y)`

Gets the color components of a specific pixel.

**Parameters:**

- `x` - the x-coordinate of the pixel
- `y` - the y-coordinate of the pixel

**Returns:** an array containing [red, green, blue, alpha] values (0-255)

### Dimension Methods

#### `int getWidth()`

Returns the width of the image data.

**Returns:** the width in pixels

#### `int getHeight()`

Returns the height of the image data.

**Returns:** the height in pixels

### Data Access

#### `void fill(int red, int green, int blue)`

Fills the entire image with a solid color.

**Parameters:**

- `red` - the red component (0-255)
- `green` - the green component (0-255)
- `blue` - the blue component (0-255)

#### `void clear()`

Clears the image data (sets all pixels to transparent).

## Usage Example

```java
import fri.shapesge.Image;
import fri.shapesge.ImageData;

public class ImageDataExample {
    public static void main(String[] args) {
        // Create a 100x100 blank image
        ImageData data = new ImageData(100, 100);

        // Draw a red diagonal line
        for (int i = 0; i < 100; i++) {
            data.setPixel(i, i, 255, 0, 0); // Red pixel
        }

        // Draw a blue horizontal line at y=50
        for (int x = 0; x < 100; x++) {
            data.setPixel(x, 50, 0, 0, 255); // Blue pixel
        }

        // Create an Image from this data
        Image customImage = new Image(data);
        customImage.changePosition(100, 100);
        customImage.makeVisible();
    }
}
```

## Procedural Texture Example

```java
public class ProceduralTexture {
    public static ImageData createCheckerboard(int size, int squares) {
        ImageData data = new ImageData(size, size);
        int squareSize = size / squares;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int squareX = x / squareSize;
                int squareY = y / squareSize;

                // Alternate between black and white
                if ((squareX + squareY) % 2 == 0) {
                    data.setPixel(x, y, 255, 255, 255); // White
                } else {
                    data.setPixel(x, y, 0, 0, 0); // Black
                }
            }
        }

        return data;
    }

    public static void main(String[] args) {
        ImageData checkerboard = createCheckerboard(200, 8);
        Image img = new Image(checkerboard);
        img.changePosition(50, 50);
        img.makeVisible();
    }
}
```

## Notes

- Pixel coordinates start at (0, 0) in the top-left corner
- Color components use values from 0 to 255
- Alpha value of 0 means fully transparent, 255 means fully opaque
- Modifying ImageData after creating an Image from it may require recreating the Image
- Useful for creating procedural textures, dynamic sprites, and pixel-based effects
- For large images, consider performance implications of pixel-by-pixel manipulation
