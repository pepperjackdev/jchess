package me.pepperjackdev.chess.core.game;

import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.move.Move;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private final GameState gameState;

    private List<Move> possibleLegalMoves;

    public Game(GameState gameState) {
        this.gameState = gameState;
        this.possibleLegalMoves = generateLegalMoves();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void move(Move move) {
        if (possibleLegalMoves.contains(move)) {
            Piece moving = getGameState().getPiecePlacementData().removePiece(move.from()).orElseThrow(
                    () -> new IllegalStateException("No piece to move")
            );

            getGameState().getPiecePlacementData().setPiece(move.to(), moving);

            this.possibleLegalMoves = generateLegalMoves();
        }
    }

    private List<Move> generateLegalMoves() {
        // FIXME: Actually, these are PSEUDO-LEGAL moves
        return generatePseudoLegalMoves();
    }

    private List<Move> generatePseudoLegalMoves() {
        List<Move> pseudoLegalMoves = new ArrayList<>();
        for (Position position: gameState.getPiecePlacementData()) {
            Optional<Piece> optionalPiece = gameState.getPiecePlacementData().getPiece(position);
            optionalPiece.ifPresent(piece -> pseudoLegalMoves.addAll(new MoveGenerator(position, gameState.getPiecePlacementData(), piece).generatePieceMoves()));
        }

        return pseudoLegalMoves;
    }
}
