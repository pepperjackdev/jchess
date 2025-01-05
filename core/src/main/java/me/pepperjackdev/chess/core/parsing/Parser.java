package me.pepperjackdev.chess.core.parsing;

import me.pepperjackdev.chess.core.game.state.GameState;

public interface Parser {
    GameState parse(String input);
    String serialize(GameState gameState);
}
