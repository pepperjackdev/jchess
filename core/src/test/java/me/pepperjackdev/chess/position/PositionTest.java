package me.pepperjackdev.chess.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Position(5, 5);
    }

    @Test
    void testConstructPositionWithGivenRankAndFile() {
        assertEquals(5, underTest.getRank());
        assertEquals(5, underTest.getFile());
    }

    @Test
    void testConstructWithInvalidRankEqualToZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position(0, 1);
        });
    }

    @Test
    void testConstructWithInvalidRankLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position(-1, 1);
        });
    }

    @Test
    void testConstructWithInvalidFileEqualToZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position(1, 0);
        });
    }

    @Test
    void testConstructWithInvalidFileLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position(1, -1);
        });
    }

    @Test
    void testConstructPositionFromAlgebraicNotation() {
        underTest = new Position("aa10");
        assertEquals(10, underTest.getRank());
        assertEquals(27, underTest.getFile());
    }

    @Test
    void testConstructPositionFromInvalidAlgebraicNotation() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position("Hello, World!");
        });
    }

    @Test
    void testConstructPositionFromInvalidAlgebraicNotationImpossibleRank() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position("a0");
        });
    }

    @Test
    void testParseRankFromAlgebraicNotation()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method parseRank = Position.class.getDeclaredMethod("parseRank", String.class);
        parseRank.setAccessible(true);
        assertEquals(12345, parseRank.invoke(underTest, "12345"));
    }

    @Test
    void testParseFileFromAlgebraicNotation()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method parseFile = Position.class.getDeclaredMethod("parseFile", String.class);
        parseFile.setAccessible(true);
        assertEquals(27, parseFile.invoke(underTest, "aa"));
    }

    @Test
    void testGetRank() {
        assertEquals(5, underTest.getRank());
    }

    @Test
    void testGetFile() {
        assertEquals(5, underTest.getFile());
    }
}
