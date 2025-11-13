# Arkanoid MVP Implementation Plan

## Project Overview

**Goal:** Build a minimum viable product (MVP) of Arkanoid using the `fri.shapesge` framework with strong OOP design that can be extended for higher grades.

**Grading Context:** Teacher values OOP design over performance optimization. MVP should demonstrate clean architecture, proper encapsulation, and inheritance/polymorphism patterns.

---

## Phase 1: Project Setup & Architecture (Days 1-2)

### Step 1.1: Project Structure

```
ArkanoidGame/
├── src/
│   ├── ArkanoidGame.java          // Main entry point & game manager
│   ├── Ball.java                  // Ball logic
│   ├── Paddle.java                // Paddle logic
│   ├── Brick.java                 // Individual brick
│   ├── BrickGrid.java             // Grid of bricks
│   ├── GameState.java             // Enum for game states
│   └── Collision.java             // Collision detection utility
├── resources/
│   ├── images/                    // Image assets
│   └── sounds/                    // Sound assets
├── docs/
│   ├── game/                      // Game documentation
│   └── shapesge/                  // Framework documentation
├── lib/                           // External libraries (shapesge.jar)
├── bin/                           // Compiled .class files
├── .vscode/                       // VS Code settings
├── .gitignore
└── README.md
```

### Step 1.2: Create Core Classes (UML Structure)

**Class Hierarchy:**

```
ArkanoidGame (implements game loop)
├── Ball (position, velocity, collision)
├── Paddle (player control, collision)
├── BrickGrid (collection of bricks)
│   └── Brick[] (individual bricks)
└── GameState (MENU, PLAYING, PAUSED, GAME_OVER, WIN)
```

---

## Phase 2: Core Game Objects (Days 2-3)

### Step 2.1: Ball Class

**File:** `Ball.java`

```java
public class Ball {
    // Fields
    private Circle shape;
    private double velocityX;
    private double velocityY;
    private final double speed = 3.0;
    private final int radius = 10;

    // Constructor
    public Ball(int startX, int startY) {
        shape = new Circle(radius * 2);
        shape.changePosition(startX, startY);
        shape.changeColor("white");
        velocityX = speed;
        velocityY = -speed;
    }

    // Movement
    public void update() {
        shape.moveHorizontal((int) velocityX);
        shape.moveVertical((int) velocityY);
    }

    // Getters
    public int getX() { return shape.getX(); }
    public int getY() { return shape.getY(); }
    public int getDiameter() { return shape.getDiameter(); }

    // Collision response
    public void reverseHorizontal() { velocityX *= -1; }
    public void reverseVertical() { velocityY *= -1; }

    // Visibility
    public void makeVisible() { shape.makeVisible(); }
}
```

**Responsibilities:**

- Track position and velocity
- Update position each tick
- Respond to collisions
- Provide collision bounds

### Step 2.2: Paddle Class

**File:** `Paddle.java`

```java
public class Paddle {
    // Fields
    private Rectangle shape;
    private final int speed = 8;
    private final int width = 100;
    private final int height = 20;

    // Constructor
    public Paddle(int startX, int startY, int canvasWidth) {
        shape = new Rectangle(width, height);
        shape.changePosition(startX, startY);
        shape.changeColor("green");
        this.canvasWidth = canvasWidth;
    }

    // Movement
    public void moveLeft() {
        if (shape.getX() > 0) {
            shape.moveHorizontal(-speed);
        }
    }

    public void moveRight() {
        if (shape.getX() + width < canvasWidth) {
            shape.moveHorizontal(speed);
        }
    }

    // Getters
    public int getX() { return shape.getX(); }
    public int getY() { return shape.getY(); }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    // Visibility
    public void makeVisible() { shape.makeVisible(); }
}
```

**Responsibilities:**

- Track paddle position
- Handle left/right movement
- Provide collision bounds
- Enforce canvas boundaries

### Step 2.3: Brick Class

**File:** `Brick.java`

