package me.pepperjackdev.chess.core.board;

/**
 * A standard chessboard with 8 ranks and 8 files
 */
public class StandardBoard
    extends Board {

    private static final int STANDARD_BOARD_NUMBER_OF_RANKS = 8;
    private static final int STANDARD_BOARD_NUMBER_OF_FILES = 8;

    public StandardBoard() {
        super(STANDARD_BOARD_NUMBER_OF_RANKS, STANDARD_BOARD_NUMBER_OF_FILES);
    }
}
