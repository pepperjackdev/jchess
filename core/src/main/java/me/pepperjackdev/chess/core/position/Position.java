package me.pepperjackdev.chess.core.position;

import java.util.Objects;

public record Position(int row, int column) {

    public Position moved(Direction direction) {
        return moved(direction.getDeltaRow(), direction.getDeltaColumn());
    }

    public Position moved(int deltaRow, int deltaColumn) {
        return new Position(row + deltaRow, column + deltaColumn);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
