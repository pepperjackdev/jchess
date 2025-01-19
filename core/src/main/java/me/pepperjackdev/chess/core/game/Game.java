package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;

import java.util.List;

public class Game {
    private final GameState gameState;

    public Game(GameState gameState) {
        this.gameState = gameState;
    }

    public void move(Move move) {
        Piece piece = gameState.getPiecePlacementData().removePiece(move.from())
                .orElseThrow(() -> new IllegalArgumentException("No piece at " + move.from()));

        if (isLegalMove(move)) {
            gameState.getPiecePlacementData().setPiece(move.to(), piece);
        }
    }

    private boolean isLegalMove(Move move) {
        return getLegalMoves().contains(move);
    }

    public List<Move> getPseudoLegalMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // TODO: implement tighter checks
    public List<Move> getLegalMoves() {
        // this method isn't supposed to work like this!
        return getPseudoLegalMoves();
    }
}
