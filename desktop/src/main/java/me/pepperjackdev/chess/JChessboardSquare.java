package me.pepperjackdev.chess;

import javax.swing.*;

public class JChessboardSquare
    extends JPanel {

    private int rank, file;

    public JChessboardSquare(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
