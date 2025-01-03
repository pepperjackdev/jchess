package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.ImmutablePosition;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;

public class Board {
    private static final int DEFAULT_NUMBER_OF_RANKS = 8;
    private static final int DEFAULT_NUMBER_OF_FILES = 8;

    private final Piece[][] board;

    private final int numberOfRanks;
    private final int numberOfFiles;

    public Board(int numberOfRanks, int numberOfFiles) {
        this.numberOfRanks = numberOfRanks;
        this.numberOfFiles = numberOfFiles;
        board = new Piece[numberOfRanks][numberOfFiles];
    }

    public Board() {
        this(DEFAULT_NUMBER_OF_RANKS, DEFAULT_NUMBER_OF_FILES);
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

    public Optional<Piece> at(int rank, int file) {
        return at(new ImmutablePosition(rank, file));
    }

    public void placeAt(Piece piece, Position position) {
        if (isOutOfBounds(position)) {
            throw new IndexOutOfBoundsException(
                    "Invalid square: rank " + position.rank() + ", file: " + position.file());
        }
        board[position.rank()][position.file()] = piece;
    }

    public void placeAt(Piece piece, int rank, int file) {
        placeAt(piece, new ImmutablePosition(rank, file));
    }

    protected boolean isOutOfBounds(Position position) {
        return position.rank() >= numberOfRanks || position.file() >= numberOfFiles;
    }
}
