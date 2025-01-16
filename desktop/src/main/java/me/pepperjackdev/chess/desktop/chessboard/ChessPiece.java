package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.desktop.rendering.PieceIconRenderer;

import javax.swing.*;
import java.awt.*;

public class ChessPiece
    extends JPanel {

    private final Piece piece;

    public ChessPiece(final Piece piece) {
        this.piece = piece;
        setOpaque(false); // shouldn't be visible
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PieceIconRenderer.PIECE_ICON_RENDERER.drawIcon(g, piece, 0, 0, getWidth(), getHeight());
    }
}
