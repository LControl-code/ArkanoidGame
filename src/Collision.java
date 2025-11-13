/**
 * Utility class for collision detection in the Arkanoid game.
 * Provides static methods for detecting collisions between various shapes and walls.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class Collision {

    /**
     * Checks if two rectangles collide using axis-aligned bounding box (AABB) collision detection.
     *
     * @param x1 the x-coordinate of the first rectangle's top-left corner
     * @param y1 the y-coordinate of the first rectangle's top-left corner
     * @param w1 the width of the first rectangle
     * @param h1 the height of the first rectangle
     * @param x2 the x-coordinate of the second rectangle's top-left corner
     * @param y2 the y-coordinate of the second rectangle's top-left corner
     * @param w2 the width of the second rectangle
     * @param h2 the height of the second rectangle
     * @return true if the rectangles collide, false otherwise
     */
    public static boolean rectsCollide(int x1, int y1, int w1, int h1,
                                       int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 &&
               x1 + w1 > x2 &&
               y1 < y2 + h2 &&
               y1 + h1 > y2;
    }

    /**
     * Checks if a circle collides with a rectangle.
     * Uses the closest point algorithm to determine collision.
     *
     * @param circleX the x-coordinate of the circle's center
     * @param circleY the y-coordinate of the circle's center
     * @param radius the radius of the circle
     * @param rectX the x-coordinate of the rectangle's top-left corner
     * @param rectY the y-coordinate of the rectangle's top-left corner
     * @param rectW the width of the rectangle
     * @param rectH the height of the rectangle
     * @return true if the circle and rectangle collide, false otherwise
     */
    public static boolean circleRectsCollide(int circleX, int circleY, int radius,
                                             int rectX, int rectY, int rectW, int rectH) {
        // Find closest point on rectangle to circle center
        int closestX = Math.max(rectX, Math.min(circleX, rectX + rectW));
        int closestY = Math.max(rectY, Math.min(circleY, rectY + rectH));

        // Calculate distance between circle center and closest point
        int distX = circleX - closestX;
        int distY = circleY - closestY;

        // Check if distance is less than radius (collision detected)
        return (distX * distX + distY * distY) < (radius * radius);
    }

    /**
     * Checks if a ball has hit the left wall.
     *
     * @param ballX the x-coordinate of the ball's center
     * @param ballRadius the radius of the ball
     * @return true if the ball hits the left wall, false otherwise
     */
    public static boolean hitsLeftWall(int ballX, int ballRadius) {
        return ballX - ballRadius <= 0;
    }

    /**
     * Checks if a ball has hit the right wall.
     *
     * @param ballX the x-coordinate of the ball's center
     * @param ballRadius the radius of the ball
     * @param canvasWidth the width of the game canvas
     * @return true if the ball hits the right wall, false otherwise
     */
    public static boolean hitsRightWall(int ballX, int ballRadius, int canvasWidth) {
        return ballX + ballRadius >= canvasWidth;
    }

    /**
     * Checks if a ball has hit the top wall.
     *
     * @param ballY the y-coordinate of the ball's center
     * @param ballRadius the radius of the ball
     * @return true if the ball hits the top wall, false otherwise
     */
    public static boolean hitsTopWall(int ballY, int ballRadius) {
        return ballY - ballRadius <= 0;
    }

    /**
     * Checks if a ball has fallen off the bottom of the screen.
     *
     * @param ballY the y-coordinate of the ball's center
     * @param ballRadius the radius of the ball
     * @param canvasHeight the height of the game canvas
     * @return true if the ball is below the canvas, false otherwise
     */
    public static boolean hitsBottomWall(int ballY, int ballRadius, int canvasHeight) {
        return ballY + ballRadius >= canvasHeight;
    }
}
