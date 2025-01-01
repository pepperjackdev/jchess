package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceSide;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;

public class App
    extends JFrame {

    private static final int DEFAULT_START_WIDTH = 800;
    private static final int DEFAULT_START_HEIGHT = 800;

    public App(String title, int startWidth, int startHeight) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(startWidth, startHeight);
        setLocationRelativeTo(null);
    }

    public App(String title) {
        this(title, DEFAULT_START_WIDTH, DEFAULT_START_HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App("JChess");
            app.add(new Chessboard());
            app.setVisible(true);
        });
    }

}
