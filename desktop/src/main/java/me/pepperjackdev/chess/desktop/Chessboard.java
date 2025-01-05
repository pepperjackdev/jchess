package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.parsing.fen.FENParser;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.ImmutablePosition;
import me.pepperjackdev.chess.core.position.MutablePosition;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Chessboard
    extends JPanel {

    private final Board board;
    private final Square[][] squares;

    public Chessboard() {
        // initializing fields
        FENParser parser = new FENParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        this.board = parser.parsePiecePlacementData();
        this.squares = new Square[board.getNumberOfRanks()][board.getNumberOfFiles()];

        // loading chessboard
        setLayout(new GridLayout(board.getNumberOfRanks(), board.getNumberOfFiles()));
        initializeSquares();
        loadBoard();
    }

    public void placeAt(Piece piece, ImmutablePosition position) {
        board.placeAt(piece, position);
        loadBoard();
    }

    private void initializeSquares() {
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {
                squares[rank][file] = new Square(new ImmutablePosition(rank, file));
                add(squares[rank][file]);
            }
        }
    }

    private void loadBoard() {
        MutablePosition position = new MutablePosition(board.getTopLeftPosition());
        for (int rank = 0; rank < board.getNumberOfRanks(); rank++) {
            for (int file = 0; file < board.getNumberOfFiles(); file++) {

                Optional<Piece> piece = board.at(position);
                if (piece.isPresent()) {
                    squares[rank][file].setPiece(piece.get());
                } else {
                    squares[rank][file].setPiece(null);
                }
                squares[rank][file].repaint();

                position.moveToNextRank();
            }
            position.moveToPrevFile();
            position.setRank(0);
        }
    }
}
