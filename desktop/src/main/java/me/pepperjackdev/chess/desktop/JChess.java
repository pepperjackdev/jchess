package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.desktop.board.Chessboard;

import javax.swing.*;

public class JChess
    extends JFrame {

    private static final String APP_TITLE = "JChess";

    private static final int APP_START_WIDTH = 800;
    private static final int APP_START_HEIGHT = 800;

    public JChess() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(APP_START_WIDTH, APP_START_HEIGHT);
        setLocationRelativeTo(null);
        Board board = new Board(8, 8);

        board.setPiece(new Position(0, 0), new Piece(PieceType.PAWN, Side.BLACK));

        add(new Chessboard(board));
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JChess::new);
    }
}