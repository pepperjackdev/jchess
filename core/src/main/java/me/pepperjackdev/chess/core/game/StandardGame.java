package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.game.state.StandardGameState;
import me.pepperjackdev.chess.core.piece.Side;

public class StandardGame
    extends Game {

    @Override
    protected GameState loadDefaultGameState() {
        return new StandardGameState(
                new StandardBoard(),
                Side.WHITE,
                new CastlingRights(true),
                null,
                0,
                0
        );
    }
}
