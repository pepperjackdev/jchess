package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {

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
}
