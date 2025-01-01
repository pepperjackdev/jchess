package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.Board;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Chessboard
    extends JPanel {

    private final Board board;
    private final Square[][] squares;

    public Chessboard() {
        // initializing fields
        this.board = new Board();
        this.squares = new Square[board.getNumberOfRanks()][board.getNumberOfFiles()];

        // loading chessboard
        setLayout(new GridLayout(board.getNumberOfRanks(), board.getNumberOfFiles()));
        initializeSquares();
        loadBoard();
    }

    public void initializeSquares() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                squares[rank][file] = new Square(new Position(rank, file));
                add(squares[rank][file]);
            }
        }
    }

    public void loadBoard() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                Optional<Piece> piece = board.at(new Position(rank, file));
                if (piece.isPresent()) {
                    squares[rank][file].setPiece(piece.get());
                }
            }
        }
    }
}
