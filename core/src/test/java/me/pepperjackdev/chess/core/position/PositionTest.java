package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position underTest;

    @BeforeEach
    void setUp() {
        underTest = new Position(2, 3);
    }

    @Test
    void testGetRow() {
        assertEquals(2, underTest.row());
    }

    @Test
    void testGetColumn() {
        assertEquals(3, underTest.column());
    }

    @Test
    void testMovedWithPositiveDeltaRow() {
        assertEquals(new Position(5, 3), underTest.moved(3, 0));
    }

    @Test
    void testMovedWithNegativeDeltaRow() {
        assertEquals(new Position(0, 3), underTest.moved(-2, 0));
    }

    @Test
    void testMovedWithPositiveDeltaColumn() {
        assertEquals(new Position(2, 6), underTest.moved(0, 3));
    }

    @Test
    void testMovedWithNegativeDeltaColumn() {
        assertEquals(new Position(2, 1), underTest.moved(0, -2));
    }

    @Test
    void testMovedWithPositiveDeltaRowAndPositiveDeltaColumn() {
        assertEquals(new Position(5, 5), underTest.moved(3, 2));
    }

    @Test
    void testMovedWithNegativeDeltaRowAndPositiveDeltaColumn() {
        assertEquals(new Position(0, 5), underTest.moved(-2, 2));
    }

    @Test
    void testMovedWithPositiveDeltaRowAndNegativeDeltaColumn() {
        assertEquals(new Position(6, 1), underTest.moved(4, -2));
    }

    @Test
    void testMovedWithNegativeDeltaRowAndNegativeDeltaColumn() {
        assertEquals(new Position(0, 0), underTest.moved(-2, -3));
    }
}
