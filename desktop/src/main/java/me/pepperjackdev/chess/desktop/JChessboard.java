package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;

import javax.swing.*;
import java.awt.*;

public class JChessboard
    extends JPanel {

    private final Board board;

    public static final String CHESSBOARD_DARK_SQUARES_COLOR = "#99B746";
    public static final String CHESSBOARD_LIGHT_SQUARES_COLOR = "#CEDECA";

    public JChessboard(Board board) {
        this.board = board;

        setLayout(new GridLayout(getNumberOfRanks(), getNumberOfFiles()));
        initializeBoard();
    }

    public int getNumberOfRanks() {
        return board.getNumberOfRanks();
    }

    public int getNumberOfFiles() {
        return board.getNumberOfFiles();
    }

    private void initializeBoard() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                JChessboardSquare square = new JChessboardSquare(rank, file, getNumberOfRanks(), getNumberOfFiles());
                square.setBackground((rank + file) % 2 == 0 ?
                        Color.decode(CHESSBOARD_LIGHT_SQUARES_COLOR) : Color.decode(CHESSBOARD_DARK_SQUARES_COLOR));
                add(square);
            }
        }
    }
}
