package me.pepperjackdev.chess.core.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MutablePositionTest {
    private MutablePosition underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MutablePosition(1, 1);
    }

    @Test
    public void testConstructorWithGivenRankAndFile() {
        assertEquals(1, underTest.rank());
        assertEquals(1, underTest.file());
    }

    @Test
    public void testConstructorWithGivenInvalidRankAndFile() {
        assertThrows(IllegalArgumentException.class,
                () -> underTest = new MutablePosition(-1, -1));
    }

    @Test
    public void testGetRank() {
        assertEquals(1, underTest.rank());
    }

    @Test
    public void testGetFile() {
        assertEquals(1, underTest.file());
    }

    @Test
    public void testMoveByRank() {
        underTest.moveByRanks(3);
        assertEquals(4, underTest.rank());
    }

    @Test
    public void testMoveByFile() {
        underTest.moveByFiles(3);
        assertEquals(4, underTest.file());
    }

    @Test
    public void testMoveByRankInvalidRank() {
        assertThrows(IllegalArgumentException.class,
                () -> underTest.moveByRanks(-10));
    }

    @Test
    public void testMoveByFileInvalidFile() {
        assertThrows(IllegalArgumentException.class,
                () -> underTest.moveByFiles(-10));
    }

    @Test
    public void testMoveBy() {
        underTest.moveBy(2, 2);
        assertEquals(3, underTest.rank());
        assertEquals(3, underTest.file());
    }

    @Test
    public void testMoveByInvalidRank() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.moveBy(-10, 10);
        });
    }

    @Test
    public void testMoveByInvalidFile() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.moveBy(10, -10);
        });
    }

    @Test
    public void testMoveToNextRank() {
        underTest.moveToNextRank();
        assertEquals(2, underTest.rank());
    }

    @Test
    public void testMoveToNextFile() {
        underTest.moveToNextFile();
        assertEquals(2, underTest.file());
    }

    @Test
    public void testMoveToPrevRank() {
        underTest.moveToPrevRank();
        assertEquals(0, underTest.rank());
    }

    @Test
    public void testMoveToPrevFile() {
        underTest.moveToPrevFile();
        assertEquals(0, underTest.file());
    }

    @Test
    public void testMoveToPrevRankInvalidRank() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.moveToPrevRank(); // now rank = 0
            underTest.moveToPrevRank(); // rank couldn't be a negative number!
        });
    }

    @Test
    public void testMoveToPrevFileInvalidFile() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.moveToPrevFile(); // now file = 0
            underTest.moveToPrevFile(); // file couldn't be a negative number
        });
    }
}
