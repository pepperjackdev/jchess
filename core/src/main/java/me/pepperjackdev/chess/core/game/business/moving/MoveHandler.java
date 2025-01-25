package me.pepperjackdev.chess.core.game.business.moving;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;

import java.util.List;
import java.util.Objects;

public class MoveHandler {
    private final Board board;

    public MoveHandler(final Board board) {
        this.board = board;
    }

    public MoveResult move(Move move) {
        // checking for out of bounds moves
        if (isOutOfBoundsMove(move)) {
            throw new IllegalStateException("Move out of bounds");
        }

        if (isLegalMove(move)) {
            board.setPiece(move.to(), board.removePiece(move.from()).orElse(null));
            return MoveResult.OK;
        }

        return MoveResult.FAILED;
    }

    public boolean isLegalMove(final Move move) {
        // TODO: tighter requirements needed
        return isPseudoLegalMove(move);
    }

    public boolean isPseudoLegalMove(final Move move) {
        Piece movingPiece = board.getPiece(move.from())
                .orElseThrow(() -> new IllegalStateException("No piece to move at " + move.from()));
        Piece targetPiece = board.getPiece(move.to()).orElse(null);

        return !isAttackingFriend(movingPiece, targetPiece)
                && isPossibleMove(move, movingPiece, targetPiece);
    }

    protected boolean isPossibleMove(final Move move, final Piece movingPiece, final Piece targetPiece) {
        return switch (movingPiece.type()) {
            case PAWN -> {
                if (targetPiece != null) {
                    yield switch (movingPiece.side()) {
                        case BLACK -> Objects.equals(move.to(), move.from().moved(-1, 1)) ||
                                        Objects.equals(move.to(), move.from().moved(-1, -1));
                        case WHITE -> Objects.equals(move.to(), move.from().moved(1, 1)) ||
                                Objects.equals(move.to(), move.from().moved(1, -1));
                    };
                } else {
                    yield switch (movingPiece.side()) {
                        case BLACK -> Objects.equals(move.to(), move.from().moved(-1, 0)) ||
                            (Objects.equals(move.to(), move.from().moved(-2, 0)) && move.from().row() == 6);
                        case WHITE -> Objects.equals(move.to(), move.from().moved(1, 0)) ||
                                (Objects.equals(move.to(), move.from().moved(2, 0)) && move.from().row() == 1);
                    };
                }
            }
            case KNIGHT -> {
                yield List.of(
                        move.from().moved(2, 1),
                        move.from().moved(2, -1),
                        move.from().moved(1, 2),
                        move.from().moved(1, -2),
                        move.from().moved(-2, 1),
                        move.from().moved(-2, -1),
                        move.from().moved(-1, 2),
                        move.from().moved(-1, -2)
                ).contains(move.to());
            }
            case BISHOP -> false;
            case ROOK -> false;
            case QUEEN -> false;
            case KING -> false;
        };
    }

    protected boolean isAttackingFriend(Piece movingPiece, Piece targetPiece) {
        return targetPiece != null && targetPiece.side() == movingPiece.side();
    }

    protected boolean isOutOfBoundsMove(Move move) {
        return board.isOutOfBoundsPosition(move.from()) ||
                board.isOutOfBoundsPosition(move.to());
    }
}
