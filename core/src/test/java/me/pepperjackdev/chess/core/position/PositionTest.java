package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position underTest;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, NoSuchMethodException {
        underTest = new Position(6, 6);
    }

    @Test
    public void testConstructorWithGivenRankAndFile() {
        assertEquals(6, underTest.getRank());
        assertEquals(6, underTest.getFile());
    }

    @Test
    public void testConstructorWithGivenInvalidRankAndFile() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest = new Position(-1, -1);
        });
    }

    @Test
    public void testCheckInputRankWithValidRank() {
        assertTrue(Position.checkInputRank(0));
    }

    @Test
    public void testCheckInputFileWithValidFile() {
        assertTrue(Position.checkInputFile(0));
    }

    @Test
    public void testCheckInputFileWithInvalidRank() {
        assertFalse(Position.checkInputFile(-1));
    }

    @Test
    public void testCheckInputFileWithInvalidFile() {
        assertFalse(Position.checkInputFile(-1));
    }

    @Test
    public void testGetRank() {
        assertEquals(6, underTest.getRank());
    }

    @Test
    public void testGetFile() {
        assertEquals(6, underTest.getFile());
    }
}
