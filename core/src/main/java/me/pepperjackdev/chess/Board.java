package me.pepperjackdev.chess;

import me.pepperjackdev.chess.piece.Piece;

public class Board {
    private static final int BOARD_SIZE = 8;

    Piece[][] board;

    public Board() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
    }
}