```java
public class Brick {
    // Fields
    private Rectangle shape;
    private boolean visible;
    private final int width = 60;
    private final int height = 20;

    // Constructor
    public Brick(int x, int y) {
        shape = new Rectangle(width, height);
        shape.changePosition(x, y);
        shape.changeColor("red");
        visible = true;
    }

    // Collision & destruction
    public void destroy() {
        visible = false;
        shape.makeInvisible();
    }

    // Getters
    public int getX() { return shape.getX(); }
    public int getY() { return shape.getY(); }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isVisible() { return visible; }

    // Visibility
    public void makeVisible() {
        visible = true;
        shape.makeVisible();
    }
}
```

**Responsibilities:**

- Represent individual brick
- Track destruction state
- Provide collision bounds

### Step 2.4: BrickGrid Class

**File:** `BrickGrid.java`

```java
public class BrickGrid {
    // Fields
    private Brick[][] bricks;
    private int visibleBrickCount;

    // Constructor
    public BrickGrid(int canvasWidth, int canvasHeight) {
        final int rows = 4;
        final int cols = 8;
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

    // Brick management
    public void destroyBrick(int row, int col) {
        if (bricks[row][col].isVisible()) {
            bricks[row][col].destroy();
            visibleBrickCount--;
        }
    }

    // Getters
    public Brick getBrick(int row, int col) {
        return bricks[row][col];
    }

    public int getVisibleCount() {
        return visibleBrickCount;
    }
}
```

**Responsibilities:**

- Create and manage grid of bricks
- Track brick destruction
- Provide access to individual bricks

---

## Phase 3: Collision Detection (Days 3-4)

### Step 3.1: Collision Utility Class

**File:** `Collision.java`

```java
public class Collision {

    // Rectangle collision detection
    public static boolean rectsCollide(int x1, int y1, int w1, int h1,
                                       int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 &&
               x1 + w1 > x2 &&
               y1 < y2 + h2 &&
               y1 + h1 > y2;
    }

    // Circle-to-rectangle collision
    public static boolean circleRectsCollide(int circleX, int circleY, int radius,
                                             int rectX, int rectY, int rectW, int rectH) {
        // Find closest point on rectangle to circle center
        int closestX = Math.max(rectX, Math.min(circleX, rectX + rectW));
        int closestY = Math.max(rectY, Math.min(circleY, rectY + rectH));

        // Calculate distance between circle center and closest point
        int distX = circleX - closestX;
        int distY = circleY - closestY;

        return (distX * distX + distY * distY) < (radius * radius);
    }

    // Wall collision detection
    public static boolean hitsLeftWall(int ballX) {
        return ballX <= 0;
    }

    public static boolean hitsRightWall(int ballX, int canvasWidth) {
        return ballX >= canvasWidth;
    }

    public static boolean hitsTopWall(int ballY) {
        return ballY <= 0;
    }

    public static boolean hitsBottomWall(int ballY, int canvasHeight) {
        return ballY >= canvasHeight;
    }
}
```

**Responsibilities:**

- Provide static collision detection methods
- Support rectangle-to-rectangle and circle-to-rectangle
- Support wall collision detection

---

## Phase 4: Game State & Main Game Manager (Days 4-5)

### Step 4.1: GameState Enum

**File:** `GameState.java`

```java
public enum GameState {
    MENU,
    PLAYING,
    PAUSED,
    GAME_OVER,
    WIN
}
```

### Step 4.2: ArkanoidGame Main Class

**File:** `ArkanoidGame.java`

