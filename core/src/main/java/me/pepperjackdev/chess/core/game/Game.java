package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.game.state.GameState;

public class Game {
    private final GameState gameState;

    public Game(GameState gameState) {
        this.gameState = gameState;
    }
}
