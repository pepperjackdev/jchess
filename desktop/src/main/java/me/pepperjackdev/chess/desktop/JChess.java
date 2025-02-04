package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.desktop.chessboard.BoardBuilder;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.desktop.chessboard.Chessboard;

import javax.swing.*;
import java.awt.*;

public class JChess
    extends JFrame {

    private static final String APP_TITLE = "JChess";

    private static final int APP_START_WIDTH = 800;
    private static final int APP_START_HEIGHT = 800;

    public JChess(GameState gameState) {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_START_WIDTH, APP_START_HEIGHT);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // adding the chessboard to the main view
        add(new Chessboard(gameState));

        // showing up the window
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JChess(new GameState(
                BoardBuilder.buildBoard(),
                Side.WHITE,
                new CastlingRights(true),
                null,
                0,
                0
        )));
    }
}