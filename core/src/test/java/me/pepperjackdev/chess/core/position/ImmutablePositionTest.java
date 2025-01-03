package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutablePositionTest {
    private ImmutablePosition underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ImmutablePosition(6, 6);
    }

    @Test
    public void testConstructorWithGivenRankAndFile() {
        assertEquals(6, underTest.rank());
        assertEquals(6, underTest.file());
    }

    @Test
    public void testConstructorWithGivenInvalidRankAndFile() {
        assertThrows(IllegalArgumentException.class,
                () -> underTest = new ImmutablePosition(-1, -1));
    }

    @Test
    public void testGetRank() {
        assertEquals(6, underTest.rank());
    }

    @Test
    public void testGetFile() {
        assertEquals(6, underTest.file());
    }
}
