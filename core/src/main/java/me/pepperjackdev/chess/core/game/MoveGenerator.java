package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Direction;
import me.pepperjackdev.chess.core.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static me.pepperjackdev.chess.core.position.Direction.*;

public class MoveGenerator {

    public static final int NO_MOVE_GENERATION_LIMIT = 0;

    public final Predicate<Optional<Piece>> emptyOrOccupiedByOpposite;

    private final Position startPosition;
    private final Board board;
    private final Piece piece;

    public MoveGenerator(Position startPosition, Board board, Piece piece) {
        this.startPosition = startPosition;
        this.board = board;
        this.piece = piece;

        emptyOrOccupiedByOpposite =
                (optionalPiece) -> optionalPiece.isEmpty() || optionalPiece.get().isOppositeOf(piece);
    }

    public List<Move> generate() {
        return switch (piece.type()) {
            case PAWN -> generatePawnMoves();
            case KNIGHT -> generateKnightMoves();
            case BISHOP -> generateBishopMoves();
            case ROOK -> generateRookMoves();
            case QUEEN -> generateQueenMoves();
            case KING -> generateKingMoves();
        };
    }

    protected List<Move> generateAllDirectionsMoves(List<SmartDirections> smartDirections) {
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
                } else {
                    break;
                }
            }
        }

        return moves;
    }

    protected List<Move> generatePawnMoves() {
        return switch (piece.side()) {
            case WHITE -> generateAllDirectionsMoves(List.of(
                    new SmartDirections(List.of(NORTH), 1, Optional::isEmpty),
                    new SmartDirections(List.of(NORTH_WEST, NORTH_EAST), 1,
                            piece -> piece.isPresent() && piece.get().side() == Side.BLACK)
            ));
            case BLACK -> generateAllDirectionsMoves(List.of(
                    new SmartDirections(List.of(SOUTH), 1, Optional::isEmpty),
                    new SmartDirections(List.of(SOUTH_WEST, SOUTH_EAST), 1,
                            piece -> piece.isPresent() && piece.get().side() == Side.WHITE)
            ));
        };
    }

    protected List<Move> generateKnightMoves() {
        return generateDirectionMoves(new SmartDirections(
                List.of(NORTH_NORTH_EAST, EAST_NORTH_EAST, EAST_SOUTH_EST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                        WEST_SOUTH_WEST, WEST_NORTH_WEST, NORTH_NORTH_WEST),
                1,
                emptyOrOccupiedByOpposite));
    }

    protected List<Move> generateBishopMoves() {
        return generateDirectionMoves(new SmartDirections(
                List.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST),
                NO_MOVE_GENERATION_LIMIT,
                emptyOrOccupiedByOpposite
        ));
    }

    protected List<Move> generateRookMoves() {
        return generateDirectionMoves(new SmartDirections(
                List.of(NORTH, EAST, SOUTH, WEST),
                NO_MOVE_GENERATION_LIMIT,
                emptyOrOccupiedByOpposite));
    }

    protected List<Move> generateQueenMoves() {
        return generateDirectionMoves(new SmartDirections(
                List.of(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST),
                NO_MOVE_GENERATION_LIMIT,
                emptyOrOccupiedByOpposite));
    }

    protected List<Move> generateKingMoves() {
        return generateDirectionMoves(new SmartDirections(
                List.of(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST),
                1,
                emptyOrOccupiedByOpposite));
    }
}
