package me.pepperjackdev.chess.position;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Position {
    private static final String ALGEBRAIC_NOTATION_REGEX = "(?<file>[a-z]+)(?<rank>[1-9]\\d*)";
    private static final Pattern matcher;

    static {
        matcher = Pattern.compile(ALGEBRAIC_NOTATION_REGEX);
    }

    private final int rank;
    private final int file;

    public Position(int rank, int file) {
        if (!checkInputRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!checkInputFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

        this.rank = rank;
        this.file = file;
    }

    public Position(String algebraicNotation) {
        Matcher match = Position.matcher.matcher(algebraicNotation);

        if (!match.matches()) {
            throw new IllegalArgumentException("Invalid algebraic notation: " + algebraicNotation);
        }

        String rankString = match.group("rank");
        String fileString = match.group("file");

        this.rank = parseRank(rankString);
        this.file = parseFile(fileString);
    }

    private static int parseRank(String rankString) {
        return Integer.parseInt(rankString);
    }

    private static int parseFile(String fileString) {
        return IntStream.iterate(0, i -> i < fileString.length(), i -> i + 1)
                .map(i -> (int)Math.pow(26, i) * ((fileString.charAt(fileString.length() - 1 - i)) - 'a' + 1))
                .sum();
    }

    public static boolean checkInputRank(int rank) {
        return rank >= 1;
    }

    public static boolean checkInputFile(int file) {
        return file >= 1;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
