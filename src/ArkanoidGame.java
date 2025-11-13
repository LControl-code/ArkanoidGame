import fri.shapesge.*;

/**
 * Main game class for Arkanoid.
 * Manages the game loop, game objects, collision detection, and user input.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public class ArkanoidGame {
    // Game framework
    private Manager manager;
    private GameState currentState;

    // Game objects
    private Ball ball;
    private Paddle paddle;
    private BrickGrid brickGrid;

    // UI elements
    private TextBlock scoreText;
    private TextBlock livesText;
    private TextBlock stateText;

    // Game state variables
    private int score;
    private int lives;
    private final int canvasWidth = 800;
    private final int canvasHeight = 600;

    /**
     * Creates and initializes a new Arkanoid game.
     * Sets up the canvas, creates game objects, and starts the game loop.
     */
    public ArkanoidGame() {
        manager = new Manager();

        // Note: Canvas size, background, and tick interval are configured in sbge.ini

        // Initialize game objects
        initializeGameObjects();

        // Initialize UI
        initializeUI();

        // Set initial state
        currentState = GameState.PLAYING;
        score = 0;
        lives = 3;

        // Start managing this game
        manager.manageObject(this);
    }

    /**
     * Initializes all game objects (ball, paddle, brick grid).
     */
    private void initializeGameObjects() {
        // Ball starts at center
        ball = new Ball(canvasWidth / 2, canvasHeight - 100);
        ball.makeVisible();

        // Paddle starts at bottom center
        paddle = new Paddle(canvasWidth / 2 - 50, canvasHeight - 40, canvasWidth);
        paddle.makeVisible();

        // Create brick grid
        brickGrid = new BrickGrid(canvasWidth, canvasHeight);
    }

    /**
     * Initializes all UI elements (score, lives, state display).
     */
    private void initializeUI() {
        // Score display
        scoreText = new TextBlock("Score: 0", 20, 20);
        scoreText.changeColor("white");
        scoreText.changeFont("Arial", FontStyle.PLAIN, 18);
        scoreText.makeVisible();

        // Lives display
        livesText = new TextBlock("Lives: 3", canvasWidth - 150, 20);
        livesText.changeColor("white");
        livesText.changeFont("Arial", FontStyle.PLAIN, 18);
        livesText.makeVisible();

        // Game state display
        stateText = new TextBlock("PLAYING", canvasWidth / 2 - 50, canvasHeight - 20);
        stateText.changeColor("yellow");
        stateText.changeFont("Arial", FontStyle.BOLD, 16);
        stateText.makeVisible();
    }

    /**
     * Main game loop - called every tick (~60 times per second).
     * Updates game objects and checks for collisions and win/lose conditions.
     */
    public void tick() {
        if (currentState == GameState.PLAYING) {
            // Update ball position
            ball.update();

            // Check collisions
            checkCollisions();

            // Check win/lose conditions
            if (brickGrid.getVisibleCount() == 0) {
                currentState = GameState.WIN;
                stateText.changeText("YOU WIN!");
                stateText.changeColor("green");
            } else if (Collision.hitsBottomWall(ball.getY(), ball.getRadius(), canvasHeight)) {
                loseLife();
            }
        }
    }

    /**
     * Checks for all collisions (ball-paddle, ball-wall, ball-brick).
     */
    private void checkCollisions() {
        // Ball-paddle collision using swept collision detection
        Collision.SweptCollisionResult paddleCollision = Collision.sweptCircleRect(
            ball.getPreviousX(), ball.getPreviousY(),
            ball.getX(), ball.getY(),
            ball.getRadius(),
            paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight()
        );

        if (paddleCollision.collided) {
            // Position ball at collision point
            ball.setPosition(paddleCollision.collisionX, paddleCollision.collisionY);

            // Bounce based on which edge was hit
            if (paddleCollision.edge == Collision.CollisionEdge.TOP ||
                paddleCollision.edge == Collision.CollisionEdge.BOTTOM) {
                ball.reverseVertical();
            } else if (paddleCollision.edge == Collision.CollisionEdge.LEFT ||
                       paddleCollision.edge == Collision.CollisionEdge.RIGHT) {
                ball.reverseHorizontal();
            }
        }

        // Ball-wall collisions
        if (Collision.hitsLeftWall(ball.getX(), ball.getRadius()) ||
            Collision.hitsRightWall(ball.getX(), ball.getRadius(), canvasWidth)) {
            ball.reverseHorizontal();
        }
        if (Collision.hitsTopWall(ball.getY(), ball.getRadius())) {
            ball.reverseVertical();
        }

        // Ball-brick collisions
        checkBrickCollisions();
    }

    /**
     * Checks for collisions between the ball and all visible bricks.
     * Destroys bricks on collision and updates the score.
     * Uses swept collision detection to determine exact collision point and which edge was hit.
     */
    private void checkBrickCollisions() {
        Collision.SweptCollisionResult earliestCollision = null;
        Brick hitBrick = null;

        // Find the earliest collision among all bricks
        for (int row = 0; row < brickGrid.getRows(); row++) {
            for (int col = 0; col < brickGrid.getCols(); col++) {
                Brick brick = brickGrid.getBrick(row, col);

                if (brick.isVisible()) {
                    Collision.SweptCollisionResult collision = Collision.sweptCircleRect(
                        ball.getPreviousX(), ball.getPreviousY(),
                        ball.getX(), ball.getY(),
                        ball.getRadius(),
                        brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()
                    );

                    // Keep track of the earliest collision
                    if (collision.collided) {
                        if (earliestCollision == null || collision.collisionTime < earliestCollision.collisionTime) {
                            earliestCollision = collision;
                            hitBrick = brick;
                        }
                    }
                }
            }
        }

        // Handle the earliest collision
        if (earliestCollision != null && hitBrick != null) {
            // Position ball at collision point
            ball.setPosition(earliestCollision.collisionX, earliestCollision.collisionY);

            // Bounce based on which edge was hit
            if (earliestCollision.edge == Collision.CollisionEdge.TOP ||
                earliestCollision.edge == Collision.CollisionEdge.BOTTOM) {
                ball.reverseVertical();
            } else if (earliestCollision.edge == Collision.CollisionEdge.LEFT ||
                       earliestCollision.edge == Collision.CollisionEdge.RIGHT) {
                ball.reverseHorizontal();
            }

            // Destroy the brick and update score
            hitBrick.destroy();
            score += 10;
            updateScoreDisplay();
        }
    }

    /**
     * Handles losing a life when the ball falls off the screen.
     * Resets the ball or ends the game if no lives remain.
     */
    private void loseLife() {
        lives--;
        updateLivesDisplay();

        if (lives <= 0) {
            currentState = GameState.GAME_OVER;
            stateText.changeText("GAME OVER");
            stateText.changeColor("red");
            ball.makeInvisible();
        } else {
            // Reset ball position
            ball.makeInvisible();
            ball = new Ball(canvasWidth / 2, canvasHeight - 100);
            ball.makeVisible();
        }
    }

    /**
     * Updates the score display with the current score.
     */
    private void updateScoreDisplay() {
        scoreText.changeText("Score: " + score);
    }

    /**
     * Updates the lives display with the current number of lives.
     */
    private void updateLivesDisplay() {
        livesText.changeText("Lives: " + lives);
    }

    /**
     * Moves the paddle to the left.
     * Called automatically by the framework when LEFT arrow is pressed.
     * Mapped in sbge.ini: moveLeft = pressed LEFT
     */
    public void moveLeft() {
        paddle.moveLeft();
    }

    /**
     * Moves the paddle to the right.
     * Called automatically by the framework when RIGHT arrow is pressed.
     * Mapped in sbge.ini: moveRight = pressed RIGHT
     */
    public void moveRight() {
        paddle.moveRight();
    }

    /**
     * Toggles pause state of the game.
     * Called automatically by the framework when SPACE is pressed.
     * Mapped in sbge.ini: pause = pressed SPACE
     */
    public void pause() {
        if (currentState == GameState.PLAYING) {
            currentState = GameState.PAUSED;
            stateText.changeText("PAUSED");
        } else if (currentState == GameState.PAUSED) {
            currentState = GameState.PLAYING;
            stateText.changeText("PLAYING");
        }
    }

    /**
     * Restarts the game by resetting all game objects and state.
     * Called automatically by the framework when R key is pressed.
     * Mapped in sbge.ini: restart = pressed R
     */
    public void restart() {
        // Remove old objects
        ball.makeInvisible();
        paddle.makeInvisible();

        // Reset game state
        currentState = GameState.PLAYING;
        score = 0;
        lives = 3;
        updateScoreDisplay();
        updateLivesDisplay();
        stateText.changeText("PLAYING");
        stateText.changeColor("yellow");

        // Reinitialize game objects
        initializeGameObjects();
    }

    /**
     * Entry point for the Arkanoid game.
     * Creates a new game instance and starts the game loop.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new ArkanoidGame();
    }
}
