package me.pepperjackdev.chess.core.parsing.fen;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.MutablePosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Forsyth-Edwards Notation parser for
 * standard chess.
 */
public class FENParser
    implements Parser {

    /**
     * The regular expression that represents a FEN string
     */
    private static final String FEN_REGEX =
            "(?<piecePlacement>(?:[pnbrqkPNBRQK1-8]{1,8}/){7}[pnbrqkPNBRQK1-8]{1,8})\\s" +  // Piece placement data
                    "(?<activeColor>[wb])\\s" +                                             // Active color
                    "(?<castlingRights>K?Q?k?q?|-)\\s" +                                    // Castling rights
                    "(?<enPassantTargetSquare>[a-h][36]|-)\\s" +                            // En passant target square
                    "(?<halfmoveClock>\\d+)\\s" +                                           // Halfmove clock
                    "(?<fullmoveNumber>\\d+)";                                              // Fullmove number

    private static final Pattern FEN_PATTERN = Pattern.compile(FEN_REGEX);

    private final Matcher matcher;

    /**
     * Builds a FENParser instance initialized with the provided FEN string
     *
     * @param fen the fen string to parse
     */
    public FENParser(String fen) {
        this.matcher = FEN_PATTERN.matcher(fen);

        // checking input FEN integrity
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Malformed FEN: " + fen);
        }
    }

    public String getPiecePlacementDataString() {
        return matcher.group("piecePlacement");
    }

    public String getActiveColorString() {
        return matcher.group("activeColor");
    }

    public String getCastlingRightsString() {
        return matcher.group("castlingRights");
    }

    public String getEnPassantTargetSquareString() {
        return matcher.group("enPassantTargetSquare");
    }

    public String getHalfmoveClockString() {
        return matcher.group("halfmoveClock");
    }

    public String getFullmoveNumberString() {
        return matcher.group("fullmoveNumber");
    }

    public Board parsePiecePlacementData() {
        Board board = new StandardBoard();
        MutablePosition position = new MutablePosition(board.getTopLeftPosition());

        for (String rank: getPiecePlacementDataString().split("/")) {
            for (char placementSymbol: rank.toCharArray()) {

                // If the placement symbol is about skipping ranks
                if (placementSymbol >= '1' && placementSymbol <= '8') {
                    int ranksToSkip = placementSymbol - '0';
                    position.skipByRanks(ranksToSkip);
                    continue;
                }

                // The placement symbol is intended to be translated into a new Piece to place
                Piece piece = new Piece(
                        parsePieceType(placementSymbol),
                        parseSide(placementSymbol)
                );

                board.placeAt(piece, position);

                // Let's increment the position rank index
                position.moveToNextRank();
            }

            // Let's reset the rank position
            position.setRank(0);
            // Let's move to previous (lower) file
            position.moveToPrevFile();
        }

        return board;
    }

    private PieceType parsePieceType(char pieceTypeChar) {
        return switch (Character.toLowerCase(pieceTypeChar)) {
            case 'p' -> PieceType.PAWN;
            case 'n' -> PieceType.KNIGHT;
            case 'b' -> PieceType.BISHOP;
            case 'r' -> PieceType.ROOK;
            case 'q' -> PieceType.QUEEN;
            case 'k' -> PieceType.KING;
            default -> throw new IllegalArgumentException("Invalid piece type: " + pieceTypeChar);
        };
    }

    private Side parseSide(char sideChar) {
        return Character.isUpperCase(sideChar) ? Side.WHITE : Side.BLACK;
    }

    @Override
    public GameState parse(String fen) {
        return null;
    }

    @Override
    public String serialize(GameState gameState) {
        return "";
    }
}
