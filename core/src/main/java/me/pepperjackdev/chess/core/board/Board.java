package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public class Board {
    private final int numberOfRows;
    private final int numberOfColumns;

    private final Piece[] piece;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.piece = new Piece[numberOfRows * numberOfColumns];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
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
        if (isOutOfBoundsPosition(position)) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        return position.row() * numberOfColumns + position.column();
    }

    public Position getTopLeftCornerPosition() {
        return new Position(getNumberOfRows() - 1, 0);
    }

    public Position getTopRightCornerPosition() {
        return new Position(getNumberOfRows() - 1, getNumberOfColumns() - 1);
    }

    public Position getBottomLeftCornerPosition() {
        return new Position(0, 0);
    }

    public Position getBottomRightCornerPosition() {
        return new Position(0, getNumberOfColumns() - 1);
    }

    public boolean isOutOfBoundsPosition(Position position) {
        return position.row() < 0 ||
                position.row() >= numberOfRows ||
                position.column() < 0 ||
                position.column() >= numberOfColumns;
    }
}
