package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.desktop.piece.PieceIcon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Square
    extends JPanel {

    private static final Color DARK_SQUARES_COLOR = Color.decode("#588157");
    private static final Color LIGHT_SQUARES_COLOR = Color.decode("#dad7cd");

    private static final PieceIcon pieceIcon;

    static {
        try {
            pieceIcon = new PieceIcon("pieces_icons.svg");
        } catch (IOException e) {
            throw new RuntimeException("Error while loading piece icon sprite: " + e);
        }
    }

    private Piece piece;

    public Square(Position position) {
        setBackground(
                (position.getRank() + position.getFile()) % 2 == 0 ?
                        DARK_SQUARES_COLOR : LIGHT_SQUARES_COLOR
        );
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderPieceIcon(g);
    }

    private void renderPieceIcon(Graphics g) {
        if (piece == null) {
            return;
        }

        pieceIcon.drawIcon(g, piece, 0, 0, getWidth(), getHeight());
    }
}
