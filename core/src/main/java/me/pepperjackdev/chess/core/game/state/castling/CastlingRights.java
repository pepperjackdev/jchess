package me.pepperjackdev.chess.core.game.state.castling;

public class CastlingRights {

    static class SideCastlingRights {
        boolean kingSide;
        boolean queenSide;

        public SideCastlingRights(boolean kingSide, boolean queenSide) {
            this.kingSide = kingSide;
            this.queenSide = queenSide;
        }
    }

    private final SideCastlingRights whiteCastlingRights;
    private final SideCastlingRights blackCastlingRights;

    public CastlingRights(
            boolean blackKingSide, boolean blackQueenSide,
            boolean whiteKingSide, boolean whiteQueenSide
    ) {
        this.whiteCastlingRights = new SideCastlingRights(whiteKingSide, whiteQueenSide);
        this.blackCastlingRights = new SideCastlingRights(blackKingSide, blackQueenSide);
    }

    public CastlingRights(boolean allCanCastle) {
        this(allCanCastle, allCanCastle, allCanCastle, allCanCastle);
    }

    public boolean whiteCanCastleKingSide() {
        return this.whiteCastlingRights.kingSide;
    }

    public boolean whiteCanCastleQueenSide() {
        return this.whiteCastlingRights.queenSide;
    }

    public boolean blackCanCastleKingSide() {
        return this.blackCastlingRights.kingSide;
    }

    public boolean blackCanCastleQueenSide() {
        return this.blackCastlingRights.queenSide;
    }

    public void setWhiteKingSideCastling(boolean canCastle) {
        this.whiteCastlingRights.kingSide = canCastle;
    }

    public void setWhiteQueenSideCastling(boolean canCastle) {
        this.whiteCastlingRights.queenSide = canCastle;
    }

    public void setBlackKingSideCastling(boolean canCastle) {
        this.blackCastlingRights.kingSide = canCastle;
    }

    public void setBlackQueenSideCastling(boolean canCastle) {
        this.blackCastlingRights.queenSide = canCastle;
    }

}