```java
import fri.shapesge.*;

public class ArkanoidGame {
    // Game framework
    private Manager manager;
    private GameState currentState;

    // Game objects
    private Ball ball;
    private Paddle paddle;
    private BrickGrid brickGrid;

    // UI
    private TextBlock scoreText;
    private TextBlock livesText;
    private TextBlock stateText;

    // Game state
    private int score;
    private int lives;
    private final int canvasWidth = 800;
    private final int canvasHeight = 600;

    // Constructor
    public ArkanoidGame() {
        manager = new Manager();

        // Set up canvas
        manager.setCanvasSize(canvasWidth, canvasHeight);
        manager.setCanvasBackground("black");
        manager.setTickInterval(16); // ~60 FPS

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

    private void initializeUI() {
        // Score display
        scoreText = new TextBlock("Score: 0", 20, 20);
        scoreText.changeColor("white");
        scoreText.changeFontSize(18);
        scoreText.makeVisible();

        // Lives display
        livesText = new TextBlock("Lives: 3", canvasWidth - 150, 20);
        livesText.changeColor("white");
        livesText.changeFontSize(18);
        livesText.makeVisible();

        // Game state display
        stateText = new TextBlock("PLAYING", canvasWidth / 2 - 50, canvasHeight - 20);
        stateText.changeColor("yellow");
        stateText.changeFontSize(16);
        stateText.makeVisible();
    }

    // Main game loop - called every tick
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
            } else if (ball.getY() > canvasHeight) {
                loseLife();
            }
        }
    }

    private void checkCollisions() {
        // Ball-paddle collision
        if (Collision.circleRectsCollide(ball.getX(), ball.getY(), ball.getDiameter() / 2,
                                         paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight())) {
            ball.reverseVertical();
        }

        // Ball-wall collisions
        if (Collision.hitsLeftWall(ball.getX()) || Collision.hitsRightWall(ball.getX(), canvasWidth)) {
            ball.reverseHorizontal();
        }
        if (Collision.hitsTopWall(ball.getY())) {
            ball.reverseVertical();
        }

        // Ball-brick collisions
        checkBrickCollisions();
    }

    private void checkBrickCollisions() {
        for (int row = 0; row < brickGrid.getBrickArray().length; row++) {
            for (int col = 0; col < brickGrid.getBrickArray()[row].length; col++) {
                Brick brick = brickGrid.getBrick(row, col);

                if (brick.isVisible() &&
                    Collision.circleRectsCollide(ball.getX(), ball.getY(), ball.getDiameter() / 2,
                                                 brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight())) {
                    brick.destroy();
                    ball.reverseVertical(); // Simplified - always bounce down
                    score += 10;
                    updateScoreDisplay();
                }
            }
        }
    }

    private void loseLife() {
        lives--;
        updateLivesDisplay();

        if (lives <= 0) {
            currentState = GameState.GAME_OVER;
            stateText.changeText("GAME OVER");
            stateText.changeColor("red");
        } else {
            // Reset ball position
            ball = new Ball(canvasWidth / 2, canvasHeight - 100);
            ball.makeVisible();
        }
    }

    private void updateScoreDisplay() {
        scoreText.changeText("Score: " + score);
    }

    private void updateLivesDisplay() {
        livesText.changeText("Lives: " + lives);
    }

    // Keyboard input
    public void onKeyPress(String key) {
        if (key.equals("left")) {
            paddle.moveLeft();
        } else if (key.equals("right")) {
            paddle.moveRight();
        } else if (key.equals("space")) {
            if (currentState == GameState.PLAYING) {
                currentState = GameState.PAUSED;
                stateText.changeText("PAUSED");
            } else if (currentState == GameState.PAUSED) {
                currentState = GameState.PLAYING;
                stateText.changeText("PLAYING");
            }
        } else if (key.equals("r")) {
            // Restart game
            restartGame();
        }
    }

    public void onKeyRelease(String key) {
        // Handle key releases if needed
    }

    private void restartGame() {
        currentState = GameState.PLAYING;
        score = 0;
        lives = 3;
        updateScoreDisplay();
        updateLivesDisplay();
        stateText.changeText("PLAYING");
        stateText.changeColor("yellow");
        initializeGameObjects();
    }

    // Entry point
    public static void main(String[] args) {
        new ArkanoidGame();
    }
}
```

**Responsibilities:**

- Manage game loop
- Coordinate all game objects
- Handle collisions
- Manage game state
- Update UI
- Handle keyboard input

---

## Phase 5: Testing & Refinement (Days 5-6)

### Checklist:

