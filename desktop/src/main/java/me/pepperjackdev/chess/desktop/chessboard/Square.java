package me.pepperjackdev.chess.desktop.chessboard;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Square
    extends JPanel {

    private static final Color WHITE_SQUARES_COLOR = Color.decode("#DAD7CD");
    private static final Color BLACK_SQUARES_COLOR = Color.decode("#A18768");

    private final Position position;

    public Square(Position position) {
        this.position = position;
        setLayout(new BorderLayout());
        setBackground(getSquareBackgroundColor());
    }

    private boolean isWhiteSquare() {
        return (position.row() + position.column()) % 2 != 0;
    }

    private Color getSquareBackgroundColor() {
        return isWhiteSquare() ?
                WHITE_SQUARES_COLOR : BLACK_SQUARES_COLOR;
    }

    public Position getPosition() {
        return position;
    }

    public Optional<ChessPiece> getChessPiece() {
        return getComponentCount() > 0 ?
                Optional.of((ChessPiece) getComponent(0)) : Optional.empty();
    }

    public void setChessPiece(ChessPiece chessPiece) {
        getChessPiece().ifPresent(this::remove);
        if (chessPiece != null) {
            add(chessPiece, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }
}
