package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.board.StandardBoard;

public class StandardChess
    extends Game {

    public StandardChess() {
        super(new StandardBoard());
    }
}
