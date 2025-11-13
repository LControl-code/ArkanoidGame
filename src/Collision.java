/**
 * Utility class for collision detection in the Arkanoid game.
 * Provides static methods for detecting collisions between various shapes and walls.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class Collision {

    /**
     * Represents the result of a swept collision detection.
     * Contains information about where and when a collision occurred.
     */
    public static class SweptCollisionResult {
        public final boolean collided;
        public final double collisionTime; // 0.0 to 1.0 along the path
        public final int collisionX;       // Exact collision point
        public final int collisionY;
        public final CollisionEdge edge;   // Which edge was hit

        public SweptCollisionResult(boolean collided, double time, int x, int y, CollisionEdge edge) {
            this.collided = collided;
            this.collisionTime = time;
            this.collisionX = x;
            this.collisionY = y;
            this.edge = edge;
        }

        public static SweptCollisionResult noCollision() {
            return new SweptCollisionResult(false, 1.0, 0, 0, CollisionEdge.NONE);
        }
    }

    /**
     * Represents which edge of a rectangle was hit during collision.
     */
    public enum CollisionEdge {
        NONE, TOP, BOTTOM, LEFT, RIGHT
    }

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

    /**
     * Performs swept collision detection between a moving circle and a rectangle.
     * This method traces the path of the circle from its previous position to its current position
     * and determines if and when it collides with the rectangle.
     *
     * Algorithm:
     * 1. Expand the rectangle by the circle's radius in all directions
     * 2. Treat the circle as a point moving along a line segment
     * 3. Test if the line segment intersects the expanded rectangle
     * 4. Find which edge is hit first and at what time (0.0 to 1.0 along the path)
     *
     * @param prevX the previous x-coordinate of the circle's center
     * @param prevY the previous y-coordinate of the circle's center
     * @param currX the current x-coordinate of the circle's center
     * @param currY the current y-coordinate of the circle's center
     * @param radius the radius of the circle
     * @param rectX the x-coordinate of the rectangle's top-left corner
     * @param rectY the y-coordinate of the rectangle's top-left corner
     * @param rectW the width of the rectangle
     * @param rectH the height of the rectangle
     * @return SweptCollisionResult containing collision information
     */
    public static SweptCollisionResult sweptCircleRect(int prevX, int prevY, int currX, int currY, int radius,
                                                       int rectX, int rectY, int rectW, int rectH) {
        // Calculate movement delta
        double deltaX = currX - prevX;
        double deltaY = currY - prevY;

        // If no movement, use standard collision detection
        if (deltaX == 0 && deltaY == 0) {
            boolean collides = circleRectsCollide(currX, currY, radius, rectX, rectY, rectW, rectH);
            return collides ? new SweptCollisionResult(true, 0.0, currX, currY, CollisionEdge.NONE) :
                            SweptCollisionResult.noCollision();
        }

        // Expand the rectangle by the circle's radius (Minkowski sum)
        int expandedX = rectX - radius;
        int expandedY = rectY - radius;
        int expandedW = rectW + radius * 2;
        int expandedH = rectH + radius * 2;

        // Test ray (line segment) against expanded rectangle
        // We need to find the time of intersection for each edge

        double entryTimeX, exitTimeX;
        double entryTimeY, exitTimeY;

        // Calculate intersection times with vertical edges (left and right)
        if (deltaX > 0) {
            // Moving right: check left edge entry, right edge exit
            entryTimeX = (expandedX - prevX) / deltaX;
            exitTimeX = (expandedX + expandedW - prevX) / deltaX;
        } else if (deltaX < 0) {
            // Moving left: check right edge entry, left edge exit
            entryTimeX = (expandedX + expandedW - prevX) / deltaX;
            exitTimeX = (expandedX - prevX) / deltaX;
        } else {
            // No horizontal movement
            entryTimeX = Double.NEGATIVE_INFINITY;
            exitTimeX = Double.POSITIVE_INFINITY;
        }

        // Calculate intersection times with horizontal edges (top and bottom)
        if (deltaY > 0) {
            // Moving down: check top edge entry, bottom edge exit
            entryTimeY = (expandedY - prevY) / deltaY;
            exitTimeY = (expandedY + expandedH - prevY) / deltaY;
        } else if (deltaY < 0) {
            // Moving up: check bottom edge entry, top edge exit
            entryTimeY = (expandedY + expandedH - prevY) / deltaY;
            exitTimeY = (expandedY - prevY) / deltaY;
        } else {
            // No vertical movement
            entryTimeY = Double.NEGATIVE_INFINITY;
            exitTimeY = Double.POSITIVE_INFINITY;
        }

        // Find the latest entry time and earliest exit time
        double entryTime = Math.max(entryTimeX, entryTimeY);
        double exitTime = Math.min(exitTimeX, exitTimeY);

        // Check if there's a collision
        // No collision if:
        // - Entry time is after exit time (never overlapped)
        // - Collision is completely behind us (exitTime < 0)
        // - Collision is completely ahead of our movement (entryTime > 1)
        if (entryTime > exitTime || exitTime < 0 || entryTime > 1) {
            return SweptCollisionResult.noCollision();
        }

        // Clamp entry time to [0, 1] range
        entryTime = Math.max(0, entryTime);

        // Determine which edge was hit
        CollisionEdge edge;
        if (entryTimeX > entryTimeY) {
            // Hit vertical edge (left or right)
            if (deltaX > 0) {
                edge = CollisionEdge.LEFT; // Hit left edge of rectangle
            } else {
                edge = CollisionEdge.RIGHT; // Hit right edge of rectangle
            }
        } else {
            // Hit horizontal edge (top or bottom)
            if (deltaY > 0) {
                edge = CollisionEdge.TOP; // Hit top edge of rectangle
            } else {
                edge = CollisionEdge.BOTTOM; // Hit bottom edge of rectangle
            }
        }

        // Calculate exact collision point
        int collisionX = (int)(prevX + deltaX * entryTime);
        int collisionY = (int)(prevY + deltaY * entryTime);

        return new SweptCollisionResult(true, entryTime, collisionX, collisionY, edge);
    }
}
