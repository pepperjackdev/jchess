package me.pepperjackdev.chess.core.move;

import me.pepperjackdev.chess.core.position.Position;

import java.util.Objects;

public record Move(Position from, Position to) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(to, move.to) && Objects.equals(from, move.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
