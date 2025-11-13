import fri.shapesge.Circle;

/**
 * Represents the ball in the Arkanoid game.
 * The ball moves continuously and bounces off walls, the paddle, and bricks.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class Ball {
    // Fields
    private Circle shape;
    private double velocityX;
    private double velocityY;
    private final double speed = 3.0;
    private final int radius = 10;
    private int previousX;
    private int previousY;

    /**
     * Creates a new Ball at the specified starting position.
     *
     * @param startX the initial x-coordinate of the ball's center
     * @param startY the initial y-coordinate of the ball's center
     */
    public Ball(int startX, int startY) {
        shape = new Circle(radius * 2, radius * 2);
        shape.changePosition(startX, startY);
        shape.changeColor("white");
        velocityX = speed;
        velocityY = -speed;
        previousX = startX;
        previousY = startY;
    }

    /**
     * Updates the ball's position based on its velocity.
     * Should be called every game tick.
     */
    public void update() {
        // Store previous position for swept collision detection
        previousX = shape.getX();
        previousY = shape.getY();

        int newX = shape.getX() + (int) velocityX;
        int newY = shape.getY() + (int) velocityY;
        shape.changePosition(newX, newY);
    }

    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return shape.getX();
    }

    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return shape.getY();
    }

    /**
     * Gets the diameter of the ball.
     *
     * @return the diameter in pixels
     */
    public int getDiameter() {
        return radius * 2;
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius in pixels
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Gets the previous x-coordinate of the ball's center.
     *
     * @return the previous x-coordinate
     */
    public int getPreviousX() {
        return previousX;
    }

    /**
     * Gets the previous y-coordinate of the ball's center.
     *
     * @return the previous y-coordinate
     */
    public int getPreviousY() {
        return previousY;
    }

    /**
     * Reverses the ball's horizontal velocity (makes it bounce left/right).
     */
    public void reverseHorizontal() {
        velocityX *= -1;
    }

    /**
     * Reverses the ball's vertical velocity (makes it bounce up/down).
     */
    public void reverseVertical() {
        velocityY *= -1;
    }

    /**
     * Makes the ball visible on the canvas.
     */
    public void makeVisible() {
        shape.makeVisible();
    }

    /**
     * Makes the ball invisible on the canvas.
     */
    public void makeInvisible() {
        shape.makeInvisible();
    }

    /**
     * Sets the ball's position to specific coordinates.
     * Used for repositioning after collision detection.
     *
     * @param x the new x-coordinate of the ball's center
     * @param y the new y-coordinate of the ball's center
     */
    public void setPosition(int x, int y) {
        shape.changePosition(x, y);
    }
}
