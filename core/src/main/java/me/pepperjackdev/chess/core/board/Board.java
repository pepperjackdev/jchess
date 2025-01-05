package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.ImmutablePosition;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public class Board {
    private final Piece[][] board;

    private final int numberOfRanks;
    private final int numberOfFiles;

    protected Board(int numberOfRanks, int numberOfFiles) {
        this.numberOfRanks = numberOfRanks;
        this.numberOfFiles = numberOfFiles;
        board = new Piece[numberOfRanks][numberOfFiles];
    }

    public int getNumberOfRanks() {
        return numberOfRanks;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public Optional<Piece> at(Position position) {
        if (isOutOfBounds(position)) {
            throw new IndexOutOfBoundsException(
                    "Invalid square: rank " + position.rank() + ", file: " + position.file());
        }
        return Optional.ofNullable(board[position.rank()][position.file()]);
    }

    public void placeAt(Piece piece, Position position) {
        if (isOutOfBounds(position)) {
            throw new IndexOutOfBoundsException(
                    "Invalid square: rank " + position.rank() + ", file: " + position.file());
        }
        board[position.rank()][position.file()] = piece;
    }

    public Optional<Piece> at(int rank, int file) {
        return at(new ImmutablePosition(rank, file));
    }

    public void placeAt(Piece piece, int rank, int file) {
        placeAt(piece, new ImmutablePosition(rank, file));
    }

    protected boolean isOutOfBounds(Position position) {
        return position.rank() < 0 || position.rank() >= numberOfRanks ||
                position.file() < 0 || position.file() >= numberOfFiles;
    }

    public Position getBottomLeftPosition() {
        return new ImmutablePosition(0, 0);
    }

    public Position getTopLeftPosition() {
        return new ImmutablePosition(0, numberOfFiles - 1);
    }

    public Position getTopRightPosition() {
        return new ImmutablePosition(numberOfRanks - 1, numberOfFiles - 1);
    }

    public Position getBottomRightPosition() {
        return new ImmutablePosition(numberOfRanks - 1, 0);
    }
}
