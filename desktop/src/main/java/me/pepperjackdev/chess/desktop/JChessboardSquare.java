package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.position.Position;
import me.pepperjackdev.chess.desktop.config.PositionIndicatorConfig;

import javax.swing.*;
import java.awt.*;

public class JChessboardSquare
    extends JPanel {

    public static final String CHESSBOARD_DARK_SQUARES_COLOR = "#99B746";
    public static final String CHESSBOARD_LIGHT_SQUARES_COLOR = "#CEDECA";

    private final int rank;
    private final int file;

    public JChessboardSquare(int rank, int file) {
        super(new BorderLayout());
        this.rank = rank;
        this.file = file;

        // getting default configurations
        setBackground(getSquareBackgroundColor());
    }

    private Color getSquareBackgroundColor() {
        return Color.decode((rank + file) % 2 == 0 ? CHESSBOARD_LIGHT_SQUARES_COLOR : CHESSBOARD_DARK_SQUARES_COLOR);
    }

    private Color getSquareForegroundColor() {
        return Color.decode((rank + file) % 2 == 0 ? CHESSBOARD_DARK_SQUARES_COLOR : CHESSBOARD_LIGHT_SQUARES_COLOR);
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
