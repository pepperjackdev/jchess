package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.piece.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StandardGameTest {
    private StandardGame underTest;

    @BeforeEach
    void setUp() {
        underTest = new StandardGame();
    }

    @Test
    void testLoadDefaultGameState() {
        GameState result = underTest.loadDefaultGameState();

        assertNotNull(result.getPiecePlacementData());
        assertEquals(Side.WHITE, result.getActiveColor());
        assertNotNull(result.getCastlingRights());
        assertTrue(result.getEnPassantTargetSquare().isEmpty());
        assertEquals(0, result.getHalfMoveClock());
        assertEquals(0, result.getFullMoveCounter());

        // checking CastlingRights internals
        assertTrue(result.getCastlingRights().blackCanCastleKingSide());
        assertTrue(result.getCastlingRights().blackCanCastleQueenSide());
        assertTrue(result.getCastlingRights().whiteCanCastleKingSide());
        assertTrue(result.getCastlingRights().whiteCanCastleQueenSide());
    }

}
