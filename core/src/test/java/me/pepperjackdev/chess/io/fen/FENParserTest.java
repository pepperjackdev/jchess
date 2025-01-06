package me.pepperjackdev.chess.io.fen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FENParserTest {

    private static final String TEST_FEN_STRING = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    private FENParser underTest;

    @BeforeEach
    public void setUp() {
        this.underTest = new FENParser(TEST_FEN_STRING);
    }

    @Test
    public void testConstructWithGivenFenString() {
        new FENParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    public void testConstructWithGivenInvalidFenString() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FENParser("hello/world/this/is/not/a/fen/string w KQkq - 0 1");
        });
    }

    @Test
    public void testGetPiecePlacementString() {
        assertEquals(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
                underTest.getPiecePlacementDataString()
        );
    }

    @Test
    public void testActiveColorString() {
        assertEquals(
                'w',
                underTest.getActiveColorChar()
        );
    }

    @Test
    public void testGetCastlingRightsString() {
        assertEquals(
                "KQkq",
                underTest.getCastlingRightsString()
        );
    }

    @Test
    public void testGetEnPassantTargetSquareString() {
        assertEquals(
                "-",
                underTest.getEnPassantTargetSquareString()
        );
     }

    @Test
    public void testGetHalfmoveClockString() {
        assertEquals(
                "0",
                underTest.getHalfmoveClockString()
        );
    }

    @Test
    public void testGetFullmoveNumberString() {
        assertEquals(
                "1",
                underTest.getFullmoveNumberString()
        );
    }
}
