package me.pepperjackdev.chess.core.game.state;

import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.ImmutablePosition;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StandardGameStateTest {
    private StandardGameState underTest;

    private static final StandardBoard STANDARD_BOARD = new StandardBoard();
    private static final Side ACTIVE_COLOR = Side.WHITE;
    private static final CastlingRights CASTLING_RIGHTS = new CastlingRights(true);
    private static final Position EN_PASSANT_TARGET_SQUARE = null;
    private static final int HALFMOVE_CLOCK = 0;
    private static final int FULLMOVE_NUMBER = 0;

    @BeforeEach
    public void setUp() {
        underTest = new StandardGameState(
                STANDARD_BOARD,
                ACTIVE_COLOR,
                CASTLING_RIGHTS,
                EN_PASSANT_TARGET_SQUARE,
                HALFMOVE_CLOCK,
                FULLMOVE_NUMBER
        );
    }

    @Test
    void testGetPiecePlacementData() {
        assertEquals(STANDARD_BOARD, underTest.getPiecePlacementData());
    }

    @Test
    void testGetActiveColor() {
        assertEquals(ACTIVE_COLOR, underTest.getActiveColor());
    }

    @Test
    void testSetActiveColor() {
        underTest.setActiveColor(Side.BLACK);
        assertEquals(Side.BLACK, underTest.getActiveColor());
    }

    @Test
    void testGetCastlingRights() {
        assertEquals(CASTLING_RIGHTS, underTest.getCastlingRights());
    }

    @Test
    void testGetEnPassantTargetSquare() {
        assertTrue(underTest.getEnPassantTargetSquare().isEmpty());
    }

    @Test
    void testSetEnPassantTargetSquare() {
        Position enPassantTargetSquare = new ImmutablePosition(5, 5);
        underTest.setEnPassantTargetSquare(enPassantTargetSquare);
        assertTrue(underTest.getEnPassantTargetSquare().isPresent());
        assertEquals(enPassantTargetSquare, underTest.getEnPassantTargetSquare().get());
    }

    @Test
    void testGetHalfMoveClock() {
        assertEquals(HALFMOVE_CLOCK, underTest.getHalfMoveClock());
    }

    @Test
    void testSetHalfMoveClock() {
        underTest.setHalfMoveClock(1);
        assertEquals(1, underTest.getHalfMoveClock());
    }

    @Test
    void testGetFullMoveCounter() {
        assertEquals(FULLMOVE_NUMBER, underTest.getFullMoveCounter());
    }

    @Test
    void testSetFullMoveCounter() {
        underTest.setFullMoveCounter(1);
        assertEquals(1, underTest.getFullMoveCounter());
    }
}
