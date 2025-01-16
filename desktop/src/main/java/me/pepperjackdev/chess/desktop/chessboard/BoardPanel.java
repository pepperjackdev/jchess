package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final Board board;

    public BoardPanel(Board board) {
        this.board = board;
        setLayout(new GridLayout(board.getNumberOfRows(), board.getNumberOfColumns()));
        // initializes the board squares
        initializeSquares();
        // loads the pieces into the chessboard
        loadPieces();
    }

    private void initializeSquares() {
        for (int row = board.getNumberOfRows() - 1; row >= 0; row--) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                add(new Square(new Position(row, column)));
            }
        }
    }

    private Square getSquare(Position position) {
        return (Square)getComponent((
                board.getNumberOfRows() - 1 - position.row()) * board.getNumberOfColumns() + position.column());
    }

    public void loadPieces() {
        for (int row = board.getNumberOfRows() - 1; row >= 0; row--) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                Position currentPosition = new Position(row, column);
                board.getPiece(currentPosition).ifPresent(piece -> {
                    getSquare(currentPosition).setChessPiece(new ChessPiece(piece));
                });
            }
        }
    }
}
