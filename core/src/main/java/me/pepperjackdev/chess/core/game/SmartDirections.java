package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Direction;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public record SmartDirections(List<Direction> directions, int limit, Predicate<Optional<Piece>> filter) {
}
