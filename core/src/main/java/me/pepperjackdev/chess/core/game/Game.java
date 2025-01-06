package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.game.state.GameState;

/**
 * Represents a single Chess match
 */
public abstract class Game {

    private final GameState state;

    public Game(GameState state) {
        this.state = state;
    }

    public Game() {
        this.state = loadDefaultGameState();
    }

    protected abstract GameState loadDefaultGameState();
}
