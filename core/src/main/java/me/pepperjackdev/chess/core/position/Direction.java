package me.pepperjackdev.chess.core.position;

public enum Direction {
    NORTH(1, 0),
    NORTH_NORTH_EAST(2, 1),
    NORTH_EAST(1, 1),
    EAST_NORTH_EAST(1, 2),
    EAST(0, 1),
    EAST_SOUTH_EST(-1, 2),
    SOUTH_EAST(-1, 1),
    SOUTH_SOUTH_EAST(-2, 1),
    SOUTH(-1, 0),
    SOUTH_SOUTH_WEST(-2, -1),
    SOUTH_WEST(-1, -1),
    WEST_SOUTH_WEST(-1, -2),
    WEST(0, -1),
    WEST_NORTH_WEST(1, -2),
    NORTH_WEST(1, -1),
    NORTH_NORTH_WEST(2, -1);

    private final int deltaRow;
    private final int deltaColumn;

    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaColumn() {
        return deltaColumn;
    }
}
