package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public abstract class GameState {
    private final Board piecePlacementData;
    private Side activeColor;
    private final CastlingRights castlingRights;
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

    public void setActiveColor(Side activeColor) {
        this.activeColor = activeColor;
    }

    public CastlingRights getCastlingRights() {
        return this.castlingRights;
    }

    public Optional<Position> getEnPassantTargetSquare() {
        return Optional.ofNullable(this.enPassantTargetSquare);
    }

    public void setEnPassantTargetSquare(Position enPassantTargetSquare) {
        this.enPassantTargetSquare = enPassantTargetSquare;
    }

    public int getHalfMoveClock() {
        return this.halfMoveClock;
    }

    public void setHalfMoveClock(int halfMoveClock) {
        this.halfMoveClock = halfMoveClock;
    }

    public int getFullMoveCounter() {
        return this.fullMoveCounter;
    }

    public void setFullMoveCounter(int fullMoveCounter) {
        this.fullMoveCounter = fullMoveCounter;
    }
}
