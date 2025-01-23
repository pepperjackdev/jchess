package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.BoardBuilder;
import me.pepperjackdev.chess.core.game.Game;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.desktop.chessboard.Chessboard;

import javax.swing.*;
import java.awt.*;

public class JChess
    extends JFrame {

    private static final String APP_TITLE = "JChess";

    private static final int APP_START_WIDTH = 800;
    private static final int APP_START_HEIGHT = 800;

    public JChess(Game game) {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_START_WIDTH, APP_START_HEIGHT);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // adding the chessboard to the main view
        add(new Chessboard(new GameState(
                BoardBuilder.buildBoard(),
                Side.WHITE,
                new CastlingRights(true),
                null,
                0,
                0
        )), BorderLayout.CENTER);

        // showing up the window
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board board = new Board(8, 8);
            board.setPiece(new Position(3, 3), new Piece(PieceType.KING, Side.WHITE));
            new JChess(new Game(new GameState(new Board(8, 8))));
        });
    }
}