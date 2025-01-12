package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.piece.Piece;

import javax.swing.*;
import java.awt.*;

public class PieceIcon
    extends ImageIcon {

    private final Piece piece;

    public PieceIcon(final Piece piece) {
        this.piece = piece;
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        super.paintIcon(c, g, x, y);
    }
}
