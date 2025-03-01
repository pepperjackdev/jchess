package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.core.position.PositionIterator;

import java.util.Iterator;
import java.util.Optional;

public class Board
    implements Iterable<Position> {
    private final BoardSize bounds;

    private final Piece[] piece;

    public Board(BoardSize bounds) {
        this.bounds = bounds;
        this.piece = new Piece[bounds.numberOfRows() * bounds.numberOfColumns()];
    }

    public BoardSize getBounds() {
        return bounds;
    }

    public Optional<Piece> getPiece(Position position) {
        return Optional.ofNullable(
                piece[getPieceIndex(position)]
        );
    }

    public Optional<Piece> setPiece(Position position, Piece piece) {
        int index = getPieceIndex(position);
        Piece oldPiece = this.piece[index];
        this.piece[index] = piece;
        return Optional.ofNullable(oldPiece);
    }

    public Optional<Piece> removePiece(Position position) {
        return setPiece(position, null);
    }

    private int getPieceIndex(Position position) {
        // checking for an out-of-bounds position
        if (getBounds().isOutOfBounds(position)) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        return position.row() * bounds.numberOfRows() + position.column();
    }

    public Position getTopLeftCornerPosition() {
        return new Position(getBounds().numberOfRows() - 1, 0);
    }

    public Position getTopRightCornerPosition() {
        return new Position(getBounds().numberOfRows() - 1,
                getBounds().numberOfColumns() - 1);
    }

    public Position getBottomLeftCornerPosition() {
        return new Position(0, 0);
    }

    public Position getBottomRightCornerPosition() {
        return new Position(0, getBounds().numberOfColumns() - 1);
    }

    @Override
    public Iterator<Position> iterator() {
        return new PositionIterator(bounds);
    }
}
