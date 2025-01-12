package me.pepperjackdev.chess.core.piece;

import me.pepperjackdev.chess.core.Side;

public record Piece(PieceType type, Side side) {
    @Override
    public String toString() {
        return "%s_%s".formatted(side.toString(), type.toString());
    }
}
