package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;

public class Square
    extends JPanel {

    private static final Color WHITE_SQUARES_COLOR = Color.decode("#DAD7CD");
    private static final Color BLACK_SQUARES_COLOR = Color.decode("#A18768");

    private Piece piece;

    private static Color getSquareColor(Position position) {
        return (position.row() + position.column()) % 2 != 0 ?
                WHITE_SQUARES_COLOR : BLACK_SQUARES_COLOR;
    }

    public Square(Position position, Piece piece) {
        this.piece = piece;
        setBackground(getSquareColor(position));
    }

    public Square(Position position) {
        this(position, null);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
