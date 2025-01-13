package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.desktop.rendering.PieceIconRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Square
    extends JPanel {

    private static final Color WHITE_SQUARES_COLOR = Color.decode("#DAD7CD");
    private static final Color BLACK_SQUARES_COLOR = Color.decode("#A18768");

    private static Color getSquareColor(Position position) {
        return (position.row() + position.column()) % 2 != 0 ?
                WHITE_SQUARES_COLOR : BLACK_SQUARES_COLOR;
    }

    private final Position position;

    public Square(Position position) {
        this.position = position;
        setLayout(new BorderLayout());
        setBackground(getSquareColor(position));
    }

    public Chessboard getChessboard() {
        return (Chessboard)getParent();
    }

    public Optional<Piece> getPiece() {
        return getChessboard().getPiece(position);
    }

    public void setPiece(Piece piece) {
        getChessboard().setPiece(position, piece);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getPiece().isPresent()) {
            PieceIconRenderer.PIECE_ICON_RENDERER.drawIcon(g, getPiece().get(), 0, 0, getWidth(), getHeight());
        }
    }
}
