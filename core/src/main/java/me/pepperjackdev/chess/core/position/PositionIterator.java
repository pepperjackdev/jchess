package me.pepperjackdev.chess.core.position;

import me.pepperjackdev.chess.core.board.Bounds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PositionIterator
    implements Iterator<Position> {

    private int row;
    private int column;
    private final Bounds bounds;

    public PositionIterator(Bounds bounds) {
        this.row = 0;
        this.column = 0;
        this.bounds = bounds;
    }

    @Override
    public boolean hasNext() {
        return (row < bounds.numberOfRows() ||
                column < bounds.numberOfColumns()) &&
                (row * bounds.numberOfRows() + column < bounds.numberOfColumns() * bounds.numberOfRows());
    }

    @Override
    public Position next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next element");
        }

        Position position = new Position(row, column);

        if (++column >= bounds.numberOfColumns()) {
            column = 0;
            row++;
        }

        return position;
    }
}
