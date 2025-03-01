package me.pepperjackdev.chess.core.position;

import me.pepperjackdev.chess.core.board.BoardSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionIteratorTest {
    private PositionIterator underTest;

    @BeforeEach
    void setUp() {
        underTest = new PositionIterator(new BoardSize(8, 8));
    }

    @Test
    void testHasNextWhenHasNextColumn() {
        underTest = new PositionIterator(new BoardSize(1, 8));
        assertTrue(underTest.hasNext());
    }

    @Test
    void testHasNextWhenHasNextRow() {
        underTest = new PositionIterator(new BoardSize(8, 1));
        assertTrue(underTest.hasNext());
    }

    @Test
    void testHadNextWhenHasNoNext() {
        underTest = new PositionIterator(new BoardSize(7, 7));
    }

    @Test
    void testNextWhenHasNext() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                assertEquals(new Position(row, column), underTest.next());
            }
        }
    }
}
