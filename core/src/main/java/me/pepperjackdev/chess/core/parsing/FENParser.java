package me.pepperjackdev.chess.core.parsing;

import java.util.regex.Pattern;

/**
 * A Forsyth-Edwards Notation parser
 */
public class FENParser {

    /**
     * The regular expression that represents a FEN string
     */
    private static final String FEN_REGEX = """
            ^(?<piecePlacement>(?:[pnbrqkPNBRQK1-8]{1,8}/){7}[pnbrqkPNBRQK1-8]{1,8})
             (?<activeColor>[wb])
             (?<castlingRights>K?Q?k?q?|-)
             (?<enPassant>[a-h][36]|-)
             (?<halfmoveClock>\\d+)
             (?<fullmoveNumber>\\d+)$
            """;

    private static final Pattern FEN_PATTERN = Pattern.compile(FEN_REGEX);
}
