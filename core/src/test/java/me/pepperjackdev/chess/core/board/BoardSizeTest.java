package me.pepperjackdev.chess.core.board;

import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardSizeTest {
    private BoardSize underTest;

    @BeforeEach
    void setUp() {
        underTest = new BoardSize(8, 8);
    }

    @Test
    void testIsRowOutOfBoundsWhenRowIsInBounds() {
        assertFalse(underTest.isRowOutOfBounds(0));
    }

    @Test
    void testIsColumnOutOfBoundsWhenColumnIsInBounds() {
        assertFalse(underTest.isColumnOutOfBounds(0));
    }

    @Test
    void testIsRowOutOfBoundsWhenRowIsOutOfBounds() {
        assertTrue(underTest.isRowOutOfBounds(8));
    }

    @Test
    void testIsColumnOutOfBoundsWhenColumnIsOutOfBounds() {
        assertTrue(underTest.isColumnOutOfBounds(8));
    }

    @Test
    void testIsOutOfBoundsWhenIsInfBounds() {
        assertFalse(underTest.isOutOfBounds(new Position(0, 0)));
    }

    @Test
    void testIsOutOfBoundsWhenIsOutBounds() {
        assertTrue(underTest.isOutOfBounds(new Position(0, 8)));
    }
}
