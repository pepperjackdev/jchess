package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.Board;

/**
 * Represents a single Chess match
 */
public abstract class Game {

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }
}
