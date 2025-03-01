package me.pepperjackdev.chess.core.position;

import me.pepperjackdev.chess.core.board.BoardSize;
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
}
