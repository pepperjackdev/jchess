package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.game.business.moving.MoveHandler;
import me.pepperjackdev.chess.core.game.business.moving.MoveResult;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.move.Move;

public class Game {
    private final GameState gameState;

    private final MoveHandler moveHandler;

    public Game(GameState gameState) {
        this.gameState = gameState;
        this.moveHandler = new MoveHandler(gameState.getPiecePlacementData());
    }

    private MoveHandler getMoveHandler() {
        if (moveHandler == null) {
            throw new IllegalStateException("Move handler not set");
        }

        return moveHandler;
    }

    public void move(Move move) {
        gameState.getPiecePlacementData().getPiece(move.from()).ifPresent(piece -> {
            if (piece.side() == gameState.getActiveColor()) {
                if (moveHandler.move(move) == MoveResult.OK) {
                    updateActiveColor();
                };
            }
        });
    }

    private void updateActiveColor() {
        gameState.setActiveColor(
                (gameState.getActiveColor() == Side.WHITE) ? Side.BLACK : Side.WHITE
        );
    }
}
