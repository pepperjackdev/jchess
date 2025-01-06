package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.Position;

public class StandardGameState
    extends GameState {

    public StandardGameState(
            StandardBoard piecePlacementData,
            Side activeColor,
            CastlingRights castlingRights,
            Position enPassantTargetSquare,
            int halfMoveClock,
            int fullMoveCounter) {
        super(
                piecePlacementData,
                activeColor,
                castlingRights,
                enPassantTargetSquare,
                halfMoveClock,
                fullMoveCounter
        );
    }
}
