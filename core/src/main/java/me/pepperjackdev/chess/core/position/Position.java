package me.pepperjackdev.chess.core.position;

public record Position(int row, int column) {
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    public Position moved(int deltaRows, int deltaColumns) {
        return new Position(row() + deltaRows, column() + deltaColumns);
    }
}
