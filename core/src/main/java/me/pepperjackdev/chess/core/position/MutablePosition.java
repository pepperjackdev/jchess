package me.pepperjackdev.chess.core.position;

public class MutablePosition
    implements Position {

    private int rank;
    private int file;

    public MutablePosition(int rank, int file) {

        if (!Position.isValidRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!Position.isValidFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

        this.rank = rank;
        this.file = file;
    }

    public MutablePosition(Position position) {
        this(position.rank(), position.file());
    }

    @Override
    public int rank() {
        return rank;
    }

    @Override
    public int file() {
        return file;
    }

    public void setRank(int rank) {
        //if (!Position.isValidRank(rank)) {
        //    throw new IllegalArgumentException("Invalid rank: " + rank);
        //}

        this.rank = rank;
    }

    public void setFile(int file) {
        //if (!Position.isValidFile(file)) {
        //    throw new IllegalArgumentException("Invalid file: " + file);
        //}

        this.file = file;
    }

    public void moveByRanks(int ranks) {
        setRank(rank + ranks);
    }

    public void moveByFiles(int files) {
        setFile(file + files);
    }

    public void moveBy(int ranks, int files) {
        moveByRanks(ranks);
        moveByFiles(files);
    }

    public void moveToNextRank() {
        moveByRanks(1);
    }

    public void moveToNextFile() {
        moveByFiles(1);
    }

    public void moveToPrevRank() {
        moveByRanks(-1);
    }

    public void moveToPrevFile() {
        moveByFiles(-1);
    }

    public void skipByRanks(int ranks) {
        moveByRanks(ranks + 1);
    }

    public void skipByFiles(int files) {
        moveByFiles(files + 1);
    }
}
