package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class BoardPanel
        extends JPanel {

    private final Board board;

    public BoardPanel(Board board) {
        this.board = board;
        setLayout(new GridLayout(board.getNumberOfRows(), board.getNumberOfColumns()));
        initializeSquares();
        loadPieces();
    }

    public Optional<Piece> getPiece(Position position) {
        return board.getPiece(position);
    }

    public Square getSquare(Position position) {
        return (Square)getComponent(
                (board.getNumberOfRows() - 1 - position.row()) * board.getNumberOfColumns() + position.column());
    }

    private void initializeSquares() {
        for (int row = board.getNumberOfRows() - 1; row >= 0; row--) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                add(new Square(new Position(row, column)));
            }
        }
    }

    private void loadPieces() {
        for (int row = 0; row < board.getNumberOfRows(); row++) {
            for (int column = 0; column < board.getNumberOfColumns(); column++) {
                Position curentPosition = new Position(row, column);
                getPiece(curentPosition).ifPresent(piece -> {
                    getSquare(curentPosition).setChessPiece(new ChessPiece(piece));
                });
            }
        }
    }
}
