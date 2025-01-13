package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Chessboard
    extends JPanel {

    private final Board board;

    public Chessboard(Board board) {
        this.board = board;
        setLayout(new GridLayout(board.getNumberOfRows(), board.getNumberOfColumns()));
        initializeSquares();
    }

    private void initializeSquares() {
        for (int row = board.getNumberOfRows() -1; row >= 0; row--) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                add(new Square(new Position(row, column)));
            }
        }
    }

    private Square getSquare(int index) {
        return (Square)getComponent(index);
    }

    private Square getSquare(Position position) {
        return getSquare(position.row() * (board.getNumberOfColumns() - 1) + position.column());
    }

    public Optional<Piece> getPiece(Position position) {
        return board.getPiece(position);
    }

    public void setPiece(Position position, Piece piece) {
        board.setPiece(position, piece);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
    }
}
