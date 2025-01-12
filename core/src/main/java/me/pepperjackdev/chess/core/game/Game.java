package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;

import java.util.Optional;

public class Game {
    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    public Optional<Piece> move(Move move) {
        Piece piece = board.removePiece(move.from())
                .orElseThrow(() -> new IllegalArgumentException("No piece at " + move.from()));

        return board.setPiece(move.to(), piece);
    }
}
