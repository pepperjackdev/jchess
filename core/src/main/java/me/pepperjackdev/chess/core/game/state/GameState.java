package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public class GameState {
    private final Board piecePlacementData;
    private Side activeColor;
    private final CastlingRights castlingRights;
    private Position enPassantTargetSquare;
    private int halfMoveClock;
    private int fullMoveClock;

    public GameState(final Board piecePlacementData,
                     Side activeColor,
                     final CastlingRights castlingRights,
                     Position enPassantTargetSquare,
                     int halfMoveClock,
                     int fullMoveClock) {
        this.piecePlacementData = piecePlacementData;
        this.activeColor = activeColor;
        this.castlingRights = castlingRights;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.halfMoveClock = halfMoveClock;
        this.fullMoveClock = fullMoveClock;
    }

    public GameState(final Board piecePlacementData) {
        this(piecePlacementData,
                Side.WHITE,
                new CastlingRights(true),
                null,
                0,
                0);
    }

    public Board getPiecePlacementData() {
        return piecePlacementData;
    }

    public Side getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(Side activeColor) {
        this.activeColor = activeColor;
    }

    public void updateActiveColor() {
        setActiveColor(activeColor == Side.WHITE ? Side.BLACK : Side.WHITE);
    }

    public CastlingRights getCastlingRights() {
        return castlingRights;
    }

    public Optional<Position> getEnPassantTargetSquare() {
        return Optional.ofNullable(enPassantTargetSquare);
    }

    public void setEnPassantTargetSquare(Position enPassantTargetSquare) {
        this.enPassantTargetSquare = enPassantTargetSquare;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public void setHalfMoveClock(int halfMoveClock) {
        this.halfMoveClock = halfMoveClock;
    }

    public void incrementHalfMoveClock() {
        halfMoveClock++;
    }

    public int getFullMoveClock() {
        return fullMoveClock;
    }

    public void setFullMoveClock(int fullMoveClock) {
        this.fullMoveClock = fullMoveClock;
    }

    public void incrementFullMoveClock() {
        fullMoveClock++;
    }
}
