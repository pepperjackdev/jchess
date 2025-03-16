package me.pepperjackdev.chess.core.piece;

import me.pepperjackdev.chess.core.Side;

public record Piece(PieceType type, Side side) {

    public boolean isFriendOf(Piece piece) {
        return piece.side == this.side;
    }

    public boolean isOppositeOf(Piece piece) {
        return !isFriendOf(piece);
    }

    @Override
    public String toString() {
        return "%s_%s".formatted(side.toString(), type.toString());
    }
}