- ✅ Ball moves smoothly
- ✅ Paddle responds to left/right keys
- ✅ Ball bounces off walls
- ✅ Ball bounces off paddle
- ✅ Ball breaks bricks on collision
- ✅ Score increases when brick destroyed
- ✅ Lives decrease when ball falls off screen
- ✅ Game ends on win/lose condition
- ✅ Game can be restarted with 'R' key
- ✅ Pause/resume with Space key

---

## Phase 6: Code Quality & Documentation (Day 6-7)

### Deliverables:

1. **JavaDoc Comments** - Add JavaDoc to all public methods
2. **README.md** - Explain how to run the game, controls, scoring
3. **UML Diagram** - Document class structure
4. **Code Review** - Ensure OOP best practices

---

## MVP Feature List (All Required)

| Feature                 | Difficulty | Time        | Status |
| ----------------------- | ---------- | ----------- | ------ |
| Ball physics & movement | Easy       | 1 day       | ✅     |
| Paddle control          | Easy       | 0.5 day     | ✅     |
| Brick grid              | Easy       | 0.5 day     | ✅     |
| Collision detection     | Medium     | 1 day       | ✅     |
| Score tracking          | Easy       | 0.5 day     | ✅     |
| Lives system            | Easy       | 0.5 day     | ✅     |
| Game states             | Easy       | 0.5 day     | ✅     |
| UI display              | Easy       | 0.5 day     | ✅     |
| Win/lose conditions     | Medium     | 1 day       | ✅     |
| **Total MVP Time**      |            | **~6 days** |        |

---

## Post-MVP Features (For Extra Points)

### Tier 1: Easy Extensions (2-3 points each)

- [ ] Different brick colors based on position
- [ ] Sound effects (paddle hit, brick break, game over)
- [ ] Smooth paddle acceleration/deceleration
- [ ] Angle-based ball bounce on paddle (angle based on hit position)

### Tier 2: Medium Extensions (4-5 points each)

- [ ] Power-ups (multi-ball, paddle size, slow ball)
- [ ] Multiple levels
- [ ] Brick types (multi-hit bricks, special bricks)
- [ ] High score persistence (save to file)
- [ ] Menu system

### Tier 3: Advanced Extensions (5-10 points each)

- [ ] Particle effects on brick destruction
- [ ] Animated sprites
- [ ] AI-controlled paddle (demo mode)
- [ ] Level editor

---

## OOP Design Highlights (For Teacher)

**Why this design scores well on OOP:**

1. **Single Responsibility Principle**

   - `Ball` handles only ball logic
   - `Paddle` handles only paddle logic
   - `Brick` represents single brick
   - `BrickGrid` manages brick collection
   - `Collision` handles all collision math

2. **Encapsulation**

   - Each class manages its own state
   - Private fields with public getters
   - `Circle`, `Rectangle` objects wrapped inside game objects

3. **Clear Composition**

   - `ArkanoidGame` **has-a** Ball, Paddle, BrickGrid
   - `BrickGrid` **has-many** Bricks
   - Easy to visualize object relationships

4. **Easy Extension**

   - Add new brick types: create `SpecialBrick extends Brick`
   - Add power-ups: create `PowerUp` interface and implementations
   - Change collision: extend `Collision` class with new methods

5. **Testability**
   - Each class can be unit tested independently
   - Clear input/output for each method
   - No hidden dependencies

---

## Timeline

**Week 1 (Days 1-7):**

- Day 1-2: Setup + Ball/Paddle classes
- Day 2-3: Brick/BrickGrid classes
- Day 3-4: Collision detection
- Day 4-5: Main game loop + UI
- Day 5-6: Testing + refinement
- Day 6-7: Documentation + code quality

**Week 2+ (Days 8+):**

- Add Tier 1 features (choose 2-3)
- Add Tier 2 features (choose 1-2)
- Polish and optimize

---

## Key Implementation Tips

1. **Test incrementally** - Don't build everything before testing
2. **Use the framework** - Leverage Manager's tick system and keyboard handling
3. **Keep methods small** - Each method should do one thing
4. **Comment your code** - Especially complex collision logic
5. **Version control** - Commit after each phase completes
6. **Ask for feedback** - Show your teacher intermediate progress

Good luck with your Arkanoid implementation! 🎮
