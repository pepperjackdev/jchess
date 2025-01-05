package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public class GameState {
    private final Board piecePlacementData;
    private Side activeColor;
    private CastlingRights castlingRights;
    private Optional<Position> enPassantTargetSquare;
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
        this.enPassantTargetSquare = Optional.of(enPassantTargetSquare);
        this.halfMoveClock = halfMoveClock;
        this.fullMoveCounter = fullMoveCounter;
    }

    public GameState(Board piecePlacementData,
                     Side activeColor,
                     CastlingRights castlingRights,
                     int halfMoveClock,
                     int fullMoveCounter) {
        this.piecePlacementData = piecePlacementData;
        this.activeColor = activeColor;
        this.castlingRights = castlingRights;
        this.enPassantTargetSquare = Optional.empty();
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

    public Optional<Position> getEnPassantTargetSquare() {
        return this.enPassantTargetSquare;
    }

    public int getHalfMoveClock() {
        return this.halfMoveClock;
    }

    public int getFullMoveCounter() {
        return this.fullMoveCounter;
    }
}
