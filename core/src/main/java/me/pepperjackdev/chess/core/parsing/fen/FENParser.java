package me.pepperjackdev.chess.core.parsing.fen;

import me.pepperjackdev.chess.core.board.Board;
import me.pepperjackdev.chess.core.board.StandardBoard;
import me.pepperjackdev.chess.core.game.state.castling.CastlingRights;
import me.pepperjackdev.chess.core.game.state.GameState;
import me.pepperjackdev.chess.core.parsing.Parser;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.piece.Side;
import me.pepperjackdev.chess.core.position.MutablePosition;
import me.pepperjackdev.chess.core.position.Position;

import java.util.Optional;
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

    public char getActiveColorChar() {
        return matcher.group("activeColor").charAt(0); // there's only one char
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
                        parsePieceSide(placementSymbol)
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

    private Side parsePieceSide(char sideChar) {
        return Character.isUpperCase(sideChar) ? Side.WHITE : Side.BLACK;
    }

    public Side parseActiveColor() {
        return switch (getActiveColorChar()) {
            case 'w' -> Side.WHITE;
            case 'b' -> Side.BLACK;
            default -> throw new IllegalStateException("Unexpected value: " + getActiveColorChar());
        };
    }

    public CastlingRights parseCastlingRights() {
        CastlingRights castlingRights = new CastlingRights(false);

        // reading the castling rights chars
        for (char castlingRightSymbol: getCastlingRightsString().toCharArray()) {
            if (castlingRightSymbol == '-') {
                // no one could castle in any way
                break;
            }

            switch (castlingRightSymbol) {
                case 'K' -> castlingRights.setWhiteKingSideCastling(true);
                case 'Q' -> castlingRights.setWhiteQueenSideCastling(true);
                case 'k' -> castlingRights.setBlackKingSideCastling(true);
                case 'q' -> castlingRights.setBlackQueenSideCastling(true);
                default -> throw new IllegalArgumentException("Invalid castling right symbol: " + castlingRightSymbol);
            }
        }

        return castlingRights;
    }

    public Optional<Position> parseEnPassantTargetSquare() {
        if (getEnPassantTargetSquareString().equals("-")) {
            return Optional.empty();
        }

        // TODO: need to implement an Algebraic Notation Parser First
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int parseHalfmoveClock() {
        return Integer.parseInt(getHalfmoveClockString());
    }

    public int parseFullmoveNumber() {
        return Integer.parseInt(getFullmoveNumberString());
    }

    @Override
    public GameState parse(String input) {
        return null;
    }

    @Override
    public String serialize(GameState gameState) {
        return "";
    }
}
