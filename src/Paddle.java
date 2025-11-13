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

    /**
     * Creates a new Paddle at the specified starting position.
     *
     * @param startX the initial x-coordinate of the paddle's top-left corner
     * @param startY the initial y-coordinate of the paddle's top-left corner
     * @param canvasWidth the width of the game canvas (for boundary checking)
     */
    public Paddle(int startX, int startY, int canvasWidth) {
        shape = new Rectangle(width, height);
        shape.changePosition(startX, startY);
        shape.changeColor("green");
        this.canvasWidth = canvasWidth;
    }

    /**
     * Moves the paddle left by the speed amount.
     * Prevents the paddle from moving past the left edge of the canvas.
     */
    public void moveLeft() {
        int currentX = shape.getX();
        if (currentX > 0) {
            int newX = currentX - speed;
            if (newX < 0) {
                newX = 0;
            }
            shape.changePosition(newX, shape.getY());
        }
    }

    /**
     * Moves the paddle right by the speed amount.
     * Prevents the paddle from moving past the right edge of the canvas.
     */
    public void moveRight() {
        int currentX = shape.getX();
        if (currentX + width < canvasWidth) {
            int newX = currentX + speed;
            if (newX + width > canvasWidth) {
                newX = canvasWidth - width;
            }
            shape.changePosition(newX, shape.getY());
        }
    }

    /**
     * Gets the x-coordinate of the paddle's top-left corner.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return shape.getX();
    }

    /**
     * Gets the y-coordinate of the paddle's top-left corner.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return shape.getY();
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
