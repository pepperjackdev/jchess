package me.pepperjackdev.chess.core.position;

public class MutablePosition
    implements Position {

    private int rank;
    private int file;

    public MutablePosition(int rank, int file) {

        if (!Position.checkInputRank(rank)) {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }

        if (!Position.checkInputFile(file)) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

        this.rank = rank;
        this.file = file;
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
        this.rank = rank;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public void moveByRanks(int ranks) {
        if (this.rank + ranks < 0) {
            throw new IllegalArgumentException("Not valid position: negative rank");
        }

        this.rank += ranks;
    }

    public void moveByFiles(int files) {
        if (this.file + files < 0) {
            throw new IllegalArgumentException("Not valid position: negative file");
        }

        this.file += files;
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
}
