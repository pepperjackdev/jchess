package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.position.Position;

public record Bounds(int numberOfRows, int numberOfColumns) {
    public boolean isOutOfBounds(Position position) {
        return isRowOutOfBounds(position.row()) || isColumnOutOfBounds(position.column());
    }

    public boolean isRowOutOfBounds(int row) {
        return row < 0 ||
                row >= numberOfRows;
    }

    public boolean isColumnOutOfBounds(int column) {
        return column < 0 ||
                column >= numberOfColumns;
    }
}
