package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Board board;

    private Game underTest;

    @BeforeEach
    void setUp() {
        board = new Board(8, 8);
        underTest = new Game(new GameState(new Board(8, 8)));
    }

    @Test
    void testMove() {
        // given
        Piece whitePawn = new Piece(PieceType.PAWN, Side.WHITE);
        Piece blackPawn = new Piece(PieceType.PAWN, Side.BLACK);
        board.setPiece(new Position(1, 1), whitePawn);
        board.setPiece(new Position(2, 3), blackPawn);

        // when
        Optional<Piece> result =  underTest.move(new Move(
                new Position(1, 1),
                new Position(2, 3)
        ));

        // then
        assertTrue(result.isPresent());
        assertEquals(blackPawn, result.get());
        assertTrue(board.getPiece(new Position(2, 3)).isPresent());
        assertFalse(board.getPiece(new Position(1, 1)).isPresent());
    }
}
