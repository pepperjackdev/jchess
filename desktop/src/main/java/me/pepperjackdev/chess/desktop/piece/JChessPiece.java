package me.pepperjackdev.chess.desktop.piece;

import me.pepperjackdev.chess.core.piece.Piece;

import javax.swing.*;

public class JChessPiece
    extends ImageIcon {

    private Piece piece;

    public JChessPiece(Piece piece) {
        this.piece = piece;
    }

    private String getPieceIconPath() {
        String side = switch (piece.getSide()) {
            case WHITE -> "white";
            case BLACK -> "black";
        };

        String type = switch (piece.getType()) {
            case PAWN -> "pawn";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case ROOK -> "rook";
            case QUEEN -> "queen";
            case KING -> "king";
        };

        return String.format("%s_%s.svg", side, type);
    }
}
