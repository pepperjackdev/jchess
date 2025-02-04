package me.pepperjackdev.chess.io.fen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FENParserTest {
    private FENParser underTest;

    @BeforeEach
    void setUp() {
        underTest = new FENParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    void testGetPiecePlacementDataString() {
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
                underTest.getPiecePlacementDataString());
    }

    @Test
    void testGetActiveColorString() {
        assertEquals("w", underTest.getActiveColorString());
    }

    @Test
    void testGetCastlingRightsString() {
        assertEquals("KQkq", underTest.getCastlingRightsString());
    }

    @Test
    void testGetEnPassantTargetSquareString() {
        assertEquals("-", underTest.getEnPassantTargetSquareString());
    }

    @Test
    void testGetHalfMoveClockString() {
        assertEquals("0", underTest.getHalfmoveClockString());
    }

    @Test
    void testGetFullmoveNumberString() {
        assertEquals("1", underTest.getFullmoveNumberString());
    }
}
