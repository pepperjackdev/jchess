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

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Board board;

    private Game underTest;

    @BeforeEach
    void setUp() {
        board = new Board(8, 8);
        underTest = new Game(new GameState(board));
    }

    @Test
    void testIsOutOfBoundMoveWhenInBounds() {
        Move inBoundsMove = new Move(new Position(0, 0),
                new Position(0, 0));

        assertFalse(underTest.isOutOfBoundsMove(inBoundsMove));
    }

    @Test
    void testIsOutOfBoundMoveWhenOutOfBoundsStartPosition() {
        Move outOfBoundsMove = new Move(new Position(8, 8),
                new Position(0, 0)
        );

        assertTrue(underTest.isOutOfBoundsMove(outOfBoundsMove));
    }

    @Test
    void testIsOutOfBoundMoveWhenOutOfBoundsEndPosition() {
        Move outOfBoundsMove = new Move(new Position(0, 0),
                new Position(8, 8));

        assertTrue(underTest.isOutOfBoundsMove(outOfBoundsMove));
    }

    @Test
    void testIsAttackingFriendWhenAttackingFriend() {
        Piece moving = new Piece(PieceType.PAWN, Side.WHITE);
        Piece attacked = new Piece(PieceType.PAWN, Side.WHITE);

        assertTrue(underTest.isAttackingFriend(moving, attacked));
    }

    @Test
    void testIsAttackingFriendWhenNotAttackingFriend() {
        Piece moving = new Piece(PieceType.PAWN, Side.WHITE);
        Piece attacked = new Piece(PieceType.PAWN, Side.BLACK);

        assertFalse(underTest.isAttackingFriend(moving, attacked));
    }

    @Test
    void testMoveWithValidMove() {
        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(1, 1);

        Piece movingPiece = new Piece(PieceType.PAWN, Side.WHITE);
        Piece attackedPiece = new Piece(PieceType.PAWN, Side.BLACK);

        board.setPiece(startPosition, movingPiece);
        board.setPiece(endPosition, attackedPiece);

        Optional<Piece> eatenPiece = underTest.move(new Move(startPosition, endPosition));

        assertTrue(eatenPiece.isPresent());
        assertEquals(attackedPiece, eatenPiece.get());

        assertTrue(board.getPiece(endPosition).isPresent());
        assertEquals(movingPiece, board.getPiece(endPosition).get());

        assertFalse(board.getPiece(startPosition).isPresent());
    }
}

