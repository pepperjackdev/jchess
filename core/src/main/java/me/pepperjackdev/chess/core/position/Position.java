package me.pepperjackdev.chess.core.position;

public interface Position {

    /**
     * Returns the rank index of this position.
     * The first rank is equal to 0.
     *
     * @return the rank index of this position
     */
    int rank();

    /**
     * Returns the file index of this position.
     * The first file is equal to 0.
     *
     * @return the file index of this position
     */
    int file();

    static boolean checkInputRank(int rank) {
        return rank >= 0;
    }

    static boolean checkInputFile(int file) {
        return file >= 0;
    }
}
