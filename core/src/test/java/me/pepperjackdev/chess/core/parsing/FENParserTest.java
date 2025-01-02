package me.pepperjackdev.chess.core.parsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FENParserTest {
    private FENParser underTest;

    @BeforeEach
    public void setUp() {
        this.underTest = new FENParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    public void testConstructWithGivenFenString() {
        new FENParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    public void testConstructWithGivenInvalidFenString() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FENParser("hello/world/this/is/not/a/fen/string w KQkq - 0 1");
        });
    }
}
