package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board underTest;

    @BeforeEach
    void setUp() {
        underTest = new Board(8, 8);
    }

    @Test
    void testConstructBoard() {
        assertEquals(8, underTest.getNumberOfRows());
        assertEquals(8, underTest.getNumberOfColumns());
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
    void testSetPieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, Side.WHITE);
        Optional<Piece> result = underTest.setPiece(new Position(4, 4), piece);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPieceAtInvalidPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.getPiece(new Position(100, 100)));
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
}
