package me.pepperjackdev.chess.desktop.chessboard;

import me.pepperjackdev.chess.core.move.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceDragHandler
        extends MouseAdapter {

    private final Chessboard chessboard;

    private Square sourceSquare;
    private ChessPiece draggingPiece;
    private Dimension pieceSize;

    public PieceDragHandler(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component targetComponent = chessboard.getBoardPanel().getComponentAt(e.getPoint());

        if (targetComponent instanceof Square targetSquare) {
            targetSquare.getChessPiece().ifPresent(chessPiece -> {
                sourceSquare = targetSquare;
                draggingPiece = chessPiece;
                pieceSize = sourceSquare.getSize();
                draggingPiece.setMaximumSize(pieceSize);

                sourceSquare.setChessPiece(null);
                chessboard.add(draggingPiece, JLayeredPane.DRAG_LAYER);

                // Update the piece's position to follow the cursor
                Point dragOffset = SwingUtilities.convertPoint(chessboard.getBoardPanel(), e.getPoint(), chessboard);
                draggingPiece.setBounds(
                        dragOffset.x - pieceSize.width / 2,
                        dragOffset.y - pieceSize.height / 2,
                        pieceSize.width,
                        pieceSize.height
                );
                draggingPiece.setLocation(e.getPoint());
            });
        }
    }

    @Override
    public void  mouseDragged(MouseEvent e) {
        if (draggingPiece != null) {
            // Update the piece's position to follow the cursor
            Point dragOffset = SwingUtilities.convertPoint(chessboard.getBoardPanel(), e.getPoint(), chessboard);
            draggingPiece.setBounds(
                    dragOffset.x - pieceSize.width / 2,
                    dragOffset.y - pieceSize.height / 2,
                    pieceSize.width,
                    pieceSize.height
            );
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (draggingPiece != null) {
            Component targetComponent = chessboard.getBoardPanel().getComponentAt(e.getPoint());
            if (targetComponent instanceof Square targetSquare) {
                chessboard.getGame().move(new Move(sourceSquare.getPosition(), targetSquare.getPosition()));
                chessboard.remove(draggingPiece);
                chessboard.getBoardPanel().loadPieces();
            }

            // clearing the vars
            draggingPiece = null;
            sourceSquare = null;
            pieceSize = null;
            chessboard.repaint();
        }
    }
}