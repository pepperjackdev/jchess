package me.pepperjackdev.chess.core.game.business;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;

import java.util.Optional;

public class MoveHandler {
    private final Board board;

    public MoveHandler(final Board board) {
        this.board = board;
    }

    public void move(Move move) {
        // checking for out of bounds moves
        if (isOutOfBoundsMove(move)) {
            throw new IllegalStateException("Move out of bounds");
        }

        if (isLegalMove(move)) {
            board.setPiece(move.to(), board.getPiece(move.from()).orElse(null));
        }
    }

    public boolean isLegalMove(final Move move) {
        // TODO: tighter requirements needed
        return isPseudoLegalMove(move);
    }

    public boolean isPseudoLegalMove(final Move move) {
        Piece movingPiece = board.getPiece(move.from())
                .orElseThrow(() -> new IllegalStateException("No piece to move at " + move.from()));
        Optional<Piece> targetPiece = board.getPiece(move.to());

        return isPossibleMove(move, movingPiece) &&
                (targetPiece.isPresent() && !isAttackingFriend(movingPiece, targetPiece.get()));
    }

    protected boolean isPossibleMove(final Move move, final Piece movingPiece) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected boolean isAttackingFriend(Piece movingPiece, Piece attackingPiece) {
        return attackingPiece.side() == movingPiece.side();
    }

    protected boolean isOutOfBoundsMove(Move move) {
        return board.isOutOfBoundsPosition(move.from()) ||
                board.isOutOfBoundsPosition(move.to());
    }
}
