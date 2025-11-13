/**
 * Represents the different states of the Arkanoid game.
 *
 * @author ArkanoidGame
 * @version 1.0
 */
public enum GameState {
    /**
     * The game is in the menu state (not yet implemented in MVP).
     */
    MENU,

    /**
     * The game is actively being played.
     */
    PLAYING,

    /**
     * The game is paused.
     */
    PAUSED,

    /**
     * The game is over (player lost all lives).
     */
    GAME_OVER,

    /**
     * The player has won (all bricks destroyed).
     */
    WIN
}
