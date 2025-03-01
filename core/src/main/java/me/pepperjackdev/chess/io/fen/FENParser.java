package me.pepperjackdev.chess.io.fen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FENParser {

    private static final String FEN_REGEX =
                "^(?<piecePlacement>(?:[pnbrqkPNBRQK1-8]{1,8}/){7}[pnbrqkPNBRQK1-8]{1,8})\\s" +
                "(?<activeColor>[wb])\\s" +
                "(?<castlingRights>K?Q?k?q?|-)\\s" +
                "(?<enPassantTargetSquare>[a-h][36]|-)\\s" +
                "(?<halfmoveClock>\\d+)\\s" +
                "(?<fullmoveNumber>\\d+)$";

    private static final Pattern FEN_PATTERN = Pattern.compile(FEN_REGEX);

    private final Matcher matcher;

    public FENParser(String fen) {
        this.matcher = FEN_PATTERN.matcher(fen);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid fen: " + fen);
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
}
