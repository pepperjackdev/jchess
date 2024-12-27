package me.pepperjackdev.chess;

import me.pepperjackdev.chess.piece.Piece;
import me.pepperjackdev.chess.piece.PieceSide;
import me.pepperjackdev.chess.piece.PieceType;
import me.pepperjackdev.chess.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Board();
    }

    @Test
    public void testConstructBoardWithDefaultSize() {
        assertEquals(8, underTest.getNumberOfRanks());
        assertEquals(8, underTest.getNumberOfFiles());
    }

    @Test
    public void testConstructBoardWithCustomSize() {
        underTest = new Board(16, 12);
        assertEquals(16, underTest.getNumberOfRanks());
        assertEquals(12, underTest.getNumberOfFiles());
    }

    @Test
    public void testGetNumberOfRanks() {
        assertEquals(8, underTest.getNumberOfRanks());
    }

    @Test
    public void testGetNumberOfFiles() {
        assertEquals(8, underTest.getNumberOfFiles());
    }

    @Test
    public void testGetPieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        underTest.placeAt(piece, new Position("a1"));
        assertTrue(underTest.at(new Position("a1")).isPresent());
        assertEquals(piece, underTest.at(new Position("a1")).get());
    }

    @Test
    public void tesPlacePieceAtPosition() {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        Optional<Piece> result = underTest.placeAt(piece, new Position("a1"));
        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
    }

    @Test
    public void testGetPieceAtOutOfBoundsPosition() {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        assertThrows(IndexOutOfBoundsException.class, () -> underTest.at(new Position("z10")));
    }

    @Test
    public void testPlacePieceAtOutOfBoundsPosition() {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        assertThrows(IndexOutOfBoundsException.class, () -> underTest.placeAt(piece, new Position("z10")));
    }
}
