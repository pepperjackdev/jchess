package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;

public class Chessboard
    extends JPanel {

    private final Board board;

    public Chessboard(Board board) {
        this.board = board;
        setLayout(new GridLayout(board.getNumberOfRows(), board.getNumberOfColumns()));
        initializeSquares();
    }

    private Square getSquare(int index) {
        return (Square)getComponent(index);
    }

    private Square getSquare(Position position) {
        return getSquare(position.row() * board.getNumberOfColumns() + position.column());
    }

    private void initializeSquares() {
        for (int row = board.getNumberOfRows() -1; row >= 0; row--) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                add(new Square(new Position(row, column),
                        board.getPiece(new Position(row, column)).orElse(null)));
            }
        }
    }
}
