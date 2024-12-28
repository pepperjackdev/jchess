package me.pepperjackdev.chess.core;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceSide;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void testGetPieceAtPosition() throws NoSuchFieldException, IllegalAccessException {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);
        ((Piece[][]) board.get(underTest))[0][0] = piece;

        Optional<Piece> result = underTest.at(new Position("a1"));

        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
    }

    @Test
    public void testPlacePieceAtPosition() throws NoSuchFieldException, IllegalAccessException {
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        underTest.placeAt(piece, new Position("a1"));

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);

        Piece result = ((Piece[][])board.get(underTest))[0][0];
        assertEquals(piece, result);
    }

    @Test
    public void testGetPieceAtPositionInCustomSizedBoard_30x30() throws NoSuchFieldException, IllegalAccessException {
        underTest = new Board(30, 30);
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);
        ((Piece[][]) board.get(underTest))[27][27] = piece;

        Optional<Piece> result = underTest.at(new Position("ab28"));

        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
    }

    @Test
    public void testPlacePieceAtPositionInCustomSizedBoard_30x30() throws NoSuchFieldException, IllegalAccessException {
        underTest = new Board(30, 30);
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        underTest.placeAt(piece, new Position("ab28"));

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);

        Piece result = ((Piece[][])board.get(underTest))[27][27];
        assertEquals(piece, result);
    }

    @Test
    public void testGetPieceAtPositionInCustomSizedBoard_100x100() throws NoSuchFieldException, IllegalAccessException {
        underTest = new Board(100, 100);
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);
        ((Piece[][]) board.get(underTest))[99][99] = piece;

        Optional<Piece> result = underTest.at(new Position("cv100"));

        assertTrue(result.isPresent());
        assertEquals(piece, result.get());
    }

    @Test
    public void testPlacePieceAtPositionInCustomSizedBoard_100x100() throws NoSuchFieldException, IllegalAccessException {
        underTest = new Board(100, 100);
        Piece piece = new Piece(PieceType.PAWN, PieceSide.WHITE);
        underTest.placeAt(piece, new Position("cv100"));

        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);

        Piece result = ((Piece[][])board.get(underTest))[99][99];
        assertEquals(piece, result);
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

    @Test
    public void testGetRankIndexFromPosition_a1()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getRankIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(0, getRankIndex.invoke(underTest, new Position("a1")));
    }

    @Test
    public void testGetFileIndexFromPosition_a1()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getFileIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(0, getRankIndex.invoke(underTest, new Position("a1")));
    }

    @Test
    public void testGetRankIndexFromPosition_aa27()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getRankIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(26, getRankIndex.invoke(underTest, new Position("aa27")));
    }

    @Test
    public void testGetFileIndexFromPosition_aa27()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getFileIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(26, getRankIndex.invoke(underTest, new Position("aa27")));
    }

    @Test
    public void testGetRankIndexFromPosition_ba53()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getRankIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(52, getRankIndex.invoke(underTest, new Position("ba53")));
    }

    @Test
    public void testGetFileIndexFromPosition_ba53()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRankIndex = Board.class.getDeclaredMethod("getFileIndex", Position.class);
        getRankIndex.setAccessible(true);

        assertEquals(52, getRankIndex.invoke(underTest, new Position("ba53")));
    }
}
