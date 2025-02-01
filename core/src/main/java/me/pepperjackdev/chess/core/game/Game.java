package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Game {
    private final GameState gameState;

    public Game(GameState gameState) {
        this.gameState = gameState;
    }

    private Board getBoard() {
        return gameState.getPiecePlacementData();
    };

    public Optional<Piece> move(Move move) {
        if (isOutOfBoundsMove(move)) {
            throw new IllegalArgumentException("Move is out of bounds");
        }

        Optional<Piece> movingPiece = getBoard().getPiece(move.from());
        if (movingPiece.isEmpty()) {
            throw new IllegalArgumentException("Piece to move not found");
        }

        if (movingPiece.get().side() != gameState.getActiveColor()) {
            return Optional.empty();
        }

        Optional<Piece> attackedPiece = getBoard().getPiece(move.to());
        if (isAttackingFriend(movingPiece.get(), attackedPiece.orElse(null))) {
            throw new IllegalArgumentException("Could not attack friend piece");
        }

        getBoard().setPiece(move.to(), movingPiece.get());
        getBoard().removePiece(move.from());
        gameState.setActiveColor(gameState.getActiveColor() == Side.WHITE ? Side.BLACK : Side.WHITE);
        return attackedPiece;
    }

    protected boolean isOutOfBoundsMove(Move move) {
        return getBoard().isOutOfBoundsPosition(move.from()) ||
                getBoard().isOutOfBoundsPosition(move.to());
    }

    protected boolean isAttackingFriend(Piece moving, Piece attacked) {
        return attacked != null &&
                moving.side() == attacked.side();
    }
}
