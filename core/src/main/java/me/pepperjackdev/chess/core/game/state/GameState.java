package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.Position;

public class GameState {
    private final Board piecePlacementData;
    private Side activeColor;
    private CastlingRights castlingRights;
    private Position enPassantTargetSquare;
    private int halfMoveClock;
    private int fullMoveCounter;

    public GameState(Board piecePlacementData,
                     Side activeColor,
                     CastlingRights castlingRights,
                     Position enPassantTargetSquare,
                     int halfMoveClock,
                     int fullMoveCounter) {
        this.piecePlacementData = piecePlacementData;
        this.activeColor = activeColor;
        this.castlingRights = castlingRights;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.halfMoveClock = halfMoveClock;
        this.fullMoveCounter = fullMoveCounter;
    }

    public Board getPiecePlacementData() {
        return this.piecePlacementData;
    }

    public Side getActiveColor() {
        return this.activeColor;
    }

    public CastlingRights getCastlingRights() {
        return this.castlingRights;
    }

    public Position getEnPassantTargetSquare() {
        return this.enPassantTargetSquare;
    }

    public int getHalfMoveClock() {
        return this.halfMoveClock;
    }

    public int getFullMoveCounter() {
        return this.fullMoveCounter;
    }
}
