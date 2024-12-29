package me.pepperjackdev.chess.core.position;

public class Position {
    private final int rank;
    private final int file;

    public Position(int rank, int file) {
        if (!checkInputRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!checkInputFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

        this.rank = rank;
        this.file = file;
    }

    protected static boolean checkInputRank(int rank) {
        return rank >= 0;
    }

    protected static boolean checkInputFile(int file) {
        return file >= 0;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
