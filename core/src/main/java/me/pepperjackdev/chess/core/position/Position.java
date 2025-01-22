package me.pepperjackdev.chess.core.position;

public record Position(int row, int column) {
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    public Position moved(int deltaRow, int deltaColumn) {
        return new Position(row + deltaRow, column + deltaColumn);
    }
}
