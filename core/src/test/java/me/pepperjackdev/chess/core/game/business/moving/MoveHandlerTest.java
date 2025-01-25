package me.pepperjackdev.chess.core.game.business;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveHandlerTest {
    private MoveHandler underTest;

    @BeforeEach
    void setUp() {
        underTest = new MoveHandler(new Board(8, 8));
    }

    @Test
    void testIsAttackingFriendWhenNotAttackingFriend() {
        Piece moving = new Piece(PieceType.PAWN, Side.WHITE);
        Piece target = new Piece(PieceType.PAWN, Side.BLACK);
        assertFalse(underTest.isAttackingFriend(moving, target));
    }

    @Test
    void testIsAttackingFriendWhenAttackingFriend() {
        Piece moving = new Piece(PieceType.PAWN, Side.WHITE);
        Piece target = new Piece(PieceType.PAWN, Side.WHITE);
        assertTrue(underTest.isAttackingFriend(moving, target));
    }

    @Test
    void testIsOutOfBoundsMoveWhenNotOutOfBoundsMoveMove() {
        Move move = new Move(new Position(0, 0), new Position(7, 7));
        assertFalse(underTest.isOutOfBoundsMove(move));
    }

    @Test
    void testIsOutOfBoundsMoveWhenOutOfBoundsMoveMoveStartPosition() {
        Move move = new Move(new Position(8, 0), new Position(7, 7));
        assertTrue(underTest.isOutOfBoundsMove(move));
    }

    @Test
    void testIsOutOfBoundsMoveWhenOutOfBoundsMoveMoveEndPosition() {
        Move move = new Move(new Position(0, 0), new Position(7, 8));
        assertTrue(underTest.isOutOfBoundsMove(move));
    }
}
