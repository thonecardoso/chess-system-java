package boardgame;

import java.util.List;

public abstract class Piece {
    protected Board board;
    protected Position position;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public abstract List<String> possibleMovesStr();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {

        var possibleMoves = possibleMoves();

        for (var possibleMovesRow : possibleMoves) {
            for (var possibleMove : possibleMovesRow) {
                if (possibleMove)
                    return true;
            }
        }

        return false;
    }

    protected boolean isValidMove(Position position) {
        return getBoard().positionExists(position) && !getBoard().thereIsAPiece(position);
    }
}
