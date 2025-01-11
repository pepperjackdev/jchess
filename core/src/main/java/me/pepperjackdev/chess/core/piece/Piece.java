package me.pepperjackdev.chess.core.piece;

public class Piece {
    private final PieceType type;
    private final PieceSide side;

    public Piece(PieceType type, PieceSide side) {
        this.type = type;
        this.side = side;
    }

    public PieceType getType() {
        return type;
    }

    public PieceSide getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "%s_%s".formatted(side.toString(), type.toString());
    }
}
