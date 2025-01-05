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

    /**
     * Checks if the given rank is valid or not.
     * Valid ranks are numbers equal or greater than 0.
     *
     * @param rank a number representing a rank index on the chessboard
     *
     * @return true if rank is equal or greater than 0; false otherwise
     */
    static boolean isValidRank(int rank) {
        return rank >= 0;
    }

    /**
     * Checks if the given file is valid or not.
     * Valid files are numbers equal or greater than 0.
     *
     * @param file a number representing a file index on the chessboard
     *
     * @return true if file is equal or greater than 0; false otherwise
     */
    static boolean isValidFile(int file) {
        return file >= 0;
    }
}
