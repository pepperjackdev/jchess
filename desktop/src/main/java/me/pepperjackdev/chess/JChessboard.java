package me.pepperjackdev.chess;

import javax.swing.*;
import java.awt.*;

public class JChessboard
    extends JPanel {

    public static final int CHESSBOARD_SQUARES_PER_SIDE = 8;

    public static final String CHESSBOARD_DARK_SQUARES_COLOR = "#99B746";
    public static final String CHESSBOARD_LIGHT_SQUARES_COLOR = "#CEDECA";

    public JChessboard() {
        setLayout(new GridLayout(CHESSBOARD_SQUARES_PER_SIDE, CHESSBOARD_SQUARES_PER_SIDE));
        initializeBoard();
    }

    private void initializeBoard() {
        for (int rank = 0; rank < CHESSBOARD_SQUARES_PER_SIDE; rank++) {
            for (int file = 0; file < CHESSBOARD_SQUARES_PER_SIDE; file++) {
                JChessboardSquare square = new JChessboardSquare(rank, file);
                square.setBackground((rank + file) % 2 == 0 ?
                        Color.decode(CHESSBOARD_LIGHT_SQUARES_COLOR) : Color.decode(CHESSBOARD_DARK_SQUARES_COLOR));
                add(square);
            }
        }
    }
}
