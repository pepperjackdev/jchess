package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {

    @Test
    public void testIsValidRankWithValidRank() {
        assertTrue(Position.isValidRank(0));
    }

    @Test
    public void testIsValidFileWithValidFile() {
        assertTrue(Position.isValidFile(0));
    }

    @Test
    public void testIsValidRankWithInvalidRank () {
        assertFalse(Position.isValidRank(-1));
    }

    @Test
    public void testIsValidFileWithInvalidFile() {
        assertFalse(Position.isValidFile(-1));
    }
}
