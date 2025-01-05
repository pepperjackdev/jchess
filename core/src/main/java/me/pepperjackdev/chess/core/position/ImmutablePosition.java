package me.pepperjackdev.chess.core.position;

public record ImmutablePosition(int rank, int file)
        implements Position {

    public ImmutablePosition {
        if (!Position.isValidRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!Position.isValidFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

    }
}
