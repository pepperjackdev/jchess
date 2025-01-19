package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.game.Game;

import javax.swing.*;

public class Chessboard
        extends JLayeredPane {

    private final Game game;
    private final BoardPanel boardPanel;

    public Chessboard(Game game) {
        this.game = game;
        this.boardPanel = new BoardPanel(game.getBoard());
        setLayout(new OverlayLayout(this));
        add(boardPanel, DEFAULT_LAYER);

        PieceDragHandler dragHandler = new PieceDragHandler(this);
        addMouseListener(dragHandler);
        addMouseMotionListener(dragHandler);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public Game getGame() {
        return game;
    }
}
