package me.pepperjackdev.chess.core.parsing;

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
            throw new IllegalArgumentException("Invalid FEN: " + fen);
        }
    }
}
