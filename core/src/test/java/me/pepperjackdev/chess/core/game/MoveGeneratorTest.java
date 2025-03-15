package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.Bounds;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveGeneratorTest {
    private Position startPosition;
    private Board board;
    private Piece piece;

    private MoveGenerator underTest;

    @BeforeEach
    public void setup() {
        startPosition = new Position(0, 3);
        board = new Board(new Bounds(8, 8));
        piece = new Piece(PieceType.PAWN, Side.WHITE);
        underTest = new MoveGenerator(startPosition, board, piece);
    }

    @Test
    void testGenerateMovesForAlonePawn() {
        assertEquals(List.of(
                new Move(startPosition, new Position(1, 3)), new Move(startPosition, new Position(2, 3))), underTest.generatePieceMoves());
    }

}
