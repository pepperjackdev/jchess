package me.pepperjackdev.chess;

import javax.swing.*;
import java.awt.*;

public class JChessboardPane
    extends JPanel {

    public JChessboardPane() {
        setLayout(new GridLayout(8, 8));
        initializeBoard();
    }

    private void initializeBoard() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                JChessboardSquare square = new JChessboardSquare(rank, file);
                square.setBackground((rank + file) % 2 == 0 ? Color.BLACK : Color.WHITE);
                add(square);
            }
        }
    }
}
