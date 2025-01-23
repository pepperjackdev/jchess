package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.Game;
import me.pepperjackdev.chess.core.game.state.GameState;

import javax.swing.*;

public class Chessboard
        extends JLayeredPane {

    private final Game game;
    private final Board board;
    private final BoardPanel boardPanel;

    public Chessboard(GameState gameState) {
        this.game = new Game(gameState);
        this.board = gameState.getPiecePlacementData();
        this.boardPanel = new BoardPanel(board);
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
