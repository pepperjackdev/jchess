package me.pepperjackdev.chess.core;

import me.pepperjackdev.chess.core.piece.Piece;
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
                    "Invalid square: rank " + position.getRank() + ", file: " + position.getFile());
        }
        return Optional.ofNullable(board[getRankIndex(position)][getFileIndex(position)]);
    }

    public Optional<Piece> placeAt(Piece piece, Position position) {
        if (isOutOfBounds(position)) {
            throw new IndexOutOfBoundsException(
                    "Invalid square: rank " + position.getRank() + ", file: " + position.getFile());
        }
        return Optional.of(board[getRankIndex(position)][getFileIndex(position)] = piece);
    }

    private int getRankIndex(Position position) {
        return position.getRank() - 1;
    }

    private int getFileIndex(Position position) {
        return position.getFile() - 1;
    }

    private boolean isOutOfBounds(Position position) {
        return position.getRank() < 1 || position.getRank() > numberOfRanks ||
                position.getFile() < 1 || position.getFile() > numberOfFiles;
    }
}
