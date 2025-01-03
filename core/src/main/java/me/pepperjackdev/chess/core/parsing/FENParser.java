package me.pepperjackdev.chess.core.parsing;

import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceSide;
import me.pepperjackdev.chess.core.piece.PieceType;
import me.pepperjackdev.chess.core.position.MutablePosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Forsyth-Edwards Notation parser
 */
public class FENParser {

    /**
     * The regular expression that represents a FEN string
     */
    private static final String FEN_REGEX =
            "(?<piecePlacement>(?:[pnbrqkPNBRQK1-8]{1,8}/){7}[pnbrqkPNBRQK1-8]{1,8})\\s" +  // Piece placement data
                    "(?<activeColor>[wb])\\s" +                                             // Active color
                    "(?<castlingAvailability>K?Q?k?q?|-)\\s" +                              // Castling availability
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

    public String getCastlingAvailabilityString() {
        return matcher.group("castlingAvailability");
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

    public Piece[][] getPiecePlacementData(int numberOfRanks, int numberOfFiles) {
        MutablePosition position = new MutablePosition(numberOfRanks - 1, 0);
        Piece[][] piecePlacementData = new Piece[numberOfRanks][numberOfFiles];

        for (String rank: getPiecePlacementDataString().split("/")) {
            for (char placementSymbol: rank.toCharArray()) {
                if (placementSymbol >= '1' && placementSymbol <= '9') {
                    // the placement symbol represents a white space over the chessboard
                    int filesToSkip = placementSymbol - '0';
                    System.out.println("Skipping file " + filesToSkip);
                    position.moveByFiles(filesToSkip);
                    continue;
                }

                Piece toPlace = new Piece(
                        getPieceType(placementSymbol),
                        getPieceSide(placementSymbol)
                );

                System.out.printf("Placing %s at %d-%d\n", toPlace, position.rank(), position.file());
                piecePlacementData[position.rank()][position.file()] = toPlace;

                position.moveToNextFile();
            }

            position.setFile(0);

            if (position.rank() > 0) {
                position.moveToPrevRank();
            }
        }

        return piecePlacementData;
    }

    private PieceType getPieceType(char piecePlacementSymbol) {
        return switch (Character.toLowerCase(piecePlacementSymbol)) {
            case 'p' -> PieceType.PAWN;
            case 'n' -> PieceType.KNIGHT;
            case 'b' -> PieceType.BISHOP;
            case 'r' -> PieceType.ROOK;
            case 'q' -> PieceType.QUEEN;
            case 'k' -> PieceType.KING;
            default ->
                    throw new IllegalStateException("Unexpected value: " + Character.toLowerCase(piecePlacementSymbol));
        };
    }

    private PieceSide getPieceSide(char piecePlacementSymbol) {
        return (Character.isUpperCase(piecePlacementSymbol)) ? PieceSide.WHITE : PieceSide.BLACK;
    }
}
