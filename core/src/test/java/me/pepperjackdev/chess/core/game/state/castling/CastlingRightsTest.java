package me.pepperjackdev.chess.core.game.state.castling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CastlingRightsTest {
    private CastlingRights underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CastlingRights(true);
    }

    @Test
    public void testConstructorWithGivenCastlingRights() {
        underTest = new CastlingRights(
                true,
                false,
                true,
                false
        );

        assertTrue(underTest.blackCanCastleKingSide());
        assertFalse(underTest.blackCanCastleQueenSide());
        assertTrue(underTest.whiteCanCastleKingSide());
        assertFalse(underTest.whiteCanCastleQueenSide());
    }

    @Test
    public void testConstructorWithAllCanCastleTrue() {
        underTest = new CastlingRights(true);

        assertTrue(underTest.blackCanCastleKingSide());
        assertTrue(underTest.blackCanCastleQueenSide());
        assertTrue(underTest.whiteCanCastleKingSide());
        assertTrue(underTest.whiteCanCastleQueenSide());
    }

    @Test
    public void testConstructorWithAllCanCastleFalse() {
        underTest = new CastlingRights(false);

        assertFalse(underTest.blackCanCastleKingSide());
        assertFalse(underTest.blackCanCastleQueenSide());
        assertFalse(underTest.whiteCanCastleKingSide());
        assertFalse(underTest.whiteCanCastleQueenSide());
    }

    @Test
    public void testWhiteCanCastleKingSide() {
        assertTrue(underTest.whiteCanCastleKingSide());
    }

    @Test
    public void testWhiteCanCastleQueenSide() {
        assertTrue(underTest.whiteCanCastleQueenSide());
    }

    @Test
    public void testBlackCanCastleKingSide() {
        assertTrue(underTest.blackCanCastleKingSide());
    }

    @Test
    public void testBlackCanCastleQueenSide() {
        assertTrue(underTest.blackCanCastleQueenSide());
    }

    @Test
    public void testSetWhiteKingSideCastling() {
        underTest.setWhiteKingSideCastling(false);
        assertFalse(underTest.whiteCanCastleKingSide());
    }

    @Test
    public void testSetWhiteQueenSideCastling() {
        underTest.setWhiteQueenSideCastling(false);
        assertFalse(underTest.whiteCanCastleQueenSide());
    }

    @Test
    public void testSetBlackKingSideCastling() {
        underTest.setBlackKingSideCastling(false);
        assertFalse(underTest.blackCanCastleKingSide());
    }

    @Test
    public void testSetBlackQueenSideCastling() {
        underTest.setBlackQueenSideCastling(false);
        assertFalse(underTest.blackCanCastleQueenSide());
    }
}
