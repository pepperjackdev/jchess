package me.pepperjackdev.chess.desktop.board;

import me.pepperjackdev.chess.core.game.Game;
import me.pepperjackdev.chess.core.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chessboard
    extends JLayeredPane {

    private final Game game;
    private final BoardPanel boardPanel;
    private ChessPiece draggingPiece;
    private Square sourceSquare;
    private Dimension pieceSize;
    private Point dragOffset;

    public Chessboard(Game game) {
        this.game = game;
        this.boardPanel = new BoardPanel(game.getBoard());
        setLayout(new OverlayLayout(this));
        add(boardPanel, DEFAULT_LAYER);
        addMouseHandlers();
    }

    private void addMouseHandlers() {
        MouseAdapter handler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component clicked = boardPanel.getComponentAt(e.getPoint());
                if (clicked instanceof Square square) {
                    sourceSquare = square;
                    draggingPiece = square.getChessPiece().orElse(null);
                    if (draggingPiece != null) {
                        // Capture the original size of the piece
                        pieceSize = draggingPiece.getSize();
                        dragOffset = SwingUtilities.convertPoint(square, e.getPoint(), Chessboard.this);

                        // Set size explicitly to avoid resizing
                        draggingPiece.setPreferredSize(pieceSize);
                        draggingPiece.setSize(pieceSize);
                        draggingPiece.setBounds(dragOffset.x - pieceSize.width / 2,
                                dragOffset.y - pieceSize.height / 2,
                                pieceSize.width,
                                pieceSize.height);

                        // Add the piece to the drag layer
                        add(draggingPiece, JLayeredPane.DRAG_LAYER);
                        square.setChessPiece(null); // Remove piece from source square
                        repaint();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggingPiece != null) {
                    Point newLocation = SwingUtilities.convertPoint(boardPanel, e.getPoint(), Chessboard.this);
                    draggingPiece.setBounds(newLocation.x - pieceSize.width / 2,
                            newLocation.y - pieceSize.height / 2,
                            pieceSize.width,
                            pieceSize.height);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (draggingPiece != null) {
                    Component target = boardPanel.getComponentAt(e.getPoint());
                    if (target instanceof Square targetSquare) {
                        targetSquare.setChessPiece(draggingPiece);
                    } else if (sourceSquare != null) {
                        sourceSquare.setChessPiece(draggingPiece);
                    }
                    draggingPiece = null;
                    sourceSquare = null;
                    pieceSize = null;
                    repaint();
                }
            }
        };

        boardPanel.addMouseListener(handler);
        boardPanel.addMouseMotionListener(handler);
    }

    private int positionToIndex(Position position) {
        int rows = game.getBoard().getNumberOfRows();
        int cols = game.getBoard().getNumberOfColumns();
        return (rows - 1 - position.row()) * cols + position.column();
    }
}
