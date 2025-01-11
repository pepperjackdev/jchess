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
        if (isOutOfBoundsPosition(position)) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        return Optional.ofNullable(
                piece[position.row() * numberOfColumns + position.column()]
        );
    }

    public Optional<Piece> setPiece(Position position, Piece piece) {
        if (isOutOfBoundsPosition(position)) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        Piece oldPiece = this.piece[getPieceIndex(position)];
        this.piece[getPieceIndex(position)] = piece;

        return Optional.ofNullable(oldPiece);
    }

    public Optional<Piece> pollPiece(Position position) {
        return setPiece(position, null);
    }

    private int getPieceIndex(Position position) {
        return position.row() * numberOfRows + position.column();
    }

    public boolean isOutOfBoundsPosition(Position position) {
        return position.row() < 0 ||
                position.row() >= numberOfRows ||
                position.column() < 0 ||
                position.column() >= numberOfColumns;
    }
}
