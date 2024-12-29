package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;

import javax.swing.*;
import java.awt.*;

public class JChessboard
    extends JPanel {

    private final Board board;
    private final JChessboardSquare[][] squares;

    public JChessboard(Board board) {
        this.board = board;
        this.squares = new JChessboardSquare[getNumberOfRanks()][getNumberOfFiles()];
        setLayout(new GridLayout(getNumberOfRanks(), getNumberOfFiles()));
        initializeChessboardSquares();
        renderChessboardSquares();
    }

    public int getNumberOfRanks() {
        return board.getNumberOfRanks();
    }

    public int getNumberOfFiles() {
        return board.getNumberOfFiles();
    }

    private void initializeChessboardSquares() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                squares[rank][file] = new JChessboardSquare(rank, file);
            }
        }
    }

    private void renderChessboardSquares() {
        for (JChessboardSquare[] files: squares) {
            for (JChessboardSquare square: files) {
                add(square);
            }
        }
    }
}
