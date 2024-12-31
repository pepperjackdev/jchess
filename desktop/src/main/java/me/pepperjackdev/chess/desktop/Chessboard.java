package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;

public class Chessboard
    extends JPanel {

    private final Board board;

    public Chessboard(Board board) {
        super(new GridLayout(board.getNumberOfRanks(), board.getNumberOfFiles()));
        this.board = board;
        initializeSquares();
    }

    public void initializeSquares() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                add(new Square(new Position(rank, file)));
            }
        }
    }
}
