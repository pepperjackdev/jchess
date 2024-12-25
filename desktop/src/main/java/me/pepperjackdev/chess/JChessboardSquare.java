package me.pepperjackdev.chess;

import javax.swing.*;
import java.awt.*;

public class JChessboardSquare
    extends JPanel {

    private int rank, file;

    public JChessboardSquare(int rank, int file) {
        super(new BorderLayout());
        this.rank = rank;
        this.file = file;

        // if this is an edge square, coordinates should be visible
        if (rank == 7) {
            Label info = new Label("%c".formatted((char)file + 'a'));
            info.setAlignment(Label.RIGHT);
            this.add(info, BorderLayout.SOUTH);
        }

        if (file == 0) {
            Label info = new Label("%d".formatted(rank + 1));
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
