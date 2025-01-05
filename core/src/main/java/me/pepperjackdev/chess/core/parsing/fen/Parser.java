package me.pepperjackdev.chess.core.parsing.fen;

import me.pepperjackdev.chess.core.game.state.GameState;

public interface Parser {
    GameState parse(String fen);
    String serialize(GameState gameState);
}
