package me.pepperjackdev.chess.core.piece;

public class Piece {
    private PieceType type;
    private final PieceSide side;

    public Piece(PieceType type, PieceSide side) {
        this.type = type;
        this.side = side;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public PieceSide getSide() {
        return side;
    }
}