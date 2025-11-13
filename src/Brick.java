import fri.shapesge.Rectangle;

/**
 * Represents an individual brick in the Arkanoid game.
 * Bricks can be destroyed when hit by the ball.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class Brick {
    // Fields
    private Rectangle shape;
    private boolean visible;
    private final int width = 60;
    private final int height = 12;
    private int x; // Track position internally
    private int y;

    /**
     * Creates a new Brick at the specified position.
     *
     * @param x the x-coordinate of the brick's top-left corner
     * @param y the y-coordinate of the brick's top-left corner
     */
    public Brick(int x, int y) {
        shape = new Rectangle(width, height);
        this.x = x;
        this.y = y;
        shape.changePosition(x, y);
        shape.changeColor("red");
        visible = true;
    }

    /**
     * Destroys the brick, making it invisible and marking it as not visible.
     */
    public void destroy() {
        visible = false;
        shape.makeInvisible();
    }

    /**
     * Gets the x-coordinate of the brick's top-left corner.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the brick's top-left corner.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the brick.
     *
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the brick.
     *
     * @return the height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Checks if the brick is currently visible.
     *
     * @return true if the brick is visible, false otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Makes the brick visible on the canvas.
     */
    public void makeVisible() {
        visible = true;
        shape.makeVisible();
    }

    /**
     * Makes the brick invisible on the canvas.
     */
    public void makeInvisible() {
        visible = false;
        shape.makeInvisible();
    }
}
