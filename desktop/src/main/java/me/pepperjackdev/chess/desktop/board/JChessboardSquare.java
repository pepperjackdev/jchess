package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;

public class JChessboardSquare
    extends JPanel {

    public static final String CHESSBOARD_DARK_SQUARES_COLOR = "#99B746";
    public static final String CHESSBOARD_LIGHT_SQUARES_COLOR = "#CEDECA";

    private final Position position;

    public JChessboardSquare(Position position) {
        super(new BorderLayout());
        this.position = position;

        // default configurations
        setBackground(getSquareBackgroundColor());
    }

    public Position getPosition() {
        return position;
    }

    private Color getSquareBackgroundColor() {
        return Color.decode((position.getRank() + position.getFile()) % 2 == 0 ?
                CHESSBOARD_LIGHT_SQUARES_COLOR : CHESSBOARD_DARK_SQUARES_COLOR);
    }

    private Color getSquareForegroundColor() {
        return Color.decode((position.getFile() + position.getRank()) % 2 == 0 ?
                CHESSBOARD_DARK_SQUARES_COLOR : CHESSBOARD_LIGHT_SQUARES_COLOR);
    }
}
