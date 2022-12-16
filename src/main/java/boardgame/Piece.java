package boardgame;

import java.util.Set;

public abstract class Piece {
    protected Board board;
    protected Position position;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract Set<Position> possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves().contains(position);
    }

    public boolean isThereAnyPossibleMove() {
        return possibleMoves().stream().findAny().isPresent();
    }

    protected boolean isValidMove(Position position) {
        return getBoard().positionExists(position) && !getBoard().thereIsAPiece(position);
    }
}
