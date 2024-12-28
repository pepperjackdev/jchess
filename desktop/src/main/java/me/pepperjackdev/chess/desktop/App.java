package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;

import javax.swing.*;

public class App
    extends JFrame {

    public App(String title) {
        super(title);
    }

    public static void main(String[] args) {
        // Application frame
        App app = new App("JChess");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(800, 800);
        app.setLocationRelativeTo(null);

        // Chessboard
        JChessboard chessboardPane = new JChessboard(new Board(16, 16));
        chessboardPane.setSize(800, 800);
        app.add(chessboardPane);

        // Showing App
        app.setVisible(true);
    }
}