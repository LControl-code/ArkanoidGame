import fri.shapesge.Rectangle;

/**
 * Represents the paddle in the Arkanoid game.
 * The paddle is controlled by the player using keyboard input to move left and right.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class Paddle {
    // Fields
    private Rectangle shape;
    private final int speed = 8;
    private final int width = 150;
    private final int height = 12;
    private int canvasWidth;
    private int x; // Track position internally
    private int y;

    /**
     * Creates a new Paddle at the specified starting position.
     *
     * @param startX the initial x-coordinate of the paddle's top-left corner
     * @param startY the initial y-coordinate of the paddle's top-left corner
     * @param canvasWidth the width of the game canvas (for boundary checking)
     */
    public Paddle(int startX, int startY, int canvasWidth) {
        shape = new Rectangle(width, height);
        this.x = startX;
        this.y = startY;
        shape.changePosition(x, y);
        shape.changeColor("green");
        this.canvasWidth = canvasWidth;
    }

    /**
     * Moves the paddle left by the speed amount.
     * Prevents the paddle from moving past the left edge of the canvas.
     */
    public void moveLeft() {
        if (x > 0) {
            x -= speed;
            if (x < 0) {
                x = 0;
            }
            shape.changePosition(x, y);
        }
    }

    /**
     * Moves the paddle right by the speed amount.
     * Prevents the paddle from moving past the right edge of the canvas.
     */
    public void moveRight() {
        if (x + width < canvasWidth) {
            x += speed;
            if (x + width > canvasWidth) {
                x = canvasWidth - width;
            }
            shape.changePosition(x, y);
        }
    }

    /**
     * Gets the x-coordinate of the paddle's top-left corner.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the paddle's top-left corner.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the paddle.
     *
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the paddle.
     *
     * @return the height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Makes the paddle visible on the canvas.
     */
    public void makeVisible() {
        shape.makeVisible();
    }

    /**
     * Makes the paddle invisible on the canvas.
     */
    public void makeInvisible() {
        shape.makeInvisible();
    }
}
