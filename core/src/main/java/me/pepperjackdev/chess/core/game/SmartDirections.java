package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Direction;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class SmartDirections {
    private final List<Direction> directions;
    private final int limit;
    private final int skip;
    private final Predicate<Optional<Piece>> filter;

    public SmartDirections(List<Direction> directions,
                           int limit,
                           int skip,
                           Predicate<Optional<Piece>> filter) {
        this.directions = directions;
        this.limit = limit;
        this.skip = skip;
        this.filter = filter;
    }

    public SmartDirections(List<Direction> directions,
                           int limit,
                           Predicate<Optional<Piece>> filter) {
        this(directions, limit, 0, filter);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public int getLimit() {
        return limit;
    }

    public int getSkip() {
        return skip;
    }

    public Predicate<Optional<Piece>> getFilter() {
        return filter;
    }
}
