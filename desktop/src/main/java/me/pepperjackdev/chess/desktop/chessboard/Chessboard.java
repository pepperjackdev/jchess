package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chessboard
        extends JLayeredPane {

    private final Game game;
    private final BoardPanel boardPanel;

    private class PieceDragHandler
            extends MouseAdapter {

        private Square sourceSquare;
        private ChessPiece draggingPiece;
        private Dimension pieceSize;
        private Point dragOffset;

        @Override
        public void mousePressed(MouseEvent e) {
            Component targetComponent = boardPanel.getComponentAt(e.getPoint());

            if (targetComponent instanceof Square targetSquare) {
                targetSquare.getChessPiece().ifPresent(chessPiece -> {
                    sourceSquare = targetSquare;
                    draggingPiece = chessPiece;
                    pieceSize = sourceSquare.getSize();

                    add(draggingPiece, JLayeredPane.DRAG_LAYER);
                    sourceSquare.setChessPiece(null);

                    dragOffset = SwingUtilities.convertPoint(boardPanel, e.getPoint(), Chessboard.this);
                    draggingPiece.setMaximumSize(pieceSize);

                    repaint();
                });
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (draggingPiece != null) {
                // Update the piece's position to follow the cursor
                dragOffset = SwingUtilities.convertPoint(boardPanel, e.getPoint(), Chessboard.this);
                draggingPiece.setBounds(
                        dragOffset.x - pieceSize.width / 2,
                        dragOffset.y - pieceSize.height / 2,
                        pieceSize.width,
                        pieceSize.height
                );
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (draggingPiece != null) {
                Component targetComponent = boardPanel.getComponentAt(e.getPoint());
                if (targetComponent instanceof Square targetSquare) {
                    targetSquare.setChessPiece(draggingPiece);
                } else if (sourceSquare != null) {
                    sourceSquare.setChessPiece(draggingPiece);
                }

                // clearing the vars
                draggingPiece = null;
                sourceSquare = null;
                pieceSize = null;
                repaint();
            }

        }
    } // end of class

    public Chessboard(Game game) {
        this.game = game;
        this.boardPanel = new BoardPanel(game.getBoard());
        setLayout(new OverlayLayout(this));
        add(boardPanel, DEFAULT_LAYER);

        PieceDragHandler dragHandler = new PieceDragHandler();
        addMouseListener(dragHandler);
        addMouseMotionListener(dragHandler);
    }
}
