package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.BoardSize;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;

public class BoardBuilder {
    public static Board buildBoard() {
        Board board = new Board(new BoardSize(8, 8));
        board.setPiece(new Position(0, 0), new Piece(PieceType.ROOK, Side.WHITE));
        board.setPiece(new Position(0, 1), new Piece(PieceType.KNIGHT, Side.WHITE));
        board.setPiece(new Position(0, 2), new Piece(PieceType.BISHOP, Side.WHITE));
        board.setPiece(new Position(0, 3), new Piece(PieceType.QUEEN, Side.WHITE));
        board.setPiece(new Position(0, 4), new Piece(PieceType.KING, Side.WHITE));
        board.setPiece(new Position(0, 5), new Piece(PieceType.BISHOP, Side.WHITE));
        board.setPiece(new Position(0, 6), new Piece(PieceType.KNIGHT, Side.WHITE));
        board.setPiece(new Position(0, 7), new Piece(PieceType.ROOK, Side.WHITE));
        board.setPiece(new Position(1, 0), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 1), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 2), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 3), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 4), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 5), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 6), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(1, 7), new Piece(PieceType.PAWN, Side.WHITE));
        board.setPiece(new Position(7, 0), new Piece(PieceType.ROOK, Side.BLACK));
        board.setPiece(new Position(7, 1), new Piece(PieceType.KNIGHT, Side.BLACK));
        board.setPiece(new Position(7, 2), new Piece(PieceType.BISHOP, Side.BLACK));
        board.setPiece(new Position(7, 4), new Piece(PieceType.KING, Side.BLACK));
        board.setPiece(new Position(7, 3), new Piece(PieceType.QUEEN, Side.BLACK));
        board.setPiece(new Position(7, 5), new Piece(PieceType.BISHOP, Side.BLACK));
        board.setPiece(new Position(7, 6), new Piece(PieceType.KNIGHT, Side.BLACK));
        board.setPiece(new Position(7, 7), new Piece(PieceType.ROOK, Side.BLACK));
        board.setPiece(new Position(6, 0), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 1), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 2), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 3), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 4), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 5), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 6), new Piece(PieceType.PAWN, Side.BLACK));
        board.setPiece(new Position(6, 7), new Piece(PieceType.PAWN, Side.BLACK));
        return board;
    }
}