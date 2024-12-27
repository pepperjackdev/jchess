package me.pepperjackdev.chess;

import javax.swing.*;
import java.awt.*;

public class JChessboardSquare
    extends JPanel {

    private final int rank;
    private final int file;
    private Image image;

    public JChessboardSquare(int rank, int file) {
        super(new BorderLayout());
        this.rank = rank;
        this.file = file;
        
        // the color of the text
        String nm = (rank + file) % 2 == 0 ? JChessboard.CHESSBOARD_DARK_SQUARES_COLOR : JChessboard.CHESSBOARD_LIGHT_SQUARES_COLOR;
        Font f = new Font(null, Font.BOLD, 16);

        if (rank == 7) {
            Label info = new Label("%c".formatted((char)file + 'a'));
            info.setForeground(Color.decode(nm));
            info.setFont(f);
            info.setAlignment(Label.RIGHT);
            this.add(info, BorderLayout.SOUTH);
        }

        if (file == 0) {
            Label info = new Label("%d".formatted(JChessboard.CHESSBOARD_SQUARES_PER_SIDE - (rank)));
            info.setForeground(Color.decode(nm));
            info.setFont(f);
            this.add(info, BorderLayout.NORTH);
        }
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
