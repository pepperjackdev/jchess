package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.Game;
import me.pepperjackdev.chess.core.game.state.GameState;

import javax.swing.*;
import java.awt.*;

public class Chessboard
        extends JLayeredPane {

    private final Game game;
    private final Board board;
    private final BoardPanel boardPanel;

    public Chessboard(GameState gameState) {
        this.game = new Game(gameState);
        this.board = gameState.getPiecePlacementData();
        this.boardPanel = new BoardPanel(board);
        add(boardPanel, DEFAULT_LAYER);

        // TODO: should be reviewed
        this.setLayout(new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {
                // You can use this to store components if needed
            }

            @Override
            public void removeLayoutComponent(Component comp) {
                // Handle component removal
            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                // Return the preferred size of the parent container
                return parent.getPreferredSize();
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                // Return the minimum size of the parent container
                return parent.getMinimumSize();
            }

            @Override
            public void layoutContainer(Container parent) {
                Component component = parent.getComponent(0);
                if (component instanceof BoardPanel) {
                    component.setBounds(0, 0, getWidth(), getHeight());
                } else {
                    component.setBounds(component.getX() - component.getWidth() / 2,
                            component.getY() - component.getHeight() / 2,
                            component.getWidth(),
                            component.getHeight());
                }
            }
        });

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

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
