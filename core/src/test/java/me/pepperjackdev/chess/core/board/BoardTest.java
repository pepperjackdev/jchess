package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board underTest;

    @BeforeEach
    void setUp() {
        underTest = new Board(new BoardSize(8, 8));
    }

    @Test
    void testConstructBoard() {
        assertEquals(8, underTest.getBounds().numberOfRows());
        assertEquals(8, underTest.getBounds().numberOfColumns());
    }

    @Test
    void testGetPieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, Side.WHITE);
        underTest.setPiece(new Position(4, 6), piece);
        Optional<Piece> result = underTest.getPiece(new Position(4, 6));
        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
    }

    @Test
    void testGetPieceAtInvalidPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.getPiece(new Position(100, 100)));
    }

    @Test
    void testSetPieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, Side.WHITE);
        Optional<Piece> result = underTest.setPiece(new Position(4, 4), piece);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSetPieceAtInvalidPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.setPiece(new Position(100, 100), new Piece(PieceType.PAWN, Side.WHITE)));
    }

    @Test
    void testRemovePieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, Side.WHITE);
        underTest.setPiece(new Position(4, 4), piece);
        Optional<Piece> result = underTest.removePiece(new Position(4, 4));
        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
        assertTrue(underTest.getPiece(new Position(4, 4)).isEmpty());
    }

    @Test
    void testRemovePieceAtInvalidPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.removePiece(new Position(100, 100)));
    }

    @Test
    void testGetTopLeftCornerPosition() {
        assertEquals(new Position(7, 0), underTest.getTopLeftCornerPosition());
    }

    @Test
    void testGetTopRightCornerPosition() {
        assertEquals(new Position(7, 7), underTest.getTopRightCornerPosition());
    }

    @Test
    void testGetBottomLeftCornerPosition() {
        assertEquals(new Position(0, 0), underTest.getBottomLeftCornerPosition());
    }

    @Test
    void testGetBottomRightCornerPosition() {
        assertEquals(new Position(0, 7), underTest.getBottomRightCornerPosition());
    }

    @Test
    void testIsOutOfBoundsPositionWithInBoundsPosition() {
        assertFalse(underTest.getBounds().isOutOfBounds(new Position(3, 3)));
    }

    @Test
    void testIsOutOfBoundsPositionWithOutBoundsRow() {
        assertTrue(underTest.getBounds().isOutOfBounds(new Position(100, 3)));
    }

    @Test
    void testIsOutOfBoundsPositionWithOutBoundsColumn() {
        assertTrue(underTest.getBounds().isOutOfBounds(new Position(3, 100)));
    }

    @Test
    void testIterator() {
        Iterator<Position> iterator = underTest.iterator();
        for (int row = 0; row < underTest.getBounds().numberOfRows(); row++) {
            for (int column = 0; column < underTest.getBounds().numberOfColumns(); column++) {
                assertEquals(new Position(row, column), iterator.next());
            }
        }
    }
}
