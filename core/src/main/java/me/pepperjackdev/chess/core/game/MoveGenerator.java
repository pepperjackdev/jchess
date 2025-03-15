package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Direction;
import me.pepperjackdev.chess.core.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static me.pepperjackdev.chess.core.position.Direction.*;

public class MoveGenerator {

    public static final int NO_MOVE_GENERATION_LIMIT = 0;

    private final Position startPosition;
    private final Board board;
    private final Piece piece;

    public MoveGenerator(Position startPosition, Board board, Piece piece) {
        this.startPosition = startPosition;
        this.board = board;
        this.piece = piece;
    }

    public List<Move> generatePieceMoves() {
        return switch (piece.type()) {
            case PAWN -> generatePawnMoves();
            case KNIGHT -> List.of();
            case BISHOP -> List.of();
            case ROOK -> List.of();
            case QUEEN -> List.of();
            case KING -> List.of();
        };
    }

    protected List<Move> generateMoves(List<SmartDirections> smartDirections) {
        return smartDirections.stream().map(this::generateDirectionMoves).flatMap(List::stream).toList();
    }

    protected List<Move> generateDirectionMoves(SmartDirections smartDirections) {
        List<Move> moves = new ArrayList<>();

        for (Direction direction : smartDirections.directions()) {
            Position currentPosition = startPosition.moved(direction);
            for (int i = 0;
                 (smartDirections.limit() == NO_MOVE_GENERATION_LIMIT || i < smartDirections.limit()) && !board.getBounds().isOutOfBounds(currentPosition);
                 i++, currentPosition = currentPosition.moved(direction)) {

                if (smartDirections.filter() == null || smartDirections.filter().test(board.getPiece(currentPosition))) {
                    moves.add(new Move(startPosition, currentPosition));
                }
            }
        }

        return moves;
    }

    protected List<Move> generatePawnMoves() {
        return switch (piece.side()) {
            case WHITE -> generateMoves(List.of(
                    new SmartDirections(List.of(NORTH), 2, Optional::isEmpty))
            );
            case BLACK -> List.of();
        };
    }
}
