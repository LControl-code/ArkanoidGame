/**
 * Represents a grid of bricks in the Arkanoid game.
 * Manages the creation, destruction, and tracking of all bricks.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class BrickGrid {
    // Fields
    private Brick[][] bricks;
    private int visibleBrickCount;
    private final int rows = 4;
    private final int cols = 8;

    /**
     * Creates a new BrickGrid with the specified canvas dimensions.
     * The grid is automatically populated with bricks.
     *
     * @param canvasWidth the width of the game canvas
     * @param canvasHeight the height of the game canvas
     */
    public BrickGrid(int canvasWidth, int canvasHeight) {
        final int brickWidth = 60;
        final int brickHeight = 20;
        final int spacingX = 10;
        final int spacingY = 10;
        final int startY = 50;

        bricks = new Brick[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * (brickWidth + spacingX) + spacingX;
                int y = startY + row * (brickHeight + spacingY);

                bricks[row][col] = new Brick(x, y);
                bricks[row][col].makeVisible();
            }
        }

        visibleBrickCount = rows * cols;
    }

    /**
     * Destroys the brick at the specified row and column.
     * Decrements the visible brick count if the brick was visible.
     *
     * @param row the row index of the brick to destroy
     * @param col the column index of the brick to destroy
     */
    public void destroyBrick(int row, int col) {
        if (bricks[row][col].isVisible()) {
            bricks[row][col].destroy();
            visibleBrickCount--;
        }
    }

    /**
     * Gets the brick at the specified row and column.
     *
     * @param row the row index of the brick
     * @param col the column index of the brick
     * @return the brick at the specified position
     */
    public Brick getBrick(int row, int col) {
        return bricks[row][col];
    }

    /**
     * Gets the number of visible bricks remaining in the grid.
     *
     * @return the count of visible bricks
     */
    public int getVisibleCount() {
        return visibleBrickCount;
    }

    /**
     * Gets the 2D array of bricks.
     *
     * @return the brick array
     */
    public Brick[][] getBrickArray() {
        return bricks;
    }

    /**
     * Gets the number of rows in the brick grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the brick grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }
}
