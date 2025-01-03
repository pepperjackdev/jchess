package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.ImmutablePosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Board();
    }

    @Test
    public void testConstructBoardWithGivenNumberOfRanksAndNumberOfFiles() {
        underTest = new Board(3, 9);
        assertEquals(3, underTest.getNumberOfRanks());
        assertEquals(9, underTest.getNumberOfFiles());
    }

    @Test
    public void testConstructBoardWithDefaultNumberOfRanksAndNumberOfFiles() {
        assertEquals(8, underTest.getNumberOfRanks());
        assertEquals(8, underTest.getNumberOfFiles());
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
    public void testPlacingAndGettingPieceAtPosition() {
        Piece piece = new Piece(null, null);
        underTest.placeAt(piece, new ImmutablePosition(0, 0));

        assertTrue(underTest.at(new ImmutablePosition(0, 0)).isPresent());
        assertEquals(piece, underTest.at(new ImmutablePosition(0, 0)).get());
    }

    @Test
    public void testPlacingAndGettingPieceAtRankAndFile() {
        Piece piece = new Piece(null, null);
        underTest.placeAt(piece, new ImmutablePosition(2, 2));
        assertTrue(underTest.at(2, 2).isPresent());
        assertEquals(piece, underTest.at(2, 2).get());
    }

    @Test
    public void testGetPieceAtOutOfBoundsPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.at(new ImmutablePosition(10, 10)));
    }

    @Test
    public void testPlacePieceAtOutOfBoundsPosition() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.placeAt(new Piece(null, null), new ImmutablePosition(10, 10)));
    }

    @Test
    public void testPlacePieceAtRankAndFile() {
        Piece piece = new Piece(null, null);
        underTest.placeAt(piece, 2, 2);
        assertTrue(underTest.at(new ImmutablePosition(2, 2)).isPresent());
        assertEquals(piece, underTest.at(new ImmutablePosition(2, 2)).get());
    }

    @Test
    public void testIsOutOfBoundsWithOutOfBoundsPosition_OutOfBoundsRank() {
        assertTrue(underTest.isOutOfBounds(new ImmutablePosition(10, 0)));
    }

    @Test
    public void testIsOutOfBoundsWithOutOfBoundsPosition_OutOfBoundsFile() {
        assertTrue(underTest.isOutOfBounds(new ImmutablePosition(10, 0)));
    }
}
