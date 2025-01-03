package me.pepperjackdev.chess.core.position;

public record ImmutablePosition(int rank, int file)
        implements Position {

    public ImmutablePosition {
        if (!Position.checkInputRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!Position.checkInputFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

    }
}
